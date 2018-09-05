package com.sterilecode.mitosis.model.behavior;

import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * A class that define a behavior of an Enemy or PowerUp.
 */
public abstract class Behavior {

  protected GameObject owner;

  /**
   * Constructor.
   */
  public Behavior(GameObject owner) {
    this.owner = owner;
  }

  /**
   * move.
   * An abstract method.
   *
   * @param deltaTime in nanoseconds.
   */
  public abstract void move(long deltaTime);
}
