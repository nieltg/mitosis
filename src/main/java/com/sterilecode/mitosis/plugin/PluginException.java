package com.sterilecode.mitosis.plugin;

/**
 * An exception for plugin-related error.
 */
public class PluginException extends Exception {

  /**
   * Create PluginException instance.
   */
  public PluginException() {
    super();
  }

  /**
   * Create PluginException with message.
   *
   * @param msg A message
   */
  public PluginException(String msg) {
    super(msg);
  }

  /**
   * Create PluginException with message and throwable.
   *
   * @param msg A message
   * @param e Previous exception
   */
  public PluginException(String msg, Throwable e) {
    super(msg, e);
  }
}
