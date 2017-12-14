package gov.usda.utils.integration;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.TestHelpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
	
	
	
	public class HomePageValidation {

	private static final Logger logger = LogManager.getLogger(TestUsdaLogin.class.getName());
	//private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;
	static String driverPath = "C:\\UsdaEE\\Web-Craw\\src\\webDrivers\\IEDriverServer.exe";
	public WebDriver webDriver;


	@Before
	public void setUp() throws Exception {
		//webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 9;
		logger.info("launching IE browser");
		System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
		webDriver = new InternetExplorerDriver();

	}

	@Test
	public void homePageValidation() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		
			
			
		}
		
		@After
		public void tearDown() throws Exception {
			try {
				webDriver.close();
			} catch (Exception ex) {
				logger.info(ex);
				throw new RuntimeException(ex);
			}
		}
	}
