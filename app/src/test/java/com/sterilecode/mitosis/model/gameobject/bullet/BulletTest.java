package com.sterilecode.mitosis.model.gameobject.bullet;

import static org.junit.Assert.assertEquals;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.gameobject.player.Player;
import org.junit.Test;

/**
 * Created by wennyyustalim on 4/26/17.
 */
public class BulletTest {

  private static final double EPS = 1e-9;
  Vector v1 = new Vector(1, 2);
  Vector v2 = new Vector(3, 4);
  Vector v3 = new Vector(5, 6);
  Player p = new Player(v3);
  Bullet b = new Bullet(v1, v2, p);

  @Test
  public void getPosition() throws Exception {
    assertEquals(1, (b.getPosition()).getX(), EPS);
    assertEquals(2, (b.getPosition()).getY(), EPS);
  }

  @Test
  public void getVelocity() throws Exception {
    assertEquals(1, (b.getPosition()).getX(), EPS);
    assertEquals(2, (b.getPosition()).getY(), EPS);
  }

  @Test
  public void getSize() throws Exception {
    assertEquals(2, b.getSize(), EPS);
  }

  @Test
  public void getViewId() throws Exception {
    assertEquals("Bullet", b.getViewId());
  }

  @Test
  public void setPosition() throws Exception {
    Vector v = new Vector(1, 1);
    b.setPosition(v);
    assertEquals(1, (b.getPosition()).getX(), EPS);
    assertEquals(1, (b.getPosition()).getY(), EPS);
  }

  @Test
  public void setVelocity() throws Exception {
    Vector v = new Vector(1, 2);
    b.setVelocity(v);
    assertEquals(1, (b.getVelocity()).getX(), EPS);
    assertEquals(2, (b.getVelocity()).getY(), EPS);
  }
}