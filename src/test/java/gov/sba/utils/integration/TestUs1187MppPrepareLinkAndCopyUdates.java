//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestUs1187MppPrepareLinkAndCopyUdates extends TestCase {
  private static final Logger logger =
          LogManager.getLogger(TestUs1187MppPrepareLinkAndCopyUdates.class);
  // Set The variables/Define
  private static WebDriver webDriver;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
      CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    System.out.println(logger.isInfoEnabled());
    logger.entry();
  }

  @Test
  public void testMainTest() throws Exception {
    String Actual_Text;
    String Expected_Text;
    try {
      WebElement Prepare_Link = webDriver.findElement(By.cssSelector("a[href*='/prepare']"));
      Prepare_Link.click();
      Prepare_Link.click();
      Thread.sleep(3000);
      logger.info("link is Clicked");

    } catch (Exception e) {
      logger.info("Link is not present + e.toString())");
    }

    try {
      // Check the Top part of the text for MPP Link
      List<WebElement> all_link = webDriver
              .findElement(By.xpath(
                      "//div[contains(@class,'usa-width-one-whole') and contains(@class,'usa-content')]"))
              .findElements(By.tagName("a"));
      WebElement mpp_link = all_link.get(4);
      Actual_Text = mpp_link.getText();
      logger.info(Actual_Text);
      Expected_Text = "All Small Mentor-Protégé Program Preparation Checklist";
      assertEquals(Expected_Text, Actual_Text);
      Thread.sleep(3000);
      mpp_link.click();
      // Check the bottom part of the text for MPP Link
      WebElement copy_Content = webDriver.findElement(
              By.xpath("//h3[contains(@id,'mpp-anc') and contains(text(),'All Small Mentor')]"));
      Actual_Text = copy_Content.getText();
      logger.info(
              webDriver.findElement(By.xpath("//div [  ul [ contains(@class, 'ul-level-one' ) ]  ] "))
                      .getText());
      logger.info(webDriver
              .findElement(By.xpath("//div/ul [ contains(@class, 'ul-level-one' ) ]   ")).getText());
      logger.info(Actual_Text);
      // Below to Assert the new Mpp Text added
      Actual_Text = webDriver
              .findElement(By.xpath(
                      "//div/p[contains(text(),'ust meet the size standard for small in the NAICS code')]"))
              .getText();
      Expected_Text =
              "A Mentor-Protégé relationship should be established before starting the application – this is not a matching program. The Protégé firm must meet the size standard for small in the NAICS code in which they are seeking business development assistance.";
      assertEquals(Actual_Text, Expected_Text);
    } catch (Exception e) {
      logger.info("Link is not present" + e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
              new String[] {TestUs1187MppPrepareLinkAndCopyUdates.class.getName(), "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}
