package gov.usda.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
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
import gov.usda.utils.integration.AdvancedSearchValdationPage;
import gov.usda.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

public class TestUsdaLogin extends TestCase {

	private static final Logger logger = LogManager.getLogger(TestUsdaLogin.class.getName());
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
		get_The_Row_From_Login_Data = 9;

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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lnkLogIn")));
			// Locate and click on the Login link.
			click_Element(webDriver, "Click_Login_Link");
			assertEquals("User ID & Password", find_Element(webDriver, "Verify_Login_Page").getText());
			// Attempt to login with wrong data to validate all the login search
			// boxes.
			click_Element(webDriver, "Click_Login_Button");
			// Verify validation for for both User id and password.
			assertEquals("User ID is a required field", find_Element(webDriver, "UserID_Validation_Message").getText());
			assertEquals("Password is a required field",
					find_Element(webDriver, "Password_Validation_Message").getText());
			// Enter negative data to validate.
			setText_Element(webDriver, "User_ID_Locator", Negative_ID_Data);
			setText_Element(webDriver, "User_Password_Locator", Negative_Pass_Data);
			click_Element(webDriver, "Click_Login_Button");
			// Verify validation for for both User id and password.
			assertEquals("Your User ID or Password was entered incorrectly.",
					find_Element(webDriver, "UserID_Password_Validation_Error").getText());
			// Navigate back.
			webDriver.navigate().back();
			webDriver.navigate().back();
			webDriver.navigate().refresh();
			// Login to dashboard.
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			// Verify the login private page.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lnkMyProfile")));
			assertEquals("Himaashri Gottumukkala", find_Element(webDriver, "Verify_Private_Login_Page").getText());
			logger.info("Completed negative login test, starting advanced search");
		} catch (Exception ex) {
			// Take screenshot
			ScreenShotPage1 ScreenShot = new ScreenShotPage1(webDriver);
			ScreenShot.ScreenShot();
			logger.info(ex);
			throw new RuntimeException(ex);
		}
		// complete advanced search.
		AdvancedSearchValdationPage advancedSearchValdation = new AdvancedSearchValdationPage(webDriver);
		advancedSearchValdation.AdvancedSearchValdation();
		// Complete Adding a new opreation.
		AddNewOperationPage addNewOperation = new AddNewOperationPage(webDriver);
		addNewOperation.AddNewOperation();
		// Logout and login to verify all negative login flow.
		click_Element(webDriver, "Usda_Logout");
		logger.info("Success");

	}

	@After
	public void tearDown() throws Exception {
		try {
			webDriver.close();
		} catch (Exception ex) {
			logger.info(ex);
			throw new RuntimeException(ex);
		}
	}
}
