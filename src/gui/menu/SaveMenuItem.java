package gui.menu;

import gui.StatusLabel;
import gui.XL;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JFileChooser;

import model.Slot;
import model.SlotMap;

class SaveMenuItem extends OpenMenuItem {
	private XL xl;
    public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
        this.xl = xl;
    }

    protected void action(String path) throws FileNotFoundException {
        XLPrintStream printStream = new XLPrintStream(path);
        Set<Entry<String, Slot>> addressSlotSet = xl.getSheet().getSlotMap().getAddresSlotSet();
        printStream.save(addressSlotSet);
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}