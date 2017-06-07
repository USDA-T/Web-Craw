//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestAnalystReview {
  private static final Logger TestAnalystReview =
      LogManager.getLogger(TestAnalystReview.class.getName());
    // Set The variabl.es/Define
    WebDriver webDriver;
  int get_The_Row_From_Login_Data;
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
    get_The_Row_From_Login_Data = 11;
  }

  @Test
  public void testMainTest() throws Exception {
    try {
      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();

        programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");

      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

      TestAnalystReview.info(file_path_abs);
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      fillApplCreatePages.finalSignatureSubmit(webDriver);

      CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

      LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
      login_Data_01.Login_With_Reference();

      // Click on Case Link on main navigator
      // //Come Back Later
      // WebElement Cases_Link =
      // webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
      // Cases_Link.click();
      // AnalystReviewPage TestReviewProcess = new AnalystReviewPage();
      //
      // TestReviewProcess.TestReviewDriver(webDriver, duns_Number,
      // "EDWOSB", "Initial Review", "Analyst2 X",
      // "Analyst3 X", "Analyst4 X");
      // TestReviewProcess.testUnderReview();
      // webDriver.navigate().back();

    } catch (Exception e) {
      TestAnalystReview.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestAnalystReview", "Exception"});
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
