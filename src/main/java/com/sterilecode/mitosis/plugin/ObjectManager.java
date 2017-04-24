package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.IPluginRegistry;
import java.util.List;

public class ObjectManager implements IPluginRegistry {

  private static final ObjectManager objectManagerInstance = new ObjectManager();

  private ObjectManager() {
  }

  public static ObjectManager getInstance() {
    return objectManagerInstance;
  }

  @Override
  public void registerObject(String objectId, Object obj) {
    PluginRegistry.getInstance().registerSupplier(new ObjectSupplier(objectId, obj));
  }

  @Override
  public List<Object> getObjects(String objectId) {
    return PluginRegistry.getInstance().getObjects(objectId);
  }
}
