package com.automation.framework.base;

import java.net.MalformedURLException;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.IInvokedMethod;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import io.appium.java_client.AppiumDriver;

/**
 * @author shwetankvashishtha
 *
 */
public abstract class WebDriverTestBase {

	protected static WebDriver webdriver;

	protected static AppiumDriver mobiledriver;

	public Logger logger;

	public abstract WebDriver getWebDriver();

	public abstract AppiumDriver getMobileDriver();

	public abstract void launchAndroidApp(String platformName, String platformVersion, String deviceName,
			String appiumServer, String appPackage, String appActivity) throws MalformedURLException;

	public abstract void launchIOSApp(String platformName, String platformVersion, String deviceName,
			String automationName, String udid, boolean touchIdEnroll, String xcodeOrgId, String xcodeSigningId,
			String bundleid, String app, int wdaport, String appiumServer) throws MalformedURLException;

	public abstract void setupBrowser(String browser, String URL, String OS);

	public abstract void clearCache();

	public abstract void shutdownBrowser();

	public abstract void closeBrowser();

	public abstract void openURL(String AUT_URL);

	public abstract void assertFalse(boolean condition, String successMessage, String failureMessage);

	public abstract void assertTrue(boolean condition, String successMessage, String failureMessage);

	public abstract boolean verifyFalse(Boolean Condition, String SuccessMessage, String FailureMessage);

	public abstract boolean verifyTrue(Boolean Condition, String SuccessMessage, String FailureMessage);

	public abstract void generateTestReport();

	public abstract void refreshPage();

	public abstract void killApp();

	public abstract WebElement waitUntilClickable(WebElement el, int timeout);

	public abstract boolean isClickable(WebElement el);

	public abstract void waitForWebElementToBeClickable(long timeout, WebElement element);

	public abstract void waitForWebElementDisappear(long timeout, WebElement element);

	public abstract void waitForMobileElementDisappear(long timeout, WebElement element);

	public abstract void waitForWebElementVisible(long timeout, WebElement element);

	public abstract void waitForWebElementsToLoad(long timeout);

	public abstract void waitForMobileElementsToLoad(long timeout);

	public abstract void waitForPageLoad(long timeout);

	public abstract void fluentWait(long timeout);

	public abstract void pause(long timeout);

	public abstract void waitForPageExpire(long timeout);

	public abstract void waitForTextToBePresentInElement(long timeout, WebElement element, String text);

	public abstract void waitForAlertPresent(long timeout, WebElement element);

	public abstract void setAsyncScriptTimeout(long timeout);

	public abstract void waitForPageTitle(long timeout, String pageTitle);

	public abstract void waitForMobileElementVisible(long timeout, WebElement element);

	public abstract void frameToBeAvailableAndSwitch(long timeout, String frameID);

	public abstract void openCurrentBrowserInstance();

	public abstract void openNewBrowserTab();

	public abstract void captureScreenshotOnFailureWeb(ITestResult result);

	public abstract void captureScreenshotOnFailureMobile(ITestResult result);

	public abstract void captureScreenshotWeb(String screenshotName);

	public abstract void captureScreenshotMobile(String screenshotName);

	public abstract void swipeBottomTop(int startXper, int startYper);

	public abstract void swipeTopBottom(int startXper, int startYper);

	public abstract void teardownWeb();

	public abstract void teardownMobile();

	public abstract void setupLogger();

	public abstract void onStart(ISuite arg0);

	public abstract void onFinish(ISuite arg0);

	public abstract void onStart(ITestContext arg0);

	public abstract void onFinish(ITestContext arg0);

	public abstract String returnMethodName(ITestNGMethod method);

	public abstract void afterInvocation(IInvokedMethod arg0, ITestResult arg1);

	public abstract void beforeInvocation(IInvokedMethod arg0, ITestResult arg1);

	public abstract void printTestResults(ITestResult result);

	public abstract void onTestSkipped(ITestResult arg0);

	public abstract void onTestStart(ITestResult arg0);

	public abstract void onTestFailure(ITestResult arg0);

	public abstract void onTestSuccess(ITestResult arg0);

	public abstract void onTestFailedButWithinSuccessPercentage(ITestResult arg0);

	public abstract void setScreenOrientation(String orientation);

	public abstract String getScreenOrientation();

	public abstract void setPortraitMode();

	public abstract void setLandscapeMode();

	public abstract void closeSoftKeyboard();
}