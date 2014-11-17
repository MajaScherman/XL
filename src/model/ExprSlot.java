package model;

import expr.Environment;
import expr.Expr;

public class ExprSlot implements Slot {
	private Expr e;
	
	public ExprSlot(Expr e) {
		this.e = e;
	}
	
	@Override
	public double value(Environment env) {
		return e.value(env);
	}

	@Override
	public String toString(Environment env) {
		return "" + e.value(env);
	}
}
