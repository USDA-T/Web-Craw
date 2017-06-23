package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class TestApp466RenameSbaOwnerToSbaAnalyst extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestApp466RenameSbaOwnerToSbaAnalyst.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();

        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 52;
    }

    @Test
    public void testMainTest() throws Exception {
        String Actual_Text;
        String Expected_Text;
        logger.info("Test, changing role name sba_owner to SBA_Analyst");
        // Login to Request role page.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
            logger.info("No role has been claim yet for this CO, test continue.");
            // Validate WOSB_Analyst and Supervisor.
            Actual_Text = webDriver.findElement(By.xpath("//li[2]/label")).getText();
            Expected_Text = "WOSB SBA Analyst";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//li[2]/p")).getText();
            Expected_Text = "An SBA Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial.";
            assertEquals(Actual_Text, Expected_Text);
            // Validate WOSB_Supervisor.
            Actual_Text = webDriver.findElement(By.xpath("//li[3]/label")).getText();
            Expected_Text = "WOSB SBA Supervisor";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//li[3]/p")).getText();
            Expected_Text = "An SBA Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations.";
            assertEquals(Actual_Text, Expected_Text);
            logger.info("WOSB Analyst and Supervisor role successfully changed");
            Thread.sleep(2000);
            // Validate SBA MPP_Analyst and Supervisor.
            Actual_Text = webDriver.findElement(By.xpath("//li[4]/label")).getText();
            Expected_Text = "SBA MPP Analyst";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//li[4]/p")).getText();
            Expected_Text = "An SBA MPP Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial for the MPP program.";
            assertEquals(Actual_Text, Expected_Text);
            // Validate SBA MPP_Supervisor.
            Actual_Text = webDriver.findElement(By.xpath("//li[5]/label")).getText();
            Expected_Text = "SBA MPP Supervisor";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//li[5]/p")).getText();
            Expected_Text = "An SBA MPP Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations for the MPP program.";
            assertEquals(Actual_Text, Expected_Text);
            logger.info("SBA MPP_Analyst and Supervisor role successfully changed");
            Thread.sleep(2000);
            // Validate SBA 8(a)_Analyst and Supervisor.
            Actual_Text = webDriver.findElement(By.xpath("//li[6]/label")).getText();
            Expected_Text = "SBA 8(a) Analyst";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//li[6]/p")).getText();
            Expected_Text = "An SBA 8(a) Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial for the 8(a) program.";
            assertEquals(Actual_Text, Expected_Text);
            // Validate SBA 8(a)_Supervisor.
            Actual_Text = webDriver.findElement(By.xpath("//li[7]/label")).getText();
            Expected_Text = "SBA 8(a) Supervisor";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//li[7]/p")).getText();
            Expected_Text = "An SBA 8(a) Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations for the 8(a) program.";
            assertEquals(Actual_Text, Expected_Text);
            logger.info("SBA 8(a)_Analyst and Supervisor role successfully changed");
            Thread.sleep(2000);
            // Validate Agreement text.
            Actual_Text = webDriver.findElement(By.cssSelector("form > p")).getText();
            Expected_Text = "You are accessing a U.S. Government information system which is provided for U.S. Government-authorized use only. By submitting a request for access, you are confirming that you are authorized to by your federal agency to access data in this system. Unauthorized or improper use of this system may result in disciplinary action, as well as civil and criminal penalties.";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.linkText("Logout")).click();
        } else {
            logger.info("CO10 has been assign a role already trying CO9");
            webDriver.findElement(By.linkText("Logout")).click();
            get_The_Row_From_Login_Data = 53;
            // Login to Request role page.
            LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data1.Login_With_Reference();
            Thread.sleep(3000);
            if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
                // Validate WOSB_Analyst and Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[2]/label")).getText();
                Expected_Text = "WOSB SBA Analyst";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[2]/p")).getText();
                Expected_Text = "An SBA Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial.";
                assertEquals(Actual_Text, Expected_Text);
                // Validate WOSB_Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[3]/label")).getText();
                Expected_Text = "WOSB SBA Supervisor";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[3]/p")).getText();
                Expected_Text = "An SBA Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations.";
                assertEquals(Actual_Text, Expected_Text);
                logger.info("WOSB Analyst and Supervisor role successfully changed");
                Thread.sleep(2000);
                // Validate SBA MPP_Analyst and Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[4]/label")).getText();
                Expected_Text = "SBA MPP Analyst";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[4]/p")).getText();
                Expected_Text = "An SBA MPP Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial for the MPP program.";
                assertEquals(Actual_Text, Expected_Text);
                // Validate SBA MPP_Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[5]/label")).getText();
                Expected_Text = "SBA MPP Supervisor";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[5]/p")).getText();
                Expected_Text = "An SBA MPP Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations for the MPP program.";
                assertEquals(Actual_Text, Expected_Text);
                logger.info("SBA MPP_Analyst and Supervisor role successfully changed");
                Thread.sleep(2000);
                // Validate SBA 8(a)_Analyst and Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[6]/label")).getText();
                Expected_Text = "SBA 8(a) Analyst";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[6]/p")).getText();
                Expected_Text = "An SBA 8(a) Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial for the 8(a) program.";
                assertEquals(Actual_Text, Expected_Text);
                // Validate SBA 8(a)_Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[7]/label")).getText();
                Expected_Text = "SBA 8(a) Supervisor";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[7]/p")).getText();
                Expected_Text = "An SBA 8(a) Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations for the 8(a) program.";
                assertEquals(Actual_Text, Expected_Text);
                logger.info("SBA 8(a)_Analyst and Supervisor role successfully changed");
                Thread.sleep(2000);
                // Validate Agreement text.
                Actual_Text = webDriver.findElement(By.cssSelector("form > p")).getText();
                Expected_Text = "You are accessing a U.S. Government information system which is provided for U.S. Government-authorized use only. By submitting a request for access, you are confirming that you are authorized to by your federal agency to access data in this system. Unauthorized or improper use of this system may result in disciplinary action, as well as civil and criminal penalties.";
                assertEquals(Actual_Text, Expected_Text);
                webDriver.findElement(By.linkText("Logout")).click();
            } else {
                logger.info("CO9 has been assign a role already trying CO8");
                webDriver.findElement(By.linkText("Logout")).click();
                get_The_Row_From_Login_Data = 54;
                // Login to Request role page.
                LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
                login_Data2.Login_With_Reference();
                Thread.sleep(3000);
                // Validate WOSB_Analyst and Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[2]/label")).getText();
                Expected_Text = "WOSB SBA Analyst";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[2]/p")).getText();
                Expected_Text = "An SBA Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial.";
                assertEquals(Actual_Text, Expected_Text);
                // Validate WOSB_Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[3]/label")).getText();
                Expected_Text = "WOSB SBA Supervisor";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[3]/p")).getText();
                Expected_Text = "An SBA Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations.";
                assertEquals(Actual_Text, Expected_Text);
                logger.info("WOSB Analyst and Supervisor role successfully changed");
                Thread.sleep(2000);
                // Validate SBA MPP_Analyst and Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[4]/label")).getText();
                Expected_Text = "SBA MPP Analyst";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[4]/p")).getText();
                Expected_Text = "An SBA MPP Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial for the MPP program.";
                assertEquals(Actual_Text, Expected_Text);
                // Validate SBA MPP_Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[5]/label")).getText();
                Expected_Text = "SBA MPP Supervisor";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[5]/p")).getText();
                Expected_Text = "An SBA MPP Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations for the MPP program.";
                assertEquals(Actual_Text, Expected_Text);
                logger.info("SBA MPP_Analyst and Supervisor role successfully changed");
                Thread.sleep(2000);
                // Validate SBA 8(a)_Analyst and Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[6]/label")).getText();
                Expected_Text = "SBA 8(a) Analyst";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[6]/p")).getText();
                Expected_Text = "An SBA 8(a) Analyst performs eligibility reviews, protests, etc., and makes recommendations for program acceptance or denial for the 8(a) program.";
                assertEquals(Actual_Text, Expected_Text);
                // Validate SBA 8(a)_Supervisor.
                Actual_Text = webDriver.findElement(By.xpath("//li[7]/label")).getText();
                Expected_Text = "SBA 8(a) Supervisor";
                assertEquals(Actual_Text, Expected_Text);
                Actual_Text = webDriver.findElement(By.xpath("//li[7]/p")).getText();
                Expected_Text = "An SBA 8(a) Supervisor can manage roles, approve or deny analyst access, perform eligibility reviews, and make final eligibility determinations for the 8(a) program.";
                assertEquals(Actual_Text, Expected_Text);
                logger.info("SBA 8(a)_Analyst and Supervisor role successfully changed");
                Thread.sleep(2000);
                // Validate Agreement text.
                Actual_Text = webDriver.findElement(By.cssSelector("form > p")).getText();
                Expected_Text = "You are accessing a U.S. Government information system which is provided for U.S. Government-authorized use only. By submitting a request for access, you are confirming that you are authorized to by your federal agency to access data in this system. Unauthorized or improper use of this system may result in disciplinary action, as well as civil and criminal penalties.";
                assertEquals(Actual_Text, Expected_Text);
                webDriver.findElement(By.linkText("Logout")).click();

            }
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
