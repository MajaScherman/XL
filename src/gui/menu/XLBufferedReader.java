package gui.menu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

import model.Slot;
import model.SlotFactory;
import model.XLException;

public class XLBufferedReader extends BufferedReader {
	public XLBufferedReader(String name) throws FileNotFoundException {
		super(new FileReader(name));
	}

	public void load(Map<String, Slot> map) {
		SlotFactory factory = new SlotFactory();
		try {
			while (ready()) {
				String string = readLine();
				int i = string.indexOf('=');
				StringBuilder sb = new StringBuilder();
				String address = "";
				int k = 0;
				while (k != i) {
					sb.append(string.charAt(k));
					k++;
				}
				address = sb.toString();
				sb = new StringBuilder();
				String expr;
				k++;
				while (k < string.length()) {
					sb.append(string.charAt(k));
					k++;
				}
				expr = sb.toString();
				Slot s = factory.buildSlot(expr);
				map.put(address, s);
			}
		} catch (Exception e) {
			throw new XLException(e.getMessage());
		}
	}
}
