//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
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

import java.util.List;
import java.util.Set;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;

@Category({gov.sba.utils.integration.StableTests.class})
public class Test1235OppSuppAdminRole extends TestCase {
  private static final Logger logger_US1235 =
      LogManager.getLogger(Test1235OppSuppAdminRole.class.getName());
    // Set The variabl.es/Define
    private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 28;
  }

  @Test
  public void testMainTest() throws Exception {
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();

    // Need to submit the application in EDWosb, Wosb, MPP::
    // Log in As OppSupport Staft - validate as per the US1235 Acceptance
    // criteria on Opp Support Staft/Admin page

    try {
      duns_Number = "159165917";
      // Opp Supp Staft search vendor records.
      CommonApplicationMethods.searchDuns_Number(webDriver, "159165917");
      // Click on the Business Name - opp Supp Staft can access
      // Draft,Completed Application
      WebElement business_Name = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@id='business_search']//div[contains(@class,'business_search_result_per_entry')]//h4/a[contains(text(),'Legal Business Name')]"));
      business_Name.click();
      // Find Draft,Active,compeleted certification
      try {
        WebElement program_Table = webDriver.findElement(By.xpath("//table[@id='certifications']"));
        List<WebElement> rows_table = program_Table.findElements(By.tagName("tr"));
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
      for (String s : handle) {
        handle_01_Value = s;
      }

      // Help Link- opp supp Staff cannot have edit functionality on Help
      // Page
      WebElement Help_Link = webDriver.findElement(By.xpath("//a[@href='/help_pages']"));
      Help_Link.click();
      Boolean element_Found = false;
      try {
        Set<String> handle_02 = webDriver.getWindowHandles();

        for (String s : handle_02) {
          System.out.println(s);
          if (handle_01_Value != s) {
            webDriver.switchTo().window(s);
          }
        }
        WebElement HelpPage_Edit = webDriver.findElement(By.xpath("//a[@href='/help_pages/edit']"));
        logger_US1235.info("Opp supp Admin having Edit on Help Page-HelpPage_Edit");
        element_Found = true;
      } catch (Exception e) {
        element_Found = false;
        logger_US1235.info("Test case Passed-HelpPage_Edit funtionality");
      }

      Assert.assertEquals(element_Found, true);
    } catch (Exception e) {
      logger_US1235.info("Search TextBox is on Main Navigator is not present" + e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[] {"Test1235OppSuppAdminRole", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
