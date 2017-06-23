
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.Constants;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.click_Element;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})
// @Category(StableTests.class)
public class TestSearchPage extends TestCase {
  private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());
  private static WebDriver webDriver;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();
        
    webDriver.get(TestHelpers.getBaseUrl());
    // Get the login based on the environment under test (e.g.
    // 'development', 'qa', 'staging')
    logger.info("FYI: your environment under test:" + System.getProperty(Constants.TEST_ENV));
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

  @Test()
  // @Category(StableTests.class)
  public void testMainLogic() throws Exception {
    logger.debug("Using test login   : " + LoginHelpers.getLoginData().getEmail());
    logger.debug("Using test password: " + LoginHelpers.getLoginData().getPassword());
    click_Element(webDriver, "SBA_Login_Button");

    CommonApplicationMethods.setText_Element(webDriver, "SBA_Login_Email", LoginHelpers.getLoginData().getEmail());
    CommonApplicationMethods.setText_Element(webDriver, "SBA_Login_Pwd", LoginHelpers.getLoginData().getPassword());

    click_Element(webDriver, "OppSup_Dashboard_Business_Signin"); Thread.sleep(1500); //Deepa Sleep needed here
    org.junit.Assert.assertTrue(webDriver.getCurrentUrl().contains("dashboard"));
  }

}
