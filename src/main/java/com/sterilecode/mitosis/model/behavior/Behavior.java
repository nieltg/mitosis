package com.sterilecode.mitosis.model.behavior;

import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A class that define a behavior of an Enemy or PowerUp
 */
public abstract class Behavior {
	/**
	 * The ownder of this behavior.
	 */
	protected GameObject owner;

	/**
	 * Constructor.
	 *
	 * @param owner
	 */
	public Behavior(GameObject owner) {
		this.owner = owner;
	}

	/**
	 * move.
	 * An abstract method.
	 *
	 * @param deltaTime in nanoseconds.
	 */
	public abstract void move(long deltaTime);
}
