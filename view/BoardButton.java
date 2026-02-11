package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import model.Marking;

public class BoardButton extends JButton{

    private int pos; // index of the button on the board

    public BoardButton(int pos) {
        this.pos = pos; // store the position of the button
        setFont(new Font("Courier New", Font.BOLD, 84)); // set the font of the button
        setForeground(Color.BLUE); // set the foreground color of the button to blue
        setMark(Marking.U); // set the initial mark of the button to U (unmarked)
    }

    public int getPos() {
        return pos; // return the position of the button
    }

    public void setMark(Marking mark) {
        String label = mark.name(); // get the name of the enum value
        if (mark == Marking.U) label = "."; // if the mark is U, set the label to an empty string
        setText(label); // set the text of the button to the label
    }


    
}
