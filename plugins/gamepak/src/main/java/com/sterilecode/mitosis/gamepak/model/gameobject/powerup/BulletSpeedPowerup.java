package com.sterilecode.mitosis.gamepak.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.gameobject.player.Player;
import com.sterilecode.mitosis.model.gameobject.powerup.PowerUp;

/**
 * A power up that increase the player's bullet speed.
 */
public class BulletSpeedPowerup extends PowerUp {
  /**
   * Constructor.
   *
   * @param position
   */
  public BulletSpeedPowerup(Vector position) {
    super(position, new Vector(0, 20), 5, "BulletSpeedPowerUp@GamePak");
    behavior = new StraightBehavior(this);
  }

  /**
   * applyPowerUp.
   *
   * @param player
   */
  public void applyPowerUp(Player player) {
    player.increaseBulletSpeed(10);
  }
}
