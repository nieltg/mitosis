package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPlugin;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class Plugin {

  private final List<DisposalListener> disposalListeners = new ArrayList<>();

  private final ClassLoader pluginClassLoader;

  private final String pluginName;
  private final String pluginVersion;

  private final Class pluginClass;

  private IPlugin pluginInstance;

  public Plugin(URL url) throws IOException, PluginException {
    this(new URLClassLoader(new URL[]{url}, Plugin.class.getClassLoader()));
  }

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

  public String getPluginName() {
    return pluginName;
  }

  public String getPluginVersion() {
    return pluginVersion;
  }

  public boolean isActivated() {
    return pluginInstance != null;
  }

  public void activate() throws InstantiationException, IllegalAccessException {

    if (pluginInstance != null) {
      throw new IllegalStateException("Plugin is already activated");
    }

    pluginInstance = (IPlugin) pluginClass.newInstance();
    pluginInstance.activate(new PluginRegistryProxy(this));
  }

  public void deactivate() {

    disposalListeners.forEach(listener -> listener.pluginDisposed(this));

    if (pluginInstance == null) {
      throw new IllegalStateException("Plugin is not activated yet");
    }

    pluginInstance.deactivate();
    pluginInstance = null;
  }

  public void addDisposalListener(DisposalListener listener) {
    disposalListeners.add(listener);
  }

  public void removeDisposalListener(DisposalListener listener) {
    disposalListeners.remove(listener);
  }

  public interface DisposalListener {

    void pluginDisposed(Plugin source);
  }
}
