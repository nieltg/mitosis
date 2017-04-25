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

import java.util.prefs.Preferences;

public class HighScoreController {
  private HighScoreController highScoreControllerInstance = new HighScoreController();
  private Preferences highScoreMemo;
  private Integer highScore;

  private HighScoreController() {
    highScoreMemo = Preferences.userRoot().node("com/sterilecode/mitosis");
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
    highScoreMemo.put("highscore", highScore.toString());
  }

  public void loadHighScore() {
    highScore = Integer.parseInt(highScoreMemo.get("highscore", "0"));
  }
}
