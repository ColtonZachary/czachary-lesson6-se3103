package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controller.App;
import controller.ButtonListener;
import controller.NewGameButtonListener;
import controller.StrategyButtonListener;
import model.strategyPattern.VsComputerStrategy;
import model.strategyPattern.VsHumanStrategy;
import view.statePattern.GameState;
import view.statePattern.GameStateInit;
import view.statePattern.VsSmartComputerStrategy;

public class AppWindow extends JFrame {

    public static final String vsHumanAction = "vs. Human";
    public static final String vsComputerAction = "vs. Computer";
    public static final String vsSmartComputerAction = "vs. Smart Computer";

    public AppCanvas canvas = new AppCanvas();
    public BoardButton[] markingButtons = new BoardButton[9]; // array of buttons for the tic-tac-toe board
    public JButton newGameButton = new JButton("New Game"); // button for starting a new game
    public JRadioButton vsHumanButton;
    public JRadioButton vsComputerButton;
    public JRadioButton vsSmartComputerButton;

    private GameState state = new GameStateInit(); // initial game state

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
        vsHumanButton = new JRadioButton(vsHumanAction, App.game.getStrategy() instanceof VsHumanStrategy); // create a new JRadioButton for the "vs. Human" strategy and set its initial state based on the current game strategy
        vsComputerButton = new JRadioButton(vsComputerAction, App.game.getStrategy() instanceof VsComputerStrategy); // create a new JRadioButton for the "vs. Computer" strategy and set its initial state based on the current game strategy
        vsSmartComputerButton = new JRadioButton(vsSmartComputerAction, App.game.getStrategy() instanceof VsSmartComputerStrategy); // create a new JRadioButton for the "vs. Smart Computer" strategy and set its initial state based on the current game strategy
        radioPanel.add(vsHumanButton); // add the vsHumanButton to the radio panel
        radioPanel.add(vsComputerButton); // add the vsComputerButton to the radio panel
        radioPanel.add(vsSmartComputerButton); // add the vsSmartComputerButton to the radio panel
        StrategyButtonListener strategyListener = new StrategyButtonListener(); // create a new StrategyButtonListener for handling strategy selection
        vsHumanButton.addActionListener(strategyListener); // add an action listener to the vsHumanButton to handle when it is selected
        vsComputerButton.addActionListener(strategyListener); // add an action listener to the vsComputerButton to handle when it is selected
        vsSmartComputerButton.addActionListener(strategyListener); // add an action listener to the vsSmartComputerButton to handle when it is selected
        ButtonGroup strategyGroup = new ButtonGroup();
        strategyGroup.add(vsHumanButton); // add the vsHumanButton to the button group
        strategyGroup.add(vsComputerButton); 
        strategyGroup.add(vsSmartComputerButton); // add the vsSmartComputerButton to the button group
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

    public void goNextState() { // method to transition to the next game state
        state.goNext(this); // call the goNext method of the current game state, passing the AppWindow instance as a parameter to allow the state to update the window as needed
    }

    public GameState getGameState () {
        return state;
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    public void updateWindow() { // method to update the state of the window based on the current game state
        state.updateWindow(); // call the updateWindow method of the current game state to update the window components (e.g., enabling/disabling buttons) based on the state of the game
        canvas.repaint(); // repaint the canvas to update the display
    }

}
