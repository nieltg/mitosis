package com.sterilecode.mitosis.view;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : GameDevice.java
 * Created at        : 4/24/17
 * Last modified at  : 4/24/17
 */

import java.awt.image.BufferStrategy;

public interface GameDevice {

  // Display operations
  BufferStrategy getBufferStrategy();
  int getBufferWidth();
  int getBufferHeight();
  int getBufferX();
  int getBufferY();

  // Input operations
  InputState getInputState();
}
