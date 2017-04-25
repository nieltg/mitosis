package com.sterilecode.mitosis.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {

  // TODO: Development environment!
  public static final String PLUGINS_DIR = "build/plugins";

  private static final PluginManager pluginManagerInstance = new PluginManager();

  private final List<Plugin> pluginList = new ArrayList<>();

  private PluginManager() {
  }

  /**
   * Get an instance of plugin manager.
   *
   * @return An instance of PluginManager
   */
  public static PluginManager getInstance() {
    return pluginManagerInstance;
  }

  /**
   * Discover plugin in default directory.
   */
  public void discoverPluginsInDefaultDirectory() {
    File pluginDir = new File(PLUGINS_DIR);

    try {
      discoverPlugins(pluginDir);
    } catch (IOException e) {
    }
  }

  /**
   * Discover plugin in specific directory.
   *
   * @param pluginDir Plugin directory
   * @throws IOException whether supplied parameter is not a directory
   */
  public void discoverPlugins(File pluginDir) throws IOException {

    if (!pluginDir.isDirectory()) {
      throw new IOException(pluginDir + " is not a directory");
    }

    File[] jarFiles = pluginDir.listFiles((file, name) -> name.toLowerCase().endsWith(".jar"));

    for (File jarFile : jarFiles) {
      try {
        pluginList.add(new Plugin(jarFile.toURI().toURL()));
      } catch (PluginException e) {
      }
    }
  }

  /**
   * Get discovered plugins.
   *
   * @return List of plugins
   */
  public List<Plugin> getPlugins() {
    return pluginList;
  }
}
