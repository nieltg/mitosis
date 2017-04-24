package com.sterilecode.mitosis.common;

import org.junit.Test;
import java.lang.Math;

import static org.junit.Assert.*;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : ${NAME}.java
 * Created at        : 4/24/17
 * Last modified at  : 4/24/17
 */
public class VectorTest {

  Vector v1 = new Vector();
  Vector v2 = new Vector(3, 4);
  private static final double EPS = 1e-9;

  @Test
  public void getX() throws Exception {
    assertEquals(0.0d, v1.getX(), EPS);
  }

  @Test
  public void getY() throws Exception {
    assertEquals(0.0d, v1.getY(), EPS);
  }

  @Test
  public void setX() throws Exception {
    v1.setX(3.0d);
    assertEquals(3.0d, v1.getX(), EPS);
  }

  @Test
  public void setY() throws Exception {
    v1.setY(4.0d);
    assertEquals(4.0d, v1.getY(), EPS);
  }

  @Test
  public void equals() throws Exception {
    Vector v3 = new Vector();
    assertTrue(v1.equals(v3));
  }

  @Test
  public void add() throws Exception {
    Vector v3 = new Vector();
    v3 = v1.add(v2);
    assertEquals(3, v3.getX(), EPS);
  }

  @Test
  public void multiply() throws Exception {
    Vector v3 = new Vector();
    v3 = v2.multiply(3);
    assertEquals(9, v3.getX(), EPS);
    assertEquals(12, v3.getY(), EPS);
  }

  @Test
  public void rotate() throws Exception {
    Vector v3 = new Vector();
    v3 = v2.rotate(0.5 * Math.PI);
    assertEquals(-4, v3.getX(), EPS);
    assertEquals(3, v3.getY(), EPS);
  }

}