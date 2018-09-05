package com.sterilecode.mitosis.controller;

import com.sterilecode.mitosis.model.gameobject.GameObject;

public interface GameListener {

  /**
   * Invoked when game is started.
   *
   * @param controller Game controller
   */
  void gameStarted(GameController controller);

  /**
   * Invoked when game is over.
   *
   * @param controller Game controller
   */
  void gameOver(GameController controller);

  /**
   * Invoked when an object is spawned.
   *
   * @param controller Game controller
   * @param gameObject Game object
   */
  void gameObjectSpawned(GameController controller, GameObject gameObject);

  /**
   * Invoked when an object is reaching the bottom of the screen.
   *
   * @param controller Game controller
   * @param gameObject Game object
   */
  void gameObjectReachedBottom(GameController controller, GameObject gameObject);

  /**
   * Invoked when objects are collide each other.
   *
   * @param controller Game controller
   * @param gameObject Game object
   */
  void gameObjectHit(GameController controller, GameObject gameObject);
}
