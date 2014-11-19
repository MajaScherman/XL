package gui.menu;

import gui.StatusLabel;
import gui.XL;

import java.io.FileNotFoundException;
import java.util.Map;

import javax.swing.JFileChooser;

import model.Slot;

class LoadMenuItem extends OpenMenuItem {
 
    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
    	Map<String, Slot> map = xl.getSheet().getSlotMap();
    	XLBufferedReader reader = new XLBufferedReader(path);
    	reader.load(map);
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}