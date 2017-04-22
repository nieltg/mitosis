package com.sterilecode.mitosis.common;

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

	public double getAbis() {
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

	public void translate(final double x, final double y) {
		this.x += x;
		this.y += y;
	}
}
