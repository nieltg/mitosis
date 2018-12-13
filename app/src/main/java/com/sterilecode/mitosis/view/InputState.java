package com.sterilecode.mitosis.view;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : InputState.java
 * Created at        : 4/24/17
 * Last modified at  : 4/24/17
 */

/**
 * Contains the state of input.
 * Input state is deliberately not synchronized (input state can change in the middle of a read).
 * To use safely, it is recommended to clone this object before reading.
 */
public class InputState implements Cloneable {

  private boolean menuKeyPressed = false;
  private boolean player1ShootKeyPressed = false;
  private boolean player2ShootKeyPressed = false;
  private boolean player1RotateLeftKeyPressed = false;
  private boolean player2RotateLeftKeyPressed = false;
  private boolean player1RotateRightKeyPressed = false;
  private boolean player2RotateRightKeyPressed = false;

  @Override
  public InputState clone() {
    try {
      return (InputState) super.clone();
    } catch (CloneNotSupportedException exception) {
      return null;
    }
  }

  public boolean isMenuKeyPressed() {
    return menuKeyPressed;
  }

  public void setMenuKeyPressed(boolean menuKeyPressed) {
    this.menuKeyPressed = menuKeyPressed;
  }

  public boolean isPlayer1ShootKeyPressed() {
    return player1ShootKeyPressed;
  }

  public void setPlayer1ShootKeyPressed(boolean player1ShootKeyPressed) {
    this.player1ShootKeyPressed = player1ShootKeyPressed;
  }

  public boolean isPlayer2ShootKeyPressed() {
    return player2ShootKeyPressed;
  }

  public void setPlayer2ShootKeyPressed(boolean player2ShootKeyPressed) {
    this.player2ShootKeyPressed = player2ShootKeyPressed;
  }

  public boolean isPlayer1RotateLeftKeyPressed() {
    return player1RotateLeftKeyPressed;
  }

  public void setPlayer1RotateLeftKeyPressed(boolean player1RotateLeftKeyPressed) {
    this.player1RotateLeftKeyPressed = player1RotateLeftKeyPressed;
  }

  public boolean isPlayer2RotateLeftKeyPressed() {
    return player2RotateLeftKeyPressed;
  }

  public void setPlayer2RotateLeftKeyPressed(boolean player2RotateLeftKeyPressed) {
    this.player2RotateLeftKeyPressed = player2RotateLeftKeyPressed;
  }

  public boolean isPlayer1RotateRightKeyPressed() {
    return player1RotateRightKeyPressed;
  }

  public void setPlayer1RotateRightKeyPressed(boolean player1RotateRightKeyPressed) {
    this.player1RotateRightKeyPressed = player1RotateRightKeyPressed;
  }

  public boolean isPlayer2RotateRightKeyPressed() {
    return player2RotateRightKeyPressed;
  }

  public void setPlayer2RotateRightKeyPressed(boolean player2RotateRightKeyPressed) {
    this.player2RotateRightKeyPressed = player2RotateRightKeyPressed;
  }
}
