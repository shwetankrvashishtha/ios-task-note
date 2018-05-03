package com.automation.application.pages;

import org.openqa.selenium.By;

import com.automation.application.locators.WebLocators.TaskLocators;
import com.automation.framework.base.PageBase;
import com.automation.framework.base.TestBase;
import com.automation.framework.utilities.PropertyManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * @author shwetankvashishtha
 *
 */
public class TaskPage extends PageBase implements TaskLocators {

	public TaskPage(AppiumDriver mobiledriver) {
		super(mobiledriver);
		// TODO Auto-generated constructor stub
	}

	TestBase base = new TestBase();
	PropertyManager property = new PropertyManager();

	@iOSFindBy(accessibility = PASSWORD)
	private MobileElement password;

	public MobileElement getPassword() {
		return password;
	}

	@iOSFindBy(accessibility = LAUNCHER)
	private MobileElement launcher;

	public MobileElement getLauncher() {
		return launcher;
	}

	@iOSFindBy(accessibility = ADD_NEW_TASK)
	private MobileElement addNewTask;

	public MobileElement getAddNewTask() {
		return addNewTask;
	}

	@iOSFindBy(xpath = TITLE)
	private MobileElement title;

	public MobileElement getTitle() {
		return title;
	}

	@iOSFindBy(accessibility = RIGHT_CHECK_ICON)
	private MobileElement rightCheckIcon;

	public MobileElement getRightCheckIcon() {
		return rightCheckIcon;
	}

	@iOSFindBy(accessibility = DELETE_TASK)
	private MobileElement deleteTask;

	public MobileElement getDeleteTask() {
		return deleteTask;
	}

	@iOSFindBy(xpath = CONFIRM_DELETE)
	private MobileElement confirmDelete;

	public MobileElement getConfirmDelete() {
		return confirmDelete;
	}

	@iOSFindBy(accessibility = START_DATE)
	private MobileElement startDate;

	public MobileElement getStartDate() {
		return startDate;
	}

	@iOSFindBy(accessibility = SAVE_START_DATE)
	private MobileElement saveStartDate;

	public MobileElement getSaveStartDate() {
		return saveStartDate;
	}

	@iOSFindBy(accessibility = DUE_DATE)
	private MobileElement dueDate;

	public MobileElement getDueDate() {
		return dueDate;
	}

	@iOSFindBy(accessibility = SAVE_DUE_DATE)
	private MobileElement saveDueDate;

	public MobileElement getSaveDueDate() {
		return saveDueDate;
	}

	@iOSFindBy(xpath = NEW_TASK_PAGE)
	private MobileElement newTaskPage;

	public MobileElement getNewTaskPage() {
		return newTaskPage;
	}

	@iOSFindBy(accessibility = REMINDER)
	private MobileElement reminder;

	public MobileElement getReminder() {
		return reminder;
	}

	@iOSFindBy(accessibility = SAVE_REMINDER)
	private MobileElement saveReminder;

	public MobileElement getSaveReminder() {
		return saveReminder;
	}

	@iOSFindBy(accessibility = REPEAT)
	private MobileElement repeat;

	public MobileElement getRepeat() {
		return repeat;
	}

	@iOSFindBy(accessibility = RECCURENCE)
	private MobileElement recurrence;

	public MobileElement getRecurrence() {
		return recurrence;
	}

	@iOSFindBy(accessibility = ADD_NOTES)
	private MobileElement addNotes;

	public MobileElement getAddNotes() {
		return addNotes;
	}

	@iOSFindBy(xpath = ENTER_NOTES)
	private MobileElement enterNotes;

	public MobileElement getEnterNotes() {
		return enterNotes;
	}

	@iOSFindBy(xpath = SAVE_NOTES)
	private MobileElement saveNotes;

	public MobileElement getSaveNotes() {
		return saveNotes;
	}

	@iOSFindBy(accessibility = DONE_ADD_TASK)
	private MobileElement doneAddTask;

	public MobileElement getDoneAddTask() {
		return doneAddTask;
	}

	@Step("Login to application")
	public void loginApp(String password) {
		if (getPassword().isDisplayed()) {
			getPassword().clear();
			getPassword().sendKeys(password + "\n");
		} else {
			System.out.println("User is already Logged-In");
		}
	}

	@Step("Click on launcher")
	public void clickLauncher() {
		base.waitForMobileElementVisible(10, getLauncher());
		getLauncher().click();
	}

	@Step("Click on Add new task")
	public void clickAddNewTask() {
		base.waitForMobileElementVisible(10, getAddNewTask());
		getAddNewTask().click();
	}

	@Step("Enter task title")
	public void enterTaskTitle(String title) {
		base.waitForMobileElementVisible(10, getTitle());
		getTitle().sendKeys(title);
	}

	@Step("Click right check icon to create task")
	public void clickRightCheckIcon() {
		base.waitForMobileElementVisible(10, getRightCheckIcon());
		getRightCheckIcon().click();
		base.pause(2000);
	}

	@Step("Click delete task icon")
	public void deleteTask() {
		base.waitForMobileElementVisible(10, getDeleteTask());
		getDeleteTask().click();
	}

	@Step("Click confirm delete button")
	public void clickConfirmDeleteBtn() {
		base.waitForMobileElementVisible(10, getConfirmDelete());
		getConfirmDelete().click();
		base.pause(1000);
	}

	@Step("Click created task")
	public void clickCreatedTask() {
		String title = property.getResourceBundle.getProperty("TITLE");
		base.pause(2000);
		base.getMobileDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + title + "']")).click();
	}

	@Step("Verify task created successfully")
	public String getTaskName(String title) {
		return base.getMobileDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + title + "']"))
				.getAttribute("name");
	}

	@Step("Verify task deleted successfully")
	public boolean isTaskDeleted() {
		if (base.getMobileDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + title + "']"))
				.isDisplayed()) {
			return false;
		}
		return true;
	}

	@Step("Click start date button")
	public void clickStartDate() {
		base.waitForMobileElementVisible(10, getStartDate());
		getStartDate().click();
	}

	@Step("Click save start date button")
	public void saveStartDate() {
		base.waitForMobileElementVisible(10, getSaveStartDate());
		getSaveStartDate().click();
	}

	@Step("Click due date button")
	public void clickDueDate() {
		base.waitForMobileElementVisible(10, getDueDate());
		getDueDate().click();
	}

	@Step("Click save due date button")
	public void saveDueDate() {
		base.waitForMobileElementVisible(10, getSaveDueDate());
		getSaveDueDate().click();
	}

	@Step("Click reminder button")
	public void clickReminder() {
		base.waitForMobileElementVisible(10, getReminder());
		getReminder().click();
	}

	@Step("Click save reminder button")
	public void saveReminder() {
		base.waitForMobileElementVisible(10, getSaveReminder());
		getSaveReminder().click();
	}

	@Step("Click repeat button")
	public void clickRepeat() {
		base.waitForMobileElementVisible(10, getRepeat());
		getRepeat().click();
	}

	@Step("Click recurrence button")
	public void clickRecurrence() {
		base.waitForMobileElementVisible(10, getRecurrence());
		getRecurrence().click();
	}

	@Step("Click add notes in task")
	public void clickNotes() {
		base.waitForMobileElementVisible(10, getAddNotes());
		getAddNotes().click();
	}

	@Step("Enter add notes comments in task")
	public void enterNotes(String notes) {
		base.waitForMobileElementVisible(10, getEnterNotes());
		getEnterNotes().clear();
		getEnterNotes().sendKeys(notes);
	}

	@Step("Click save notes in comments")
	public void saveNotes() {
		base.waitForMobileElementVisible(10, getSaveNotes());
		getSaveNotes().click();
	}

	@Step("Click done button")
	public void clickDoneAddTask() {
		base.waitForMobileElementVisible(10, getDoneAddTask());
		getDoneAddTask().click();
	}
}