package com.epam.course.battleship.ship;

import com.epam.course.battleship.Point;
import com.epam.course.battleship.field.Field;

public class Ship {

	public static final int SHIP_FIELD_SIZE = 4;
	
	private String name;
	
	private Field[][] shipFields;
	
	private int size;
	private int hitCount;
	
	Ship(String name) {
		this.name = name;
		shipFields = new Field[SHIP_FIELD_SIZE][SHIP_FIELD_SIZE];
		
		for (int i = 0; i < SHIP_FIELD_SIZE; i++) {
			for (int j = 0; j < SHIP_FIELD_SIZE; j++) {
				shipFields[i][j] = Field.createEmptyField();
			}
		}
	}
	
	public void hit() {
		hitCount++;
	}
	
	public boolean isSunk() {
		return hitCount >= size;
	}
	
	public boolean spaceIsLargeEnough(int width, int height) {
		return getExtent().getY() <= height && getExtent().getX() <= width;
	}
	
	public Point getExtent() {
		Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Point max = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		for (int i = 0; i < SHIP_FIELD_SIZE; i++) {
			for (int j = 0; j < SHIP_FIELD_SIZE; j++) {
				if (shipFields[i][j].isOccupied()) {
					min.setIfLess(j, i);
					max.setIfGreater(j, i);
				}
			}
		}
		
		return new Point(max.getX() - min.getX() + 1, max.getY() - min.getY() + 1);
	}

	public boolean isOccupied(Point coordinate) {
		return shipFields[coordinate.getY()][coordinate.getX()].isOccupied();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	int getSize() {
		return size;
	}
	
	void setFieldToOccupied(int x, int y) {
		shipFields[x][y] = Field.createShipField(this);
		size++;
	}
}
