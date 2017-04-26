package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.event.SplitEvent;
import com.sterilecode.mitosis.model.gameobject.GameObject;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Enemy.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/22/2017
 */

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

/**
 * An abstract class that is used to represent enemy.
 */
public abstract class Enemy extends GameObject{
	protected Behavior behavior;
  private int splitting;
  private Vector originalVelocity;
  private long elatedTime;

	/**
	 * Constructor.
	 *
	 * @param position
	 * @param velocity
	 * @param size
	 * @param viewId
	 */
	public Enemy(Vector position, Vector velocity, double size, String viewId) {
		super(position, 0.0, velocity, 0.0, size, viewId);
		originalVelocity = velocity;
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
   * getSplitting.
   *
   * @return boolean
   */
  public int getSplitting() {
    return splitting;
  }

  /**
   * setSplitting.
   *
   * @param splitting
   */
  public void setSplitting(int splitting) {
    this.splitting = splitting;
    if (splitting == 1 || splitting == -1) {
      setVelocity(getVelocity().add(new Vector((double) splitting * 10, 0)));
    }
  }

  /**
   * update.
   * Move enemy according to it's behavior.
   *
   * @param deltaTime
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
    this.setChanged();
    setSplitting(-1);
    notifyObservers(new SplitEvent(new Bacteria(position)));
  }
}
