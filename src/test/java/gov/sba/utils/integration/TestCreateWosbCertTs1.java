// TS Created By _deepa patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.checkApplicationExists;
import static gov.sba.automation.CommonApplicationMethods.createApplication;
import static gov.sba.automation.CommonApplicationMethods.deleteApplication;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.AssertionUtils;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestCreateWosbCertTs1 extends TestCase {
  // Get the questions names for which Prepopulate flag set to true
  // Start create New Wosb/Edwosb application
  // Check the Answers are prepopulating with previous answers.
  private static WebDriver webDriver;
  private static final Logger logger_TestApp395Edwosb =
      LogManager.getLogger(TestApp395EdwosbFlag.class.getName());
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

  public void testMainTest() throws Exception {
    // Login to dashboard.
    try {
      // Check Dashboard Pending status
      new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data).Login_With_Reference();

      // Example Usage for Others
      // verify_Element_Attribute(webDriver,
      // "Main_Page_Menu_Button","Main_Page_Menu_Button_Class");
      // verify_Element_Property(webDriver, "Main_Page_Menu_Button",
      // "General_Element_Property_Enabled");
      // verify_Element_Attribute(webDriver, "Main_Page_Menu_Button",
      // "Main_Page_Menu_Button_Name");

      // Return the Applicatiom;
      if (checkApplicationExists(webDriver, "WOSB", "Active")) {
        navigationMenuClick(webDriver, "LOGOUT");
        AssertionUtils.return_all_Applications(webDriver, 11, "159165917");
        new LoginPageWithReference(webDriver, 10).Login_With_Reference();
      }

      deleteApplication(webDriver, "Edwosb", "Draft");
      deleteApplication(webDriver, "Wosb", "Draft");

      // start New WOSB Applicatiom
      navigationMenuClick(webDriver, "Programs");
      createApplication(webDriver, "WOSB");
      new NewLLCQuestionanireDeepa().NewLLCQuestionanireDeepa(webDriver);
      fillApplCreatePages.finalSignatureSubmit(webDriver);
    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      take_ScreenShot_TestCaseName(webDriver,
          new String[] {TestCreateWosbCertTs1.class.getName(), "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {

    webDriver.quit();
  }
}
