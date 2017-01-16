package gov.sba.utils;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.WorkflowPages.newMppUploadDocumentPage;
import gov.sba.utils.helpers.FixtureUtils;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class TestApp37OpsSupportStaffChangeBusinessType extends TestCase {
    private static final Logger TestApp37OpsSupportStaffChangeBusinessType = LogManager
            .getLogger(TestApp37OpsSupportStaffChangeBusinessType.class.getName());
    // Set The variabl.es/Define
    WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {

        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 10;
    }

    @Test
    public void testMainTest() throws Exception {
        try {

            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
//            Thread.sleep(2000);

            commonApplicationMethods.deleteApplication(webDriver, "EDWOSB", "Draft");
            commonApplicationMethods.deleteApplication(webDriver, "MPP", "Draft");
            commonApplicationMethods.deleteApplication(webDriver, "WOSB", "Draft");
            
            String duns_Number = webDriver
                    .findElement(
                            By.xpath("//*[@id='main-content']/section/article//p[ (b[contains(text(),'DUNS:')]) ]"))
                    .getText().replaceAll("DUNS:", "").trim();

            TestApp37OpsSupportStaffChangeBusinessType.info(duns_Number);

            if (commonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active")) {

                commonApplicationMethods.onlyReturnAppToVendorMethd(webDriver, 11, duns_Number, "EDWOSB", "Active",
                        get_The_Row_From_Login_Data);

                commonApplicationMethods.deleteApplication(webDriver, "EDWOSB", "Draft");
            }
            if (commonApplicationMethods.checkApplicationExists(webDriver, "WOSB", "Active")) {

                commonApplicationMethods.onlyReturnAppToVendorMethd(webDriver, 11, duns_Number, "WOSB", "Active",
                        get_The_Row_From_Login_Data);

                commonApplicationMethods.deleteApplication(webDriver, "WOSB", "Draft");
            }
            if (commonApplicationMethods.checkApplicationExists(webDriver, "MPP", "Pending")) {
                commonApplicationMethods.onlyReturnAppToVendorMethd(webDriver, 29, duns_Number, "MPP", "Pending",
                        get_The_Row_From_Login_Data);
                commonApplicationMethods.deleteApplication(webDriver, "MPP", "Draft");
            }

            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            login_Data = new LoginPageWithReference(webDriver, 27);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);

            webDriver.findElement(By.id("query")).sendKeys(duns_Number);
            webDriver
                    .findElement(
                            By.xpath("//*[@id='analyst-search']/div/button[ span[contains(text(),'Search')] ]"))
                    .click();
            webDriver.findElement(By.xpath("//*[@id='business_search']/div[h2[contains(text(),'Search Results')]]/div[1]/div/h4/a")).click();
            webDriver.findElement(By.xpath("//a[contains(text(),'endor') and contains(text(),'upport')]")).click();

            webDriver.findElement(By.id("change_business_type_link")).click();
            Select dropdown1 = new Select(webDriver.findElement(By.id("business_type")));
            dropdown1.selectByIndex(1);
            webDriver.findElement(By.id("save")).click();
            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            login_Data = new LoginPageWithReference(webDriver, 10);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);

            //Verify the Business type is changes to Scorp[from LLC]
            webDriver.findElement(By.xpath("//button[@aria-controls='more-details']")).click();
            String business_Type= webDriver.findElement(By.id("more-details")).getText();
//            asse
            assertTrue(business_Type.contains("C-Corporation"));            		
            assertTrue(business_Type.contains("C-Corporation"));            		
            
            // commonApplicationMethods.createApplication(webDriver, "EDWOSB");
            commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
            webDriver.findElement(By.id("certificate_type_edwosb")).click();
            webDriver.findElement(By.id("add_certification")).click();
            webDriver.findElement(By.xpath("//*[@id='js-navigation-menu']/li/a[contains(text(),'Programs')]")).click();
            webDriver.findElement(By.id("certificate_type_edwosb")).click();
            webDriver.findElement(By.id("add_certification")).click();

            ScorpQuestionsPage scorpQuestionsPage = new ScorpQuestionsPage(webDriver);
            scorpQuestionsPage.ScorpQuestions();


        } catch (Exception e) {
            TestApp37OpsSupportStaffChangeBusinessType.info(e.toString());
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
//        webDriver.quit();
    }
}