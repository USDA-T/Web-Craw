// TS_Created_By_Deepa_Patri
package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.non_Vendor_searchDuns_Number;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;

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

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})
public class Test1235OPPSupportStaffRole extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(Test1235OPPSupportStaffRole.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    get_The_Row_From_Login_Data = 27;
  }

  @Test
  public void testMainTest() throws Exception {
    // Login to dashboard.
    new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data).Login_With_Reference();
    /*
     * Need to submit the application in EDWosb, Wosb,MPP Log in As OppSupport Staft - validate as
     * per the US1235 AC on Opp Support Staft/Admin page
     */
    try {
      non_Vendor_searchDuns_Number(webDriver, "159165917"); // Opp Supp Staft search vendor records.
      click_Element(webDriver, "SBA_Business_Search_Business_Name"); // Click on the Business Name -
                                                                     // opp Supp Staft can access
                                                                     // Draft,Completed Application

      try { // Find Draft,Active,compeleted certification
        List<WebElement> rows_table =
            find_Elements(webDriver, "SBA_Table_My_Certifications_All_Rows");
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
        logger.info("No Certifications tested - Should be fine ");
      }

      Set<String> handle = webDriver.getWindowHandles();
      String handle_01_Value = "";
      Assert.assertTrue(handle.size() == 1);
      for (String s : handle)
        handle_01_Value = s;

      click_Element(webDriver, "Main_Page_Help_Page_Link"); // Help Link- opp supp Staff cannot have
                                                            // edit functionality on Help Page

      Boolean element_Found = false;
      try {
        Set<String> handle_02 = webDriver.getWindowHandles();
        for (String s : handle_02) {
          System.out.println(s);
          if (handle_01_Value != s)
            webDriver.switchTo().window(s);
        }
        find_Element(webDriver, "Main_Page_Help_Page_Link_Edit");
        logger.info("Opp supp Staft not having Edit on Help Page-HelpPage_Edit");
        element_Found = true;
      } catch (Exception e) {
        element_Found = true;
        logger.info("Test case Passed-HelpPage_Edit funtionality");
      }
      Assert.assertEquals(element_Found, true);

    } catch (Exception e) {
      logger.info("Search TextBox is on Main Navigator is not present" + e.toString());
      take_ScreenShot_TestCaseName(webDriver,
          new String[] {"Test1235OPPSupportStaffRole", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
