package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

public class TestUs1780SupervisorsShouldSeeRolesReguested extends TestCase {
    private static final Logger logger = LogManager
            .getLogger(TestUs1780SupervisorsShouldSeeRolesReguested.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 33;

    }

    @Test
    public void testMainTest() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        logger.info("Adding the Role Requested Supervisors management table");
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
            logger.info("The CO Account has no role, new CO role is requested.");
            Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
            Expected_Text = "If you are a federal contracting officer or contracting specialist please request access to the system by selecting the role below.";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.id("role_name_sba_supervisor")).click();
            webDriver.findElement(By.className("usa-button")).click();
            // Agree to the requesting access terms.
            Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
            Expected_Text = "Request access";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.id("request")).click();
            webDriver.findElement(By.className("usa-button")).click();
            // Verify that request was successful.
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
            Expected_Text = "Access Request was successfully created.";
            assertEquals(Actual_Text, Expected_Text);
            // Login as a supervisor and verify request table with requested
            // role on it.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 31;
            LoginPageWithReference login_Data0 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data0.Login_With_Reference();
            // Click on the Management link.
            webDriver.findElement(By.linkText("Management")).click();
            Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
            Expected_Text = "Manage analyst access";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.linkText("CO3 X")).getText();
            Expected_Text = "CO3 X";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/thead/tr/th[3]")).getText();
            Expected_Text = "Role Requested";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
            Expected_Text = "WOSB Supervisor";
            assertEquals(Actual_Text, Expected_Text);
            WebElement RoleRequestedColum = webDriver
                    .findElement(By.xpath("//div[@id='table-search']/table/thead/tr/th[3]"));
            HighLight.highLightElement(webDriver, RoleRequestedColum);
            WebElement RoleRequested = webDriver
                    .findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]"));
            HighLight.highLightElement(webDriver, RoleRequested);
            webDriver.findElement(By.linkText("Deny")).click();
            webDriver.switchTo().alert().accept();
            webDriver.findElement(By.linkText("Logout")).click();
        } else {
            // Click on the Management link.
            webDriver.findElement(By.linkText("Management")).click();
            Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
            Expected_Text = "Manage analyst access";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.linkText("CO3 X")).getText();
            Expected_Text = "CO3 X";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/thead/tr/th[3]")).getText();
            Expected_Text = "Role Requested";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
            Expected_Text = "WOSB Supervisor";
            assertEquals(Actual_Text, Expected_Text);
            WebElement RoleRequestedColum = webDriver
                    .findElement(By.xpath("//div[@id='table-search']/table/thead/tr/th[3]"));
            HighLight.highLightElement(webDriver, RoleRequestedColum);
            WebElement RoleRequested = webDriver
                    .findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]"));
            HighLight.highLightElement(webDriver, RoleRequested);
            webDriver.findElement(By.linkText("Logout")).click();

        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
