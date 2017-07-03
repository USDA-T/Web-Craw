package gov.sba.others;


import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.non_Vendor_searchDuns_Number;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;
import static gov.sba.others.TestuserProfileSearchType.TestuserProfileSearch;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

public class TestWorkflowOppSuppAdmin extends TestCase {
  // US1235
  Logger logger = LogManager.getLogger("TestWorkflowOppSuppAdmin");
  private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    // CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 28;
    String[] details = DatabaseUtils.findUnusedDunsNumber("");
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {
    try {
      // Login as vendor admin and submit a application
      // new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      // programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      // page8aFillUp(webDriver, "Yes");
      // finalSignatureSubmit(webDriver);
      // navigationMenuClick(webDriver, "LOGOUT");
      // Login to opp support admin dashboard.
      new LoginPageWithReference(webDriver, 28).Login_With_Reference();
      // validate as per the US1235 Acceptance criteria on Opp Support Staft/Admin page
      // duns_Number = "159165917";
      non_Vendor_searchDuns_Number(webDriver, duns_Number); // Click on the Business Name - opp Supp
                                                            // Staft can access Draft,Completed
                                                            // Application
      click_Element(webDriver, "SBA_Business_Search_Business_Name");
      try { // Find Draft,Active,compeleted certification
        List<WebElement> rows_table =
            find_Elements(webDriver, "SBA_Table_My_Certifications_All_Rows", true);
        int rows_count = rows_table.size();
        for (int row = 0; row < rows_count; row++) {
          List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
          int columns_count = Columns_row.size();
          for (int column = 0; column < columns_count; column++) {
            String celtext = Columns_row.get(column).getText();
            logger.info("Cell Value Of row number " + row + " and column number " + column + " Is "
                + celtext);
          }
          logger.info("--------------------------------------------------");
        }
      } catch (Exception e) {
        logger.info("No Certifications tested - Should be fine");
      }
      // App37 -vendor support link and changing the business type
      try {
        click_Element(webDriver, "Ops_Support_Cases_Search_Vendor_Supp_Link");
        click_Element(webDriver, "Ops_Support_Change_Business_Link");
        new Select(find_Element(webDriver, "Ops_Support_Change_Business_Type")).selectByIndex(2);
        click_Element(webDriver, "Application_Common_Save_button");
        navigationBarClick(webDriver, "LOGOUT");
        new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
        // Verify the Business type is changes to Scorp[from LLC]
        // click_Element(webDriver, "Vendor_Admin_Dashboard_More_Details");
        // assertTrue(find_Element(webDriver,
        // "Vendor_Admin_Dashboard_More_Details_Id").getText().contains("S-Corporation"));
        // programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
        // navigationMenuClick(webDriver, "Programs");
        // click_Element(webDriver, "JoinNewPgm_Create_App_EDWOSB");
        // click_Element(webDriver, "JoinNewPgm_Add_Cert");
        // click_Element(webDriver, "Application_Common_Accept_Button");
        // new NewScorpQuestionPageDeepa(webDriver).NewScorpQuestionPageDeepa();
      } catch (Exception e) {
        logger.info(e.toString());
        throw e;
      }

      // US1280
      navigationMenuClick(webDriver, "LOGOUT");
      new LoginPageWithReference(webDriver, 28).Login_With_Reference();
      try {
        String sql_query =
            "select email,max_first_name,max_last_name from sbaone.users where max_id is not null and max_first_name is not null and max_last_name is not null";
        DatabaseUtils dbcall = new DatabaseUtils();
        String[][] returned_GovProfile_Rows = DatabaseUtils.queryForData(sql_query, 2, 3);
        logger.info(returned_GovProfile_Rows[1][1]);
        String Expected_Result = "Government user profile";
        TestuserProfileSearch(webDriver, returned_GovProfile_Rows[1][1],
            "OppSup_Dashboard_Govt_User_Radio_Bt", Expected_Result);
        webDriver.navigate().back();

        sql_query =
            "select email,first_name,last_name from sbaone.users where max_id is null and email like 'QA%'";
        @SuppressWarnings("static-access")
        String[][] areturned_Rows = dbcall.queryForData(sql_query, 2, 3);
        logger.info(areturned_Rows[1][1]);
        String Expected_Result1 = "Vendor user profile";
        TestuserProfileSearch(webDriver, areturned_Rows[1][1],
            "OppSup_Dashboard_Vend_User_Radio_Bt", Expected_Result1);
        webDriver.navigate().back();
      } catch (Exception e) {
        logger.info(e.toString());
      }

      // App-45 cases page
      Assert.assertEquals("Cases",
          find_Element(webDriver, "Opp_Support_Page_Cases_Link").getText());
      click_Element(webDriver, "Opp_Support_Page_Cases_Link");
      Assert.assertEquals("Cases",
          find_Element(webDriver, "Opp_Support_Admin_Page_Cases").getText());
      // US1280 -Opp Support Admin should be able to edit help page
      Set<String> handle = webDriver.getWindowHandles();
      String handle_01_Value = "";
      Assert.assertTrue(handle.size() == 1);
      for (String s : handle)
        handle_01_Value = s;
      // Help Link- opp supp Staff cannot have edit functionality on Help Page
      click_Element(webDriver, "Main_Page_Help_Page_Link");
      Boolean element_Found = false;
      try {
        Set<String> handle_02 = webDriver.getWindowHandles();
        for (String s : handle_02) {
          System.out.println(s);
          if (handle_01_Value != s) {
            webDriver.switchTo().window(s);
          }
        }
        find_Element(webDriver, "Main_Page_Help_Page_Link_Edit");
        logger.info("Opp supp Admin having Edit on Help Page-HelpPage_Edit");
        element_Found = true;
      } catch (Exception e) {
        element_Found = false;
        logger.info("Test case Passed-HelpPage_Edit funtionality");
      }

      Assert.assertEquals(element_Found, true);


    } catch (Exception e) {
      logger.info("help page  is edit not present" + e.toString());
      take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestWorkflowOppSuppAdmin", "Exception"});
      throw e;
    }

  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
