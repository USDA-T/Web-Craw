package gov.sba.utils.integration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.CoreUtils;
import junit.framework.TestCase;

public class SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1Page extends TestCase {
  private static final Logger logger = LogManager
      .getLogger(SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1Page.class.getName());
  WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  public SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1Page(WebDriver webDriver) {
    this.webDriver = webDriver;
  }
  public void SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1() throws Exception {
    WebDriverWait wait = new WebDriverWait(webDriver, 40);
    String Actual_Text;
    String Expected_Text;
    logger.debug("Returning submited certification back to the two partners (Vendor)");
    // Verify first paragraph
    logger.info("  Verify first paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//div[2]/label")).getText();
    Expected_Text =
        "All required documents verifying eligibility for the Woman-Owned Small Business (WOSB) or Economically Disadvantaged Woman-Owned Small Business (EDWOSB) requirements have been submitted to the WOSB Program Repository, including any supplemental documents if there have been changes since the last representation.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify first paragraph
    logger.info("  Verify first paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//div[2]/label")).getText();
    Expected_Text =
        "All required documents verifying eligibility for the Woman-Owned Small Business (WOSB) or Economically Disadvantaged Woman-Owned Small Business (EDWOSB) requirements have been submitted to the WOSB Program Repository, including any supplemental documents if there have been changes since the last representation.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Second paragraph
    logger.info("  Verify second paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//label[2]")).getText();
    Expected_Text =
        "All the statements and information provided in this form and any documents submitted are true, accurate and complete. If assistance was obtained in completing this form and the supporting documentation, I have personally reviewed the information and it is true and accurate. I understand that these statements are made for the purpose of determining eligibility for a WOSB Program contract.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify third paragraph
    logger.info("  Verify third paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//label[3]")).getText();
    Expected_Text =
        "I understand that the information submitted may be given to Federal, State and local agencies for determining violations of law and other purposes. The certifications in this document are continuing in nature. Each WOSB or EDWOSB prime contract for which the WOSB or EDWOSB submits an offer/quote or receives an award constitutes a restatement and reaffirmation of these certifications.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify fourth paragraph
    logger.info("  Verify fourth paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//label[4]")).getText();
    Expected_Text =
        "Warning: By clicking the Submit Button, you are certifying that you are representing on your own behalf, and on behalf of the woman-owned small business or economically disadvantaged woman-owned small business, that the information provided in this certification, and any document or supplemental information submitted, is true and correct as of the date set forth opposite your signature. Any intentional or negligent misrepresentation of the information contained in this certification may result in criminal, civil or administrative sanctions including, but not limited to: 1) fines of up to $500,000, and imprisonment of up to 10 years, or both, as set forth in 15 U.S.C. § 645 and 18 U.S.C. § 1001, as well as any other applicable criminal laws; 2) treble damages and civil penalties under the False Claims Act; 3) double damages and civil penalties under the Program Fraud Civil Remedies Act; 4) suspension and/or debarment from all Federal procurement and non-procurement transactions; and 5) program termination.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify fifth paragraph
    logger.info("  Verify fifth paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//label[5]")).getText();
    Expected_Text =
        "I understand that the woman-owned small business or economically disadvantaged woman-owned small business may not misrepresent its status as a WOSB or EDWOSB to: 1) obtain a contract under the Small Business Act; or 2) obtain any benefit under a provision of Federal law that references the WOSB Program for a definition of program eligibility.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify sixth paragraph
    logger.info("  Verify sixth paragraph");
    Actual_Text = webDriver.findElement(By.xpath("//label[6]")).getText();
    Expected_Text =
        "By submitting this certification I, QA User, am an officer or owner of Entity 23 Legal Business Name authorized to represent it and electronically sign this certification on its behalf.";
    assertEquals(Actual_Text, Expected_Text);
    // Step 9 - Click the Continue button
    logger.info("Clicking accept buuton to trigger pop up alert");
    webDriver.findElement(By.id("accept-button")).click();
    wait.until(ExpectedConditions.alertIsPresent());
    Actual_Text = webDriver.switchTo().alert().getText();
    Expected_Text ="In order to submit your application, you must accept all of the conditions of authorization.";
    assertEquals(Actual_Text, Expected_Text);
    // Step 10 - Accept the error message
    logger.info("Step 10 - Accept the error message");
    webDriver.switchTo().alert().accept();
    // Step 11 - Accept the statements and click Continue
    logger.info("Step 11 - Click to accept the statements");
    webDriver.findElement(By.id("legal_0")).click();
    webDriver.findElement(By.id("legal_1")).click();
    webDriver.findElement(By.id("legal_2")).click();
    webDriver.findElement(By.id("legal_3")).click();
    webDriver.findElement(By.id("legal_4")).click();
    webDriver.findElement(By.id("legal_5")).click();
    webDriver.findElement(By.id("accept-button")).click();
    // Click on the dashboard button.
    webDriver.findElement(By.linkText("Dashboard")).click();
    WebElement ActiveCert =
        webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]"));
    HighLight.highLightElement(webDriver, ActiveCert);
    // Login as WOSB-analyst and return WOSB program back to vendor.
    webDriver.findElement(By.linkText("Logout")).click();
    get_The_Row_From_Login_Data = 0;
    LoginPageWithReference login_Data7 =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data7.Login_With_Reference();
    webDriver.findElement(By.xpath("//button[@id='searchtext']")).click();
    webDriver.findElement(By.id("query")).sendKeys("158899368");
    webDriver.findElement(By.xpath("//form/div/button")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Entity 23 Legal Business Name")));   
    webDriver.findElement(By.linkText("Entity 23 Legal Business Name")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("EDWOSB Self-Certification")));   
    webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_button")));   
    webDriver.findElement(By.id("submit_button")).click();
    // Question review.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "Question review";
    assertEquals(Actual_Text, Expected_Text);
    // Add question review notes.
    webDriver.findElement(By.id("note_link")).click();
    webDriver.findElement(By.id("assessments__note_body")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[20]")).click();
    webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[20]")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    // Click on the save and continue button.
    webDriver.findElement(By.id("save_notes")).click();
    // Take screenshot and store as a file format
    ScreenShotPage screenShot = new ScreenShotPage(webDriver);
    screenShot.ScreenShot();
    // Financial review for Denzel.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "Denzel Washington";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Denzel's net worth.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='table1-pad']/table/tbody/tr[11]/td[2]")).getText();
    Expected_Text = "-$256,651.05";
    assertEquals(Actual_Text, Expected_Text);
    // Add review for Denzel.
    webDriver.findElement(By.id("note_link")).click();
    webDriver.findElement(By.id("assessments__note_body")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[11]")).click();
    webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[11]")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    // Click on the save and continue button.
    webDriver.findElement(By.id("save_notes")).click();
    // Financial review for Will Smith.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "Will Smith";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Will's net worth.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='table1-pad']/table/tbody/tr[11]/td[2]")).getText();
    Expected_Text = "-$256,651.05";
    assertEquals(Actual_Text, Expected_Text);
    // Add review for Will Smith.
    webDriver.findElement(By.id("note_link")).click();
    webDriver.findElement(By.id("assessments__note_body")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[12]")).click();
    webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[12]")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    // Click on the save and continue button.
    webDriver.findElement(By.id("save_notes")).click();
    // Verify signature review for both partners.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "Signature review";
    assertEquals(Actual_Text, Expected_Text);
    // Add review for Signature page.
    webDriver.findElement(By.id("assessment_status")).click();
    webDriver.findElement(By.xpath("//option[5]")).click();
    webDriver.findElement(By.id("note_link")).click();
    webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    // Click on the save and continue button.
    CoreUtils.clickContinue(webDriver);
    // Verify that the notes are being saved.
    webDriver.findElement(By.linkText("Question review")).click();
    webDriver.findElement(By.linkText("Show more")).click();
    Actual_Text = webDriver.findElement(By.xpath("//div[3]/p")).getText();
    Expected_Text =
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.";
    assertEquals(Actual_Text, Expected_Text);
    WebElement SavedNotes = webDriver.findElement(By.xpath("//div[3]/p"));
    HighLight.highLightElement(webDriver, SavedNotes);
    // Click on Determination.
    webDriver.findElement(By.linkText("Determination")).click();
    // Verify Determination page and return certification back to vendor.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "Determination";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
    webDriver.findElement(By.cssSelector("#assessment_note_body")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
    // Click on the save and continue button.
    CoreUtils.clickContinue(webDriver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.usa-alert-text")));   
    // Verifying that the application was successfully return.
    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
    Expected_Text = "You can view the vendor's record but can not make edits";
    assertEquals(Actual_Text, Expected_Text);
    ScreenShotPage screenShot1 = new ScreenShotPage(webDriver);
    screenShot1.ScreenShot();
    webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();
    // Verify that the return certification is now on draft.
    Actual_Text =
        webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]")).getText();
    Expected_Text = "Draft";
    assertEquals(Actual_Text, Expected_Text);
    WebElement ReturnDraft =
        webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]"));
    HighLight.highLightElement(webDriver, ReturnDraft);
    webDriver.findElement(By.linkText("Logout")).click();
  }
}
