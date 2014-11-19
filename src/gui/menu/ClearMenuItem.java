package gui.menu;

import gui.ErrorMessage;
import gui.XL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import model.XLException;

class ClearMenuItem extends JMenuItem implements ActionListener {
	private XL xl;
	
    public ClearMenuItem(XL xl) {
        super("Clear");
        this.xl = xl;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
    	String currentSlotAddress = xl.getCurrentAddress().getCurrent();
    	try {
    		xl.getSheet().deleteSlot(currentSlotAddress);
		} catch (XLException err) {
			ErrorMessage errorMessage = xl.getErrorMessage();
			errorMessage.Error(err.getMessage());
		}
    }
}