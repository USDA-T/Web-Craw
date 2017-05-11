// TS Created By _deepa patri
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestApp187OppSupport extends TestCase {

  // Set The variabl.es/Define
  private static WebDriver webDriver;
  private static final Logger TestApp187OppSupport =
      LogManager.getLogger(TestApp187OppSupport.class.getName());
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {

    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    get_The_Row_From_Login_Data = 27;
  }

  @Test
  public void testMainTest() throws Exception {
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();

    try {
      // US1280- Search Government ;
      // Get Email,First,Name,Last from the Db to use as serach term in UI
      // Connect SBAONE QA DB -to get data from DB
      // TestApp187OppSupport.info(returned_GovProfile_Rows[1][1]);
      // pass Government/vendor profile criteria
      String Expected_Result = "Government user profile";
      // TestuserProfileSearchType.TestuserProfileSearch(webDriver,
      // returned_GovProfile_Rows[1][1],Gov_Radio_xpath,Expected_Result);
      TestuserProfileSearchType.TestuserProfileSearch(webDriver, "Analyst",
          "Opp_Support_Page_Govt_Profile_Search", Expected_Result);
      webDriver.navigate().back();
      // US1280- Search Vendor ; //userprofile search function pass
      // Government/vendor profile criteria
      Expected_Result = "Vendor user profile";
      TestuserProfileSearchType.TestuserProfileSearch(webDriver, "QA",
          "Opp_Support_Page_Vendor_Prof_Search", Expected_Result);
      // Click organization
      webDriver.findElement(By.xpath("//table/tbody/tr/td/a[contains(text(),'Business Name')]"))
          .click();
      Expected_Result = "Vendor Support";
      String Actual_Result = webDriver
          .findElement(By.xpath("//div[@id='business_search']/div/div/a")).getText().toString();
      assertEquals(Expected_Result, Actual_Result);

    } catch (Exception e) {
      TestApp187OppSupport.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestApp187OppSupport", "Exception"});
      TestApp187OppSupport.info("test failed ");
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}
