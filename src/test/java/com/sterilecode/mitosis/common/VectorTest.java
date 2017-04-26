package com.sterilecode.mitosis.common;

import static com.sterilecode.mitosis.common.Vector.EPS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : VectorTest.java
 * Created at        : 4/24/17
 * Last modified at  : 4/26/17
 */

public class VectorTest {

  @Test
  public void getX() throws Exception {
    Vector v1 = new Vector();
    assertEquals(0.0, v1.getX(), EPS);
    Vector v2 = new Vector(2, 3);
    assertEquals(2.0, v2.getX(), EPS);
  }

  @Test
  public void getY() throws Exception {
    Vector v1 = new Vector();
    assertEquals(0.0, v1.getY(), EPS);
    Vector v2 = new Vector(2, 3);
    assertEquals(3.0, v2.getY(), EPS);
  }

  @Test
  public void setX() throws Exception {
    Vector v1 = new Vector();
    v1.setX(3.0);
    assertEquals(3.0, v1.getX(), EPS);
  }

  @Test
  public void setY() throws Exception {
    Vector v1 = new Vector();
    v1.setY(3.0);
    assertEquals(3.0, v1.getY(), EPS);
  }

  @Test
  public void equals() throws Exception {
    Vector v1 = new Vector();
    Vector v2 = new Vector();
    assertTrue(v1.equals(v2));
    v1.setX(5.0);
    assertTrue(!v1.equals(v2));
    v2.setX(5.0);
    assertTrue(v1.equals(v2));
    v1.setY(-2.34);
    assertTrue(!v1.equals(v2));
    v2.setY(-2.34);
    assertTrue(v1.equals(v2));
  }

  @Test
  public void add() throws Exception {
    Vector v1 = new Vector(3, 7);
    Vector v2 = new Vector(-2, 1);
    Vector v3 = v1.add(v2);
    assertEquals(1, v3.getX(), EPS);
    assertEquals(8, v3.getY(), EPS);
  }

  @Test
  public void multiply() throws Exception {
    Vector v1 = new Vector(3, 7);
    Vector v3 = v1.multiply(-2.0);
    assertEquals(-6.0, v3.getX(), EPS);
    assertEquals(-14.0, v3.getY(), EPS);
  }

  @Test
  public void rotate() throws Exception {
    Vector v1 = new Vector(3, 7);
    Vector v3 = v1.rotate(Math.PI / 2.0);
    assertEquals(-7.0, v3.getX(), EPS);
    assertEquals(3.0, v3.getY(), EPS);
  }

}