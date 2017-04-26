package com.sterilecode.mitosis.model.event;

import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;

/**
 * A class that is thrown when the Enemy.split() method is invoked.
 */
public class SplitEvent {

  /**
   * The new enemy that split from the original.
   */
  private Enemy enemy;

  /**
   * Constructor.
   */
  public SplitEvent(Enemy enemy) {
    this.enemy = enemy;
  }

  /**
   * Returns the enemy that is created as a result of this split.
   *
   * @return Enemy that is created as a result of this split.
   */
  public Enemy getEnemy() {
    return enemy;
  }
}
