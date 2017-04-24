package com.sterilecode.mitosis.common;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by Reinaldo on 4/22/2017.
 */
public class Vector {
	private double x;
	private double y;

	public Vector() {
		x = 0;
		y = 0;
	}

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getAbsis() {
		return x;
	}

	public double getOrdinat() {
		return y;
	}

	public void setAbsis(final double x) {
		this.x = x;
	}

	public void setOrdinat(final double y) {
		this.y = y;
	}

	public boolean equals(final Vector vector) {
		return (x == vector.x && y == vector.y);
	}

	public Vector add(final Vector vector) {
		return new Vector(x + vector.x, y + vector.y);
	}

	public Vector multiply(final double multiplier) {
		return new Vector(x * multiplier, y * multiplier);
	}

	public Vector rotate(final double rotation) {
		return new Vector(x*cos(rotation) - y*sin(rotation), x*sin(rotation) + y*cos(rotation));
	}
}
