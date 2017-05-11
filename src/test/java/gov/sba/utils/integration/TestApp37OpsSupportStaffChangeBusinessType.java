// TS Created By _deepa patri
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.UnstableTests.class})
public class TestApp37OpsSupportStaffChangeBusinessType extends TestCase {
  private static final Logger logger_37OpsSpStfCh =
      LogManager.getLogger(TestApp37OpsSupportStaffChangeBusinessType.class.getName());
  // Set The variabl.es/Define
  WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
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

    LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 27);
    login_Data_01.Login_With_Reference();

    try {

      webDriver.findElement(By.id("query")).sendKeys(duns_Number);
      webDriver
          .findElement(
              By.xpath("//*[@id='analyst-search']/div/button[ span[contains(text(),'Search')] ]"))
          .click();
      webDriver
          .findElement(By.xpath(
              "//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a"))
          .click();
      webDriver.findElement(By.xpath("//a[contains(text(),'endor') and contains(text(),'upport')]"))
          .click();

      webDriver.findElement(By.id("change_business_type_link")).click();
      Select dropdown1 = new Select(webDriver.findElement(By.id("business_type")));

      dropdown1.selectByIndex(1);
      webDriver.findElement(By.id("save")).click();
      CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();

      // Verify the Business type is changes to Scorp[from LLC]
      CommonApplicationMethods.click_Element(webDriver, "Vendor_Admin_Dashboard_More_Details");

      String business_Type = webDriver.findElement(By.id("more-details")).getText();

      assertTrue(business_Type.contains("C-Corporation"));
      assertTrue(business_Type.contains("C-Corporation"));

      // CommonApplicationMethods.createApplication(webDriver, "EDWOSB");
      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      webDriver.findElement(By.id("certificate_type_edwosb")).click();
      webDriver.findElement(By.id("add_certification")).click();
      webDriver
          .findElement(By.xpath("//*[@id='js-navigation-menu']/li/a[contains(text(),'Programs')]"))
          .click();
      webDriver.findElement(By.id("certificate_type_edwosb")).click();
      webDriver.findElement(By.id("add_certification")).click();

      ScorpQuestionsPage scorpQuestionsPage = new ScorpQuestionsPage(webDriver);
      scorpQuestionsPage.ScorpQuestions();

    } catch (Exception e) {
      logger_37OpsSpStfCh.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestApp37OpsSupportStaffChangeBusinessType", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
