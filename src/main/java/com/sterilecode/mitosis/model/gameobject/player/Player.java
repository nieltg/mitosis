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
public class Player extends GameObject {
	private final int defaultLife = 3;
	private final double defaultRotation = 0;
	private final long defaultMinimumTimeToShoot = 1000;
	private final int defaultBulletSpeed = 10;
	private int life;
	private double orientation;
	private long minimumTimeToShoot;
	private long lastShotTime;
	private int bulletSpeed;
	private Vector bulletVelocity;

	public Player(Vector position) {
		super(position, new Vector(), 0, "Player");
		life = defaultLife;
		orientation = defaultRotation;
		minimumTimeToShoot = defaultMinimumTimeToShoot;
		lastShotTime = 0;
		bulletSpeed = defaultBulletSpeed;
		bulletVelocity = new Vector(bulletSpeed * cos(orientation), bulletSpeed * sin(orientation));
	}

	public void increaseLife(int increment) {
		life += increment;
	}

	public void decreaseLife(int decrement) {
		life -= decrement;
	}

	public void rotate(double rotation) {
		this.orientation += rotation;
		bulletVelocity = bulletVelocity.rotate(rotation);
	}

	public void shoot(long currentTime) {
		if (currentTime - lastShotTime > minimumTimeToShoot) {
			notifyObservers(new ShootEvent(new Bullet(position, bulletVelocity)));
		}
	}
}
