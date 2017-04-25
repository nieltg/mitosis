package com.sterilecode.mitosis.model.gameobject.bullet;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.player.Player;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Bullet.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/24/2017
 */

/**
 * A class that is used to represent a bullet.
 */
public class Bullet extends GameObject {
  private Player owner;

  /**
   * Constructor.
   */
  public Bullet(Vector position, Vector velocity, Player owner) {
    super(position, 0.0, velocity, 0.0, 2, "Bullet");
    this.owner = owner;
  }

  /**
   * getOwner
   * @return Player
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * Move bullet according to its velocity.
   * @param deltaTime Time between updates in nanoseconds.
   */
  public void update(long deltaTime) {
    setPosition(getPosition().add(getVelocity().multiply(deltaTime / (double) NANOSECONDS_IN_A_SECOND)));
  }
}
