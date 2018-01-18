package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class Test_Anet_Login extends TestCase {

	private static final Logger logger = LogManager.getLogger(Test_Anet_Login.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;
	String Negative_ID_Data;
	String Negative_Pass_Data;

	@Before
	public void setUp() throws Exception {
		Negative_ID_Data = "123456@gmail";
		Negative_Pass_Data = "747447";
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 11;

	}

	@Test
	public void testUsdaLogin() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		// Clear cash if chrome.
		ChromeClearCashPage chromeClearCash = new ChromeClearCashPage(webDriver);
		chromeClearCash.ChromeClearCash();
		// Login to dashboard.
		logger.info("Test for Usda login verification");
		// Verify the logout private page.
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong")));
			LoginAnetPageWithReference1 login_Data = new LoginAnetPageWithReference1(webDriver,
					get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			logger.info("Completed Login");
		} catch (Exception ex) {
			// Take screenshot
			ScreenShotPage1 ScreenShot = new ScreenShotPage1(webDriver);
			ScreenShot.ScreenShot();
			logger.info(ex);
			throw new RuntimeException(ex);
		}
		logger.info("Success");

	}

	@After
	public void tearDown() throws Exception {
		try {
			webDriver.close();
		} catch (Exception ex) {
			ScreenShotPage1 ScreenShot = new ScreenShotPage1(webDriver);
			ScreenShot.ScreenShot();
			logger.info(ex);
			throw new RuntimeException(ex);
		}
	}
}
