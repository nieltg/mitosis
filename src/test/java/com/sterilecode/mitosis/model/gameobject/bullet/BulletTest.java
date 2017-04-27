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
  Player player = new Player(v3);
  Bullet bullet = new Bullet(v1, v2, player);

  @Test
  public void getPosition() throws Exception {
    assertEquals(1, (bullet.getPosition()).getX(), EPS);
    assertEquals(2, (bullet.getPosition()).getY(), EPS);
  }

  @Test
  public void getVelocity() throws Exception {
    assertEquals(1, (bullet.getPosition()).getX(), EPS);
    assertEquals(2, (bullet.getPosition()).getY(), EPS);
  }

  @Test
  public void getSize() throws Exception {
    assertEquals(2, bullet.getSize(), EPS);
  }

  @Test
  public void getViewId() throws Exception {
    assertEquals("Bullet", bullet.getViewId());
  }

  @Test
  public void setPosition() throws Exception {
    Vector v = new Vector(1, 1);
    bullet.setPosition(v);
    assertEquals(1, (bullet.getPosition()).getX(), EPS);
    assertEquals(1, (bullet.getPosition()).getY(), EPS);
  }

  @Test
  public void setVelocity() throws Exception {
    Vector v = new Vector(1, 2);
    bullet.setVelocity(v);
    assertEquals(1, (bullet.getVelocity()).getX(), EPS);
    assertEquals(2, (bullet.getVelocity()).getY(), EPS);
  }
}