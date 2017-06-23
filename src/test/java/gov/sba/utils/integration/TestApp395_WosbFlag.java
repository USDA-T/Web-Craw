// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.accept_Alert;
import static gov.sba.automation.CommonApplicationMethods.checkApplicationExists;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.AssertionUtils;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })

public class TestApp395_WosbFlag extends TestCase {

    private static final Logger logger_TestApp395 = LogManager.getLogger(TestApp395_WosbFlag.class.getName());
    // Get the questions names for which Prepopulate flag set to true
    // Start create New Wosb/Edwosb application
    // Check the Answers are prepopulating with previous answers.
    private static WebDriver webDriver;
    String duns_Number, email, password;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.get_Stop_Execution_Flag();
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

            // Return the Applicatiom;
            if (checkApplicationExists(webDriver, "WOSB", "Active")) {
                CommonApplicationMethods.navigationMenuClick(webDriver, "LOGOUT");
                AssertionUtils.return_all_Applications(webDriver, 11, "159165917");
                login_Data = new LoginPageWithReference(webDriver, 10);
                login_Data.Login_With_Reference();
            }
            CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
            CommonApplicationMethods.deleteApplication(webDriver, "Wosb", "Draft");

            // start New WOSB Applicatiom

            programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
            NewLLCQuestionanireDeepa NewLLCQuestionanireDeepa = new NewLLCQuestionanireDeepa();
            NewLLCQuestionanireDeepa.NewLLCQuestionanireDeepa(webDriver);
            fillApplCreatePages.finalSignatureSubmit(webDriver);

            // Return the Applicatiom;
            CommonApplicationMethods.navigationMenuClick(webDriver, "LOGOUT");
            AssertionUtils.return_all_Applications(webDriver, 11, "159165917");
            // Delete All the Draft Applications
            login_Data = new LoginPageWithReference(webDriver, 10);
            login_Data.Login_With_Reference();
            CommonApplicationMethods.deleteApplication(webDriver, "wosb", "Draft");

            // start New WOSB Applicatiom again - to check the prepopulation

            programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");

            String checkBoxElement = find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_No")
                    .getAttribute("outerHTML");
            assertFalse(checkBoxElement.toLowerCase().contains("checked"));
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_188_No");
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            logger_TestApp395.info(" 8(a) question assert not being prepopulated");

            checkBoxElement = find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_No").getAttribute("outerHTML");
            assertFalse(checkBoxElement.toLowerCase().contains("checked"));
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_189_Y");
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

            checkBoxElement = find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y").getAttribute("outerHTML");
            assertFalse(checkBoxElement.toLowerCase().contains("checked"));
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_190_Y");
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_201_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_202_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_203_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));

            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_204_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_205_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_206_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));

            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_207_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_208_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_209_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));
            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_210_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));

            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_211_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));

            assertTrue(find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_212_N").getAttribute("outerHTML")
                    .toLowerCase().contains("checked"));

            click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");

            checkBoxElement = find_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N").getAttribute("outerHTML");
            assertFalse(checkBoxElement.toLowerCase().contains("checked"));
            click_Element(webDriver, "WOSB_Questionnaire_Page_Ans_213_N");
            click_Element(webDriver, "Application_Common_Submit_Button");

            // Review Section
            click_Element(webDriver, "Application_Common_Submit_Button");
            accept_Alert(webDriver, 6);
            fillApplCreatePages.finalSignatureSubmit(webDriver);
            // Check if the Active certificate is exist-Then Return by analyst
            checkApplicationExists(webDriver, "wosb", "Active");
            navigationMenuClick(webDriver, "LOGOUT");
            AssertionUtils.return_all_Applications(webDriver, 11, "159165917");
        } catch (Exception e) {
            logger_TestApp395.info(e.toString());
            take_ScreenShot_TestCaseName(webDriver, new String[] { "TestApp395_WosbFlag", "Exception" });
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
