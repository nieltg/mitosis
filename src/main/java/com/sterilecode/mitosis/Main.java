package com.sterilecode.mitosis;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Main.java
 * Created at        : 4/22/17
 * Last modified at  : 4/24/17
 */

import com.sterilecode.mitosis.common.Constants;
import com.sterilecode.mitosis.controller.GameController;
import com.sterilecode.mitosis.controller.ModelManager;
import com.sterilecode.mitosis.plugin.ObjectManager;
import com.sterilecode.mitosis.plugin.Plugin;
import com.sterilecode.mitosis.plugin.PluginManager;
import com.sterilecode.mitosis.plugin.client.RegistrationToken;
import com.sterilecode.mitosis.view.Renderer;
import com.sterilecode.mitosis.view.ViewManager;
import com.sterilecode.mitosis.view.swing.GameFrame;
import com.sterilecode.mitosis.view.swing.MenuDialog;

public class Main {

  /**
   * Program entry point.
   *
   * @param args Command-line arguments for this program.
   */
  public static void main(String[] args) {
    // Load local models
    ModelManager modelManager = ModelManager.getInstance();
    modelManager.loadLocalEnemies();
    modelManager.loadLocalBehavior();
    modelManager.loadLocalPowerUp();

    // Load custom fonts for renderer
    Renderer.loadFonts();

    // Discover plugins
    PluginManager pluginManager = PluginManager.getInstance();
    try {
      pluginManager.discoverPluginsInDefaultDirectory();
    } catch (Exception e) {
      System.out.println("DEBUG: Failed to discover plugins.");
    }

    // Load plugins
    for (Plugin plugin : pluginManager.getPlugins()) {
      System.out
          .println("DEBUG: Plugin: " + plugin.getPluginName() + " " + plugin.getPluginVersion());
      try {
        plugin.activate();
      } catch (Exception e) {
        System.out.println("DEBUG: Plugin failed to activate: " + plugin.getPluginName());
      }
    }

    // Load and cache views
    ViewManager.getInstance().initialize();

    // Initialize game device, show menu dialog
    GameFrame gameDevice = new GameFrame();

    // Loop between main menu and game, only exiting from main menu or if this thread is interrupted
    while (true) {

      MenuDialog menuDialog = new MenuDialog(gameDevice);
      menuDialog.setVisible(true);

      // Run GameController thread, then show it on the game frame.
      gameDevice.resetInputState();
      GameController gameController = new GameController(gameDevice,
          menuDialog.getNumberOfPlayers());

      final RegistrationToken registrationToken = ObjectManager.getInstance()
          .registerObject(Constants.GAME_CONTROLLER_PROVIDER_ID, gameController);

      Thread gameControllerThread = new Thread(gameController);
      gameControllerThread.start();
      gameDevice.showFrame();

      do {
        try {
          gameControllerThread.join();
        } catch (InterruptedException e) {
          // Ignore if interrupted.
        }
      } while (gameController.isThreadRunning());

      registrationToken.unregister();
      gameDevice.hideFrame();
    }
  }
}
