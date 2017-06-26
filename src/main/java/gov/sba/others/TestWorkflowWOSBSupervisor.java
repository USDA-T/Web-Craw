// TS_Created_By_Deepa_Patri
package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.casesPageSearch;
import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.ProgramsPage;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

// Verify US1674
@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowWOSBSupervisor extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestWorkflowWOSBSupervisor.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    // CommonApplicationMethods.focus_window()
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];

  }

  @Test
  public void testMainTest() throws Exception {

    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();
    String app_Type_Passed = "WOSB";
    // For WOSB and EDWOSB Active status - Create new app if not existing
    ProgramsPage.join_New_Program_CheckBoxes(webDriver, app_Type_Passed);
    // String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    page8aFillUp(webDriver, "Yes");
    finalSignatureSubmit(webDriver);

    navigationMenuClick(webDriver, "Logout");

    LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 55);
    login_Data_01.Login_With_Reference();
    // Thread.sleep(3000);
    try {

      navigationBarClick(webDriver, "Cases");
      casesPageSearch(webDriver, duns_Number);
      logger.info("Cases link is on Main Navigator is Clicked");

      List<WebElement> current_Row_WOSB = find_Elements(webDriver, "xpath",
          "//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'"
              + duns_Number + "')]  ]/td[1]/a",
          true);
      // List<WebElement> current_Row_WOSB = find_Elements(webDriver, "xpath",
      // "//div[@id='table-search']/table/tbody/tr[ td[position()=2]/a[contains(text(),'146323332')]
      // ]/td[1]/a");

      current_Row_WOSB.get(0).click();

      assertEquals("Case Overview", find_Element(webDriver, "Case_CaseOverview_title").getText());
      // assertEquals("Start a review",
      // find_Element(webDriver,"Case_CaseOverview_startReview").getText());
      // click_Element(webDriver, "SBA_Business_Search_Business_Name");
      assertEquals("Open application summary",
          find_Element(webDriver, "SBA_Case_Overview_Open_Application_Summary").getText());
      assertEquals("Return to Vendor",
          find_Element(webDriver, "SBA_Case_Overview_Return_to_vendor").getText());
      click_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav");

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

      // For Wosb Financial review link not exist
      assertNull(find_Element(webDriver, "SBA_Question_Financial_Review_SideNav", true));
      // CommonApplicationMethods.click_Element(webDriver, "SBA_WOSB_Table_Link");
      // Signature page
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
      // Determination page
      assertNotNull(find_Element(webDriver, "SBA_Question_Determinations_SideNav", true));
      Assert.assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Review_Started").getText(),
          "Review Started");
      Assert.assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Return_For_Mod").getText(),
          "Return for Modification");
      Assert.assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_Eligibile")
              .getText(),
          "Recommend Eligible");
      Assert.assertEquals(
          find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_InEligibile")
              .getText(),
          "Recommend Ineligible");

      setText_Element(webDriver, "SBA_Assesment_Note_Body", "Qa Test");
      // Verify on Analyst Detremination page -Determination Made, Decision not displayed
      assertNotNull(find_Element(webDriver, "SBA_Review_Determ_Made", true));
      click_Element(webDriver, "SBA_Review_Determ_Made");
      assertNotNull(find_Element(webDriver, "Analyst_Review_Determ_Decision", true));
      new Select(find_Element(webDriver, "Analyst_Review_Determ_Decision")).selectByIndex(0);
      click_Element(webDriver, "Application_Common_Submit_Button");

      // click_Element(webDriver, "SBA_Question_Determinations_SideNav");
      // click_Element(webDriver, "SBA_Review_Return_For_Mod");
      // click_Element(webDriver, "SBA_Signature_Review_Save_Continue");

    } catch (Exception e) {
      logger.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestWorkflowWOSBSupervisor", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
