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
    try {
      AudioEntity entity = AudioManager.getInstance().getAudioEntity("start");
      entity.getAudioClip().loop(Clip.LOOP_CONTINUOUSLY);
      entity.play();
    } catch (Exception ignored) {
    }
  }

  @Override
  public void gameOver(GameController controller) {
    try {
      AudioManager.getInstance().playAudio("over");
    } catch (Exception ignored) {
    }
  }

  @Override
  public void gameObjectSpawned(GameController controller, GameObject gameObject) {
    try {
      AudioManager.getInstance().playAudio("spawn");
    } catch (Exception ignored) {
    }
  }

  @Override
  public void gameObjectReachedBottom(GameController controller, GameObject gameObject) {
    try {
      AudioManager.getInstance().playAudio("bottom");
    } catch (Exception ignored) {
  }
  }

  @Override
  public void gameObjectHit(GameController controller, GameObject gameObject) {
    try {
      AudioManager.getInstance().playAudio("hit");
    } catch (Exception ignored) {
    }
  }
}
