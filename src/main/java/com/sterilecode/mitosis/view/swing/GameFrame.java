package com.sterilecode.mitosis.view.swing;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : GameFrame.java
 * Created at        : 4/24/17
 * Last modified at  : 4/24/17
 */

import com.sterilecode.mitosis.controller.GameDevice;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements GameDevice {

  private static final int BUFFER_WIDTH = 720;
  private static final int BUFFER_HEIGHT = 480;
  private static final int BUFFER_COUNT = 2;
  private Insets insets;

  public GameFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIgnoreRepaint(true);
    setResizable(false);
    getContentPane().setPreferredSize(new Dimension(BUFFER_WIDTH, BUFFER_HEIGHT));
    pack();
    insets = this.getInsets();
    setVisible(true);
    createBufferStrategy(BUFFER_COUNT);
  }

  @Override
  public int getBufferWidth() {
    return BUFFER_WIDTH;
  }

  @Override
  public int getBufferHeight() {
    return BUFFER_HEIGHT;
  }

  @Override
  public int getBufferX() {
    return insets.left;
  }

  @Override
  public int getBufferY() {
    return insets.top;
  }
}
