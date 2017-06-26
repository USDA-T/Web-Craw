package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.pageObjetcs.vendor_Dashboard_Page.verify_Row_In_A_Table_And_Return;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import gov.sba.utils.integration.FillApplCreatePages;
/**
 * Created by deepa on 6/19/2017.
 */
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowMPPVendor extends TestCase {
  private static final Logger logger = LogManager.getLogger(TestWorkflowMPPVendor.class.getName());
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {

    try {
      // Us1699,!457,1463 Check Dashboard Pending status
      // return_All_Applications(webDriver, 11, duns_Number);
      // delete_All_Application_Draft(webDriver, email, password, duns_Number);
      new LoginPageWithDetails(webDriver, email, password).Login_With_Details();

      String get_Current_Duns_No = duns_Number;
      logger.info(get_Current_Duns_No);
      programs_Page.join_New_Program_CheckBoxes(webDriver, "Mpp");
      FillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", get_Current_Duns_No);
      FillApplCreatePages.finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "Dashboard");

      List<WebElement> all_Cells = verify_Row_In_A_Table_And_Return(webDriver,
          new String[] {"MPP Application", "", "Draft", "", "", "", "Delete"});
      assertNotNull(all_Cells);


      // Check Programs Pending status

      WebElement current_Title = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']//div[contains(@class,'wosb_detail_title')]/h1[text()='All Small Mentor Protégé Program Application Summary']"));
      logger.info(current_Title.getText());
      WebElement current_Title_Business = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h3[contains(text(),'Entity ') and contains(text(),' Legal Business Name')]"));
      logger.info(current_Title_Business.getText());

      Connection databaseConnection = DatabaseUtils.getDatabaseConnection();

      Statement statement_SQL = databaseConnection.createStatement();

      ResultSet result_Set = statement_SQL.executeQuery(
          "select  issue_date, expiry_date, workflow_state from sbaone.certificates A,"
              + "sbaone.organizations where duns_number = '" + get_Current_Duns_No + "'"
              + "and  certificate_type_id =3" + "and workflow_state = 'pending'" + ";");

      // Code for US 1457 And US 1491
      result_Set.next();

      // -- Get Data from DB to test Pending status validation on
      // UI with DB --1457
      String issue_date = result_Set.getString("issue_date");
      String expiry_date = result_Set.getString("expiry_date");
      if (result_Set.wasNull()) {
        Assert.assertEquals("Test case Passed on Issue date For US1491",
            "Test case Passed on Issue date For US1491");
      } else {
        logger.info(issue_date);
        String time_Stamp = expiry_date.toString().substring(0, expiry_date.length() - 16);
        logger.info(time_Stamp);
        DateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-mm-dd");
        // you can change format of date
        // Date date = formatter.parse(time_Stamp);
        // Timestamp timeStampDate = new Timestamp(date.getTime());
        // logger.info(timeStampDate);
      }

      // Expiry date Null for Mpp application
      if (result_Set.wasNull()) {
        Assert.assertEquals("Test case Passed on Expiry date For US1699",
            "Test case Passed on Expiry date For US1699");
      }

      Assert.assertEquals(result_Set.getString("workflow_state").toLowerCase(),
          // duns_Number_status_To_Verify.toLowerCase());
          // result_Set.close();



          // Code for US 1457 And US 1491-- Pending status validation
          // on Vendor Dashboard,Program
          // WebElement current_Duns = webDriver.findElement(By.xpath(
          "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/b[contains(text(),'DUNS')]");
      // logger.info(current_Duns.getText());
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

      webDriver.findElement(By.xpath("//a[@data-method='delete']")).click();

      LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 29);
      login_Data1.Login_With_Reference();

      navigationMenuClick(webDriver, "Cases");
      CommonApplicationMethods.casesPageSearch(webDriver, duns_Number);

      webDriver.findElement(By.xpath("//td/a[contains(text(),'" + duns_Number + "')]")).click();

      WebElement duns_Row_Pending_Check =
          webDriver.findElement(By.xpath("//td[contains(text(),'ending')]"));

      // else Delete it if in Draft all of the Draft applications
      Boolean isPresent =
          (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
      logger.info(isPresent);
      while (isPresent) {
        webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
        logger.info(isPresent);
        isPresent = false;
      }


    } catch (Exception e) {
      logger.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestMPPVenodorWorkflowTC1", "Exception"});
      throw e;

    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();

  }
}

