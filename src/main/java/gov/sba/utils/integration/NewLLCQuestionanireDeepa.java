// Created BY Deepa Patri
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;

public class NewLLCQuestionanireDeepa {

  public static void newLLCQuestionanireDeepa(WebDriver webDriver) throws Exception {
    Logger logger = LogManager.getLogger(NewLLCQuestionanireDeepa.class.getName());
    String Actual_Text = null;
    String Expected_Text = null;

    /* Locate the 8(a) question and select No and continue. */
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
    assertEquals (Actual_Text, Expected_Text);

    /* Verify the More detail meaning for the 8(A) question. */
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_No");
    click_Element(webDriver, "Application_Common_Submit_Button");
    logger.info(" 8(a) question has been answered");

    /* Locate the Third Party Certification, question1 and select yes and */
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Is the qualifying individual(s) certified as a WOSB or EDWOSB by an SBA-approved Third-Party Certifier?";
    assertEquals(Actual_Text, Expected_Text);

    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_Y");
    FillApplCreatePages.page8aFillUp(webDriver, "Yes");

    click_Element(webDriver, "Application_Common_Submit_Button");

    /* Locate the Change in Eligiblity question,Verify,select yes and continue. */

    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y");
    click_Element(webDriver, "Application_Common_Submit_Button");
    logger.info("  Change Eligiblity questions have been answered");

    // Locate the LLC two question and select Yes, and upload the document
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_201_N");
    setText_Element(webDriver, "WOSB_financial_Page_Ans_201_setText", "Qa testing");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_202_N");
    setText_Element(webDriver, "WOSB_financial_Page_Ans_202_setText", "Qa testing");
    click_Element(webDriver, "Application_Common_Submit_Button");
    logger.info("The LLC questions have been answered");

    // Locate the Citizenship & Ownership question
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_N");
    click_Element(webDriver, "Application_Common_Submit_Button");
    // Ownership questions.
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_N");
    // 2nd
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_N");
    // 3rd
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_N");
    setText_Element(webDriver, "WOSB_financial_Page_Ans_206_setText", "Qa testing");
    click_Element(webDriver, "Application_Common_Submit_Button");
    logger.info("the Ownership questions are answered sucessfully");

    /* Management questions starts */
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_oper3_q1']/fieldset/h4")).getText();
    Expected_Text =
        "Are the management and daily operations of the business controlled by the qualifying individual(s)?";
    assertEquals(Actual_Text, Expected_Text);


    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_N");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_N");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_N");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_N");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_N");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_N");
    setText_Element(webDriver, "WOSB_financial_Page_Ans_212_setText", "Qa testing");
    click_Element(webDriver, "Application_Common_Submit_Button");
    click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N"); /* SBA Exam */
    click_Element(webDriver, "Application_Common_Submit_Button");
    click_Element(webDriver, "Application_Common_Submit_Button"); /* Review Section start */
    accept_Alert(webDriver, 8);

  }

  private static void assertEquals(String actual_Text1, String expected_Text1) {
    // TODO Auto-generated method stub
  }
}
