package model;

import java.util.Observable;

import expr.Environment;
import gui.ErrorMessage;
import gui.StatusLabel;

public class MainSheet extends Observable implements Environment {
	private SlotFactory factory;
	private SlotMap map;
	private ErrorMessage errorMessage;
	
	// TODO notify observers on the right places
	public MainSheet(SlotFactory factory) {
		errorMessage = new ErrorMessage();
		this.factory = factory;
		map = new SlotMap();
	}

	/*
	 * remove/clear-metod behöver implementeras 
	 * Ganska mycket av
	 * createSlot-metoden saknas också än så länge, den ska checka efter
	 * cirkel-beroende samt kontrollera att uttrycket som tas in inte refererar
	 * till ickeinstansierade slots, vi kom också överens om att den skulle ta
	 * currentSlot som argument om jag inte minns fel Vi behöver också hjälpas
	 * åt att implementera alla olika menyalternativ som inte är relaterade till
	 * felhantering, typ print, save, load etc.
	 * 
	 */
	public void clearSlotMap(){
		map = new SlotMap();
	}
	public void deleteSlot(String address){
		
		map.remove(address, this);	
		
	}
	
	
	public void createSlot(String currentSlotAddress, String editorText) {
		Slot tempSlot = map.get(currentSlotAddress);
		try {
			
			Slot slot = factory.buildSlot(editorText);
			//kontroll efter cirkulärt beroende - efter bombslot
	
			map.put(currentSlotAddress, new BombSlot());
			slot.value(this);// if there is a circular dependency exception will be thrown here
			map.put(currentSlotAddress, slot);
			setChanged(); 
			notifyObservers();

		} catch (XLException e) {
			System.out.print(e.getMessage());
			errorMessage.Error("Failed to build a slot: " + e.getMessage());
			map.put(currentSlotAddress, tempSlot);
		}
	}

	/** Returnerar value:n av Sloten med addressen name */
	@Override
	public double value(String name) {
		try {
			Slot slot = map.get(name);
			return slot.value(this);
		} catch (XLException e) {
			errorMessage.Error(e.getMessage());
			return 0;
		}
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
	
	public String getEditorText() {
		return null;
	}

}
