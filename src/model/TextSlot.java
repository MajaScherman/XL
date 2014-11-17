package model;

import expr.Environment;

public class TextSlot implements Slot {
	private String txt;
	
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
