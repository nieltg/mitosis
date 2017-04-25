package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import com.sterilecode.mitosis.plugin.client.RegistryListener;
import java.util.List;

/**
 * A class which works as interface of plugin registry to main program.
 */
public class ObjectManager implements IPluginRegistry {

  private static final ObjectManager objectManagerInstance = new ObjectManager();

  private ObjectManager() {
  }

  /**
   * Get an instance of ObjectManager from the cache.
   *
   * @return an instance of ObjectManager
   */
  public static ObjectManager getInstance() {
    return objectManagerInstance;
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
    ObjectSupplier supplier = new ObjectSupplier(objectId, obj);
    PluginRegistry.getInstance().registerSupplier(supplier);

    return supplier;
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
}
