package com.sterilecode.mitosis.model.event;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : LifeChangeEvent.java
 * Created at        : 4/25/2017
 * Last modified at  : 4/25/2017
 */

public class LifeChangeEvent {
  private int lifeChange;

  /**
   * Constructor.
   *
   * @param lifeChange
   */
  public LifeChangeEvent(int lifeChange) {
    this.lifeChange = lifeChange;
  }

  /**
   * getLifeChange.
   *
   * @return int
   */
  public int getLifeChange() {
    System.out.println("BBBBB");
    return lifeChange;
  }
}
