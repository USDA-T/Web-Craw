package gov.sba.utils.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

//_ Project Helpers
public class TestUS942AddWosb extends TestCase {
    // Set The variables/Define
    private static WebDriver webDriver;
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        CommonApplicationMethods.focus_window();
        String[] details = CommonApplicationMethods.return_Good_Duns_no();
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
