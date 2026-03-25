package model.strategyPattern;

import model.TicTacToeGame;

public class VsHumanStrategy implements PlayStrategy {

    private TicTacToeGame game;

    public VsHumanStrategy(TicTacToeGame game) {
        this.game = game;
    }

    @Override
    public void play(int position) {
        game.getBoard()[position] = game.getTurn();
        game.incMoves();
        game.setWinner();
    }
}
