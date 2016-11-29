package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

public class TestUs1789Create8aInterimQuestionnaire extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestUs1789Create8aInterimQuestionnaire.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 3;

	}

	@Test
	public void testMainTest() throws Exception {
		logger.info("Test Creating 8(a) Interim questionnaire");
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		// Verify if there is an existing Draft certification on the dashboard and delete to start a new certification.
		DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
		deleteDraftCert.DeleteDraftCert();
		//Start an 8(a) Program.
		AddOrStartEightACertificationPage addOrStartEightACertification = new AddOrStartEightACertificationPage(webDriver);
		addOrStartEightACertification.AddOrStartEightACertification();
		//8(a) Interim questionnaire.
		EightATestPage eightATest = new EightATestPage(webDriver);
		eightATest.EightATest();
		

}

@After
public void tearDown() throws Exception {
	webDriver.quit();
}
}	
	
	
	
	
	
	
	
	
	
	
	