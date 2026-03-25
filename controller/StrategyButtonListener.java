package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.strategyPattern.VsComputerStrategy;
import model.strategyPattern.VsHumanStrategy;
import view.AppWindow;
import view.statePattern.VsSmartComputerStrategy;

public class StrategyButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        var actionCommand = e.getActionCommand(); // get the action command from the event
        switch (actionCommand) { // switch on the action command to determine which button was selected
            case AppWindow.vsHumanAction:
                App.game.setStrategy(new VsHumanStrategy(App.game)); // set the game to be against the human
                break; 
            case AppWindow.vsComputerAction:
                App.game.setStrategy(new VsComputerStrategy(App.game)); // set the game to be against the computer
                break; 
            case AppWindow.vsSmartComputerAction:
                App.game.setStrategy(new VsSmartComputerStrategy(App.game)); // set the game to be against the smart computer
                break;
        }
    }
}
