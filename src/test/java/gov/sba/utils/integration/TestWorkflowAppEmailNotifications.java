// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.AssertionUtils.delete_All_Application_Draft;
import static gov.sba.automation.AssertionUtils.return_All_Applications;
import static gov.sba.automation.DatabaseUtils.executeSQLScript;
import static gov.sba.pageObjetcs.ProgramsPage.join_New_Program_CheckBoxes;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})

public class TestWorkflowAppEmailNotifications extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestWorkflowAppEmailNotifications.class.getName());
  /*
   * set the flag as 0 day create application, set expiry date to get 45,30,15,1 day notification
   * set the flag 1 day after daily cron job runs verify emails set the flag to o again Set The
   * variables/Define
   */
  private static WebDriver webDriver;
  String duns_Number, email, password, flagvalue;

  public void setUp() throws Exception {
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
    flagvalue = CommonApplicationMethods.getflagvalue();
  }

  @Test
  public void testMainTest() throws Exception {
    try {
      return_All_Applications(webDriver, 55, duns_Number);
      delete_All_Application_Draft(webDriver, email, password, duns_Number);
      if (flagvalue.contains("0")) {
        new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
        join_New_Program_CheckBoxes(webDriver, "EDWOSB");
        page8aFillUp(webDriver, "Yes");
        finalSignatureSubmit(webDriver);

        List<WebElement> count_Active =
            webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr" + "["
                + "td[position()=1]/a[contains(text(),'EDWOSB')]" + " and "
                + "td[position()=5 and (contains(text(),'Active'))]" + "]"));

        assertEquals(count_Active.size(), 1);
        logger.info("Doc has been uploaded.-Edwosb application submitted sucessfully");

        /*
         * update the expiry date to current date to make thecertificate to expire after the
         * midnight cron job runs check the status of the certificate to - Expired -- APP387
         */

        String sql_Q_01 = "update SBAONE.certificates " + "   set expiry_date = ("
            + "select cast(issue_date as date) + 31"
            + "                           from SBAONE.certificates " + "   where "
            + "organization_id = (select id from SBAONE.organizations where duns_number = 'replaceDune')"
            + "and  type = 'Certificate::Edwosb'" + "    ) " + "   where "
            + "  organization_id = (select id from SBAONE.organizations where duns_number = 'replaceDune')"
            + "and   type = 'Certificate::Edwosb'";

        executeSQLScript(sql_Q_01.replace("replaceDune", duns_Number));

      }
    } catch (Exception e) {
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestWorkflowAppEmailNotifications", "Exception"});
      throw e;
    }

    if (flagvalue.contains("1")) {
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
