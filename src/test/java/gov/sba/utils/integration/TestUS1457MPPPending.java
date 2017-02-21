package gov.sba.utils.integration;

import static gov.sba.utils.integration.CommonApplicationMethods.return_Db_URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

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

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestUS1457MPPPending extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1457 = LogManager.getLogger(TestUS1457MPPPending.class.getName());
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        CommonApplicationMethods.focus_window();
        String[] details = CommonApplicationMethods.return_Good_Duns_no();
        email = details[0];
        password = details[1];
        duns_Number = details[2];
    }

    @Test
    public void testMainTest() throws Exception {

        // Login to dashboard.
        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();
        Thread.sleep(3000);

        try {

            CommonApplicationMethods.createApplication(webDriver, "MPP");
            webDriver.findElement(By.id("answers_117_value_yes")).click();
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, duns_Number);
            fillApplCreatePages.finalSignatureSubmit(webDriver);
            logger_US1457.info("Doc has been uploaded.");

            List<WebElement> count_Pending = webDriver.findElements(
                    By.xpath("//*[@id='certifications']/tbody/tr" + "[" + "td[position()=1]/a[contains(text(),'MPP')]"
                            + " and " + "td[position()=4 and (contains(text(),'ending'))]" + "]"));

            assertEquals(count_Pending.size(), 1);

            CommonApplicationMethods.clickOnApplicationAllCasesPage(webDriver, "MPP");

            WebElement current_Title = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']//div[contains(@class,'wosb_detail_title')]/h1[text()='All Small Mentor Protégé Program Application Summary']"));

            logger_US1457.info(current_Title.getText());
            WebElement current_Title_Business = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h3[contains(text(),'Entity ') and contains(text(),' Legal Business Name')]"));
            logger_US1457.info(current_Title_Business.getText());

            // Connect SBAONE QA DB -to get data from DB
            String url = return_Db_URL();
            Properties props = new Properties();
            props.setProperty("user", "app_ruby");
            props.setProperty("password", "rubypassword");
            Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
            logger_US1457.info(connection_SBA_One_Qa);
            Statement statement_SQL = connection_SBA_One_Qa.createStatement();
            ResultSet result_Set = statement_SQL
                    .executeQuery("select  issue_date, expiry_date, workflow_state from sbaone.certificates A,"
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
            WebElement current_Duns_Value = webDriver.findElement(By
                    .xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/span[contains(text(),'"
                            + duns_Number + "')]"));
            logger_US1457.info(current_Duns_Value.getText());
            String text_To_Find = "Thank you for submitting your application to participate in SBA’s All Small Mentor-Protégé Program. Once your application is processed and evaluated, a member of the All Small Mentor-Protégé Program Office will contact you to verify your application status.";
            WebElement current_Title_Txt = webDriver.findElement(By
                    .xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h4[contains(text(),'"
                            + text_To_Find + "')]"));
            logger_US1457.info(current_Title_Txt.getText());

            webDriver.findElement(By.xpath("//a[@data-method='delete']")).click();
            Thread.sleep(3000);

            LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 29);

            logger_US1457.info(login_Data1);
            login_Data1.Login_With_Reference();
            logger_US1457.info(login_Data1);
            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

            webDriver.findElement(By.xpath("//td/a[contains(text(),'" + duns_Number + "')]")).click();

            WebElement duns_Row_Pending_Check = webDriver.findElement(By.xpath("//td[contains(text(),'ending')]"));

            // else Delete it if in Draft all of the Draft applications
            Boolean isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
            logger_US1457.info(isPresent);
            while (isPresent) {
                webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
                webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
                isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
                logger_US1457.info(isPresent);
            }

        } catch (Exception e) {
            logger_US1457.info(e.toString());
            throw new Exception("Error: ", e);
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

}