package com.sterilecode.mitosis.controller;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : ModelManager.java
 * Created at        : 4/25/2017
 * Last modified at  : 4/25/2017
 */

import static com.sterilecode.mitosis.common.Constants.BEHAVIOR_SERVICE_ID;
import static com.sterilecode.mitosis.common.Constants.ENEMY_SERVICE_ID;
import static com.sterilecode.mitosis.common.Constants.POWER_UP_SERVICE_ID;

import com.sterilecode.mitosis.model.behavior.Behavior;
import com.sterilecode.mitosis.model.behavior.straightbehavior.StraightBehavior;
import com.sterilecode.mitosis.model.gameobject.enemy.Bacteria;
import com.sterilecode.mitosis.model.gameobject.enemy.Enemy;
import com.sterilecode.mitosis.model.gameobject.powerup.ExtraLifePowerUp;
import com.sterilecode.mitosis.model.gameobject.powerup.PowerUp;
import com.sterilecode.mitosis.plugin.ObjectManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A singleton for caching and retrieving list of model classes.
 */
public class ModelManager {

  private static ModelManager modelManagerInstance = new ModelManager();

  private ModelManager() {
    // Not implemented because of singleton's nature
  }

  /**
   * getInstance.
   *
   * @return ModelManager
   */
  public static ModelManager getInstance() {
    return modelManagerInstance;
  }

  /**
   * Load all local classes that extend Enemy.
   */
  public void loadLocalEnemies() {
    ObjectManager.getInstance().registerObject(ENEMY_SERVICE_ID, Bacteria.class);
  }

  /**
   * Load all local classes that extend Behavior.
   */
  public void loadLocalBehavior() {
    ObjectManager.getInstance().registerObject(BEHAVIOR_SERVICE_ID, StraightBehavior.class);
  }

  /**
   * Load all local classes that extend PowerUp.
   */
  public void loadLocalPowerUp() {
    ObjectManager.getInstance().registerObject(POWER_UP_SERVICE_ID, ExtraLifePowerUp.class);
  }

  /**
   * Get a list of all class that extend Enemy.
   *
   * @return List;Class;? extends Enemy;;
   */
  public List<Class<? extends Enemy>> getListOfEnemy() {
    return ObjectManager.getInstance().getObjects(ENEMY_SERVICE_ID)
        .stream().map(x -> (Class<? extends Enemy>) x).collect(Collectors.toList());
  }

  /**
   * Get a list of all class that extend Behavior.
   *
   * @return List;Class;? extends Behavior;;
   */
  public List<Class<? extends Behavior>> getListOfBehavior() {
    return ObjectManager.getInstance().getObjects(BEHAVIOR_SERVICE_ID)
        .stream().map(x -> (Class<? extends Behavior>) x).collect(Collectors.toList());
  }

  /**
   * Get a list of all class that extend PowerUp.
   *
   * @return List;Class;? extends PowerUp;;
   */
  public List<Class<? extends PowerUp>> getListOfPowerUp() {
    return ObjectManager.getInstance().getObjects(POWER_UP_SERVICE_ID)
        .stream().map(x -> (Class<? extends PowerUp>) x).collect(Collectors.toList());
  }
}
