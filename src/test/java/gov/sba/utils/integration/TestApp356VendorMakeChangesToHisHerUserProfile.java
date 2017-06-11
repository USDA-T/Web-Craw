package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class TestApp356VendorMakeChangesToHisHerUserProfile extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestApp356VendorMakeChangesToHisHerUserProfile.class.getName());
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();
        CommonApplicationMethods.get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
    webDriver.manage().window().maximize();
    get_The_Row_From_Login_Data = 20;

  }

  @Test
  public void testMainTest() throws Exception {
    try{
    String Actual_Text;
    String Expected_Text;
    logger.info("Upding vendor's user profile.");
    // Explicit wait.
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    // Login to Dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    // locate and click on the Profile link.
    webDriver.findElement(By.xpath("//li[5]/a/span")).click();
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "My profile";
    assertEquals(Actual_Text, Expected_Text);
    // Click on the Edit profile button.
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article/a")));
    webDriver.findElement(By.xpath("//article/a")).click();
    // Verify page.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "Edit profile";
    assertEquals(Actual_Text, Expected_Text);
    // Verify to make sure user cannot continue profile update with no data.
    webDriver.findElement(By.id("user_first_name")).clear();
    webDriver.findElement(By.id("user_last_name")).clear();
    webDriver.findElement(By.id("user_phone_number")).clear();
    webDriver.findElement(By.xpath("//div[4]/div/button")).click();
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("//article[@id='main-content']/div/div/div/div/h5")));
    Actual_Text = webDriver
        .findElement(By.xpath("//article[@id='main-content']/div/div/div/div/h5")).getText();
    Expected_Text = "2 errors prohibited this user from being saved:";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver.findElement(By.xpath("//div/div/ul/li")).getText();
    Expected_Text = "First name can't be blank";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver.findElement(By.xpath("//div/div/ul/li[2]")).getText();
    Expected_Text = "Last name can't be blank";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.xpath("//li[5]/a/span")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//article/a")));
    webDriver.findElement(By.xpath("//article/a")).click();
    // Verify and update first and last name.
    if (webDriver.getPageSource().contains("User")) {
      // if first and last name = QA User, then change it.
      assertEquals(Actual_Text, Expected_Text);
      webDriver.findElement(By.id("user_first_name")).clear();
      webDriver.findElement(By.id("user_last_name")).clear();
      webDriver.findElement(By.id("user_phone_number")).clear();
      webDriver.findElement(By.id("user_first_name")).sendKeys("John");
      webDriver.findElement(By.id("user_last_name")).sendKeys("DrinkWater");
      webDriver.findElement(By.id("user_phone_number")).sendKeys("708-456-9087");
      // click on the update button.
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]/div/button")));
      webDriver.findElement(By.xpath("//div[4]/div/button")).click();
      Actual_Text = webDriver.findElement(By.xpath("//article/div/div/div/div/p")).getText();
      Expected_Text = "Your account has been updated successfully.";
      assertEquals(Actual_Text, Expected_Text);
      // Verify updates to user profile.
      Actual_Text = webDriver.findElement(By.xpath("//p/span")).getText();
      Expected_Text = "John";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("//p[2]/span")).getText();
      Expected_Text = "DrinkWater";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("//p[4]/span")).getText();
      Expected_Text = "708-456-9087";
      assertEquals(Actual_Text, Expected_Text);
      // click on dashboard and verify changes.
      webDriver.findElement(By.xpath("//a/span")).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[5]/a/span")));
      webDriver.findElement(By.xpath("//li[5]/a/span")).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[4]/span")));
      Actual_Text = webDriver.findElement(By.xpath("//p[4]/span")).getText();
      Expected_Text = "708-456-9087";
      assertEquals(Actual_Text, Expected_Text);
      WebElement NewFN = webDriver.findElement(By.xpath("//p/span"));
      HighLight.highLightElement(webDriver, NewFN);
      WebElement NewLN = webDriver.findElement(By.xpath("//p[2]/span"));
      HighLight.highLightElement(webDriver, NewLN);
      WebElement NewPN = webDriver.findElement(By.xpath("//p[4]/span"));
      HighLight.highLightElement(webDriver, NewPN);
    } else {
      webDriver.findElement(By.id("user_first_name")).clear();
      webDriver.findElement(By.id("user_last_name")).clear();
      webDriver.findElement(By.id("user_phone_number")).clear();
      webDriver.findElement(By.id("user_first_name")).sendKeys("QA");
      webDriver.findElement(By.id("user_last_name")).sendKeys("User");
      webDriver.findElement(By.id("user_phone_number")).sendKeys("708-456-8888");
      // click on the update button.
      webDriver.findElement(By.xpath("//div[4]/div/button")).click();
      Actual_Text = webDriver.findElement(By.xpath("//article/div/div/div/div/p")).getText();
      Expected_Text = "Your account has been updated successfully.";
      assertEquals(Actual_Text, Expected_Text);
      // Verify updates to user profile.
      Actual_Text = webDriver.findElement(By.xpath("//p/span")).getText();
      Expected_Text = "QA";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("//p[2]/span")).getText();
      Expected_Text = "User";
      assertEquals(Actual_Text, Expected_Text);
      Actual_Text = webDriver.findElement(By.xpath("//p[4]/span")).getText();
      Expected_Text = "708-456-8888";
      assertEquals(Actual_Text, Expected_Text);
      // click on dashboard and verify changes.
      webDriver.findElement(By.xpath("//a/span")).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[5]/a/span")));
      webDriver.findElement(By.xpath("//li[5]/a/span")).click();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[4]/span")));
      Expected_Text = "708-456-8888";
      assertEquals(Actual_Text, Expected_Text);
      WebElement NewFN = webDriver.findElement(By.xpath("//p/span"));
      HighLight.highLightElement(webDriver, NewFN);
      WebElement NewLN = webDriver.findElement(By.xpath("//p[2]/span"));
      HighLight.highLightElement(webDriver, NewLN);
      WebElement NewPN = webDriver.findElement(By.xpath("//p[4]/span"));
      HighLight.highLightElement(webDriver, NewPN);
    }
    // Logout of the system.
    webDriver.findElement(By.linkText("Logout")).click();
    logger.info("Success");
    }
    catch (Exception e) {
    ScreenShotPage screenShot = new ScreenShotPage(webDriver);
    screenShot.ScreenShot();
    logger.info(e.getMessage());    }
  
    logger.info("Success");
    }
@After
public void tearDown() throws Exception {
  webDriver.close();
}
}

