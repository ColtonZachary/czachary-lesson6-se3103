package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import model.Marking;

public class BoardButton extends JButton{

    private int pos; // index of the button on the board

    public BoardButton(int pos) {
        this.pos = pos; // store the position of the button
        setFont(new Font("Courier New", Font.BOLD, 84)); 
        setForeground(Color.BLUE); // set the foreground color of the button to blue
        setMark(Marking.U); // set the initial mark of the button to U (unmarked)
    }

    public int getPos() {
        return pos; // return the position of the button
    }

    public void setMark(Marking mark) { // method to set the mark of the button based on the state of the corresponding cell on the board
        String label = mark.name(); // get the name of the enum value // 'enum' is a special data type that enables for a variable to be a set of predefined constants. The 'name()' method returns the name of the enum constant as a string.
        if (mark == Marking.U) label = "."; // if the mark is U, set the label to an empty string
        setText(label); // set the text of the button to the label
    }


    
}
