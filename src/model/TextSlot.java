package model;

import expr.Environment;

public class TextSlot implements Slot {
	private String txt;
	
	//TODO ska man lägga till en '#' när man kallar toString eller ska den finnas med i txt-String:en hela tiden?
	public TextSlot(String txt) {
		this.txt = txt;
	}

	@Override
	public double value(Environment env) {
		return 0;
	}
	
	@Override
	public String toString(Environment env) {
		return txt;
	}
}
