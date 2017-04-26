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
   * @param position Creates a bullet speed power up.
   */
  public BulletSpeedPowerup(Vector position) {
    super(position, new Vector(0, 20), 5, "BulletSpeedPowerUp@GamePak");
    behavior = new StraightBehavior(this);
  }

  /**
   * Applies this power up's effects to a player.
   * @param player The player on which to apply this power up's effects.
   */
  public void applyPowerUp(Player player) {
    player.increaseBulletSpeed(10);
  }
}
