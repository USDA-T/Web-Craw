//TS Created By _deepa patri
package gov.sba.utils.integration;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
@Category({ gov.sba.utils.integration.StableTests.class })
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
    get_The_Row_From_Login_Data = 9;

  }
  @Test

  public void testMainTest() throws Exception {
    // Login to dashboard.
    try {
      // Check Dashboard Pending status
      LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data.Login_With_Reference();
      //Return the Applicatiom;
      if (CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active"))
      { CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
        CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
        login_Data = new LoginPageWithReference(webDriver, 9);
        login_Data.Login_With_Reference();
      }
      CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      CommonApplicationMethods.deleteApplication( webDriver, "Wosb", "Draft");

      //start New Applicatiom
      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      CommonApplicationMethods.createApplication(webDriver,"EDWOSB");
      //Create New Edwosb Application with select No to all questions with one person's 413 form
      NewScorpQuestionPageDeepa scorpQuestionsPage = new NewScorpQuestionPageDeepa(webDriver);
      scorpQuestionsPage.NewScorpQuestionPageDeepa();
      NewFinancialSectionQuestionDeepa financialsection = new NewFinancialSectionQuestionDeepa(webDriver);
      financialsection.NewFinancialQuestion();
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      //Return the Applicatiom;
       CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
       CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
      //Delete All the Draft Applications
       login_Data = new LoginPageWithReference(webDriver, 9);
       login_Data.Login_With_Reference();
       CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      //Check the Pre-Answered with prior Answer.
      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      webDriver.findElement(By.id("certificate_type_edwosb")).click();
      webDriver.findElement(By.id("add_certification")).click();
      webDriver.findElement(By.className("accept_button")).click();

      // Verify the answers are prepopulated with prior answeres for certain questions and answers are not prepopulated for certian questions
      String checkBoxElement = webDriver.findElement(By.id("answers_389_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));

      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_389_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      logger_TestApp395Edwosb.info(" 8(a) question assert not being prepopulated");
      // Locate the Third Party Certification, question1 and select No and
      // continue.
      checkBoxElement = webDriver.findElement(By.id("answers_390_value_yes")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_390_Y");
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      logger_TestApp395Edwosb.info("  Third party question assert not being prepopulated");
      //Locate Changes in Eligiblity
      checkBoxElement = webDriver.findElement(By.id("answers_391_value_yes")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_391_Y");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      logger_TestApp395Edwosb.info("  Change in Eligiblity assert not being prepopulated");
      // Locate the Three Business Corporation and S-Corp(Stocks) question
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_393_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_394_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_395_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_396_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_397_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_398_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_398_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
  // Locate the Citizenship & Ownership question 1and2, Verify,select No
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_399_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

  CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

   assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_404_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
 CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
   assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_405_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

     assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_406_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));

     assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_407_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
  CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

     assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_408_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_409_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_410_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_411_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_412_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      assertTrue(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_413_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      // Locate the SBA Exam & Daily Operations questions,Verify, select No
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_414_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_414_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
     //NetWorth
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_415_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_415_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      //Adjusted Gross Income
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_416_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_416_N");
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_417_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_417_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

      //Assets
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_418_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_418_N");
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_419_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_419_N");
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_420_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_420_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      //Economic Disadvantage
      assertFalse(CommonApplicationMethods.find_Element(webDriver,"EDWOSB_Questionnaire_Page_Ans_421_N" ).getAttribute("outerHTML").toLowerCase().contains("checked"));
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_421_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
      logger_TestApp395Edwosb.info("EDWOSB application questions have been answered");
      // Validate that user successfully navigated to the Financial Data
      // section.
      String actual_Text49 = webDriver.findElement(By.cssSelector("h2")).getText();
      String expected_Text49 = "Financial Data";
      assertEquals(actual_Text49, expected_Text49);
      String actual_Text52 = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
      String expected_Text52 = "This section must be completed by each individual claiming economic disadvantage in connection with the 8(a) Program and/or the Women-Owned Small Business Federal Contract Program. If married, the spouse must complete this section, except when the individual and the spouse are legally separated. If separated, provide copy of separation document.";
      assertEquals(actual_Text52, expected_Text52);
      //Validate the Personal Information.
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Add_Person");
      // Verify that the section to Create new record is been seen by user and
      // enter record2.
      logger_TestApp395Edwosb.info("the page to Create and Add new Record is Present, PASS");
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_FirstName","Deepa") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_LastName","MaheshP") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_SSN","123456789") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Email","DeepaMaheshP@gmail.com") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_City","Mclean") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Address","8421 Broad Street") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_State","Virgina") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Postal","22190") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Co","Usa") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_HPhone","1234561234") ;
      CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_Questionnaire_Page_Bphone","1012023004") ;

      new Select(CommonApplicationMethods.find_Element (webDriver,  "EDWOSB_Questionnaire_Page_title")).selectByIndex(2);
      new Select(CommonApplicationMethods.find_Element (webDriver,  "EDWOSB_Questionnaire_Page_MaritalSt")).selectByIndex(2);

      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Done_Button");

      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_423_N");
      CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

      //Check if the Active certificate is exist-Then Return by analyst
      CommonApplicationMethods.checkApplicationExists(webDriver,"Edwosb","Active");
      CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
      CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"TestApp395EdwosbFlag", "Exception"});
      throw new Exception("Error: ", e);
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}

