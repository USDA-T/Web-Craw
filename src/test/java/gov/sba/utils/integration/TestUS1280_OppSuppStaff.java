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
public class TestUS1280_OppSuppStaff extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1280 = LogManager.getLogger(TestUS1280_OppSuppStaff.class.getName());
    private int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {

        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        get_The_Row_From_Login_Data = 28;
    }

    @Test
    public void testMainTest() throws Exception {
        try {

            String sql_query = "select email,max_first_name,max_last_name from sbaone.users where max_id is not null and max_first_name is not null and max_last_name is not null";
            DatabaseQuery dbcall = new DatabaseQuery();
            String[][] returned_GovProfile_Rows = dbcall.getDBData(sql_query, 2, 3);

            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(1500);

            // US1280- Search Government ;
            // Get Email,First,Name,Last from the Db to use as serach term in UI
            // Connect SBAONE QA DB -to get data from DB
            // To call DB-- pass Sql query, no of rows,no of cols to db function

            logger_US1280.info(returned_GovProfile_Rows[1][1]);

            // pass Government/vendor profile criteria
            String Gov_Radio_xpath = "//input[@id='user_type_gov_user']";
            String Expected_Result = "Government user profile";
            TestuserProfileSearchType.TestuserProfileSearch(webDriver, returned_GovProfile_Rows[1][1], Gov_Radio_xpath,
                    Expected_Result);
            webDriver.navigate().back();
            // US1280- Search Vendor ;
            // Get Email,First,Name,Last from the Db to use as serach term in UI
            // Connect SBAONE QA DB -to get data from DB
            // To call DB-- pass Sql query, no of rows,no of cols to db function
            sql_query = "select email,first_name,last_name from sbaone.users where max_id is null and email like 'd%'";
            @SuppressWarnings("static-access")
            String[][] areturned_Rows = dbcall.getDBData(sql_query, 2, 3);
            logger_US1280.info(areturned_Rows[1][1]);
            // Pass the user search radio button,the serach term to the
            // userprofile search function
            // pass Government/vendor profile criteria
            String Ven_Radio_xpath = "//input[@id='user_type_vendor_user']";
            String Expected_Result1 = "Vendor user profile";
            TestuserProfileSearchType.TestuserProfileSearch(webDriver, areturned_Rows[1][1], Ven_Radio_xpath,
                    Expected_Result1);

        } catch (Exception e) {
            logger_US1280.info(e.toString());
            throw e;
            // logger_US1081.info("test failed as return_vendor link dididnot
            // work");
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}