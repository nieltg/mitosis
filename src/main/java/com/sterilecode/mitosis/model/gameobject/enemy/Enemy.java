package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.event.SplitEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public abstract class Enemy extends GameObject{
	protected Behavior behavior;

	public Enemy(Vector position, Vector velocity, int size, String viewId) {
		super(position, velocity, size, viewId);
	}

	public abstract void split();
}
