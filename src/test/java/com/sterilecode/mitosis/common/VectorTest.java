package com.sterilecode.mitosis.common;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wennyyustalim on 4/24/17.
 */
public class VectorTest {
    Vector v1 = new Vector();
    Vector v2 = new Vector(3,4);
    @Test
    public void getX() throws Exception {
        assertEquals(0.0d, v1.getX(), 0);
    }

    @Test
    public void getY() throws Exception {
        assertEquals(0.0d, v1.getY(), 0);
    }

    @Test
    public void setX() throws Exception {
        v1.setX(3.0d);
        assertEquals(3.0d, v1.getX(), 0);
    }

    @Test
    public void setY() throws Exception {
        v1.setY(4.0d);
        assertEquals(4.0d, v1.getX(), 0);
    }

    @Test
    public void equals() throws Exception {
        assertTrue(v1.equals(v2));
    }

    @Test
    public void add() throws Exception {
        v1.add(v2);
        assertEquals(6, v1.getX());
    }

    @Test
    public void multiply() throws Exception {
    }

    @Test
    public void rotate() throws Exception {
    }

}