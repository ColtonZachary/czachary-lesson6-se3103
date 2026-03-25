package view.statePattern;

import java.awt.Color;
import java.awt.Graphics2D;

import controller.App;
import view.AppWindow;

public class GameStateInit implements GameState {
    @Override
    public void goNext(AppWindow context) {
        context.setGameState(new GameStatePlaying());
    }

    @Override
    public void updateWindow() {
        for (var b : App.win.markingButtons) {
            b.setEnabled(false); // disable all the marking buttons when the game is in the initial state
        }
        App.win.newGameButton.setEnabled(true); // enable the new game button when the game is in the initial state
        App.win.vsHumanButton.setEnabled(true);
        App.win.vsComputerButton.setEnabled(true);

        App.win.canvas.repaint(); // repaint the canvas to show the final state of the game
    }

    @Override
    public void updateCanvas(Graphics2D g2) {
        // if the game is in the initial state, display a message to start the game
        g2.setColor(Color.BLUE);
        g2.drawString("Press <New Game> to start", 50, 50);
    }

}
