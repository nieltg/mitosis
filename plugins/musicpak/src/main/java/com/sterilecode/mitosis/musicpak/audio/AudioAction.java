package com.sterilecode.mitosis.musicpak.audio;

import com.sterilecode.mitosis.controller.GameController;
import com.sterilecode.mitosis.controller.GameListener;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import javax.sound.sampled.Clip;

public class AudioAction implements GameListener {

  private static final AudioAction audioActionInstance = new AudioAction();

  public static AudioAction getInstance() {
    return audioActionInstance;
  }

  private AudioAction() {
  }

  @Override
  public void gameStarted(GameController controller) {
    // TODO!
  }

  @Override
  public void gameOver(GameController controller) {
    // TODO!
  }

  @Override
  public void gameObjectSpawned(GameController controller, GameObject gameObject) {
    try {
      AudioManager.getInstance().playAudio("spawn");
    } catch (Exception e) {
    }
  }

  @Override
  public void gameObjectReachedBottom(GameController controller, GameObject gameObject) {
    try {
      AudioManager.getInstance().playAudio("bottom");
    } catch (Exception e) {
  }
  }

  @Override
  public void gameObjectHit(GameController controller, GameObject gameObject) {
    try {
      AudioManager.getInstance().playAudio("hit");
    } catch (Exception e) {
    }
  }
}
