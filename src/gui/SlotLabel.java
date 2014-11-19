package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SlotLabel extends ColoredLabel implements MouseListener {
	private char col;
	private int row;
	private CurrentAddress curr;
	
    public SlotLabel(char col, int row, CurrentAddress curr) {
        super("                    ", Color.WHITE, RIGHT);
        this.col = col;
        this.row = row;
        this.curr = curr;
        addMouseListener(this);
    }

	public String toString(){
		return "" + col + row;
	}
	
	public char getCol(){
		return col;
	}
	
	public int getRow(){
		return row;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		curr.setCurrent(this);			
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}


	public void mouseExited(MouseEvent arg0) {
		
	}


	public void mousePressed(MouseEvent arg0) {
		
	}


	public void mouseReleased(MouseEvent arg0) {
		
	}
    
}