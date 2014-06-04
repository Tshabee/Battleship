package com.epam.course.battleship.game.exception;


public class AllShipsSunkException extends GameException {

	private static final long serialVersionUID = 1L;

	public AllShipsSunkException() {
		super("According to my calculations, I have sunk all of your ships, so I have won.");
	}
}
