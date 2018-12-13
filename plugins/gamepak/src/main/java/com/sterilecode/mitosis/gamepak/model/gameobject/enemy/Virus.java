package com.sterilecode.mitosis.gamepak.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.gamepak.model.behavior.AlternatingSpeedBehavior;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

/**
 * An virus enemy that has AlternatingSpeedBehavior.
 */
public class Virus extends Enemy {

  /**
   * Creates a virus.
   *
   * @param position The starting position of this virus.
   */
  public Virus(Vector position) {
    super(position, new Vector(0, 40), 5, "Virus@GamePak"); // TODO: @?
    behavior = new AlternatingSpeedBehavior(this);
  }
}
