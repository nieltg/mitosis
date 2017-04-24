package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import java.util.List;

public class ServiceManager implements IPluginRegistry {

  private static final ServiceManager serviceManagerInstance = new ServiceManager();

  private ServiceManager() {
  }

  public static ServiceManager getInstance() {
    return serviceManagerInstance;
  }

  @Override
  public void registerService(String serviceId, Object serviceObject) {
    PluginRegistry.getInstance().registerSupply(new ServiceSupplier(serviceId, serviceObject));
  }

  @Override
  public List<Object> getServices(String serviceId) {
    return PluginRegistry.getInstance().getServices(serviceId);
  }
}
