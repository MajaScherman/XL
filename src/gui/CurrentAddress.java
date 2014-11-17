package gui;

import java.util.Observable;

public class CurrentAddress extends Observable{
	private String current;
	
	public CurrentAddress(){
		current = "A1";
	}
	
	public void setCurrent(String newCurrent){
		current = newCurrent;
		setChanged();
		notifyObservers();
	}
	
	public String getCurrent(){
		return current;
	}
}
