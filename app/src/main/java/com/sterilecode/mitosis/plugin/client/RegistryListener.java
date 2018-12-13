package com.sterilecode.mitosis.plugin.client;

/**
 * An interface for listening registry-related events.
 */
public interface RegistryListener {

  /**
   * Invoked when a registry object is added.
   *
   * @param serviceId Service identifier
   * @param obj An object
   */
  void registryObjectAdded(String serviceId, Object obj);

  /**
   * Invoked when a registry object is removed.
   *
   * @param serviceId Service identifier
   * @param obj An object
   */
  void registryObjectRemoved(String serviceId, Object obj);
}
