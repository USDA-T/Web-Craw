//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.pageObjetcs.programs_Page.join_New_Program_CheckBoxes;
import static gov.sba.utils.integration.fillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.fillApplCreatePages.page8aFillUpDunsNo;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestApp170LinkDunsNoMpp extends TestCase {
  private static final Logger TestApp170LinkDunsNo =
          LogManager.getLogger(TestApp170LinkDunsNoMpp.class.getName());
  // Set The variabl.es/Define
  WebDriver webDriver;
  String    duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {
    try {
      // __________________________________________________________________________________
      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();

      join_New_Program_CheckBoxes(webDriver, "MPP");
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      TestApp170LinkDunsNo.info(file_path_abs);

      page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, duns_Number);
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "Logout");

      // Click on Case Link on main navigator-- Mpp Analyst
      new LoginPageWithReference(webDriver, 29).Login_With_Reference();

      String typ_App_Passed = "MPP";
      navigationMenuClick(webDriver, "Cases");

      if (!webDriver.getPageSource().contains("No results found")) {
        // All cases page for Mpp Analyst
        String xpath_Value =
                "//div[@id='table-search']/table/tbody/tr[ " + "td/a[contains(text(),'" + duns_Number
                        + "')]	and " + "td[position()=3 and (text() = '" + typ_App_Passed + "')]" + "]";
        List<WebElement> current_Row = webDriver.findElements(By.xpath(xpath_Value));
        // Vendor Overview page should display clicking on Duns Number
        // Link
        if (current_Row.size() > 0) {
          current_Row.get(0).findElement(By.xpath("td[2]/a")).click();
          WebElement asset_Exists = webDriver
                  .findElement(By.xpath("//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'"
                          + duns_Number + "')] ]"));
          assertEquals(asset_Exists.getText(), "DUNS:" + duns_Number);

        }
        navigationMenuClick(webDriver, "Logout");
      }

    } catch (Exception e) {
      TestApp170LinkDunsNo.info(e.toString());
      take_ScreenShot_TestCaseName(webDriver, new String[]{"TestApp170LinkDunsNoMpp", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
