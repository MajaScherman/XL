package model;

import java.util.Observable;

import expr.Environment;

public class MainSheet extends Observable implements Environment {
	private String currentSlotAddress;
	private SlotFactory factory;
	private SlotMap map;
	private String status;
	
	public void setCurrent(String currentSlotAddress) {
		//TODO ska spara den inmatade
	}
	
	public void createSlot(String editorText) {
		try {
			//TODO använda currentSlot och SlotFactory för att bygga en slot
		} catch (XLException e) {
			// TODO: sätt statuslabel text
		}
	}

	/** Returnerar value:n av Sloten med addressen name */
	@Override
	public double value(String name) {
		//TODO implementera
		return 0;
	}
	
	/* ************************************
	 * BELOW ARE ALL THE GETSTATE() METHODS
	 * ************************************ */
	
	/** Returns the text of the slot with the specified address. 
	 * 	If the Slot doesn't exist, it returns an empty string */
	public String getSlotText(String address) {
		
		//kolla om Slot:en finns i SlotMap med exists(String)
			//om den inte finns, returna ""
			//annars, returnera slotens toString()
		return null;
	}
	
	public String getCurrent() {
		return currentSlotAddress;
	}
	
	public String getStatus() {
		//TODO returnera status (errormeddelande, etc)
		return null;
	}
	
}
