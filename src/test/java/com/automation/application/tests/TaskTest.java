package com.automation.application.tests;

import org.testng.annotations.Test;
import com.automation.application.pages.TaskPage;
import com.automation.framework.base.TestBase;
import com.automation.framework.base.TestBuilder;
import com.automation.framework.utilities.PropertyManager;

public class TaskTest extends TestBuilder {

	TestBase base = new TestBase();
	PropertyManager property = new PropertyManager();

	@Test(description = "Create a new Task")
	public void C2802901() {

		TaskPage taskPage = new TaskPage(base.getMobileDriver());
		String password = property.getResourceBundle.getProperty("CORRECT_PASSWORD");
		String title = property.getResourceBundle.getProperty("TITLE");
		String notes = property.getResourceBundle.getProperty("NOTES");

		taskPage.loginApp(password);
		taskPage.clickAddNewTask();
		base.verifyTrue(taskPage.getNewTaskPage().getAttribute("label").equalsIgnoreCase("New Task"),
				"User is redirected to New Task screen successfully as expected.",
				"User fails to redirect to New Task screen.");
		taskPage.enterTaskTitle(title);
		taskPage.clickStartDate();
		taskPage.saveStartDate();
		taskPage.clickCreatedTask();
		taskPage.clickDueDate();
		taskPage.saveDueDate();
		taskPage.clickCreatedTask();
		taskPage.clickReminder();
		taskPage.saveReminder();
		taskPage.clickCreatedTask();
		taskPage.clickNotes();
		taskPage.enterNotes(notes);
		taskPage.saveNotes();
		taskPage.clickDoneAddTask();
		taskPage.clickRightCheckIcon();
		base.verifyTrue(taskPage.getTaskName(title).equalsIgnoreCase(title), "Task created successfully as expected.",
				"Failed to create task. Please see console logs for more details.");
	}

	@Test(description = "Delete a task")
	public void C2802904() {

		TaskPage taskPage = new TaskPage(base.getMobileDriver());
		String password = property.getResourceBundle.getProperty("CORRECT_PASSWORD");

		taskPage.loginApp(password);
		taskPage.clickCreatedTask();
		taskPage.deleteTask();
		taskPage.clickConfirmDeleteBtn();
		base.verifyTrue(taskPage.isTaskDeleted(), "Task deleted successfully as expected.",
				"Failed to delete task. Please see console logs for more details.");

		// Delete by swiping.
	}
}
