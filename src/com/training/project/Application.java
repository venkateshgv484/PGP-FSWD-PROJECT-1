package com.training.project;

import java.io.IOException;

import com.training.project.fms.FileManagementSystem;
import com.training.project.utilities.WelcomeMessage;

public class Application {

	public static void main(String[] args) {
		try {
			WelcomeMessage.display();
			FileManagementSystem fms = new FileManagementSystem();
			fms.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
