package gui.menu;

import gui.StatusLabel;
import gui.XL;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Observable;

import javax.swing.JFileChooser;

import model.Sheet;
import model.Slot;

class LoadMenuItem extends OpenMenuItem {
	Sheet sh;
	
    public LoadMenuItem(XL xl, StatusLabel statusLabel, Sheet sh) {
        super(xl, statusLabel, "Load");
        this.sh = sh;
    }

    protected void action(String path) throws FileNotFoundException {
    	Map<String, Slot> map = xl.getSheet().getSlotMap();
    	XLBufferedReader reader = new XLBufferedReader(path);
    	reader.load(map);
    	sh.finishLoad();
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}