package gov.sba.utils.part_01_Deepa;

import gov.sba.utils.CommonFunctions.LoginPageWithDetails;
import gov.sba.utils.CommonFunctions.LoginPageWithReference;
import gov.sba.utils.CommonFunctions.ScorpQuestionsPage;
import gov.sba.utils.CommonFunctions.TestHelpers;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
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

        LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver,  27);
        login_Data_01.Login_With_Reference();

        try{

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

                LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
                login_Data.Login_With_Details();
                Thread.sleep(1500);

                //Verify the Business type is changes to Scorp[from LLC]
                webDriver.findElement(By.xpath("//button[@aria-controls='more-details']")).click();
                String business_Type= webDriver.findElement(By.id("more-details")).getText();

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
        webDriver.quit();
    }
}
