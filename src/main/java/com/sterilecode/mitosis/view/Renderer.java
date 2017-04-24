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

import com.sterilecode.mitosis.common.Vector;
import com.sterilecode.mitosis.controller.GameDevice;
import com.sterilecode.mitosis.model.gameobject.GameObject;
import com.sterilecode.mitosis.view.ViewManager.ViewNotLoadedException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.List;

public class Renderer {
  private BufferStrategy bufferStrategy;
  private int bufferX;
  private int bufferY;
  private int bufferWidth;
  private int bufferHeight;

  public Renderer(GameDevice gameDevice) {
    bufferStrategy = gameDevice.getBufferStrategy();
    bufferX = gameDevice.getBufferX();
    bufferY = gameDevice.getBufferY();
    bufferWidth = gameDevice.getBufferWidth();
    bufferHeight = gameDevice.getBufferHeight();
  }

  /**
   * Renders game objects on the Renderer's game device's buffer.
   * @param gameObjects List of game objects to be rendered.
   */
  public void render(List<GameObject> gameObjects) {
    try {
      Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

      // Translate drawn graphics to avoid drawing on buffer insets/borders
      graphics.translate(bufferX, bufferY);

      // Draw game objects on the provided graphics object
      drawGame(gameObjects, graphics);

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
   * Draws game objects on the provided graphics object.s
   * @param gameObjects The game objects to be drawn.
   * @param graphics The graphics object which is drawn on.
   */
  private void drawGame(List<GameObject> gameObjects, Graphics2D graphics) {
    // Draw background
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, bufferWidth, bufferHeight);

    // Draw game objects
    ViewManager viewManager = ViewManager.getInstance();
    for (GameObject gameObject : gameObjects) {
      int objX = (int) Math.round(gameObject.getPosition().getX());
      int objY = (int) Math.round(gameObject.getPosition().getY());
      int objWidth = 50;
      int objHeight = 50; // TODO: fix object size
      try {
        Image image = viewManager.getView(gameObject.getViewId());
        graphics.drawImage(image, objX, objY, objX + objWidth - 1, objY + objHeight - 1,
            0, 0, image.getWidth(null), image.getHeight(null), null);
      } catch (ViewNotLoadedException exception) {
        // View not found, draw a placeholder
        graphics.setColor(Color.RED);
        graphics.fillRect(objX, objY, objWidth, objHeight);
      }
    }
  }

}
