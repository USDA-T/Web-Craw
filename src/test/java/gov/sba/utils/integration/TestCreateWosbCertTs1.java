package gov.sba.utils.integration;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import gov.sba.automation.utils.CommonApplicationMethods;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
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
      Thread.sleep(2000);
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
      webDriver.findElement(By.id("certificate_type_wosb")).click();
      webDriver.findElement(By.id("add_certification")).click();
      webDriver.findElement(By.className("accept_button")).click();
      NewLLCQuestionaire NewLLCQuestionaire = new NewLLCQuestionaire(webDriver);
      NewLLCQuestionaire.NewLlcquestions();
      fillApplCreatePages.finalSignatureSubmit(webDriver);


    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      throw new Exception("Error: ", e);
    }
  }

  @After
  public void tearDown() throws Exception {
    //webDriver.quit();
  }
}