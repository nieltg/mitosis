package com.sterilecode.mitosis.view;

import com.sterilecode.mitosis.common.Constants;
import com.sterilecode.mitosis.plugin.ObjectManager;
import com.sterilecode.mitosis.plugin.client.RegistryListener;
import com.sterilecode.mitosis.view.provider.LocalViewProvider;
import com.sterilecode.mitosis.view.provider.ViewProvider;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * A singleton for caching and retrieving views (game object images).
 */
public class ViewManager {

  private static final ViewManager viewManagerInstance = new ViewManager();

  private final Map<String, Stack<ViewProvider>> providerMap = new HashMap<>();

  private final RegistryListener registryListener = new RegistryListener() {
    @Override
    public void registryObjectAdded(String serviceId, Object obj) {
      try {
        ViewProvider viewProvider = (ViewProvider) obj;
        String providerNamespace = viewProvider.getNamespace();
        viewProvider.loadViews();

        Stack<ViewProvider> providerStack = providerMap.get(providerNamespace);

        if (providerStack == null) {
          providerStack = new Stack<>();
          providerMap.put(providerNamespace, providerStack);
        }

        providerStack.push(viewProvider);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    @Override
    public void registryObjectRemoved(String serviceId, Object obj) {
      ViewProvider viewProvider = (ViewProvider) obj;
      String providerNamespace = viewProvider.getNamespace();

      Stack<ViewProvider> providerStack = providerMap.get(providerNamespace);

      if (providerStack != null) {
        providerStack.remove(viewProvider);

        if (providerStack.isEmpty()) {
          providerMap.remove(providerNamespace);
        }
      }
    }
  };

  public class ViewNotLoadedException extends Exception {

    public ViewNotLoadedException(String msg) {
      super(msg);
    }
  }

  private ViewManager() {
    // Prevent class instantiation as this is a singleton
  }

  /**
   * Initializes this view manager singleton with an instance of object manager.
   */
  public void initialize() {
    ObjectManager objManager = ObjectManager.getInstance();

    objManager.getObjects(Constants.VIEW_PROVIDER_SERVICE_ID).forEach(obj ->
        registryListener.registryObjectAdded(Constants.VIEW_PROVIDER_SERVICE_ID, obj));

    objManager.addRegistryListener(Constants.VIEW_PROVIDER_SERVICE_ID, registryListener);
    objManager.registerObject(Constants.VIEW_PROVIDER_SERVICE_ID, LocalViewProvider.getInstance());
  }

  /**
   * Get a game object view (image) from cache.
   *
   * @param viewId Game object view identifier.
   * @return A view corresponding to the viewId.
   */
  public Image getView(String viewId) throws ViewNotLoadedException {
    String namespace;
    String locViewId;
    int separatorIndex = viewId.indexOf('@');

    if (separatorIndex == -1) {
      locViewId = viewId;
      namespace = LocalViewProvider.PROVIDER_NAMESPACE;
    } else {
      locViewId = viewId.substring(0, separatorIndex);
      namespace = viewId.substring(separatorIndex + 1);
    }

    Stack<ViewProvider> providerStack = providerMap.get(namespace);

    if (providerStack == null) {
      throw new ViewNotLoadedException("Namespace is not found");
    }

    ViewProvider viewProvider = providerStack.peek();

    if (viewProvider == null) {
      // Should not be happened anyway.
      throw new ViewNotLoadedException("Provider is not found");
    }

    Image viewImage = viewProvider.getView(locViewId);

    if (viewImage == null) {
      throw new ViewNotLoadedException("View " + locViewId + " is missing in specified namespace");
    }

    return viewImage;
  }

  /**
   * Get an instance of ViewManager.
   *
   * @return An instance of ViewManager.
   */
  public static ViewManager getInstance() {
    return viewManagerInstance;
  }
}
