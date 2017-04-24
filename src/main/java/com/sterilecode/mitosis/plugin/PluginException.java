package com.sterilecode.mitosis.plugin;

public class PluginException extends Exception {

  public PluginException() {
    super();
  }

  public PluginException(String msg) {
    super(msg);
  }

  public PluginException(String msg, Throwable e) {
    super(msg, e);
  }
}
