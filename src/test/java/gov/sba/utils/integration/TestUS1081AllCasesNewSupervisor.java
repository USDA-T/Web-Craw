package gov.sba.utils.integration;

import static gov.sba.utils.integration.CommonApplicationMethods.return_Db_URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
public class TestUS1081AllCasesNewSupervisor extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1081 = LogManager.getLogger(TestUS1081AllCasesNewSupervisor.class.getName());
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

        // Create a application to check
        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();
        Thread.sleep(3000);

        CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        CommonApplicationMethods.createApplication(webDriver, "WOSB");
        String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
        logger_US1081.info(file_path_abs);
        fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
        fillApplCreatePages.finalSignatureSubmit(webDriver);
        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

        // Login to verify analyst Dashboard
        LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 31);
        login_Data_01.Login_With_Reference();
        Thread.sleep(3000);

        try {

            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            Thread.sleep(1000);
            logger_US1081.info("Cases link is on Main Navigator is Clicked");

            String Allcases_PageTitle = webDriver
                    .findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'All cases')]")).getText();
            assertEquals(Allcases_PageTitle, "All cases");

            String[] header_Names_Array = new String[] { "Business name", "DUNS", "Program", "Review type", "Submitted",
                    "Owner", "Current reviewer", "Status" };

            List<WebElement> rows_Header = webDriver
                    .findElements(By.xpath("//div[@id='table-search']/table/thead/tr/th"));
            // Get Table Header Cells
            String[] header_Names_Array_Validate = new String[8];
            java.util.Iterator<WebElement> list_elements = rows_Header.iterator();
            int i = 0;
            while (list_elements.hasNext()) {
                header_Names_Array_Validate[i] = list_elements.next().getText();
                i = i + 1;
            }

            Assert.assertArrayEquals(header_Names_Array, header_Names_Array_Validate);

            String url = return_Db_URL();
            Properties props = new Properties();
            props.setProperty("user", "app_ruby");
            props.setProperty("password", "rubypassword");
            Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
            logger_US1081.info(connection_SBA_One_Qa);

            Statement statement_SQL = connection_SBA_One_Qa.createStatement();
            ResultSet result_Set = statement_SQL.executeQuery(" SELECT F.legal_business_name AS legal_Name, "
                    + "		 C.duns_number AS duns_No, " + "		 G.name AS cert_Name, "
                    + "		 to_char(A.application_submitted_at, 'mm/dd/yyyy') AS sub_Date , "
                    + "		 I.workflow_state AS sub_Status" + " 	FROM "
                    + "			sbaone.sba_applications A INNER JOIN sbaone.organizations C ON (A.organization_id = C.id), "
                    + "			sbaone.certificate_types 		G , " + "			reference.mvw_sam_organizations 	F,"
                    + "			sbaone.certificates I"
                    + " 	where(  A.workflow_state 		= 'submitted'				"
                    + "       AND A.progress 				->>'current' = 'signature')	"
                    + "       AND C.duns_number 			= F.duns" + "       AND C.duns_number 			= '"
                    + duns_Number + "' " + "       and G.name != 'mpp' " + "       and G.name != 'eight_a' "
                    + "       and G.name != 'edwosb' " + "       and  I.organization_id =  A.organization_id "
                    + "		order by sub_Date Asc, cert_Name Desc ;	");

            List<ArrayList<String>> db_rows_array = new ArrayList<>();
            while (result_Set.next()) {
                ArrayList<String> db_rows_Cell = new ArrayList<>(); // Add
                // inside a second Dimension Array
                db_rows_Cell.add(result_Set.getString("legal_Name").toUpperCase());
                db_rows_Cell.add(result_Set.getString("duns_No"));
                db_rows_Cell.add(result_Set.getString("cert_Name").toUpperCase());
                db_rows_Cell.add(result_Set.getString("sub_Date"));
                db_rows_Cell.add(result_Set.getString("sub_Status").toUpperCase());
                // Add a Row
                db_rows_array.add(db_rows_Cell);
            }
            logger_US1081.info(db_rows_array.toString()); // Thread.sleep(50000);
            result_Set.close();

            // Entire Table Verification for next Sprint
            List<ArrayList<String>> ui_rows_array = new ArrayList<>();

            List<WebElement> rows_Body = webDriver.findElements(
                    By.xpath("//div[@id='table-search']/table/tbody/tr[ td[position()=2]/a[ contains( text(),'"
                            + duns_Number + "') ] ]")); // Get
            // the Table rows /logger.info(rows_Body.size());
            for (int j = 0; j < rows_Body.size(); j++) {
                ArrayList<String> ui_rows_Cell = new ArrayList<>();
                logger_US1081.info(rows_Body.get(j).getAttribute("innerHTML"));
                List<WebElement> rows_Body_Cells = rows_Body.get(j).findElements(By.xpath("td")); // Get
                                                                                                  // the
                                                                                                  // Table
                                                                                                  // Cells
                ui_rows_Cell.add(rows_Body_Cells.get(0).getText().toUpperCase());
                ui_rows_Cell.add(rows_Body_Cells.get(1).getText());
                ui_rows_Cell.add(rows_Body_Cells.get(2).getText().toUpperCase());
                ui_rows_Cell.add(rows_Body_Cells.get(4).getText());
                ui_rows_Cell.add(rows_Body_Cells.get(7).getText().toUpperCase());
                ui_rows_array.add(ui_rows_Cell);
            }
            logger_US1081.info(ui_rows_array.toString());
            for (int j = 0; j < ui_rows_array.size(); j++) {
                for (int k = 0; k < ui_rows_array.get(j).size(); k++) {
                    assertEquals(ui_rows_array.get(j).get(k), db_rows_array.get(j).get(k));
                }
            }

            rows_Body.get(0).findElement(By.xpath("td[position()=1]/a")).click();
            webDriver
                    .findElement(
                            By.xpath("//div[contains(@class,'review_nav')]/p/a[contains(text(),'Vendor Overview')]"))
                    .click();
            Thread.sleep(1500);
            webDriver.findElement(By.linkText("Return to Vendor")).click();
            Thread.sleep(1500);
            try {
                webDriver.switchTo().alert().accept();
            } catch (Exception e) {
                logger_US1081.info("None Alert");
            }

            List<WebElement> rows_Body_01 = webDriver.findElements(By
                    .xpath("//table[@id='certifications']/tbody/tr[ td[position()=4 and contains(text(),'Draft')]  ]")); // Get
            Assert.assertTrue(rows_Body_01.size() > 0);
            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            Assert.assertFalse(webDriver.getPageSource().contains(duns_Number));

        }

        catch (Exception e) {
            logger_US1081.info("Cases link is on Main Navigator is not present" + e.toString());
            throw e;
        }

    }



  @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
