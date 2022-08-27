package com.training.project.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WelcomeMessage {

	public static void display() throws IOException {
		File file = new File("welcome.txt"); // creates a new file instance
		FileReader fr = new FileReader(file); // reads the file
		BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		fr.close(); // closes the stream and release the resources
	}

}
