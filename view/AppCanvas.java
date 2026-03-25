package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.App;

public class AppCanvas extends JPanel {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 100;

    public AppCanvas() { // constructor
        setPreferredSize(new Dimension(WIDTH, HEIGHT)); // set the preferred size of the canvas
    }

    @Override // override the paintComponent method to draw on the canvas
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the superclass's paintComponent method to clear the canvas
        Graphics2D g2 = (Graphics2D) g; // cast the Graphics object to a Graphics2D object for more advanced drawing capabilities
        App.win.getGameState().updateCanvas(g2); // call the updateCanvas method of the current game state to draw the appropriate content on the canvas based on the state of the game
    }
}
