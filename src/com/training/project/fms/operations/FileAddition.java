package com.training.project.fms.operations;

import java.io.File;
import java.io.IOException;

import com.training.project.constants.AppConstants;

public class FileAddition implements Runnable {

	private FileStack fileStack;
	private String filename;

	public FileAddition(FileStack fileStack, String filename) {
		this.fileStack = fileStack;
		this.filename = filename;
	}

	@Override
	public void run() {
		if (fileStack.searchFile(filename)) {
			System.out.printf("File has already exist with this file name [%s].", filename);
			System.out.println();
			return;
		}

		if (createFile()) {
			fileStack.addFile(filename);
			System.out.printf("File [%s] created successfully in the root directory.", filename);
			System.out.println();
			return;
		}
	}

	private boolean createFile() {
		File file = new File(AppConstants.ROOT_FOLDER + File.separator + filename);

		if (file.exists()) {
			System.out.printf("File has already exist with this file name [%s].", filename);
			return false;
		}

		File directory = new File(file.getParent());

		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				System.out.printf("Error while creating the file [%s].", filename);
				return false;
			}
		}

		if (!file.getName().contains(".")) {
			if (!file.mkdirs()) {
				System.out.printf("Error while creating the file [%s].", filename);
				return false;
			}

			return true;
		}

		try {
			return file.createNewFile();
		} catch (IOException e) {
			System.out.printf("Error while creating the file [%s] with error is [%s].", filename, e.getMessage());
		}

		return false;
	}

}