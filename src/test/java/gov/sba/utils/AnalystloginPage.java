package gov.sba.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnalystloginPage {
	WebDriver webDriver;

	public AnalystloginPage(WebDriver webDriver) {
		this.webDriver = webDriver;

	}

	public void Analystlogin() throws Exception {
		webDriver.findElement(By.cssSelector("button.usa-button.hp-login-btn")).click();
		Thread.sleep(5000);
		String actual_Text4 = webDriver.findElement(By.cssSelector("span.blue-bar-text")).getText();
		String expected_Text4 = "Sign in to certify.SBA.gov";
		assertEquals(actual_Text4, expected_Text4);
		webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("analyst1@mailinator.com");
		// Step 2. Locate the password search box and enter a valid password.
		webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("password");
		// Step 3. Locate the Sign-in button and click on it to login.
		webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();

	}

	private void assertEquals(String actual_Text4, String expected_Text4) {
		// TODO Auto-generated method stub

	}

}
