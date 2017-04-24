package com.sterilecode.mitosis.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.player.Player;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public abstract class PowerUp extends GameObject {
	public Behavior behavior;

	public PowerUp(Vector position, Vector velocity, int size, String viewId) {
		super(position, velocity, size, viewId);
	}

	public abstract void applyPowerUp(Player player);
}
