// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import gov.sba.pageObjetcs.ProgramsPage;
import junit.framework.TestCase;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})
public class TestMPPVenodorWorkflowTC1 extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestMPPVenodorWorkflowTC1.class.getName());
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    // CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {

    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    try {
      // Us1699,!457,1463
      // Check Dashboard Pending status
      String get_Current_Duns_No = webDriver
          .findElement(By.xpath(
              "//article[@id='main-content']/section[@class='usa-width-one-whole']/article[@class='usa-width-three-fourths']/div[@class='usa-width-one-whole']/div/div/p/b[contains(text(),'DUNS:')]"))
          .findElement(By.xpath("..")).findElement(By.xpath("span")).getText();
      logger.info(get_Current_Duns_No);
      ProgramsPage.join_New_Program_CheckBoxes(webDriver, "Mpp");
      FillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", get_Current_Duns_No);
      FillApplCreatePages.finalSignatureSubmit(webDriver);
      WebElement current_Row_Draft1_A = webDriver.findElement(
          By.xpath("//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP')]"));
      WebElement current_Row1_A =
          current_Row_Draft1_A.findElement(By.xpath("..")).findElement(By.xpath(".."));
      logger.info(current_Row1_A.getText());
      List<WebElement> all_Cells1_A = current_Row1_A.findElements(By.xpath("td"));
      logger.info('|' + all_Cells1_A.get(0).getText() + '|');
      logger.info('|' + all_Cells1_A.get(2).getText() + '|');
      // If Pending - Click and verify the summary page
      if (all_Cells1_A.get(3).getText().equals("Pending")
          && all_Cells1_A.get(0).getText().equals("MPP Application")) {
        // Check Programs Pending status
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        WebElement current_Row_Draft1 = webDriver.findElement(By.xpath(
            "//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP Application')]"));
        WebElement current_Row1 =
            current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
        logger.info(current_Row1.getText());
        List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
        logger.info(all_Cells1.size());
        logger.info('|' + all_Cells1.get(0).getText() + '|');
        logger.info('|' + all_Cells1.get(2).getText() + '|');
        String duns_Number_status_To_Verify = all_Cells1.get(3).getText();

        // US1463-If Pending - Click and verify the summary page
        if (all_Cells1.get(3).getText().equals("Pending")
            && all_Cells1.get(0).getText().equals("MPP Application")) {
          all_Cells1.get(0).findElement(By.xpath("a")).click();
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
            Date date = formatter.parse(time_Stamp);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            logger.info(timeStampDate);
          }

          // Expiry date Null for Mpp application
          if (result_Set.wasNull()) {
            Assert.assertEquals("Test case Passed on Expiry date For US1699",
                "Test case Passed on Expiry date For US1699");
          }

          Assert.assertEquals(result_Set.getString("workflow_state").toLowerCase(),
              duns_Number_status_To_Verify.toLowerCase());
          result_Set.close();
        }
      }

      // Code for US 1457 And US 1491-- Pending status validation
      // on Vendor Dashboard,Program
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

      webDriver.findElement(By.xpath("//a[@data-method='TestWorkFlowxx8aInProgress']")).click();

      LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 29);
      login_Data1.Login_With_Reference();

      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      CommonApplicationMethods.casesPageSearch(webDriver, duns_Number);

      webDriver.findElement(By.xpath("//td/a[contains(text(),'" + duns_Number + "')]")).click();

      WebElement duns_Row_Pending_Check =
          webDriver.findElement(By.xpath("//td[contains(text(),'ending')]"));

      // else Delete it if in Draft all of the Draft applications
      Boolean isPresent = (webDriver
          .findElements(By.xpath("//a[@class='TestWorkFlowxx8aInProgress-cert']")).size() > 0);
      logger.info(isPresent);
      while (isPresent) {
        webDriver.findElement(By.xpath("//a[@class='TestWorkFlowxx8aInProgress-cert']")).click();
        webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
        isPresent = (webDriver
            .findElements(By.xpath("//a[@class='TestWorkFlowxx8aInProgress-cert']")).size() > 0);
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

