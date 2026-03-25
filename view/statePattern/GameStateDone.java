package view.statePattern;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import controller.App;
import model.Marking;
import view.AppWindow;

public class GameStateDone implements GameState {

    @Override
    public void goNext(AppWindow context) {
        context.setGameState(new GameStatePlaying());
    }

    @Override
    public void updateWindow() {
        for (int i = 0; i < App.game.getBoard().length; i++) {
            App.win.markingButtons[i].setMark(App.game.getBoard()[i]); // update the marking buttons to reflect the current state of the game board
            App.win.markingButtons[i].setEnabled(false); // disable all the marking buttons when the game is in the initial state
        }
        App.win.newGameButton.setEnabled(true); // enable the new game button when the game is in the initial state
        App.win.vsHumanButton.setEnabled(true);
        App.win.vsComputerButton.setEnabled(true);

        App.win.canvas.repaint(); // repaint the canvas to show the final state of the game
    }

    @Override
    public void updateCanvas(Graphics2D g2) {
        // if the game is over, display the winner or if it's a draw
        Marking winner = App.game.getWinner();
        String overMessage = winner + " has won!";
        if (winner == Marking.U) {
            overMessage = "Draw/Tie";
        }
        g2.setFont(new Font("Courier New", Font.BOLD, 16));
        g2.setColor(Color.RED);
        g2.drawString("Game Over " + overMessage, 50, 50);
        g2.drawString("Press <New Game> to Play again", 50, 80);
    }

}
