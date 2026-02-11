package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameState;
import model.TicTacToeGame;
import view.BoardButton;

public class ButtonListener implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        TicTacToeGame game = App.game; 
        BoardButton button = (BoardButton) e.getSource(); // get the button that was clicked from the event

        game.play(button.getPos()); // call the play method of the game with the position of the button that was clicked
        if (game.getWinner() != null) { // check if there is a winner after the move
            game.setState(GameState.OVER); // set the game state to GAMEOVER
            System.out.println("Game Over" + game.getWinner());
        }else {
            game.changeTurns();
        }
        App.win.updateWindow(); // update the window to reflect the changes in the game state
        

    }
    
}
