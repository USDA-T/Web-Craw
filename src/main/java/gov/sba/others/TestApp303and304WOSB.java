// TS_Created_By_Deepa_Patri
package gov.sba.others;

import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import gov.sba.utils.integration.NewScorpQuestionPageDeepa;
import gov.sba.utils.integration.FillApplCreatePages;

import gov.sba.automation.*;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})

public class TestApp303and304WOSB extends TestCase {
  private static final Logger logger_303 =
      LogManager.getLogger(TestApp303and304WOSB.class.getName());
  // Set The variables/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    CommonApplicationMethods.get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
    // focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {
    // Before testing - verify the prepopulate flag - false -Should not
    // prepoluate the answers
    String sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8aq1')";
    DatabaseUtils dbcall = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);

    new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
    // Create application Mpp/Edwosb/Wosb/8a
    programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");

    FillApplCreatePages.page8aFillUp(webDriver, "Yes");
    FillApplCreatePages.finalSignatureSubmit(webDriver);

    navigationMenuClick(webDriver, "LOGOUT");

    AssertionUtils.return_All_Applications(webDriver, 11, duns_Number);

    new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
    AssertionUtils.delete_all_Drafts(webDriver);

    // Verify the Answers are not prefilling from the previous answers when
    // the prepulate falg = 'false';
    programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
    String checkBoxElement =
        find_Element(webDriver, "General_Answer_Page_8A_Yes").getAttribute("outerHTML");
    assertFalse(checkBoxElement.toLowerCase().contains("checked"));

    // Update the - Prepopulate flag- True ---should Prepopluate the answers
    sql_Q_01 = "update sbaone.questions set  prepopulate = true where name in ('8aq1')";
    DatabaseUtils.executeSQLScript(sql_Q_01);

    webDriver.navigate().refresh();
    webDriver.navigate().refresh();
    webDriver.navigate().refresh();
    checkBoxElement =
        find_Element(webDriver, "General_Answer_Page_8A_Yes").getAttribute("outerHTML");
    assertTrue(checkBoxElement.toLowerCase().contains("checked"));

    // Reset to Default
    sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8aq1')";
    dbcall = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);

  }

  @After
  public void tearDown() throws Exception {
    String sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8aq1')";
    DatabaseUtils dbcall = new DatabaseUtils();
    DatabaseUtils.executeSQLScript(sql_Q_01);
    webDriver.quit();
  }
}
