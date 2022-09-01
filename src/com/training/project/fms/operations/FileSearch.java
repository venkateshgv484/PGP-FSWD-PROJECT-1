package com.training.project.fms.operations;

import java.io.File;

import com.training.project.constants.AppConstants;

public class FileSearch implements Runnable {

	private String filename;
	private int count = 0;

	public FileSearch(String filename) {
		this.filename = filename;
	}

	@Override
	public void run() {
		searchFile(new File(AppConstants.ROOT_FOLDER));

		if (count == 0) {
			System.out.println("No files found with this file name [%s].");
		}
		
		System.out.println();
	}

	private void searchFile(File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				if (subFile.isDirectory()) {
					searchFile(subFile);
				} else {
					if (filename.startsWith("*") && filename.endsWith("*")) {
						String tempFilename = filename.replaceAll("\\*", "");
						if (subFile.getName().contains(tempFilename)) {
							count += 1;
							if(count == 1) {
								System.out.printf("List of files matched with provided file name [%s]:", filename);
								System.out.println();
							}
							System.out.println(subFile.getAbsolutePath().substring(AppConstants.ROOT_FOLDER_LEN));
						}
					} else if (filename.startsWith("*")) {
						String tempFilename = filename.replaceAll("\\*", "");
						if (subFile.getName().endsWith(tempFilename)) {
							count += 1;
							if(count == 1) {
								System.out.printf("List of files matched with provided file name [%s]:", filename);
								System.out.println();
							}
							System.out.println(subFile.getAbsolutePath().substring(AppConstants.ROOT_FOLDER_LEN));
						}
					} else if (filename.endsWith("*")) {
						String tempFilename = filename.replaceAll("\\*", "");
						if (subFile.getName().startsWith(tempFilename)) {
							count += 1;
							if(count == 1) {
								System.out.printf("List of files matched with provided file name [%s]:", filename);
								System.out.println();
							}
							System.out.println(subFile.getAbsolutePath().substring(AppConstants.ROOT_FOLDER_LEN));
						}
					} else {
						if (subFile.getName().equals(filename)) {
							count += 1;
							if(count == 1) {
								System.out.printf("List of files matched with provided file name [%s]:", filename);
								System.out.println();
							}
							System.out.println(subFile.getAbsolutePath().substring(AppConstants.ROOT_FOLDER_LEN));
						}
					}

				}
			}
		}
	}

}