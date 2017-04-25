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

import com.sterilecode.mitosis.controller.GameController;
import com.sterilecode.mitosis.controller.ModelManager;
import com.sterilecode.mitosis.plugin.ObjectManager;
import com.sterilecode.mitosis.plugin.Plugin;
import com.sterilecode.mitosis.plugin.PluginManager;
import com.sterilecode.mitosis.view.GameDevice;
import com.sterilecode.mitosis.view.ViewManager;
import com.sterilecode.mitosis.view.swing.GameFrame;
import java.io.IOException;

public class Main {

  /**
   * Program entry point.
   * @param args Command-line arguments for this program.
   */
  public static void main(String[] args) {
    // Load local models
    ModelManager modelManager = ModelManager.getInstance();
    modelManager.loadLocalEnemies();
    modelManager.loadLocalBehavior();
    modelManager.loadLocalPowerUp();

    // Load plugins
    PluginManager pluginManager = PluginManager.getInstance();
    pluginManager.discoverPluginsInDefaultDirectory();

    System.out.println("DEBUG: Discovering plugins... ");

    for (Plugin plugin : pluginManager.getPlugins()) {
      System.out.println("DEBUG: Plugin: " + plugin.getPluginName() + " " + plugin.getPluginVersion());
      try {
        plugin.activate();
      } catch (Exception e) {
      }
    }

    // Load and cache views
    ViewManager.getInstance().initialize();

    // Initialize and show Swing UI
    GameDevice gameDevice = new GameFrame();

    // TODO: show main menu

    // DEVELOPMENT: run GameController thread and wait for it to end
    GameController gameController = new GameController(gameDevice, 1);
    Thread gameControllerThread = new Thread(gameController);
    gameControllerThread.start();
    try {
      gameControllerThread.join();
    } catch (InterruptedException exception) {
      // Ignore interrupts
    }

  }
}
