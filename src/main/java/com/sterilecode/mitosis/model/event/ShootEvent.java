package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : ShootEvent.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/24/2017
 */

/**
 * A class that is thrown if the Player.shoot() method is invoked
 */
public class ShootEvent {

  /**
   * Bullet that is shot by the Player.
   */
  private Bullet bullet;

  /**
   * Constructor.
   */
  public ShootEvent(Bullet bullet) {
    this.bullet = bullet;
  }

  /**
   * getBullet.
   *
   * @return Bullet
   */
  public Bullet getBullet() {
    return bullet;
  }
}
