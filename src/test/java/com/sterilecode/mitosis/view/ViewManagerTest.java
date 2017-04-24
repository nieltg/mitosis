package com.sterilecode.mitosis.view;

import static org.junit.Assert.*;

import org.junit.Test;

public class ViewManagerTest {

  @Test
  public void getResource() throws Exception {
    ViewManager vm = ViewManager.getInstance();

    assertNotNull(vm.getResource("sample"));
  }

  @Test
  public void getInstance() throws Exception {
    assertNotNull(ViewManager.getInstance());
  }

}