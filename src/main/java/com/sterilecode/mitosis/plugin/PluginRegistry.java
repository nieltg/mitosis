package com.sterilecode.mitosis.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginRegistry {

  private static final PluginRegistry pluginRegistryInstance = new PluginRegistry();

  private final Map<String, List<ObjectSupplier>> knownSupplies = new HashMap<>();

  private final ObjectDisposalListener supplyDisposalListener = new ObjectDisposalListener() {
    @Override
    public void objectDisposed(ObjectSupplier source) {
      List<ObjectSupplier> supplyList = knownSupplies.get(source.getObjectId());
      supplyList.remove(source);

      source.removeDisposalListener(this);
    }
  };

  private PluginRegistry() {
  }

  public static PluginRegistry getInstance() {
    return pluginRegistryInstance;
  }

  public void registerSupplier(ObjectSupplier supply) {
    String objectId = supply.getObjectId();

    List<ObjectSupplier> supplyList = knownSupplies.get(objectId);

    if (supplyList == null) {
      supplyList = new ArrayList<>();
      knownSupplies.put(objectId, supplyList);
    }

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
