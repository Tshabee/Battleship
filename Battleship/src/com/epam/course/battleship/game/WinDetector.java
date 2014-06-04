package com.epam.course.battleship.game;

import com.epam.course.battleship.game.exception.AllShipsSunkException;
import com.epam.course.battleship.ship.ShipImporter;

public class WinDetector {

	private int numberOfHitsRequired;
	private int numberOfHits;
	
	public WinDetector(String rawShipData) {
		this.numberOfHitsRequired = new ShipImporter(rawShipData).generateFromString().getTotalSize();
	}
	
	public void hit() {
		numberOfHits++;
		
		if (numberOfHits >= numberOfHitsRequired) {
			throw new AllShipsSunkException();
		}
	}
}
