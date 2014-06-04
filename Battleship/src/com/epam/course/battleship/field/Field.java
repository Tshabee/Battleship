package com.epam.course.battleship.field;

import com.epam.course.battleship.ship.Ship;

public abstract class Field {

	public static Field createEmptyField() {
		return new Empty();
	}
	
	public static Field createShipField(Ship ship) {
		return new Occupied(ship);
	}
	
	public abstract Field nextIfHit();
	
	public void hit() {
		
	}
	
	public boolean isOccupied() {
		return false;
	}
	
	public boolean isHit() {
		return false;
	}
}
