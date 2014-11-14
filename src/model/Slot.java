package model;

import expr.Environment;

public abstract class Slot {
	private String address;
	
	/** All extensions of Slot will return 0 by default, unless 
	 * otherwise specified, as in ExprSlot */
	public double value(Environment env) {
		return 0;
	}
	public abstract String toString();
}
