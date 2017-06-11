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
import org.openqa.selenium.WebElement;

import java.util.List;

@Category({gov.sba.utils.integration.StableTests.class})
public class TestApp220MPPQuestionnaire extends TestCase {
  private static final Logger TestApp220MPPQuestionnaire =
      LogManager.getLogger(TestApp220MPPQuestionnaire.class.getName());
    // Set The variabl.es/Define
    WebDriver webDriver;
  int get_The_Row_From_Login_Data;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
        CommonApplicationMethods.get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {
    try {

        new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
        programs_Page.join_New_Program_CheckBoxes(webDriver, "MPP");
        webDriver.findElement(By.xpath("//input[@type='radio' and contains(@id,'answers_') and contains(@id,'_value_yes') ]")).click();

      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

      TestApp220MPPQuestionnaire.info(file_path_abs);
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);

      WebElement Business_text =
          webDriver.findElement(By.xpath("//article/h2[contains(text(),'Business Info')]"));

      List<WebElement> duns_No = webDriver
          .findElements(By.xpath("//input[@type='number' and contains(@id,'duns-value')]"));
      if (duns_No.size() > 0) {
        duns_No.get(0).sendKeys(duns_Number);
        webDriver.findElement(By.xpath("//a[contains(@id,'search-duns')]")).click();
        CommonApplicationMethods.accept_Alert(webDriver);
      }

      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      CommonApplicationMethods.accept_Alert(webDriver);

      fillApplCreatePages.finalSignatureSubmit(webDriver);
      TestApp220MPPQuestionnaire.info("Application has been submitted sucessfully.");

    } catch (Exception e) {
      TestApp220MPPQuestionnaire.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[] {"TestApp220MPPQuestionnaire", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
