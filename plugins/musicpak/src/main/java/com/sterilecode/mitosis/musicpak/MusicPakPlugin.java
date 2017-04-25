package com.sterilecode.mitosis.musicpak;

import com.sterilecode.mitosis.common.Constants;
import com.sterilecode.mitosis.controller.GameController;
import com.sterilecode.mitosis.musicpak.audio.AudioAction;
import com.sterilecode.mitosis.musicpak.audio.AudioManager;
import com.sterilecode.mitosis.plugin.client.IPlugin;
import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import com.sterilecode.mitosis.plugin.client.RegistryListener;

public class MusicPakPlugin implements IPlugin {

  private final RegistryListener registryListener = new RegistryListener() {
    @Override
    public void registryObjectAdded(String serviceId, Object obj) {
      ((GameController) obj).addGameListener(AudioAction.getInstance());
    }

    @Override
    public void registryObjectRemoved(String serviceId, Object obj) {
      ((GameController) obj).removeGameListener(AudioAction.getInstance());
    }
  };

  @Override
  public void activate(IPluginRegistry contract) {
    try {
      AudioManager.getInstance().loadSounds();

      contract.addRegistryListener(Constants.GAME_CONTROLLER_PROVIDER_ID, registryListener);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deactivate() {
    AudioManager.getInstance().stopAllAudio();
  }
}
