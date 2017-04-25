package com.sterilecode.mitosis.gamepak.view.provider;

import com.sterilecode.mitosis.view.provider.ViewProvider;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public class PluginViewProvider implements ViewProvider {

  public static final String PROVIDER_NAMESPACE = "GamePak";

  private static final PluginViewProvider pluginViewProviderInstance = new PluginViewProvider();

  private static final String VIEW_DIRECTORY = "gamepak/views";

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

    try {
      URI uri = getClass().getClassLoader().getResource(VIEW_DIRECTORY).toURI();
      Path walkPath;

      if (uri.getScheme().equals("jar")) {
        FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        walkPath = fileSystem.getPath(VIEW_DIRECTORY);
      } else {
        walkPath = Paths.get(uri);
      }

      Files.list(walkPath).forEach(path -> {
        String viewId = path.getFileName().toString().replaceFirst("[.][^.]+$", "");

        try {
          BufferedImage image = ImageIO.read(Files.newInputStream(path));
          views.put(viewId, image);

          System.out.println("DEBUG: PluginViewProvider: " + viewId);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Image getView(String viewId) {
    return views.get(viewId);
  }
}
