package com.sterilecode.mitosis.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginRegistry {

  private static final PluginRegistry pluginRegistryInstance = new PluginRegistry();

  private final Map<String, List<ServiceSupplier>> knownSupplies = new HashMap<>();

  private final ServiceSupplier.DisposalListener supplyDisposalListener = new ServiceSupplier.DisposalListener() {

    @Override
    public void pluginDisposed(ServiceSupplier source) {
      List<ServiceSupplier> supplyList = knownSupplies.get(source.getServiceId());
      supplyList.remove(source);
    }
  };

  private PluginRegistry() {
  }

  public static PluginRegistry getInstance() {
    return pluginRegistryInstance;
  }

  public void registerSupply(ServiceSupplier supply) {
    List<ServiceSupplier> supplyList = knownSupplies.get(supply.getServiceId());
    supplyList.add(supply);

    supply.addDisposalListener(supplyDisposalListener);
  }

  public List<Object> getServices(String serviceId) {
    List<ServiceSupplier> supplies = knownSupplies.get(serviceId);

    return supplies.stream()
        .map(supply -> supply.getServiceObject())
        .collect(Collectors.toList());
  }
}
