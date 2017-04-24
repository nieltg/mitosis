package com.sterilecode.mitosis.plugin.client;

import java.util.List;

public interface IPluginRegistry {

  void registerService(String serviceId, Object serviceObject);

  List<Object> getServices(String serviceId);
}
