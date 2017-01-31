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

public class TestApp49ChangeConnectToBizErrorMessages extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestApp49ChangeConnectToBizErrorMessages.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 36;

    }

    @Test
    public void testMainTest() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        logger.info("Change connect to biz error messages");
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // Validate page.
        Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-one-whole > p")).getText();
        Expected_Text = "There are two roles that are available for a firm to manage SBA's small business certifications. If this is the first time that your business is creating or updating a certification in this system, please select the Vendor Admin role below. There must be at least one Vendor Admin assigned to the account.";
        assertEquals(Actual_Text, Expected_Text);
        // Select Vendor Admin for a business that is new to certify.SBA.gov.
        webDriver.findElement(By.id("request_new_application")).click();
        webDriver.findElement(By.className("usa-button")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("div.usa-grid-full > p")).getText();
        Expected_Text = "You can connect with a business by using the following information to attach your profile to a business profile.";
        assertEquals(Actual_Text, Expected_Text);
        // Enter an invalid DUN#, TIN# and MPIN# to trigger the error message.
        webDriver.findElement(By.id("search_duns_number")).sendKeys("222222222");
        webDriver.findElement(By.id("search_ssn_ein")).sendKeys("444444444");
        webDriver.findElement(By.id("search_mpin")).sendKeys("424444444");
        // Click on the Find Business Button to validate error message.
        webDriver.findElement(By.cssSelector("div.usa-width-one-whole > #find_business")).click();
        Actual_Text = webDriver.findElement(By.xpath("//form[@id='find_business']/div[4]/p[4]")).getText();
        Expected_Text = "SBA will only accept submissions and digital signatures from qualifying business owners. If you are NOT the business owner, please do not proceed. In order to protect personal financial information entered into the system, only the qualifying business owner will have access to their business records.";
        assertEquals(Actual_Text, Expected_Text);
        WebElement ErrorMessage1 = webDriver.findElement(By.xpath("//form[@id='find_business']/div[4]"));
        HighLight.highLightElement(webDriver, ErrorMessage1);
        WebElement WarningErrorMessage = webDriver.findElement(By.xpath("//form[@id='find_business']/div[4]/p[4]"));
        HighLight.highLightElement(webDriver, WarningErrorMessage);
        Actual_Text = webDriver.findElement(By.cssSelector("h5")).getText();
        Expected_Text = "Your records in SAM.gov must be in the “Active” status to be able to access your records in certify.SBA.gov. Please check your DUNS, TIN/EIN and MPIN to make sure they match exactly as they are displayed in SAM.gov. The DUNS and EIN should be numbers only (no spaces or dashes) and your MPIN is case sensitive.";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//h5[2]")).getText();
        Expected_Text = "NOTE: Any changes to SAM.gov can take up to 72 hours to display in certify.SBA.gov.";
        assertEquals(Actual_Text, Expected_Text);
        WebElement ErrorMessage = webDriver.findElement(By.cssSelector("h5"));
        HighLight.highLightElement(webDriver, ErrorMessage);
        WebElement BizErrorMsg = webDriver.findElement(By.xpath("//h5[2]"));
        HighLight.highLightElement(webDriver, BizErrorMsg);
        webDriver.findElement(By.cssSelector("div.usa-width-one-whole > #find_business")).click();
        logger.info("Success");

    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
