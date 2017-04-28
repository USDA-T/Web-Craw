//TS Created By _deepa patri
package gov.sba.utils.integration;

import junit.framework.TestCase;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import gov.sba.automation.utils.CommonApplicationMethods;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
@Category({ gov.sba.utils.integration.StableTests.class })
public class TestCreateWosbCertTs1 extends TestCase {
  //Get the questions names for which Prepopulate flag set to true
//Start create New Wosb/Edwosb application
//Check the Answers are prepopulating with previous answers.
  private static WebDriver webDriver;
  private static final Logger logger_TestApp395Edwosb = LogManager.getLogger(TestApp395EdwosbFlag.class.getName());
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
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
      //Return the Applicatiom;
      if (CommonApplicationMethods.checkApplicationExists(webDriver, "WOSB", "Active"))
      { CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
        CommonApplicationMethods.return_all_Applications(webDriver, 11, "159165917");
        login_Data = new LoginPageWithReference(webDriver, 10);
        login_Data.Login_With_Reference();
      }
      CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      CommonApplicationMethods.deleteApplication( webDriver, "Wosb", "Draft");

    //start New WOSB Applicatiom
        CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        CommonApplicationMethods.createApplication(webDriver,"WOSB");
        NewLLCQuestionanireDeepa NewLLCQuestionanireDeepa = new NewLLCQuestionanireDeepa();
        NewLLCQuestionanireDeepa.NewLLCQuestionanireDeepa(webDriver);
        fillApplCreatePages.finalSignatureSubmit(webDriver);


    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{TestCreateWosbCertTs1.class.getName(), "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {

    webDriver.quit();
  }
}