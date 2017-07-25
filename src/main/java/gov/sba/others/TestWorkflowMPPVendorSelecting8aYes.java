// Ts Created by Deepa Patri
package gov.sba.others;

import static gov.sba.automation.AssertionUtils.return_All_Applications;
import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.AnalystCasesPage;
import gov.sba.pageObjetcs.ProgramsPage;
import gov.sba.utils.integration.FillApplCreatePages;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

@Ignore
public class TestWorkflowMPPVendorSelecting8aYes extends TestCase {
  // verify App-220
  private static final Logger logger =
      LogManager.getLogger(TestWorkflowMPPVendorSelecting8aYes.class.getName());
  WebDriver webDriver;
  int get_The_Row_From_Login_Data;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
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
  public void testWorkflowMPPVendorSelecting8aYest() throws Exception {
    try {

      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
      ProgramsPage.join_New_Program_CheckBoxes(webDriver, "MPP");
      webDriver.findElement(By.xpath(
          "//input[@type='radio' and contains(@id,'answers_') and contains(@id,'_value_yes') ]"))
          .click();

      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

      logger.info(file_path_abs);
      FillApplCreatePages.page8aFillUp(webDriver, "Yes");

      WebElement Business_text =
          webDriver.findElement(By.xpath("//article/h2[contains(text(),'Business Info')]"));

      List<WebElement> duns_No = webDriver
          .findElements(By.xpath("//input[@type='number' and contains(@id,'duns-value')]"));
      if (duns_No.size() > 0) {
        duns_No.get(0).sendKeys(duns_Number);
        webDriver.findElement(By.xpath("//a[contains(@id,'search-duns')]")).click();
        CommonApplicationMethods.accept_Alert(webDriver, 4);
      }

      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      CommonApplicationMethods.click_Element(webDriver, "Application_Common_Submit_Button");
      CommonApplicationMethods.accept_Alert(webDriver, 4);

      FillApplCreatePages.finalSignatureSubmit(webDriver);
      logger.info("Application has been submitted sucessfully.");
      // Verify US1457
      List<WebElement> count_Pending =
          webDriver.findElements(By.xpath("//*[@id='certifications']/tbody/tr" + "[ "
              + "     td[position()=1]/a[contains(text(),'MPP')] and "
              + "     td[position()=5 and (contains(text(),'ending'))] " + "]"));

      assertEquals(count_Pending.size(), 1);

      click_Element(webDriver, "SBA_Application_All_Cases_Page_MPP");
      assertTrue(webDriver.getPageSource().toString().contains(duns_Number));
      logger.info(find_Element(webDriver, "SBA_MPP_Self_Cert_Summ_Title").getText());
      logger.info(find_Element(webDriver, "SBA_MPP_Self_Cert_Summ_Name").getText());
      Connection databaseConnection = DatabaseUtils.getDatabaseConnection();

      Statement statement_SQL = databaseConnection.createStatement();
      // TODO: search for executeQuery() to see list of query
      ResultSet result_Set = statement_SQL
          .executeQuery("select issue_date, expiry_date, workflow_state from sbaone.certificates A,"
              + "sbaone.organizations where duns_number = '" + duns_Number + "'"
              + "and  certificate_type_id =3" + "and workflow_state = 'pending'" + ";");

      // Code for US 1457 And US 1491
      result_Set.next();

      // -- Get Data from DB to test Pending status validation on
      // UI with DB
      String issue_date = result_Set.getString("issue_date");

      if (result_Set.wasNull()) {
        Assert.assertEquals("Test case Passed on Issue date For US1491",
            "Test case Passed on Issue date For US1491");
      } else {
        logger.info(issue_date);
        Assert.assertEquals("Test case Failed For issue date value:", issue_date);
      }

      Assert.assertEquals(result_Set.getString("workflow_state").toLowerCase(), "pending");

      logger.info(duns_Number); // Thread.sleep(50000);

      result_Set.close();

      // Code for US 1457 And US 1491 &US1463-- Pending status validation on
      // Vendor Dashboard,Program
      WebElement current_Duns = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/b[contains(text(),'DUNS')]"));
      logger.info(current_Duns.getText());
      WebElement current_Duns_Value = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/span[contains(text(),'"
              + duns_Number + "')]"));
      logger.info(current_Duns_Value.getText());
      String text_To_Find =
          "Thank you for submitting your application to participate in SBA’s All Small Mentor-Protégé Program. Once your application is processed and evaluated, a member of the All Small Mentor-Protégé Program Office will contact you to verify your application status.";
      WebElement current_Title_Txt = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h4[contains(text(),'"
              + text_To_Find + "')]"));
      logger.info(current_Title_Txt.getText());
      // verify US1463
      String stext = webDriver
          .findElement(By.xpath("//h1[contains(text(),'Program Self-Certification Summary')]"))
          .getText();
      logger.info(stext);
      assertEquals(stext, "All Small Mentor Protégé Program Program Self-Certification Summary");

      Boolean found = false;
      try {
        webDriver.findElement(
            By.xpath("//a[ @class='tab-link' and contains ( text(), 'Certificate Letter' ) ]"));
        found = true;
      } catch (Exception e) {
        logger.info("Good , no Link");
      }
      assertFalse(found);
      webDriver.findElement(By.xpath("//a[@data-method='delete']")).click();
      // Verify US1457
      LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 29);
      login_Data1.Login_With_Reference();
      CommonApplicationMethods.navigationBarClick(webDriver, "Cases");
      AnalystCasesPage.search_Duns_And_Verify(webDriver, duns_Number, "yes", "Pending", "No");



      // Return the Application to vendor
      navigationBarClick(webDriver, "LOGOUT");
      return_All_Applications(webDriver, 29, duns_Number);


    } catch (Exception e) {
      logger.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestWorkflowMPPVendorSelecting8aYes", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}


