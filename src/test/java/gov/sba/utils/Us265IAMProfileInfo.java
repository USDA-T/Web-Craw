package gov.sba.utils;
import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Us265IAMProfileInfo {
	private static final Logger logger = LogManager
			.getLogger(Us265IAMProfileInfo.class.getName());
	private static WebDriver webDriver;
	String Username;
	String Password;
	@Before
	public void seUup() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		Username = "Deric.nguni@sba.gov";
		Password = "Montana$74045883";
	}
	@Test
	public void mainTest() throws Exception {
		// Navigate the MAX.gov landing page.
		Thread.sleep(5000);
		logger.info("US265 test.max.gov");
		assertElementPresent(webDriver.findElement(By.id("homepage-login-button")));
		webDriver.findElement(By.id("homepage-login-button")).click();
		// Locate the User ID search box and enter a valid SBA.gov email.
		webDriver.findElement(By.id("input-userID")).sendKeys(Username);
		// Locate the password search box and enter a valid password.
		webDriver.findElement(By.id("input-password")).sendKeys(Password);
		// Verify and click on the Login With User ID button.
		String actual_Text = webDriver.findElement(By.id("button-userID")).getText();
		String expected_Text = "Login With User ID";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.id("button-userID")).click();
		Thread.sleep(4000);
		// Locate and verify the Welcome link on the top right corner of the
		// page and select my profile.
		String actual_Text1 = webDriver.findElement(By.linkText("Welcome Montana")).getText();
		String expected_Text1 = "Welcome Montana";
		assertEquals(actual_Text1, expected_Text1);
		webDriver.findElement(By.linkText("Welcome Montana")).click();
		webDriver.findElement(By.id("user-community-profile-link")).click();
		// Verify that all personal informations on the my profile page.
		// First and Last name, Employment type.
		String actual_Text2 = webDriver.findElement(By.cssSelector("h3.max-profile-user-name")).getText();
		String expected_Text2 = "Montana Nguni SBA,CTR";
		assertEquals(actual_Text2, expected_Text2);
		// Agency or Organization.
		String actual_Text3 = webDriver.findElement(By.id("max-profile-agency")).getText();
		String expected_Text3 = "Small Business Administration";
		assertEquals(actual_Text3, expected_Text3);
		// Phone Number.
		String actual_Text4 = webDriver.findElement(By.id("max-profile-phone")).getText();
		String expected_Text4 = "5713797090";
		assertEquals(actual_Text4, expected_Text4);
		// Email.
		String actual_Text5 = webDriver.findElement(By.id("max-profile-email")).getText();
		String expected_Text5 = "deric.nguni@sba.gov";
		assertEquals(actual_Text5, expected_Text5);
		// Street.
		String actual_Text6 = webDriver.findElement(By.id("max-profile-street-one")).getText();
		String expected_Text6 = "409 3rd St SW";
		assertEquals(actual_Text6, expected_Text6);
		// City.
		String actual_Text7 = webDriver.findElement(By.id("max-profile-city")).getText();
		String expected_Text7 = "Washington";
		assertEquals(actual_Text7, expected_Text7);
		// State.
		String actual_Text8 = webDriver.findElement(By.id("max-profile-state")).getText();
		String expected_Text8 = "DC";
		assertEquals(actual_Text8, expected_Text8);
		// Zipcode.
		String actual_Text9 = webDriver.findElement(By.id("max-profile-zip")).getText();
		String expected_Text9 = "20024";
		assertEquals(actual_Text9, expected_Text9);
		// Country.
		String actual_Text10 = webDriver.findElement(By.id("max-profile-country")).getText();
		String expected_Text10 = "US";
		assertEquals(actual_Text10, expected_Text10);
		// Logout.
    	webDriver.findElement(By.id("nav-header-signout")).click();
	}
	private void assertElementPresent(WebElement findElement) {
		// TODO Auto-generated method stub
	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}