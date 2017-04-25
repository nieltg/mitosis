package com.sterilecode.mitosis.controller;

import com.sterilecode.mitosis.model.gameobject.GameObject;

public interface GameListener {

  void gameStarted(GameController controller);
  void gameOver(GameController controller);

  void gameObjectSpawned(GameController controller, GameObject gameObject);
  void gameObjectReachedBottom(GameController controller, GameObject gameObject);
  void gameObjectHit(GameController controller, GameObject gameObject);
}
