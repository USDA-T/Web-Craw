package gov.sba.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogincorpPage {
	WebDriver webDriver;

	public LogincorpPage(WebDriver driver) {
		this.webDriver = driver;

	}

	public void Logincorp() throws Exception {
		Thread.sleep(4000);
		webDriver.findElement(By.cssSelector("button.usa-button.hp-login-btn")).click();
		String actual_Text4 = webDriver.findElement(By.cssSelector("span.blue-bar-text")).getText();
		String expected_Text4 = "Sign in to certify.SBA.gov";
		assertEquals(actual_Text4, expected_Text4);
		webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("akanamontana@gmail.com");
		// Step 2. Locate the password search box and enter a valid password.
		webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("password");
		// Step 3. Locate the Sign-in button and click on it to login.
		webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();

	}

	private void assertEquals(String actual_Text4, String expected_Text4) {
		// TODO Auto-generated method stub

	}

}
