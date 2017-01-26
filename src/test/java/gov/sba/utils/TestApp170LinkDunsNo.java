package gov.sba.utils;

import java.util.List;

import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
import gov.sba.utils.helpers.LoginHelpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import junit.framework.TestCase;

public class TestApp170LinkDunsNo extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp170LinkDunsNo = LogManager.getLogger(TestApp170LinkDunsNo.class.getName());
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        commonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        String[] details = commonApplicationMethods.return_Good_Duns_no();
        email = details[0];
        password = details[1];
        duns_Number = details[2];
    }

    @Test
    public void testMainTest() throws Exception {
        try {

            LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();
            Thread.sleep(3000);

            commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
            commonApplicationMethods.createApplication(webDriver, "WOSB");
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            TestApp170LinkDunsNo.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            fillApplCreatePages.finalSignatureSubmit(webDriver) ;
            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");


            LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
            login_Data_01.Login_With_Reference();

            // Click on Case Link on main navigator-- SBA Analyst
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            String xpath_Value;
            List<WebElement> current_Row;
            WebElement asset_Exists;
            String typ_App_Passed = "WOSB";

            if (! webDriver.getPageSource().contains("No results found")){
                xpath_Value = "//div[@id='table-search']/table/tbody/tr[ " + "td/a[contains(text(),'" + duns_Number
                        + "')]	and " + "td[position()=3 and (text()= '" + typ_App_Passed + "')]" + "]";
                // All cases page
                TestApp170LinkDunsNo.info(xpath_Value);
                 current_Row = webDriver.findElements(By.xpath(xpath_Value));
                TestApp170LinkDunsNo.info(current_Row.size());
                if (current_Row.size() > 0) {
                    TestApp170LinkDunsNo.info(current_Row.get(0).getText());
                    current_Row.get(0).findElement(By.xpath("td[2]/a")).click();
                    asset_Exists = webDriver.findElement(
                            By.xpath("//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]"));
                    // Vendor Overview page should display clicking on Duns Number Link
                    assertEquals(asset_Exists.getText(), "DUNS:" + duns_Number);
                }

            }
            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");

            login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();
            Thread.sleep(3000);

            commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
            commonApplicationMethods.createApplication(webDriver, "MPP");
            file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            TestApp170LinkDunsNo.info(file_path_abs);
            fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, duns_Number) ;
            fillApplCreatePages.finalSignatureSubmit(webDriver) ;
            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");


            // Click on Case Link on main navigator-- Mpp Analyst
             login_Data_01 = new LoginPageWithReference(webDriver, 29);
            login_Data_01.Login_With_Reference();
            Thread.sleep(2000);
            typ_App_Passed = "MPP";
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            if (! webDriver.getPageSource().contains("No results found")) {
                // All cases page for Mpp Analyst
                xpath_Value = "//div[@id='table-search']/table/tbody/tr[ " + "td/a[contains(text(),'" + duns_Number
                        + "')]	and " + "td[position()=3 and (text() = '" + typ_App_Passed + "')]" + "]";
                current_Row = webDriver.findElements(By.xpath(xpath_Value));
                // Vendor Overview page should display clicking on Duns Number Link
                if (current_Row.size() > 0) {
                    current_Row.get(0).findElement(By.xpath("td[2]/a")).click();
                    asset_Exists = webDriver.findElement(
                            By.xpath("//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]"));
                    assertEquals(asset_Exists.getText(), "DUNS:" + duns_Number);

                }
                commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            }


            // Click on Case Link on main navigator-- 8(a) Analyst
            login_Data_01 = new LoginPageWithReference(webDriver, 35);
            login_Data_01.Login_With_Reference();
            Thread.sleep(2000);

            typ_App_Passed = "8(a) Document Upload";
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            TestApp170LinkDunsNo.info(webDriver.getPageSource());
            if (! webDriver.getPageSource().contains("No results found")) {
                // All cases page 8(a) Analyst
                xpath_Value = "//div[@id='table-search']/table/tbody/tr[ " + "td/a[contains(text(),'" + duns_Number
                        + "')]	and " + "td[position()=3 and (text() = '" + typ_App_Passed + "')]" + "]";
                current_Row = webDriver.findElements(By.xpath(xpath_Value));
                // Vendor Overview page should display clicking on Duns Number Link
                if (current_Row.size() > 0) {
                    current_Row.get(0).findElement(By.xpath("td[2]/a")).click();

                    asset_Exists = webDriver.findElement(
                            By.xpath("//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]"));
                    assertEquals(asset_Exists.getText(), "DUNS:" + duns_Number);
                }
            }

        } catch (Exception e) {
            TestApp170LinkDunsNo.info(e.toString());
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}