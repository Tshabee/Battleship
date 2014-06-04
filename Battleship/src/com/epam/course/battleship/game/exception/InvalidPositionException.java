package com.epam.course.battleship.game.exception;


public class InvalidPositionException extends GameException {

	private static final long serialVersionUID = 1L;

	public InvalidPositionException(int boardWidth, int boardHeight, int x, int y) {
		super(String.format("Valid positions range from (0,0) to (%d,%d). Shot position was (%d,%d)", boardWidth, boardHeight, y, x));
	}
}
