package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class SplitEvent {
	private Enemy enemy;

	public SplitEvent(Enemy enemy) {
		this.enemy = enemy;
	}

	public Enemy getEnemy() {
		return enemy;
	}
}
