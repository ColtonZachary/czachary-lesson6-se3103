package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class AppCanvas extends JPanel {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 100;

    public AppCanvas() { // constructor
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // set the preferred size of the canvas
    }

    @Override // override the paintComponent method to draw on the canvas
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the superclass's paintComponent method to clear the canvas
    }
}
