// TS created by Deepa Patri
package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageWithDetails {
	private static final Logger logger = LogManager.getLogger(LoginPageWithDetails.class.getName());
	WebDriver webDriver;
	String email, password;

	public LoginPageWithDetails(WebDriver webDriver_Passed_From_CallingFn, String email_Passed, String pwd_Passed) {
		this.webDriver = webDriver_Passed_From_CallingFn;
		this.email = email_Passed;
		this.password = pwd_Passed;
	}

	public void Login_With_Details() throws Exception {
		logger.debug("Using test login   : " + email);
		logger.debug("Using test password: " + password);
		webDriver.findElement(By.linkText("Log In")).click();
		webDriver.findElement(By.name("USERID")).sendKeys(email);
		webDriver.findElement(By.name("PASS")).sendKeys(password);
		webDriver.findElement(By.id("ibLogin")).click();
	}
}
