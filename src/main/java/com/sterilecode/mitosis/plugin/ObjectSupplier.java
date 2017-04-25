package com.sterilecode.mitosis.plugin;

import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import java.util.ArrayList;
import java.util.List;

public class ObjectSupplier implements RegistrationToken {

  private final List<ObjectDisposalListener> disposalListeners = new ArrayList<>();

  private final String supplyObjectId;
  private final Object supplyObject;

  public ObjectSupplier(String objectId, Object obj) {
    supplyObjectId = objectId;
    supplyObject = obj;
  }

  protected void notifyDisposal() {
    disposalListeners.forEach(listener -> listener.objectDisposed(this));
  }

  public String getObjectId() {
    return supplyObjectId;
  }

  public Object getObject() {
    return supplyObject;
  }

  public void addDisposalListener(ObjectDisposalListener listener) {
    disposalListeners.add(listener);
  }

  public void removeDisposalListener(ObjectDisposalListener listener) {
    disposalListeners.remove(listener);
  }

  @Override
  public void unregister() {
    notifyDisposal();
  }
}
