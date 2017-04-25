package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import com.sterilecode.mitosis.plugin.client.RegistryListener;
import java.util.List;

public class PluginRegistryProxy implements IPluginRegistry {

  private final Plugin pluginInstance;

  public PluginRegistryProxy(Plugin plugin) {
    pluginInstance = plugin;
  }

  @Override
  public RegistrationToken registerObject(String objectId, Object obj) {
    ObjectSupplier supply = new Supplier(pluginInstance, objectId, obj);
    PluginRegistry.getInstance().registerSupplier(supply);

    return supply;
  }

  @Override
  public List<Object> getObjects(String objectId) {
    return PluginRegistry.getInstance().getObjects(objectId);
  }

  @Override
  public void addRegistryListener(String serviceId, RegistryListener listener) {
    PluginRegistry.getInstance().addRegistryListener(serviceId, listener);
  }

  @Override
  public void removeRegistryListener(String serviceId, RegistryListener listener) {
    PluginRegistry.getInstance().removeRegistryListener(serviceId, listener);
  }

  public static class Supplier extends ObjectSupplier {

    public Supplier(Plugin plugin, String objectId, Object obj) {
      super(objectId, obj);

      plugin.addDisposalListener((Plugin unused1) -> {
        notifyDisposal();
      });
    }
  }
}
