//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.*;
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

@Category({gov.sba.utils.integration.StableTests.class})

public class TestApp303and304EDWOSB extends TestCase {
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
    String        sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8aq1')";
    DatabaseUtils dbcall   = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);

    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    // Create application
    // Mpp/EdC:\IdeaProj\SBA_One\src\main\DataFiles\Upload.pdf
    //
    // wosb/Wosb/8a
      programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    logger_303.info(file_path_abs);
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    fillApplCreatePages.finalSignatureSubmit(webDriver);

    CommonApplicationMethods.navigationMenuClick(webDriver, "LOGOUT");
    logger_303.info("First Logout");

    AssertionUtils.return_all_Applications(webDriver, 11, duns_Number);
    // webDriver.navigate().back();
    // webDriver.navigate().back();
    // Thread.sleep(1500);
    // CommonApplicationMethods.navigationMenuClick(webDriver, "LOGOUT");
    // logger_303.info("Second Logout");

    login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();
    AssertionUtils.delete_all_Drafts(webDriver);

    // Verify the Answers are not prefilling from the previous answers when
    // the prepulate falg = 'false';
      programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
    // String checkBoxElement =
    // webDriver.findElement(By.id("answers_228_value_yes")).getAttribute("outerHTML");
    String checkBoxElement = webDriver
            .findElement(By.xpath(
                    "//input[@type='radio' and contains(@id,'answers_') and contains(@id,'_value_yes') ]"))
            .getAttribute("outerHTML");
    assertFalse(checkBoxElement.toLowerCase().contains("checked"));

    // Update the - Prepopulate flag- True ---should Prepopluate the answers
    sql_Q_01 = "update sbaone.questions set  prepopulate = true where name in ('8aq1')";
    DatabaseUtils.executeSQLScript(sql_Q_01);

    webDriver.navigate().refresh();
    webDriver.navigate().refresh();
    webDriver.navigate().refresh();
    Thread.sleep(1000); // CheckSleep

    checkBoxElement = webDriver
            .findElement(By.xpath(
                    "//input[@type='radio' and contains(@id,'answers_') and contains(@id,'_value_yes') ]"))
            .getAttribute("outerHTML");
    assertTrue(checkBoxElement.toLowerCase().contains("checked"));
    // Reset to Default
    sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8aq1')";
    dbcall = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);

  }

  @After
  public void tearDown() throws Exception {
    String        sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8aq1')";
    DatabaseUtils dbcall   = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);
    webDriver.quit();
  }
}
