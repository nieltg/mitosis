package com.sterilecode.mitosis.model.gameobject.powerup;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.event.LifeChangeEvent;
import com.sterilecode.mitosis.model.gameobject.player.Player;
import com.sterilecode.mitosis.model.gameobject.powerup.PowerUp;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : ExtraLifePowerUp.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/22/2017
 */

/**
 * A PowerUp that gives extra life to the Player.
 */
public class ExtraLifePowerUp extends PowerUp {
	/**
   *
	 * Constructor.
	 *
	 * @param position
	 */
	public ExtraLifePowerUp(Vector position) {
		super(position, new Vector(0, 40), 5, "ExtraLifePowerUp");
		behavior = new StraightBehavior(this);
	}

	/**
	 * applyPowerUp
	 * Increase the Player's life by 1.
	 * @param player
	 */
	public void applyPowerUp(Player player) {
	  this.setChanged();
	  notifyObservers(new LifeChangeEvent(1));
	}
}
