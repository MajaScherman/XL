package model;

import expr.Environment;
import expr.Expr;

public class ExprSlot implements Slot {
	private Expr e;
	private double value;
	
	public ExprSlot(Expr e) {
		this.e = e;
	}
	
	@Override
	public double value(Environment env) {
		try {
			value = e.value(env);
			return value;
		} catch (XLException e) {
			throw e;
		}
	}

	@Override
	public String toString(Environment env) throws XLException {
		return "" + value(env);
	}
	
	public String editorString() {
		return e.toString();
	}
}
