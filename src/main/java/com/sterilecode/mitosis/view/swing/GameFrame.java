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

import com.sterilecode.mitosis.view.GameDevice;
import com.sterilecode.mitosis.view.InputState;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements GameDevice, KeyListener {

  private static final int BUFFER_WIDTH = 720;
  private static final int BUFFER_HEIGHT = 480;
  private static final int BUFFER_COUNT = 2;
  private Insets insets;
  private InputState inputState;
  private Map<Integer, Consumer<Boolean>> keyMap;

  public GameFrame() {

    // Set up window and drawing surface
    setTitle("Mitosis");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIgnoreRepaint(true);
    setResizable(false);
    getContentPane().setPreferredSize(new Dimension(BUFFER_WIDTH, BUFFER_HEIGHT));
    pack();
    insets = this.getInsets();
    createBufferStrategy(BUFFER_COUNT);

    // Prepare to receive input
    inputState = new InputState();

    // Set up key map
    keyMap = new HashMap<>();
    keyMap.put(KeyEvent.VK_ESCAPE, inputState::setMenuKeyPressed);
    keyMap.put(KeyEvent.VK_LEFT, inputState::setPlayer1RotateLeftKeyPressed);
    keyMap.put(KeyEvent.VK_RIGHT, inputState::setPlayer1RotateRightKeyPressed);
    keyMap.put(KeyEvent.VK_DOWN, inputState::setPlayer1ShootKeyPressed);
    keyMap.put(KeyEvent.VK_A, inputState::setPlayer2RotateLeftKeyPressed);
    keyMap.put(KeyEvent.VK_D, inputState::setPlayer2RotateRightKeyPressed);
    keyMap.put(KeyEvent.VK_S, inputState::setPlayer2ShootKeyPressed);
  }

  /**
   * Shows this game frame.
   */
  public synchronized void showFrame() {
    if (!isVisible()) {
      addKeyListener(this); // Begin listening for key input events
      setVisible(true);
    }
  }

  /**
   * Hides this game frame.
   */
  public synchronized void hideFrame() {
    if (isVisible()) {
      removeKeyListener(this);
      setVisible(false);
    }
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

  @Override
  public InputState getInputState() {
    return inputState;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  /**
   * Handles key press event; set the corresponding state in input state to true.
   * e Key event details.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    Consumer<Boolean> keySetMethod = keyMap.get(e.getKeyCode());
    if (keySetMethod != null) {
      keySetMethod.accept(true);
    }
  }

  /**
   * Handles key release event; set the corresponding state in input state to false.
   * e Key event details.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    Consumer<Boolean> keySetMethod = keyMap.get(e.getKeyCode());
    if (keySetMethod != null) {
      keySetMethod.accept(false);
    }
  }
}
