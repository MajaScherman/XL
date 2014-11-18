package gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class CurrentLabel extends ColoredLabel implements Observer  {
    public CurrentLabel() {
        super("A1", Color.WHITE);
    }
    
	@Override
	public void update(Observable arg0, Object arg1) {
		CurrentAddress temp = (CurrentAddress) arg0;
		this.setText(temp.getCurrent());
	}
}