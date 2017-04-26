package com.sterilecode.mitosis.view;

/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : Renderer.java
 * Created at        : 4/23/17
 * Last modified at  : 4/23/17
 */

import static java.awt.Font.SANS_SERIF;

import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.view.ViewManager.ViewNotLoadedException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Renderer {

  private BufferStrategy bufferStrategy;
  private int bufferX;
  private int bufferY;
  private int bufferWidth;
  private int bufferHeight;
  private Font hudCounterFont;
  private Font hudLabelFont;

  /**
   * Creates a renderer.
   *
   * @param gameDevice The game device on which buffer this renderer will draw on.
   */
  public Renderer(GameDevice gameDevice) {
    bufferStrategy = gameDevice.getBufferStrategy();
    bufferX = gameDevice.getBufferX();
    bufferY = gameDevice.getBufferY();
    bufferWidth = gameDevice.getBufferWidth();
    bufferHeight = gameDevice.getBufferHeight();

    // Load custom fonts
    try {
      ClassLoader classLoader = getClass().getClassLoader();

      hudCounterFont = Font.createFont(Font.TRUETYPE_FONT,
          classLoader.getResourceAsStream("fonts/lato/Lato-Thin.ttf"))
          .deriveFont(Font.PLAIN, 24f);
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(hudCounterFont);

      hudLabelFont = Font.createFont(Font.TRUETYPE_FONT,
          classLoader.getResourceAsStream("fonts/lato/Lato-Bold.ttf"))
          .deriveFont(Font.BOLD, 10f);
      GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(hudLabelFont);

    } catch (Exception exception) {
      hudCounterFont = new Font(SANS_SERIF, Font.PLAIN, 24);
      hudLabelFont = new Font(SANS_SERIF, Font.BOLD, 10);
    }
  }

  /**
   * Renders game objects on the Renderer's game device's buffer.
   *
   * @param gameObjects List of game objects to be rendered.
   * @param life The current life (for HUD).
   * @param score The current score (for HUD).
   */
  public void render(List<GameObject> gameObjects, int life, int score) {
    try {
      Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

      // Translate drawn graphics to avoid drawing on buffer insets/borders
      graphics.translate(bufferX, bufferY);

      // Draw game objects on the provided graphics object
      drawBackground(graphics);
      drawGame(gameObjects, graphics);
      drawHud(life, score, graphics);

      // Clean up
      graphics.dispose();

      // Only show frame if the bufferStrategy is stil valid
      if (!bufferStrategy.contentsLost() && !bufferStrategy.contentsRestored()) {
        bufferStrategy.show();
        Toolkit.getDefaultToolkit().sync();
      }
    } catch (IllegalStateException exception) {
      // Ignore, see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6933331
    }
  }

  /**
   * Draws the frame's background.
   *
   * @param graphics The graphics object which is drawn on.
   */
  private void drawBackground(Graphics2D graphics) {
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, bufferWidth, bufferHeight);
  }

  /**
   * Draws game objects on the provided graphics object.s
   *
   * @param gameObjects The game objects to be drawn.
   * @param graphics The graphics object which is drawn on.
   */
  private void drawGame(List<GameObject> gameObjects, Graphics2D graphics) {
    // Draw game objects
    ViewManager viewManager = ViewManager.getInstance();
    for (GameObject gameObject : gameObjects) {
      int objTopLeftX = (int) Math
          .round(gameObject.getPosition().getX() - gameObject.getSize());
      int objTopLeftY = (int) Math
          .round(gameObject.getPosition().getY() - gameObject.getSize());
      int objCenterX = (int) Math.round(gameObject.getPosition().getX());
      int objCenterY = (int) Math.round(gameObject.getPosition().getY());
      int objWidth = (int) Math.round(gameObject.getSize() * 2);
      int objHeight = (int) Math.round(gameObject.getSize() * 2);
      try {
        Image image = viewManager.getView(gameObject.getViewId());
        AffineTransform originalTransform = graphics.getTransform();
        graphics.rotate(gameObject.getRotation(), objCenterX, objCenterY);
        graphics.drawImage(image, objTopLeftX, objTopLeftY, objTopLeftX + objWidth - 1,
            objTopLeftY + objHeight - 1,
            0, 0, image.getWidth(null), image.getHeight(null), null);
        graphics.setTransform(originalTransform);
      } catch (ViewNotLoadedException exception) {
        // View not found, draw a placeholder
        graphics.setColor(Color.RED);
        graphics.fillOval(objTopLeftX, objTopLeftY, objWidth, objHeight);
      }
    }
  }

  private void drawHud(Integer life, Integer score, Graphics2D graphics) {
    graphics.setColor(Color.BLACK);
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    // Draw score counter
    graphics.setFont(hudCounterFont);
    graphics.drawString(score.toString(), 20, bufferHeight - 20);
    graphics.setFont(hudLabelFont);
    graphics.drawString("SCORE", 20, bufferHeight - 45);

    // Draw life counter
    graphics.setFont(hudCounterFont);
    graphics.drawString(life.toString(), 120, bufferHeight - 20);
    graphics.setFont(hudLabelFont);
    graphics.drawString(" LIFE", 120, bufferHeight - 45);
  }

}
