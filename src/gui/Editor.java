package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import model.MainSheet;

public class Editor extends JTextField implements Observer {
    private MainSheet sheet;
	public Editor(MainSheet sheet) {
        setBackground(Color.WHITE);
        this.sheet = sheet;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		CurrentAddress curr = (CurrentAddress) arg0;
		setText("" + sheet.getSlotText(curr.getCurrent()));
	}
}