package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import gui.menu.XLMenuBar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import javax.swing.JFrame;

import model.Sheet;
import model.SlotFactory;

public class XL extends JFrame implements Printable {
    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;
    private StatusPanel statusPanel;
    private CurrentLabel currentLabel;
    private CurrentAddress currentAddress;
    private ErrorMessage errorMessage;
    private Sheet sheet;
    
    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        
        errorMessage = new ErrorMessage();
        currentAddress = new CurrentAddress();
        sheet = new Sheet(new SlotFactory(), errorMessage);
        
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();
        statusPanel = new StatusPanel(statusLabel);
        SheetPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, currentAddress);
        Editor editor = new Editor(sheet);                                                                                                   
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel, sheet));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        errorMessage.addObserver(statusLabel);
        currentAddress.addObserver(statusPanel.getCurrentLabel());
        currentAddress.addObserver(editor);
        SlotLabels slotLabels = sheetPanel.getSlotLabels();
        sheet.addObserver(slotLabels);
        slotLabels.clickFirstLabel();
    }

    public int print(Graphics g, PageFormat pageFormat, int page) {
        if (page > 0)
            return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(g2d);
        return PAGE_EXISTS;
    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
    
    public Sheet getSheet() {
    	return sheet;
    }
    
    public CurrentAddress getCurrentAddress() {
    	return currentAddress;
    }
    
    public ErrorMessage getErrorMessage() {
    	return errorMessage;
    }
}