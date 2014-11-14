package model;

import expr.Environment;
import expr.Expr;

public class ExprSlot extends Slot {
	private Expr e;
	
	public ExprSlot(Expr e) {
		
	}
	
	@Override
	public double value(Environment env) {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}
}
