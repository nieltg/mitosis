package com.sterilecode.mitosis.view.provider;

import java.awt.Image;
import java.io.IOException;

public interface ViewProvider {
  String getNamespace();
  void loadViews() throws IOException;
  Image getView(String viewId);
}
