package com.sterilecode.mitosis.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.event.LifeChangeEvent;
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
	 */
	public ExtraLifePowerUp(Vector position) {
		super(position, new Vector(0, 20), 5, "ExtraLifePowerUp");
		behavior = new StraightBehavior(this);
	}

	/**
	 * applyPowerUp
	 * Increase the Player's life by 1.
	 * @param player
	 */
	public void applyPowerUp(Player player) {
		notifyObservers(new LifeChangeEvent(1));
	}
}
