package com.automation.framework.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.automation.framework.utilities.PropertyManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author shwetankvashishtha
 *
 */
public class TestBase extends WebDriverTestBase implements ITestListener, ISuiteListener, IInvokedMethodListener {

	PropertyManager property = new PropertyManager();

	@Override
	public WebDriver getWebDriver() {
		return webdriver;
	}

	@Override
	public AppiumDriver getMobileDriver() {
		return mobiledriver;
	}

	@Override
	public void launchAndroidApp(String platformName, String platformVersion, String deviceName, String appiumServer,
			String appPackage, String appActivity) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("autoAcceptAlerts", "true");
		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("autoGrantPermissions", "true");
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);
		mobiledriver = new AndroidDriver(new URL(appiumServer), capabilities);
	}

	@Override
	public void launchIOSApp(String platformName, String platformVersion, String deviceName, String automationName,
			String udid, boolean touchIdEnroll, String xcodeOrgId, String xcodeSigningId, String bundleid, String app,
			int wdaport, String appiumServer) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("udid", udid);
		capabilities.setCapability("automationName", automationName);
		capabilities.setCapability("bundleid", bundleid);
		capabilities.setCapability("app", app);
		capabilities.setCapability("xcodeOrgId", xcodeOrgId);
		capabilities.setCapability("xcodeSigningId", xcodeSigningId);
		capabilities.setCapability("wdaLocalPort", wdaport);
		mobiledriver = new IOSDriver(new URL(appiumServer), capabilities);
	}

	@Override
	public void setupBrowser(String browser, String URL, String OS) {

		if (OS.equalsIgnoreCase("windows") && browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					property.getResourceBundle.getProperty("WIN_GECKO_DRIVER_PATH"));
			webdriver = new FirefoxDriver();
			openURL(URL);
			webdriver.manage().window().maximize();
		} else if (OS.equalsIgnoreCase("windows") && browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					property.getResourceBundle.getProperty("WIN_CHROME_DRIVER_PATH"));
			webdriver = new ChromeDriver();
			openURL(URL);
			webdriver.manage().window().maximize();
		} else if (OS.equalsIgnoreCase("windows") && browser.equalsIgnoreCase("phantomjs")) {
			System.setProperty("phantomjs.binary.path",
					property.getResourceBundle.getProperty("WIN_PHANTOMJS_DRIVER_PATH"));
			webdriver = new PhantomJSDriver();
			openURL(URL);
			webdriver.manage().window().maximize();
		} else if (OS.equalsIgnoreCase("mac") && browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					property.getResourceBundle.getProperty("MAC_GECKO_DRIVER_PATH"));
			webdriver = new FirefoxDriver();
			openURL(URL);
			webdriver.manage().window().maximize();
		} else if (OS.equalsIgnoreCase("mac") && browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					property.getResourceBundle.getProperty("MAC_CHROME_DRIVER_PATH"));
			webdriver = new ChromeDriver();
			openURL(URL);
			webdriver.manage().window().maximize();
		} else if (OS.equalsIgnoreCase("mac") && browser.equalsIgnoreCase("phantomjs")) {
			System.setProperty("phantomjs.binary.path",
					property.getResourceBundle.getProperty("MAC_PHANTOMJS_DRIVER_PATH"));
			webdriver = new PhantomJSDriver();
			openURL(URL);
			webdriver.manage().window().maximize();
		}
		Reporter.log(browser + " launched successfully");
		Reporter.log(URL + " passed to browser");
	}

	@Override
	public void openURL(String AUT_URL) {
		webdriver.get(AUT_URL);
		Reporter.log(AUT_URL + " has been opened in browser successfully");
	}

	@Override
	public void teardownWeb() {
		refreshPage();
		shutdownBrowser();
		generateTestReport();
	}

	@Override
	public void killApp() {
		mobiledriver.closeApp();
	}

	@Override
	public void refreshPage() {
		webdriver.navigate().refresh();
	}

	@Override
	public void shutdownBrowser() {
		clearCache();
		closeBrowser();
	}

	@Override
	public void teardownMobile() {
		// generateTestReport();
		mobiledriver.quit();
	}

	@Override
	public void clearCache() {
		webdriver.manage().deleteAllCookies();
	}

	@Override
	public void closeBrowser() {
		if (webdriver != null) {
			webdriver.quit();
		}
	}

	@Override
	public void generateTestReport() {
		setupBrowser(property.getResourceBundle.getProperty("BROWSER1"),
				property.getResourceBundle.getProperty("REPORT_LOCATION"),
				property.getResourceBundle.getProperty("OS"));
	}

	@Override
	public void openCurrentBrowserInstance() {
		webdriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
	}

	@Override
	public void openNewBrowserTab() {
		webdriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	@Override
	public WebElement waitUntilClickable(WebElement el, int timeout) {
		int waitSeconds = timeout;
		final String message = "Element never became clickable after '%d' seconds" + waitSeconds;
		WebDriverWait wait = new WebDriverWait(mobiledriver, waitSeconds);
		wait.withMessage(message).ignoring(StaleElementReferenceException.class);
		wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				if (isClickable(el)) {
					return el;
				}
				return null;
			}
		});
		return el;
	}

	@Override
	public boolean isClickable(WebElement el) {
		if (el == null) {
			return false;
		}
		try {
			if (!el.isDisplayed()) {
				return false;
			}
			if (!el.isEnabled()) {
				return false;
			}
			if (el.getSize().getHeight() <= 0 || el.getSize().getWidth() <= 0) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public void pause(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fluentWait(long timeout) {
		new FluentWait(webdriver).withTimeout(timeout, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

	}

	@Override
	public void waitForPageLoad(long timeout) {
		WebDriverWait wait = new WebDriverWait(webdriver, timeout);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) webdriver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	@Override
	public void waitForWebElementsToLoad(long timeout) {
		webdriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void waitForMobileElementsToLoad(long timeout) {
		mobiledriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void waitForWebElementVisible(long timeout, WebElement element) {
		new WebDriverWait(webdriver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public void waitForMobileElementVisible(long timeout, WebElement element) {
		new WebDriverWait(mobiledriver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public void waitForWebElementDisappear(long timeout, WebElement element) {
		new WebDriverWait(webdriver, timeout).until(ExpectedConditions.invisibilityOf(element));
	}

	@Override
	public void waitForMobileElementDisappear(long timeout, WebElement element) {
		new WebDriverWait(mobiledriver, timeout).until(ExpectedConditions.invisibilityOf(element));
	}

	@Override
	public void waitForWebElementToBeClickable(long timeout, WebElement element) {
		new WebDriverWait(webdriver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void waitForPageExpire(long timeout) {
		webdriver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void setAsyncScriptTimeout(long timeout) {
		webdriver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
	}

	@Override
	public void waitForTextToBePresentInElement(long timeout, WebElement element, String text) {
		new WebDriverWait(webdriver, timeout).until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	@Override
	public void waitForAlertPresent(long timeout, WebElement element) {

	}

	@Override
	public void waitForPageTitle(long timeout, String pageTitle) {
		new WebDriverWait(webdriver, timeout).until(ExpectedConditions.titleIs(pageTitle));
	}

	@Override
	public void frameToBeAvailableAndSwitch(long timeout, String frameID) {
		new WebDriverWait(webdriver, timeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameID)));
	}

	@Override
	public void captureScreenshotOnFailureWeb(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshotWeb(result.getName());
		}
	}

	@Override
	public void captureScreenshotWeb(String screenshotName) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Calendar cal = Calendar.getInstance();
		TakesScreenshot takesScreenshot = (TakesScreenshot) webdriver;
		File screenshotSrc = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String spacesFileName = System.getProperty("user.dir")
				+ property.getResourceBundle.getProperty("SCREENSHOT_LOCATION") + cal.getTime() + screenshotName
				+ ".png";
		String fileName = spacesFileName.replaceAll(" ", "");
		File screenShotName = new File(fileName);
		try {
			FileUtils.copyFile(screenshotSrc, screenShotName);
			Reporter.log("<br><b>" + screenshotName + "></b><br>");
			Reporter.log("<br> <img src='" + screenShotName + "' height='650' width='700'/><br>");
			Reporter.log("<a href=" + screenShotName + ">VIEW SCREENSHOT FOR FAILED CASE</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void captureScreenshotOnFailureMobile(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshotMobile(result.getName());
		}
	}

	@Override
	public void captureScreenshotMobile(String screenshotName) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Calendar cal = Calendar.getInstance();
		TakesScreenshot takesScreenshot = (TakesScreenshot) mobiledriver;
		File screenshotSrc = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String spacesFileName = System.getProperty("user.dir")
				+ property.getResourceBundle.getProperty("SCREENSHOT_LOCATION") + cal.getTime() + screenshotName
				+ ".png";
		String fileName = spacesFileName.replaceAll(" ", "");
		File screenShotName = new File(fileName);
		try {
			FileUtils.copyFile(screenshotSrc, screenShotName);
			Reporter.log("<br><b>" + screenshotName + "></b><br>");
			Reporter.log("<br> <img src='" + screenShotName + "' height='650' width='700'/><br>");
			Reporter.log("<a href=" + screenShotName + ">VIEW SCREENSHOT FOR FAILED CASE</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void swipeBottomTop(int startXper, int startYper) {
		Dimension size = mobiledriver.manage().window().getSize();
		int startX = (int) (size.getWidth() * startXper / 100);
		int startY = (int) (size.getHeight() * startYper / 100);
		int endX = 0;
		int endY = (int) (startY * -1 * 0.75);
		TouchAction action = new TouchAction(mobiledriver);
		action.press(startX, startY).moveTo(endX, endY).release().perform();
	}

	@Override
	public void swipeTopBottom(int startXper, int startYper) {
		Dimension size = mobiledriver.manage().window().getSize();
		int startX = (int) (size.getWidth() * startXper / 100);
		int startY = (int) (size.getHeight() * startYper / 100);
		int endX = 0;
		int endY = (int) (startY * 2);
		TouchAction action = new TouchAction(mobiledriver);
		action.press(startX, startY).moveTo(endX, endY).release().perform();
	}

	@Override
	public boolean verifyTrue(Boolean Condition, String SuccessMessage, String FailureMessage) {
		if (Condition) {
			Reporter.log(SuccessMessage, true);
			return true;
		} else {
			Reporter.log(FailureMessage, false);
			return false;
		}
	}

	@Override
	public boolean verifyFalse(Boolean Condition, String SuccessMessage, String FailureMessage) {
		if (!Condition) {
			Reporter.log(SuccessMessage, true);
			return true;
		} else {
			Reporter.log(FailureMessage, false);
			return false;
		}
	}

	@Override
	public void assertTrue(boolean condition, String successMessage, String failureMessage) {
		if (condition) {
			Reporter.log(successMessage, true);
		} else {
			Reporter.log(failureMessage, false);
			throw new AssertionError("Assertion Error");
		}
	}

	@Override
	public void assertFalse(boolean condition, String successMessage, String failureMessage) {
		if (condition) {
			Reporter.log(successMessage, true);
		} else {
			Reporter.log(failureMessage, false);
			throw new AssertionError("Assertion Error");
		}
	}

	@Override
	public void setupLogger() {
		Calendar cal = Calendar.getInstance();
		try {
			logger = Logger.getLogger("");
			FileHandler fh;

			fh = new FileHandler(
					property.getResourceBundle.getProperty("LOG_FILE_PATH") + "log" + cal.getTime() + ".txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ISuite arg0) {
		Reporter.log("About to begin executing Suite " + arg0.getName(), true);
	}

	@Override
	public void onFinish(ISuite arg0) {
		Reporter.log("About to end executing Suite " + arg0.getName(), true);
	}

	@Override
	public void onStart(ITestContext arg0) {
		Reporter.log("About to begin executing Test " + arg0.getName(), true);
	}

	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log("Completed executing test " + arg0.getName(), true);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("The execution of the main test starts now");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}

	@Override
	public String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}

	@Override
	public void printTestResults(ITestResult result) {
		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLandscapeMode() {
		mobiledriver.rotate(ScreenOrientation.LANDSCAPE);
	}

	@Override
	public void setPortraitMode() {
		mobiledriver.rotate(ScreenOrientation.PORTRAIT);
	}

	@Override
	public void setScreenOrientation(String orientation) {
		ScreenOrientation orientationcurr = mobiledriver.getOrientation();
		orientation = orientation.toUpperCase().trim();
		if (!orientationcurr.equals(orientation)) {
			if (orientation.equals("PORTRAIT")) {
				mobiledriver.rotate(ScreenOrientation.PORTRAIT);
			} else {
				mobiledriver.rotate(ScreenOrientation.LANDSCAPE);
			}
		}
	}

	@Override
	public String getScreenOrientation() {
		ScreenOrientation objorientation = mobiledriver.getOrientation();
		String strorientation = objorientation.value().toUpperCase().trim();
		return strorientation;
	}

	@Override
	public void closeSoftKeyboard() {
		try {
			mobiledriver.hideKeyboard();
		} catch (Exception e) {
			throw e;
		}
	}
}
