package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class Editor extends JTextField implements Observer {
    public Editor() {
        setBackground(Color.WHITE);
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO Fyll editor-textfielden med Expression/commenten från den nuvarande valda Slot:en
	}
}