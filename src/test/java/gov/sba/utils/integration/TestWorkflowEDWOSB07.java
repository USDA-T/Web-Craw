// TS Created by Deepa Patri
package gov.sba.utils.integration;

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

/*
 * Documentation for Workflow WorkFlows for EDWOSB - Accommodating best minimal Workflow Tests
 * TestWorkflowEDWOSB + 07 Vendor admin create EDWOSB application for corporation business type with
 * having 8a No,one business partners 413 form and all other yes Analyst Review the Submitted MPP
 * application, Supervisor approves it.
 */

@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowEDWOSB07 extends TestCase {
  private static final Logger logger = LogManager.getLogger(TestWorkflowEDWOSB07.class.getName());
  private static WebDriver webDriver;
  int stop_Exec = 1;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    logger.info("Set as head");
   //TestHelpers.set_Headless();
    webDriver = set_Timeouts(TestHelpers.getDefaultWebDriver());
    webDriver.get(TestHelpers.getBaseUrl());
    String[] details = findUnusedDunsNumber("corp");
    email = details[0];
    password = details[1];
    duns_Number = details[2];
    /* duns_Number = "376736143";get_The_Row_From_Login_Data = 64; */

  }

  @Test
  public void testWorkflowEDWOSB06() throws Exception {
    try {
      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      join_New_Program_CheckBoxes(webDriver, "EDWOSB");

      if (stop_Exec == 1) {
        return;
      } /* TODO Working On */


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
