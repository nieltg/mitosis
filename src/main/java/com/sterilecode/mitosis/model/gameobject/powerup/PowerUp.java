package com.sterilecode.mitosis.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.player.Player;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * An abstract class that is used to represent power up.
 */
public abstract class PowerUp extends GameObject {
	/**
	 * Behavior of the power up.
	 */
	public Behavior behavior;

	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 * @param size
	 * @param viewId
	 */
	public PowerUp(Vector position, Vector velocity, int size, String viewId) {
		super(position, velocity, size, viewId);
	}

	/**
	 * applyPowerUp.
	 * An abstract method.
	 * @param player
	 */
	public abstract void applyPowerUp(Player player);
}
