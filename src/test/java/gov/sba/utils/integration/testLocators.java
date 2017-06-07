//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
//@Category({gov.sba.utils.integration.StableTests.class})

public class testLocators extends TestCase {
  private static final Logger logger_US1235 =
      LogManager.getLogger(Test1235OppSuppAdminRole.class.getName());
    // Set The variabl.es/Define
    private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 10;
  }

  @Test
  public void testMainTest() throws Exception {
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();

    // Need to submit the application in EDWosb, Wosb, MPP::
    // Log in As OppSupport Staft - validate as per the US1235 Acceptance
    // criteria on Opp Support Staft/Admin page

    try {
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Self_Certification_Link");

    } catch (Exception e) {
      logger_US1235.info("Search TextBox is on Main Navigator is not present" + e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[] {"Test1235OppSuppAdminRole", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
