package com.sterilecode.mitosis.plugin.client;

/**
 * An interface as a base for a plugin.
 */
public interface IPlugin {

  /**
   * Invoked when plugin is loaded.
   *
   * @param contract Plugin registry interface
   */
  public void activate(IPluginRegistry contract);

  /**
   * Invoked when plugin is unloaded.
   */
  public void deactivate();
}
