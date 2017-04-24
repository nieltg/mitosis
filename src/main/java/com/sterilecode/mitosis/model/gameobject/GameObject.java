package com.sterilecode.mitosis.model.gameobject;

import com.sterilecode.mitosis.common.Vector;
import java.util.Observable;/*
 * Mitosis - IF2210 Object-oriented Programming
 * Group 1 - SterileCode
 * - 13515001 [K-01] Jonathan Christopher
 * - 13515002 [K-02] Wenny Yustalim
 * - 13515071 [K-02] Daniel Pintara
 * - 13515093 [K-03] Reinaldo Ignatius
 * ***
 * File name         : GameObject.java
 * Created at        : 4/22/2017
 * Last modified at  : 4/24/2017
 */

/**
 * An abstract class that is the super class of all objects in the game.
 */
public abstract class GameObject extends Observable {

  /**
   * Position of the object in 2D vector.
   */
  protected Vector position;

  protected double rotation;

  /**
   * Velocity of the object in 2D vector.
   */
  protected Vector velocity;

  protected double angularVelocity;

  /**
   * Size of the object in integer.
   */
  protected double size;

  /**
   * An id used to represent certain view.
   */
  protected String viewId;

  /**
   * Constructor.
   */
  public GameObject(Vector position, double rotation, Vector velocity, double angularVelocity, double size, String viewId) {
    this.position = position;
    this.rotation = rotation;
    this.velocity = velocity;
    this.angularVelocity = angularVelocity;
    this.size = size;
    this.viewId = viewId;
  }

  /**
   * getPosition.
   * @return position;
   */
  public Vector getPosition() {
    return position;
  }

  public double getRotation() {
    return rotation;
  }

  /**
   * getVelocity.
   *
   * @return velocity;
   */
  public Vector getVelocity() {
    return velocity;
  }

  public double getAngularVelocity() {
    return angularVelocity;
  }

  /**
   * getSize.
   *
   * @return size;
   */
  public double getSize() {
    return size;
  }

  /**
   * getViewId.
   *
   * @return viewId
   */
  public String getViewId() {
    return viewId;
  }

  /**
   * setPosition.
   */
  public void setPosition(Vector newPosition) {
    position = newPosition;
  }

  public void setRotation(double rotation) {
    this.rotation = rotation;
  }

  /**
   * setVelocity.
   */
  public void setVelocity(Vector newVelocity) {
    velocity = newVelocity;
  }

  public void setAngularVelocity(double angularVelocity) {
    this.angularVelocity = angularVelocity;
  }

  public void setSize(double size) {
    this.size = size;
  }

  /**
   * update.
   * Update gameobjects attributes in deltaTime period.
   */
  public abstract void update(long deltaTime);
}

