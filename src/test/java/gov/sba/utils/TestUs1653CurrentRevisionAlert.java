package gov.sba.utils;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

	public class TestUs1653CurrentRevisionAlert extends TestCase {
		private static WebDriver webDriver;
		private static final Logger logger = LogManager
				.getLogger(TestUs1653CurrentRevisionAlert.class.getName());
		int get_The_Row_From_Login_Data;

		@Before
		public void setUp() throws Exception {

			webDriver = TestHelpers.getDefaultWebDriver();
			webDriver.get(TestHelpers.getBaseUrl());
			webDriver.manage().window().maximize();
			get_The_Row_From_Login_Data = 17;

		}

		@Test
		public void testMainTest() throws Exception {
			String Actual_Text = null;
			String Expected_Text = null;
			// Login to dashboard.
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			Thread.sleep(3000);
			logger.info("US1653 Analyst views current revision alert of an application");
			// Verify if there is an existing certification on the dashboard and
			// delete to start a new certification.
			DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
			deleteDraftCert.DeleteDraftCert();
			// Verify for active and Draft certification on the dashboard, if draft
			// delete and start a new EDWOSB certification.
			Thread.sleep(4000);
			AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
			addOrStartCertification.AddOrStartCertification();
			// Start new 8(a) application.
			EdwobEightAMppTest1Page edwobEightAMppTest1 = new EdwobEightAMppTest1Page(webDriver);
			edwobEightAMppTest1.EdwobEightAMppTest1();
			// Logout and login as an analyst.
			webDriver.findElement(By.linkText("Logout")).click();
			get_The_Row_From_Login_Data = 31;
			LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data1.Login_With_Reference();
			//Search for the business with submitted certification.
			webDriver.findElement(By.id("query")).sendKeys("144291293");
			webDriver.findElement(By.xpath("//button[@type='submit']")).click();
			webDriver.findElement(By.linkText("Entity 127 Legal Business Name")).click();
			Thread.sleep(6000);
			//Verify the fist version alert.
			webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.linkText("Revision history")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
			Expected_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/table/tbody/tr/td[4]")).getText();
			String[] Actual_Text1 = Actual_Text.split(" ");
			String part1 = Actual_Text1[4]; // Submitted Date
			assertEquals(part1, Expected_Text);
			logger.info(part1);
			webDriver.findElement(By.linkText("Case overview")).click();
			WebElement V1 = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
			HighLight.highLightElement(webDriver, V1);
			Thread.sleep(5000);
			WebElement AlertV1 = webDriver.findElement(By.cssSelector("p.usa-alert-text"));
			HighLight.highLightElement(webDriver, AlertV1);
			// Take screenshot and store as a file format
			ScreenShotPage screenShot = new ScreenShotPage(webDriver);
			screenShot.ScreenShot();
			Thread.sleep(4000);
			webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();
			//Locate and click on the return to vendor link.
			webDriver.findElement(By.linkText("Return to Vendor")).click();
			webDriver.switchTo().alert().accept();
			Thread.sleep(3000);
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "A new application has been reopenned for the vendor";
			assertEquals(Actual_Text, Expected_Text);
			// Logout and login as the vendor with the clone certification and re-submit.
			webDriver.findElement(By.linkText("Logout")).click();
			get_The_Row_From_Login_Data = 17;
			LoginPageWithReference login_Data11 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data11.Login_With_Reference();
			Thread.sleep(3000);
			//Vendor dashboard.
			Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
			Expected_Text = "Draft";
			assertEquals(Actual_Text, Expected_Text);
			WebElement Draft1 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
			HighLight.highLightElement(webDriver, Draft1);
			webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
			Thread.sleep(2000);
			webDriver.findElement(By.name("commit")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Review";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.name("commit")).click();
			webDriver.switchTo().alert().accept();
			logger.info("Step 11 - Click to accept the statements");
			webDriver.findElement(By.id("legal_0")).click();
			webDriver.findElement(By.id("legal_1")).click();
			webDriver.findElement(By.id("legal_2")).click();
			webDriver.findElement(By.id("legal_3")).click();
			webDriver.findElement(By.id("legal_4")).click();
			webDriver.findElement(By.id("legal_5")).click();
			Thread.sleep(2000);
			webDriver.findElement(By.id("accept-button")).click();
			Thread.sleep(3000);
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "Your application has been submitted";
			assertEquals(Actual_Text, Expected_Text);
			WebElement RESUBMIT = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
			HighLight.highLightElement(webDriver, RESUBMIT);
			// Logout and login as an analyst.
			webDriver.findElement(By.linkText("Logout")).click();
			get_The_Row_From_Login_Data = 31;
			LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data2.Login_With_Reference();
			//Search for the business with submitted certification.
			webDriver.findElement(By.id("query")).sendKeys("144291293");
			webDriver.findElement(By.xpath("//button[@type='submit']")).click();
			webDriver.findElement(By.linkText("Entity 127 Legal Business Name")).click();
			Thread.sleep(6000);
			//Verify the fist version alert.
			webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.linkText("Revision history")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
			Expected_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/table/tbody/tr/td[4]")).getText();
			String[] Actual_Text0 = Actual_Text.split(" ");
			String part0 = Actual_Text0[4]; // Submitted Date
			assertEquals(part0, Expected_Text);
			logger.info(part0);
			webDriver.findElement(By.linkText("Case overview")).click();
			Actual_Text = webDriver.findElement(By.linkText("View previous version")).getText();
			Expected_Text = "View previous version";
			assertEquals(Actual_Text, Expected_Text);
			WebElement V2 = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
			HighLight.highLightElement(webDriver, V2);
			Thread.sleep(5000);
			WebElement AlertV2 = webDriver.findElement(By.cssSelector("p.usa-alert-text"));
			HighLight.highLightElement(webDriver, AlertV2);
			Thread.sleep(2000);
			// Take screenshot and store as a file format
			ScreenShotPage screenShot1 = new ScreenShotPage(webDriver);
			screenShot1.ScreenShot();
			//Click to view previous version.
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String main_window = webDriver.getWindowHandle();
			logger.info("Before switching, title is = certify.sba.gov");
			webDriver.findElement(By.linkText("View previous version")).click();
			Thread.sleep(5000);
			assertEquals(Actual_Text, Expected_Text);
			java.util.Set<String> S1 = webDriver.getWindowHandles();
			Iterator<String> i1 = S1.iterator();
			while (i1.hasNext()) {
				String Second_window = i1.next();
				if (!main_window.equalsIgnoreCase(Second_window)) {
					webDriver.switchTo().window(Second_window);
					webDriver.manage().window().maximize();
					Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
					Expected_Text = "You are in view-only mode (Version #1)";
					assertEquals(Actual_Text, Expected_Text);
					Thread.sleep(5000);
					WebElement NewText = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
					HighLight.highLightElement(webDriver, NewText);
					// Take screenshot and store as a file format
					ScreenShotPage screenShot3 = new ScreenShotPage(webDriver);
					screenShot3.ScreenShot();
					Thread.sleep(3000);
					webDriver.close();
					webDriver.switchTo().window(main_window);
					logger.info("Back to manin_window = certify.sba.gov");
				} else {
					logger.info("Second Window is thesame as first window");

				}
			
			}}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}		
		
		
		
		
		
		
		
