package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import com.sterilecode.mitosis.plugin.client.RegistryListener;
import java.util.List;

public class ObjectManager implements IPluginRegistry {

  private static final ObjectManager objectManagerInstance = new ObjectManager();

  private ObjectManager() {
  }

  public static ObjectManager getInstance() {
    return objectManagerInstance;
  }

  @Override
  public RegistrationToken registerObject(String objectId, Object obj) {
    ObjectSupplier supplier = new ObjectSupplier(objectId, obj);
    PluginRegistry.getInstance().registerSupplier(supplier);

    return supplier;
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
}
