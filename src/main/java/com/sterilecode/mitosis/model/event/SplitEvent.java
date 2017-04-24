package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A class that is thrown when the Enemy.split() method is invoked.
 */
public class SplitEvent {
	/**
	 * The new enemy that is splitted from the original
	 */
	private Enemy enemy;

	/**
	 * Constructor.
	 *
	 * @param enemy
	 */
	public SplitEvent(Enemy enemy) {
		this.enemy = enemy;
	}

	/**
	 * getEnemy
	 *
	 * @return
	 */
	public Enemy getEnemy() {
		return enemy;
	}
}
