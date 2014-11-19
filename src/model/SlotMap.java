package model;

import java.util.Collection;
import java.util.HashMap;

import expr.Environment;

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

	public boolean exists(String address) {
		return map.containsKey(address);
	}

}
