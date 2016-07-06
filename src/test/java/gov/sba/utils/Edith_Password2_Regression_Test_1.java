package gov.sba.utils;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Edith_Password2_Regression_Test_1 {
	private static final Logger logger = LogManager.getLogger(Edith_Password2_Regression_Test_1.class.getName());

	private static WebDriver webDriver;
	String myurl;
	String Email;
	String PassW;
	String Old_PassW;
	String New_PassW;
	String New_PassW2;

	@Before
	public void Edith_Password2_Regression_Test_Setup() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();

		Email = "staging@mailinator.com";
		Old_PassW = "The Quick Brown Fox Jumps Over The Lazy Dog";

		New_PassW = "The Quick Brown Fox Jumps Over The Lazy";
		New_PassW2 = "The Quick Brown Fox Jumps Over The Lazy";
	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void Edith_Password2_Regression_Test_Maintest() throws Exception {
		// Open Firefox,Chrome,and IE and navigate to the valid url.

		Thread.sleep(5000);
		webDriver.findElement(By.cssSelector("button.usa-button.hp-login-btn")).click();
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
		// Locate the password search box and enter a valid password.
		webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Old_PassW);
		// Locate the user Sign-in button and click on it.
		webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		Thread.sleep(4000);

		Thread.sleep(3000);
		if (webDriver.getPageSource().contains("Invalid email or password")) {

			webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
			// Locate the email search box and enter a valid email.
			webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
			// Locate the password search box and enter a valid password.
			webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(New_PassW);
			// Locate the user Sign-in button and click on it.
			webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();

			Thread.sleep(4000);

			Thread.sleep(3000);
			// Locate the My Profile button on the left navigation and click on
			// it.
			String actual_Text = webDriver.findElement(By.linkText("My Profile")).getText();
			String expected_Text = "My Profile";
			assertEquals(actual_Text, expected_Text);
			webDriver.findElement(By.linkText("My Profile")).click();
			Thread.sleep(5000);
			// Verify and click on the link Edit Passphrase.
			String actual_Text1 = webDriver.findElement(By.linkText("Edit Passphrase")).getText();
			String expected_Text1 = "Edit Passphrase";
			assertEquals(actual_Text1, expected_Text1);
			webDriver.findElement(By.linkText("Edit Passphrase")).click();
			Thread.sleep(3000);
			// Verify that user is navigated to the change password page.
			String actual_Text2 = webDriver.findElement(By.cssSelector("h2.usa-heading")).getText();
			String expected_Text2 = "Edit Passphrase";
			assertEquals(actual_Text2, expected_Text2);
			// Locate the current password search box and enter a valid current
			// password.
			webDriver.findElement(By.xpath(".//*[@id='user_current_password']")).sendKeys(New_PassW);
			Thread.sleep(3000);
			// locate that new password search box and enter the new password
			// you wish to update to.
			webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Old_PassW);
			Thread.sleep(4000);
			// Verify the strength of the new password and accept only better or
			// strong password.
			String actual_Text3 = webDriver.findElement(By.id("text_strength")).getText();
			String expected_Text3 = "Passphrase strength - Strong";
			assertEquals(actual_Text3, expected_Text3);
			// Locate the confirm new password search box and re-enter the new
			// password.
			webDriver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).sendKeys(Old_PassW);
			Thread.sleep(3000);
			// Locate the Update button and click on it.
			webDriver.findElement(By.xpath(".//*[@id='submit']")).click();
			Thread.sleep(3000);
			// Verify that User sees alert message 'Your account has been
			// updated successfully'.
			if (webDriver.getPageSource().contains("Your account has been updated successfully.")) {
				logger.info("User change password Successfully");
				webDriver.findElement(By.id("labelid")).click();
			} else {
				logger.info(" line 113 User Edith password was not successfull Successfully");

			}

			// Locate the logout button and click on it then log back in with
			// the new password.
			webDriver.findElement(By.linkText("Logout")).click();
			Thread.sleep(3000);

			// Locate and click on the log-in link.
			webDriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();

			// Locate the email search box and enter a valid email.
			webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
			// Locate the password search box and enter a valid password.
			webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Old_PassW);
			// Locate the user Sign-in button and click on it.
			webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
			Thread.sleep(3000);

			// Verify that user logged in successfully with the new updated
			// password.
			if (webDriver.getPageSource().contains("144754156")) {
				logger.info("Password Update is Successful");
				// mydriver.close();
			} else {
				logger.info("Password Update is Not Successful");
				webDriver.quit();

			}

			return;

		}

		else {
			System.out.println("First attempt to log-in with current password was successful, PASS");

		}
		Thread.sleep(4000);
		// mydriver.close();

		Thread.sleep(4000);

		// Locate the My Profile button on the left navigation and click on it.
		webDriver.findElement(By.linkText("My Profile")).click();
		Thread.sleep(5000);
		// Verify and click on the link Edit Passphrase.
		String actual_Text4 = webDriver.findElement(By.cssSelector("a.center_button.usa-button")).getText();
		String expected_Text4 = "Edit Passphrase";
		assertEquals(actual_Text4, expected_Text4);

		webDriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[2]/div/div/a")).click();

		Thread.sleep(3000);
		// Verify that user is navigated to the change password page.
		String actual_Text5 = webDriver.findElement(By.cssSelector("h2.usa-heading")).getText();
		String expected_Text5 = "Edit Passphrase";
		assertEquals(actual_Text5, expected_Text5);
		Thread.sleep(3000);
		// Locate the current password search box and enter a valid current
		// password.
		webDriver.findElement(By.xpath(".//*[@id='user_current_password']")).sendKeys(Old_PassW);
		Thread.sleep(3000);
		// locate that new password search box and enter the new password you
		// wish to update to.
		webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(New_PassW);
		Thread.sleep(4000);
		// Verify the strength of the new password and accept only better or
		// strong password.
		String actual_Text6 = webDriver.findElement(By.id("text_strength")).getText();
		String expected_Text6 = "Passphrase strength - Strong";
		assertEquals(actual_Text6, expected_Text6);
		// Locate the confirm new password search box and re-enter the new
		// password.
		webDriver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).sendKeys(New_PassW2);
		Thread.sleep(4000);
		// Locate the Update button and click on it.
		webDriver.findElement(By.xpath(".//*[@id='submit']")).click();
		Thread.sleep(4000);
		// Verify that User sees alert message 'Your account has been updated
		// successfully'.
		if (webDriver.getPageSource().contains("Your account has been updated successfully.")) {
			logger.info("User change password Successfully");
			webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
			// Click on the log-out button.
			webDriver.findElement(By.linkText("Logout")).click();

			Thread.sleep(3000);
			if (webDriver.getPageSource().contains("Signed out successfully")) {
				webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
			} else {
				logger.info("Successful sign out alert message not present");
			}
			// Locate and click on the log-in link.
			webDriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();

			// Locate the email search box and enter a valid email.
			webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
			// Locate the password search box and enter a valid password.
			webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(New_PassW);
			// Locate the user Sign-in button and click on it.
			webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();

		}
	}

	@After
	public void Edith_Password2_Regression_Test_Endtest() throws Exception {

		webDriver.quit();

	}

}
