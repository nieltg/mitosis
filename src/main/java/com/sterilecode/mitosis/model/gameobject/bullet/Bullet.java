package com.sterilecode.mitosis.model.gameobject.bullet;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.player.Player;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A class that is used to represent a bullet.
 */
public class Bullet extends GameObject{
  /**
   * Owner of the bullet.
   */
  private Player owner;

	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 */
	public Bullet(Vector position, Vector velocity, Player owner) {
		super(position, velocity, 2, "Bullet");
		this.owner = owner;
	}

  /**
   * getOwner
   * @return Player
   */
	public Player getOwner() {
	  return owner;
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
