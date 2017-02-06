package gov.sba.utils.integration;


import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Testapp244AllCases8a extends TestCase {
    // Set The variables/Define
    private static WebDriver webDriver;
    String duns_Number, email, password;
    private static final Logger logger_244 = LogManager.getLogger(VerifyWosbFlow.class.getName());

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

        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();
        Thread.sleep(1500);
        //Create 2 MPP Apps
            CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
            CommonApplicationMethods.createApplication(webDriver, "8a");
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            logger_244.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            fillApplCreatePages.finalSignatureSubmit8A(webDriver);


        //Login as Analyst
        CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
        LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 35);
        login_Data_01.Login_With_Reference();

        CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

        //Check Column 1 - Business Name - Ascending
        List<WebElement> row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[1]"));
        Boolean something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == 1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 1 - Business Name - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[1]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[1]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == -1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________
        //Check Column 2 - Duns - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[2]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[2]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == 1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 2 - Duns - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[2]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[2]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == -1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________
        //Check Column 3 - Pgm - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[3]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[3]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == 1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 3 - Pgm - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[3]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[3]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == -1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________
        //Check Column 5 - Submitted - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[5]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[5]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == 1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 5 - Submitted - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[5]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[5]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == -1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________
        //Check Column 8 - Status - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[8]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[8]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == 1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 8 - Status - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[8]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[8]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) == -1) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________



        //Check Column 4 - Review Type - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[4]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[4]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) > 0) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 3 - Review Type - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[4]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[4]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) < 0) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________

        //Check Column 6 - Owner - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[6]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[6]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) > 0) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 6 - Owner - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[6]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[6]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) < 0) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________

        //Check Column 7 - Current Reviewer - Descending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[7]")).click();

        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[7]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) > 0) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //Check Column 7 - Current Reviewer - Ascending
        webDriver.findElement(By.xpath("//div[@id='table-search']/div/table/thead/tr/th[7]")).click();
        something = false;
        row_cell= webDriver.findElements(By.xpath("//div[@id='table-search']/div/table/tbody/tr/td[7]"));
        something = false;
        for (int i = 0; i < row_cell.size()-1; i++) {
            if ((row_cell.get(i).getText().trim().compareTo(row_cell.get(i+1).getText().trim())) < 0) {
                something = true;
                break;
            }
        }
        assertFalse(something);
        //_________________________________________________________________________________



    }
    @After
    public void tearDown () throws Exception {
//        webDriver.quit();
    }
}