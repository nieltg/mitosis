package com.sterilecode.mitosis.gamepak.view.provider;

import com.sterilecode.mitosis.view.provider.ViewProvider;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class PluginViewProvider implements ViewProvider {

  public static final String PROVIDER_NAMESPACE = "GamePak";

  private static final PluginViewProvider pluginViewProviderInstance = new PluginViewProvider();

  private static final String VIEW_DIRECTORY = "views";

  private final Map<String, Image> views = new HashMap<>();

  private PluginViewProvider() {
  }

  public static PluginViewProvider getInstance() {
    return pluginViewProviderInstance;
  }

  @Override
  public String getNamespace() {
    return PROVIDER_NAMESPACE;
  }

  @Override
  public void loadViews() throws IOException {
    views.clear();

    // Get list of view files
    List<File> files = new ArrayList<>();
    URL url = getClass().getClassLoader().getResource(VIEW_DIRECTORY);
    try {
      File urlFile = new File(url.toURI());
      if (urlFile.isDirectory()) {
        for (File file : urlFile.listFiles()) {
          files.add(file);
        }
      }
    } catch (URISyntaxException exception) {
      throw new IOException("Invalid URI");
    }

    // Load views from the files
    for (File file : files) {
      String viewId = file.getName().replaceFirst("[.][^.]+$", "");
      BufferedImage image = ImageIO.read(file);
      views.put(viewId, image);
    }
  }

  @Override
  public Image getView(String viewId) {
    return views.get(viewId);
  }
}
