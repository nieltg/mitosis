package com.sterilecode.mitosis.model.gameobject;


import com.sterilecode.mitosis.common.Vector;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class GameObject {
	protected Vector position;
	protected Vector velocity;
	protected int size;
	private String viewId;

	public GameObject(Vector position, Vector velocity, int size, String viewId) {
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		this.viewId = viewId;
	}

	public void moveTo(Vector newPosition) {
		position = newPosition;
	}
}
