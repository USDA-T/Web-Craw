package gov.sba.utils;

//__ Logger

import gov.sba.utils.helpers.LoginHelpers;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;

//_ Project Helpers
public class TestUS942AddWosb extends TestCase {
    // Set The variables/Define
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;
    String duns_Number;

    @Before
    public void setUp() throws Exception {
        commonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 10;
        duns_Number = LoginHelpers.getLoginDataWithIndex(get_The_Row_From_Login_Data).getDunsNumber();
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard- Check teh EDWOSB application in Active status
        commonApplicationMethods.return_all_Applications(webDriver, 11, duns_Number);
        commonApplicationMethods.return_all_Applications(webDriver, 29, duns_Number);


        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        commonApplicationMethods.delete_all_Drafts(webDriver);
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
