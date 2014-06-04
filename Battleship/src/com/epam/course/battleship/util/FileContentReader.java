package com.epam.course.battleship.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.epam.course.battleship.exception.FileReadingException;

public class FileContentReader {
	public static String readFileContent(String fileName) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			return tryToReadFileContent(bufferedReader);
		} catch (IOException e) {
			throw new FileReadingException(e);
		}
	}

	private static String tryToReadFileContent(BufferedReader bufferedReader) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		String line = bufferedReader.readLine();
		while (line != null) {
			stringBuilder.append(line);
			stringBuilder.append("\n");
			line = bufferedReader.readLine();
		}
		return stringBuilder.substring(0, stringBuilder.length() - 1);
	}
}
