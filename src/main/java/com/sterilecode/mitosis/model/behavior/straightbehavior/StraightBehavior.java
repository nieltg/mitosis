package com.sterilecode.mitosis.model.behavior.straightbehavior;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : StraightBehavior.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/22/2017
 */

/**
 * A behavior that moves its owner straight down the screen.
 */
public class StraightBehavior extends Behavior {

  /**
   * Constructor.
   */
  public StraightBehavior(GameObject owner) {
    super(owner);
  }

  /**
   * move.
   *
   * @param deltaTime in nanoseconds.
   */
  public void move(long deltaTime) {
    owner.setPosition(owner.getPosition()
        .add(owner.getVelocity().multiply(deltaTime / (double) NANOSECONDS_IN_A_SECOND)));
  }
}
