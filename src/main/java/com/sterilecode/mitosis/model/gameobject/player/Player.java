package com.sterilecode.mitosis.model.gameobject.player;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_MILLISECOND;
import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.event.ShootEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Player.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/24/2017
 */

/**
 * A class that is used to represent player.
 */
public class Player extends GameObject {

  public static final double MAX_ANGULAR_VELOCITY = 2.0;
  private static final int DEFAULT_LIFE = 3;
  private static final double DEFAULT_ROTATION = 0;
  private static final long DEFAULT_MINIMUM_TIME_TO_SHOOT = NANOSECONDS_IN_A_MILLISECOND * 100;
  private static final int DEFAULT_BULLET_SPEED = 100;
  private int life;
  private long lastShotTime;
  private int bulletSpeed;
  private long minimumTimeToShoot;

  /**
   * Constructor.
   */
  public Player(Vector position) {
    super(position, -Math.PI / 2.0, new Vector(), 0.0, 24, "Player");
    life = DEFAULT_LIFE;
    minimumTimeToShoot = DEFAULT_MINIMUM_TIME_TO_SHOOT;
    lastShotTime = 0;
    bulletSpeed = DEFAULT_BULLET_SPEED;
  }

  /**
   * Increases this player's bullet speed.
   * @param increment The amount by which to increase the bullet speed.
   */
  public void increaseBulletSpeed(int increment) {
    bulletSpeed += increment;
  }

  /**
   * Player shoot action.
   * @param currentTime The current game time in nanoseconds.
   */
  public void shoot(long currentTime) {
    if (currentTime - lastShotTime > minimumTimeToShoot) {
      this.setChanged();
      Vector bulletVelocity = new Vector(bulletSpeed * cos(rotation), bulletSpeed * sin(rotation));
      notifyObservers(new ShootEvent(new Bullet(position, bulletVelocity, this)));
      lastShotTime = currentTime;
    }
  }

  /**
   * update.
   * Change angular velocity
   */
  public void update(long deltaTime) {
    rotation += angularVelocity * deltaTime / (double) NANOSECONDS_IN_A_SECOND;
  }

}
