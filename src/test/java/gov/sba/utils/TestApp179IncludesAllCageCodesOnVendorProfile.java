package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

	public class TestApp179IncludesAllCageCodesOnVendorProfile extends TestCase {
		private static final Logger logger = LogManager.getLogger(TestApp179IncludesAllCageCodesOnVendorProfile.class.getName());
		private static WebDriver webDriver;
		int get_The_Row_From_Login_Data;

		@Before
		public void setUp() throws Exception {
			webDriver = TestHelpers.getDefaultWebDriver();
			webDriver.get(TestHelpers.getBaseUrl());
			webDriver.manage().window().maximize();
			get_The_Row_From_Login_Data = 25;

		}
		
		@Test
		public void testMainTest() throws Exception {
			String Actual_Text=null;
			String Expected_Text=null;
			logger.info("Vendor Profile info includes all CAGE codes");
			// Login to dashboard.
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			Thread.sleep(3000);
			Actual_Text = webDriver.findElement(By.cssSelector("h2 > a")).getText();
			Expected_Text = "My business";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.xpath("//p[3]/b")).getText();
			Expected_Text = "CAGE:";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.xpath("//p[3]/span")).getText();
			Expected_Text = "4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R";
			assertEquals(Actual_Text, Expected_Text);
			WebElement CAGECodes = webDriver.findElement(By.xpath("//p[3]/span"));
			HighLight.highLightElement(webDriver, CAGECodes);
		    //Logout and login as an analyst.
			webDriver.findElement(By.linkText("Logout")).click();
			get_The_Row_From_Login_Data = 0;
			LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data1.Login_With_Reference();
			//Search the business and verify the CAGE Codes.
			webDriver.findElement(By.id("query")).sendKeys("137151292");
			webDriver.findElement(By.xpath("//button")).click();
			webDriver.findElement(By.linkText("Entity 399 Legal Business Name")).click();
			Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
			Expected_Text = "CAGE:";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span")).getText();
			Expected_Text = "4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R, 4RU2R";
			assertEquals(Actual_Text, Expected_Text);
			WebElement CAGECodesAP = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
			HighLight.highLightElement(webDriver, CAGECodesAP);
			return;	
			
		}

		@After
		public void tearDown() throws Exception {
			webDriver.quit();
		}
	}
