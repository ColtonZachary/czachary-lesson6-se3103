package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameButtonListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        App.game.reset(); // Reset the game state
        App.win.goNextState(); // Transition to the next game state (e.g., from INIT to PLAYING)
        App.win.updateWindow();
    }
}
