// TS_Created_By_Deepa_Patri
package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.VerifyEdwosbFlow;
import junit.framework.TestCase;


@Category({gov.sba.utils.integration.StableTests.class})

public class TestUS942AddEdwosb extends TestCase {
  // Set The variables/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {

    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();

    webDriver.get(TestHelpers.getBaseUrl());
    // CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber("");
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {

    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    VerifyEdwosbFlow VerifyEDWOSBFlow = new VerifyEdwosbFlow();
    VerifyEDWOSBFlow.VerifyEDWOSBFlowSetDriver(webDriver);
    VerifyEDWOSBFlow.VerifyEDWOSBFlowLogic();

  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}
