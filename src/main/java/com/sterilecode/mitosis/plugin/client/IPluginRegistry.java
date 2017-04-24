package com.sterilecode.mitosis.plugin.client;

import java.util.List;

public interface IPluginRegistry {

  void registerObject(String serviceId, Object serviceObject);

  List<Object> getObjects(String serviceId);
}
