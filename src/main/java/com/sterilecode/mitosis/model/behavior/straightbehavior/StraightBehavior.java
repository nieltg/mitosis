package com.sterilecode.mitosis.model.behavior.straightbehavior;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

import com.sterilecode.mitosis.common.Constants;
import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;

/**
 * Created by Reinaldo on 4/22/2017.
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
