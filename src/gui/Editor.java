package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

import model.MainSheet;

public class Editor extends JTextField implements Observer, ActionListener {
    private MainSheet sheet;
    private String currentAddress;
    
	public Editor(MainSheet sheet) {
        setBackground(Color.WHITE);
        this.sheet = sheet;
        addActionListener(this);
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		CurrentAddress curr = (CurrentAddress) arg0;
		currentAddress = curr.getCurrent();
		setText("" + sheet.getEditorText(currentAddress));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String txt = this.getText();
		if(txt.length() != 0){
			sheet.createSlot(currentAddress, this.getText());
		}
	}
}