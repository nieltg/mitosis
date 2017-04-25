package com.sterilecode.mitosis.gamepak.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

public class Amoeba extends Enemy {

  public Amoeba(Vector position) {
    super(position, new Vector(0, 10), 5, "Bacteria");
    behavior = new StraightBehavior(this);
  }

  @Override
  public void split() {

  }
}
