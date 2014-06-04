package com.epam.course.battleship.net;

import com.epam.course.battleship.Point;
import com.epam.course.battleship.game.FireResult;

public class MessageFactory {
	
	public static final String ERROR = "ERROR";
	public static final String FIRE = "FIRE";
	public static final String READY = "READY";
	public static final String SHIPS = "SHIPS";
	public static final String NAME = "NAME";
	public static final String INIT = "INIT";

	public static Message getInitMessage(Point size) {
		return new Message(INIT, String.format("%s", size.toString()));
	}
	
	public static Message getNameMessage() {
		return new Message(NAME, "Csaba");
	}

	public static Message getShipsMessage(String shipData) {
		return new Message(SHIPS, shipData);
	}

	public static Message getReadyMessage() {
		return new Message(READY);
	}
	
	public static Message getFireMessage(Point point) {
		return new Message(FIRE, point.toString());
	}
	
	public static Message getResultMessage(FireResult fireResult) {
		return new Message(fireResult.name());
	}
	
	public static Message getErrorMessage(String error) {
		return new Message(ERROR, error);
	}
}
