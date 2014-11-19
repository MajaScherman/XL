package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import gui.menu.XLMenuBar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MainSheet;
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
    private MainSheet sheet;
    
    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    //TODO lägga till att man skapar(?) ett XL-fönster så skapas modellen (MainSheet)
    //		och så lägger man till dess Observers (CurrentLabel, SlotLabel, etc)
    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        
        errorMessage = new ErrorMessage();
        currentAddress = new CurrentAddress();
        sheet = new MainSheet(new SlotFactory(), errorMessage);
        
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
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel));
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
}