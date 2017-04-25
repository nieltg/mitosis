package com.sterilecode.mitosis.plugin.client;

public interface RegistryListener {
  void registryObjectAdded(String serviceId, Object serviceObject);
  void registryObjectRemoved(String serviceId, Object serviceObject);
}
