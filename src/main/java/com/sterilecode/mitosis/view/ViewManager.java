package com.sterilecode.mitosis.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * A singleton for caching and retrieving views (game object images).
 */
public class ViewManager {

  private static final String VIEW_DIRECTORY = "views";
  private static ViewManager viewManagerInstance = new ViewManager();
  private final Map<String, Image> views = new HashMap<>();

  public class ViewNotLoadedException extends Exception {
    public ViewNotLoadedException(String viewId) {
      super("View with ID " + viewId + " not yet loaded.");
    }
  }

  private ViewManager() {
    // Prevent class instantiation as this is a singleton
  }

  /**
   * Loads views (images) from the view directory and stores it in the view manager cache.
   * @throws IOException
   */
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

  /**
   * Get a game object view (image) from cache.
   * @param viewId Game object view identifier.
   * @return A view corresponding to the viewId.
   */
  public Image getView(String viewId) throws ViewNotLoadedException {
    Image view = views.get(viewId);
    if (view == null) {
      throw new ViewNotLoadedException(viewId);
    } else {
      return view;
    }
  }

  /**
   * Get an instance of ViewManager.
   * @return An instance of ViewManager.
   */
  public static ViewManager getInstance() {
    return viewManagerInstance;
  }
}
