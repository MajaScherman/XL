package gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.SwingConstants;

public class SlotLabels extends GridPanel {
    private List<SlotLabel> labelList;
    private List<SlotLabel> observers;

    public SlotLabels(int rows, int cols) {
        super(rows + 1, cols);
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY,
                    SwingConstants.CENTER));
        }
    	observers = new ArrayList<SlotLabel>();
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                SlotLabel label = new SlotLabel(ch, row);
                add(label);
                labelList.add(label);
                observers.add(label);
            }
        }
        SlotLabel firstLabel = labelList.get(0);
        firstLabel.setBackground(Color.YELLOW);
    }
    
    public List<SlotLabel> getObservers() {
    	return observers;
    }
}
