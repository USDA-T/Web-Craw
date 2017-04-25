// Created BY Deepa Patri
package gov.sba.utils.integration;

import gov.sba.automation.utils.CommonApplicationMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewLLCQuestionanireDeepa {

    private static final Logger logger = LogManager
        .getLogger(gov.sba.utils.integration.NewLLCQuestionanireDeepa.class.getName());
    WebDriver webDriver;

    public void NewLLCQuestionanireDeepa(WebDriver webDriver ) throws Exception {
String Actual_Text = null;
      String Expected_Text = null;
      // Locate the 8(a) question and select No and continue.
      Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
      Expected_Text = "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
      assertEquals(Actual_Text, Expected_Text);
      // Verify the More detail meaning for the 8(A) question.
      CommonApplicationMethods.click_Element(webDriver,
          "WOSB_Questionnaire_Page_Ans_188_N");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      logger.info("  8(a) question has been answered");
      // Locate the Third Party Certification, question1 and select yes and
      Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
      Expected_Text =  "Is the qualifying individual(s) certified as a WOSB or EDWOSB by an SBA-approved Third-Party Certifier?";
    assertEquals(Actual_Text, Expected_Text);
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_Y");
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      fillApplCreatePages.genericUploadDoc(webDriver, "Yes", file_path_abs);
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      // Locate the Change in Eligiblity question,Verify,select yes and continue.
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      logger.info("  Change Eligiblity questions have been answered");
      // Locate the LLC two question and select Yes, and upload the document
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_201_N");
      CommonApplicationMethods.setText_Element(webDriver,"WOSB_financial_Page_Ans_201_setText","Qa testing");

      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_202_N");
      CommonApplicationMethods.setText_Element(webDriver,"WOSB_financial_Page_Ans_202_setText", "Qa testing");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      logger.info("The LLC questions have been answered");
      // Locate the Citizenship & Ownership question
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_N");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      //Ownership questions.
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_N");
      // 2nd
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_N");
      // 3rd
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_N");
      CommonApplicationMethods.setText_Element(webDriver,"WOSB_financial_Page_Ans_206_setText","Qa testing");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      logger.info("the Ownership questions are answered sucessfully");
      // Management questions starts
      Actual_Text =
          webDriver.findElement(By.xpath("//div[@id='answers_oper3_q1']/fieldset/h4")).getText();
      Expected_Text =
          "Are the management and daily operations of the business controlled by the qualifying individual(s)?";
      assertEquals(Actual_Text, Expected_Text);
      // 1st question meaning.
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_N");
      // 2nd question.
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_N");
      // 3rd question meaning.
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_N");
      // 4th Question
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_N");
      // 5th Question
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_N");
      // 6th question.
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_N");

      CommonApplicationMethods.setText_Element(webDriver,"WOSB_financial_Page_Ans_212_setText","Qa testing");

     //save and conitune
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
     // SBA Exam
      CommonApplicationMethods.click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      //Review Section
        CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
        CommonApplicationMethods.accept_Optional_Alert(webDriver);
    }

    private void assertEquals(String actual_Text1, String expected_Text1) {
      // TODO Auto-generated method stub
    }
  }
