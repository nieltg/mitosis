package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import com.sterilecode.mitosis.plugin.client.RegistryListener;
import java.util.List;

/**
 * A class which works as interface of plugin registry to plugins.
 */
public class PluginRegistryProxy implements IPluginRegistry {

  private final Plugin pluginInstance;

  /**
   * Create an instance of PluginRegistryProxy.
   *
   * @param plugin Related plugin
   */
  public PluginRegistryProxy(Plugin plugin) {
    pluginInstance = plugin;
  }

  /**
   * Register an object to plugin registry.
   *
   * @param objectId Service identifier
   * @param obj Object to be supplied
   * @return Token for cancelling registration
   */
  @Override
  public RegistrationToken registerObject(String objectId, Object obj) {
    ObjectSupplier supply = new Supplier(pluginInstance, objectId, obj);
    PluginRegistry.getInstance().registerSupplier(supply);

    return supply;
  }

  /**
   * Get objects from service identifier.
   *
   * @param objectId Service identifier
   * @return List of associated objects
   */
  @Override
  public List<Object> getObjects(String objectId) {
    return PluginRegistry.getInstance().getObjects(objectId);
  }

  /**
   * Add a listener for listening to registry event.
   *
   * @param serviceId Service identifier to be listened
   * @param listener Listener for specified registry event
   */
  @Override
  public void addRegistryListener(String serviceId, RegistryListener listener) {
    PluginRegistry.getInstance().addRegistryListener(serviceId, listener);
  }

  /**
   * Remove a listener from listening to registry event.
   *
   * @param serviceId Service identifier to be listened
   * @param listener Listener to be removed
   */
  @Override
  public void removeRegistryListener(String serviceId, RegistryListener listener) {
    PluginRegistry.getInstance().removeRegistryListener(serviceId, listener);
  }

  private static class Supplier extends ObjectSupplier {

    Supplier(Plugin plugin, String objectId, Object obj) {
      super(objectId, obj);

      plugin.addDisposalListener((Plugin unused1) -> {
        notifyDisposal();
      });
    }
  }
}
