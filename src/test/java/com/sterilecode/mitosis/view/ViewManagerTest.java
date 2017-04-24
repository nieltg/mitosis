package com.sterilecode.mitosis.view;

import static org.junit.Assert.*;

import org.junit.Test;

public class ViewManagerTest {

  @Test
  public void loadAndGetView() throws Exception {
    ViewManager vm = ViewManager.getInstance();
    vm.loadViews();
    assertNotNull(vm.getView("sample"));
  }

  @Test
  public void getInstance() throws Exception {
    assertNotNull(ViewManager.getInstance());
  }

}