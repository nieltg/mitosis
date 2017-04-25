package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for supplying objects to the plugin registry.
 */
public class ObjectSupplier implements RegistrationToken {

  private final List<ObjectDisposalListener> disposalListeners = new ArrayList<>();

  private final String supplyObjectId;
  private final Object supplyObject;

  /**
   * Create an instance of ObjectSupplier.
   *
   * @param objectId Service identifier
   * @param obj Object to be registered
   */
  public ObjectSupplier(String objectId, Object obj) {
    supplyObjectId = objectId;
    supplyObject = obj;
  }

  /**
   * Notify listeners that this supplier is going to be disposed.
   */
  protected void notifyDisposal() {
    disposalListeners.forEach(listener -> listener.objectDisposed(this));
  }

  /**
   * Get service identifier of this supplier.
   *
   * @return Service identifier
   */
  public String getObjectId() {
    return supplyObjectId;
  }

  /**
   * Get object from this supplier.
   *
   * @return Registered object
   */
  public Object getObject() {
    return supplyObject;
  }

  /**
   * Add a listener for listening disposal event.
   *
   * @param listener A listener
   */
  public void addDisposalListener(ObjectDisposalListener listener) {
    disposalListeners.add(listener);
  }

  /**
   * Remove a listener from listening disposal event.
   *
   * @param listener A listener
   */
  public void removeDisposalListener(ObjectDisposalListener listener) {
    disposalListeners.remove(listener);
  }

  /**
   * Unregister current supplier.
   */
  @Override
  public void unregister() {
    notifyDisposal();
  }
}
