package com.sterilecode.mitosis.model.gameobject.bullet;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.gameobject.GameObject;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A class that is used to represent a bullet.
 */
public class Bullet extends GameObject{
	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 */
	public Bullet(Vector position, Vector velocity) {
		super(position, velocity, 2, "Bullet");
	}

  /**
   * update.
   * Move bullet accroding to it's velocity.
   * @param deltaTime
   */
  public void update(long deltaTime) {
    setPosition(getPosition().add(getVelocity().multiply(deltaTime/NANOSECONDS_IN_A_SECOND)));
  }
}
