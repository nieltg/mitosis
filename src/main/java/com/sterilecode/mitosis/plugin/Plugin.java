package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPlugin;
import java.io.IOException;
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

  public Plugin(ClassLoader loader) throws IOException, ClassNotFoundException {
    // Keep class loader for class-loading.
    pluginClassLoader = loader;

    // Parse plugin info in manifest file.
    Manifest manifest = new Manifest(loader.getResourceAsStream("META-INF/MANIFEST.MF"));

    Attributes attributes = manifest.getMainAttributes();

    pluginName = attributes.getValue("Sterilecode-Mitosis-Plugin-Name");
    pluginVersion = attributes.getValue("Sterilecode-Mitosis-Plugin-Version");

    String className = attributes.getValue("Sterilecode-Mitosis-Plugin-Class");
    pluginClass = pluginClassLoader.loadClass(className);
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
