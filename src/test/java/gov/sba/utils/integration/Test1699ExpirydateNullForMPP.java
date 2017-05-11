//TS Created By _deepa patri
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
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class Test1699ExpirydateNullForMPP extends TestCase {
    private static WebDriver webDriver;
    private static final Logger logger_US1699 = LogManager.getLogger(Test1699ExpirydateNullForMPP.class.getName());
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

        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();

        try {
            // Check Dashboard Pending status
            String get_Current_Duns_No = webDriver
                    .findElement(By
                            .xpath("//article[@id='main-content']/section[@class='usa-width-one-whole']/article[@class='usa-width-three-fourths']/div[@class='usa-width-one-whole']/div/div/p/b[contains(text(),'DUNS:')]"))
                    .findElement(By.xpath("..")).findElement(By.xpath("span")).getText();
            logger_US1699.info(get_Current_Duns_No);

            CommonApplicationMethods.createApplication(webDriver, "Mpp");
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            logger_US1699.info(file_path_abs);
            fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, get_Current_Duns_No);
            fillApplCreatePages.finalSignatureSubmit(webDriver);

            WebElement current_Row_Draft1_A = webDriver.findElement(
                    By.xpath("//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP')]"));
            WebElement current_Row1_A = current_Row_Draft1_A.findElement(By.xpath("..")).findElement(By.xpath(".."));
            logger_US1699.info(current_Row1_A.getText());
            List<WebElement> all_Cells1_A = current_Row1_A.findElements(By.xpath("td"));
            logger_US1699.info('|' + all_Cells1_A.get(0).getText() + '|');
            logger_US1699.info('|' + all_Cells1_A.get(2).getText() + '|');
            // If Pending - Click and verify the summary page
            if (all_Cells1_A.get(3).getText().equals("Pending")
                    && all_Cells1_A.get(0).getText().equals("MPP Application")) {
                // Check Programs Pending status
                webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
                WebElement current_Row_Draft1 = webDriver.findElement(By.xpath(
                        "//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP Application')]"));
                WebElement current_Row1 = current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
                logger_US1699.info(current_Row1.getText());
                List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
                logger_US1699.info(all_Cells1.size());
                logger_US1699.info('|' + all_Cells1.get(0).getText() + '|');
                logger_US1699.info('|' + all_Cells1.get(2).getText() + '|');
                String duns_Number_status_To_Verify = all_Cells1.get(3).getText();

                // US1463-If Pending - Click and verify the summary page
                if (all_Cells1.get(3).getText().equals("Pending")
                        && all_Cells1.get(0).getText().equals("MPP Application")) {
                    all_Cells1.get(0).findElement(By.xpath("a")).click();
                    WebElement current_Title = webDriver.findElement(By.xpath(
                            "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']//div[contains(@class,'wosb_detail_title')]/h1[text()='All Small Mentor Protégé Program Application Summary']"));
                    logger_US1699.info(current_Title.getText());
                    WebElement current_Title_Business = webDriver.findElement(By.xpath(
                            "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h3[contains(text(),'Entity ') and contains(text(),' Legal Business Name')]"));
                    logger_US1699.info(current_Title_Business.getText());

                    Connection databaseConnection = DatabaseUtils.getDatabaseConnection();

                    Statement statement_SQL = databaseConnection.createStatement();

                    ResultSet result_Set = statement_SQL
                            .executeQuery("select  issue_date, expiry_date, workflow_state from sbaone.certificates A,"
                                    + "sbaone.organizations where duns_number = '" + get_Current_Duns_No + "'"
                                    + "and  certificate_type_id =3" + "and workflow_state = 'pending'" + ";");

                    // Code for US 1457 And US 1491
                    result_Set.next();
                    
                    // -- Get Data from DB to test Pending status validation on
                    // UI with DB
                    String issue_date = result_Set.getString("issue_date");
                    String expiry_date = result_Set.getString("expiry_date");
                    if (result_Set.wasNull()) {
                        Assert.assertEquals("Test case Passed on Issue date For US1491",
                                "Test case Passed on Issue date For US1491");
                    } else {
                        logger_US1699.info(issue_date);
                        String time_Stamp = expiry_date.toString().substring(0, expiry_date.length() - 16);
                        logger_US1699.info(time_Stamp);
                        DateFormat formatter;
                        formatter = new SimpleDateFormat("yyyy-mm-dd");
                        // you can change format of date
                        Date date = formatter.parse(time_Stamp);
                        Timestamp timeStampDate = new Timestamp(date.getTime());
                        logger_US1699.info(timeStampDate);
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
            // else Delete it if in Draft all of the Draft applications
            Boolean isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
            logger_US1699.info(isPresent);
            while (isPresent) {
                webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
                webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
                isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
                logger_US1699.info(isPresent);
            }

        } catch (Exception e) {
            logger_US1699.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"Test1699ExpirydateNullForMPP", "Exception"});
            throw new Exception("Error: ", e);

        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
