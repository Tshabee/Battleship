package com.epam.course.battleship.net.exception;

public class InvalidMessageFormatException extends NetworkException {

	private static final long serialVersionUID = 1L;

	public InvalidMessageFormatException(String messageName, String expected, String actual) {
		super(String.format("Format of message %s was invalid. Expected: %s, but actual was: %s", messageName, expected, actual));
	}
}
