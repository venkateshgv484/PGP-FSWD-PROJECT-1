package com.training.project.constants;

public enum SubMenu {

	ADD_FILE("21", "Add new file"), DELETE_FILE("22", "Delete a file"), SEARCH_FILE("23", "Search for a file"),
	RETURN_TO_MAINMENU("24", "Return to main menu");

	private String option;
	private String optionDesc;

	private SubMenu(String option, String optionDesc) {
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
