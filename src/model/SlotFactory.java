package model;

import java.io.IOException;

import expr.ExprParser;

public class SlotFactory {
	private String contents;
	StringBuilder sb;

	public Slot buildSlot(String input) {
		sb = new StringBuilder(input);
		char letter = sb.charAt(0);
		if (letter == '#') {
			sb.deleteCharAt(0);
			contents = sb.toString();
			return new TextSlot(contents);

		} else {
			ExprParser parser = new ExprParser();
			
			try {
				return new ExprSlot(parser.build(input));
			} catch (IOException e) {
				throw new XLException(e.getMessage());
			}
			
	

		}

	}
}
