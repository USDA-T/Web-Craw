package gov.sba.utils.integration;

import java.util.List;

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
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestApp309DecisionIneligible_EDWOSB extends TestCase {
  // Submit Wosb/EDWOSBApplication
  // Login with Supervisor acccont Decline
  // Login as vendor and Verfiy the dasboard, certificate,Sba_application
  // table.
  // Login as Analyst- verfiy the applciation status
  // Login as Contracting Office - only Active certifcate should be displayed.
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  private static final Logger logger_TestApp309 =
      LogManager.getLogger(TestApp309DecisionIneligible_EDWOSB.class.getName());
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
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

    try {
      // Check Dashboard Pending status
      String typ_App = "EDWOSB";

      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      CommonApplicationMethods.createApplication(webDriver, typ_App);
      logger_TestApp309.info(file_path_abs);
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      fillApplCreatePages.finalSignatureSubmit(webDriver);

      CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
      LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 55);
      login_Data_01.Login_With_Reference();
      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      CommonApplicationMethods.search_Cases_Duns_Number_Table(webDriver, duns_Number);
      List<WebElement> current_Row =
          webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
              + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
              + "td[position()=3 and contains(text()," + typ_App + ")]	" + "]"));
      logger_TestApp309.info(current_Row.size() + ": Is the total  Elements");
      if (current_Row.size() > 0) {
        logger_TestApp309.info(current_Row.get(0).getAttribute("innerHTML"));
      }
      WebElement a1 =
          current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
      logger_TestApp309.info(a1.getText() + "__1");
      a1.click();
      webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
      webDriver.findElement(By.xpath("//input[@id='save_notes']")).click();
      webDriver.findElement(By.xpath("//input[@type='submit']")).click();
      // Select Determination -Decision as -Decline Ineligible
      webDriver
          .findElement(By.xpath(
              "//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Determination Made')]"))
          .click();
      Select dropdown =
          new Select(webDriver.findElement(By.xpath("//select[@id='determination_decision']")));
      // dropdown.selectByValue("Declined Ineliglible");
      webDriver.findElement(By.xpath("//input[@type='submit']")).click();
      webDriver.navigate().back();
      Thread.sleep(2000);
      webDriver.navigate().back();
      webDriver.navigate().back();
      Thread.sleep(2000);
      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      List<WebElement> current_Row1 =
          webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
              + "td[position()=8 and contains(text(),'neligible')]   and "
              + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
              + "td[position()=3 and contains(text()," + typ_App + ")]	" + "]"));
      Assert.assertTrue(current_Row1.size() > 0);
      CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
      login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();
      // Certificate status - Ineligible, decision- Sba Declined
      List<WebElement> listOfIneliglebEDWOSB =
          webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/" + "tr[  " + ""
              + "		(td[position()=5 and contains(text(),'neligible')]) "
              + "and  (td[position()=1]/a[contains(text(),'EDWOSB')]) " + "	]"));
      Assert.assertTrue(listOfIneliglebEDWOSB.size() > 0);

    } catch (Exception e) {
      logger_TestApp309.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestApp309DecisionIneligible_EDWOSB", "Exception"});
      throw new Exception("Error: ", e);
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
