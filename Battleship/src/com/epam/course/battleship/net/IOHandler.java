package com.epam.course.battleship.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IOHandler {

	private DataInputStream in;
	private DataOutputStream out;
	
	IOHandler(Connector connector) throws IOException {
		this.in = connector.getInputStream();
		this.out = connector.getOutputStream();
	}
	
	void send(Message message) throws IOException {
		out.writeUTF(message.toString());
	}
	
	Message receive() throws IOException {
		return new Message(in.readUTF());
	}
}
