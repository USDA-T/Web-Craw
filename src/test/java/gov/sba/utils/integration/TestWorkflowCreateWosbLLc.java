//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.AssertionUtils;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.pageObjetcs.programs_Page.join_New_Program_CheckBoxes;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})

public class TestWorkflowCreateWosbLLc extends TestCase {
  private static final Logger logger_TestApp395Edwosb =
          LogManager.getLogger(TestWorkflowCreateWosbLLc.class.getName());
  // Get the questions names for which Prepopulate flag set to true
  // Start create New Wosb/Edwosb application
  // Check the Answers are prepopulating with previous answers.
  private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
        
    webDriver.get(TestHelpers.getBaseUrl());
    //CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 10;
  }

  @Test
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
        AssertionUtils.return_All_Applications(webDriver, 11, "159165917");
        new LoginPageWithReference(webDriver, 10).Login_With_Reference();
      }

      deleteApplication(webDriver, "Edwosb", "Draft");
      deleteApplication(webDriver, "Wosb", "Draft");

      // start New WOSB Applicatiom
      join_New_Program_CheckBoxes(webDriver, "WOSB");
      new NewLLCQuestionanireDeepa().NewLLCQuestionanireDeepa(webDriver);
      FillApplCreatePages.finalSignatureSubmit(webDriver);
    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      take_ScreenShot_TestCaseName(webDriver, new String[]{TestWorkflowCreateWosbLLc.class.getName(), "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {

    webDriver.quit();
  }
}
