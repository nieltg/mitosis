package com.sterilecode.mitosis.common;

import org.junit.Test;
import java.lang.Math;
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
        assertEquals(4.0d, v1.getY(), 0);
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
        assertEquals(3, v3.getX(), 0);
    }

    @Test
    public void multiply() throws Exception {
        Vector v3 = new Vector();
        v3 = v2.multiply(3);
        assertEquals(9, v3.getX(), 0);
        assertEquals(12, v3.getY(), 0);
    }

    @Test
    public void rotate() throws Exception {
        Vector v3 = new Vector();
        v3 = v2.rotate(0.5*Math.PI);
        assertEquals(-4, v3.getX(), 0);
        assertEquals(3, v3.getY(), 0.0001);
    }

}