package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : SplitEvent.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/22/2017
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
		enemy.setSplitting(1);
	}

	/**
	 * getEnemy
	 *
	 * @return Enemy
	 */
	public Enemy getEnemy() {
		return enemy;
	}
}
