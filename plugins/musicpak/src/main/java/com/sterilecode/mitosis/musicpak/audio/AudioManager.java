package com.sterilecode.mitosis.musicpak.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioSystem;

public class AudioManager {

  private static final AudioManager audioManagerInstance = new AudioManager();
  private static final String AUDIO_DIRECTORY = "musicpak/audio";

  private final Map<String, AudioEntity> entities = new HashMap<>();

  private AudioManager() {
  }

  public static AudioManager getInstance() {
    return audioManagerInstance;
  }

  public void loadSounds() throws IOException {
    entities.clear();

    try {
      URI uri = getClass().getClassLoader().getResource(AUDIO_DIRECTORY).toURI();
      Path walkPath;

      if (uri.getScheme().equals("jar")) {
        FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        walkPath = fileSystem.getPath(AUDIO_DIRECTORY);
      } else {
        walkPath = Paths.get(uri);
      }

      Files.list(walkPath).forEach(path -> {
        String audioId = path.getFileName().toString().replaceFirst("[.][^.]+$", "");

        try {
          BufferedInputStream in = new BufferedInputStream(Files.newInputStream(path));
          entities.put(audioId, new AudioEntity(AudioSystem.getAudioInputStream(in)));
        } catch (Exception e) {
          System.out.println("Sound error: " + audioId);
          e.printStackTrace();
        }
      });

    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public AudioEntity getAudioEntity(String clipId) {
    return entities.get(clipId);
  }

  public void playAudio(String clipId) {
    try {
      entities.get(clipId).play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stopAllAudio() {
    entities.values().forEach(clip -> clip.stop());
  }
}
