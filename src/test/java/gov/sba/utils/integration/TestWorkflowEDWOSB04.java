// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

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

import static gov.sba.automation.AssertionUtils.delete_All_Application_Draft;
import static gov.sba.automation.AssertionUtils.return_All_Applications;
import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.pageObjetcs.AnalystCasesPage.return_DunsNo_Cases_Table;
import static gov.sba.pageObjetcs.ProgramsPage.join_New_Program_CheckBoxes;
import static gov.sba.pageObjetcs.VendorDashboardPage.click_On_App_In_Vend_Dash;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

/*
 * Documentation for Workflow WorkFlows for EDWOSB - Accommodating best minimal Workflow Tests
 * TestWorkflowEDWOSB + 01. Vendor Draft Create , logout. Update draft submit , Analyst Review,
 * Supervisor Approve - 8a Yes 02. Vendor Create , Submit, Analyst Review, Supervisor Approve - 8a
 * No 03. Vendor Create , Submit, Analyst Review, Supervisor Reject - Declined 04. Vendor Create ,
 * Submit, Analyst return, Vendor Change Draft , Resubmit, Analyst Review, Supervisor Approve 05.
 * Vendor Create , Submit, Analyst return, Vendor Change Draft , Resubmit, AAnalyst Review,
 * Supervisor reject 06. Vendor Create , Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Approve 07. Vendor Create , Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Reject
 */


@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowEDWOSB04 extends TestCase {
  Logger logger = LogManager.getLogger(TestWorkflowWOSB03.class.getName());
  private static WebDriver webDriver;
  int stop_Exec = 1;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    duns_Number = "158112285";
    get_The_Row_From_Login_Data = 63;
  }

  @Test
  public void testMainTest() throws Exception {
    try {

      return_All_Applications(webDriver, 55, duns_Number);
      delete_All_Application_Draft(webDriver, 63, duns_Number);
      new LoginPageWithReference(webDriver, 63).Login_With_Reference();
      join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      new NewScorpQuestionPageDeepa(webDriver).NewScorpQuestionPageDeepa();
      new NewFinancialSectionQuestionDeepa(webDriver).NewFinancialQuestion();
      /* if (stop_Exec == 1) return; */ /* TODO DE exists on submit button */
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "LOGOUT");
      new LoginPageWithReference(webDriver, 11).Login_With_Reference();

      /* Verify Download Zip or generate Zip link displayed on vendor overview page -APP-473 */
      return_DunsNo_Cases_Table(webDriver, duns_Number, "EDWOSB");
      navigationBarClick(webDriver, "LOGOUT");

      new LoginPageWithReference(webDriver, 63).Login_With_Reference();
      /* Resubmit the application */
      click_On_App_In_Vend_Dash(webDriver, "EDWOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "LOGOUT");
      new LoginPageWithReference(webDriver, 55).Login_With_Reference();
      search_Cases_Duns_Number_Table(webDriver, duns_Number);
      click_Element(webDriver, "SBA_Legal_Businesss_Name_Link");
      click_Element(webDriver, "Application_Common_Submit_Button_Id"); Thread.sleep(2000); //Sleep neded here
      click_Element(webDriver, "Application_Common_Save_Notes_Id"); Thread.sleep(2000); //Sleep neded here
      click_Element(webDriver, "SBA_Question_Determinations_SideNav"); Thread.sleep(2000); //Sleep neded here
      click_Element(webDriver, "Application_Common_Submit_Button"); Thread.sleep(2000); //Sleep neded here
      click_Element(webDriver, "SBA_Review_Return_For_Mod");
      click_Element(webDriver, "SBA_Review_Determination_Save_Button");
      click_Element(webDriver, "SBA_Analyst_Review_Vendor_Overview");
      navigationBarClick(webDriver, "LOGOUT");
      new LoginPageWithReference(webDriver, 63).Login_With_Reference();
      click_On_App_In_Vend_Dash(webDriver, "EDWOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "LOGOUT");
      new LoginPageWithReference(webDriver, 55).Login_With_Reference();
      search_Cases_Duns_Number_Table(webDriver, duns_Number);
      click_Element(webDriver, "SBA_Legal_Businesss_Name_Link");
      click_Element(webDriver, "Application_Common_Submit_Button_Id");
      click_Element(webDriver, "SBA_Question_Determinations_SideNav");
      click_Element(webDriver, "SBA_Review_Determ_Made");
      new Select(find_Element(webDriver, "Analyst_Review_Determ_Decision")).selectByIndex(1);
      if (stop_Exec == 1) { return; } /* TODO Hard Coded Duns Number */

      click_Element(webDriver, "Application_Common_Submit_Button");
      click_Element(webDriver, "SBA_Analyst_Review_Vendor_Overview");
      assertTrue(find_Element(webDriver, "SBA_Review_Nav").getText().contains("Status: Active"));
      assertTrue(find_Element(webDriver, "SBA_Review_Nav").getText().contains("Decision: SBA Approved"));
      navigationBarClick(webDriver, "LOGOUT");

    } catch (Exception e) {
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}