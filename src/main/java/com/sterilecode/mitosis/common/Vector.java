package com.sterilecode.mitosis.common;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * A class that represent a 2D vector.
 */
public class Vector {

  public static final double EPS = 1e-9;
  private double vectorX;
  private double vectorY;

  /**
   * Default constructor.
   */
  public Vector() {
    vectorX = 0;
    vectorY = 0;
  }

  /**
   * Parameterized constructor.
   */
  public Vector(double vectorX, double vectorY) {
    this.vectorX = vectorX;
    this.vectorY = vectorY;
  }

  /**
   * getX.
   *
   * @return vectorX
   */
  public double getX() {
    return vectorX;
  }

  /**
   * getY.
   *
   * @return vectorY
   */
  public double getY() {
    return vectorY;
  }

  /**
   * setX.
   */
  public void setX(final double vectorX) {
    this.vectorX = vectorX;
  }

  /**
   * setY.
   */
  public void setY(final double vectorY) {
    this.vectorY = vectorY;
  }

  /**
   * equals.
   *
   * @return boolean
   */
  public boolean equals(final Vector vector) {
    return (Math.abs(vectorX - vector.vectorX) < EPS && Math.abs(vectorY - vector.vectorY) < EPS);
  }

  /**
   * add.
   *
   * @return Vector
   */
  public Vector add(final Vector vector) {
    return new Vector(vectorX + vector.vectorX, vectorY + vector.vectorY);
  }

  /**
   * multiply.
   *
   * @return Vector
   */
  public Vector multiply(final double multiplier) {
    return new Vector(vectorX * multiplier, vectorY * multiplier);
  }

  /**
   * rotate.
   *
   * @return Vector
   */
  public Vector rotate(final double rotation) {
    return new Vector(vectorX * cos(rotation) - vectorY * sin(rotation),
        vectorX * sin(rotation) + vectorY
            * cos(rotation));
  }
}
