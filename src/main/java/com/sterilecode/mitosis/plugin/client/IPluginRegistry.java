package com.sterilecode.mitosis.plugin.client;

import java.util.List;

public interface IPluginRegistry {

  RegistrationToken registerObject(String serviceId, Object serviceObject);

  List<Object> getObjects(String serviceId);

  void addRegistryListener(String serviceId, RegistryListener listener);
  void removeRegistryListener(String serviceId, RegistryListener listener);
}
