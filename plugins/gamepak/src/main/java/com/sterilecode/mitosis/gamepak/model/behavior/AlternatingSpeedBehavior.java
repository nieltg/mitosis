package com.sterilecode.mitosis.gamepak.model.behavior;

import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.common.Vector;

import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_MILLISECOND;
import static com.sterilecode.mitosis.common.Constants.NANOSECONDS_IN_A_SECOND;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : AlternatingSpeedBehavior.java
 * Created at        : 4/25/2017
 * Last modified at  : 4/25/2017
 */

public class AlternatingSpeedBehavior extends Behavior{
  private long lastTimeAlternating = 0;
  private long elatedTime;
  private boolean slowDown;

  public AlternatingSpeedBehavior(GameObject owner) {
    super(owner);
    elatedTime = 0;
    slowDown = false;
  }

  public void move(long deltaTime) {
    if (elatedTime > NANOSECONDS_IN_A_SECOND * 2) {
      if (!slowDown) {
        slowDown = true;
        owner.setVelocity(owner.getVelocity().multiply(0.1));
      } else {
        slowDown = false;
        owner.setVelocity(owner.getVelocity().multiply(10));
      }
      elatedTime -= NANOSECONDS_IN_A_SECOND * 3;
    }
    owner.setPosition(owner.getPosition()
        .add(owner.getVelocity().multiply(deltaTime / (double) NANOSECONDS_IN_A_SECOND)));
    elatedTime += deltaTime;
  }
}
