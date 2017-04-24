package com.sterilecode.mitosis.model.gameobject;

import com.sterilecode.mitosis.common.Vector;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Observable;

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

public class GameObjectTest extends TestCase {
    Vector pos = new Vector(1, 2);
    Vector velo = new Vector(1, 1);
    double size = 5;
    String vID = "test";
    private GameObject gobj;

    public void setUp(Vector pos, Vector velo, double size, String vID) {
        gobj = new GameObject(pos, velo, size, vID) {
        };
    }

    @Test
    public void getPosition() throws Exception {
        setUp();
        assertEquals(1, pos.getX());
        assertEquals(2, pos.getY());
    }

    @Test
    public void getVelocity() throws Exception {
    }

    @Test
    public void getSize() throws Exception {
    }

    @Test
    public void getViewId() throws Exception {
    }

    @Before
    public void setPosition() throws Exception {
        gobj.setPosition(pos);
    }

    @Before
    public void setVelocity() throws Exception {
    }

}