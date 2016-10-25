package gov.sba.utils;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.junit.*;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	import junit.framework.TestCase;

	public class US1464VendorSubmitsTwoApplicationsAtOnce extends TestCase {
		// Set The variabl.es/Define
		private static WebDriver webDriver;
		private static final Logger logger = LogManager
				.getLogger(TestUs1531RestrictCoAccessToActiveOrPendingMppRecords.class.getName());
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
			//String Actual_Text = null;
			//String Expected_Text = null;
			// Login to dashboard.
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			Thread.sleep(3000);
			logger.info("US1531Restrict search results on Request Access to View Records page for CO role");
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
			EDWOSBEightATest1Page eDWOSBEightATest1 = new EDWOSBEightATest1Page(webDriver);
			eDWOSBEightATest1.EDWOSBEightATest1();
			// Verify for active and Draft program on the dashboard, if draft
			// delete and start a new one.
			webDriver.findElement(By.linkText("Dashboard")).click();
			// Verify if there is an existing certification on the dashboard and
			// delete to start a new certification.
			DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
			deleteDraftCert1.DeleteDraftCert();
			Thread.sleep(4000);
			AddOrStartNewMppProgramPage1 addOrStartNewMppProgram = new AddOrStartNewMppProgramPage1(webDriver);
			addOrStartNewMppProgram.AddOrStartNewMppProgram();
			// Start new 8(a) application.
			EdwobEightAMppTest1Page edwobEightAMppTest1 = new EdwobEightAMppTest1Page(webDriver);
			edwobEightAMppTest1.EdwobEightAMppTest1();
			//Start new WOSB.
			webDriver.findElement(By.linkText("Dashboard")).click();
			AddOrStartNewWosbCertPage addOrStartNewWosbbCert = new AddOrStartNewWosbCertPage(webDriver);
			addOrStartNewWosbbCert.AddOrStartNewWosbbCert();
			Thread.sleep(4000);
			WOSBEightATestPage wOSBEightATest = new WOSBEightATestPage(webDriver);
			wOSBEightATest.WOSBEightATest();
			
			
		}

		@After
		public void tearDown() throws Exception {
			// webDriver.quit();
		}
	}

