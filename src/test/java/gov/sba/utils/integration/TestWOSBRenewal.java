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
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestWOSBRenewal extends TestCase {
  private static final Logger logger_TestWOSBRenewal =
          LogManager.getLogger(TestWOSBRenewal.class.getName());
  private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

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

    // Login to dashboard.
    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();
    try {
      // Create application Mpp/Edwosb/Wosb/8a
      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      logger_TestWOSBRenewal.info(file_path_abs);
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      List<WebElement> count_Active = find_Elements(webDriver,"xpath","//table[@id='certifications']/tbody/tr[ td[1]/a[ contains(text(),'EDWOSB') ] and td[5][ contains(text(),'Active') ]]");
      assertEquals(count_Active.size(), 1);
      logger_TestWOSBRenewal
              .info("Doc has been uploaded.-Edwosb application submitted sucessfully");
      // update the expiry date to current date to make thecertificate to
      // expire
      // --after the midnight cron job runs
      // check the status of the certificate to - Expired -- APP387
      String sql_Q_01 =
              "update sbaone.certificates set expiry_date = CURRENT_TIMESTAMP where organization_id = (select id from sbaone.organizations where duns_number = '"
                      + duns_Number + "')";
      DatabaseUtils dbcall = new DatabaseUtils();
      DatabaseUtils.executeSQLScript(sql_Q_01);
      // check the status of the certificate to - Expired
      // verify the Renewal link - submit new renew application
      webDriver.navigate().refresh();
      List<WebElement> count_ReNew = find_Elements(webDriver,"xpath","//table[@id='certifications']/tbody/tr[ td[1]/a[ contains(text(),'EDWOSB') ] and td[7]/a[ contains(text(),'Renew')] ]");
      assertEquals(count_ReNew.size(), 1);
      // Create new renew application - submit
      count_ReNew.get(0).findElement(By.xpath("td[7]/a")).click();
      click_Element(webDriver,"Application_Common_Accept_Button");
      file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      logger_TestWOSBRenewal.info(file_path_abs);
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      // Verify the old application's status - Expired
      // The renewed Application's status - Active
      List<WebElement> count_Edwosb_Active = find_Elements(webDriver,"xpath","//table[@id='certifications']/tbody/tr[ td[1]/a[ contains(text(),'EDWOSB Self-Certification') ] and td[5][ contains(text(),'Active') ]]");
      assertTrue(count_Edwosb_Active.size() > 0);
      List<WebElement> count_Edwosb_Expire = find_Elements(webDriver,"xpath","//*[@id='certifications']/tbody/tr" + "["
              + "td[position()=1]/a[contains(text(),'EDWOSB')]" + " and "
              + "td[position()=5 and (contains(text(),'Expired'))]" + "]");
      assertEquals(count_Edwosb_Expire.size(), 1);
    } catch (Exception e) {
      logger_TestWOSBRenewal.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
              new String[] {TestWOSBRenewal.class.getName(), "Exception"});
        throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
