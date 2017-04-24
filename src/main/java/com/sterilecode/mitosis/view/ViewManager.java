package com.sterilecode.mitosis.view;

import java.net.URL;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * ViewManager is a helper for caching and retrieving resources.
 */
public class ViewManager {

  private static final ViewManager viewManagerInstance = new ViewManager();

  private final HashMap<String, ImageIcon> resourcesMap = new HashMap<>();

  private ViewManager () {
  }

  /**
   * Get game resource (icon) from cache (if exists) which is retrieved from system resources.
   *
   * @param resId Resource identifier.
   * @return Image resource.
   */
  public ImageIcon getResource (String resId) {
    ImageIcon val = resourcesMap.get(resId);

    if (val == null)
    {
      final URL resource = getClass().getResource("/resources/icon/" + resId + ".png");

      if (resource != null) {
        val = new ImageIcon(resource);
        resourcesMap.put(resId, val);
      }
    }

    return val;
  }

  /**
   * Get single instance of ViewManager.
   *
   * @return an instance of ViewManager.
   */
  public static ViewManager getInstance () {
    return viewManagerInstance;
  }
}
