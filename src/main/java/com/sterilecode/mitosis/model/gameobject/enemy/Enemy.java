package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.event.SplitEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

/**
 * An abstract class that is used to represent enemy.
 */
public abstract class Enemy extends GameObject {

  protected Behavior behavior;
  private int splitting;
  private long elatedTime;

  /**
   * Constructor.
   */
  public Enemy(Vector position, Vector velocity, double size, String viewId) {
    super(position, 0.0, velocity, 0.0, size, viewId);
    splitting = 0;
    elatedTime = 0;
  }

  /**
   * getBehavior
   *
   * @return behavior.
   */
  public Behavior getBehavior() {
    return behavior;
  }

  public void setSplitting(int splitting) {
    this.splitting = splitting;
    if (splitting == 1 || splitting == -1) {
      setVelocity(getVelocity().add(new Vector((double) splitting * 10, 0)));
    }
  }

  /**
   * update.
   * Move enemy according to it's behavior.
   */
  public final void update(long deltaTime) {
    behavior.move(deltaTime);
    elatedTime += deltaTime;
    if (elatedTime > NANOSECONDS_IN_A_SECOND * 3) {
      split();
      elatedTime -= NANOSECONDS_IN_A_SECOND * 3;
    }
    if (splitting == 1 || splitting == -1) {
      setVelocity(getVelocity().add(new Vector(- splitting, 0)));
      if (getVelocity().getX() == 0) {
        splitting = 0;
      }
    }
  }

  /**
   * split.
   * Split enemy into 2 enemies that are identical to the original.
   */
  public void split() {
    try {
      this.setChanged();
      setSplitting(-1);
      notifyObservers(new SplitEvent(this.getClass().getConstructor(Vector.class).newInstance(position)));
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      exception.printStackTrace();
    }
  }
}
