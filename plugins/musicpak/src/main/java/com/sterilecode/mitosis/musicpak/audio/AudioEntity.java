package com.sterilecode.mitosis.musicpak.audio;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AudioEntity {

  private final Clip audioClip;

  AudioEntity(AudioInputStream au) throws LineUnavailableException, IOException {
    audioClip = AudioSystem.getClip();
    audioClip.open(au);
  }

  public Clip getAudioClip() {
    return audioClip;
  }

  public void play() {
    audioClip.setFramePosition(0);
    audioClip.start();
  }

  public void stop() {
    audioClip.stop();
  }
}
