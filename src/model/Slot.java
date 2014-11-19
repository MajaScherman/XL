package model;

import expr.Environment;

public interface Slot {
	
	public double value(Environment env);
	public String toString(Environment env);
	public String editorString(Environment env);
}
