package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.AssertionUtils;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import gov.sba.utils.integration.FillApplCreatePages;
import gov.sba.utils.integration.LoginPageWithReference;
import gov.sba.utils.integration.NewAddBusinessPartern413Deepa;
import gov.sba.utils.integration.NewFinancialSectionQuestionDeepa;
import gov.sba.utils.integration.NewScorpQuestionPageDeepa;
import junit.framework.TestCase;


@Category({gov.sba.utils.integration.UnstableTests.class})
public class TestWorkflowBusinessPartner413AddandRemove extends TestCase {
  private static final Logger logger_TestApp395Edwosb =
      LogManager.getLogger(TestWorkflowBusinessPartner413AddandRemove.class.getName());
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
    // CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 49;
  }

  public void testMainTest() throws Exception {
    // Login to dashboard.
    try {
      // Check Dashboard Pending status
      LoginPageWithReference login_Data =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data.Login_With_Reference();
      // Return the Applicatiom;
      if (CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active")) {
        navigationMenuClick(webDriver, "LOGOUT");
        AssertionUtils.return_All_Applications(webDriver, 11, "246235962");
        login_Data = new LoginPageWithReference(webDriver, 49);
        login_Data.Login_With_Reference();
      }
      CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      CommonApplicationMethods.deleteApplication(webDriver, "Wosb", "Draft");

      // start New Applicatiom
      navigationMenuClick(webDriver, "Programs");
      programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      // Create New Edwosb Application with select No to all questions
      // with one person's 413 form
      NewScorpQuestionPageDeepa scorpQuestionsPage = new NewScorpQuestionPageDeepa(webDriver);
      scorpQuestionsPage.NewScorpQuestionPageDeepa();

      new NewAddBusinessPartern413Deepa(webDriver).NewFinancialQuestion("Mahesh", "Prem",
          "987654321", "12@gmail.com", "1234", "Virginia", "11111", "123", "123", "Mclean", "USA");
      new NewFinancialSectionQuestionDeepa(webDriver).NewFinancialQuestion();
      FillApplCreatePages.finalSignatureSubmit(webDriver);
      // Return the Applicatiom;
      navigationMenuClick(webDriver, "LOGOUT");
      AssertionUtils.return_All_Applications(webDriver, 11, "246235962");
      // Delete All the Draft Applications
      login_Data = new LoginPageWithReference(webDriver, 49);
      login_Data.Login_With_Reference();
      CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");

    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestWorkflowBusinessPartner413AddandRemove", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
