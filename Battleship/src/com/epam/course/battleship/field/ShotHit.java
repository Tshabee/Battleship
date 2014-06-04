package com.epam.course.battleship.field;

class ShotHit extends Field {

	@Override
	public Field nextIfHit() {
		return this;
	}
	
	@Override
	public String toString() {
		return "X";
	}
}
