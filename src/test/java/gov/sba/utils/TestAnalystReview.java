package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import gov.sba.utils.AnalystReviewPage;

public class TestAnalystReview {
	// Set The variabl.es/Define
     WebDriver webDriver;
	private static final Logger TestAnalystReview = LogManager.getLogger(TestAnalystReview.class.getName());
	int get_The_Row_From_Login_Data;
	
	@Before
	public void setUp() throws Exception {

		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 11;
 	}
	@Test
	public void testMainTest() throws Exception {
		try{
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		//Click on Case Link on main navigator
        WebElement Cases_Link = webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
        Cases_Link.click();
	     //Pass the user search radio button,the serach term to the userprofile search function
		AnalystReviewPage TestReviewProcess = new AnalystReviewPage( );
		// Give Duns Number
		TestReviewProcess.TestReviewDriver(webDriver, "178961981");
		//pass Government/vendor profile criteria
		TestReviewProcess.testMainTest();
		 //webDriver.navigate().back();
	}	
		catch (Exception e) {
			TestAnalystReview.info(e.toString());
			//logger_US1081.info("test failed as return_vendor link dididnot work");
		}
	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
 }
}

		
