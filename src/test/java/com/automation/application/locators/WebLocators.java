package com.automation.application.locators;

public interface WebLocators {

	public interface TaskLocators {
		String PASSWORD = "BBDValidatePasswordFieldID";
		String LAUNCHER = "launchpad";
		String ADD_NEW_TASK = "New Task";
		String TITLE = "//XCUIElementTypeTextField[@value='Title']";
		String RIGHT_CHECK_ICON = "Save";
		String DELETE_TASK = "Delete Task";
		String CONFIRM_DELETE = "(//XCUIElementTypeButton[@name='Delete Task'])[2]";
		String NEW_TASK_PAGE = "//XCUIElementTypeOther[@name='New Task']";
		String START_DATE = "Start Date";
		String SAVE_START_DATE = "Save";
		String DUE_DATE = "Due Date";
		String SAVE_DUE_DATE = "Back";
		String REMINDER = "Reminder";
		String SAVE_REMINDER = "Back";
		String REPEAT = "Repeat";
		String RECCURENCE = "//XCUIElementTypeStaticText[@name='Recurrence']";
		String ADD_NOTES = "Add Notes";
		String ENTER_NOTES = "//XCUIElementTypeTextField/preceding::XCUIElementTypeTextField";
		String SAVE_NOTES = "(//XCUIElementTypeButton[@name='Discard'])[1]";
		String DONE_ADD_TASK = "Back";
	}
}
