package com.epam.course.battleship.field;

class ShotMissed extends Field {

	@Override
	public Field nextIfHit() {
		return this;
	}
	
	@Override
	public String toString() {
		return "M";
	}
}
