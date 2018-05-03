package com.automation.framework.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.automation.framework.utilities.PropertyManager;

public class TestBuilder {

	TestBase base = new TestBase();
	PropertyManager property = new PropertyManager();

	@BeforeMethod
	public void startTest() throws Exception {
		base.launchIOSApp(property.getResourceBundle.getProperty("IOS_DEV1_PLATFORM_NAME"),
				property.getResourceBundle.getProperty("IOS_DEV1_PLATFORM_VERSION"),
				property.getResourceBundle.getProperty("IOS_DEV1_DEVICE_NAME"),
				property.getResourceBundle.getProperty("IOS_DEV1_AUTOMATION_NAME"),
				property.getResourceBundle.getProperty("IOS_DEV1_UDID"), false,
				property.getResourceBundle.getProperty("IOS_DEV1_APPIUM_SERVER"),
				property.getResourceBundle.getProperty("IOS_DEV1_XCODE_ORG_ID"),
				property.getResourceBundle.getProperty("IOS_DEV1_BUNDLEID"),
				property.getResourceBundle.getProperty("IOS_DEV1_TASK_APP"), 8100,
				property.getResourceBundle.getProperty("IOS_DEV1_APPIUM_SERVER"));
		base.setupLogger();
	}

	@AfterMethod
	public void closeTest(ITestResult result) {
		base.captureScreenshotOnFailureMobile(result);
		base.killApp();
	}

	@AfterClass
	public void shutdown() {
		base.teardownMobile();
	}
}
