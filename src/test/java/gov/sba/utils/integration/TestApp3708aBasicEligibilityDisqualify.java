package gov.sba.utils.integration;

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

public class TestApp3708aBasicEligibilityDisqualify extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestApp3708aBasicEligibilityDisqualify.class.getName());
  private static WebDriver webDriver;
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
    try{
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    String Actual_Text;
    String Expected_Text;
    // Login to dashboard.
    logger.info("8(a) Basic Eligibility disqualifying warning message test");
    // Locate the accept button and click on it.
    // Login to dashboard.
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    // Verify if there is an existing certification on the dashboard and
    // delete to start a new certification.
    DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
    deleteDraftCert.DeleteDraftCert();
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
    Actual_Text = webDriver.findElement(By.linkText("Basic Eligibility")).getText();
    Expected_Text = "Basic Eligibility";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Status.
    Actual_Text = webDriver.findElement(By.cssSelector("td.not_started")).getText();
    Expected_Text = "NOT STARTED";
    assertEquals(Actual_Text, Expected_Text);
    // Click on the link to start eligibility check.
    webDriver.findElement(By.linkText("Basic Eligibility")).click();
    // Verify intro page.
    //Actual_Text = webDriver.findElement(By.xpath("//form/div/div/p")).getText();
    //Expected_Text =
        //"The Federal government relies on the information in the forms and any documents or supplemental information submitted to determine whether your business is eligible to participate in the 8(a) Business Development Program. The definition of important terms are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 CFR § 124.3), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person providing information may be prosecuted if they have provided false information. The Government may pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
    //assertEquals(Actual_Text, Expected_Text);
    // Click on the accept button.
    //CoreUtils.clickContinue(webDriver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4")));
    // ===>Section 1: Eligibility Screening, Subsection 1.1: Screen.
    // Verify and validate this question
    // Q1.1.a.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Is the applicant firm a for-profit business?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section.
    Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    Expected_Text =
        "The applicant firm must be a for-profit business in order to qualify for the 8(a) Business Development Program. If the applicant firm is not a for-profit business, it is not eligible for program participation.";
    assertEquals(Actual_Text, Expected_Text);
    // Select No and verify the disqualifying warning message.
    webDriver.findElement(By.xpath("//label[2]")).click();
    // Verify the warning message.
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.usa-alert-text")));
    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
    Expected_Text =
        "To qualify for the 8(a) Business Development Program, the applicant firm must be organized as for-profit business.";
    assertEquals(Actual_Text, Expected_Text);
    // Q1.1.b.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_is_broker > fieldset > h4")).getText();
    Expected_Text = "Is the applicant firm operating as a broker?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_is_broker > fieldset > p")).getText();
    Expected_Text =
        "A broker adds no material value to an item being supplied to a procuring activity. If the applicant firm does not take ownership or possession of, or handle the item being procured with its own equipment or facilities, the applicant firm may be operating as a broker. Please note that SBA’s definition of ‘broker’ does not include real estate or investment brokers.";
    assertEquals(Actual_Text, Expected_Text);
    ///// Verify link. +++Open when link added.
    // webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    // String main_window = webDriver.getWindowHandle();
    // logger.info("Before switching, title is = certify.sba.gov");
    // webDriver.findElement(By.linkText("13 CFR § 124.108(d).")).click();
    // assertEquals(Actual_Text, Expected_Text);
    // java.util.Set<String> S = webDriver.getWindowHandles();
    // Iterator<String> i = S.iterator();
    // while (i.hasNext()) {
    // String Second_window = i.next();
    // if (!main_window.equalsIgnoreCase(Second_window)) {
    // webDriver.switchTo().window(Second_window);
    // logger.info("After switching title is = certify.sba.gov");
    // webDriver.close();
    // webDriver.switchTo().window(main_window);
    // logger.info("Back to manin_window = certify.sba.gov");
    // } else {
    // logger.info("Second Window is not thesame as first window");
    // }
    // }
    // Select Yes and verify the disqualifying warning message.
    webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("//div[2]/fieldset/div[2]/div/div/p")));
    Actual_Text = webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div/div/p")).getText();
    Expected_Text =
        "In order to participate in 8(a) Business Development Program, the applicant firm must not be a broker. Please email 8aBD@sba.gov for assistance if you are unsure about your status as a broker. Include your firm name, DUNS number and address in the email.";
    assertEquals(Actual_Text, Expected_Text);
    // Q1.1.c.
    Actual_Text = webDriver.findElement(By.cssSelector("#answers_generate_revenue > fieldset > h4"))
        .getText();
    Expected_Text = "Has the firm generated any revenue?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section Paragraph1.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_generate_revenue > fieldset > p")).getText();
    Expected_Text =
        "Your firm must demonstrate potential for success by showing that it has been in business in its primary industry for two years, or you will need to request a waiver of this requirement. If the business concern has not yet generated revenues, you will not be successful in obtaining a waiver. Reference: 13 CFR § 124.107.\n\nPlease note that firms owned by Alaska Native Corporations, Tribes, Native Hawaiian Organizations, and Community Development Corporations have special rules regarding potential for success. Therefore, if your firm is entity-owned, select “Not applicable”. Reference: 13 CFR §§ 124.109(c)(6), 124.110(g), and 124.111(f).";
    assertEquals(Actual_Text, Expected_Text);
    // Select No and verify the disqualifying warning message.
    webDriver.findElement(By.xpath("//div[3]/fieldset/div/label[2]")).click();
    // Verify the warning message.
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("(//div[@id='disqualifier-warning']/div/div/p)[3]")));
    Actual_Text = webDriver
        .findElement(By.xpath("(//div[@id='disqualifier-warning']/div/div/p)[3]")).getText();
    Expected_Text =
        "In order to participate in 8(a) Business Development Program, the applicant firm must demonstrate potential for success by showing that it has been in business in its primary industry for two years, or you will need to request a waiver of this requirement. If the business concern has not yet generated revenues, you will not be successful in obtaining a waiver. Please note this is not applicable to entity-owned firms. Please email 8aBD@sba.gov for assistance if you are unsure about the firm’s revenue status. Include your firm name, DUNS number and address in the email.";
    assertEquals(Actual_Text, Expected_Text);
    // Q1.1.d.
    Actual_Text = webDriver
        .findElement(By.cssSelector("#answers_disadvantaged_citizens > fieldset > h4")).getText();
    Expected_Text =
        "Are all of the individual(s) claiming disadvantaged status in the applicant firm U.S. citizens?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section Paragraph1.
    Actual_Text = webDriver
        .findElement(By.cssSelector("#answers_disadvantaged_citizens > fieldset > p")).getText();
    Expected_Text =
        "A U.S. citizen means a person born or naturalized in the United States. Resident aliens and holders of permanent visas are not considered to be citizens for program purposes. If the applicant firm is entity-owned, select ‘Not applicable’.";
    assertEquals(Actual_Text, Expected_Text);
    // Select No and verify the disqualifying warning message.
    webDriver.findElement(By.xpath("//div[4]/fieldset/div/label[2]")).click();
    // Verify the warning message.
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(By.xpath("//div[4]/fieldset/div[2]/div/div/p")));
    Actual_Text = webDriver.findElement(By.xpath("//div[4]/fieldset/div[2]/div/div/p")).getText();
    Expected_Text =
        "In order to participate in 8(a) Business Development Program, the individual(s) claiming disadvantaged status must be a U.S. citizen.";
    assertEquals(Actual_Text, Expected_Text);
    // Q1.1.e.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_have_dba > fieldset > h4")).getText();
    Expected_Text = "Does the applicant firm have a Doing Business As (DBA) Name?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section Paragraph1.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_have_dba > fieldset > p")).getText();
    Expected_Text =
        "If yes, please add names. Doing Business As Name may include an Assumed Name, Fictitious Name, or Trade Name.";
    assertEquals(Actual_Text, Expected_Text);
    // Select Yes and enter comment.
    webDriver.findElement(By.xpath("//div[5]/fieldset/div/input")).click();
    webDriver.findElement(By.xpath("//textarea")).sendKeys(
        "Cats are among the most feared and revered creatures on the planet.  Their power, strength, and enigmatic nature have fascinated us for centuries.  They’ve dominated human culture since the dawn of civilization.  Go from the rainforests, to the savannah, to the mountain peaks all the way into the comfort of our homes.  Get an in-depth look at this unique species and the evolutionary tricks and adaptations that truly make a cat, a cat. Cats are loving animals as all creature of mother nature are.");
    // Click on the Save and Continue button.
    CoreUtils.clickContinue(webDriver);
    // ===>Subsection 1.2: Prior 8(a) Involvement.
    // Q1.2.a.
    // Verify and Validate this Question.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Prior 8(a) Involvement";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Was the applicant firm ever a certified 8(a) Business Development Program Participant?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section correct open when fix.
    Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    Expected_Text =
        "The 8(a) Business Development Program has one-time eligibility. The applicant firm must not have participated in the 8(a) Business Development Program in the past (including participation under another name, voluntary withdrawal, termination, or graduation).";
    assertEquals(Actual_Text, Expected_Text);
    // Select Yes and verify the disqualifying warning message.
    webDriver.findElement(By.xpath("//div/input")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='disqualifier-warning']/div/div/p")));
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='disqualifier-warning']/div/div/p")).getText();
    Expected_Text = "The 8(a) Business Development Program has one-time eligibility.";
    assertEquals(Actual_Text, Expected_Text);
    // Q1.2.b.
    // Verify and Validate this Question.
    Actual_Text = webDriver
        .findElement(By.cssSelector("#answers_submitted_app_to_8a > fieldset > h4")).getText();
    Expected_Text =
        "Has the applicant firm ever submitted an application to the 8(a) Business Development Program?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section.
    Actual_Text = webDriver
        .findElement(By.cssSelector("#answers_submitted_app_to_8a > fieldset > p")).getText();
    Expected_Text =
        "If yes, describe what happened to the previous application to the 8(a) Business Development Program. Possibilities may include that the applicant firm may have applied but failed to respond to a screening letter, or the applicant firm may have withdrawn the application prior to a formal decision, or the applicant firm may have been denied program participation. If the applicant firm was previously denied, it must wait one year from the date of the last denial letter to apply. Please include the date of the last denial letter. Please respond ‘yes’ if the applicant firm has ever submitted an application under another name.";
    assertEquals(Actual_Text, Expected_Text);
    // Select Yes.
    webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
    // 1.2.c
    // Verify and Validate this Question.
    Actual_Text = webDriver
        .findElement(
            By.cssSelector("#answers_previous_participant_assets_over_50_percent > fieldset > h4"))
        .getText();
    Expected_Text =
        "Do the assets of a previously certified 8(a) Business Development Program Participant constitute 50% or more of applicant firm’s assets?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section.
    Actual_Text = webDriver
        .findElement(
            By.cssSelector("#answers_previous_participant_assets_over_50_percent > fieldset > p"))
        .getText();
    Expected_Text =
        "If yes, provide the name of the 8(a) Program Participant and any relevant acquisition documents. Assets may include machinery, contracts, equipment, etc. At the time of application, if the assets of a former 8(a) Program Participant constitute 50% or more of applicant firm’s assets, then the applicant firm will not be eligible for participation in the program because of one-time eligibility.";
    assertEquals(Actual_Text, Expected_Text);
    // Select NO and attached a doc.
    webDriver.findElement(By.xpath("//div[3]/fieldset/div/label[2]")).click();
    // Upload document.
    // String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
    // MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
    // MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
    // Thread.sleep(2000);
    // Click on the Save and Continue button.
    CoreUtils.clickContinue(webDriver);
    webDriver.findElement(By.xpath("//textarea")).sendKeys(
        "Cats are among the most feared and revered creatures on the planet.  Their power, strength, and enigmatic nature have fascinated us for centuries.  They’ve dominated human culture since the dawn of civilization.  Go from the rainforests, to the savannah, to the mountain peaks all the way into the comfort of our homes.  Get an in-depth look at this unique species and the evolutionary tricks and adaptations that truly make a cat, a cat. Cats are loving animals as all creature of mother nature are.");
    CoreUtils.clickContinue(webDriver);
    // ===>Subsection 1.3: Outside Assistance.
    // 1.3.a
    // Verify and Validate this Question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Did the applicant firm hire an outside consultant to assist with its 8(a) application?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section.
    Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    Expected_Text =
        "If yes, upload a signed representative information form, proof of payment and the consulting agreement (if applicable). Outside consultants may include attorneys, accountants, appraisers, agents, or other representatives who assisted in the preparation of the application.";
    assertEquals(Actual_Text, Expected_Text);
    // verify link.
    ///// Verify link. +++Open when link added.
    // webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    // String main_window = webDriver.getWindowHandle();
    // logger.info("Before switching, title is = certify.sba.gov");
    // webDriver.findElement(By.linkText("representative information")).click();
    // assertEquals(Actual_Text, Expected_Text);
    // java.util.Set<String> S1 = webDriver.getWindowHandles();
    // Iterator<String> i1 = S1.iterator();
    // while (i1.hasNext()) {
    // String Second_window = i1.next();
    // if (!main_window.equalsIgnoreCase(Second_window)) {
    // webDriver.switchTo().window(Second_window);
    // logger.info("After switching title is = certify.sba.gov");
    // webDriver.close();
    // webDriver.switchTo().window(main_window);
    // logger.info("Back to manin_window = certify.sba.gov");
    // } else {
    // logger.info("Second Window is not thesame as first window");
    // }
    // }
    // Select Yes and attached a doc.
    webDriver.findElement(By.xpath("//label[2]")).click();
    // Upload Doc.
    // file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
    // MontanaUploadDocumentPage MontanaUploadDocument1 = new MontanaUploadDocumentPage(webDriver);
    // MontanaUploadDocument1.MontanaUploadDocument(file_path_abs);
    // Click on the Save and Continue button.
    CoreUtils.clickContinue(webDriver);
    // ===>Subsection 1.4: Business Size.
    // Q1.4.a.
    // Verify and Validate this Question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Is the applicant firm considered small in accordance with its primary North American Industry Classification System (NAICS) code?";
    assertEquals(Actual_Text, Expected_Text);
    // Detail Section. open when fix.
    Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    Expected_Text =
        "If no, the applicant firm may be denied participation in the 8(a) Business Development Program, or a formal size determination may be requested by SBA officials.\n\nThe applicant firm must be small according to SBA’s size standards to participate in the program. Size standards have been established for types of economic activity, or industry, under the North American Industry Classification System (NAICS). To determine the size standard associated with a particular NAICS code, refer to the table of size standards in the Small Business Size Regulations, 13 CFR § 121.201. The applicable size standard is the one corresponding to the applicant firm’s primary NAICS code.";
    assertEquals(Actual_Text, Expected_Text);
    // Select No and verify the disqualifying warning message.
    webDriver.findElement(By.xpath("//label[2]")).click();
    // Verify the warning message.
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.usa-alert-text")));
    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
    Expected_Text =
        "To qualify for the 8(a) Business Development Program, the applicant firm must meet SBA’s small business size standards. Please email 8aBD@sba.gov for assistance if you are unsure if the firm meets SBA’s small business size standards. Include your firm name, DUNS number and address in the email.";
    assertEquals(Actual_Text, Expected_Text);
    // Q1.4.b.
    // Verify and Validate this Question.
    Actual_Text = webDriver
        .findElement(By.cssSelector("#answers_formal_determination > fieldset > h4")).getText();
    Expected_Text =
        "Has the applicant firm or any of its affiliates received a formal SBA size determination?";
    assertEquals(Actual_Text, Expected_Text);
    // Select No and verify that user navigates to the review page.
    webDriver.findElement(By.xpath("//div[2]/fieldset/div/label[2]")).click();
    CoreUtils.clickContinue(webDriver);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.usa-alert-text")));
    // Verify that Size Determination section is skipped and user is navigated to the review page.
    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
    Expected_Text =
        "To qualify for the 8(a) Business Development Program, the applicant firm must be organized as for-profit business.";
    assertEquals(Actual_Text, Expected_Text);
    // ===>Review section.
    Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
    Expected_Text = "8(A) Eligibility Summary";
    assertEquals(Actual_Text, Expected_Text);
    // Verify the review warning question for the Screening section.
    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
    Expected_Text =
        "To qualify for the 8(a) Business Development Program, the applicant firm must be organized as for-profit business.";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver
        .findElement(By.xpath("(//div[@id='disqualifier-warning']/div/div/p)[2]")).getText();
    Expected_Text =
        "In order to participate in 8(a) Business Development Program, the applicant firm must not be a broker. Please email 8aBD@sba.gov for assistance if you are unsure about your status as a broker. Include your firm name, DUNS number and address in the email.";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text = webDriver
        .findElement(By.xpath("(//div[@id='disqualifier-warning']/div/div/p)[4]")).getText();
    Expected_Text =
        "In order to participate in 8(a) Business Development Program, the individual(s) claiming disadvantaged status must be a U.S. citizen.";
    assertEquals(Actual_Text, Expected_Text);
    // Open this assertion when the right text is added.
    Actual_Text = webDriver
        .findElement(By.xpath("(//div[@id='disqualifier-warning']/div/div/p)[5]")).getText();
    Expected_Text = "The 8(a) Business Development Program has one-time eligibility.";
    assertEquals(Actual_Text, Expected_Text);
    // Verify the review warning question for the Business Size section.
    Actual_Text = webDriver
        .findElement(By.xpath("(//div[@id='disqualifier-warning']/div/div/p)[6]")).getText();
    Expected_Text =
        "To qualify for the 8(a) Business Development Program, the applicant firm must meet SBA’s small business size standards. Please email 8aBD@sba.gov for assistance if you are unsure if the firm meets SBA’s small business size standards. Include your firm name, DUNS number and address in the email.";
    assertEquals(Actual_Text, Expected_Text);
    // webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Navigate back and verify in-progress status for the draft.
    webDriver.findElement(By.xpath("//a/span")).click();
    // click on the draft 8(a) Initial Program.
    webDriver.findElement(By.linkText("8(a) Initial Program")).click();
    Actual_Text = webDriver.findElement(By.xpath("//td[3]")).getText();
    Expected_Text = "IN PROGRESS";
    assertEquals(Actual_Text, Expected_Text);
    WebElement EligibilityInProgressStatus = webDriver.findElement(By.xpath("//td[3]"));
    HighLight.highLightElement(webDriver, EligibilityInProgressStatus);
    webDriver.findElement(By.linkText("Basic Eligibility")).click();
    webDriver.findElement(By.id("eight_a_basic_eligibility_assistance")).click();
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Business Size";
    assertEquals(Actual_Text, Expected_Text);
    webDriver.findElement(By.xpath("//label[2]")).click();
    webDriver.findElement(By.xpath("//div[2]/fieldset/div/label[2]")).click();
    CoreUtils.clickContinue(webDriver);
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Review";
    assertEquals(Actual_Text, Expected_Text);
    // Click on the Submit button.
    CoreUtils.clickContinue(webDriver);
    webDriver.switchTo().alert().accept();
    // verify completion of eligibility.
    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
    Expected_Text = "8(a) Basic Eligibility section is complete";
    assertEquals(Actual_Text, Expected_Text);
    WebElement EligibilityCompleteStatus = webDriver.findElement(By.xpath("//td[3]"));
    HighLight.highLightElement(webDriver, EligibilityCompleteStatus);
    webDriver.findElement(By.linkText("Logout")).click();
    logger.info("Success");
    }
    catch (Exception e) {
    ScreenShotPage screenShot = new ScreenShotPage(webDriver);
    screenShot.ScreenShot();
    logger.info(e.getMessage());  
    }
  
}
@After
public void tearDown() throws Exception {
  webDriver.close();
}
}


