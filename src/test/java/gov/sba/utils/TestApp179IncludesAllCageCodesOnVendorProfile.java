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

public class TestApp179IncludesAllCageCodesOnVendorProfile extends TestCase {
    private static final Logger logger = LogManager
            .getLogger(TestApp179IncludesAllCageCodesOnVendorProfile.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 37;

    }

    @Test
    public void testMainTest() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        logger.info("Vendor Profile info includes all CAGE codes");
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // Verify if user has claim a business if not claim one.
        if (webDriver.getPageSource().contains("Request access")) {
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Request access";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.id("request_new_application")).click();
            webDriver.findElement(By.xpath("//input[@name='commit']")).click();
            webDriver.findElement(By.id("search_duns_number")).sendKeys("791985859");
            webDriver.findElement(By.id("search_ssn_ein")).sendKeys("123456789");
            webDriver.findElement(By.id("search_mpin")).sendKeys("A12345678");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.id("business_type")).click();
            webDriver.findElement(By.xpath("//option[4]")).click();
            webDriver.findElement(By.id("connect")).click();
            Actual_Text = webDriver.findElement(By.cssSelector("h2 > a")).getText();
            Expected_Text = "My business";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//p[3]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//p[3]/span")).getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodes = webDriver.findElement(By.xpath("//p[3]/span"));
            HighLight.highLightElement(webDriver, CAGECodes);
            // Logout and login as an analyst.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 0;
            LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data1.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodesAP = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodesAP);
            // Logout and login as an 8(a)analyst.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 34;
            LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data2.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodes8aA = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodes8aA);
            // Logout and login as an OPS support.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 28;
            LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data3.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Actual_Text, Expected_Text);
            WebElement CAGECodesOpsS = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodesOpsS);
            // Logout and login as an MPP Analyst.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 29;
            LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data4.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodesMppA = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodesMppA);
            return;

        } else {
            Actual_Text = webDriver.findElement(By.cssSelector("h2 > a")).getText();
            Expected_Text = "My business";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//p[3]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//p[3]/span")).getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodes = webDriver.findElement(By.xpath("//p[3]/span"));
            HighLight.highLightElement(webDriver, CAGECodes);
            // Logout and login as an analyst.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 0;
            LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data1.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodesAP = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodesAP);
            // Logout and login as an 8(a)analyst.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 34;
            LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data2.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodes8aA = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodes8aA);
            // Logout and login as an OPS support.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 28;
            LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data3.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Actual_Text, Expected_Text);
            WebElement CAGECodesOpsS = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodesOpsS);
            // Logout and login as an MPP Analyst.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 29;
            LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data4.Login_With_Reference();
            // Search the business and verify the CAGE Codes.
            webDriver.findElement(By.id("query")).sendKeys("791985859");
            webDriver.findElement(By.xpath("//button")).click();
            webDriver.findElement(By.linkText("Entity 90 Legal Business Name")).click();
            Actual_Text = webDriver.findElement(By.xpath("//p[4]/b")).getText();
            Expected_Text = "CAGE:";
            assertEquals(Expected_Text, Actual_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"))
                    .getText();
            Expected_Text = "Z495F, K70WB";
            assertEquals(Expected_Text, Actual_Text);
            WebElement CAGECodesMppA = webDriver
                    .findElement(By.xpath("//div[@id='business_search']/div/div/div/div/p[4]/span"));
            HighLight.highLightElement(webDriver, CAGECodesMppA);
            logger.info("Success");
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
