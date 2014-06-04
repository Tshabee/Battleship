package com.epam.course.battleship.game;

import java.util.Collection;
import java.util.Iterator;

import com.epam.course.battleship.Point;
import com.epam.course.battleship.ship.Ship;
import com.epam.course.battleship.ship.ShipImporter;
import com.epam.course.battleship.ship.ShipList;
import com.epam.course.battleship.util.FileContentReader;

public class Game {

	public static final int BOARD_SIZE = 30;
	
	private Board board;
	private Collection<Ship> ships;
	
	public Game() {
		this(BOARD_SIZE, BOARD_SIZE, FileContentReader.readFileContent("ships.txt"));
	}
	
	public Game(int boardWidth, int boardHeight, String rawShipData) {
		board = new Board(boardWidth, boardHeight);
		initializeShips(rawShipData);
		initializeBoard();
		printBoard();
	}

	private void initializeBoard() {
		for (Ship ship : ships) {
			addShipToBoard(ship);
		}
	}
	
	public FireResult shoot(Point point) {
		FireResult result = board.handleShot(point) ? FireResult.HIT : FireResult.MISS;
		result = checkShipsForSunkOne(result);
		
		if (hasWon()) {
			result = FireResult.WIN;
		}
		
		return result;
	}
	
	public boolean hasWon() {
		return ships.size() == 0;
	}
	
	public void printBoard() {
		board.printBoard();
	}
	
	private FireResult checkShipsForSunkOne(FireResult result) {
		Iterator<Ship> shipIterator = ships.iterator();
		
		while (shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			result = handleShipRemovalIfNecessary(shipIterator, ship, result);
		}
		
		return result;
	}

	private FireResult handleShipRemovalIfNecessary(Iterator<Ship> shipIterator, Ship ship, FireResult result) {
		if (ship.isSunk()) {
			System.out.println(String.format("A %s ship has sunk", ship));
			shipIterator.remove();
			result = FireResult.SUNK;
		}
		return result;
	}
	
	private void initializeShips(String rawShipData) {
		ships = new ShipList(new ShipImporter(rawShipData).generateFromString());
	}
	
	private void addShipToBoard(Ship ship) {
		while (!board.addShip(ship, Point.randomPoint(BOARD_SIZE, BOARD_SIZE)));
	}
}
