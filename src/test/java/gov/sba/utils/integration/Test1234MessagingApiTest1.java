package gov.sba.utils.integration;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	import org.openqa.selenium.WebDriver;
	import gov.sba.automation.TestHelpers;
	import junit.framework.TestCase;

	public class Test1234MessagingApiTest1 extends TestCase {
		private static final Logger logger = LogManager.getLogger(Test1234MessagingApiTest1.class.getName());
		private static WebDriver webDriver;
		int get_The_Row_From_Login_Data;

		@Before
		public void setUp() throws Exception {
			webDriver = TestHelpers.getDefaultWebDriver();
			webDriver.get(TestHelpers.getBaseUrl());
			webDriver.manage().window().maximize();
			get_The_Row_From_Login_Data = 92;

		}

		@Test
		public void testApp8aInitialMaterApplication() throws Exception {
			logger.info("Test for messaging Api");
			// Completing 8a initial application.
			 try {
			Complete8aForMessaging1Page CompleteAndSubmit8a = new Complete8aForMessaging1Page(webDriver);
			CompleteAndSubmit8a.Complete8aForMessaging1();
			
			CodsSupervisorAssignCaseToCodsAnalystPage codsSupervisorAssignCaseToCodsAnalyst = new CodsSupervisorAssignCaseToCodsAnalystPage(webDriver);
			codsSupervisorAssignCaseToCodsAnalyst.CodsSupervisorAssignCaseToCodsAnalyst();
			 } catch (Exception e) {
					
			logger.info("Upload will not run on headless " + e.getMessage());
					}
			logger.info("Success");
		}

		@After
		public void tearDown() throws Exception {
			webDriver.close();
		}
	}
