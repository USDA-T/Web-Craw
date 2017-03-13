package gov.sba.utils.integration;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import gov.sba.automation.utils.CommonApplicationMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestApp395EdwosbFlag extends TestCase {

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
  @Test

  public void testMainTest() throws Exception {
    // Login to dashboard.
    try {
      // Check Dashboard Pending status
      LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data.Login_With_Reference();
      Thread.sleep(2000);
      //Return the Applicatiom;
      if (CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active"))
      { CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
        CommonApplicationMethods.return_all_Applications(webDriver, 11, "159165917");
        login_Data = new LoginPageWithReference(webDriver, 10);
        login_Data.Login_With_Reference();
      }
      CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      CommonApplicationMethods.deleteApplication( webDriver, "Wosb", "Draft");

      //start New Applicatiom

      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      webDriver.findElement(By.id("certificate_type_edwosb")).click();
      webDriver.findElement(By.id("add_certification")).click();
      webDriver.findElement(By.className("accept_button")).click();
      NewScorpQuestionPage scorpQuestionsPage = new NewScorpQuestionPage(webDriver);
      scorpQuestionsPage.NewScorpQuestion();
      NewFinancialSectionQuestion financialsection = new NewFinancialSectionQuestion(webDriver);
      financialsection.NewFinancialQuestion();
      fillApplCreatePages.finalSignatureSubmit(webDriver);

      //Return the Applicatiom;
       CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
       CommonApplicationMethods.return_all_Applications(webDriver, 11, "159165917");
      //Delete All the Draft Applications
       CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
       login_Data = new LoginPageWithReference(webDriver, 10);
       login_Data.Login_With_Reference();
       CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");



      //Check if the Active certificate is exist-Then Return by analyst
      CommonApplicationMethods.checkApplicationExists(webDriver,"Edwosb","Active");
      CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
      CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      throw new Exception("Error: ", e);
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}

