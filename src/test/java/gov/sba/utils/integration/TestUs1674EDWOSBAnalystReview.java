//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import ch.qos.logback.core.joran.conditional.ElseAction;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.pageObjetcs.cases_Page.search_Duns_And_Verify;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestUs1674EDWOSBAnalystReview extends TestCase {
  private static final Logger logger_US1674_EDWOSB =
          LogManager.getLogger(TestUs1674EDWOSBAnalystReview.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];

  }

  @Test
  public void testMainTest() throws Exception {

    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    String app_Type_Passed = "EDWOSB";
    // For WOSB and EDWOSB Active status - Create new app if not existing
    CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
    programs_Page.join_New_Program_CheckBoxes(webDriver, app_Type_Passed);

    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

    logger_US1674_EDWOSB.info(file_path_abs);
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    fillApplCreatePages.finalSignatureSubmit(webDriver);

    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

    LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
    login_Data_01.Login_With_Reference();
    // Thread.sleep(3000);
    try {

      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      CommonApplicationMethods.casesPageSearch(webDriver, duns_Number);
      logger_US1674_EDWOSB.info("Cases link is on Main Navigator is Clicked");

      List<WebElement> current_Row_EDWOSB = find_Elements_Optional(webDriver, "xpath", "//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'" + duns_Number + "')] and td[4][ not(text()) ] ]/td[1]/a");

      if (current_Row_EDWOSB.size() > 0 ){
        Assert.assertTrue(current_Row_EDWOSB.size() == 1);
        {
          current_Row_EDWOSB.get(0).click();
          assertEquals("Case Overview", find_Element(webDriver, "Case_CaseOverview_title").getText());
          assertEquals("Start a review", find_Element(webDriver, "Case_CaseOverview_startReview").getText());
          assertEquals(1, new Select(find_Element(webDriver, "Case_Current_ReviewType")).getOptions().size());
          assertEquals("Initial Review", new Select(find_Element(webDriver, "Case_Current_ReviewType")).getFirstSelectedOption().getText());
          new Select(find_Element(webDriver, "Case_Current_Reviewer")).selectByIndex(0);
          new Select(find_Element(webDriver, "Case_Current_Owner")).selectByVisibleText("Analyst3 X");
          new Select(find_Element(webDriver, "Case_Current_Supervisor")).selectByVisibleText("Analyst4 X");
          click_Element(webDriver, "Case_Submit_Button");
          click_Element(webDriver, "Case_SaveNotes_Button");
        }
      }
      else {
          List<WebElement> current_Row_EDWOSB1 = find_Elements(webDriver, "xpath", "//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'" + duns_Number + "')] and td[4][ contains(text(),'Initial Review')] ]/td[1]/a");
          current_Row_EDWOSB1.get(0).click();
          click_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav");

          List<WebElement> dropdown = new Select(find_Element(webDriver, "SBA_Assesment_Status")).getOptions();
          logger_US1674_EDWOSB.info(dropdown.get(0).getText());
          assertEquals("Confirmed", dropdown.get(0).getText());
          assertEquals("Not reviewed", dropdown.get(1).getText());
          assertEquals("Information missing", dropdown.get(2).getText());

          assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
          assertEquals("Needs further review", dropdown.get(4).getText());
          click_Element(webDriver, "SBA_Note_Link");

          setText_Element(webDriver, "SBA_Assesments_Note_Body", "Adding notes QA");

          click_Element(webDriver, "Application_Common_Save_Notes");
          click_Element(webDriver, "SBA_Question_Financial_Review_SideNav");

          // Signature Review Page
          click_Element(webDriver, "SBA_Question_Signature_Review_SideNav");

          dropdown = new Select(find_Element(webDriver, "SBA_Assesment_Status")).getOptions();
          logger_US1674_EDWOSB.info(dropdown.get(0).getText());
          assertEquals("Confirmed", dropdown.get(0).getText());
          assertEquals("Not reviewed", dropdown.get(1).getText());
          assertEquals("Information missing", dropdown.get(2).getText());
          assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
          assertEquals("Needs further review", dropdown.get(4).getText());
          click_Element(webDriver, "SBA_Note_Link");
          setText_Element(webDriver, "SBA_Assesment_Note_Body", "Adding notes QA Signature Page");

          click_Element(webDriver, "EDWOSB_Common_Page_Commit");
      }


       } catch (Exception e) {
      logger_US1674_EDWOSB.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
              new String[] {"TestUs1674EDWOSBAnalystReview", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
