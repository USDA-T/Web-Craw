//Ts Created BY Deepa Patri
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;

public class NewAddBusinessPartern413Deepa {
    private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
    int get_The_Row_From_Login_Data;
    private WebDriver webDriver;

    public NewAddBusinessPartern413Deepa(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void NewFinancialQuestion(String Firstname, String lastname, String Ssn, String Email, String Address,
            String State, String Postalcode, String HPhone, String BPhone, String City, String Co) throws Exception {
        // Validate that user successfully navigated to the Financial Data
        // section.
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_FinancialPage_Link");
        // Validate the Personal Information.
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Add_Person");
        // Verify that the section to Create new record is been seen by user and
        // enter record2.
        logger.info("the page to Create and Add new Record is Present, PASS");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_FirstName", Firstname);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_LastName", lastname);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_SSN", Ssn);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Email", Email);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_City", City);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Address", Address);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_State", State);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Postal", Postalcode);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Co", Co);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_HPhone", HPhone);
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Bphone", BPhone);

        new Select(CommonApplicationMethods.find_Element(webDriver, "EDWOSB_Questionnaire_Page_title"))
                .selectByIndex(2);
        new Select(CommonApplicationMethods.find_Element(webDriver, "EDWOSB_Questionnaire_Page_MaritalSt"))
                .selectByIndex(2);

        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Done_Button");
    }

    private void assertEquals(String actual_Text1, String expected_Text1) {
        // TODO Auto-generated method stub
    }
}
