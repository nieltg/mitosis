package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.bullet.Bullet;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class ShootEvent {
	private Bullet bullet;

	public ShootEvent(Bullet bullet) {
		this.bullet = bullet;
	}

	public Bullet getBullet() {
		return bullet;
	}
}
