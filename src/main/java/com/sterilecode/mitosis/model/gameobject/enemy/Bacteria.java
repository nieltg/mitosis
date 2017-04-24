package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.event.SplitEvent;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * Enemy that has StraightBehavior
 */
public class Bacteria extends Enemy {
	/**
	 * Constructor.
	 *
	 * @param position
	 */
	public Bacteria(Vector position) {
		super(position, new Vector(0, 10), 5, "Bacteria");
		behavior = new StraightBehavior(this);
	}

	/**
	 * split.
	 */
	public void split() {
		notifyObservers(new SplitEvent(new Bacteria(position)));
	}
}
