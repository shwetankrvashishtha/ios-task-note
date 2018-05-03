package com.automation.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PageBase {

	/** The webdriver. */
	protected WebDriver webdriver = null;

	/** The mobiledriver. */
	protected AppiumDriver mobiledriver = null;

	/**
	 * Instantiates a new adds the feedback page.
	 * 
	 * @param webdriver
	 * @param mobiledriver
	 *            the webdriver the mobiledriver
	 */
	public PageBase(WebDriver webdriver) {
		this.webdriver = webdriver;
		PageFactory.initElements(webdriver, this);
	}

	public PageBase(AppiumDriver mobiledriver) {
		this.mobiledriver = mobiledriver;
		PageFactory.initElements(new AppiumFieldDecorator(mobiledriver), this);
	}
}
