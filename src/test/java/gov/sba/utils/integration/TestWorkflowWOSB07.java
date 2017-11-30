//Ts Created by Deepa patri
package gov.sba.utils.integration;

import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.automation.DatabaseUtils.findUnusedDunsNumber;
import static gov.sba.pageObjetcs.ProgramsPage.join_New_Program_CheckBoxes;
import static gov.sba.pageObjetcs.VendorDashboardPage.verify_Row_In_A_Table_And_Return;
import static gov.sba.pageObjetcs.WosbCorpScorpQuestionnairePage.*;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
/*
 * Documentation for Workflow WorkFlows for EDWOSB - Accommodating best minimal Workflow Tests
 * TestWorkflowEDWOSB + 07. - 8a No, Vendor Create , Submit, Analyst Review, Supervisor Approve - 8a
 * 07. Vendor Create , Submit, Annual Review, auto pre populate{App303&304], ReSubmit, Supervisor Review,
 * Supervisor approve
 */

@Category({gov.sba.utils.integration.StableTests.class})
    public class TestWorkflowWOSB07 extends TestCase {
        private static final Logger
            logger = LogManager.getLogger(gov.sba.utils.integration.TestWorkflowWOSB07.class.getName());
        private static WebDriver webDriver;
        int stop_Exec = 1;
        String duns_Number, email, password;

        @Before
        public void setUp() throws Exception {
            get_Stop_Execution_Flag();
            clear_Env_Chrome();
            logger.info("Set as head");
            //TestHelpers.set_Headless();
            webDriver = set_Timeouts(TestHelpers.getDefaultWebDriver());
            webDriver.get(TestHelpers.getBaseUrl());
            String[] details = findUnusedDunsNumber("corp");
            email = details[0];
            password = details[1];
            duns_Number = details[2];
    /* duns_Number = "376736143";get_The_Row_From_Login_Data = 64; */

        }

        @Test
        public void testWorkflowEDWOSB07() throws Exception {
            try {
                new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
                join_New_Program_CheckBoxes(webDriver, "WOSB");
      /* Wosb questionnaire Coperation */
                wosb_Questionnaire_8a_Page(webDriver, "no");
                wosb_Questionnaire_ThridParty_Page(webDriver, "yes");
                wosb_Questionnaire_ChangeEligiblity_Page(webDriver, "yes");
                WOSB_Questionnaire_Cooperation_Scorp_Page(webDriver, "yes", "yes", "yes", "yes", "yes",
                    "yes");
                wosb_Questionnaire_CitizenShip_Page(webDriver, "yes");
                WOSB_Questionnaire_cooperation_Ownership_Page(webDriver, "yes", "yes", "yes");
                WOSB_Questionnaire_cooperation_Management_Page(webDriver, "yes", "yes", "yes", "yes", "yes",
                    "yes");
                wosb_Questionnaire_SBAExam_Page(webDriver, "yes");
                wosb_Review_Page(webDriver);
                finalSignatureSubmit(webDriver);
      /* if (stop_Exec == 1) {return;} /* TODO Working On */
                String sql_Q_01 =
                    "update sbaone.certificates set expiry_date = CURRENT_TIMESTAMP where organization_id = (select id from sbaone.organizations where type = 'Certificate::Wosb' and duns_number = '"
                        + duns_Number + "')";
                new DatabaseUtils().executeSQLScript(sql_Q_01);
      /*
       * check the status of the certificate to Expired verify the Renewal link, submit new renew
       * application
       */
                webDriver.navigate().refresh();
                assertNotNull(verify_Row_In_A_Table_And_Return(webDriver,
                new String[] {"WOSB Self-Certification", "Certificate", "Active", "", "", "", "Renew"}));

      /* Create new renew application - submit App303,304*/
                click_Element(webDriver, "SBA_WOSB_Table_Renew_Link");
                click_Element(webDriver, "Application_Common_Accept_Button");
                wosb_Questionnaire_8a_Page(webDriver, "assert_no");
                wosb_Questionnaire_ThridParty_Page(webDriver, "assert_yes");
                wosb_Questionnaire_ChangeEligiblity_Page(webDriver, "assert_yes");
                WOSB_Questionnaire_Cooperation_Scorp_Page(webDriver, "assert_yes", "assert_yes", "assert_yes", "assert_yes", "assert_yes",
                    "assert_yes");
                wosb_Questionnaire_CitizenShip_Page(webDriver, "assert_yes");
                WOSB_Questionnaire_cooperation_Ownership_Page(webDriver, "assert_yes", "assert_yes", "assert_yes");
                WOSB_Questionnaire_cooperation_Management_Page(webDriver, "assert_yes", "assert_yes", "assert_yes", "assert_yes", "assert_yes",
                    "assert_yes");
                wosb_Questionnaire_SBAExam_Page(webDriver, "assert_yes");
                wosb_Review_Page(webDriver);
                finalSignatureSubmit(webDriver);
                navigationMenuClick(webDriver, "LOGOUT");
       /*Start Review process */
                new LoginPageWithReference(webDriver, 55).Login_With_Reference();
                search_Cases_Duns_Number_Table(webDriver, duns_Number);
                click_Element(webDriver, "SBA_Legal_Business_Name_Link");
                click_Element(webDriver, "Application_Common_Submit_Button_Id");
                click_Element(webDriver, "Application_Common_Save_Notes_Id");
                click_Element(webDriver, "Application_Common_Submit_Button");
                click_Element(webDriver, "SBA_Review_Determination_Save_Button");
                click_Element(webDriver, "SBA_Analyst_Review_Vendor_Overview");
                click_Element(webDriver, "SBA_Review_Determ_Made");
                new Select(find_Element(webDriver, "Analyst_Review_Determ_Decision")).selectByIndex(1);
                click_Element(webDriver, "Application_Common_Submit_Button");
                assertTrue(
                    find_Element(webDriver, "SBA_Review_Nav").getText().contains("Status: Active"));
                if (stop_Exec == 1) {
                    return;
                } /* TODO DE APP-1499 exist */
                assertTrue(
                    find_Element(webDriver, "SBA_Review_Nav").getText().contains("Decision: SBA Approved"));
                click_Element(webDriver, "SBA_Analyst_Review_Vendor_Overview");
                navigationBarClick(webDriver, "LOGOUT");
            } catch (Exception e) {
                logger.info("Search TextBox is on Main Navigator is not present" + e.toString());
                take_ScreenShot_TestCaseName(webDriver, new String[] {"TestWorkflowWOSB07", "Exception"});
            }
        }

        @After
        public void tearDown() throws Exception {
            webDriver.quit();
        }
    }
