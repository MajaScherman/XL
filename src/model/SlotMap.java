package model;

import java.util.HashMap;

public class SlotMap {
				   //address, slot
	private HashMap<String, Slot> map;
	
	public Slot put(String address, Slot slot) {
		// map.put()
		return null;
	}
	
	public Slot get(String address) throws XLException {
		//if map.get(address) == null
			//return NullSlot;
		
		//TODO ska kasta exception när t.ex. vi har A3 = A1 + A2 och A1/A2 är tom
		return null;
	}
	
	public Slot remove(String address) {
		return map.remove(address);
	}

	public boolean exists(String address) {
		return map.containsKey(address);
	}
	
}
