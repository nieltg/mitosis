package com.sterilecode.mitosis.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginRegistry {

  private static final PluginRegistry pluginRegistryInstance = new PluginRegistry();

  private final Map<String, List<ObjectSupplier>> knownSupplies = new HashMap<>();

  private final ObjectSupplier.DisposalListener supplyDisposalListener = source -> {
    List<ObjectSupplier> supplyList = knownSupplies.get(source.getObjectId());
    supplyList.remove(source);
  };

  private PluginRegistry() {
  }

  public static PluginRegistry getInstance() {
    return pluginRegistryInstance;
  }

  public void registerSupplier(ObjectSupplier supply) {
    List<ObjectSupplier> supplyList = knownSupplies.get(supply.getObjectId());
    supplyList.add(supply);

    supply.addDisposalListener(supplyDisposalListener);
  }

  public List<Object> getObjects(String objectId) {
    List<ObjectSupplier> supplies = knownSupplies.get(objectId);

    return supplies.stream()
        .map(supply -> supply.getObject())
        .collect(Collectors.toList());
  }
}
