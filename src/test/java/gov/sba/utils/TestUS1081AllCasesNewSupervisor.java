package gov.sba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;
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

public class TestUS1081AllCasesNewSupervisor extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1081 = LogManager.getLogger(TestUS1081AllCasesNewSupervisor.class.getName());
    int get_The_Row_From_Login_Data;
    String duns_Number;

    @Before
    public void setUp() throws Exception {
        commonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 21;
        duns_Number = LoginHelpers.getLoginDataWithIndex(get_The_Row_From_Login_Data).getDunsNumber();
    }

    @Test
    public void testMainTest() throws Exception {

        commonApplicationMethods.return_all_Applications(webDriver, 11, duns_Number);
        commonApplicationMethods.return_all_Applications(webDriver, 29, duns_Number);

        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        commonApplicationMethods.delete_all_Drafts(webDriver);
        Thread.sleep(3000);

        try {

            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            Thread.sleep(1000);
            logger_US1081.info("Cases link is on Main Navigator is Clicked");

            String Allcases_PageTitle = webDriver
                    .findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'All cases')]")).getText();
            assertEquals(Allcases_PageTitle, "All cases");

            String[] header_Names_Array = new String[] { "Business name", "DUNS", "Program", "Review type", "Active",
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

            String url = "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
            Properties props = new Properties();
            props.setProperty("user", "app_etl");
            props.setProperty("password", "etlpassworddev");
            Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
            logger_US1081.info(connection_SBA_One_Qa);

            Statement statement_SQL = connection_SBA_One_Qa.createStatement();
            ResultSet result_Set = statement_SQL.executeQuery(" SELECT F.legal_business_name AS legal_Name, "
                    + "		 C.duns_number AS duns_No, " + "		 G.name AS cert_Name, "
                    + "		 to_char(A.application_submitted_at, 'mm/dd/yyyy') AS sub_Date , "
                    + "		 A.workflow_state AS sub_Status" + " 	FROM "
                    + "			sbaone.sba_applications A INNER JOIN sbaone.organizations C ON (A.organization_id = C.id), "
                    + "			sbaone.certificate_types 		G , " + "			reference.sam_organizations 	F"
                    + " 	where(  A.workflow_state 		= 'submitted'				"
                    + "       AND A.progress 				->>'current' = 'signature')	"
                    + "       AND A.certificate_type_id 	= G.id						"
                    + "       AND C.duns_number 			= F.duns"
                    + "		order by sub_Date Asc, cert_Name Desc ;					");

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
            List<WebElement> rows_Body = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr")); // Get
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

            WebElement current_Row_EDWOSB = webDriver.findElement(By.xpath(
                    "//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='EDWOSB']"));
            // WebElement current_Row_WOSB =
            // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
            WebElement current_Row_EDWOSB_Parent = current_Row_EDWOSB.findElement(By.xpath("parent::node()"));
            logger_US1081.info(current_Row_EDWOSB_Parent.getAttribute("innerHTML"));
            WebElement current_Row_EDWOSB_Link = current_Row_EDWOSB_Parent
                    .findElement(By.xpath("//td/a[contains(text(),'Entity 454 ')]"));
            logger_US1081.info(current_Row_EDWOSB_Link.getAttribute("innerHTML"));
            current_Row_EDWOSB_Link.click();

            WebElement current_Page_Title = webDriver.findElement(By.xpath(
                    "//article[@id='main-content']/div[@id='business_search']/div/h4[contains(text(),'Legal Business Name')]"));
            logger_US1081.info(current_Page_Title.getText());

            // Return to vendor link
            WebElement return_to_vendor_link = webDriver.findElement(
                    By.xpath("//a[contains(@class,'confirmation') and contains(@href,'/dashboard/resubmit')]"));
            logger_US1081.info(return_to_vendor_link.getText());
            return_to_vendor_link.click();

        }

        catch (Exception e) {
            logger_US1081.info("Cases link is on Main Navigator is not present" + e.toString());
            // logger_US1081.info("test failed as return_vendor link dididnot
            // work");
        }

    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}