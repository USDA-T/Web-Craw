// Ts Created By Deepa Patri
package gov.sba.utils.integration;


import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.automation.DatabaseUtils.findUnusedDunsNumber;
import static gov.sba.pageObjetcs.ProgramsPage.join_New_Program_CheckBoxes;
import static gov.sba.pageObjetcs.VendorDashboardPage.verify_Row_In_A_Table_And_Return;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

/*
 * Documentation for Workflow WorkFlows for EDWOSB - Accommodating best minimal Workflow Tests
 * TestWorkflowEDWOSB + 01. Vendor Draft Create , logout. Update draft submit , Analyst Review,
 * Supervisor Approve - 8a Yes 02. Vendor Create , Submit, Analyst Review, Supervisor Approve - 8a
 * No 03. Vendor Create , Submit, Analyst Review, Supervisor Reject - Declined 04. Vendor Create ,
 * Submit, Analyst return, Vendor Change Draft , Resubmit, Analyst Review, Supervisor Approve 05.
 * Vendor Create , Submit, Analyst return, Vendor Change Draft , Resubmit, AAnalyst Review,
 * Supervisor reject 06. Vendor Create , Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Approve 07. Vendor Create , Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Reject
 */
/*
 * @Category({gov.sba.utils.integration.StableTests.class,
 * gov.sba.utils.integration.DeepaTests.class})
 */
@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowWOSB06 extends TestCase {
  Logger logger = LogManager.getLogger(TestWorkflowWOSB06.class.getName());
  private static WebDriver webDriver;
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    logger.info("Set as head");
    //TestHelpers.set_Headless();
    webDriver = set_Timeouts(TestHelpers.getDefaultWebDriver());
    webDriver.get(TestHelpers.getBaseUrl());
    String[] details = findUnusedDunsNumber("");
    email = details[0];
    password = details[1];
    duns_Number = details[2];
    /* duns_Number = "376736143";get_The_Row_From_Login_Data = 64; */
  }

  @Test
  public void testWorkflowEDWOSB06() throws Exception {
    try {
      /*
       * return_All_Applications(webDriver, 55, duns_Number);
       * delete_All_Application_Draft(webDriver, email, password, duns_Number);
       */
      /* Create application Edwosb */
      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      join_New_Program_CheckBoxes(webDriver, "WOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);

      /* TODO - Later Annual Wosb Review */
      String sql_Q_01 =
          "update sbaone.certificates set expiry_date = CURRENT_TIMESTAMP where organization_id = (select id from sbaone.organizations where type = 'Certificate::Wosb' and duns_number = '"
              + duns_Number + "')";
      new DatabaseUtils().executeSQLScript(sql_Q_01);
      /*
       * check the status of the certificate to Expired verify the Renewal link, submit new renew
       * application
       */

      webDriver.navigate().refresh();
      assertNotNull(verify_Row_In_A_Table_And_Return(webDriver,
          new String[] {"WOSB Self-Certification", "Certificate", "Active", "", "", "", "Renew"}));

      /* Create new renew application - submit */
      click_Element(webDriver, "SBA_WOSB_Table_Renew_Link");
      click_Element(webDriver, "Application_Common_Accept_Button");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);

      /* Verify the old application's status - Expired The renewed Application's status - Active */
      assertNotNull(verify_Row_In_A_Table_And_Return(webDriver,
          new String[] {"WOSB Self-Certification", "", "Active", "", "", "", ""}));
      assertNotNull(verify_Row_In_A_Table_And_Return(webDriver,
          new String[] {"WOSB Self-Certification", "", "Expired", "", "", "", ""}));

    } catch (Exception e) {
      logger.info(e.toString());
      take_ScreenShot_TestCaseName(webDriver,
          new String[] {TestWorkflowEDWOSB06.class.getName(), "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
