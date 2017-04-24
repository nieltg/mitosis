package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A class that is thrown if the Player.shoot() method is invoked
 */
public class ShootEvent {
	/**
	 * Bullet that is shot by the Player.
	 */
	private Bullet bullet;

	/**
	 * Constructor.
	 *
	 * @param bullet
	 */
	public ShootEvent(Bullet bullet) {
		this.bullet = bullet;
	}

	/**
	 * getBullet.
	 *
	 * @return Bullet
	 */
	public Bullet getBullet() {
		return bullet;
	}
}
