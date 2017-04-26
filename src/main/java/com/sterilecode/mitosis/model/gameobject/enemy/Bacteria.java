package com.sterilecode.mitosis.model.gameobject.enemy;

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.event.SplitEvent;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Bacteria.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/24/2017
 */

/**
 * Enemy that has StraightBehavior
 */
public class Bacteria extends Enemy {

  /**
   * Constructor.
   */
  public Bacteria(Vector position) {
    super(position, new Vector(0, 20), 5, "Bacteria");
    behavior = new StraightBehavior(this);
  }
}
