package com.training.project.fms.operations;

import java.io.File;

import com.training.project.constants.AppConstants;

public class FileListLoad implements Runnable {

	private FileStack fileStack;
	private int rootFolderLength;
	private boolean clearList;

	public FileListLoad(FileStack fileStack) {
		this.fileStack = fileStack;
		this.rootFolderLength = AppConstants.ROOT_FOLDER.length() + 1;
		this.clearList = false;
	}

	public FileListLoad(FileStack fileStack, boolean clearList) {
		this.fileStack = fileStack;
		this.rootFolderLength = AppConstants.ROOT_FOLDER.length() + 1;
		this.clearList = clearList;
	}

	@Override
	public void run() {
		if (clearList) {
			fileStack.clearList();
		}

		File directoryPath = new File(AppConstants.ROOT_FOLDER);
		prepareList(directoryPath);
	}

	private void prepareList(File directoryPath) {
		File filesList[] = directoryPath.listFiles();
		for (File file : filesList) {
			
			if (file.isDirectory()) {
				prepareList(file);
				continue;
			}
			
			fileStack.addFile(file.getAbsolutePath().substring(rootFolderLength));
		}
	}

}
