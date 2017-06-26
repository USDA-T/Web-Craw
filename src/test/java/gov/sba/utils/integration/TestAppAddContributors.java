package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class TestAppAddContributors extends TestCase {

  private static final Logger logger = LogManager.getLogger(TestAppAddContributors.class.getName());
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();

    webDriver.get(TestHelpers.getBaseUrl());
    webDriver.manage().window().maximize();
    get_The_Row_From_Login_Data = 12;
  }

  @Test
  public void testMainTest() throws Exception {
    String Actual_Text;
    String Expected_Text;
    logger.info(
        "As an 8(a) applicant, I want to enter my business' Control information on an 8(a) application, No with skip to review flow.");
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    // TestWorkFlowxx8aInProgress to start a new certification.
    DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
    deleteDraftCert.DeleteDraftCert();
    // TestWorkFlowxx8aInProgress to start a new certification.
    DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
    deleteDraftCert1.DeleteDraftCert();
    webDriver.navigate().to(
        "https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initia");
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Verify the Basic Eligibility link.
    Actual_Text = webDriver.findElement(By.linkText("Contributors")).getText();
    Expected_Text = "Contributors";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Status.
    Actual_Text = webDriver.findElement(By.xpath("//tr[2]/td[3]")).getText();
    Expected_Text = "NOT STARTED";
    assertEquals(Actual_Text, Expected_Text);
    WebElement ContributorStatusNotStarted = webDriver.findElement(By.xpath("//tr[2]/td[3]"));
    HighLight.highLightElement(webDriver, ContributorStatusNotStarted);
    // Click on the contributor link and verify page.
    webDriver.findElement(By.linkText("Contributors")).click();
    Actual_Text = webDriver.findElement(By.cssSelector("#main-content > p")).getText();
    Expected_Text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify all the 4 links.
    Actual_Text =
        webDriver.findElement(By.linkText("Start your individual application now")).getText();
    Expected_Text = "Start your individual application now";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver
        .findElement(By.linkText("Add another Disadvantaged Individual, if applicable")).getText();
    Expected_Text = "Add another Disadvantaged Individual, if applicable";
    assertEquals(Actual_Text, Expected_Text);
    // Details section note.
    Actual_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/p[2]")).getText();
    Expected_Text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text =
        webDriver.findElement(By.linkText("Add a spouse of a Disadvantaged Individual")).getText();
    Expected_Text = "Add a spouse of a Disadvantaged Individual";
    assertEquals(Actual_Text, Expected_Text);
    // Details section note.
    Actual_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/p[3]")).getText();
    Expected_Text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver.findElement(By.linkText("Add other individuals")).getText();
    Expected_Text = "Add other individuals";
    assertEquals(Actual_Text, Expected_Text);
    // Details section note.
    Actual_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/p[3]")).getText();
    Expected_Text =
        "p Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.linkText("Logout")).click();

  }

  @After
  public void tearDown() throws Exception {
    webDriver.close();
  }
}
