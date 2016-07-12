package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.utils.helpers.LoginHelpers;
import junit.framework.TestCase;

public class TestSearchPage extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());
	private static WebDriver webDriver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		
		// Get the login based on the environment under test (e.g. 'development', 'qa', 'staging')
		logger.info("FYI: your environment under test:" + System.getProperty(Constants.TEST_ENV));
		//loginInfo = LoginHelpers.getLoginCredentials(System.getProperty(Constants.TEST_ENV));
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}

	@Test
	public void testMainLogic() throws Exception {
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
}
