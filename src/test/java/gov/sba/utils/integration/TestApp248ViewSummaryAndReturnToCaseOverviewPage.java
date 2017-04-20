package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

public class TestApp248ViewSummaryAndReturnToCaseOverviewPage extends TestCase {

//Set The variabl.es/Define
  private static WebDriver webDriver;
  private static final Logger logger = LogManager
          .getLogger(TestUs1531RestrictCoAccessToActiveOrPendingMppRecords.class.getName());
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {

      webDriver = TestHelpers.getDefaultWebDriver();
      webDriver.get(TestHelpers.getBaseUrl());
      webDriver.manage().window().maximize();
      get_The_Row_From_Login_Data = 25;

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
      // Delete second draft if any.
      DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
      deleteDraftCert1.DeleteDraftCert();
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
      DeleteDraftCertPage deleteDraftCert2 = new DeleteDraftCertPage(webDriver);
      deleteDraftCert2.DeleteDraftCert();
      Thread.sleep(2000);
      AddOrStartNewMppProgramPage1 addOrStartNewMppProgram = new AddOrStartNewMppProgramPage1(webDriver);
      addOrStartNewMppProgram.AddOrStartNewMppProgram();
      // Start new 8(a) application.
      EdwobEightAMppTestPage edwobEightAMppTest = new EdwobEightAMppTestPage(webDriver);
      edwobEightAMppTest.EdwobEightAMppTest();     
      Thread.sleep(3000);
      webDriver.findElement(By.linkText("Logout")).click();
      // Login as WOSB-analyst and return EDWOSB back to vendor.
      get_The_Row_From_Login_Data = 0;
      LoginPageWithReference login_Data61 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data61.Login_With_Reference();
      webDriver.findElement(By.xpath("//button[@id='searchtext']")).click();
      webDriver.findElement(By.id("query")).sendKeys("137151292");
      webDriver.findElement(By.xpath("//form/div/button")).click();
      Thread.sleep(2000);
      webDriver.findElement(By.linkText("Entity 399 Legal Business Name")).click();
      Thread.sleep(2000);
      //Locate the Active EDWOSB application and click on it.
      webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
      Thread.sleep(3000);
      //Verify the Open Application Summary link.
      Actual_Text = webDriver.findElement(By.cssSelector("button.usa-button-outline")).getText();
      Expected_Text = "Open application summary";
      assertEquals(Actual_Text, Expected_Text);
      //Verify the Return to Vendor link.
      Actual_Text = webDriver.findElement(By.xpath("//div[2]/a/button")).getText();
      Expected_Text = "Return to Vendor";
      assertEquals(Actual_Text, Expected_Text);
      //Click on the view summary link.      
      webDriver.findElement(By.cssSelector("button.usa-button-outline")).click();
      //Click on the back button 
      webDriver.findElement(By.linkText("Back")).click();
      Thread.sleep(3000);
      //Click on the Return to Vendor link.
      webDriver.findElement(By.xpath("//div[2]/a/button")).click();
      Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
      Expected_Text = "A new application has been reopenned for the vendor";
      assertEquals(Actual_Text, Expected_Text);      
      webDriver.findElement(By.linkText("Logout")).click();      
      // Login as MPP-analyst and return MPP program back to vendor.
      get_The_Row_From_Login_Data = 29;
      LoginPageWithReference login_Data71 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data71.Login_With_Reference();
      webDriver.findElement(By.xpath("//button[@id='searchtext']")).click();
      webDriver.findElement(By.id("query")).sendKeys("137151292");
      webDriver.findElement(By.xpath("//form/div/button")).click();
      Thread.sleep(2000);
      //Locate the business name and click on it.
      webDriver.findElement(By.linkText("Entity 399 Legal Business Name")).click();
      Thread.sleep(3000);
      //Locate the Pending MPP application and click on it.
      webDriver.findElement(By.linkText("MPP Application")).click();
      //Verify the Open Application Summary link.
      Actual_Text = webDriver.findElement(By.cssSelector("button.usa-button-outline")).getText();
      Expected_Text = "Open application summary";
      assertEquals(Actual_Text, Expected_Text);
      //Verify the Return to Vendor link.
      Actual_Text = webDriver.findElement(By.xpath("//div[2]/a/button")).getText();
      Expected_Text = "Return to Vendor";
      assertEquals(Actual_Text, Expected_Text);
      //Click on the view summary link.
      webDriver.findElement(By.cssSelector("button.usa-button-outline")).click();
      //Click on the back button 
      webDriver.findElement(By.linkText("Back")).click();
      Thread.sleep(3000);
      //Click on the Return to Vendor link.
      webDriver.findElement(By.xpath("//div[2]/a/button")).click();
      Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
      Expected_Text = "A new application has been reopenned for the vendor";
      assertEquals(Actual_Text, Expected_Text);      
      webDriver.findElement(By.linkText("Logout")).click();      
      logger.info("Success");
      }
  @After
  public void tearDown() throws Exception {
      webDriver.close();
  }
}