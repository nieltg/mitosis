package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import java.util.List;

public class PluginRegistryProxy implements IPluginRegistry {

  private final Plugin pluginInstance;

  public PluginRegistryProxy(Plugin plugin) {
    pluginInstance = plugin;
  }

  @Override
  public void registerService(String serviceId, Object serviceObject) {
    ServiceSupplier supply = new Supplier(pluginInstance, serviceId, serviceObject);
    PluginRegistry.getInstance().registerSupply(supply);
  }

  @Override
  public List<Object> getServices(String serviceId) {
    return PluginRegistry.getInstance().getServices(serviceId);
  }

  public static class Supplier extends ServiceSupplier {

    public Supplier(Plugin plugin, String serviceId, Object serviceObject) {
      super(serviceId, serviceObject);

      plugin.addDisposalListener((Plugin obj) -> {
        notifyDisposal();
      });
    }
  }
}
