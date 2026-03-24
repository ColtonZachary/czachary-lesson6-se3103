package view.statePattern;

import java.awt.Graphics2D;

import view.AppWindow;

public interface GameState {
    void goNext(AppWindow context);
    void updateWindow();
    void updateCanvas(Graphics2D g2);
    
}