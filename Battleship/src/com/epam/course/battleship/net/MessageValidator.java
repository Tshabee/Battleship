package com.epam.course.battleship.net;

import com.epam.course.battleship.net.exception.InvalidMessageFormatException;
import com.epam.course.battleship.net.exception.UnexpectedMessageException;

class MessageValidator {

	private static final String SIZE_FORMAT = "[1-9]+[0-9]*,[1-9]+[0-9]*";

	static void validateInit(Message initMessage) {
		validateMessageName(MessageFactory.INIT, initMessage.getName());
		validateInitData(initMessage);
	}

	private static void validateInitData(Message initMessage) {
		if (initMessage.getData() == null || !initMessage.getData().matches(SIZE_FORMAT)) {
			throw new InvalidMessageFormatException(MessageFactory.INIT, SIZE_FORMAT, initMessage.getData());
		}
	}

	private static void validateMessageName(String expected, String actual) {
		if (!expected.equals(actual)) {
			throw new UnexpectedMessageException(expected, actual);
		}
	}
	
	static void validateName(Message nameMessage) {
		validateMessageName(MessageFactory.NAME, nameMessage.getName());
	}
	
	static void validateShips(Message message) {
		validateMessageName(MessageFactory.SHIPS, message.getName());
	}
	
	static void validateReady(Message message) {
		validateMessageName(MessageFactory.READY, message.getName());
	}
	
	private MessageValidator() { }
}
