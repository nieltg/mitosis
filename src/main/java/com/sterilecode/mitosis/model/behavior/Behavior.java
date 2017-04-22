package com.sterilecode.mitosis.model.behavior;

import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public abstract class Behavior {
	protected GameObject owner;

	public Behavior(GameObject owner) {
		this.owner = owner;
	}

	public abstract void move(long deltaTime);
}
