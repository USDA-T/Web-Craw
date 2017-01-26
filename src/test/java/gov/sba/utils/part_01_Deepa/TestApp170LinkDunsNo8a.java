package gov.sba.utils.part_01_Deepa;

import gov.sba.utils.CommonFunctions.LoginPageWithDetails;
import gov.sba.utils.CommonFunctions.LoginPageWithReference;
import gov.sba.utils.CommonFunctions.TestHelpers;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
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

public class TestApp170LinkDunsNo8a extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp170LinkDunsNo = LogManager.getLogger(TestApp170LinkDunsNo8a.class.getName());
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        commonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        commonApplicationMethods.focus_window();
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
            commonApplicationMethods.createApplication(webDriver, "8A");
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
            TestApp170LinkDunsNo.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs) ;
            fillApplCreatePages.finalSignatureSubmit8A(webDriver) ;
            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");


            // Click on Case Link on main navigator-- 8(a) Analyst
            LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 35);
            login_Data_01.Login_With_Reference();
            Thread.sleep(2000);

            String typ_App_Passed = "8(a) Document Upload";
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            if (! webDriver.getPageSource().contains("No results found")) {
                // All cases page 8(a) Analyst
                String xpath_Value = "//div[@id='table-search']/table/tbody/tr[ " + "td/a[contains(text(),'" + duns_Number
                        + "')]	and " + "td[position()=3 and (text() = '" + typ_App_Passed + "')]" + "]";
                 List<WebElement>  current_Row = webDriver.findElements(By.xpath(xpath_Value));
                // Vendor Overview page should display clicking on Duns Number Link
                if (current_Row.size() > 0) {
                    current_Row.get(0).findElement(By.xpath("td[2]/a")).click();

                    WebElement asset_Exists = webDriver.findElement(
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