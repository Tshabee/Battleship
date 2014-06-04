package com.epam.course.battleship.ship;

final class ShipFactory {

	static Ship createSingleFieldShip() {
		return createSimpleShip("Single", 1);
	}
	
	static Ship createDoubleFieldShip() {
		return createSimpleShip("Double", 2);
	}
	
	static Ship createTripleFieldShip() {
		return createSimpleShip("Triple", 3);
	}
	
	 static Ship createQuadrupleFieldShip() {
		return createSimpleShip("Quadruple", 4);
	}
	
	static Ship createTetrisShip() {
		Ship result = new Ship("Tetris");
		
		result.setFieldToOccupied(0, 1);
				
		for (int i = 0; i < 3; i++) {
			result.setFieldToOccupied(1, i);
		}
		
		return result;
	}

	static Ship createCustomShip(String[] data) {
		normalizeCustomShipData(data);
		return createCustomShipFromNormalizedData(data);
	}

	private static void normalizeCustomShipData(String[] data) {
		while (!isNormalized(data)) {
			normalize(data);
		}
	}

	private static void normalize(String[] data) {
		String firstLine = data[0];
		for (int x = 0; x < Ship.SHIP_FIELD_SIZE - 1; x++) {
			data[x] = data[x + 1];
		}
		data[Ship.SHIP_FIELD_SIZE - 1] = firstLine;
	}

	private static boolean isNormalized(String[] data) {
		for (int i = 0; i < data[0].length(); i++) {
			if (data[0].charAt(i) == 'x') {
				return true;
			}
		}
		return false;
	}

	private static Ship createCustomShipFromNormalizedData(String[] data) {
		Ship result = new Ship("Custom");
		fillCustomShipData(data, result);
		return result;
	}

	private static void fillCustomShipData(String[] data, Ship result) {
		for (int x = 0; x < Ship.SHIP_FIELD_SIZE; x++) {
			for (int y = 0; y < Ship.SHIP_FIELD_SIZE; y++) {
				if (data[x].charAt(y) == 'x') {
					result.setFieldToOccupied(x, y);
				}
			}
		}
	}
	
	private static Ship createSimpleShip(String name, int width) {
		Ship result = new Ship(name);
		fillSimpleShipData(width, result);
		return result;
	}

	private static void fillSimpleShipData(int width, Ship result) {
		for (int i = 0; i < width; i++) {
			result.setFieldToOccupied(0, i);
		}
	}

	private ShipFactory() { }
}
