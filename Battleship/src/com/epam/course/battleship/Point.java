package com.epam.course.battleship;

import java.util.Random;

public final class Point {
	
	private static Random random = new Random();
	
	public static Point randomPoint(int maxX, int maxY) {
		return new Point(random.nextInt(maxX), random.nextInt(maxY));
	}
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setIfGreater(int x, int y) {
		setYIfGreater(y);
		setXIfGreater(x);
	}

	private void setXIfGreater(int x) {
		if (x > this.x) {
			this.x = x;
		}
	}

	private void setYIfGreater(int y) {
		if (y > this.y) {
			this.y = y;
		}
	}
	
	public void setIfLess(int x, int y) {
		setYIfLess(y);
		setXIfLess(x);
	}

	private void setXIfLess(int x) {
		if (x < this.x) {
			this.x = x;
		}
	}

	private void setYIfLess(int y) {
		if (y < this.y) {
			this.y = y;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}
		
		Point other = (Point) obj;
		
		return x == other.x
				&& y == other.y;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%d", x, y);
	}
}