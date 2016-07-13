package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.utils.helpers.LoginHelpers;

public class AnalystloginPage {
	private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());

	WebDriver webDriver;

	public AnalystloginPage(WebDriver webDriver) {
		this.webDriver = webDriver;

	}

	public void Analystlogin() throws Exception {
		logger.info("FYI: using test login   : " + LoginHelpers.getLoginData().getEmail());
		logger.info("FYI: using test password: " + LoginHelpers.getLoginData().getPassword());

		webDriver.findElement(By.xpath(".//*[@id=\"gov_login_box\"]/form[1]/button")).click();
		
		// NOTE: original code with hard-coded values
		// webDriver.findElement(By.name("user[email]")).sendKeys("analyst1@mailinator.com");
		// webDriver.findElement(By.name("user[password]")).sendKeys("password");
		
		// NOTE: new code with proper login logic captured
		webDriver.findElement(By.name("user[email]")).sendKeys(LoginHelpers.getLoginData().getEmail());
		webDriver.findElement(By.name("user[password]")).sendKeys(LoginHelpers.getLoginData().getPassword());
		
		webDriver.findElement(By.id("business_signin")).click();
		String url = webDriver.getCurrentUrl();
		assertTrue(url.contains("dashboard"));

	}

	private void assertTrue(boolean contains) {
		// TODO Auto-generated method stub
		
	}

	

}
