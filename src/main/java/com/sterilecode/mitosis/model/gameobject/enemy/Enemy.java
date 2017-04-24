package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * An abstract class that is used to represent enemy.
 */
public abstract class Enemy extends GameObject{
	/**
	 * The behavior of the enemy.
	 */
	protected Behavior behavior;

	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 * @param size
	 * @param viewId
	 */
	public Enemy(Vector position, Vector velocity, double size, String viewId) {
		super(position, velocity, size, viewId);
	}

  /**
   * getBehavior
   *
   * @return behavior.
   */
	public Behavior getBehavior() {
		return behavior;
	}

  /**
   * update.
   * Move enemy according to it's behavior.
   *
   * @param deltaTime
   */
	public final void update(long deltaTime) {
	  behavior.move(deltaTime);
  }

	/**
	 * split.
	 * Split enemy into 2 enemies that are identical to the original.
	 */
	public abstract void split();
}
