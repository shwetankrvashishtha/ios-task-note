package com.automation.framework.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Authentication")

	public static Object[] Authentication() {

		return new Object[] { "abcd" };

	}
}
