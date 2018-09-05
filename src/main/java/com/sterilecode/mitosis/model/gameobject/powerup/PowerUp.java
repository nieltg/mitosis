package com.sterilecode.mitosis.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.player.Player;

/**
 * An abstract class that is used to represent power up.
 */
public abstract class PowerUp extends GameObject {

  public Behavior behavior;

  /**
   * Constructor.
   */
  public PowerUp(Vector position, Vector velocity, int size, String viewId) {
    super(position, 0.0, velocity, 0.0, size, viewId);
  }

  /**
   * getBehavior.
   *
   * @return behavior
   */
  public Behavior getBehavior() {
    return behavior;
  }

  /**
   * update.
   * Move power up according to it's behavior.s
   */
  public final void update(long deltaTime) {
    behavior.move(deltaTime);
  }

  /**
   * applyPowerUp.
   * An abstract method.
   */
  public abstract void applyPowerUp(Player player);
}
