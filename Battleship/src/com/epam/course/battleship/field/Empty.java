package com.epam.course.battleship.field;

class Empty extends Field {

	@Override
	public Field nextIfHit() {
		return new ShotMissed();
	}
	
	@Override
	public String toString() {
		return "~";
	}
}
