package com.epam.course.battleship.field;

import com.epam.course.battleship.ship.Ship;

class Occupied extends Field {

	private Ship ship;
	
	Occupied(Ship ship) {
		this.ship = ship;
	}
	
	@Override
	public Field nextIfHit() {
		return new ShotHit();
	}
	
	@Override
	public void hit() {
		ship.hit();
	}
	
	@Override
	public boolean isOccupied() {
		return true;
	}
	
	@Override
	public boolean isHit() {
		return true;
	}
	
	@Override
	public String toString() {
		return "O";
	}
}
