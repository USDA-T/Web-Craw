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

public class TestUs1531RestrictCoAccessToActiveOrPendingMppRecords extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger = LogManager
            .getLogger(TestUs1531RestrictCoAccessToActiveOrPendingMppRecords.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {

        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 3;

    }

    @Test
    public void testMainTest() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        logger.info("US1531Restrict search results on Request Access to View Records page for CO role");
        // Verify if there is an existing certification on the dashboard and
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
        deleteDraftCert.DeleteDraftCert();
        // Verify for active and Draft certification on the dashboard, if draft
        // delete and start a new EDWOSB certification.
        Thread.sleep(2000);
        AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
        addOrStartCertification.AddOrStartCertification();
        // Start new 8(a) application.
        EDWOSBEightATestPage eDWOSBEightATest = new EDWOSBEightATestPage(webDriver);
        eDWOSBEightATest.EDWOSBEightATest();
        // Verify for active and Draft program on the dashboard, if draft
        // delete and start a new one.
        webDriver.findElement(By.linkText("Dashboard")).click();
        // Verify if there is an existing certification on the dashboard and
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
        deleteDraftCert1.DeleteDraftCert();
        Thread.sleep(2000);
        AddOrStartNewMppProgramPage1 addOrStartNewMppProgram = new AddOrStartNewMppProgramPage1(webDriver);
        addOrStartNewMppProgram.AddOrStartNewMppProgram();
        // Start new 8(a) application.
        EdwobEightAMppTestPage edwobEightAMppTest = new EdwobEightAMppTestPage(webDriver);
        edwobEightAMppTest.EdwobEightAMppTest();
        // Logout and login as a CO.
        webDriver.findElement(By.linkText("Logout")).click();
        get_The_Row_From_Login_Data = 16;
        LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data1.Login_With_Reference();
        webDriver.manage().window().maximize();
        // Verify if the CO account has a role, if not request a co role for the
        // account.
        if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
            logger.info("The CO Account has no role, new CO role is requested.");
            Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
            Expected_Text = "If you are a federal contracting officer, contracting specialist, or authorized SBA personnel, please request access to the system by selecting the role below.";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.id("role_name_contracting_officer")).click();
            webDriver.findElement(By.className("usa-button")).click();
            // Search valid DUNs with no Active certification and verify
            // message.
            webDriver.findElement(By.id("duns_number")).sendKeys("135453634");
            webDriver.findElement(By.id("find_business")).click();
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.xpath(".//*[@id='vendor_found_not_certified']/h3")).getText();
            Expected_Text = "The vendor has not completed self-certification in certify.SBA.gov. Please contact the vendor to direct them to complete self-certification.";
            assertEquals(Actual_Text, Expected_Text);
            WebElement NewText = webDriver.findElement(By.xpath(".//*[@id='vendor_found_not_certified']/h3"));
            HighLight.highLightElement(webDriver, NewText);
            Thread.sleep(6000);
            // Take screenshot and store as a file format
            ScreenShotPage screenShot = new ScreenShotPage(webDriver);
            screenShot.ScreenShot();
            Thread.sleep(3000);
            // Search Invalid DUNs verify message.
            webDriver.findElement(By.id("duns_number")).clear();
            webDriver.findElement(By.id("duns_number")).sendKeys("135000634");
            webDriver.findElement(By.id("find_business")).click();
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.xpath(".//*[@id='no_vendor_found']/h3")).getText();
            Expected_Text = "No vendor exists in certify.SBA.gov for the DUNS number you have entered. Please contact the vendor to direct them to self-certify at certify.SBA.gov.";
            assertEquals(Actual_Text, Expected_Text);
            WebElement NewText1 = webDriver.findElement(By.xpath(".//*[@id='no_vendor_found']/h3"));
            HighLight.highLightElement(webDriver, NewText1);
            Thread.sleep(6000);
            // Take screenshot and store as a file format
            ScreenShotPage screenShot1 = new ScreenShotPage(webDriver);
            screenShot1.ScreenShot();
            Thread.sleep(3000);
            // Search valid DUNs With active MPP and WOSB verify..
            webDriver.findElement(By.id("duns_number")).clear();
            webDriver.findElement(By.id("duns_number")).sendKeys("172115728");
            webDriver.findElement(By.id("find_business")).click();
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.id("vendor_name")).getText();
            Expected_Text = "Entity 81 Legal Business Name";
            assertEquals(Actual_Text, Expected_Text);
            // Enter a Solicitation and NAICS number.
            webDriver.findElement(By.id("access_request_solicitation_number")).sendKeys("1721157");
            webDriver.findElement(By.id("access_request_solicitation_naics")).sendKeys("656782");
            // On type of procurement, select WOSB set-aside.
            webDriver.findElement(By.id("access_request_procurement_type_wosb_set_aside")).click();
            // Click on i accept.
            webDriver.findElement(By.id("contracting_officer_certification")).click();
            Thread.sleep(3000);
            webDriver.findElement(By.id("submit_request_access")).click();
            Thread.sleep(2000);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Review vendor certifications";
            assertEquals(Actual_Text, Expected_Text);
            // Logout and login to the requested vendors profile and accept the
            // request.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 3;
            LoginPageWithReference login_Data11 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data11.Login_With_Reference();
            // click on the Business link.
            webDriver.findElement(By.linkText("Business")).click();
            Thread.sleep(3000);
            // Click on Manage CO access.
            webDriver.findElement(By.linkText("Manage CO access")).click();
            Thread.sleep(3000);
            // Click on the accept button.
            webDriver.findElement(By.xpath("//input[@value='Accept']")).click();
            webDriver.switchTo().alert().accept();
            Thread.sleep(3000);
            // Logout and login to the requester, CO profile and verify that CO
            // sees only the WOSB and not the MPP certification.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 16;
            LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data2.Login_With_Reference();
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[7]")).getText();
            Expected_Text = "EDWOSB";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.linkText("Logout")).click();
        } else {
            // Click on the My Request Link.
            webDriver.findElement(By.linkText("Request access")).click();
            Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
            Expected_Text = "Request access to view records";
            assertEquals(Actual_Text, Expected_Text);
            // Search valid DUNs with no Active certification and verify
            // message.
            webDriver.findElement(By.id("duns_number")).sendKeys("135453634");
            webDriver.findElement(By.id("find_business")).click();
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.xpath(".//*[@id='vendor_found_not_certified']/h3")).getText();
            Expected_Text = "The vendor has not completed self-certification in certify.SBA.gov. Please contact the vendor to direct them to complete self-certification.";
            assertEquals(Actual_Text, Expected_Text);
            WebElement NewText = webDriver.findElement(By.xpath(".//*[@id='vendor_found_not_certified']/h3"));
            HighLight.highLightElement(webDriver, NewText);
            Thread.sleep(6000);
            // Take screenshot and store as a file format
            ScreenShotPage screenShot2 = new ScreenShotPage(webDriver);
            screenShot2.ScreenShot();
            Thread.sleep(3000);
            // Search Invalid DUNs verify message.
            webDriver.findElement(By.id("duns_number")).clear();
            webDriver.findElement(By.id("duns_number")).sendKeys("1721157");
            webDriver.findElement(By.id("find_business")).click();
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='no_vendor_found']/h3")).getText();
            Expected_Text = "No vendor exists in certify.SBA.gov for the DUNS number you have entered. Please contact the vendor to direct them to self-certify at certify.SBA.gov.";
            assertEquals(Actual_Text, Expected_Text);
            WebElement NewText1 = webDriver.findElement(By.xpath("//div[@id='no_vendor_found']/h3"));
            HighLight.highLightElement(webDriver, NewText1);
            Thread.sleep(6000);
            // Take screenshot and store as a file format
            ScreenShotPage screenShot3 = new ScreenShotPage(webDriver);
            screenShot3.ScreenShot();
            Thread.sleep(3000);
            // Search valid DUNs With active MPP and WOSB verify..
            webDriver.findElement(By.id("duns_number")).clear();
            webDriver.findElement(By.id("duns_number")).sendKeys("172115728");
            webDriver.findElement(By.id("find_business")).click();
            Thread.sleep(3000);
            Actual_Text = webDriver.findElement(By.id("vendor_name")).getText();
            Expected_Text = "Entity 81 Legal Business Name";
            assertEquals(Actual_Text, Expected_Text);
            // Enter a Solicitation and NAICS number.
            webDriver.findElement(By.id("access_request_solicitation_number")).sendKeys("1721157");
            webDriver.findElement(By.id("access_request_solicitation_naics")).sendKeys("656782");
            // On type of procurement, select WOSB set-aside.
            Thread.sleep(3000);
            webDriver.findElement(By.id("access_request_procurement_type_wosb_set_aside")).click();
            // Click on i accept.
            webDriver.findElement(By.id("contracting_officer_certification")).click();
            Thread.sleep(3000);
            webDriver.findElement(By.id("submit_request_access")).click();
            Thread.sleep(2000);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Review vendor certifications";
            assertEquals(Actual_Text, Expected_Text);
            // Logout and login to the requested vendors profile and accept the
            // request.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 3;
            LoginPageWithReference login_Data11 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data11.Login_With_Reference();
            // click on the Business link.
            webDriver.findElement(By.linkText("Business")).click();
            // Click on Manage CO access.
            Thread.sleep(3000);
            webDriver.findElement(By.linkText("Manage CO access")).click();
            // Click on the accept button.
            Thread.sleep(3000);
            webDriver.findElement(By.xpath("//input[@value='Accept']")).click();
            webDriver.switchTo().alert().accept();
            Thread.sleep(3000);
            // Logout and login to the requester, CO profile and verify that CO
            // sees only the WOSB and not the MPP certification.
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 16;
            LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data2.Login_With_Reference();
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[7]")).getText();
            Expected_Text = "EDWOSB";
            assertEquals(Actual_Text, Expected_Text);
            // Take screenshot and store as a file format
            ScreenShotPage screenShot11 = new ScreenShotPage(webDriver);
            screenShot11.ScreenShot();
            Thread.sleep(3000);
            webDriver.findElement(By.linkText("Logout")).click();
            //Login as MPP-analyst and return MPP back to vendor.
            get_The_Row_From_Login_Data = 29;
            LoginPageWithReference login_Data6 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data6.Login_With_Reference();
            webDriver.findElement(By.id("query")).sendKeys("172115728");
            webDriver.findElement(By.xpath("//button")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.linkText("Entity 81 Legal Business Name")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.linkText("Return to Vendor")).click();
            webDriver.switchTo().alert().accept();
            webDriver.findElement(By.linkText("Logout")).click();
           //Login as WOSB-analyst and return WOSB program back to vendor.
            get_The_Row_From_Login_Data = 0;
            LoginPageWithReference login_Data7 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data7.Login_With_Reference();
            webDriver.findElement(By.id("query")).sendKeys("172115728");
            webDriver.findElement(By.xpath("//button")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.linkText("Entity 81 Legal Business Name")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.linkText("Return to Vendor")).click();
            webDriver.switchTo().alert().accept();
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 3;
            LoginPageWithReference login_Data8 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data8.Login_With_Reference();
            //delete one start application.
            DeleteDraftCertPage deleteDraftCert3 = new DeleteDraftCertPage(webDriver);
            deleteDraftCert3.DeleteDraftCert();
            webDriver.findElement(By.linkText("Logout")).click();

        }

    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
