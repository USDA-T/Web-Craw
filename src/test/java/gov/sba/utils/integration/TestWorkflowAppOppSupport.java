// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.utils.integration.TestUserAppProfileSearchType.TestuserProfileSearch;


@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowAppOppSupport extends TestCase {
  Logger logger = LogManager.getLogger(TestWorkflowAppOppSupport.class.getName());
  /* Set The variablesDefine */
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.clear_Env_Chrome();
    get_Stop_Execution_Flag();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    get_The_Row_From_Login_Data = 27;
  }

  @Test
  public void testMainTest() throws Exception {
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();

    try {
      /*
       * US1280- Search Government ; Get Email,First,Name,Last from the Db to use as serach term in
       * UI Connect SBAONE QA DB -to get data from DB
       * TestWorkflowAppOppSupport.info(returned_GovProfile_Rows[1][1]); // pass Government/vendor
       * profile criteria TestUserAppProfileSearchType.TestuserProfileSearch(webDriver, //
       * returned_GovProfile_Rows[1][1],Gov_Radio_xpath,Expected_Result);
       */

      TestuserProfileSearch(webDriver, "Analyst", "Opp_Support_Page_Govt_Profile_Search",
          "Government user profile");
      navigationBarClick(webDriver, "DashBoard");

      /*
       * US1280- Search Vendor ; //userprofile search function pass Government/vendor profile
       * criteria
       */
      TestuserProfileSearch(webDriver, "QA", "Opp_Support_Page_Vendor_Prof_Search",
          "Vendor user profile");
      click_Element(webDriver, "Opp_Support_Page_Test_Business_Name");
      assertEquals("Vendor Support",
          find_Element(webDriver, "Opp_Support_Page_Test_Business_Search").getText().toString());

    } catch (Exception e) {
      logger.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestWorkflowAppOppSupport", "Exception"});
      logger.info("test failed ");
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}
