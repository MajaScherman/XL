package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements ActionListener, Observer {
	private char col;
	private int row;
	private CurrentAddress curr;
	
    public SlotLabel(char col, int row, CurrentAddress curr) {
        super("                    ", Color.WHITE, RIGHT);
        this.col = col;
        this.row = row;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//TODO kalla på att currentLabel i mainsheet ska uppdateras
		curr.setCurrent(col + "" + row);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO hämta labelns innehåll från model
		
	}
    
}