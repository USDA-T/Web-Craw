package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestApp187OppSupport extends TestCase {

    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger TestApp187OppSupport = LogManager.getLogger(TestApp187OppSupport.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {

        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        get_The_Row_From_Login_Data = 27;
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        try {
            // US1280- Search Government ;
            // Get Email,First,Name,Last from the Db to use as serach term in UI
            // Connect SBAONE QA DB -to get data from DB
            // TestApp187OppSupport.info(returned_GovProfile_Rows[1][1]);
            // pass Government/vendor profile criteria
            String Gov_Radio_xpath = "//input[@id='user_type_gov_user']";
            String Expected_Result = "Government user profile";
            // TestuserProfileSearchType.TestuserProfileSearch(webDriver,
            // returned_GovProfile_Rows[1][1],Gov_Radio_xpath,Expected_Result);
            TestuserProfileSearchType.TestuserProfileSearch(webDriver, "Analyst", Gov_Radio_xpath, Expected_Result);
            webDriver.navigate().back();
            // US1280- Search Vendor ;
            // userprofile search function
            // pass Government/vendor profile criteria
            String Ven_Radio_xpath = "//input[@id='user_type_vendor_user']";
            String Expected_Result1 = "Vendor user profile";
            TestuserProfileSearchType.TestuserProfileSearch(webDriver, "Deepa", Ven_Radio_xpath, Expected_Result1);
        } catch (Exception e) {
            TestApp187OppSupport.info(e.toString());
            // TestApp187OppSupport.info("test failed ");
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

}
