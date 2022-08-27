package com.training.project.constants;

import java.io.File;

public final class AppConstants {

	public static final String ROOT_FOLDER = System.getProperty("user.dir") + File.separator + "FSM_ROOT";
	public static final int ROOT_FOLDER_LEN = ROOT_FOLDER.length() + 1;
	public static final String INVALID_FILENAME_CHARS = "^.*[^a-zA-Z0-9._-].*$";
	public static final String DECORATOR_LINE = "**********************************************************************************************************************";

}
