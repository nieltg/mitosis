package com.sterilecode.mitosis.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.event.LifeChangeEvent;
import com.sterilecode.mitosis.model.gameobject.player.Player;

/**
 * A PowerUp that gives extra life to the Player.
 */
public class ExtraLifePowerUp extends PowerUp {

  /**
   * Constructor.
   */
  public ExtraLifePowerUp(Vector position) {
    super(position, new Vector(0, 40), 5, "ExtraLifePowerUp");
    behavior = new StraightBehavior(this);
  }

  /**
   * applyPowerUp
   * Increase the Player's life by 1.
   */
  public void applyPowerUp(Player player) {
    this.setChanged();
    notifyObservers(new LifeChangeEvent(1));
  }
}
