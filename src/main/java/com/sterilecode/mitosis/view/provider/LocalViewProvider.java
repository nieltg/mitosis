package com.sterilecode.mitosis.view.provider;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class LocalViewProvider implements ViewProvider {

  public static final String PROVIDER_NAMESPACE = "main";

  private static final LocalViewProvider localViewProviderInstance = new LocalViewProvider();

  private static final String VIEW_DIRECTORY = "views";

  private final Map<String, Image> views = new HashMap<>();

  private LocalViewProvider() {
  }

  public static LocalViewProvider getInstance() {
    return localViewProviderInstance;
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
    Image view = views.get(viewId);
    return view;
  }
}
