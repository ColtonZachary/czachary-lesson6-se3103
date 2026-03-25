package model.strategyPattern;

import model.Marking;
import model.TicTacToeGame;

public class VsComputerStrategy implements PlayStrategy {
    
    private TicTacToeGame game;

    public VsComputerStrategy(TicTacToeGame game) {
        this.game = game;
    }

    @Override
    public void play(int position) {
        game.getBoard()[position] = game.getTurn(); // mark the board with the human player's move
        game.incMoves();
        game.setWinner();
        if (game.getWinner() != null) { // if the human player wins, don't let the computer play
            return;
        }

        // Computer's turn
        game.changeTurns(); // switch to computer's turn
        int pos = computerPick(); // let the computer pick a position
        game.getBoard()[pos] = game.getTurn(); // mark the board with the computer's move
        game.incMoves(); // increment the move count
        game.setWinner(); // check if the computer wins after its move
    }
    
    private int computerPick() { // pick a position for the computer player
        int pos = -1;
        for (int i = 0; i < game.getBoard().length; i++) { // simple strategy: pick the first available cell
            if (game.getBoard()[i] == Marking.U) {
                pos = i;
                break;
            }
        }
        assert pos >= 0 : "Invalid position from computerPick()"; 
        return pos;
        
    }
}
