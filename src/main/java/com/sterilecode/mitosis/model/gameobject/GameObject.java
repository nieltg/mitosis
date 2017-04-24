package com.sterilecode.mitosis.model.gameobject;

import com.sterilecode.mitosis.common.Vector;
import java.util.Observable;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * An abstract class that is the super class of all objects in the game.
 */
public abstract class GameObject extends Observable{
	/**
	 * Position of the object in 2D vector.
	 */
	protected Vector position;
	/**
	 * Velocity of the object in 2D vector.
	 */
	protected Vector velocity;

	/**
	 * Size of the object in integer.
	 */
	protected double size;

	/**
	 * An id used to represent certain view.
	 */
	protected String viewId;

	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 * @param size
	 * @param viewId
	 */
	public GameObject(Vector position, Vector velocity, double size, String viewId) {
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		this.viewId = viewId;
	}


	/**
	 * getPosition.
	 *
	 * @return position;
	 */
	public Vector getPosition() {
		return position;
	}

	/**
	 * getVelocity.
	 *
	 * @return velocity;
	 */
	public Vector getVelocity() {
		return velocity;
	}

	/**
	 * getSize.
	 *
	 * @return size;
	 */
	public double getSize() {
		return size;
	}


	/**
	 * getViewId.
	 *
	 * @return viewId
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * setPosition.
	 *
	 * @param newPosition
	 */
	public void setPosition(Vector newPosition) {
		position = newPosition;
	}

	/**
	 * setVelocity.
	 *
	 * @param newVelocity
	 */
	public void setVelocity(Vector newVelocity) {
		velocity = newVelocity;
	}
}
