package gov.sba.utils;


import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class Analyst_search_View_vendors_Record_Regression_1 {
	private static final Logger logger = LogManager.getLogger(Analyst_search_View_vendors_Record_Regression_1.class.getName());
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
	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
	@Test
	public void testMainLogic() throws Exception {
		Thread.sleep(4000);
		// Step 01. Login to dashboard, Locate the email search box and enter a
		// valid email.
		AnalystloginPage analystlogin = new AnalystloginPage(webDriver);
		analystlogin.Analystlogin();
		Thread.sleep(4000);
		// Locate the search for a vendor search box and emter a valid duns
		// number.
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query"))
				.sendKeys("275276652");
		// Locate the search button and click on it.
		webDriver.findElement(By.cssSelector("button.search-styli-desktop")).click();
		// Print out the business information for each search.
		logger.info(webDriver.findElement(By.id("view_business_profile")).getText());
		Thread.sleep(4000);
		// Locate the view profile button and click on it.
		webDriver.findElement(By.id("view_business_profile")).click();
		Thread.sleep(3000);
		assertTrue(webDriver.getPageSource().contains("Active"));
		assertTrue(webDriver.getPageSource().contains("View summary"));
		webDriver.findElement(By.linkText("View summary")).click();
		Thread.sleep(3000);
		// Verify that the view button works.
		assertTrue(webDriver.getPageSource().contains("Summary"));
		logger.info("The view certification button by analyst is active and working correctly, Pass");
		webDriver.navigate().back();
		Thread.sleep(2000);
		webDriver.navigate().back();
		// Clear the search for a vendor search box and enter a new valid duns
		// number
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query"))
				.sendKeys("Entity 37 Legal");
		webDriver.findElement(By.cssSelector("button.search-styli-desktop")).click();
		Thread.sleep(4000);
		// Locate the view profile button and click on it.
		webDriver.findElement(By.id("view_business_profile")).click();
		Thread.sleep(3000);
		assertTrue(webDriver.getPageSource().contains("Active"));
		webDriver.findElement(By.linkText("View summary")).click();
		Thread.sleep(3000);
		// Verify that the view button works.
		assertTrue(webDriver.getPageSource().contains("Summary"));
		logger.info("The view certification button by analyst is active and working correctly, Pass");
		webDriver.navigate().back();
		webDriver.navigate().back();
	}
}
