package com.sterilecode.mitosis.view.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuDialog extends JDialog {

  public MenuDialog(JFrame parent) {
    super(parent, "Main Menu");

    setModal(true);
    setUndecorated(true);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 1, 20, 20));

    JButton button;

    button = new JButton("New Game");
    button.addActionListener(actionEvent -> {
      dispose();
    });

    panel.add(button);

    button = new JButton("High Score");
    button.addActionListener(actionEvent -> {
      // TODO: impl. high score
    });

    panel.add(button);

    button = new JButton("Options");
    button.addActionListener(actionEvent -> {
      // TODO: impl. options
    });

    panel.add(button);

    button = new JButton("Quit");
    button.addActionListener(actionEvent -> {
      System.exit(0);
    });

    panel.add(button);

    panel.setBorder(new EmptyBorder(40, 50, 40, 50));

    add(panel);
    pack();

    Point loc = parent.getLocation();
    Dimension size = parent.getSize();
    Dimension mSize = getSize();

    loc.x += (size.width - mSize.width) / 2;
    loc.y += (size.height - mSize.height) / 2;

    setLocation(loc);
  }

}
