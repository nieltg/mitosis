package com.sterilecode.mitosis.plugin.client;

public interface IPlugin {

  public void activate(IPluginRegistry contract);

  public void deactivate();
}
