//TS created By Deepa
package gov.sba.utils.integration;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import gov.sba.automation.CommonApplicationMethods;
//*Deepa-
public class NewFinancialSectionQuestionDeepa {
  private static final Logger logger = LogManager.getLogger(FinancialSectionPage.class.getName());
  private WebDriver webDriver;
  int get_The_Row_From_Login_Data;


  public NewFinancialSectionQuestionDeepa(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void NewFinancialQuestion() throws Exception {
    // Locate section for 'Cash on Hand' enter all valid data as required.
    CommonApplicationMethods.sendKeys_Element(webDriver,"EDWOSB_financial_Page_Ans_363_setText","01052017") ;
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_364_setText","2000") ;
    // Locate the Savings Account(s) Balance Search box
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_365_setText","5000") ;
    // Locate the Checking Account(s) Balance Search box
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_366_setText","5000") ;
    // Locate the Continue button and click on it to continue.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Locate section for Other Source of Income enter all valid data as
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_367_setText","7000") ;
    // Locate the Other Income search box and enter Other Income.
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_368_setText","0"); ;
    // Locate the applicant Business Type and enter amount of applicant
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_369_setText","800000") ;
    // Locate the applicant equity in other firm and enter applicant
    // business equity.
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_370_setText","50000") ;
      // Locate the continue button and click on it to continue.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Locate and NO for question 'Do you have any notes receivable from
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_371_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Select NO for the two question on Retirement Accounts.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_372_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_373_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Locate and select yes for question, Do you have loan against a life
    // Life insurance.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_374_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_375_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Select No for the Stock and Bonds Section.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_377_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Select No for Real Estate - Primary Residence Section questions.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_378_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    //Select No for Real Estate - Other
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_380_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    //Personal Property.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_382_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_383_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Notes Payable and Other Liabilities
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_384_N");
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Assesses Taxes
     CommonApplicationMethods.click_Element(webDriver, "EDWOSB_financial_Page_Ans_385_N");
    // Locate and click on the continue button.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Locate the next 3 search boxes for Adjusted Gross Income and enter
    // valid data.
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_386_SetText","1000") ;
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_387_SetText","2000") ;
    CommonApplicationMethods.setText_Element(webDriver,"EDWOSB_financial_Page_Ans_388_SetText","3000") ;
    // Locate and click on the continue button.
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Check Personal Summary page Reached -Click on the save and continue button.
//    webDriver.findElement(By.xpath("//a[ contains (@id,'personal_summary')]")).getText();
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    //Privacy Statement
    CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    // Verify that user is being navigated to the Review Page.
    CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
    CommonApplicationMethods.accept_Alert(webDriver);

  }

  private void assertEquals(String actual_Text1, String expected_Text1) {
    // TODO Auto-generated method stub
  }
}