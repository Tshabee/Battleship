package com.epam.course.battleship.ship;

import java.util.LinkedList;
import java.util.List;

public class ShipImporter {
	
	private String rawData;
	private List<Ship> ships = new LinkedList<>();
	
	public ShipImporter(String rawData) {
		this.rawData = rawData;
	}
	
	public ShipList generateFromString() {
		String[] lines = rawData.split("\n");
		
		for (int i = 0; i < lines.length; i += 5) {
			loadNextShipType(lines, i);
		}
		
		return new ShipList(ships);
	}

	private void loadNextShipType(String[] lines, int i) {
		createShipsForType(copyShipData(lines, i), Integer.parseInt(lines[i + 4]));
	}

	private String[] copyShipData(String[] lines, int i) {
		String[] shipData = new String[Ship.SHIP_FIELD_SIZE];

		for (int j = 0; j < shipData.length; j++) {
			shipData[j] = lines[i + j];
		}
		
		return shipData;
	}

	private void createShipsForType(String[] shipData, int shipCount) {
		for (int i = 0; i < shipCount; i++) {
			ships.add(ShipFactory.createCustomShip(shipData));
		}
	}
}
