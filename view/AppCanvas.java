package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.App;
import model.GameState;
import model.TicTacToeGame;

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
        g2.setFont(new Font("Courier New", Font.BOLD, 16));

        TicTacToeGame game = App.game;
        GameState gameState = game.getState(); // get the current state of the game

        switch (gameState) {
            case INIT:
                g2.setColor(Color.BLUE);
                g2.drawString("Press <New Game> to start", 50, 50);
                break;
            case PLAYING:
                break;
            case OVER:
                break;
        }

    }
}
