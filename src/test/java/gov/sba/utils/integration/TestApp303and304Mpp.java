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

import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.utils.integration.fillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.fillApplCreatePages.page8aFillUpDunsNo;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestApp303and304Mpp extends TestCase {
  private static final Logger logger_303 = LogManager.getLogger(VerifyWosbFlow.class.getName());
  // Set The variables/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

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
    // Before testing - verify the prepopulate flag - false -Should not
    // prepoluate the answers
      String sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8a_certified')";
      DatabaseUtils dbcall = new DatabaseUtils();
      DatabaseUtils.executeSQLScript(sql_Q_01);

      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();

      // Create application Mpp/Edwosb/Wosb/8a
      programs_Page.join_New_Program_CheckBoxes(webDriver, "Mpp");
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      logger_303.info(file_path_abs);
      page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, duns_Number);
      finalSignatureSubmit(webDriver);

      // Verify the Answers are not prefilling from the previous answers when
      // the prepulate falg = 'false';
      programs_Page.join_New_Program_CheckBoxes(webDriver, "Mpp");
      assertFalse(find_Element(webDriver, "General_Answer_Page_8A_117_Yes").getAttribute("outerHTML").toLowerCase().contains("checked"));

      // Update the - Prepopulate flag- True ---should Prepopluate the answers
      sql_Q_01 = "update sbaone.questions set  prepopulate = true where name in ('8a_certified')";
      DatabaseUtils.executeSQLScript(sql_Q_01);
      webDriver.navigate().refresh();
      webDriver.navigate().refresh();
      webDriver.navigate().refresh();
      Thread.sleep(2000); // CheckSleep
      assertTrue(find_Element(webDriver, "General_Answer_Page_8A_117_Yes").getAttribute("outerHTML").toLowerCase().contains("checked"));
      // Reset to Default
      sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8a_certified')";
      new DatabaseUtils().executeSQLScript(sql_Q_01);

  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
    String sql_Q_01 =
            "update sbaone.questions set  prepopulate = false where name in ('8a_certified')";
    DatabaseUtils dbcall = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);
  }
}
