package com.sterilecode.mitosis.model.gameobject.enemy.enemya;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.event.SplitEvent;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * Enemy that has StraightBehavior
 */
public class EnemyA extends Enemy {
	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 * @param size
	 */
	public EnemyA(Vector position, Vector velocity, double size) {
		super(position, velocity, size, "EnemyA");
		behavior = new StraightBehavior(this);
	}

	/**
	 * split.
	 */
	public void split() {
		notifyObservers(new SplitEvent(new EnemyA(position, velocity, size)));
	}
}
