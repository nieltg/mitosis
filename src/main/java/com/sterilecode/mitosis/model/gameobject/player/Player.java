package com.sterilecode.mitosis.model.gameobject.player;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.event.ShootEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_MILLISECOND;
import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

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

  /**
   * Rotation speed
   */
  public static final double MAX_ANGULAR_VELOCITY = 2.0;

  /**
   * Default rotation of the player
   */
  private final double defaultRotation = 0;

  /**
   * Default minimum time required to shoot another bullet
   */
  private final long defaultMinimumTimeToShoot = NANOSECONDS_IN_A_MILLISECOND * 100;

  /**
   * Default speed of the bullet that is shot by the player.
   */
  private final int defaultBulletSpeed = 100;

  /**
   * Player's current minimum time required to shoot another bullet.
   */
  private long minimumTimeToShoot;

  /**
   * Last time the player shoot.
   */
  private long lastShotTime;

  /**
   * Current speed of the bullet that is shot by the player.
   */
  private int bulletSpeed;

  /**
   * Constructor.
   */
  public Player(Vector position) {
    super(position, -Math.PI / 2.0, new Vector(), 0.0, 24, "Player");
    minimumTimeToShoot = defaultMinimumTimeToShoot;
    lastShotTime = 0;
    bulletSpeed = defaultBulletSpeed;
  }

  /**
   * shoot.
   */
  public void shoot(long currentTime) {
    if (currentTime - lastShotTime > minimumTimeToShoot) {
      this.setChanged();
      Vector bulletVelocity = new Vector(bulletSpeed * cos(rotation), bulletSpeed * sin(rotation));
      notifyObservers(new ShootEvent(new Bullet(position, bulletVelocity, this)));
      lastShotTime = currentTime;
    }
  }

  public void update(long deltaTime) {
    rotation += angularVelocity * deltaTime / (double) NANOSECONDS_IN_A_SECOND;
  }

}
