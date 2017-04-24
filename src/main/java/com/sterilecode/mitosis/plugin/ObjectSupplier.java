package com.sterilecode.mitosis.plugin;

import java.util.ArrayList;
import java.util.List;

public class ObjectSupplier {

  private final List<DisposalListener> disposalListeners = new ArrayList<>();

  private final String supplyObjectId;
  private final Object supplyObject;

  public ObjectSupplier(String objectId, Object obj) {
    supplyObjectId = objectId;
    supplyObject = obj;
  }

  protected void notifyDisposal() {
    disposalListeners.forEach(listener -> listener.pluginDisposed(this));
  }

  public String getObjectId() {
    return supplyObjectId;
  }

  public Object getObject() {
    return supplyObject;
  }

  public void addDisposalListener(DisposalListener listener) {
    disposalListeners.add(listener);
  }

  public void removeDisposalListener(DisposalListener listener) {
    disposalListeners.remove(listener);
  }

  public interface DisposalListener {

    void pluginDisposed(ObjectSupplier source);
  }
}
