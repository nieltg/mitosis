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
    public void getAbsis() throws Exception {
        assertEquals(0.0d, v1.getAbsis(), 0);
    }

    @Test
    public void getOrdinat() throws Exception {
        assertEquals(0.0d, v1.getOrdinat(), 0);
    }

    @Test
    public void setAbsis() throws Exception {
        v1.setAbsis(3.0d);
        assertEquals(3.0d, v1.getAbsis(), 0);
    }

    @Test
    public void setOrdinat() throws Exception {
        v1.setOrdinat(4.0d);
        assertEquals(4.0d, v1.getAbsis(), 0);
    }

    @Test
    public void equals() throws Exception {
        assertTrue(v1.equals(v2));
    }

    @Test
    public void add() throws Exception {
        v1.add(v2);
        assertEquals(6, v1.getAbsis());
    }

    @Test
    public void multiply() throws Exception {
    }

    @Test
    public void rotate() throws Exception {
    }

}