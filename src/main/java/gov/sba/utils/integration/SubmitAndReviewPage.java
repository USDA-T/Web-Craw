package gov.sba.utils.integration;


  import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;
  import org.openqa.selenium.By;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import junit.framework.TestCase;

  public class SubmitAndReviewPage extends TestCase {
    private static final Logger logger =
        LogManager.getLogger(SubmitAndReviewPage.class.getName());
    WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    public SubmitAndReviewPage(WebDriver webDriver) {
      this.webDriver = webDriver;
    }

    public void SubmitAndReview() throws Exception {
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
      logger.info("Step 9 - Click the Continue button");
      webDriver.findElement(By.id("accept-button")).click();
      Thread.sleep(2000);
      Actual_Text = webDriver.switchTo().alert().getText();
      Expected_Text =
          "In order to submit your application, you must accept all of the conditions of authorization.";
      assertEquals(Actual_Text, Expected_Text);
      Thread.sleep(3000);
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
      Thread.sleep(2000);
      webDriver.findElement(By.id("accept-button")).click();
      // Click on the dashboard button.
      webDriver.findElement(By.linkText("Dashboard")).click();
      WebElement ActiveCert =
          webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
      HighLight.highLightElement(webDriver, ActiveCert);
      // Login as WOSB-analyst and return WOSB program back to vendor.
      Thread.sleep(2000);
      webDriver.findElement(By.linkText("Logout")).click();
      get_The_Row_From_Login_Data = 0;
      LoginPageWithReference login_Data7 =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data7.Login_With_Reference();
      webDriver.findElement(By.xpath("//button[@id='searchtext']")).click();
      webDriver.findElement(By.id("query")).sendKeys("158899368");
      webDriver.findElement(By.xpath("//form/div/button")).click();
      Thread.sleep(2000);
      webDriver.findElement(By.linkText("Entity 23 Legal Business Name")).click();
      Thread.sleep(2000);
      logger.info("Return to Vendor Link is missing please verify why.");
      webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
      Thread.sleep(3000);
      webDriver.findElement(By.id("submit_button")).click();
      //Question review.
      Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
      Expected_Text ="Question review";
      assertEquals(Actual_Text, Expected_Text);
      //Add question review notes.
      webDriver.findElement(By.id("note_link")).click();
      webDriver.findElement(By.id("assessments__note_body")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
      Thread.sleep(2000);
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[22]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[22]")).sendKeys("Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll.");
      //Click on the save and continue button.
      Thread.sleep(2000);
      webDriver.findElement(By.id("save_notes")).click();
      Thread.sleep(2000);
      //Take screenshot and store as a file format
      ScreenShotPage screenShot = new ScreenShotPage(webDriver);
      screenShot.ScreenShot();
      //Financial review.
      Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
      Expected_Text ="Financial review";
      assertEquals(Actual_Text, Expected_Text);
      //Verifying partners.
      Actual_Text = webDriver.findElement(By.linkText("Will Smith")).getText();
      Expected_Text ="Will Smith";
      assertEquals(Actual_Text, Expected_Text); 
      Actual_Text = webDriver.findElement(By.linkText("Denzel Washington")).getText();
      Expected_Text ="Denzel Washington";
      assertEquals(Actual_Text, Expected_Text); 
      
      logger.info("Error at this point, cant review fiancial section for two partners");
      //Take screenshot and store as a file format
      ScreenShotPage screenShot1 = new ScreenShotPage(webDriver);
      screenShot1.ScreenShot();

      
        
        
        
        
        
      Actual_Text = webDriver.findElement(By.xpath("")).getText();
      Expected_Text ="";
      assertEquals(Actual_Text, Expected_Text); 
      webDriver.findElement(By.linkText("Determination")).click();
      webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
      webDriver.findElement(By.xpath("//form[@id='new_determination']/input[5]")).click();
      webDriver.findElement(By.linkText("Vendor Overview")).click();
      //Logout and log back in with the vendor to add the second partner.
      webDriver.findElement(By.linkText("Logout")).click();
      get_The_Row_From_Login_Data = 8;
      LoginPageWithReference login_Data8 =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data8.Login_With_Reference();
      //Verify the returned draft certification.
      Thread.sleep(2000);
      Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
      Expected_Text ="Draft";
      assertEquals(Actual_Text, Expected_Text);
      WebElement ReturnDraft =
          webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
      HighLight.highLightElement(webDriver, ReturnDraft);
      //Click on the Draft cert.
      webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
      //Navigate to the financial section form413 and add a new partner.
      Thread.sleep(2000);
      webDriver.findElement(By.id("form413")).click();
      Thread.sleep(2000);
      logger.info("Adding second partner personal information");
      // Verify that user is being navigated to the Review Page.
      String actual_Text2 = webDriver.findElement(By.cssSelector("h2")).getText();
      String expected_Text2 = "Financial Data";
      assertEquals(actual_Text2, expected_Text2); 
      //Validate the Personal Information.
      webDriver.findElement(By.id("answers_99_value_new_button")).click();
      Thread.sleep(1000);
      webDriver.findElement(By.cssSelector("div.DTED_Lightbox_Close")).click();
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_99_value_new_button")).click();
      Thread.sleep(2000);
      // Verify that the section to Create new record is been seen by user and
      // enter record2.
      String actual_Text511 = webDriver.findElement(By.className("DTE_Header_Content")).getText();
      String expected_Text511 = "Create new record";
      assertEquals(actual_Text511, expected_Text511);
      logger.info("the page to Create and Add new Record is Present, PASS");
      webDriver.findElement(By.id("DTE_Field_first_name")).sendKeys("King");
      webDriver.findElement(By.id("DTE_Field_last_name")).sendKeys("Julian");
      webDriver.findElement(By.id("DTE_Field_title")).click();
      webDriver.findElement(By.xpath("//option[@value='President']")).click();
      webDriver.findElement(By.id("DTE_Field_ssn")).sendKeys("187669987");
      webDriver.findElement(By.id("DTE_Field_address")).sendKeys("8765 Jungle Drive");
      webDriver.findElement(By.id("DTE_Field_city")).sendKeys("Washington City");
      webDriver.findElement(By.id("DTE_Field_state")).sendKeys("Washington");
      webDriver.findElement(By.id("DTE_Field_postal_code")).sendKeys("28776");
      webDriver.findElement(By.id("DTE_Field_country")).sendKeys("United State");
      webDriver.findElement(By.id("DTE_Field_home_phone")).sendKeys("7024762987");
      webDriver.findElement(By.id("DTE_Field_business_phone")).sendKeys("7023764876");
      webDriver.findElement(By.id("DTE_Field_email")).sendKeys("Wsmith@mailinator.com");
      Thread.sleep(2000);
      webDriver.findElement(By.xpath("//div[3]/button")).click();
      Thread.sleep(3000);
      // Select No for question Is anyone listed above divorced? If yes,
      // please provide separation documents.
      Actions act4 = new Actions(webDriver);
      act4.doubleClick(webDriver.findElement(By.id("answers_100_value_yes"))).build().perform();
      // Locate the Continue Button and click on it to continue.
      Thread.sleep(2000);
      //Upload a document.
      String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
      MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
      MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
      Thread.sleep(2000);
      webDriver.findElement(By.xpath("//input[@name='commit']")).click();
      //click on the first person personal summary and navigate to the second person cash on hand.
      Thread.sleep(2000);
      webDriver.findElement(By.id("personal_summary_denzel_washington")).click();
      Thread.sleep(2000);
      webDriver.findElement(By.xpath("//input[@name='commit']")).click();
      Thread.sleep(2000);
      String actual_Text111 = webDriver.findElement(By.cssSelector("h2")).getText();
      String expected_Text111 = "Cash On Hand";
      assertEquals(actual_Text111, expected_Text111);
      logger.info("Entering Second partner Finances");

      }
    }
  
