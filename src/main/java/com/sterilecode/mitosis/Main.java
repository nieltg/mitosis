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
 * Last modified at  : 4/22/17
 */

import com.sterilecode.mitosis.controller.GameController;
import com.sterilecode.mitosis.controller.GameDevice;
import com.sterilecode.mitosis.view.swing.GameFrame;

public class Main {

  /**
   * Program entry point.
   * @param args Command-line arguments for this program.
   */
  public static void main(String[] args) {

    System.out.println("Hello!");

    // loadResources();
    // initializeUi();

    // TODO: show main menu

    // DEVELOPMENT: show game screen
    GameDevice gameDevice = new GameFrame();

    // DEVELOPMENT: run GameController thread and wait for it to end
    GameController gameController = new GameController(gameDevice);
    Thread gameControllerThread = new Thread(gameController);
    gameControllerThread.start();
    try {
      gameControllerThread.join();
    } catch (InterruptedException exception) {
      // Ignore interrupts
    }

  }

}
