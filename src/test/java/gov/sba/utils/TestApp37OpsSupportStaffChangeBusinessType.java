package gov.sba.utils;

import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.helpers.LoginHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TestApp37OpsSupportStaffChangeBusinessType extends TestCase {
    private static final Logger logger_37OpsSpStfCh = LogManager
            .getLogger(TestApp37OpsSupportStaffChangeBusinessType.class.getName());
    // Set The variabl.es/Define
    WebDriver webDriver;
    int get_The_Row_From_Login_Data;
    String duns_Number;

    @Before
    public void setUp() throws Exception {

        commonApplicationMethods.clear_Env_Chrome();
        Thread.sleep(3000);
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 44;
        duns_Number = LoginHelpers.getLoginDataWithIndex(get_The_Row_From_Login_Data).getDunsNumber();
        logger_37OpsSpStfCh.info(duns_Number);
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        try{

            Boolean pending_Application_Found = false;
            logger_37OpsSpStfCh.info(pending_Application_Found);

            commonApplicationMethods.return_all_Applications(webDriver, 29, duns_Number);
            commonApplicationMethods.return_all_Applications(webDriver, 29, duns_Number);
            commonApplicationMethods.return_all_Applications(webDriver, 11, duns_Number);

            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(3000);

            commonApplicationMethods.delete_all_Drafts(webDriver);

                commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                login_Data = new LoginPageWithReference(webDriver, 27);
                login_Data.Login_With_Reference();
                Thread.sleep(2000);
                commonApplicationMethods.delete_all_Drafts(webDriver);

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
                commonApplicationMethods.delete_all_Drafts(webDriver);
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


        }
        catch (Exception e) { logger_37OpsSpStfCh.info(e.toString()); throw e; }
    }

    @After
    public void tearDown() throws Exception {
//        webDriver.quit();
    }
}
