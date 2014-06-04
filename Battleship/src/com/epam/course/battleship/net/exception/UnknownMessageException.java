package com.epam.course.battleship.net.exception;

import com.epam.course.battleship.net.Message;

public class UnknownMessageException extends NetworkException {

	private static final long serialVersionUID = 1L;

	public UnknownMessageException(Message message) {
		super(String.format("Unknown message received. Name: %s, Data: %s", message.getName(), message.getData()));
	}
}
