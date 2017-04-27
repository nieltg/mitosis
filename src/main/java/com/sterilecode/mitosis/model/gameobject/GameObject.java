package com.sterilecode.mitosis.model.gameobject;

import com.sterilecode.mitosis.common.Vector;
import java.util.Observable;

/**
 * An abstract class that is the super class of all objects in the game.
 */
public abstract class GameObject extends Observable {

  protected Vector position;
  protected double rotation;
  protected Vector velocity;
  protected double angularVelocity;
  protected double size;
  protected String viewId;

  /**
   * Constructor.
   */
  public GameObject(Vector position, double rotation, Vector velocity, double angularVelocity,
      double size, String viewId) {
    this.position = position;
    this.rotation = rotation;
    this.velocity = velocity;
    this.angularVelocity = angularVelocity;
    this.size = size;
    this.viewId = viewId;
  }

  /**
   * getPosition.
   *
   * @return position;
   */
  public Vector getPosition() {
    return position;
  }

  /**
   * setPosition.
   */
  public void setPosition(Vector newPosition) {
    position = newPosition;
  }

  public double getRotation() {
    return rotation;
  }

  public void setRotation(double rotation) {
    this.rotation = rotation;
  }

  /**
   * getVelocity.
   *
   * @return velocity;
   */
  public Vector getVelocity() {
    return velocity;
  }

  /**
   * setVelocity.
   */
  public void setVelocity(Vector newVelocity) {
    velocity = newVelocity;
  }

  public double getAngularVelocity() {
    return angularVelocity;
  }

  public void setAngularVelocity(double angularVelocity) {
    this.angularVelocity = angularVelocity;
  }

  /**
   * getSize.
   *
   * @return size;
   */
  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
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
   * update.
   * Update gameobjects attributes in deltaTime period.
   */
  public abstract void update(long deltaTime);
}

