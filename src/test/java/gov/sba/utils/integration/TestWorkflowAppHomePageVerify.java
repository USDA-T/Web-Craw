// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;


@Category({gov.sba.utils.integration.StableTests.class})

public class TestWorkflowAppHomePageVerify extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestWorkflowAppHomePageVerify.class.getName());
  // Set The variables/Define
  private static WebDriver webDriver;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
  }

  @Test
  public void testMainTest() throws Exception {

    String Actual_Text = null;
    String Expected_Text;
    try {
      WebElement welcome_box_Element = webDriver.findElement(By.className("usa-font-lead"));
      Actual_Text = welcome_box_Element.getText();
      logger.info("welcome text is present");

    } catch (Exception e) {
      logger.info("welcome text is not present");
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {TestWorkflowAppHomePageVerify.class.getName(), "Exception"});
    }
    Expected_Text =
        "The U.S. Small Business Administration (SBA) is working to modernize the application process for federal contracting programs. Manage your application and eligibility documentation for the WOSB and All Small Mentor-Protégé programs online from our easy-to-use dashboard. Get started today!";
    assertEquals(Expected_Text, Actual_Text);
    Thread.sleep(3000);
    try {
      List<WebElement> upcoming_Event = webDriver
          .findElement(By.xpath(
              "//div[contains(@class,'usa-width-one-whole') and contains(@class,'row_block')]"))
          .findElements(By.className("usa-width-one-third"));
      Actual_Text = upcoming_Event.get(2).getText();
      logger.info(Actual_Text);

    } catch (Exception e) {
      logger.info("upcoming event text is not present" + e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {TestWorkflowAppHomePageVerify.class.getName(), "Exception"});
    }
    Expected_Text =
        "Upcoming Activities\nNew programs will soon be incorporated onto certify.SBA.gov including the 8(a) Business Development Program (Winter 2017), HUBZone Program (Spring 2017) and Dynamic Small Business Search (DSBS) (Summer 2017).";
    assertEquals(Expected_Text, Actual_Text);

    try {
      Actual_Text = webDriver.findElement(By.xpath(
          "//div[contains(@class,'currently-grey-box') and contains(@class,'usa-width-one-whole')]"))
          .getText();
      logger.info("Grey Box text is present");

    } catch (Exception e) {
      logger.info("Grey Box text is not present" + e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {TestWorkflowAppHomePageVerify.class.getName(), "Exception"});
    }
    Expected_Text =
        "Currently, this website is available for the Women-Owned Small Business (WOSB) and All Small Mentor Protégé programs only. For the 8(a) Business Development and HUBZone programs, please continue to use the SBA General Login System (GLS) to manage your certifications.";
    assertEquals(Expected_Text, Actual_Text);
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}
