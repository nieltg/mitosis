package com.sterilecode.mitosis.gamepak.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.gamepak.model.behavior.AlternatingSpeedBehavior;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

/**
 * An enemy that has AlternatingSpeedBehavior
 */
public class Virus extends Enemy {
  /**
   * Constructor.
   *
   * @param position
   */
  public Virus(Vector position) {
    super(position, new Vector(0, 40), 5, "Virus@GamePak");
    behavior = new AlternatingSpeedBehavior(this);
  }

  @Override
  public void split() {

  }
}
