package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * An abstract class that is used to represent enemy.
 */
public abstract class Enemy extends GameObject {

  protected Behavior behavior;

  /**
   * Constructor.
   */
  public Enemy(Vector position, Vector velocity, double size, String viewId) {
    super(position, 0.0, velocity, 0.0, size, viewId);
  }

  /**
   * getBehavior
   *
   * @return behavior.
   */
  public Behavior getBehavior() {
    return behavior;
  }

  /**
   * update.
   * Move enemy according to it's behavior.
   */
  public final void update(long deltaTime) {
    behavior.move(deltaTime);
  }

  /**
   * split.
   * Split enemy into 2 enemies that are identical to the original.
   */
  public abstract void split();
}
