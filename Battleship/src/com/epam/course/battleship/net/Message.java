package com.epam.course.battleship.net;

public class Message {

	private String name;
	private String data;
	
	public Message(String messageString) {
		int indexOfSpace = messageString.indexOf(' ');
		
		if (indexOfSpace < 1) {
			this.name = messageString;
		} else {
			this.name = messageString.substring(0, indexOfSpace);
			this.data = messageString.substring(indexOfSpace + 1);
		}
	}

	public Message(String name, String data) {
		this(name);
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public String getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", name, data);
	}
}
