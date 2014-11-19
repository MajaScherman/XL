package model;

import java.util.Observable;

import expr.Environment;
import gui.ErrorMessage;
import gui.StatusLabel;

public class MainSheet extends Observable implements Environment {
	private SlotFactory factory;
	private SlotMap map;
	//private StatusLabel statusLabel; //is it ok for me to add this and thus create a connection to the gui? dont know how to do it otherwise
	//statusLabel behövs inte, kommenterar ut allt med den
	private ErrorMessage errorMessage;
	private Slot slot;
//TODO notify observers on the right places
	public MainSheet(SlotFactory factory) {
		//this.statusLabel = new StatusLabel();
		errorMessage = new ErrorMessage();
		this.factory = factory;
		map = new SlotMap();
	}

	public void createSlot(String currentSlotAddress, String editorText) {
		try {
			slot = factory.buildSlot(editorText);
			//bygger på att current är verkligen satt först!!!
			map.put(currentSlotAddress, slot);
			setChanged(); //tydligen måste detta göras innan man kör notifyObservers
			notifyObservers();//här kanske e bra
			
		} catch (XLException e) {
	//		System.out.print("Failed to build a slot");
			System.out.print(e.getMessage());	//man kan fråga sig om detta verkligen behövs
			errorMessage.Error("Failed to build a slot: " + e.getMessage());
			//statusLabel.update(this, errorMessage);
			//raden ovan behövs inte, errormessage kör notifyObservers i errorMessage.Error()
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

}
