package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPlugin;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * A class that represents a plugin.
 */
public class Plugin {

  private final List<PluginDisposalListener> disposalListeners = new ArrayList<>();

  private final ClassLoader pluginClassLoader;

  private final String pluginName;
  private final String pluginVersion;

  private final Class pluginClass;

  private IPlugin pluginInstance;

  /**
   * Create plugin from URL.
   *
   * @param url Path to JAR file
   * @throws IOException whether there is I/O error
   * @throws PluginException whether there is plugin error
   */
  public Plugin(URL url) throws IOException, PluginException {
    this(new URLClassLoader(new URL[]{url}, Plugin.class.getClassLoader()));
  }

  /**
   * Create plugin from class loader.
   *
   * @param loader Class loader
   * @throws IOException whether there is I/O error
   * @throws PluginException whether there is plugin error
   */
  public Plugin(ClassLoader loader) throws IOException, PluginException {
    // Keep class loader for class-loading.
    pluginClassLoader = loader;

    // Parse plugin info in manifest file.
    Manifest manifest = new Manifest(loader.getResourceAsStream("META-INF/MANIFEST.MF"));

    Attributes attributes = manifest.getMainAttributes();

    pluginName = attributes.getValue("Sterilecode-Mitosis-Plugin-Name");
    if (pluginName == null) {
      throw new PluginException("Malformed plugin: Plugin name is not specified");
    }

    pluginVersion = attributes.getValue("Sterilecode-Mitosis-Plugin-Version");
    if (pluginVersion == null) {
      throw new PluginException("Malformed plugin: Plugin version is not specified");
    }

    String className = attributes.getValue("Sterilecode-Mitosis-Plugin-Class");
    if (className == null) {
      throw new PluginException("Malformed plugin: Plugin class-name is not specified");
    }

    try {
      pluginClass = pluginClassLoader.loadClass(className);
    } catch (ClassNotFoundException e) {
      throw new PluginException("Malformed plugin: Specified class is not found", e);
    }
  }

  /**
   * Get plugin name from manifest.
   *
   * @return Plugin name
   */
  public String getPluginName() {
    return pluginName;
  }

  /**
   * Get plugin version from manifest.
   *
   * @return Plugin version
   */
  public String getPluginVersion() {
    return pluginVersion;
  }

  /**
   * Check if a plugin is active or not.
   *
   * @return True if active, otherwise False.
   */
  public boolean isActivated() {
    return pluginInstance != null;
  }

  /**
   * Activate a plugin and integrate it to plugin registry.
   *
   * @throws InstantiationException whether plugin can't be instantiated
   * @throws IllegalAccessException whether there's access-control error
   */
  public void activate() throws InstantiationException, IllegalAccessException {

    if (pluginInstance != null) {
      throw new IllegalStateException("Plugin is already activated");
    }

    pluginInstance = (IPlugin) pluginClass.newInstance();
    pluginInstance.activate(new PluginRegistryProxy(this));
  }

  /**
   * Deactivate a plugin and disintegrate it from plugin registry.
   */
  public void deactivate() {

    new CopyOnWriteArrayList<>(disposalListeners)
        .forEach(listener -> listener.pluginDisposed(this));

    if (pluginInstance == null) {
      throw new IllegalStateException("Plugin is not activated yet");
    }

    pluginInstance.deactivate();
    pluginInstance = null;
  }

  /**
   * Add a listener for listening disposal event.
   *
   * @param listener A listener.
   */
  public void addDisposalListener(PluginDisposalListener listener) {
    disposalListeners.add(listener);
  }

  /**
   * Remove a listener from listening disposal event.
   *
   * @param listener A listener.
   */
  public void removeDisposalListener(PluginDisposalListener listener) {
    disposalListeners.remove(listener);
  }
}
