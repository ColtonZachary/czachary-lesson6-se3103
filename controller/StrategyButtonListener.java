package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PlayStrategy;
import view.AppWindow;

public class StrategyButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        var actionCommand = e.getActionCommand(); // get the action command from the event
        switch (actionCommand) { // switch on the action command to determine which button was selected
            case AppWindow.vsHumanAction:
                App.game.setStrategy(PlayStrategy.VsHuman); // set the game to be against the human
                break; 
            case AppWindow.vsComputerAction:
                App.game.setStrategy(PlayStrategy.VsComputer); // set the game to be against the computer
                break; 
    }
    

    }
}
