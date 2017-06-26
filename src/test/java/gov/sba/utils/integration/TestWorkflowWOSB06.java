// Ts Created By Deepa Patri
package gov.sba.utils.integration;


import static gov.sba.automation.AssertionUtils.delete_All_Application_Draft;
import static gov.sba.automation.AssertionUtils.return_All_Applications;
import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;
import static gov.sba.pageObjetcs.programs_Page.join_New_Program_CheckBoxes;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

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
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    duns_Number = "376736143";
    get_The_Row_From_Login_Data = 64;
  }

  @Test
  public void testMainTest() throws Exception {
    try {

      return_All_Applications(webDriver, 11, duns_Number);
      delete_All_Application_Draft(webDriver, 64, duns_Number);
      // Create application Edwosb
      new LoginPageWithReference(webDriver, 64).Login_With_Reference();
      join_New_Program_CheckBoxes(webDriver, "WOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);

      // return; // TODO - Later

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
