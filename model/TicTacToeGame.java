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

    public void changeTurns() { // switch turns between X and O
        turn = (turn == Marking.X) ? Marking.O : Marking.X;
    }

    public Marking getTurn() {
        return turn;
    }

    public void play(int position) { // play a move at the given position
        if (strategy == PlayStrategy.VsHuman) { // human vs human
            humanPlayer(position);
            setWinner();
        }
        else if (strategy == PlayStrategy.VsComputer) { // human vs computer
            humanPlayer(position);
            setWinner();
            if (getWinner() != null) return;
            changeTurns();
            computerPlayer();
            setWinner();
        }
    }

    private int computerPick() { // pick a position for the computer player
        int pos = -1;
        for (int i = 0; i < board.length; i++) { // simple strategy: pick the first available cell
            if (board[i] == Marking.U) {
                pos = i;
                break;
            }
        }
        assert pos >= 0 : "Invalid position from computerPick()"; 
        return pos;
        
    }

    public void computerPlayer() { // computer makes a move
        int pos = computerPickAdvanced();
        board[pos] = turn; // mark the board
        ++moves; 
        
    }

    public void humanPlayer(int pos) {
        board[pos] = turn; // mark the board
        ++moves; 
    }

    public Marking getWinner() {
            return winner;
        }

    public void setWinner() { // check all rows, columns, and diagonals for a winner
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
    }

    private Marking checkRow(int n) { // check if a row has a winner
        int r = n * 3; // row start index
        if (board[r] != Marking.U && // not unmarked
            board[r] == board[r + 1] &&  // check row
            board[r] == board[r + 2]) { // all three in the row are the same
            return board[r];
        }else {
            return null; // no winner 
        }
    }

    private Marking checkCol(int n) { // check if a column has a winner
        if (board[n] != Marking.U && 
            board[n] == board[n + 3] &&  // check column
            board[n] == board[n + 6]) { // all three in the column are the same
            return board[n];
        }else {
            return null; // no winner 
        }
    }

    private Marking checkDiag1() { // check if the first diagonal has a winner
        if (board[0] != Marking.U && 
            board[0] == board[4] &&  // check diagonal
            board[0] == board[8]) { // all three in the diagonal are the same
            return board[0];
        }else {
            return null; // no winner 
        }
    }

    private Marking checkDiag2() { // check if the second diagonal has a winner
        if (board[2] != Marking.U && 
            board[2] == board[4] &&  // check diagonal
            board[2] == board[6]) { // all three in the diagonal are the same
            return board[2];
        }else {
            return null; // no winner 
        }
    }

    public GameState getState() { // get the current game state
        return state;
    }

    public void setState(GameState state) { // set the current game state
        this.state = state;
    }

    public PlayStrategy getStrategy() { // get the current play strategy
        return strategy;
    }

    public void setStrategy(PlayStrategy strategy) { // set the current play strategy
        this.strategy = strategy;
    }

    public Marking[] getBoard() { // get the current game board
        return board;
    }

    // Advanced computer strategy implementation
    public int computerPickAdvanced() {
        Marking me = turn; // computer's marking
        Marking opponent = (turn == Marking.X) ? Marking.O : Marking.X; // opponent's marking

        int winMove = findWinningMove(me); // check if computer can win in the next move
        if (winMove != -1) return winMove; // if winning move found, take it

        int blockMove = findWinningMove(opponent); // check if opponent can win in the next move
        if (blockMove != -1) return blockMove; // if opponent has a winning move, block it

        if (board[4] == Marking.U) return 4; // if center is available, take it

        int[] corners = {0, 2, 6, 8}; // check corners
        for (int c : corners) { // if a corner is available, take it
            if (board[c] == Marking.U) return c;
        }

        for (int i = 0; i < board.length; i++) { // if any open space is available, take it
            if (board[i] == Marking.U) return i;
        }

        return -1; // no moves left (should not happen if called correctly)

    }
    // Helper method to find a winning move for the given player
    private int findWinningMove(Marking player) { // check all lines (rows, columns, diagonals) for a winning move
        int[][] lines = { 
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };

        for (int[] line : lines) { // count how many of the player's marks are in the line and if there's an empty spot
            int countPlayer = 0; // count how many of the player's marks are in the line
            int emptySpot = -1; // track the index of the empty spot in the line

            for (int pos : line) { // check each position in the line
                if (board[pos] == player) countPlayer++; // if the position is marked by the player, increment the count
                if (board[pos] == Marking.U) emptySpot = pos; // if there's an empty spot, remember its index
            }

            if (countPlayer == 2 && emptySpot != -1) { // if there are 2 of the player's marks and 1 empty spot, return the empty spot as the winning move
                return emptySpot;
            }
        }

        return -1;
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
