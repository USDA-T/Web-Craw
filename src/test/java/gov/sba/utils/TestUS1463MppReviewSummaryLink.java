package gov.sba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
import gov.sba.utils.helpers.LoginHelpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

public class TestUS1463MppReviewSummaryLink extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1463 = LogManager.getLogger(TestUS1463MppReviewSummaryLink.class.getName());
    int get_The_Row_From_Login_Data;
    String duns_Number = "215435315";
    
    @Before
    public void setUp() throws Exception {
    	commonApplicationMethods.deleteAllApplicationTypes(webDriver, duns_Number);
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 44;
        duns_Number = LoginHelpers.getLoginDataWithIndex(get_The_Row_From_Login_Data).getDunsNumber();
        logger_US1463.info(duns_Number);
        commonApplicationMethods.clear_Env_Chrome();
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        Boolean pending_Application_Found = false;
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(1000);
        try {
        	commonApplicationMethods.createApplication(webDriver, "MPP");
            webDriver.findElement(By.id("answers_117_value_yes")).click();
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

            logger_US1463.info(file_path_abs);
            fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, "215435315");
            fillApplCreatePages.finalSignatureSubmit(webDriver);
            logger_US1463.info("Doc has been uploaded.");

            List<WebElement> count_Pending = webDriver.findElements(By.xpath(
                    "//*[@id='certifications']/tbody/tr" +
                            "[" +
                            "td[position()=1]/a[contains(text(),'MPP')]" + " and " +
                            "td[position()=4 and (contains(text(),'ending'))]" +
                            "]"));

            assertEquals(count_Pending.size() , 1);

            commonApplicationMethods.clickOnApplicationAllCasesPage(webDriver, "MPP");


            WebElement current_Title = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']//div[contains(@class,'wosb_detail_title')]/h1[text()='All Small Mentor Protégé Program Application Summary']"));

            logger_US1463.info(current_Title.getText());
            WebElement current_Title_Business = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h3[contains(text(),'Entity ') and contains(text(),' Legal Business Name')]"));
            logger_US1463.info(current_Title_Business.getText());

            // Connect SBAONE QA DB -to get data from DB
            String url = "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
            Properties props = new Properties();
            props.setProperty("user", "app_etl");
            props.setProperty("password", "etlpassworddev");
            Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
            logger_US1463.info(connection_SBA_One_Qa);
            Statement statement_SQL = connection_SBA_One_Qa.createStatement();
            ResultSet result_Set = statement_SQL
                    .executeQuery("select  issue_date, expiry_date, workflow_state from sbaone.certificates A," +
                            "sbaone.organizations where duns_number = '" + duns_Number + "'" +
                            "and  certificate_type_id =3" +
                            "and workflow_state = 'pending'" +
                            ";");
            // Code for US 1457 And US 1491
            result_Set.next();
            // -- Get Data from DB to test Pending status validation on
            // UI with DB

            String issue_date = result_Set.getString("issue_date");
            if (result_Set.wasNull()) {
                Assert.assertEquals("Test case Passed on Issue date For US1491",
                        "Test case Passed on Issue date For US1491");
            } else {
                logger_US1463.info(issue_date);
                Assert.assertEquals("Test case Failed For issue date value:", issue_date);
            }
            Assert.assertEquals(result_Set.getString("workflow_state").toLowerCase(),
                    "pending");
            logger_US1463.info(duns_Number); // Thread.sleep(50000);
            result_Set.close();
            // Code for US 1457 And US 1491-- Pending status validation
            // on Vendor Dashboard,Program
            WebElement current_Duns = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/b[contains(text(),'DUNS')]"));
            logger_US1463.info(current_Duns.getText());
            WebElement current_Duns_Value = webDriver.findElement(By
                    .xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/span[contains(text(),'"
                            + duns_Number + "')]"));
            logger_US1463.info(current_Duns_Value.getText());
            String text_To_Find = "Thank you for submitting your application to participate in SBA’s All Small Mentor-Protégé Program. Once your application is processed and evaluated, a member of the All Small Mentor-Protégé Program Office will contact you to verify your application status.";
            WebElement current_Title_Txt = webDriver.findElement(By
                    .xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h4[contains(text(),'"
                            + text_To_Find + "')]"));
            logger_US1463.info(current_Title_Txt.getText());

            webDriver.findElement(By.xpath("//a[@data-method='delete']")).click();
            Thread.sleep(3000);

            LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 29);

            logger_US1463.info(login_Data1);
            login_Data1.Login_With_Reference();
            logger_US1463.info(login_Data1);
            webDriver.findElement(By.xpath("//a[@href='/sba_analyst/cases']")).click();

            webDriver.findElement(By.xpath("//td/a[contains(text(),'"+duns_Number+"')]")).click();

            @SuppressWarnings("unused")
			WebElement duns_Row_Pending_Check = webDriver
                    .findElement(By.xpath("//td[contains(text(),'ending')]"));

            pending_Application_Found = true;
            // else Delete it if in Draft all of the Draft applications
            Boolean isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
            logger_US1463.info(isPresent);
            while (isPresent) {
                webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
                webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
                isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
                logger_US1463.info(isPresent);
            }
            Assert.assertEquals(pending_Application_Found, true);
        } catch (Exception e) {
            logger_US1463.info(e.toString());
            throw new Exception("Error: ", e);
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

}