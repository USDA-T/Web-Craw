//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;
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

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.click_Element;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestApp40AndApp190EDWosb extends TestCase {
  private static final Logger TestApp40AndApp190 =
          LogManager.getLogger(TestApp40AndApp190Wosb.class.getName());
  // Set The variables/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  public void run_This_app(String app_Type_Passed, String duns_No_Passed) throws Exception {

    // Delete if Draft
    TestApp40AndApp190.info(app_Type_Passed);

    // Check Pending for MPP
    if (app_Type_Passed.toLowerCase().trim().contentEquals("mpp")) {

      programs_Page.join_New_Program_CheckBoxes(webDriver, app_Type_Passed);
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      TestApp40AndApp190.info(file_path_abs);
      fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, "148832876");
      fillApplCreatePages.finalSignatureSubmit(webDriver);

      CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
      LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 29);
      login_Data.Login_With_Reference();
    } else {
      // For WOSB and EDWOSB Active status - Create new app if not existing

      programs_Page.join_New_Program_CheckBoxes(webDriver, app_Type_Passed);
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      TestApp40AndApp190.info(file_path_abs);
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
      LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 11);
      login_Data.Login_With_Reference();
    }

    String xpath = "";

    CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
    CommonApplicationMethods.casesPageSearch(webDriver, duns_Number);

    // Seperate XPaths for Each Type of Application
    if (app_Type_Passed.toLowerCase().trim().contentEquals("edwosb")) {
      xpath = "//div[@id='table-search']/table/tbody/tr[ " + "td[position()=2]/a[contains(text(),'"
              + duns_No_Passed + "')]" + " and " + "td[position()=3 and contains(text(),'" + "EDWOSB"
              + "')]	" + "]";
    }
    if (app_Type_Passed.toLowerCase().trim().contentEquals("wosb")) {
      xpath = "//div[@id='table-search']/table/tbody/tr[ " + "td[position()=2]/a[contains(text(),'"
              + duns_No_Passed + "')]" + " and " + "td[position()=3 and contains(text(),'" + "WOSB"
              + "')]	" + " and " + "td[position()=3 and not(contains(text(),'" + "EDWOSB"
              + "')) ]	" + "]";
    }
    if (app_Type_Passed.toLowerCase().trim().contentEquals("mpp")) {
      xpath = "//div[@id='table-search']/table/tbody/tr[ " + "td[position()=2]/a[contains(text(),'"
              + duns_No_Passed + "')]" + " and " + "td[position()=3 and contains(text(),'MPP')]	" + "]";
    }

    TestApp40AndApp190.info(app_Type_Passed.toLowerCase().trim());
    TestApp40AndApp190.info(xpath);

    // For all app types click on the Application and go to all cases pages
    List<WebElement> current_Row = webDriver.findElements(By.xpath(xpath));
    TestApp40AndApp190.info(current_Row.size() + ": Is the total Under Review Elements");
    if (current_Row.size() > 0) {
      WebElement a1 =
              current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
      TestApp40AndApp190.info(a1.getText());
      a1.click();

      click_Element(webDriver, "Application_Common_Submit_Button_Id");
      click_Element(webDriver, "Application_Common_Save_Notes");

      if (app_Type_Passed.toLowerCase().trim().contentEquals("wosb") || app_Type_Passed.toLowerCase().trim().contentEquals("mpp")) {
        List<WebElement> check_Side_Panels =
                webDriver.findElements(By.xpath("//ul[contains(@class,'usa-sidenav-list')]"
                        + "/li/a[contains(text(),'inancial') and contains(text(),'eview')]"));
        Assert.assertEquals(check_Side_Panels.size(), 0);
        click_Element(webDriver, "Application_Common_Submit_Button");
        webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));
        click_Element(webDriver, "Application_Common_Submit_Button");
        webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));
      }

      if (app_Type_Passed.toLowerCase().trim().contentEquals("edwosb")) {
        List<WebElement> check_Side_Panels =
                webDriver.findElements(By.xpath("//ul[contains(@class,'usa-sidenav-list')]"
                        + "/li/a[contains(text(),'inancial') and contains(text(),'eview')]"));
        Assert.assertTrue(check_Side_Panels.size() > 0);
        click_Element(webDriver, "Application_Common_Submit_Button");
        webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));
        click_Element(webDriver, "Application_Common_Submit_Button");
        webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));

      }
    }

  }

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
    TestApp40AndApp190.info(duns_Number);
    run_This_app("EDWOSB", duns_Number);
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
