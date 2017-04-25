package com.sterilecode.mitosis.controller;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : HighScoreController.java
 * Created at        : 4/25/2017
 * Last modified at  : 4/25/2017
 */

public class HighScoreController {
  private HighScoreController highScoreControllerInstance = new HighScoreController();
  private int highScore;

  private HighScoreController() {
    // Singleton
  }

  public int getHighScore() {
    return highScore;
  }

  public void checkHighScore(int newScore) {
    if (newScore > highScore) {
      highScore = newScore;
    }
  }

  public void saveHighScore() {
    //TODO
  }

  public void loadHighScore() {
    //TODO
  }
}
