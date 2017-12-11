package gov.usda.others;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import gov.usda.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

@Category({gov.usda.utils.integration.StableTests.class})
public class TestLocators extends TestCase {
  private static final Logger logger = LogManager.getLogger(TestLocators.class.getName());
  private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {

    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();

    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 10;
  }

  @Test
  public void testLocators() throws Exception {
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();

    // Need to submit the application in EDWosb, Wosb, MPP::
    // Log in As OppSupport Staft - validate as per the US1235 Acceptance
    // criteria on Opp Support Staft/Admin page

    try {
      CommonApplicationMethods.click_Element(webDriver, "SBA_WOSB_Table_Link");
    } catch (Exception e) {
      logger.info("Search TextBox is on Main Navigator is not present" + e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"Test1235OppSuppAdminRole", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
