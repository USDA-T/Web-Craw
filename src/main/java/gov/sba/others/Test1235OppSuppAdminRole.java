// TS_Created_By_Deepa_Patri
package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.non_Vendor_searchDuns_Number;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.ProgramsPage;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})
public class Test1235OppSuppAdminRole extends TestCase {
  private static final Logger logger_US1235 =
      LogManager.getLogger(Test1235OppSuppAdminRole.class.getName());
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
  public void test1235OppSuppAdminRole() throws Exception {
    try {
      // Login as vendor admin and submit a application
      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      ProgramsPage.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "LOGOUT");
      // Login to opp support admin dashboard.
      new LoginPageWithReference(webDriver, 28).Login_With_Reference();
      // validate as per the US1235 Acceptance criteria on Opp Support Staft/Admin page
      duns_Number = "159165917"; // Opp Supp Staft search vendor records.
      non_Vendor_searchDuns_Number(webDriver, duns_Number); // Click on the Business Name - opp Supp
                                                            // Staft can access Draft,Completed
                                                            // Application
      click_Element(webDriver, "SBA_Business_Search_Business_Name");
      try { // Find Draft,Active,compeleted certification
        List<WebElement> rows_table = find_Elements(webDriver, "SBA_Table_My_Certifications", true);
        int rows_count = rows_table.size();
        for (int row = 0; row < rows_count; row++) {
          List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
          int columns_count = Columns_row.size();
          for (int column = 0; column < columns_count; column++) {
            String celtext = Columns_row.get(column).getText();
            logger_US1235.info("Cell Value Of row number " + row + " and column number " + column
                + " Is " + celtext);
          }
          logger_US1235.info("--------------------------------------------------");
        }
      } catch (Exception e) {
        logger_US1235.info("No Certifications tested - Should be fine");
      }
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
        logger_US1235.info("Opp supp Admin having Edit on Help Page-HelpPage_Edit");
        element_Found = true;
      } catch (Exception e) {
        element_Found = false;
        logger_US1235.info("Test case Passed-HelpPage_Edit funtionality");
      }

      Assert.assertEquals(element_Found, true);

    } catch (Exception e) {
      logger_US1235.info("Search TextBox is on Main Navigator is not present" + e.toString());
      take_ScreenShot_TestCaseName(webDriver,
          new String[] {"Test1235OppSuppAdminRole", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
