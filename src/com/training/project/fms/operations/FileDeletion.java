package com.training.project.fms.operations;

import java.io.File;

import com.training.project.constants.AppConstants;

public class FileDeletion implements Runnable {

	private FileStack fileStack;
	private String filename;

	public FileDeletion(FileStack fileStack, String filename) {
		this.fileStack = fileStack;
		this.filename = filename;
	}

	@Override
	public void run() {

		if (!fileStack.searchFile(filename)) {
			System.out.printf("No file found with this filename [%s].", filename);
			return;
		}

		File file = new File(AppConstants.ROOT_FOLDER + File.separator + filename);

		if (!file.exists()) {
			System.out.printf("No file found with this file name [%s].", filename);
			return;
		}

		deleteFile(file);
		fileStack.removeFile(filename);
		System.out.printf("File [%s] deleted successfully from the root directory.", filename);
	}

	private void deleteFile(File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				if (subFile.isDirectory()) {
					deleteFile(subFile);
				} else {
					subFile.delete();
				}
			}
		}

		file.delete();
	}

}
