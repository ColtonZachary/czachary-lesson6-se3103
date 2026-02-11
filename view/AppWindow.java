package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonListener;

import controller.App;
import controller.ButtonListener;
import controller.NewGameButtonListener;
import controller.StrategyButtonListener;
import model.Marking;
import model.PlayStrategy;
import model.TicTacToeGame;

public class AppWindow extends JFrame {

    public static final String vsHumanAction = "vs. Human";
    public static final String vsComputerAction = "vs. Computer";

    private AppCanvas canvas = new AppCanvas();
    private BoardButton[] markingButtons = new BoardButton[9]; // array of buttons for the tic-tac-toe board
    private JButton newGameButton = new JButton("New Game"); // button for starting a new game
    private JRadioButton vsHumanButton;
    private JRadioButton vsComputerButton;

    public void init() {
        var cp = getContentPane(); // get the content pane of the JFrame
        cp.add(canvas, BorderLayout.NORTH); // add the canvas to the north of the content pane

        ButtonListener buttonListener = new ButtonListener(); // create a new ButtonListener for handling button clicks
        for (int i = 0; i < markingButtons.length; i++) { 
            markingButtons[i] = new BoardButton(i); // create a new BoardButton for each position on the board
            markingButtons[i].addActionListener(buttonListener); // add the button listener to each BoardButton to handle when it is clicked
        }

        JPanel gameBoardPanel = new JPanel(); 
        gameBoardPanel.setLayout(new GridLayout(3, 3)); // set the layout of the game board panel to a 3x3 grid
        for (var cell: markingButtons) {
            gameBoardPanel.add(cell); // add each BoardButton to the game board panel
        }
        cp.add(gameBoardPanel, BorderLayout.CENTER); // add the game board panel to the center of the content pane

        JPanel southPanel = new JPanel(); 
        southPanel.setLayout(new GridLayout(2, 1)); // set the layout of the south panel to a 2x1 grid
        cp.add(southPanel, BorderLayout.SOUTH); 

        JPanel radioPanel = new JPanel(); 
        radioPanel.setBorder(new TitledBorder("Play Strategy")); // set a titled border for the radio panel to indicate that it contains options for selecting the play strategy
        vsHumanButton = new JRadioButton(vsHumanAction, App.game.getStrategy() == PlayStrategy.VsHuman); // create a new JRadioButton for the "vs. Human" strategy and set its initial state based on the current game strategy
        vsComputerButton = new JRadioButton(vsComputerAction, App.game.getStrategy() == PlayStrategy.VsComputer); 
        radioPanel.add(vsHumanButton); // add the vsHumanButton to the radio panel
        radioPanel.add(vsComputerButton); 
        StrategyButtonListener strategyListener = new StrategyButtonListener(); // create a new StrategyButtonListener for handling strategy selection
        vsHumanButton.addActionListener(strategyListener); // add an action listener to the vsHumanButton to handle when it is selected
        vsComputerButton.addActionListener(strategyListener); 
        ButtonGroup strategyGroup = new ButtonGroup();
        strategyGroup.add(vsHumanButton); // add the vsHumanButton to the button group
        strategyGroup.add(vsComputerButton); 
        southPanel.add(radioPanel); // add the radio panel to the south panel

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Action"));
        actionPanel.add(newGameButton); // add the newGameButton to the action panel
        newGameButton.addActionListener(new NewGameButtonListener()); // add an action listener to the new game button to start a new game when clicked
        JButton exitButton = new JButton("Exit"); // create a new button for exiting the application
        exitButton.addActionListener(e -> System.exit(0)); // add an action listener to the exit button to exit the application when clicked
        actionPanel.add(exitButton); // add the exit button to the action panel
        southPanel.add(actionPanel); // add the action panel to the south panel

        updateWindow();
        
    }

    public void updateWindow() { // method to update the state of the window based on the current game state
        TicTacToeGame game = App.game; 
        Marking[] board = game.getBoard(); // get the current state of the board from the game instance
        for (int i = 0 ; i < board.length; i++) { 
            markingButtons[i].setMark(board[i]); // set the marking of the corresponding button to match the state of the cell on the board (X, O, or unmarked)
        }

        switch(game.getState()) {
            case INIT: // if the game is in the Initial/Over state, disable all the marking buttons and enable the new game button and strategy selection buttons
            case OVER:
                for (var b: markingButtons) {
                    b.setEnabled(false); // disable all the marking buttons when the game is in the initial state
                }
                newGameButton.setEnabled(true); // enable the new game button when the game is in the initial state
                vsHumanButton.setEnabled(true);
                vsComputerButton.setEnabled(true);
                break;
            case PLAYING: // if the game is in the Playing state, enable the marking buttons that correspond to unmarked cells and disable the new game button and strategy selection buttons
                newGameButton.setEnabled(false); // disable the new game button while the game is in progress
                vsHumanButton.setEnabled(false);
                vsComputerButton.setEnabled(false);
                for (int i = 0; i < board.length; i++) {
                    markingButtons[i].setEnabled(board[i] == Marking.U); // enable the button if the cell is unmarked
                }
                break;
                
        }

        canvas.repaint(); // repaint the canvas to update the display

    }

}
