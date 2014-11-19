package model;

import expr.Environment;

public class BombSlot implements Slot {

	@Override
	public double value(Environment env) {
		throw new XLException("Circular dependency detected!");
	}

	@Override
	public String toString(Environment env) {
		return "bomb";
	}

}
