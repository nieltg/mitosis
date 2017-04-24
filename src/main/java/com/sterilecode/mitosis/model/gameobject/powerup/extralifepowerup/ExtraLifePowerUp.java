package com.sterilecode.mitosis.model.gameobject.powerup.extralifepowerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.gameobject.player.Player;
import com.sterilecode.mitosis.model.gameobject.powerup.PowerUp;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class ExtraLifePowerUp extends PowerUp {
	public ExtraLifePowerUp(Vector position, Vector velocity) {
		super(position, velocity, 10, "ExtraLifePowerUp");
		behavior = new StraightBehavior(this);
	}

	public void applyPowerUp(Player player) {
		player.increaseLife(1);
	}
}
