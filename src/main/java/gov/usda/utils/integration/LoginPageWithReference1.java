package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageWithReference1 {
	private static final Logger logger = LogManager.getLogger(LoginPageWithReference1.class.getName());
	WebDriver webDriver;
	int get_Row_From_credentials_Recvd;

	public LoginPageWithReference1(WebDriver webDriver_Passed_From_CallingFn, int get_Row_From_credentials_Passed) {
		this.webDriver = webDriver_Passed_From_CallingFn;
		this.get_Row_From_credentials_Recvd = get_Row_From_credentials_Passed;
	}

	public void Login_With_Reference() throws Exception {
		logger.debug("Using test login   : "
				+ LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
		logger.debug("Using test password: "
				+ LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getPassword());
		webDriver.findElement(By.linkText("Log In")).click();
		webDriver.findElement(By.name("USERID")).sendKeys(LoginHelpers.getLoginData().getEmail());
		webDriver.findElement(By.name("PASS")).sendKeys(LoginHelpers.getLoginData().getPassword());
		webDriver.findElement(By.id("ibLogin")).click();
		String url = webDriver.getCurrentUrl();
		org.junit.Assert.assertTrue(url.contains("usda"));
	}
}
