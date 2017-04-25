package com.sterilecode.mitosis.gamepak;

import com.sterilecode.mitosis.common.Constants;
import com.sterilecode.mitosis.gamepak.model.gameobject.enemy.Virus;
import com.sterilecode.mitosis.gamepak.model.gameobject.powerup.BulletSpeedPowerup;
import com.sterilecode.mitosis.plugin.client.IPlugin;
import com.sterilecode.mitosis.plugin.client.IPluginRegistry;

public class GamePakPlugin implements IPlugin {

  @Override
  public void activate(IPluginRegistry contract) {
    contract.registerObject(Constants.ENEMY_SERVICE_ID, Virus.class);
    contract.registerObject(Constants.POWER_UP_SERVICE_ID, BulletSpeedPowerup.class);
  }

  @Override
  public void deactivate() {
  }
}
