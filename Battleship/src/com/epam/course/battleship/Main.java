package com.epam.course.battleship;

import java.io.IOException;

import com.epam.course.battleship.game.FireResult;
import com.epam.course.battleship.game.Game;
import com.epam.course.battleship.game.GameStrategy;
import com.epam.course.battleship.game.WinDetector;
import com.epam.course.battleship.game.exception.AllShipsSunkException;
import com.epam.course.battleship.game.exception.InvalidPositionException;
import com.epam.course.battleship.net.Connector;
import com.epam.course.battleship.net.Message;
import com.epam.course.battleship.net.MessageFactory;
import com.epam.course.battleship.net.MessageHandler;
import com.epam.course.battleship.net.exception.NetworkException;
import com.epam.course.battleship.net.exception.UnknownMessageException;
import com.epam.course.battleship.util.FileContentReader;

public class Main {

	private Game game;
	private GameStrategy gameStrategy;
	private WinDetector winDetector;
	private Point boardSize;
	
	private Connector connector;
	private MessageHandler messageHandler;
	
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		
		try {
			if (args.length > 0) {
				main.runClient(args[0]);
			} else {
				main.runServer();
			}
		} catch (NetworkException e) {
			main.messageHandler.sendError(e);
		}
	}
	
	private void runServer() throws IOException {
		connector = Connector.createServer();
		messageHandler = new MessageHandler(connector);
		
		boardSize = new Point(Game.BOARD_SIZE, Game.BOARD_SIZE);
		
		messageHandler.sendInit(boardSize);
		messageHandler.sendName();
		
		String rawShipData = FileContentReader.readFileContent("ships.txt");
		messageHandler.sendShips(rawShipData);
		winDetector = new WinDetector(rawShipData);
		
		game = new Game();
		gameStrategy = new GameStrategy(boardSize);
		
		messageHandler.sendReady();
		
		processGame();
	}
	
	private void runClient(String host) throws IOException {
		connector = Connector.createClient(host);
		messageHandler = new MessageHandler(connector);
		
		boardSize = createPointFromMessage(messageHandler.receiveInit());
		System.out.println(messageHandler.receiveName());
		
		String rawShipData = messageHandler.receiveShips();
		winDetector = new WinDetector(rawShipData);
		game = new Game(boardSize.getX(), boardSize.getY(), rawShipData);
		gameStrategy = new GameStrategy(boardSize);
		
		messageHandler.sendReady();
		
		messageHandler.receiveReady();
		messageHandler.sendFire(gameStrategy.getNextTarget());
		
		processGame();
	}
	
	private Point createAndCheckPointFromMessage(String coordinateString) {
		Point point = createPointFromMessage(coordinateString);
		
		if (point.getX() < 0 || point.getX() >= boardSize.getX() || point.getY() < 0 || point.getY() >= boardSize.getY()) {
			throw new InvalidPositionException(boardSize.getX(), boardSize.getY(), point.getX(), point.getY());
		}
		
		return point;
	}
	
	private Point createPointFromMessage(String coordinateString) {
		String[] coordinates = coordinateString.split(",");
		int x = Integer.valueOf(coordinates[1]);
		int y = Integer.valueOf(coordinates[0]);
		return new Point(x, y);
	}

	private void processGame() throws IOException {
		while (!game.hasWon()) {
			Message message = messageHandler.receive();
			
			try {
				switch (message.getName()) {
					case "HIT":
					case "SUNK":
						winDetector.hit();
						gameStrategy.lastTargetHit();
						System.out.println(message.getName());
						break;
					case "MISS":
						gameStrategy.lastTargetMissed();
						System.out.println(message.getName());
						break;
					case MessageFactory.READY:
						System.out.println(message.getName());
						break;
					case MessageFactory.FIRE:
						messageHandler.sendFireResult(handleNextGameStep(message.getData()));
						messageHandler.sendFire(gameStrategy.getNextTarget());
						break;
					case "WIN":
						System.out.println("Congratulations, you have won!");
						return;
					case MessageFactory.ERROR:
						System.err.println(message.getData());
						break;
					default:
						throw new UnknownMessageException(message);
				}
			} catch (InvalidPositionException | NetworkException e) {
				messageHandler.sendError(e);
			} catch (AllShipsSunkException e) {
				messageHandler.sendError(e);
				System.out.println("Congratulations, you have won!");
				return;
			}
		}
	}
	
	private FireResult handleNextGameStep(String line) {
		FireResult result = game.shoot(createAndCheckPointFromMessage(line));
		game.printBoard();
		return result;
	}
}
