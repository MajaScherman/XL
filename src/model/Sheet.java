package model;

import java.util.Observable;

import expr.Environment;
import gui.ErrorMessage;

public class Sheet extends Observable implements Environment {
	private SlotFactory factory;
	private SlotMap map;
	private ErrorMessage errorMessage;

	public Sheet(SlotFactory factory, ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
		this.factory = factory;
		map = new SlotMap();
	}

	public void clearSlotMap() {
		map = new SlotMap();
		setChanged();
		notifyObservers();
	}

	public void deleteSlot(String address) {

		map.remove(address, this);

	}

	public void createSlot(String currentSlotAddress, String editorText) {
		Slot tempSlot = null;
		try {
			tempSlot = map.get(currentSlotAddress);
		} catch (XLException e) {
			try {
				Slot newSlot = factory.buildSlot(editorText);
				map.put(currentSlotAddress, new BombSlot());
				newSlot.value(this);
				map.put(currentSlotAddress, newSlot);
				setChanged();
				notifyObservers();
				return;
			} catch (XLException e2) {
				System.out.print(e2.getMessage());
				errorMessage.error("Failed to build a slot: " + e2.getMessage());
				map.put(currentSlotAddress, tempSlot);
			}
		}
		try {

			Slot slot = factory.buildSlot(editorText);
			// kontroll efter cirkulärt beroende - efter bombslot

			map.put(currentSlotAddress, new BombSlot());
			slot.value(this);// if there is a circular dependency exception will
								// be thrown here
			map.put(currentSlotAddress, slot);
			setChanged();
			notifyObservers();

		} catch (XLException e) {
			System.out.print(e.getMessage());
			errorMessage.error("Failed to build a slot: " + e.getMessage());
			map.put(currentSlotAddress, tempSlot);
		}
	}

	/** Returnerar value:n av Sloten med addressen name */
	@Override
	public double value(String name) throws XLException {
		try {
			Slot slot = map.get(name);
			return slot.value(this);
		} catch (XLException e) {
			// errorMessage.Error(e.getMessage());
			throw e;
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
		try {
			Slot slot = map.get(address);
			return "" + slot.toString(this);
		} catch (XLException e) {
			return "";
		}
	}

	public String getEditorText(String address) {
		try {
			Slot slot = map.get(address);
			return slot.editorString();
		} catch (XLException e) {
			return "";
		}
	}
	
	public SlotMap getSlotMap() {
		return map;
	}
	
	public void finishLoad(){
		setChanged();
		notifyObservers();
	}
}
