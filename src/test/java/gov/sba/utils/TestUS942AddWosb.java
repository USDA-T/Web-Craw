package gov.sba.utils;

//__ Logger

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

    @Before
    public void setUp() throws Exception {

        commonApplicationMethods.clear_Env_Chrome();

        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 10;
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
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
