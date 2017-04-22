package com.sterilecode.mitosis.model.gameobject;


import com.sterilecode.mitosis.common.Vector;

import java.util.Observable;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class GameObject extends Observable{
	protected Vector position;
	protected Vector velocity;
	protected int size;
	protected String viewId;

	public GameObject(Vector position, Vector velocity, int size, String viewId) {
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		this.viewId = viewId;
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getVelocity() {
		return position;
	}

	public String getViewId() {
		return viewId;
	}

	public void setPosition(Vector newPosition) {
		position = newPosition;
	}

	public void setVelocity(Vector newVelocity) {
		velocity = newVelocity;
	}
}
