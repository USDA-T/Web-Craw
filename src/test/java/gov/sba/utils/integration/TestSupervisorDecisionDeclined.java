// Ts Created By Deepa Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;

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
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestSupervisorDecisionDeclined extends TestCase {
  private static final Logger logger_TestApp309 =
      LogManager.getLogger(TestSupervisorDecisionDeclined.class.getName());
  // Submit Wosb/EDWOSBApplication
  // Login with Supervisor acccont Decline
  // Login as vendor and Verfiy the dasboard, certificate,Sba_application
  // table.
  // Login as Analyst- verfiy the applciation status
  // Login as Contracting Office - only Active certifcate should be displayed.
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    // CommonApplicationMethods.focus_window();

    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  // Test APP-309 Decision Ineligible
  public void testMainTest() throws Exception {

    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    try {
      // Check Dashboard Pending status
      String typ_App = "EDWOSB";

      // String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      programs_Page.join_New_Program_CheckBoxes(webDriver, typ_App);
      // logger_TestApp309.info(file_path_abs);
      FillApplCreatePages.page8aFillUp(webDriver, "Yes");
      FillApplCreatePages.finalSignatureSubmit(webDriver);

      navigationMenuClick(webDriver, "Logout");
      new LoginPageWithReference(webDriver, 55).Login_With_Reference();
      navigationBarClick(webDriver, "Cases");
      CommonApplicationMethods.search_Cases_Duns_Number_Table(webDriver, duns_Number);

      String xpath_Element = "//div[@id='table-search']/table/tbody/tr[ "
          + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
          + "td[position()=3 and contains(text()," + typ_App + ")]	" + "]";
      List<WebElement> current_Row = find_Elements(webDriver, "xpath", xpath_Element);
      logger_TestApp309.info(current_Row.size() + ": Is the total  Elements");
      if (current_Row.size() > 0) {
        logger_TestApp309.info(current_Row.get(0).getAttribute("innerHTML"));
        xpath_Element = xpath_Element + "/td/a[contains(text(),'Legal Business Name')]";
        click_Element(webDriver, "xpath", xpath_Element);
      }
      click_Element(webDriver, "Case_Submit_Button");
      click_Element(webDriver, "Case_SaveNotes_Button");
      click_Element(webDriver, "Application_Common_Submit_Button");

      // Select Determination -Decision as -Decline Ineligible
      click_Element(webDriver, "SBA_Review_Determ_Made");
      new Select(find_Element(webDriver, "Analyst_Review_Determ_Decision"));
      click_Element(webDriver, "Application_Common_Submit_Button");
      webDriver.navigate().back();
      Thread.sleep(2000);
      webDriver.navigate().back();
      Thread.sleep(2000);
      webDriver.navigate().back();
      Thread.sleep(2000);
      navigationBarClick(webDriver, "Cases");
      xpath_Element =
          "//div[@id='table-search']/table/tbody/tr[ td[position()=8 and contains(text(),'neligible')] "
              + "and td[position()=2]/a[contains(text(),'" + duns_Number + "')]	"
              + "and td[position()=3 and contains(text()," + typ_App + ")] ]";
      List<WebElement> current_Row1 = find_Elements(webDriver, "xpath", xpath_Element);
      Assert.assertTrue(current_Row1.size() > 0);
      navigationBarClick(webDriver, "Logout");
      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      // Certificate status - Ineligible, decision- Sba Declined
      xpath_Element =
          "//*[@id='certifications']/tbody/tr[ (td[position()=5 and contains(text(),'neligible')]) and (td[position()=1]/a[contains(text(),'EDWOSB')]) ]";
      List<WebElement> listOfIneliglebEDWOSB = find_Elements(webDriver, "xpath", xpath_Element);
      Assert.assertTrue(listOfIneliglebEDWOSB.size() > 0);

    } catch (Exception e) {
      logger_TestApp309.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestSupervisorDecisionDeclined", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
