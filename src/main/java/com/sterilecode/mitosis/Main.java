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
import com.sterilecode.mitosis.view.GameDevice;
import com.sterilecode.mitosis.view.ViewManager;
import com.sterilecode.mitosis.view.swing.GameFrame;
import com.sterilecode.mitosis.view.swing.MenuDialog;
import java.io.IOException;

public class Main {

  /**
   * Program entry point.
   * @param args Command-line arguments for this program.
   */
  public static void main(String[] args) {

    // Load and cache views
    try {
      ViewManager.getInstance().loadViews();
    } catch (IOException exception) {
      System.out.println(exception.getMessage());
      exception.printStackTrace();
      return;
    }

    // Initialize game device, show menu dialog
    GameFrame gameDevice = new GameFrame();

    // Loop between main menu and game, only exiting from main menu or if this thread is interrupted
    while (true) {

      MenuDialog menuDialog = new MenuDialog(gameDevice);
      menuDialog.setVisible(true);

      // Run GameController thread, then show it on the game frame.
      GameController gameController = new GameController(gameDevice, 1);
      Thread gameControllerThread = new Thread(gameController);
      gameControllerThread.start();
      gameDevice.showFrame();

      // Wait for the game to end.
      try {
        gameControllerThread.join();
      } catch (InterruptedException exception) {
        System.exit(0);
      } finally {
        gameDevice.hideFrame();
      }
    }
  }
}
