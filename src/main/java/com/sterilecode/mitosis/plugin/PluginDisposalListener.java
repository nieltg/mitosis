package com.sterilecode.mitosis.plugin;

/**
 * An interface for listening plugin disposal event.
 */
public interface PluginDisposalListener {

  /**
   * Invoked when a plugin is disposed.
   *
   * @param source Disposing plugin
   */
  void pluginDisposed(Plugin source);
}
