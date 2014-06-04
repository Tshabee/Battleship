package com.epam.course.battleship.ship;

import java.util.LinkedList;
import java.util.List;

public class ShipList extends LinkedList<Ship> {

	private static final long serialVersionUID = 1L;
	
	public ShipList(List<Ship> ships) {
		super(ships);
	}
	
	public int getTotalSize() {
		int totalSize = 0;
		
		for (Ship ship : this) {
			totalSize += ship.getSize();
		}
		
		return totalSize;
	}
}
