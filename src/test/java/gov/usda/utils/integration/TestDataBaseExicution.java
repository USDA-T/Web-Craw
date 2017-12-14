package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import gov.sba.automation.TestHelpers;
import gov.usda.utils.integration.DataBaseExicutionPage;
import junit.framework.TestCase;

public class TestDataBaseExicution extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestDataBaseExicution.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		// webDriver.get(TestHelpers.getBaseUrl());
		// webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 8;
	}

	@Test
	public void testDataBaseExicution() throws Exception {
		logger.info("Running querries in the db");
		// Login to Dashboard.
		// LoginPageWithReference login_Data =
		// new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		// login_Data.Login_With_Reference();
		// Database test
		DataBaseExicutionPage dataBaseExicution = new DataBaseExicutionPage(webDriver);
		dataBaseExicution.DataBaseExicution();

		logger.info("Success");

	}

	@After
	public void tearDown() throws Exception {
		webDriver.close();
	}
}
