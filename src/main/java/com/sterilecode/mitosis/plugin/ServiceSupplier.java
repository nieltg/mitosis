package com.sterilecode.mitosis.plugin;

import java.util.ArrayList;
import java.util.List;

public class ServiceSupplier {

  private final List<DisposalListener> disposalListeners = new ArrayList<>();

  private final String supplyServiceId;
  private final Object supplyServiceObj;

  public ServiceSupplier(String serviceId, Object serviceObject) {
    supplyServiceId = serviceId;
    supplyServiceObj = serviceObject;
  }

  protected void notifyDisposal() {
    disposalListeners.forEach(listener -> listener.pluginDisposed(this));
  }

  public String getServiceId() {
    return supplyServiceId;
  }

  public Object getServiceObject() {
    return supplyServiceObj;
  }

  public void addDisposalListener(DisposalListener listener) {
    disposalListeners.add(listener);
  }

  public void removeDisposalListener(DisposalListener listener) {
    disposalListeners.remove(listener);
  }

  public interface DisposalListener {

    void pluginDisposed(ServiceSupplier source);
  }
}
