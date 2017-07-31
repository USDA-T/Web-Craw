package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class TestApp466RenameSbaOwnerToSbaAnalyst extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestApp466RenameSbaOwnerToSbaAnalyst.class.getName());
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
  public void testApp466RenameSbaOwnerToSbaAnalyst() throws Exception {
    String Actual_Text;
    String Expected_Text;
    logger.info("Test, changing role name sba_owner to SBA_Analyst");
    // Login to Request role page.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
      logger.info("No role has been claim yet for this CO, test continue.");
      // Validate WOSB_Analyst and Supervisor.
      wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//li[2]/label"), false));
      Actual_Text = webDriver.findElement(By.xpath("//li[2]/label")).getText();
      Expected_Text = "I am an SBA Analyst";
      assertEquals(Actual_Text, Expected_Text);
      // Validate WOSB_Supervisor.
      Actual_Text = webDriver.findElement(By.xpath("//label")).getText();
      Expected_Text = "I am an SBA Supervisor";
      assertEquals(Actual_Text, Expected_Text);
      logger.info("WOSB Analyst and Supervisor role successfully changed");
      webDriver.findElement(By.linkText("Logout")).click();
    } else {
      logger.info("CO10 has been assign a role already trying CO9");
      webDriver.findElement(By.linkText("Logout")).click();
      get_The_Row_From_Login_Data = 53;
      // Login to Request role page.
      LoginPageWithReference login_Data1 =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data1.Login_With_Reference();
      Thread.sleep(3000);
      if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
        // Validate WOSB_Analyst and Supervisor.
        wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//li[2]/label"), false));
        Actual_Text = webDriver.findElement(By.xpath("//li[2]/label")).getText();
        Expected_Text = "I am an SBA Analyst";
        assertEquals(Actual_Text, Expected_Text);
        // Validate WOSB_Supervisor.
        Actual_Text = webDriver.findElement(By.xpath("//label")).getText();
        Expected_Text = "I am an SBA Supervisor";
        assertEquals(Actual_Text, Expected_Text);
        logger.info("WOSB Analyst and Supervisor role successfully changed");
        webDriver.findElement(By.linkText("Logout")).click();
      } else {
        logger.info("CO9 has been assign a role already trying CO8");
        webDriver.findElement(By.linkText("Logout")).click();
        get_The_Row_From_Login_Data = 54;
        // Login to Request role page.
        LoginPageWithReference login_Data2 =
            new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data2.Login_With_Reference();
        Thread.sleep(3000);
        // Validate WOSB_Analyst and Supervisor.
        wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//li[2]/label"), false));
        Actual_Text = webDriver.findElement(By.xpath("//li[2]/label")).getText();
        Expected_Text = "I am an SBA Analyst";
        assertEquals(Actual_Text, Expected_Text);
        // Validate WOSB_Supervisor.
        Actual_Text = webDriver.findElement(By.xpath("//label")).getText();
        Expected_Text = "I am an SBA Supervisor";
        assertEquals(Actual_Text, Expected_Text);
        logger.info("WOSB Analyst and Supervisor role successfully changed");
        webDriver.findElement(By.linkText("Logout")).click();

      }
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
