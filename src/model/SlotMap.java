package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class SlotMap extends HashMap<String, Slot> {
	// address, slot
	private HashMap<String, Slot> map;

	public SlotMap() {
		super();
	}

	public Slot get(String address) throws XLException {
		Slot slot = super.get(address);
		if (slot == null) {
			throw new XLException(
					"There is no data in this slot,"+ address );
		}
		return slot;
	}

	public Slot remove(String address, Sheet sheet) throws XLException {
		Slot tempSlot = super.get(address);
		put(address, new BombSlot());
		Collection<Slot> slots = values();
		try {
			for (Slot s : slots) {
				s.value(sheet);
			}
		} catch (XLException e) {
			throw new XLException("Slot at " + address
					+ " is used somwhere else." + e.getMessage());
		}
		super.remove(address);
		return tempSlot;
	}
	
	public Set<Entry<String, Slot>> getAddresSlotSet() {
		Set<Entry<String, Slot>> addresSlotset = new HashSet<Entry<String, Slot>>();
		Set<String> addresses = keySet();
		for(String address : addresses) {
			Slot slot = super.get(address);
			addresSlotset.add(new SimpleEntry<String, Slot>(address, slot));
		}
		return addresSlotset;
	}
}
