package gui;

import java.util.Observable;

public class ErrorMessage extends Observable{
	private String message;
	
	public ErrorMessage(){
	
	}
	
	public void Error(String message){
		this.message = message;
		setChanged();
		notifyObservers();
	}
	
	public String getMessage(){
		return message;
	}
}
