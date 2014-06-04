package com.epam.course.battleship.net.exception;

public class UnexpectedMessageException extends NetworkException {

	private static final long serialVersionUID = 1L;

	public UnexpectedMessageException(String expected, String actual) {
		super(String.format("Unexpected message received. Expected: %s, actual was: %s", expected, actual));
	}
}
