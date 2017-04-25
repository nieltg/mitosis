package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.RegistryListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PluginRegistry {

  private static final PluginRegistry pluginRegistryInstance = new PluginRegistry();

  private final Map<String, List<ObjectSupplier>> knownSupplies = new HashMap<>();
  private final Map<String, List<RegistryListener>> registryListeners = new HashMap<>();

  private final ObjectDisposalListener supplyDisposalListener = new ObjectDisposalListener() {
    @Override
    public void objectDisposed(ObjectSupplier source) {
      String objectId = source.getObjectId();
      List<ObjectSupplier> supplyList = knownSupplies.get(objectId);

      if (supplyList != null) {

        if (supplyList.remove(source)) {
          List<RegistryListener> listenerList = registryListeners.get(objectId);

          listenerList.forEach(listener ->
              listener.registryObjectRemoved(objectId, source.getObject()));
        }

        if (supplyList.isEmpty())
          knownSupplies.remove(objectId);
      }

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

    // Notify listeners.

    List<RegistryListener> listenerList = registryListeners.get(objectId);

    if (listenerList != null)
      listenerList.forEach(listener -> listener.registryObjectAdded(objectId, supply.getObject()));
  }

  public List<Object> getObjects(String objectId) {
    List<ObjectSupplier> supplies = knownSupplies.get(objectId);

    return supplies.stream()
        .map(ObjectSupplier::getObject)
        .collect(Collectors.toList());
  }

  public void addRegistryListener(String serviceId, RegistryListener listener) {
    List<RegistryListener> listenerList = registryListeners.get(serviceId);

    if (listenerList == null) {
      listenerList = new ArrayList<>();
      registryListeners.put(serviceId, listenerList);
    }

    listenerList.add(listener);
  }

  public void removeRegistryListener(String serviceId, RegistryListener listener) {
    List<RegistryListener> listenerList = registryListeners.get(serviceId);

    if (listenerList != null) {
      listenerList.remove(listener);

      if (listenerList.isEmpty())
        registryListeners.remove(serviceId);
    }
  }
}
