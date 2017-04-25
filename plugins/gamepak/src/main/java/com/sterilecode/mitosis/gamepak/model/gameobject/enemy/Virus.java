package com.sterilecode.mitosis.gamepak.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.gamepak.model.behavior.AlternatingSpeedBehavior;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

public class Virus extends Enemy {

  public Virus(Vector position) {
    super(position, new Vector(0, 20), 5, "Virus");
    behavior = new AlternatingSpeedBehavior(this);
  }

  @Override
  public void split() {

  }
}
