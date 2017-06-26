// Created BY Deepa Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class NewScorpQuestionPageDeepa {
    private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
    WebDriver webDriver;

    public NewScorpQuestionPageDeepa(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void NewScorpQuestionPageDeepa() throws Exception {
        String actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();

        String expected_Text = "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
        assertEquals(actual_Text, expected_Text);
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_389_N");

        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        logger.info("  8(a) question has been answered");
        // Locate the Third Party Certification, question1 and select No and
        assertEquals(actual_Text, expected_Text);
        // Verify the detail meaning for the third party question.
        actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_tpc1_q1']/fieldset/p[2]")).getText();
        expected_Text = "You may self-certify for the WOSB Program through this website or you may elect to use the services of a Third-Party Certifier to demonstrate eligibility. There is no requirement to use a Third-Party Certifier. However, if you have worked with an SBA-approved Third-Party Certifier to review your business information, please upload the current Third-Party Certifier Certificate.";
        assertEquals(actual_Text, expected_Text);
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_390_Y");
        //String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
        FillApplCreatePages.page8aFillUp(webDriver, "Yes");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Locate the Changes in Eligiblity
        actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        expected_Text = "Since receiving an SBA-approved Third-Party Certification, have there been any changes in circumstances affecting the qualifying individual(s) eligibility?";
        assertEquals(actual_Text, expected_Text);
        // Verify the more detail for the Non-qualification question
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_391_Y");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Locate the Non-qualification question,Verify,select No and continue.
        logger.info("  Third Party questions have been answered");
        // Locate the Three Business Corporation and S-Corp(Stocks) question-
        // Scorp questions beings
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_393_N");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_393_setText", "QA testing");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_394_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_395_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_396_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_397_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_398_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_399_N");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_399_setText", "QA testing");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Scorp questions ends
        // CitizenShip
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_404_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // OwnerShip
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_405_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_406_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_407_N");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_407_setText", "QA testing");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Management
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_408_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_409_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_410_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_411_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_412_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_413_N");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_413_setText", "QA testing");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // SBA Exam
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_414_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Net Worth
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_415_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Adjusted Gross Income
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_416_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_417_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Assets
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_418_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_419_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_420_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        // Economic Disadvantage
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_421_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
        logger.info("EDWOSB application questions have been answered");
        // Validate that user successfully navigated to the Financial Data
        // section.
        String actual_Text49 = webDriver.findElement(By.cssSelector("h2")).getText();
        String expected_Text49 = "Financial Data";
        assertEquals(actual_Text49, expected_Text49);
        String actual_Text52 = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
        String expected_Text52 = "This section must be completed by each individual claiming economic disadvantage in connection with the 8(a) Program and/or the Women-Owned Small Business Federal Contract Program. If married, the spouse must complete this section, except when the individual and the spouse are legally separated. If separated, provide copy of separation document.";
        assertEquals(actual_Text52, expected_Text52);
        // Validate the Personal Information.
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Add_Person");
        // Verify that the section to Create new record is been seen by user and
        // enter record2.
        logger.info("the page to Create and Add new Record is Present, PASS");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_FirstName", "Deepa");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_LastName", "MaheshP");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_SSN", "123456789");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Email",
                "DeepaMaheshP@gmail.com");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_City", "Mclean");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Address", "8421 Broad Street");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_State", "Virgina");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Postal", "22190");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Co", "Usa");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_HPhone", "1234561234");
        CommonApplicationMethods.setText_Element(webDriver, "EDWOSB_Questionnaire_Page_Bphone", "1012023004");
        new Select(CommonApplicationMethods.find_Element(webDriver, "EDWOSB_Questionnaire_Page_title"))
                .selectByIndex(2);
        new Select(CommonApplicationMethods.find_Element(webDriver, "EDWOSB_Questionnaire_Page_MaritalSt"))
                .selectByIndex(2);
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Done_Button");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Ans_423_N");
        CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
    }
}
