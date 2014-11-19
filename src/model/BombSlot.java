package model;

import expr.Environment;

public class BombSlot implements Slot {

	public double value(Environment env) {
		throw new XLException("Circular dependency detected!");
	}

	public String toString(Environment env) {
		throw new XLException("This is a bomb, circular dependency detected");
	}
	
	public String editorString(){
		return null;
	}

}
