// TS Created by Deepa Patri
package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
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

import gov.sba.automation.AssertionUtils;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.ProgramsPage;
import gov.sba.utils.integration.LoginPageWithDetails;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowWOSBVendorSelecting8aYes extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestWorkflowWOSBVendorSelecting8aYes.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    // CommonApplicationMethods.focus_window();
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
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    page8aFillUp(webDriver, "Yes");
    finalSignatureSubmit(webDriver);

    try {
      if (CommonApplicationMethods.checkApplicationExists(webDriver, "WOSB", "Active")) {
        navigationMenuClick(webDriver, "LOGOUT");
        AssertionUtils.return_All_Applications(webDriver, 11, duns_Number);

        login_Data.Login_With_Details();
        // Verify application's returns to vendor after application is re
        String xpath_Element =
            "//*[@id='certifications']/tbody/tr[ (td[position()=5 and contains(text(),'Draft')]) and (td[position()=1]/a[contains(text(),'WOSB')]) ]";
        List<WebElement> listOfDraftWOSB = find_Elements(webDriver, "xpath", xpath_Element);
        Assert.assertTrue(listOfDraftWOSB.size() > 0);
        AssertionUtils.delete_all_Drafts(webDriver);

      }

    } catch (Exception e) {
      logger.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"logger", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}


