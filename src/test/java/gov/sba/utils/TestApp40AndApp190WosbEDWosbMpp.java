package gov.sba.utils;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class TestApp40AndApp190WosbEDWosbMpp extends TestCase {
    private static final Logger TestApp40AndApp190 = LogManager.getLogger(TestApp40AndApp190WosbEDWosbMpp.class.getName());
    // Set The variables/Define
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    public void run_This_app(String app_Type_Passed, String duns_No_Passed) throws Exception {

        //Delete if Draft
        TestApp40AndApp190.info(app_Type_Passed);
        commonApplicationMethods.deleteApplication(webDriver, app_Type_Passed, "Draft");

        //Check Pending for MPP
        if (app_Type_Passed.toLowerCase().trim().contentEquals("mpp")) {

            if (!commonApplicationMethods.checkApplicationExists(webDriver, app_Type_Passed, "Pending")) {

                commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
                commonApplicationMethods.createApplication(webDriver, app_Type_Passed);

                String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

                TestApp40AndApp190.info(file_path_abs);
                fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
                fillApplCreatePages.finalSignatureSubmit(webDriver);
                webDriver.findElement(By.xpath("//input[contains(@id,'duns-value')]")).sendKeys("148832876");
                webDriver.findElement(By.xpath("//a[contains(text(), 'Confirm DUNS')]")).click();
//                webDriver.findElement(By.xpath("//inut[@id='duns-value-167']")).sendKeys("148832876");
                
            }

            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 29);
            login_Data.Login_With_Reference();
        } else {
            // For WOSB and EDWOSB Active status - Create new app if not existing
            if (!commonApplicationMethods.checkApplicationExists(webDriver, app_Type_Passed, "Active")) {

                commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
                commonApplicationMethods.createApplication(webDriver, app_Type_Passed);

                String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

                TestApp40AndApp190.info(file_path_abs);
                fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
                fillApplCreatePages.finalSignatureSubmit(webDriver);

            }

            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 11);
            login_Data.Login_With_Reference();
        }


        String xpath = "";

        commonApplicationMethods.navigationMenuClick(webDriver, "Cases");

        //Seperate XPaths for Each Type of Application
        if (app_Type_Passed.toLowerCase().trim().contentEquals("edwosb")) {
            xpath = "//div[@id='table-search']/table/tbody/tr[ "
                    + "td[position()=2]/a[contains(text(),'" + duns_No_Passed + "')]" + " and "
                    + "td[position()=3 and contains(text(),'" + "EDWOSB" + "')]	" + "]";
        }
        if (app_Type_Passed.toLowerCase().trim().contentEquals("wosb")) {
            xpath = "//div[@id='table-search']/table/tbody/tr[ "
                    + "td[position()=2]/a[contains(text(),'" + duns_No_Passed + "')]" + " and "
                    + "td[position()=3 and contains(text(),'" + "WOSB" + "')]	" + " and "
                    + "td[position()=3 and not(contains(text(),'" + "EDWOSB" + "')) ]	" + "]";
        }
        if (app_Type_Passed.toLowerCase().trim().contentEquals("mpp")) {
            xpath = "//div[@id='table-search']/table/tbody/tr[ "
                    + "td[position()=2]/a[contains(text(),'" + duns_No_Passed + "')]" + " and "
                    + "td[position()=3 and contains(text(),'MPP')]	"
                    + "]";
        }


        TestApp40AndApp190.info(app_Type_Passed.toLowerCase().trim());
        TestApp40AndApp190.info(xpath);


        // For all app types click on the Application and go to all cases pages
        List<WebElement> current_Row = webDriver.findElements(By.xpath(xpath));
        TestApp40AndApp190.info(current_Row.size() + ": Is the total Under Review Elements");
        if (current_Row.size() > 0) {
            WebElement a1 = current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
            TestApp40AndApp190.info(a1.getText());
            a1.click();
        }

        webDriver.findElement(By.xpath("//*[@id='submit_button']")).click();
        webDriver.findElement(By.xpath("//*[@id='save_notes']")).click();

        if (
                app_Type_Passed.toLowerCase().trim().contentEquals("wosb") ||
                        app_Type_Passed.toLowerCase().trim().contentEquals("mpp")
                ) {
            List<WebElement> check_Side_Panels = webDriver.findElements(By.xpath(
                    "//ul[contains(@class,'usa-sidenav-list')]" +
                            "/li/a[contains(text(),'inancial') and contains(text(),'eview')]"
            ));
            Assert.assertEquals(check_Side_Panels.size(), 0);

            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));

        }
        if (app_Type_Passed.toLowerCase().trim().contentEquals("edwosb")) {
            List<WebElement> check_Side_Panels = webDriver.findElements(By.xpath(
                    "//ul[contains(@class,'usa-sidenav-list')]" +
                            "/li/a[contains(text(),'inancial') and contains(text(),'eview')]"
            ));

            Assert.assertTrue(check_Side_Panels.size() > 0);

            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            webDriver.findElement(By.xpath("//div[contains(@class, 'review_main')]/h1[contains(text(),'etermination')]"));

        }

    }

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 10;
    }

    @Test
    public void testMainTest() throws Exception {

        // Login to dashboard- Check teh EDWOSB application in Active status
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        String get_Current_Duns_No = webDriver
                .findElement(By
                        .xpath("//article[@id='main-content']/section[@class='usa-width-one-whole']/" +
                                "article[@class='usa-width-three-fourths']/" +
                                "div[@class='usa-width-one-whole']/div/div/p/b[contains(text(),'DUNS:')]"))
                .findElement(By.xpath("..")).findElement(By.xpath("span")).getText();
        TestApp40AndApp190.info(get_Current_Duns_No);

        run_This_app("WOSB", get_Current_Duns_No);

        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, 10);
        login_Data.Login_With_Reference();

        // Currently defective - need to Update code once QA is deployed.
        //run_This_app("EDWOSB", get_Current_Duns_No);

        commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, 10);
        login_Data.Login_With_Reference();

        run_This_app("MPP", get_Current_Duns_No);
        //commonApplicationMethods.checkApplicationExists(webDriver, "WOSB", "Active");
        //commonApplicationMethods.returnAppToVendorMethd(webDriver, 11,"159165917","WOSB", "Active",10);
    }

}