package gov.sba.utils;

//__ Logger
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.helpers.LoginHelpers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

//_ Project Helpers
public class TestUS942AddEdwosb extends TestCase {
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
        get_The_Row_From_Login_Data = 9;
        duns_Number = LoginHelpers.getLoginDataWithIndex(get_The_Row_From_Login_Data).getDunsNumber();

    }

    @Test
    public void testMainTest() throws Exception {
        commonApplicationMethods.return_all_Applications(webDriver, 11, duns_Number);
        commonApplicationMethods.return_all_Applications(webDriver, 29, duns_Number);

        // Login to dashboard.
        commonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");

        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        commonApplicationMethods.delete_all_Drafts(webDriver);

        Thread.sleep(3000);

        commonApplicationMethods.delete_all_Drafts(webDriver);


        VerifyEdwosbFlow VerifyEDWOSBFlow = new VerifyEdwosbFlow();
        VerifyEDWOSBFlow.VerifyEDWOSBFlowSetDriver(webDriver);
        VerifyEDWOSBFlow.VerifyEDWOSBFlowLogic();

    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

}
