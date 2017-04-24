package com.sterilecode.mitosis.controller;

import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.view.Renderer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

  // TODO: move to common.constants
  private final long NANOSECONDS_IN_A_SECOND = 1000000000;
  private final long NANOSECONDS_IN_A_MILLISECOND = 1000000;
  private final long TARGET_FPS = 60;
  private final long TARGET_DELTA_TIME = NANOSECONDS_IN_A_SECOND / TARGET_FPS;

  private GameDevice gameDevice;
  private Renderer renderer;
  private List<GameObject> gameObjects;
  private int score;
  private double fps;
  private long currentTime;
  private boolean isGameRunning;

  /**
   * Creates a new GameController, ready to run.
   * @param gameDevice An object which provides game IO and display.
   */
  public GameController(GameDevice gameDevice) {
    this.gameDevice = gameDevice;
    renderer = new Renderer(gameDevice);
    initializeGame();
  }

  /**
   * Runs main game logic in a GameController thread.
   */
  @Override
  public void run() {

    initializeGame();

    // The game loop - variable delta time
    while (isGameRunning) {
      long newTime = System.nanoTime();
      long deltaTime = newTime - currentTime;
      currentTime = newTime;
      fps = (double) NANOSECONDS_IN_A_SECOND / (double) deltaTime;

      processInput();
      updatePhysics(deltaTime);
      collisionDetection();
      spawnEnemies();
      spawnPowerUps();
      renderer.render(gameObjects);

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
   * @param o the observable object.
   */
  @Override
  public void update(Observable o, Object arg) {
    // TODO: ShootEvent, SplitEvent handlers (add new objects to gameObject too)
  }

  /**
   * Gets the number of frames rendered per second.
   * @return Frames per second.
   */
  public double getFps() {
    return fps;
  }

  /**
   * Resets game to a state ready to be started as a new game.
   */
  private void initializeGame() {

    // TODO: seed random, add player to gameObjects
    gameObjects = new ArrayList<>(); // TODO: investigate performance when deleting
    score = 0;

    fps = 0.0;
    currentTime = System.nanoTime();
    isGameRunning = true;
  }

  /**
   * Do actions according to input received from the Swing thread.
   */
  private void processInput() {
    // TODO
  }

  /**
   * Update game objects' physical states (position, velocity, etc.) according to deltaTime.
   * @param deltaTime Time elapsed between last frame and current frame.
   */
  private void updatePhysics(long deltaTime) {
    for (GameObject gameObject : gameObjects) {
      //gameObject.update(deltaTime); // TODO
    }
  }

  /**
   * Checks game objects for collision (particularly bullets, powerups and enemies).
   */
  private void collisionDetection() {
    // TODO
  }

  /**
   * Randomly spawns enemies.
   */
  private void spawnEnemies() {
    // TODO
  }

  /**
   * Randomly spawns power ups.
   */
  private void spawnPowerUps() {
    // TODO
  }

  /**
   * Ends the game - displays game over text and high score.
   */
  private void gameOver() {
    // TODO
  }
}
