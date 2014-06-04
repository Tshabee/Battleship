package com.epam.course.battleship.exception;

import java.io.IOException;

public class FileReadingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileReadingException(IOException cause) {
		super(cause);
	}
}
