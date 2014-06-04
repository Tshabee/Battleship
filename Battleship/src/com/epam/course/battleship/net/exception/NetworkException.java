package com.epam.course.battleship.net.exception;

public abstract class NetworkException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NetworkException(String message) {
		super(message);
	}
}
