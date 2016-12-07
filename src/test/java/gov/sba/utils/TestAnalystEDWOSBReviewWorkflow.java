package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import junit.framework.TestCase;

public class TestAnalystEDWOSBReviewWorkflow extends TestCase {
	WebDriver webDriver;
	private static final Logger logger_TestEDWOSBWorkflow = LogManager.getLogger(TestAnalystEDWOSBReviewWorkflow.class.getName());
	int get_The_Row_From_Login_Data;
	
    @Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 9;
 	}

	@Test    
    public void testMainTest() throws Exception {
        try {
    		// Login to dashboard.
			//C:\Upload\Upload.pdf


    		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    		login_Data.Login_With_Reference();
    		Thread.sleep(2000);
    		logger_TestEDWOSBWorkflow.info(webDriver.findElement(By.xpath("//p[ (b[contains(text(),'DUNS:')]) ]")).getText().replaceAll("DUNS:", "").trim());
    		String duns_Number = webDriver.findElement(By.xpath("//p[ (b[contains(text(),'DUNS:')]) ]")).getText().replaceAll("DUNS:", "").trim();
			String typ_App = "EDWOSB";
			//Clear EDWOSB Application
    		if (commonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active")){
    			commonApplicationMethods.returnApplicationToVendorMethd(webDriver, 11, duns_Number, "EDWOSB", "Active", get_The_Row_From_Login_Data);
    			commonApplicationMethods.deleteApplication(webDriver, "EDWOSB", "Draft");
    		}
    		// Delete draft EDWOSB
    		commonApplicationMethods.deleteApplication(webDriver, "EDWOSB", "Draft");
			// Create EDWOSB Application
    		commonApplicationMethods.createApplication(webDriver, "EDWOSB");
			//8a Page fill uP
    		fillApplCreatePages.page8aFillUp(webDriver, "Yes", "C:\\Upload\\Upload.pdf");
			//Signature Page fill uP
    		fillApplCreatePages.finalSignatureSubmit(webDriver);
			//Assert Application is Created
			org.junit.Assert.assertTrue(commonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active"));
			commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
			login_Data = new LoginPageWithReference(webDriver, 11);
			login_Data.Login_With_Reference();
			commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
			AnalystReviewPage TestReviewProcess = new AnalystReviewPage( );
			TestReviewProcess.TestReviewDriver(webDriver, duns_Number,typ_App, "Initial Review", "Analyst2 X", "Analyst3 X","Analyst4 X");
			TestReviewProcess.testUnderReview();

			login_Data = new LoginPageWithReference(webDriver, 31);
			login_Data.Login_With_Reference();

			commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            SuperVisorReviewPage TestReviewProcess1 = new SuperVisorReviewPage( );
			TestReviewProcess1.TestReviewDriver(webDriver, duns_Number);
            TestReviewProcess1.testMainTest();
		}
        catch (Exception e) {
        	logger_TestEDWOSBWorkflow.info(e.toString());
        }
    }
}