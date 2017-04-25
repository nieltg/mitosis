package com.sterilecode.mitosis.plugin;

/**
 * The listener interface for listening object disposal event.
 */
public interface ObjectDisposalListener {

  /**
   * Invoked when the object supplied by plugin or self is being unregistered.
   *
   * @param source Source supplier.
   */
  void objectDisposed(ObjectSupplier source);
}
