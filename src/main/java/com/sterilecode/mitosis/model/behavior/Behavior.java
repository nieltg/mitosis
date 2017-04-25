package com.sterilecode.mitosis.model.behavior;

import com.sterilecode.mitosis.model.gameobject.GameObject;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Behavior.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/22/2017
 */

/**
 * A class that define a behavior of an Enemy or PowerUp
 */
public abstract class Behavior {
	protected GameObject owner;

	/**
	 * Constructor.
	 *
	 * @param owner
	 */
	public Behavior(GameObject owner) {
		this.owner = owner;
	}

	/**
	 * move.
	 * An abstract method.
	 *
	 * @param deltaTime in nanoseconds.
	 */
	public abstract void move(long deltaTime);
}
