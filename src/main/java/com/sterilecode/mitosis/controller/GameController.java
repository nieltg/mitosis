package com.sterilecode.mitosis.controller;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_MILLISECOND;
import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;
import static java.lang.Math.pow;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.event.LifeChangeEvent;
import com.sterilecode.mitosis.model.event.ShootEvent;
import com.sterilecode.mitosis.model.event.SplitEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;
import com.sterilecode.mitosis.model.gameobject.player.Player;
import com.sterilecode.mitosis.model.gameobject.powerup.PowerUp;
import com.sterilecode.mitosis.plugin.Plugin;
import com.sterilecode.mitosis.view.GameDevice;
import com.sterilecode.mitosis.view.InputState;
import com.sterilecode.mitosis.view.Renderer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.stream.Collectors;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : GameController.java
 * Created at        : 4/22/17
 * Last modified at  : 4/22/17
 */

public class GameController implements Runnable, Observer {

  private static final long TARGET_FPS = 60;
  private static final long TARGET_DELTA_TIME = NANOSECONDS_IN_A_SECOND / TARGET_FPS;
  private static final int INITIAL_HEALTH = 3;

  private GameDevice gameDevice;
  private Renderer renderer;
  private List<GameObject> gameObjects;
  private int score;
  private double fps;
  private long currentTime;
  private boolean isGameRunning;
  private boolean isThreadRunning;
  private List<Player> players;
  private long timeSinceLastEnemySpawn;
  private long timeSinceLastPowerUpSpawn;
  private int life;

  /**
   * Creates a new GameController, ready to run.
   *
   * @param gameDevice An object which provides game IO and display.
   * @param playerCount Number of players for this game (1 or 2 players only).
   */
  public GameController(GameDevice gameDevice, int playerCount) {
    assert playerCount > 0 && playerCount <= 2 : "Game currently only supports 1 or 2 players";
    this.gameDevice = gameDevice;
    renderer = new Renderer(gameDevice);
    System.out.println("Initializing game...");
    initializeGame(playerCount);
  }

  /**
   * Runs main game logic in a GameController thread.
   */
  @Override
  public void run() {
    System.out.println("Starting game loop...");
    isThreadRunning = true;

    // The game loop - variable delta time
    while (isGameRunning) {
      long newTime = System.nanoTime();
      long deltaTime = newTime - currentTime;
      currentTime = newTime;

      fps = (double) NANOSECONDS_IN_A_SECOND / (double) deltaTime;

      processInput();
      updatePhysics(deltaTime);
      detectCollision();
      detectReachedBottom();
      detectOutOfBound();
      spawnEnemies();
      spawnPowerUps();
      renderer.render(gameObjects, life, score, false);

      // If we are going faster than the ideal delta time, we can wait
      long extraTime = TARGET_DELTA_TIME - (System.nanoTime() - currentTime);
      if (extraTime > 0) {
        try {
          Thread.sleep(extraTime / NANOSECONDS_IN_A_MILLISECOND);
        } catch (Exception exception) {
          // Ignore Thread's InterruptedException
        }
      }
    }

    gameOver();
  }

  /**
   * Handles events raised by game objects.
   *
   * @param o the observable object.
   */
  @Override
  public void update(Observable o, Object arg) {
    if (arg instanceof ShootEvent) {
      gameObjects.add(((ShootEvent) arg).getBullet());
    } else if (arg instanceof SplitEvent) {
      gameObjects.add(((SplitEvent) arg).getEnemy());
    } else if (arg instanceof LifeChangeEvent) {
      life += ((LifeChangeEvent) arg).getLifeChange();
    }
  }

  /**
   * Gets the number of frames rendered per second.
   *
   * @return Frames per second.
   */
  public double getFps() {
    return fps;
  }

  /**
   * Resets game to a state ready to be started as a new game.
   */
  private void initializeGame(int playerCount) {
    gameObjects = new ArrayList<>();

    // Add players
    players = new ArrayList<>();
    for (int i = 0; i < playerCount; i++) {
      int centerX = gameDevice.getBufferWidth() / 2;
      int centerY = gameDevice.getBufferHeight() / 2;
      final int playerSpacing = 300;
      int offsetX = -(playerCount - 1) * playerSpacing / 2 + i * playerSpacing;
      Player player = new Player(new Vector(centerX + offsetX, centerY));
      player.addObserver(this);
      gameObjects.add(player);
      players.add(player);
    }

    life = INITIAL_HEALTH;
    score = 0;
    fps = 0.0;
    currentTime = System.nanoTime();
    isGameRunning = true;

    timeSinceLastEnemySpawn = 0;
    timeSinceLastPowerUpSpawn = 0;
  }

  /**
   * Do actions according to input received from the Swing thread.
   */

  private void processInput() {
    InputState inputState = gameDevice.getInputState().clone();

    // TODO: menu key

    // Player 1 input
    if (players.size() >= 1) {
      if (inputState.isPlayer1RotateLeftKeyPressed()) {
        players.get(0).setAngularVelocity(-Player.MAX_ANGULAR_VELOCITY);
      } else if (inputState.isPlayer1RotateRightKeyPressed()) {
        players.get(0).setAngularVelocity(Player.MAX_ANGULAR_VELOCITY);
      } else {
        players.get(0).setAngularVelocity(0.0);
      }
      if (inputState.isPlayer1ShootKeyPressed()) {
        players.get(0).shoot(currentTime);
      }
    }

    // Player 2 input
    if (players.size() >= 2) {
      if (inputState.isPlayer2RotateLeftKeyPressed()) {
        players.get(1).setAngularVelocity(-Player.MAX_ANGULAR_VELOCITY);
      } else if (inputState.isPlayer2RotateRightKeyPressed()) {
        players.get(1).setAngularVelocity(Player.MAX_ANGULAR_VELOCITY);
      } else {
        players.get(1).setAngularVelocity(0.0);
      }
      if (inputState.isPlayer2ShootKeyPressed()) {
        players.get(1).shoot(currentTime);
      }
    }

  }

  /**
   * Update game objects' physical states (position, velocity, etc.) according to deltaTime.
   *
   * @param deltaTime Time elapsed between last frame and current frame.
   */
  private void updatePhysics(long deltaTime) {
    for (GameObject gameObject : gameObjects) {
      gameObject.update(deltaTime);
    }
  }

  /**
   * deleteObjects.
   * Remove all objects from gameObjects that is in mustDelete
   */
  private void deleteObjects(List<GameObject> mustDelete) {
    for (GameObject delete : mustDelete) {
      for (GameObject gameObject : gameObjects) {
        if (delete == gameObject) {
          gameObjects.remove(gameObject);
          break;
        }
      }
    }
  }

  /**
   * Checks game objects for collision (particularly bullets, powerups and enemies).
   */
  private void detectCollision() {
    List<GameObject> enemiesAndPowerUps = gameObjects.stream()
        .filter(x -> x instanceof Enemy || x instanceof PowerUp)
        .collect(Collectors.toList());
    List<Bullet> bullets = gameObjects.stream().filter(x -> x instanceof Bullet)
        .map(y -> (Bullet) y).collect(Collectors.toList());
    List<GameObject> mustDelete = new ArrayList<>();
    for (Bullet bullet : bullets) {
      for (GameObject enemyOrPowerUp : enemiesAndPowerUps) {
        if (pow(bullet.getSize() - enemyOrPowerUp.getSize(), 2)
            <= pow(bullet.getPosition().getX() - enemyOrPowerUp.getPosition().getX(), 2)
            + pow(bullet.getPosition().getY() - enemyOrPowerUp.getPosition().getY(), 2)
            && pow(bullet.getPosition().getX() - enemyOrPowerUp.getPosition().getX(), 2)
            + pow(bullet.getPosition().getY() - enemyOrPowerUp.getPosition().getY(), 2)
            <= pow(bullet.getSize() + enemyOrPowerUp.getSize(), 2)) {
          if (enemyOrPowerUp instanceof PowerUp) {
            ((PowerUp) enemyOrPowerUp).applyPowerUp(bullet.getOwner());
          }
          ++score;
          mustDelete.add(enemyOrPowerUp);
        }
      }
    }
    deleteObjects(mustDelete);
  }

  private void detectReachedBottom() {
    List<Enemy> enemies = gameObjects.stream().filter(x -> x instanceof Enemy).map(y -> (Enemy) y)
        .collect(Collectors.toList());
    List<GameObject> mustDelete = new ArrayList<>();
    for (Enemy enemy : enemies) {
      if (enemy.getPosition().getY() > gameDevice.getBufferHeight()) {
        --life;

        // Check game over
        if (life <= 0) {
          isGameRunning = false;
        }

        mustDelete.add(enemy);
      }
    }
    deleteObjects(mustDelete);
  }

  /**
   * Check if gameobjects' position are out of bound.
   */
  private void detectOutOfBound() {
    List<GameObject> mustDelete = new ArrayList<>();
    for (GameObject gameObject : gameObjects) {
      if (gameObject.getPosition().getX() < 0 || gameObject.getPosition().getY() < 0
          || gameObject.getPosition().getX() > gameDevice.getBufferWidth()
          || gameObject.getPosition().getY() > gameDevice.getBufferHeight()) {
        mustDelete.add(gameObject);
      }
    }
    deleteObjects((mustDelete));
  }

  /**
   * Randomly spawns enemies.
   */
  private void spawnEnemies() {
    if (currentTime - timeSinceLastEnemySpawn > NANOSECONDS_IN_A_MILLISECOND * 500) {
      timeSinceLastEnemySpawn = currentTime;
      List<Class<? extends Enemy>> enemyClasses = ModelManager.getInstance().getListOfEnemy();
      Random random = new Random(System.currentTimeMillis());
      try {
        Enemy newEnemy = enemyClasses.get(random.nextInt(enemyClasses.size()))
            .getConstructor(Vector.class)
            .newInstance(new Vector(random.nextInt(gameDevice.getBufferWidth()), 0));
        newEnemy.addObserver(this);
        gameObjects.add(newEnemy);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
      }
    }
  }

  /**
   * Randomly spawns power ups.
   */
  private void spawnPowerUps() {
    if (currentTime - timeSinceLastPowerUpSpawn > NANOSECONDS_IN_A_MILLISECOND * 5000) {
      timeSinceLastPowerUpSpawn = currentTime;
      List<Class<? extends PowerUp>> powerUpClasses = ModelManager.getInstance().getListOfPowerUp();
      Random random = new Random(System.currentTimeMillis());
      try {
        PowerUp newPowerUp = powerUpClasses.get(random.nextInt(powerUpClasses.size()))
            .getConstructor(Vector.class)
            .newInstance(new Vector(random.nextInt(gameDevice.getBufferWidth()), 0));
        newPowerUp.addObserver(this);
        gameObjects.add(newPowerUp);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
      }
    }
  }

  /**
   * Ends the game - displays game over text and high score.
   */
  private void gameOver() {
    InputState inputState;
    do {
      inputState = gameDevice.getInputState().clone();
      try {
        renderer.render(gameObjects, life, score, true);
        Thread.sleep(TARGET_DELTA_TIME / NANOSECONDS_IN_A_MILLISECOND);
      } catch (InterruptedException e) {
        // Ignore interrupts, doesn't matter anyway.
      }
    } while (!inputState.isMenuKeyPressed());
    isThreadRunning = false;
  }

  public boolean isThreadRunning() {
    return isThreadRunning;
  }
}
