package com.sterilecode.mitosis.model.gameobject.player;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.event.ShootEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Reinaldo on 4/22/2017.
 */

/**
 * A class that is used to represent player.
 */
public class Player extends GameObject {
	/**
	 * Default life of the player.
	 */
	private final int defaultLife = 3;

	/**
	 * Default orientation of the player
	 */
	private final double defaultOrientation = 0;

	/**
	 * Default minimum time required to shoot another bullet
	 */
	private final long defaultMinimumTimeToShoot = 1000;

	/**
	 * Default speed of the bullet that is shot by the player.
	 */
	private final int defaultBulletSpeed = 10;

	/**
	 * Player's current life.
	 */
	private int life;

	/**
	 * Player's current orientation.
	 */
	private double orientation;

	/**
	 * Player's current minimum time required to shoot another bullet.
	 */
	private long minimumTimeToShoot;

	/**
	 * Last time the player shoot.
	 */
	private long lastShotTime;

	/**
	 * Current speed of the bullet that is shot by the player.
	 */
	private int bulletSpeed;

	/**
	 * Velocity of the bullet shot by the player.
	 */
	private Vector bulletVelocity;

	/**
	 * Constructor.
	 *
	 * @param position
	 */
	public Player(Vector position) {
		super(position, new Vector(), 0, "Player");
		life = defaultLife;
		orientation = defaultOrientation;
		minimumTimeToShoot = defaultMinimumTimeToShoot;
		lastShotTime = 0;
		bulletSpeed = defaultBulletSpeed;
		bulletVelocity = new Vector(bulletSpeed * cos(orientation), bulletSpeed * sin(orientation));
	}

	/**
	 * increaseLife.
	 *
	 * @param increment
	 */
	public void increaseLife(int increment) {
		life += increment;
	}

	/**
	 * decreaseLife.
	 *
	 * @param decrement
	 */
	public void decreaseLife(int decrement) {
		life -= decrement;
	}


	/**
	 * rotate.
	 *
	 * @param rotation
	 */
	public void rotate(double rotation) {
		this.orientation += rotation;
		bulletVelocity = bulletVelocity.rotate(rotation);
	}

	/**
	 * shoot.
	 *
	 * @param currentTime
	 */
	public void shoot(long currentTime) {
		if (currentTime - lastShotTime > minimumTimeToShoot) {
			notifyObservers(new ShootEvent(new Bullet(position, bulletVelocity)));
		}
		lastShotTime = currentTime;
	}
}
