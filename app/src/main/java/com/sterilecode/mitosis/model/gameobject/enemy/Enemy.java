package com.sterilecode.mitosis.model.gameobject.enemy;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.event.SplitEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import java.util.Random;

/**
 * An abstract class that is used to represent enemy.
 */
public abstract class Enemy extends GameObject {

  protected Behavior behavior;
  private int splitting;
  private double splitSpeed;
  private double splitOffset;
  private long elatedTime;

  /**
   * Constructor.
   */
  public Enemy(Vector position, Vector velocity, double size, String viewId) {
    super(position, 0.0, velocity, 0.0, size, viewId);
    splitting = 0;
    splitSpeed = 0.0;
    splitOffset = 0.0;
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

  /**
   * setSplitting.
   * Set enemie's splitting direction and speed.
   *
   * @param splitting split direction.
   */
  public void setSplitting(int splitting) {
    this.splitting = splitting;
    if (splitting == 1 || splitting == -1) {
      splitSpeed = 25.0;
    }
  }

  /**
   * update.
   * Move enemy according to it's behavior.
   */
  public final void update(long deltaTime) {
    behavior.move(deltaTime);

    // Split after a specific time
    elatedTime += deltaTime;
    if (elatedTime > NANOSECONDS_IN_A_SECOND * 5) {
      Random random = new Random(10);
      if (random.nextInt() < 3) {
        split();
      }
      elatedTime -= NANOSECONDS_IN_A_SECOND * 5;
    }

    // Update split position and velocity
    if (splitting == 1 || splitting == -1) {
      splitSpeed -= 0.2;
      setPosition(getPosition().add(
          new Vector(splitting * splitSpeed * deltaTime / NANOSECONDS_IN_A_SECOND, 0)));
      if (splitSpeed <= 0.0) {
        splitting = 0;
        splitSpeed = 0.0;
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
      notifyObservers(
          new SplitEvent(this.getClass().getConstructor(Vector.class).newInstance(position)));
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      exception.printStackTrace();
    }
  }
}
