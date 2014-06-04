package com.epam.course.battleship.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connector {

	private static final int PORT = 1146;
	
	public static Connector createServer() throws IOException {
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			return new Connector(serverSocket.accept());
		}
	}
	
	public static Connector createClient(String host) throws IOException {
		return new Connector(new Socket(host, PORT));
	}
	
	private Socket socket;
	
	private Connector(Socket socket) {
		this.socket = socket;
	}
	
	DataInputStream getInputStream() throws IOException {
		return new DataInputStream(socket.getInputStream());
	}
	
	DataOutputStream getOutputStream() throws IOException {
		return new DataOutputStream(socket.getOutputStream());
	}
}
