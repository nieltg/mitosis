package com.sterilecode.mitosis.plugin.client;

import java.util.List;

/**
 * An interface which works as a gateway to plugin registry.
 */
public interface IPluginRegistry {

  /**
   * Register an object to plugin registry.
   *
   * @param serviceId Service identifier
   * @param obj Object to be supplied
   * @return Token for cancelling registration
   */
  RegistrationToken registerObject(String serviceId, Object obj);

  /**
   * Get objects from service identifier.
   *
   * @param serviceId Service identifier
   * @return List of associated objects
   */
  List<Object> getObjects(String serviceId);

  /**
   * Add a listener for listening to registry event.
   *
   * @param serviceId Service identifier to be listened
   * @param listener Listener for specified registry event
   */
  void addRegistryListener(String serviceId, RegistryListener listener);

  /**
   * Remove a listener from listening to registry event.
   *
   * @param serviceId Service identifier to be listened
   * @param listener Listener to be removed
   */
  void removeRegistryListener(String serviceId, RegistryListener listener);
}
