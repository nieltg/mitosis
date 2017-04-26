package com.sterilecode.mitosis.view.swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuDialog extends JDialog {

  /**
   * Creates a new main menu dialog.
   * @param parent The dialog parent frame of this menu. Pass null to use a global, shared parent.
   */
  public MenuDialog(JFrame parent) {
    super(parent, "Mitosis");
    setModal(true);
    setResizable(false);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5, 1, 20, 20));

    JLabel titleLabel = new JLabel("mitosis");
    titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    panel.add(titleLabel);

    JLabel descriptionLabel = new JLabel("by SterileCode");
    descriptionLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
    descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
    panel.add(descriptionLabel);

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

    button = new JButton("Quit");
    button.addActionListener(actionEvent -> {
      System.exit(0);
    });
    panel.add(button);

    panel.setBorder(new EmptyBorder(20, 40, 30, 40));

    add(panel);
    pack();

    Point loc = parent.getLocation();
    Dimension size = parent.getSize();
    Dimension menuSize = getSize();

    loc.x += (size.width - menuSize.width) / 2;
    loc.y += (size.height - menuSize.height) / 2;

    setLocation(loc);
  }

}
