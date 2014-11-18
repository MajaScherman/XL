package model;

import java.util.Observable;

import expr.Environment;
import gui.ErrorMessage;
import gui.StatusLabel;

public class MainSheet extends Observable implements Environment {
	private String currentSlotAddress;
	private SlotFactory factory;
	private SlotMap map;
	private StatusLabel statusLabel; //is it ok for me to add this and thus create a connection to the gui? dont know how to do it otherwise
	private ErrorMessage errorMessage;
	private Slot slot;
//TODO notify observers on the right places
	public MainSheet(SlotFactory factory) {
		this.statusLabel = new StatusLabel();
		errorMessage = new ErrorMessage();
		this.factory = factory;
		map = new SlotMap();
	}

	public void setCurrent(String currentSlotAddress) {
		// TODO ska spara den inmatade
		this.currentSlotAddress = currentSlotAddress;
	}

	public void createSlot(String editorText) {
		try {
			// TODO anvÃ¤nda currentSlot och SlotFactory fÃ¶r att bygga en slot
			slot = factory.buildSlot(editorText);
			//bygger på att current är verkligen satt först!!!
			map.put(currentSlotAddress, slot);
			notifyObservers();//här kanske e bra
			
		} catch (XLException e) {
			// TODO: sÃ¤tt statuslabel text
	//		System.out.print("Failed to build a slot");
			System.out.print(e.getMessage());
			errorMessage.Error("Failed to build a slot" + e.getMessage());
			statusLabel.update(this, errorMessage);
		}
	}

	/** Returnerar value:n av Sloten med addressen name */
	@Override
	public double value(String name) {
		Slot slot = map.get(name);
		return slot.value(this);
	}

	/* ************************************
	 * BELOW ARE ALL THE GETSTATE() METHODS ************************************
	 */

	/**
	 * Returns the text of the slot with the specified address. If the Slot
	 * doesn't exist, it returns an empty string
	 */
	public String getSlotText(String address) {

		// kolla om Slot:en finns i SlotMap med exists(String)
		// om den inte finns, returna ""
		// annars, returnera slotens toString()
		
		if (!map.exists(address)) {
			return "";

		} else {
			Slot slot = map.get(address);
			return slot.toString();
		}
	}

	public String getCurrent() {
		return currentSlotAddress;
	}

	public String getStatus() {
		// TODO returnera status (errormeddelande, etc) 
		
		return errorMessage.getMessage();
	}

}
