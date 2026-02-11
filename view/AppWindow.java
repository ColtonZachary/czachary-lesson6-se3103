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

import controller.ButtonListener;
import controller.NewGameButtonListener;
import controller.StrategyButtonListener;

public class AppWindow extends JFrame {

    public static final String vsHumanAction = "vs. Human";
    public static final String vsComputerAction = "vs. Computer";

    private AppCanvas canvas = new AppCanvas();
    private BoardButton[] markingButtons = new BoardButton[9]; // array of buttons for the tic-tac-toe board
    private JButton newGameButton = new JButton("New Game"); // button for starting a new game
    private JRadioButton vsHumanButton = new JRadioButton(vsHumanAction); // button for playing against another human
    private JRadioButton vsComputerButton = new JRadioButton(vsComputerAction); // button for playing against the computer

    public void init() {
        var cp = getContentPane(); // get the content pane of the JFrame
        cp.add(canvas, BorderLayout.NORTH); // add the canvas to the center of the content pane

        ButtonListener buttonListener = new ButtonListener(); // create a new ButtonListener for handling button clicks
        for (int i = 0; i < markingButtons.length; i++) {
            markingButtons[i] = new BoardButton(i); // create a new BoardButton for each position on the board
            markingButtons[i].addActionListener(buttonListener);
        }

        JPanel gameBoardPanel = new JPanel(); // create a new JPanel for the game board
        gameBoardPanel.setLayout(new GridLayout(3, 3)); // set the layout of the game board panel to a 3x3 grid
        for (var cell: markingButtons) {
            gameBoardPanel.add(cell); // add each BoardButton to the game board panel
        }
        cp.add(gameBoardPanel, BorderLayout.CENTER); // add the game board panel to the south of the content pane

        JPanel southPanel = new JPanel(); // create a new JPanel for the south part of the window
        southPanel.setLayout(new GridLayout(2, 1)); // set the layout of the south panel to a grid layout
        cp.add(southPanel, BorderLayout.SOUTH); // add the south panel to the south of the content pane

        JPanel radioPanel = new JPanel(); // create a new JPanel for the radio buttons
        radioPanel.setBorder(new TitledBorder("Play Strategy"));
        radioPanel.add(vsHumanButton); // add the vsHumanButton to the radio panel
        radioPanel.add(vsComputerButton); // add the vsComputerButton to the radio panel
        StrategyButtonListener strategyListener = new StrategyButtonListener(); // create a new StrategyButtonListener for handling strategy selection
        vsHumanButton.addActionListener(strategyListener); // add an action listener to the vsHumanButton to handle when it is selected
        vsComputerButton.addActionListener(strategyListener); 
        ButtonGroup strategyGroup = new ButtonGroup();
        strategyGroup.add(vsHumanButton); // add the vsHumanButton to the button group
        strategyGroup.add(vsComputerButton); // add the vsComputerButton to the button group
        southPanel.add(radioPanel); // add the radio panel to the south panel

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(new TitledBorder("Action"));
        actionPanel.add(newGameButton); // add the newGameButton to the action panel
        newGameButton.addActionListener(new NewGameButtonListener()); // add an action listener to the new game button to start a new game when clicked
        JButton exitButton = new JButton("Exit"); // create a new button for exiting the application
        exitButton.addActionListener(e -> System.exit(0)); // add an action listener to the exit button to exit the application when clicked
        actionPanel.add(exitButton); // add the exit button to the action panel
        southPanel.add(actionPanel); // add the action panel to the south panel

    }

    public void updateWindow() {
        canvas.repaint(); // repaint the canvas to update the display

    }

}
