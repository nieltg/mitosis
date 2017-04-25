package com.sterilecode.mitosis.common;

import static java.lang.Math.cos;
import static java.lang.Math.sin;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Vector.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/22/2017
 */

/**
 * A class that represent a 2D vector.
 */
public class Vector {
	private double x;
	private double y;

	/**
	 * Default constructor.
	 */
	public Vector() {
		x = 0;
		y = 0;
	}

	/**
	 * Parameterized constructor.
	 *
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * getX.
	 *
	 * @return x
	 */
	public double getX() {
		return x;
	}

	/**
	 * getY.
	 *
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * setX.
	 *
	 * @param x
	 */
	public void setX(final double x) {
		this.x = x;
	}

	/**
	 * setY.
	 *
	 * @param y
	 */
	public void setY(final double y) {
		this.y = y;
	}

	/**
	 * equals.
	 *
	 * @param vector
	 * @return boolean
	 */
	public boolean equals(final Vector vector) {
		return (x == vector.x && y == vector.y);
	}

	/**
	 * add.
	 *
	 * @param vector
	 * @return Vector
	 */
	public Vector add(final Vector vector) {
		return new Vector(x + vector.x, y + vector.y);
	}

	/**
	 * multiply.
	 *
	 * @param multiplier
	 * @return Vector
	 */
	public Vector multiply(final double multiplier) {
		return new Vector(x * multiplier, y * multiplier);
	}

	/**
	 * rotate.
	 *
	 * @param rotation
	 * @return Vector
	 */
	public Vector rotate(final double rotation) {
		return new Vector(x*cos(rotation) - y*sin(rotation), x*sin(rotation) + y*cos(rotation));
	}
}
