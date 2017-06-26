//TS_Created_By_Deepa_Patri
package gov.sba.others;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import gov.sba.utils.integration.FillApplCreatePages;


import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.analyst_Cases_Page;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;


@Ignore
@Category({gov.sba.utils.integration.StableTests.class})


public class TestUS1457MPPPending extends TestCase {
  private static final Logger logger_US1457 =
          LogManager.getLogger(TestUS1457MPPPending.class.getName());
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
        
    webDriver.get(TestHelpers.getBaseUrl());
    //CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {

    // Login to dashboard.
    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    try {

      programs_Page.join_New_Program_CheckBoxes(webDriver, "MPP");
      webDriver.findElement(By.id("answers_117_value_yes")).click();
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      FillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", duns_Number);
      FillApplCreatePages.finalSignatureSubmit(webDriver);
      logger_US1457.info("Doc has been uploaded.");

      List<WebElement> count_Pending = webDriver.findElements(By.xpath(
              "//*[@id='certifications']/tbody/tr" +
                      "[ " +
                      "     td[position()=1]/a[contains(text(),'MPP')] and " +
                      "     td[position()=5 and (contains(text(),'ending'))] " +
                      "]"));

      assertEquals(count_Pending.size(), 1);

      click_Element(webDriver, "SBA_Application_All_Cases_Page_MPP");

      logger_US1457.info(find_Element(webDriver, "SBA_MPP_Self_Cert_Summ_Title").getText());
      logger_US1457.info(find_Element(webDriver, "SBA_MPP_Self_Cert_Summ_Name").getText());

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
        logger_US1457.info(issue_date);
        Assert.assertEquals("Test case Failed For issue date value:", issue_date);
      }

      Assert.assertEquals(result_Set.getString("workflow_state").toLowerCase(), "pending");

      logger_US1457.info(duns_Number); // Thread.sleep(50000);

      result_Set.close();

      // Code for US 1457 And US 1491-- Pending status validation on
      // Vendor Dashboard,Program
      WebElement current_Duns = webDriver.findElement(By.xpath(
              "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/b[contains(text(),'DUNS')]"));
      logger_US1457.info(current_Duns.getText());
      WebElement current_Duns_Value = webDriver.findElement(By.xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/span[contains(text(),'" + duns_Number + "')]"));
      logger_US1457.info(current_Duns_Value.getText());
      String text_To_Find = "Thank you for submitting your application to participate in SBA’s All Small Mentor-Protégé Program. Once your application is processed and evaluated, a member of the All Small Mentor-Protégé Program Office will contact you to verify your application status.";
      WebElement current_Title_Txt = webDriver.findElement(By.xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h4[contains(text(),'" + text_To_Find + "')]"));
      logger_US1457.info(current_Title_Txt.getText());

      webDriver.findElement(By.xpath("//a[@data-method='delete']")).click();

      LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 29);

      logger_US1457.info(login_Data1);
      login_Data1.Login_With_Reference();
      logger_US1457.info(login_Data1);
      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      analyst_Cases_Page.search_Duns_And_Verify(webDriver, duns_Number, "yes", "Pending", "No");

    } catch (Exception e) {
      logger_US1457.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{TestUS1457MPPPending.class.getName(), "Exception"});
        throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

}
