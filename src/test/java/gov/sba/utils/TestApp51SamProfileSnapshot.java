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

public class TestApp51SamProfileSnapshot extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestApp51SamProfileSnapshot.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;
    String BaseUrl;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 23;

    }

    @Test
    public void testMainTest() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        logger.info("Implement 8a page permissions - Copy MPP analyst permissions");
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // Verify if there is an existing Draft certification on the dashboard
        // and delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
        deleteDraftCert.DeleteDraftCert();
        // Start an 8(a) Program.
        AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
        addOrStartCertification.AddOrStartCertification();
        // 8(a) Interim questionnaire.
        SimpleEDWOSBEightATestPage simpleEDWOSBEightATest = new SimpleEDWOSBEightATestPage(webDriver);
        simpleEDWOSBEightATest.SimpleEDWOSBEightATest();
        // Login as a WOSB analyst and verify responsibilities.
        webDriver.findElement(By.linkText("Logout")).click();
        get_The_Row_From_Login_Data = 0;
        LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data1.Login_With_Reference();
        webDriver.findElement(By.id("query")).sendKeys("111419538");
        webDriver.findElement(By.xpath("//button")).click();
        Actual_Text = webDriver.findElement(By.linkText("Entity 10 Legal Business Name")).getText();
        Expected_Text = "Entity 10 Legal Business Name";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.linkText("Entity 10 Legal Business Name")).click();
        Thread.sleep(2000);
        // Verify that analyst is navigated to the vendor overview page.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Entity 10 Legal Business Name";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("td")).getText();
        Expected_Text = "EDWOSB Self-Certification";
        assertEquals(Actual_Text, Expected_Text);
        // verify Verify and get the creation date.
        WebElement CreationDate = webDriver.findElement(By.xpath("//td[2]"));
        HighLight.highLightElement(webDriver, CreationDate);
        String Actual_Text1;
        Actual_Text1 = webDriver.findElement(By.xpath("//td[2]")).getText();
        webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
        String Expected_Text1;
        Expected_Text1 = webDriver.findElement(By.xpath("//div[2]/div[3]/p")).getText();
        String[] Actual_Text2 = Expected_Text1.split(" ");
        String part2 = Actual_Text2[5]; // Submitted Date
        assertEquals(part2, Actual_Text1);
        logger.info(part2);
        // Verify the SAM snapshot.
        WebElement SAMDate = webDriver.findElement(By.xpath("//div[2]/div[3]/p"));
        HighLight.highLightElement(webDriver, SAMDate);
        // Click on start a review and return the certification back to vendor.
        webDriver.findElement(By.id("submit_button")).click();
        webDriver.findElement(By.linkText("Determination")).click();
        webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("div.usa-alert-warning.usa-alert")).getText();
        Expected_Text = "You are in view-only mode (Version #1)You can view the vendor's record but can not make edits";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.linkText("Vendor Overview")).click();
        // Logout.
        webDriver.findElement(By.linkText("Logout")).click();
        logger.info("Success");

    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
