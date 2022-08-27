package com.training.project.fms;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.training.project.constants.AppConstants;
import com.training.project.constants.MainMenu;
import com.training.project.constants.SubMenu;
import com.training.project.fms.operations.FileAddition;
import com.training.project.fms.operations.FileDeletion;
import com.training.project.fms.operations.FileListLoad;
import com.training.project.fms.operations.FileSearch;
import com.training.project.fms.operations.FileStack;

public class FileManagementSystem {

	private FileStack fileStack;
	private Scanner scanner;

	public FileManagementSystem() {
		fileStack = new FileStack();
		scanner = new Scanner(System.in);
	}

	public void start() {
		initialize();
		executeMainMenu();
		scanner.close();
	}

	public void initialize() {
		File rootDirectory = new File(AppConstants.ROOT_FOLDER);

		if (!rootDirectory.exists()) {
			if (!rootDirectory.mkdirs()) {
				System.out.println("Error while creating the root directory. Please contact administrator.");
			}
		}

		Thread fileListLoadThread = new Thread(new FileListLoad(fileStack));
		fileListLoadThread.start();
		try {
			fileListLoadThread.join();
		} catch (InterruptedException e) {
		}
	}

	private void executeMainMenu() {
		while (true) {
			String userChoice = getMainMenuUserChoice();

			if (MainMenu.LIST_FILES.getOption().equalsIgnoreCase(userChoice)) {
				fileStack.display();
			} else if (MainMenu.FILE_OPERATIONS.getOption().equalsIgnoreCase(userChoice)) {
				executeFileOperationMenu();
			} else if (MainMenu.EXIT.getOption().equalsIgnoreCase(userChoice)) {
				return;
			} else {
				System.out.println("Wrong choice. Please try again.");
			}
		}
	}

	private void executeFileOperationMenu() {
		while (true) {
			String userChoice = getSubMenuUserChoice();

			if (SubMenu.ADD_FILE.getOption().equalsIgnoreCase(userChoice)) {
				createFile();
			} else if (SubMenu.DELETE_FILE.getOption().equalsIgnoreCase(userChoice)) {
				deleteFile();
			} else if (SubMenu.SEARCH_FILE.getOption().equalsIgnoreCase(userChoice)) {
				searchFile();
			} else if (SubMenu.RETURN_TO_MAINMENU.getOption().equalsIgnoreCase(userChoice)) {
				return;
			} else {
				System.out.println("Wrong choice. Please try again.");
			}
		}
	}

	private String getMainMenuUserChoice() {
		System.out.println();
		System.out.println(AppConstants.DECORATOR_LINE);
		System.out.println("MAIN MENU - OPTIONS");
		System.out.println("1. Display File List");
		System.out.println("2. Go to File Operations Menu");
		System.out.println("3. Exit");
		System.out.println(AppConstants.DECORATOR_LINE);
		System.out.println();
		System.out.println("Enter your choice :: ");

		return getTextInput();
	}

	private String getSubMenuUserChoice() {
		System.out.println();
		System.out.println(AppConstants.DECORATOR_LINE);
		System.out.println("FILE OPERATIONS MENU - OPTIONS");
		System.out.println("21. Add file");
		System.out.println("22. Delete file");
		System.out.println("23. Search file");
		System.out.println("24. Return to a MAIN MENU");
		System.out.println(AppConstants.DECORATOR_LINE);
		System.out.println();
		System.out.println("Enter your choice :: ");

		return getTextInput();
	}

	private String getTextInput() {
		String userChoice;

		while (true) {
			userChoice = scanner.nextLine();
			if (!userChoice.isEmpty()) {
				return userChoice;
			}
		}
	}

	private boolean validateFileName(String filename) {
		String splitRegex = Pattern.quote(System.getProperty("file.separator"));
		String tempFilename = filename.replaceAll(splitRegex, "");
		return !tempFilename.matches(AppConstants.INVALID_FILENAME_CHARS);
	}

	private void createFile() {
		String filename;
		System.out.println("You are adding a new file in root directory. Enter a file name :: ");
		while (true) {
			filename = getTextInput();

			if (!validateFileName(filename)) {
				System.out.println("Invalid file name. Please enter a valid name.");
				continue;
			}

			Thread fileAdditionThread = new Thread(new FileAddition(fileStack, filename));
			fileAdditionThread.start();
			try {
				fileAdditionThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			break;
		}
	}

	private void deleteFile() {
		String filename;
		System.out.println("You are deleting a file from root directory. Enter a file name :: ");
		while (true) {
			filename = getTextInput();

			if (!validateFileName(filename)) {
				System.out.println("Invalid file name. Please enter a valid name.");
				continue;
			}

			Thread fileDeletionThread = new Thread(new FileDeletion(fileStack, filename));
			fileDeletionThread.start();
			try {
				fileDeletionThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			break;
		}
	}

	private void searchFile() {
		String filename;
		System.out.println("You are searching a file in root directory. Enter a file name :: ");
		while (true) {
			filename = getTextInput();

			if (!validateFileName(filename)) {
				System.out.println("Invalid file name. Please enter a valid name.");
				continue;
			}

			Thread fileSearchThread = new Thread(new FileSearch(filename));
			fileSearchThread.start();
			try {
				fileSearchThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return;
		}
	}

}
