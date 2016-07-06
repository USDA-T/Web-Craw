package gov.sba.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class US1168_IAM_Configure_SBA_Ops_Support_Role_and_Permissions_MVP {

	private static WebDriver webDriver;
	String myurl;
	String Email;
	String Passphrase;

	@Before
	public void US1168_IAM_Configure_SBA_Ops_Support_Role_and_Permissions_MVP_setup() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		Email = "opssupport1@mailinator.com";
		Passphrase = "password";

	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void US1168_IAM_Configure_SBA_Ops_Support_Role_and_Permissions_MVP_Maintest() throws Exception {
		// open firefox, chrome or IE and navigate to certify.sba login page.

		// Login to SBA Ops support dashboard.
		Thread.sleep(5000);
		webDriver.findElement(By.cssSelector("button.usa-button.hp-login-btn")).click();
		Thread.sleep(4000);
		String actual_Text4 = webDriver.findElement(By.cssSelector("span.blue-bar-text")).getText();
		String expected_Text4 = "Sign in to certify.SBA.gov";
		assertEquals(actual_Text4, expected_Text4);

		webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("opssupport1@mailinator.com");
		// Step 2. Locate the password search box and enter a valid password.
		webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("password");
		// Step 3. Locate the Sign-in button and click on it to login.
		webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		Thread.sleep(5000);
		verifyTrue("Save my work", webDriver.findElement(By.name("commit")));
		// Logout.
		webDriver.findElement(By.linkText("Logout")).click();

	}

	private void verifyTrue(String string, WebElement findElement) {
		// TODO Auto-generated method stub

	}

	@After
	public void US1168_IAM_Configure_SBA_Ops_Support_Role_and_Permissions_MVP_Teardown() throws Exception {
		webDriver.quit();
	}
}