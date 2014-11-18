package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {
	private CurrentLabel currLabel;
	
    protected StatusPanel(StatusLabel statusLabel) {
        currLabel = new CurrentLabel();
    	add(WEST, currLabel);
        add(CENTER, statusLabel);
    }
    
    public CurrentLabel getCurrentLabel() {
    	return currLabel;
    }
}