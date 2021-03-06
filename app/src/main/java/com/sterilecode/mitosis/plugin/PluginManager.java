package com.sterilecode.mitosis.plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {
  public static final String PLUGINS_DIR = "plugins";

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
  public void discoverPluginsInDefaultDirectory() throws IOException, PluginException {
    File pluginDir = new File(PLUGINS_DIR);
    discoverPlugins(pluginDir);
  }

  /**
   * Discover plugin in specific directory.
   *
   * @param pluginDir Plugin directory
   * @throws IOException whether supplied parameter is not a directory
   */
  public void discoverPlugins(File pluginDir) throws IOException, PluginException {

    if (!pluginDir.isDirectory()) {
      throw new IOException(pluginDir + " is not a directory");
    }

    File[] jarFiles = pluginDir.listFiles((file, name) -> name.toLowerCase().endsWith(".jar"));

    for (File jarFile : jarFiles) {
      System.out.println(jarFile.toString());

      try {
        pluginList.add(new Plugin(jarFile.toURI().toURL()));
      } catch (Exception e) {
        System.out.println("Unable to load plugin");
        e.printStackTrace();
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
