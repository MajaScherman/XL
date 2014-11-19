package model;

import expr.Environment;

public class BombSlot implements Slot {

	@Override
	public double value(Environment env) {
		throw new XLException("Circular dependency detected!");
	}

	@Override
	public String toString(Environment env) {
		throw new XLException("This is a bomb, circular dependency detected");
	}
	
	public String editorString(Environment env){
		return toString(env);
	}
}
