package gov.sba.utils.integration;


import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
@Category({gov.sba.utils.integration.StableTests.class})
public class TestApp303and304Mpp extends TestCase {
    // Set The variables/Define
    private static WebDriver webDriver;
    String duns_Number, email, password;
    private static final Logger logger_303 = LogManager.getLogger(VerifyWosbFlow.class.getName());

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
        //Before testing - verify the  prepopulate flag - false  -Should not prepoluate the answers
        String sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8a_certified')";
        DatabaseQuery dbcall = new DatabaseQuery();
        dbcall.executeSQLScript(sql_Q_01);

        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();
        Thread.sleep(3000);

        //Create  application Mpp/Edwosb/Wosb/8a
        CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        CommonApplicationMethods.createApplication(webDriver, "Mpp");
        String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
        logger_303.info(file_path_abs);
        fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, duns_Number);
        fillApplCreatePages.finalSignatureSubmit(webDriver);

        //Verify the Answers are not prefilling from the previous answers when the prepulate falg = 'false';
        CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        CommonApplicationMethods.createApplication(webDriver, "Mpp");
        String  checkBoxElement = webDriver.findElement(By.id("answers_117_value_yes")).getAttribute("outerHTML");
        assertFalse(checkBoxElement.toLowerCase().contains("checked"));

        //Update the - Prepopulate flag- True ---should Prepopluate the answers
        sql_Q_01 = "update sbaone.questions set  prepopulate = true where name in ('8a_certified')";
        dbcall.executeSQLScript(sql_Q_01);

        webDriver.navigate().refresh();
        webDriver.navigate().refresh();
        webDriver.navigate().refresh();
        Thread.sleep(1000);
        checkBoxElement = webDriver.findElement(By.id("answers_117_value_yes")).getAttribute("outerHTML");
        assertTrue(checkBoxElement.toLowerCase().contains("checked"));
        // Reset to Default
        sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8a_certified')";
        dbcall = new DatabaseQuery();
        dbcall.executeSQLScript(sql_Q_01);



        }
    @After
    public void tearDown () throws Exception {
        webDriver.quit();
        String sql_Q_01 = "update sbaone.questions set  prepopulate = false where name in ('8a_certified')";
        DatabaseQuery dbcall = new DatabaseQuery();
        dbcall.executeSQLScript(sql_Q_01);
    }
}