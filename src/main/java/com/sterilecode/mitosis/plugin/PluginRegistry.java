package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.RegistryListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * A central storage for sharable things in plugin.
 */
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

          new CopyOnWriteArrayList<>(listenerList).forEach(listener ->
              listener.registryObjectRemoved(objectId, source.getObject()));
        }

        if (supplyList.isEmpty()) {
          knownSupplies.remove(objectId);
        }
      }

      source.removeDisposalListener(this);
    }
  };

  private PluginRegistry() {
  }

  /**
   * Get an instance of PluginRegistry.
   *
   * @return instance of PluginRegistry
   */
  public static PluginRegistry getInstance() {
    return pluginRegistryInstance;
  }

  /**
   * Register supplier to PluginRegistry.
   *
   * @param supply A supplier
   */
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

    if (listenerList != null) {
      new CopyOnWriteArrayList<>(listenerList)
          .forEach(listener -> listener.registryObjectAdded(objectId, supply.getObject()));
    }
  }

  /**
   * Get objects from service identifier.
   *
   * @param objectId Service identifier
   * @return List of objects
   */
  public List<Object> getObjects(String objectId) {
    List<ObjectSupplier> supplies = knownSupplies.get(objectId);

    if (supplies == null) {
      return Collections.emptyList();
    } else {
      return supplies.stream()
          .map(ObjectSupplier::getObject)
          .collect(Collectors.toList());
    }
  }

  /**
   * Add listener for listening registry-related events.
   *
   * @param serviceId Service identifier
   * @param listener A listener
   */
  public void addRegistryListener(String serviceId, RegistryListener listener) {
    List<RegistryListener> listenerList = registryListeners.get(serviceId);

    if (listenerList == null) {
      listenerList = new ArrayList<>();
      registryListeners.put(serviceId, listenerList);
    }

    listenerList.add(listener);
  }

  /**
   * Remove listener from listening registry-related events.
   *
   * @param serviceId Service identifier
   * @param listener A listener
   */
  public void removeRegistryListener(String serviceId, RegistryListener listener) {
    List<RegistryListener> listenerList = registryListeners.get(serviceId);

    if (listenerList != null) {
      listenerList.remove(listener);

      if (listenerList.isEmpty()) {
        registryListeners.remove(serviceId);
      }
    }
  }
}
