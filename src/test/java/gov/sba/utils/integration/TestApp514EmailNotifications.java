// TS Created By _deepa patri
package gov.sba.utils.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestApp514EmailNotifications extends TestCase {
  //
  // set the flag as 0 day
  // create application,
  // set expiry date to get 45,30,15,1 day notification
  // set the flag 1 day
  // after daily cron job runs
  // verify emails
  // set the flag to o again
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  private static final Logger logger_App514 =
      LogManager.getLogger(gov.sba.utils.integration.TestApp514EmailNotifications.class.getName());
  String duns_Number, email, password, flagvalue;

  public void setUp() throws Exception {
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
    flagvalue = CommonApplicationMethods.getflagvalue();
  }

  @Test
  public void testMainTest() throws Exception {
    // Chekc the flag value -initial set to 0
    if (flagvalue.contains("0")) {

      // Login to dashboard.
      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();

      try {

        CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        CommonApplicationMethods.createApplication(webDriver, "EDWOSB");
        String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
        logger_App514.info(file_path_abs);
        fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
        fillApplCreatePages.finalSignatureSubmit(webDriver);
        List<WebElement> count_Active =
            webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr" + "["
                + "td[position()=1]/a[contains(text(),'EDWOSB')]" + " and "
                + "td[position()=5 and (contains(text(),'Active'))]" + "]"));

        assertEquals(count_Active.size(), 1);
        logger_App514.info("Doc has been uploaded.-Edwosb application submitted sucessfully");

        // update the expiry date to current date to make thecertificate
        // to expire
        // --after the midnight cron job runs
        // check the status of the certificate to - Expired -- APP387
        String sql_Q_01 = "update SBAONE.certificates " + "   set expiry_date = ("
            + "select cast(issue_date as date) + 31"
            + "                           from SBAONE.certificates " + "   where "
            + "organization_id = (select id from SBAONE.organizations where duns_number = 'replaceDune')"
            + "and  type = 'Certificate::Edwosb'" + "    ) " + "   where "
            + "  organization_id = (select id from SBAONE.organizations where duns_number = 'replaceDune')"
            + "and   type = 'Certificate::Edwosb'";

        DatabaseUtils dbcall = new DatabaseUtils();
        dbcall.executeSQLScript(sql_Q_01.replace("replaceDune", duns_Number));

      } catch (Exception e) {
        CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
            new String[] {"TestApp514EmailNotifications", "Exception"});
        throw e;
      }
    }

    if (flagvalue.contains("1")) {

    }

  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
