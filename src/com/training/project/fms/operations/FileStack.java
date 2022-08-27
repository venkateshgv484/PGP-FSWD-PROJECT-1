package com.training.project.fms.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileStack {

	private List<String> fileList = new ArrayList<String>();

	public synchronized void clearList() {
		fileList.clear();
	}

	public synchronized void addFile(String filename) {
		if (!searchFile(filename)) {
			fileList.add(filename);
		}
	}

	public synchronized void removeFile(String filename) {
		if (searchFile(filename)) {
			fileList.remove(filename);
		}
	}

	public synchronized boolean searchFile(String filename) {
		if (fileList.contains(filename)) {
			return true;
		}

		return false;
	}

	public void display() {
		if (fileList.size() == 0) {
			System.out.println("No files available in ROOT directory.");
			return;
		}

		Collections.sort(fileList);

		System.out.println("List of files in the ROOT directory:");

		for (String filename : fileList) {
			System.out.println(filename);
		}
	}

}