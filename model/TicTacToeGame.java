package model;

public class TicTacToeGame {
    private Marking[] board = new Marking[9];
    private Marking turn = Marking.X; // X always starts first
    private int moves = 0;
    private Marking winner = null; // null means no winner yet // O, X, U (draw)
    private GameState state = GameState.INIT; 
    private PlayStrategy strategy = PlayStrategy.VsHuman; // default strategy

    public TicTacToeGame() {
        reset(); // initialize the game board
    }

    public void reset() { // reset the game to initial state
        for (int i = 0; i < board.length; i++) {
            board[i] = Marking.U; // U for unmarked
        } 
        turn = Marking.X; 
        moves = 0;
        winner = null;
        state = GameState.INIT;
    }

    public void changeTurns() {
        turn = (turn == Marking.X) ? Marking.O : Marking.X;
    }

    public void play(int position) { 
        if (strategy == PlayStrategy.VsHuman) {
            humanPlayer(position);
            setWinner();
        }
        else if (strategy == PlayStrategy.VsComputer) {

        }
    }

    public void humanPlayer(int pos) {
        board[pos] = turn; // mark the board
        ++moves; // increment move count
    }

    public void setWinner() {
        for (int i = 0; i < 3; i++) {
            winner = checkRow(i);
            if (winner != null) return; // winner found in row
            winner = checkCol(i);
            if (winner != null) return; // winner found in column
        }
        winner = checkDiag1();
        if (winner != null) return; // winner found in diagonal 1
        winner = checkDiag2();
        if (winner != null) return; // winner found in diagonal 2

        if (moves == 9) { // all cells are filled and no winner
            winner = Marking.U; // U for draw
        }
        winner = null; // no winner yet
    }

    private Marking checkRow(int n) { 
        int r = n * 3; // row start index
        if (board[r] != Marking.U && // not unmarked
            board[r] == board[r + 1] &&  // check row
            board[r] == board[r + 2]) { // all three in the row are the same
            return board[r];
        }else {
            return null; // no winner 
        }
    }

    private Marking checkCol(int n) {
        if (board[n] != Marking.U && // not unmarked
            board[n] == board[n + 3] &&  // check column
            board[n] == board[n + 6]) { // all three in the column are the same
            return board[n];
        }else {
            return null; // no winner 
        }
    }

    private Marking checkDiag1() {
        if (board[0] != Marking.U && // not unmarked
            board[0] == board[4] &&  // check diagonal
            board[0] == board[8]) { // all three in the diagonal are the same
            return board[0];
        }else {
            return null; // no winner 
        }
    }

    private Marking checkDiag2() {
        if (board[2] != Marking.U && // not unmarked
            board[2] == board[4] &&  // check diagonal
            board[2] == board[6]) { // all three in the diagonal are the same
            return board[2];
        }else {
            return null; // no winner 
        }
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public PlayStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PlayStrategy strategy) {
        this.strategy = strategy;
    }

    public Marking getWinner() {
        return winner;
    }

    @Override
    public String toString() { // string representation of the game board
        var r1 = String.format(" %s  %s  %s \n", board[0], board[1], board[2]);
        var r2 = String.format(" %s  %s  %s \n", board[3], board[4], board[5]);
        var r3 = String.format(" %s  %s  %s \n", board[6], board[7], board[8]);
        var r4 = String.format("Winner: %s (moves: %d)\n", winner, moves);
        return r1 + r2 + r3 + r4;
    }

}
