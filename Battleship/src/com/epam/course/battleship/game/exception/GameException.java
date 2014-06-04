package com.epam.course.battleship.game.exception;

public abstract class GameException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameException(String message) {
		super(message);
	}
}
