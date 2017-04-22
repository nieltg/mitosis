package com.sterilecode.mitosis.model.gameobject.player;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class Player extends GameObject {
	private final int defaultHealth = 3;
	private final double defaultRotation = 0;
	private int health;
	private double rotation;

	public Player(Vector position) {
		super(position, new Vector(), 0, "Player");
		health = defaultHealth;
		rotation = defaultRotation;
	}

	public void increaseHealth(int increment) {
		health += increment;
	}

	public void decreaseHealth(int decrement) {
		health -= decrement;
	}

	public void rotate(double rotation) {
		this.rotation += rotation;
	}

}
