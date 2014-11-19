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
	public MainSheet(SlotFactory factory, ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
		this.factory = factory;
		map = new SlotMap();
	}

	/*
	 * remove/clear-metod behöver implementeras Ganska mycket av
	 * createSlot-metoden saknas också än så länge, den ska checka efter
	 * cirkel-beroende samt kontrollera att uttrycket som tas in inte refererar
	 * till ickeinstansierade slots, vi kom också överens om att den skulle ta
	 * currentSlot som argument om jag inte minns fel Vi behöver också hjälpas
	 * åt att implementera alla olika menyalternativ som inte är relaterade till
	 * felhantering, typ print, save, load etc.
	 */
	public void clearSlotMap() {
		map = new SlotMap();
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
				map.put(currentSlotAddress, newSlot);
				setChanged();
				notifyObservers();
				return;
			} catch (XLException e2) {
				System.out.print(e2.getMessage());
				errorMessage.Error("Failed to build a slot: " + e2.getMessage());
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
			errorMessage.Error("Failed to build a slot: " + e.getMessage());
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
			return slot.toString(this);
		} catch (XLException e) {
			errorMessage.Error(e.getMessage());
			return "";
		}
	}

	public String getEditorText() {
		return null;
	}

}
