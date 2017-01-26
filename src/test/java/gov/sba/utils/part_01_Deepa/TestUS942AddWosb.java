package gov.sba.utils.part_01_Deepa;

//__ Logger

import gov.sba.utils.CommonFunctions.LoginPageWithDetails;
import gov.sba.utils.CommonFunctions.TestHelpers;
import gov.sba.utils.CommonFunctions.VerifyWosbFlow;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;

//_ Project Helpers
public class TestUS942AddWosb extends TestCase {
    // Set The variables/Define
    private static WebDriver webDriver;
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        commonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        commonApplicationMethods.focus_window();
        String[] details = commonApplicationMethods.return_Good_Duns_no();
        email = details[0];
        password = details[1];
        duns_Number = details[2];
    }

    @Test
    public void testMainTest() throws Exception {


        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();
        Thread.sleep(3000);

        VerifyWosbFlow VerifyWOSBFlow = new VerifyWosbFlow();
        VerifyWOSBFlow.VerifyWOSBFlowSetDriver(webDriver);
        VerifyWOSBFlow.VerifyWOSBFlowLogic();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

}
