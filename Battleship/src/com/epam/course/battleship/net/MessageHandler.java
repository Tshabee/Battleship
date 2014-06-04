package com.epam.course.battleship.net;

import java.io.IOException;

import com.epam.course.battleship.Point;
import com.epam.course.battleship.game.FireResult;

public class MessageHandler {

	private IOHandler handler;
	
	public MessageHandler(Connector connector) throws IOException {
		this.handler = new IOHandler(connector);
	}
	
	public void sendInit(Point boardSize) throws IOException {
		handler.send(MessageFactory.getInitMessage(boardSize));
	}
	
	public void sendName() throws IOException {
		handler.send(MessageFactory.getNameMessage());
	}
	
	public void sendShips(String ships) throws IOException {
		handler.send(MessageFactory.getShipsMessage(ships));
	}
	
	public void sendReady() throws IOException {
		handler.send(MessageFactory.getReadyMessage());
	}
	
	public void sendFire(Point target) throws IOException {
		handler.send(MessageFactory.getFireMessage(target));
	}
	
	public void sendFireResult(FireResult fireResult) throws IOException {
		handler.send(MessageFactory.getResultMessage(fireResult));
	}
	
	public void sendError(Throwable e) throws IOException {
		handler.send(MessageFactory.getErrorMessage(e.getMessage()));
	}
	
	public String receiveInit() throws IOException {
		Message initMessage = handler.receive();
		MessageValidator.validateInit(initMessage);
		return initMessage.getData();
	}
	
	public String receiveName() throws IOException {
		Message nameMessage = handler.receive();
		MessageValidator.validateName(nameMessage);
		return nameMessage.getData();
	}
	
	public String receiveShips() throws IOException {
		Message shipsMessage = handler.receive();
		MessageValidator.validateShips(shipsMessage);
		return shipsMessage.getData();
	}
	
	public void receiveReady() throws IOException {
		Message message = handler.receive();
		MessageValidator.validateReady(message);
	}
	
	public Message receive() throws IOException {
		return handler.receive();
	}
}
