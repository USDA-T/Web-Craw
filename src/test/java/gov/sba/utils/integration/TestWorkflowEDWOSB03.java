// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.search_Cases_Duns_Number_Table;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;
import static gov.sba.automation.DatabaseUtils.findUnusedDunsNumber;
import static gov.sba.pageObjetcs.ProgramsPage.join_New_Program_CheckBoxes;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

/*
 * Documentation for Workflow WorkFlows for EDWOSB - Accommodating best minimal Workflow Tests
 * TestWorkflowEDWOSB + 01. Vendor Draft Create , logout. Update draft submit , Analyst Review,
 * Supervisor Approve - 8a Yes 02. Vendor Create , Submit, Analyst Review, Supervisor Approve - 8a
 * No 03. Vendor Create , Submit, Analyst Review, Supervisor reject-Declined 04. Vendor Create ,
 * Submit, Analyst return, Vendor Change Draft , Resubmit, Analyst Review, Supervisor Approve 05.
 * Vendor Create , Submit, Analyst return, Vendor Change Draft , Resubmit, AAnalyst Review,
 * Supervisor reject 06. Vendor Create , Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Approve 07. Vendor Create , Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Reject
 */


@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowEDWOSB03 extends TestCase {
  Logger logger = LogManager.getLogger(TestWorkflowEDWOSB03.class.getName());
  WebDriver webDriver;
  int stop_Exec = 1;
  String duns_Number, email, password, typ_App;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    String[] details = findUnusedDunsNumber("");
    email = details[0];
    password = details[1];
    duns_Number = details[2];
    /* email = "qa108@mailinator.com";password = "password";duns_Number = "384141756"; */
  }

  @Test
  public void testMainTest() throws Exception {
    try {
      /*
       * return_All_Applications(webDriver, 55, duns_Number);
       * delete_All_Application_Draft(webDriver, email, password, duns_Number);
       */
      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "LOGOUT");
      new LoginPageWithReference(webDriver, 11).Login_With_Reference();
      navigationBarClick(webDriver, "Cases");
      search_Cases_Duns_Number_Table(webDriver, duns_Number);
      click_Element(webDriver, "SBA_Legal_Business_Name_Link");
      assertEquals("Case Overview", find_Element(webDriver, "Case_CaseOverview_title").getText());
      assertEquals("Open application summary",
          find_Element(webDriver, "SBA_Case_Overview_Open_Application_Summary").getText());
      assertEquals("Return to Vendor",
          find_Element(webDriver, "SBA_Case_Overview_Return_to_vendor").getText());
      click_Element(webDriver, "Case_Submit_Button");

      /* Verify the Question review page */
      assertNotNull(find_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav", true));
      List<WebElement> dropdown =
          new Select(find_Element(webDriver, "SBA_Assesment_Status")).getOptions();
      logger.info(dropdown.get(0).getText());
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());

      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver, "SBA_Note_Link");
      setText_Element(webDriver, "SBA_Assesments_Note_Body", "Adding notes QA");
      click_Element(webDriver, "Application_Common_Save_Notes_Id");

      /* For Wosb Financial review link not exist */
      assertNotNull(find_Element(webDriver, "SBA_Question_Financial_Review_SideNav", true));

      // CommonApplicationMethods.click_Element(webDriver, "SBA_WOSB_Table_Link");

      /* Signature page */
      assertNotNull(find_Element(webDriver, "SBA_Question_Signature_Review_SideNav", true));
      dropdown =
          new Select(find_Element(webDriver, "SBA_Question_Assesment_Status_Options")).getOptions();
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());
      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver, "SBA_Note_Link");
      setText_Element(webDriver, "SBA_Assesment_Note_Body", "Adding notes QA Signature Page");
      click_Element(webDriver, "SBA_Common_Page_Commit");

      /* Determination page */
      assertNotNull(find_Element(webDriver, "SBA_Question_Determinations_SideNav", true));
      assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Review_Started").getText(),
          "Review Started");
      assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Return_For_Mod").getText(),
          "Return for Modification");
      assertEquals(find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_Eligibile")
          .getText(), "Recommend Eligible");
      assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_InEligibile")
              .getText(),
          "Recommend Ineligible");
      setText_Element(webDriver, "SBA_Assesment_Note_Body", "Qa Test");

      /* Verify on Analyst Detremination page -Determination Made, Decision not displayed */
      assertNull(find_Element(webDriver, "SBA_Review_Determ_Made", true));
      assertNull(find_Element(webDriver, "SBA_Review_Determ_Decision", true));

      /* TODO DE exists on submit button */
      if (stop_Exec == 1) {
        return;
      }

      click_Element(webDriver, "Application_Common_Submit_Button");

      click_Element(webDriver, "SBA_Question_Determinations_SideNav");

      assertTrue(find_Element(webDriver, "SBA_Review_Nav").getText().contains("Status: Active"));
      assertTrue(
          find_Element(webDriver, "SBA_Review_Nav").getText().contains("Decision: Self Certified"));

      navigationBarClick(webDriver, "LOGOUT");
      /* Supervisor Flow - Approve */
      new LoginPageWithReference(webDriver, 55).Login_With_Reference();
      search_Cases_Duns_Number_Table(webDriver, duns_Number);
      click_Element(webDriver, "SBA_Legal_Business_Name_Link");
      click_Element(webDriver, "SBA_Question_Determinations_SideNav");

      /* Verify on Analyst Detremination page -Determination Made, Decision not displayed */
      click_Element(webDriver, "SBA_Review_Determ_Made");
      assertNotNull(find_Element(webDriver, "Analyst_Review_Determ_Decision", true));
      new Select(find_Element(webDriver, "Analyst_Review_Determ_Decision")).selectByIndex(0);
      click_Element(webDriver, "Application_Common_Submit_Button");
      assertTrue(
          find_Element(webDriver, "SBA_Review_Nav").getText().contains("Status: Ineligible"));
      assertTrue(
          find_Element(webDriver, "SBA_Review_Nav").getText().contains("Decision: SBA Declined"));


    } catch (Exception e) {
      logger.info("Search TextBox is on Main Navigator is not present" + e.toString());
      take_ScreenShot_TestCaseName(webDriver, new String[] {"TestWorkflowEDWOSB03", "Exception"});
      throw e;

    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
