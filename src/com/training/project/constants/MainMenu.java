package com.training.project.constants;

public enum MainMenu {

	LIST_FILES("1", "Display list of files"), FILE_OPERATIONS("2", "File operations"), EXIT("3", "Exit");

	private String option;
	private String optionDesc;

	private MainMenu(String option, String optionDesc) {
		this.option = option;
		this.optionDesc = optionDesc;
	}

	public String getOption() {
		return option;
	}

	public String getOptionDesc() {
		return optionDesc;
	}

}
