package model;

import expr.Environment;

public interface Slot {
	
	public double value(Environment env);
	public abstract String toString(Environment env);
	public String editorString(Environment env);
}
