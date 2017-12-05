package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CologinPage {
	private static final Logger logger = LogManager.getLogger(CologinPage.class.getName());
	WebDriver webDriver;

	public CologinPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void Cologin() throws Exception {
		logger.debug("Using test login   : " + LoginHelpers.getLoginData().getEmail());
		logger.debug("Using test password: " + LoginHelpers.getLoginData().getPassword());
		webDriver.findElement(By.linkText("Log In")).click();
		webDriver.findElement(By.name("USERID")).sendKeys(LoginHelpers.getLoginData().getEmail());
		webDriver.findElement(By.name("PASS")).sendKeys(LoginHelpers.getLoginData().getPassword());
		webDriver.findElement(By.id("ibLogin")).click();
		String url = webDriver.getCurrentUrl();
		org.junit.Assert.assertTrue(url.contains("usda"));
	}
}
