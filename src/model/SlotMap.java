package model;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

public class SlotMap {
	// address, slot
	private HashMap<String, Slot> map;

	public SlotMap() {

		map = new HashMap<String, Slot>();
	}

	public Slot put(String address, Slot slot) {

		return map.put(address, slot);
	}

	public Slot get(String address) throws XLException {
		Slot slot = map.get(address);
		if (slot == null) {
			throw new XLException(
					"There is no data in this slot,"+ address );
		}

		return slot;
	}

	public Slot remove(String address, MainSheet sheet) throws XLException {
		Slot tempSlot = this.get(address);
		put(address, new BombSlot());
		Collection<Slot> slots = map.values();
		try {
			for (Slot s : slots) {
				s.value(sheet);
			}
		} catch (XLException e) {
			throw new XLException("Slot at " + address
					+ " is used somwhere else." + e.getMessage());
		}
		map.remove(address);
		return tempSlot;
	}
	
	public Set<Entry<String, Slot>> getAddresSlotSet() {
		Set<Entry<String, Slot>> addresSlotset = new HashSet<Entry<String, Slot>>();
		Set<String> addresses = map.keySet();
		for(String address : addresses) {
			Slot slot = map.get(address);
			addresSlotset.add(new SimpleEntry<String, Slot>(address, slot));
		}
		return addresSlotset;
	}
}
