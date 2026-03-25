package view.statePattern;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import controller.App;
import model.Marking;
import view.AppWindow;

public class GameStatePlaying implements GameState {
    @Override
    public void goNext(AppWindow context) {
        context.setGameState(new GameStateDone());
    }

    @Override
    public void updateWindow() {
        App.win.newGameButton.setEnabled(false); // disable the new game button while the game is in progress
        App.win.vsHumanButton.setEnabled(false);
        App.win.vsComputerButton.setEnabled(false);
        for (int i = 0; i < App.game.getBoard().length; i++) {
            App.win.markingButtons[i].setMark(App.game.getBoard()[i]); // update the marking buttons to reflect the current state of the game board
            App.win.markingButtons[i].setEnabled(App.game.getBoard()[i] == Marking.U); // enable the button if the cell
        }
        App.win.canvas.repaint(); // repaint the canvas to show the final state of the game
    }

    @Override
    public void updateCanvas(Graphics2D g2) {
        // if the game is in the playing state, display the current turn
        g2.setFont(new Font("Courier New", Font.BOLD, 16));
        g2.setColor(Color.BLUE);
        g2.drawString("Turn: " + App.game.getTurn(), 50, 90);
    }

}
