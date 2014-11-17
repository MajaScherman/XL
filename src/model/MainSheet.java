package model;

import java.util.Observable;

import expr.Environment;

public class MainSheet extends Observable implements Environment {
	private String currentSlotAddress;
	private SlotFactory factory;
	private SlotMap map;
	private String status;

	public MainSheet(SlotFactory factory) {
		this.factory = factory;
		map = new SlotMap();
	}

	public void setCurrent(String currentSlotAddress) {
		// TODO ska spara den inmatade
		this.currentSlotAddress = currentSlotAddress;
	}

	public void createSlot(String editorText) {
		try {
			// TODO anv√§nda currentSlot och SlotFactory f√∂r att bygga en slot
			// h‰r skulle vi v‰l inte anv‰nda current slot
			factory.buildSlot(editorText);

		} catch (XLException e) {
			// TODO: s√§tt statuslabel text
			System.out.print("Failed to build a slot");
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
		Slot slot = map.get(address);
		if (slot == null) {
			return "";

		} else {
			return slot.toString();
		}
	}

	public String getCurrent() {
		return currentSlotAddress;
	}

	public String getStatus() {
		// TODO returnera status (errormeddelande, etc) 
		return null;
	}

}
