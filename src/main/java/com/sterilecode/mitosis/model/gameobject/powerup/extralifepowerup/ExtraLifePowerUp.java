package com.sterilecode.mitosis.model.gameobject.powerup.extralifepowerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.gameobject.player.Player;
import com.sterilecode.mitosis.model.gameobject.powerup.PowerUp;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A PowerUp that gives extra life to the Player.
 */
public class ExtraLifePowerUp extends PowerUp {
	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 */
	public ExtraLifePowerUp(Vector position, Vector velocity) {
		super(position, velocity, 10, "ExtraLifePowerUp");
		behavior = new StraightBehavior(this);
	}

	/**
	 * applyPowerUp
	 * Increase the Player's life by 1.
	 * @param player
	 */
	public void applyPowerUp(Player player) {
		player.increaseLife(1);
	}
}
