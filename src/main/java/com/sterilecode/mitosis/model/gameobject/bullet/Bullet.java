package com.sterilecode.mitosis.model.gameobject.bullet;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class Bullet extends GameObject{
	public Bullet(Vector position, Vector velocity) {
		super(position, velocity, 2, "Bullet");
	}
}
