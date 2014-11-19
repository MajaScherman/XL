package gui;

import java.awt.Color;
import java.util.Observable;

public class CurrentAddress extends Observable{
	private SlotLabel current;
	
	public CurrentAddress(){
		current = new SlotLabel('A', 1, this);
	}
	
	public void setCurrent(SlotLabel newCurrent){
		current.setBackground(Color.WHITE);
		current = newCurrent;
		current.setBackground(Color.YELLOW);
		setChanged();
		notifyObservers();
	}
	
	public String getCurrent(){
		return current.toString();
	}
	
	public char getCol(){
		return current.getCol();
	}
	
	public int getRow(){
		return current.getRow();
	}
}
