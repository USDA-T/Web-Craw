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

import gov.sba.automation.CoreUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class TestApp375Applicant8aCharacterSectionNo extends TestCase {

  private static final Logger logger =
      LogManager.getLogger(TestApp375Applicant8aCharacterSectionNo.class.getName());
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();
        CommonApplicationMethods.get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
    webDriver.manage().window().maximize();
    get_The_Row_From_Login_Data = 12;
  }

  @Test
  public void testMainTest() throws Exception {
    try{
    String Actual_Text;
    String Expected_Text;
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    logger.info(
        "As an 8(a) applicant, I want to enter my business's Character information on an 8(a) application");
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    // delete to start a new certification.
    DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
    deleteDraftCert.DeleteDraftCert();
    // delete to start a new certification.
    DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
    deleteDraftCert1.DeleteDraftCert();
    webDriver.navigate().to(
        "https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a_initial");
    // webDriver.navigate().to("http://localhost/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a");
    // Verify new intro page.
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
    Actual_Text = webDriver.findElement(By.xpath("//form/div/div/p")).getText();
    Expected_Text =
        "The Federal government relies on the information in the forms and any documents or supplemental information submitted to determine whether your business is eligible to participate in the 8(a) Business Development Program. The definition of important terms are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 CFR § 124.3), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person providing information may be prosecuted if they have provided false information. The Government may pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
    assertEquals(Actual_Text, Expected_Text);
    CoreUtils.clickContinue(webDriver);
    // Verify the Basic Eligibility link.
    Actual_Text = webDriver.findElement(By.linkText("Character")).getText();
    Expected_Text = "Character";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Status.
    Actual_Text = webDriver.findElement(By.xpath("//tr[4]/td[3]")).getText();
    Expected_Text = "NOT STARTED";
    assertEquals(Actual_Text, Expected_Text);
    // Click on the link to start eligibility check.
    webDriver.findElement(By.linkText("Character")).click();
    // Verify intro page.
    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div/div/p")));
    //Actual_Text = webDriver.findElement(By.xpath("//form/div/div/p")).getText();
    //Expected_Text ="The Federal government relies on the information in the forms and any documents or supplemental information submitted to determine whether your business is eligible to participate in the 8(a) Business Development Program. The definition of important terms are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 CFR § 124.3), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person providing information may be prosecuted if they have provided false information. The Government may pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
    //assertEquals(Actual_Text, Expected_Text);
    // Click on the accept button.
    //CoreUtils.clickContinue(webDriver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Character";
    assertEquals(Actual_Text, Expected_Text);
    // Verify question and detail section and Select Yes and upload a
    // document.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Has the applicant firm (under any name) ever been debarred or suspended by any Federal entity?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail section.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='answers_character_16a']/fieldset/p[2]")).getText();
    Expected_Text =
        "If yes, provide the details regarding the debarment or suspension. Debarred or suspended firms or firms owned by debarred or suspended persons are ineligible for admission to the 8(a) Business Development program. If you have documents showing the debarment has been lifted please upload.";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.id("answers_169_value_no")).click();
    // webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Delinquent Liens Section.
    // Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    // Expected_Text = "Delinquent Liens";
    // assertEquals(Actual_Text, Expected_Text);
    // Click on the on the Save and continue button and verify that user is
    // prompted to answer the question.
    CoreUtils.clickContinue(webDriver);
    Actual_Text = webDriver.findElement(By.id("answers[170][value]-error")).getText();
    Expected_Text = "Please answer this question";
    assertEquals(Actual_Text, Expected_Text);
    // Verify question and detail section and Select Yes and upload a
    // document.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_character_16b > fieldset > h4")).getText();
    Expected_Text =
        "Does the applicant firm have any outstanding delinquent Federal, state or local financial obligations or liens filed against it?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail section.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='answers_character_16b']/fieldset/p[2]")).getText();
    Expected_Text =
        "Report any obligations including delinquent tax returns and delinquent SBA loans. If yes, provide any of the following that may apply:";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.id("answers_170_value_no")).click();
    // Click on the on the Save and continue button and verify that user is
    // prompted to answer the question.
    CoreUtils.clickContinue(webDriver);
    Actual_Text = webDriver.findElement(By.id("answers[171][value]-error")).getText();
    Expected_Text = "Please answer this question";
    assertEquals(Actual_Text, Expected_Text);
    // Verify question and detail section and Select Yes and upload a
    // document.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_character_16c > fieldset > h4")).getText();
    Expected_Text = "Is the applicant firm a defendant in any pending lawsuit?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail section.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='answers_character_16c']/fieldset/p[2]")).getText();
    Expected_Text =
        "If yes, summarize its interest in the suit, the claims, the current status, and provide a copy of the complaint, answer, and/or counterclaim filed in the suit.";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.id("answers_171_value_no")).click();
    // Click on the on the Save and continue button and verify that user is
    // prompted to answer the question.
    CoreUtils.clickContinue(webDriver);
    Actual_Text = webDriver.findElement(By.id("answers[172][value]-error")).getText();
    Expected_Text = "Please answer this question";
    assertEquals(Actual_Text, Expected_Text);
    // Verify question and detail section and Select Yes and upload a
    // document.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_character_16d > fieldset > h4")).getText();
    Expected_Text =
        "Has the applicant firm filed for bankruptcy or insolvency within the past 7 years?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail section.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='answers_character_16d']/fieldset/p[2]")).getText();
    Expected_Text =
        "If yes, provide details and a copy of the bankruptcy court’s final order or discharge.";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.id("answers_172_value_no")).click();
    CoreUtils.clickContinue(webDriver);
    // Review Section.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Review";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver
        .findElement(By.cssSelector("div.review_questions.question-separator > h3")).getText();
    Expected_Text = "Character";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text =
        webDriver.findElement(By.cssSelector("div.usa-width-three-fourths > p")).getText();
    Expected_Text =
        "Has the applicant firm (under any name) ever been debarred or suspended by any Federal entity?";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver
        .findElement(
            By.cssSelector("#character_16b > div.usa-grid-full > div.usa-width-three-fourths > p"))
        .getText();
    Expected_Text =
        "Does the applicant firm have any outstanding delinquent Federal, state or local financial obligations or liens filed against it?";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='character_16c']/div/div/p")).getText();
    Expected_Text = "Is the applicant firm a defendant in any pending lawsuit?";
    assertEquals(Actual_Text, Expected_Text);
    // Navigate back and verify in-progress status for the draft.
    webDriver.findElement(By.xpath("//a/span")).click();
    // click on the draft 8(a) Initial Program.
    webDriver.findElement(By.linkText("8(a) Initial Program")).click();
    Actual_Text = webDriver.findElement(By.xpath("//tr[4]/td[3]")).getText();
    Expected_Text = "IN PROGRESS";
    assertEquals(Actual_Text, Expected_Text);
    WebElement CharacterInProgressStatus = webDriver.findElement(By.xpath("//tr[4]/td[3]"));
    HighLight.highLightElement(webDriver, CharacterInProgressStatus);
    webDriver.findElement(By.linkText("Character")).click();
    // Thread.sleep(2000);
    webDriver.findElement(By.id("character")).click();
    CoreUtils.clickContinue(webDriver);
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Review";
    assertEquals(Actual_Text, Expected_Text);
    CoreUtils.clickContinue(webDriver);
    // webDriver.switchTo().alert().accept();
    // Click on the Save and Continue button.
    // Verify status.
    Actual_Text = webDriver.findElement(By.xpath("//tr[4]/td[3]")).getText();
    Expected_Text = "COMPLETE";
    assertEquals(Actual_Text, Expected_Text);
    WebElement CharacterComplteStatus = webDriver.findElement(By.xpath("//tr[4]/td[3]"));
    HighLight.highLightElement(webDriver, CharacterComplteStatus);
    webDriver.findElement(By.linkText("Logout")).click();
    }
    catch (Exception e) {
    ScreenShotPage screenShot = new ScreenShotPage(webDriver);
    screenShot.ScreenShot();
    logger.info(e.getMessage());    }
      }
@After
public void tearDown() throws Exception {
  webDriver.close();
}
}

