package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

public class TestDocumentUploadAndAttachedPdf extends TestCase {

    private static final Logger logger = LogManager.getLogger(TestUswdsEdwosbPartnership.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 26;
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        String Actual_Text;
        String Expected_Text;
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        logger.info("Test for pdf upload and attachments");
        // Verify if there is an existing certification on the dashboard and
        // delete to start a new certification.
        try {
            assertTrue(webDriver.getPageSource().contains("Delete"));
            webDriver.findElement(By.linkText("Delete")).click();
            // webDriver.switchTo().alert().accept();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webDriver.navigate().refresh();
        } catch (Error e) {
            logger.info("There are(is) no program in-progress on the dashboard, a new program is beinng created");
        }
        // Verify for active and Draft certification on the dashboard, if draft
        // delete and start a new one.
        AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
        addOrStartCertification.AddOrStartCertification();
        // Locate and accept the terms for certification.
        webDriver.findElement(By.name("commit")).click();
        webDriver.findElement(By.id("answers_65_value_no")).click();
        webDriver.findElement(By.name("commit")).click();
        webDriver.findElement(By.id("answers_66_value_yes")).click();
        // Third Party Upload.
        webDriver.findElement(By.cssSelector("label.yes")).click();
        // Upload a pdf.
        MontanaUploadDocumentPage montanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
        montanaUploadDocument.MontanaUploadDocument();
        Thread.sleep(4000);
        // webDriver.findElement(By.cssSelector("#attach")).click();
        Thread.sleep(6000);
        webDriver.findElement(By.name("commit")).click();
        // Changes in Eligibility
        webDriver.findElement(By.id("answers_67_value_no")).click();
        webDriver.findElement(By.name("commit")).click();
        webDriver.findElement(By.id("third_party_cert_part_1")).click();
        // String Doc Name;
        Actual_Text = webDriver.findElement(By.cssSelector("#currently_attached > h4")).getText();
        Expected_Text = "Documents previously added";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.name("commit")).click();
        webDriver.findElement(By.id("answers_67_value_yes")).click();
        webDriver.findElement(By.name("commit")).click();
        // Stocks
        webDriver.findElement(By.id("answers_69_value_yes")).click();
        webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector("#doc-lib-button")).click();
        webDriver.findElement(By.name("selector")).click();
        webDriver.findElement(By.cssSelector("#document_library_associate")).click();
        webDriver.findElement(By.id("answers_70_value_yes")).click();
        webDriver.findElement(By.id("answers_71_value_yes")).click();
        webDriver.findElement(By.name("commit")).click();
        // ERROR: Caught exception [ERROR: Unsupported command [getTable |
        // css=table.display-table.1.0 | ]]
        webDriver.findElement(By.id("answers_72_value_yes")).click();
        webDriver.findElement(By.name("commit")).click();
        webDriver.findElement(By.id("third_party_cert_part_2")).click();
        webDriver.findElement(By.name("commit")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("#currently_attached > h4")).getText();
        Expected_Text = "Documents previously added";
        logger.info("Success");
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
