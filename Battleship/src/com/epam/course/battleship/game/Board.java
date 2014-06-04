package com.epam.course.battleship.game;

import com.epam.course.battleship.Point;
import com.epam.course.battleship.field.Field;
import com.epam.course.battleship.ship.Ship;

class Board {

	private int width;
	private int height;
	private Field[][] board;
	
	Board(int boardWidth, int boardHeight) {
		this.width = boardWidth;
		this.height = boardHeight;
		board = new Field[boardHeight][boardWidth];
		
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				board[i][j] = Field.createEmptyField();
			}
		}
	}
	
	boolean handleShot(Point point) {
		Field target = board[point.getX()][point.getY()];
		target.hit();
		printHitMessage(target.isHit());
		board[point.getX()][point.getY()] = target.nextIfHit();
		return target.isHit();
	}

	private void printHitMessage(boolean hit) {
		if (hit) {
			System.out.println("It's a hit!");
		} else {
			System.out.println("Missed me!");
		}
	}

	void printBoard() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	boolean addShip(Ship ship, Point upperLeft) {
		if (canAddShip(ship, upperLeft)) {
			doAddShip(ship, upperLeft);
			return true;
		}
		
		return false;
	}
	
	private void doAddShip(Ship ship, Point upperLeft) {
		for (int i = 0; i < ship.getExtent().getY(); i++) {
			for (int j = 0; j < ship.getExtent().getX(); j++) {
				if (ship.isOccupied(new Point(j, i))) {
					board[getYOfShipField(upperLeft, i)][getXOfShipField(upperLeft, j)]
							= Field.createShipField(ship);
				}
			}
		}
	}

	private int getXOfShipField(Point upperLeft, int shipFieldX) {
		return upperLeft.getX() + shipFieldX;
	}

	private int getYOfShipField(Point upperLeft, int shipFieldY) {
		return upperLeft.getY() + shipFieldY;
	}
	
	private boolean canAddShip(Ship ship, Point upperLeft) {
		return ship.spaceIsLargeEnough(getAvailableWidth(upperLeft), getAvailableHeight(upperLeft))
				&& !shipsWouldOverlap(ship, upperLeft);
	}

	private int getAvailableHeight(Point upperLeft) {
		return height - upperLeft.getY();
	}

	private int getAvailableWidth(Point upperLeft) {
		return width - upperLeft.getX();
	}

	private boolean shipsWouldOverlap(Ship ship, Point upperLeft) {
		for (int i = 0; i < ship.getExtent().getY(); i++) {
			for (int j = 0; j < ship.getExtent().getX(); j++) {
				if (shipsWouldOverlap(ship, upperLeft, new Point(j, i))) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean shipsWouldOverlap(Ship ship, Point upperLeft, Point shipCoordinate) {
		Field fieldToCheck = board[getYOfShipField(upperLeft, shipCoordinate.getY())][getXOfShipField(upperLeft, shipCoordinate.getX())];
		return fieldToCheck.isOccupied() && ship.isOccupied(shipCoordinate);
	}
}
