package gov.sba.utils;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

public class TestUs1048UpdateTextInCertificationLetter extends TestCase {
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
        get_The_Row_From_Login_Data = 4;

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
        EDWOSBEightATest1Page eDWOSBEightATest1 = new EDWOSBEightATest1Page(webDriver);
        eDWOSBEightATest1.EDWOSBEightATest1();
        // Verify active certification and click on it.
        webDriver.navigate().refresh();
        Thread.sleep(2000);
        Actual_Text = webDriver.findElement(By.linkText("EDWOSB Self-Certification")).getText();
        Expected_Text = "EDWOSB Self-Certification";
        assertEquals(Actual_Text, Expected_Text);
        // click on the certification link.
        webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
        Actual_Text = webDriver.findElement(By.linkText("Certificate Letter")).getText();
        Expected_Text = "Certificate Letter";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.linkText("Certificate Letter")).click();
        Thread.sleep(3000);
        Actual_Text = webDriver.findElement(By.linkText("Click to view and print certificate letter")).getText();
        Expected_Text = "Click to view and print certificate letter";
        assertEquals(Actual_Text, Expected_Text);
        Thread.sleep(2000);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        String main_window = webDriver.getWindowHandle();
        logger.info("Before switching, title is = certify.sba.gov");
        webDriver.findElement(By.linkText("Click to view and print certificate letter")).click();
        Thread.sleep(5000);
        assertEquals(Actual_Text, Expected_Text);
        java.util.Set<String> S1 = webDriver.getWindowHandles();
        Iterator<String> i1 = S1.iterator();
        while (i1.hasNext()) {
            String Second_window = i1.next();
            if (!main_window.equalsIgnoreCase(Second_window)) {
                webDriver.switchTo().window(Second_window);
                webDriver.manage().window().maximize();
                Actual_Text = webDriver.findElement(By.xpath("//div[@id='pageContainer1']/div[2]/div[12]")).getText();
                Expected_Text = "It is your responsibility to ensure you have uploaded all of the documents required by 13 C.F.R.";
                assertEquals(Actual_Text, Expected_Text);
                Thread.sleep(5000);
                WebElement NewText = webDriver.findElement(By.xpath("//div[@id='pageContainer1']/div[2]/div[12]"));
                HighLight.highLightElement(webDriver, NewText);
                // Take screenshot and store as a file format
                ScreenShotPage screenShot = new ScreenShotPage(webDriver);
                screenShot.ScreenShot();
                Thread.sleep(3000);
                webDriver.close();
                webDriver.switchTo().window(main_window);
                logger.info("Back to manin_window = certify.sba.gov");
            } else {
                logger.info("Second Window is thesame as first window");

            }
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
