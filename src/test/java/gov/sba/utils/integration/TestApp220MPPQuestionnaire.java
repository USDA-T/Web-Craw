package gov.sba.utils.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestApp220MPPQuestionnaire extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp220MPPQuestionnaire = LogManager
            .getLogger(TestApp220MPPQuestionnaire.class.getName());
    int get_The_Row_From_Login_Data;
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
        try {

            LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();

            CommonApplicationMethods.createApplication(webDriver, "MPP");
            webDriver
                    .findElement(By
                            .xpath("//input[@type='radio' and contains(@id,'answers_') and contains(@id,'_value_yes') ]"))
                    .click();

            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

            TestApp220MPPQuestionnaire.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);

            WebElement Business_text = webDriver
                    .findElement(By.xpath("//article/h2[contains(text(),'Business Info')]"));

            List<WebElement> duns_No = webDriver
                    .findElements(By.xpath("//input[@type='number' and contains(@id,'duns-value')]"));
            if (duns_No.size() > 0) {
                duns_No.get(0).sendKeys(duns_Number);
                webDriver.findElement(By.xpath("//a[contains(@id,'search-duns')]")).click();
                Thread.sleep(1200);
                webDriver.switchTo().alert().accept();
            }

            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            webDriver.switchTo().alert().accept();

            fillApplCreatePages.finalSignatureSubmit(webDriver);
            TestApp220MPPQuestionnaire.info("Application has been submitted sucessfully.");

        } catch (Exception e) {
            TestApp220MPPQuestionnaire.info(e.toString());
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
         webDriver.quit();
    }
}
