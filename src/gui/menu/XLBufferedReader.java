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
		Scanner scan = null;
		SlotFactory factory = new SlotFactory();
		try {
			while (ready()) {
				String string = readLine();
				int i = string.indexOf('=');
				scan = new Scanner(string);
				map.put(scan.next(), factory.buildSlot(scan.next()));
			}
		} catch (Exception e) {
			throw new XLException(e.getMessage());
		}
	}
}
