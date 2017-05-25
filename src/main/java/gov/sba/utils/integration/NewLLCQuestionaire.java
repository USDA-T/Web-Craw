package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.FixtureUtils;

public class NewLLCQuestionaire {
  private static final Logger logger = LogManager.getLogger(NewLLCQuestionaire.class.getName());
  WebDriver webDriver;

  public NewLLCQuestionaire(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void NewLlcquestions() throws Exception {
    String Actual_Text = null;
    String Expected_Text = null;
    // Locate the 8(a) question and select No and continue.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
    assertEquals(Actual_Text, Expected_Text);
    // Verify the More detail meaning for the 8(A) question.
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/h4")).getText();
    Expected_Text =
        "If the qualifying individual is not currently an 8(a) BD Program Participant, please select “No”. If the qualifying individual was already approved by the 8(a) BD Program and at least 51% of the business is held by women, you are eligible for the WOSB Program as an EDWOSB and you will skip forward to the “Review” section of this application. Please upload your original 8(a) Acceptance Letter and your most recent Annual Review Letter.";
    assertEquals(Actual_Text, Expected_Text);
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[3]")).getText();
    Expected_Text =
        "If the qualifying individual is both 8(a) and Third-Party Certified, upload the documentation for both certifications.";
    assertEquals(Actual_Text, Expected_Text);

    try {
      webDriver.findElement(By.id("answers_188_value_no")).click();
    } catch (Exception e1) {
      webDriver.findElement(By.id("answers_247_value_no")).click();
    }
    webDriver.findElement(By.name("commit")).click();
    webDriver.findElement(By.name("commit")).click();
    logger.info("  8(a) question has been answered");
    // Locate the Third Party Certification, question1 and select No and
    // continue.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Is the qualifying individual(s) certified as a WOSB or EDWOSB by an SBA-approved Third-Party Certifier?";
    assertEquals(Actual_Text, Expected_Text);
    // Verify the detail meaning for the third party question.
    String actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_tpc1_q1']/fieldset/p[2]")).getText();
    String expected_Text =
        "You may self-certify for the WOSB Program through this website or you may elect to use the services of a Third-Party Certifier to demonstrate eligibility. There is no requirement to use a Third-Party Certifier. However, if you have worked with an SBA-approved Third-Party Certifier to review your business information, please upload the current Third-Party Certifier Certificate.";
    assertEquals(actual_Text, expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_189_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_248_value_yes")).click();
    }
    Thread.sleep(2000);
    webDriver.findElement(By.name("commit")).click();
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.genericUploadDoc(webDriver, "Yes", file_path_abs);
    webDriver.findElement(By.xpath("//input[@type='submit']")).click();
    Thread.sleep(2000);
    // Locate the Change in Eligiblity question,Verify,select No and continue.
    Actual_Text =
        webDriver.findElement(By.xpath("//*[@id='answers_tpc2_q1']/fieldset/h4")).getText();
    Expected_Text =
        "Since receiving an SBA-approved Third-Party Certification, have there been any changes in circumstances affecting the qualifying individual(s) eligibility?";
    assertEquals(Actual_Text, Expected_Text);

    // Verify the more detail for the Change in Eligiblity question.
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_190_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_249_value_yes")).click();
    }
    Thread.sleep(2000);
    webDriver.findElement(By.name("commit")).click();
    logger.info("  Change Eligiblity questions have been answered");
    // Locate the LLC two question and select Yes, and upload the document
    // continue.
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_201_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_250_value_yes")).click();
    }
    file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.genericUploadDoc(webDriver, "Yes", file_path_abs);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_202_value_no")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_251_value_no")).click();
    }
    // file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    // fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_202_comment")).sendKeys(" QA Testing");
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_252_comment")).click();
    }

    webDriver.findElement(By.name("commit")).click();
    Thread.sleep(2000);
    logger.info("The LLC questions have been answered");

    // Locate the Citizenship & Ownership question 1and2, Verify,select No
    // and continue.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Do the birth certificates, naturalization papers, or passports show the qualifying individual(s) are U.S. citizens?";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_203_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_203_value_yes")).click();
    }
    file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    webDriver.findElement(By.name("commit")).click();
    // Verify the more detail meaning for Ownership questions.
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_oper1_q2 > fieldset > h4")).getText();
    Expected_Text =
        "Is the following statement true? The qualifying individual(s) is not subject to any conditions, executory agreements, voting trusts, or other arrangements that cause or potentially cause ownership benefits to go to another person.";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_204_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_254_value_yes")).click();
    }
    // 2nd
    Actual_Text =
        webDriver.findElement(By.xpath("//*[@id='answers_oper2_q2']/fieldset/h4")).getText();
    Expected_Text =
        "If the 51% ownership is held through a trust, is the trust revocable, and does it designate the qualifying individual(s) as the grantor, the trustee, and the sole current beneficiary?";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_205_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_255_value_yes")).click();
    }
    // 3rd
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text =
        "Is the qualifying individual’s ownership direct; that is the ownership is not held through another business entity (including employee stock ownership plan) that is, in turn, owned and controlled by the qualifying individual(s)?";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_206_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_256_value_yes")).click();
    }
    // 4th
    Thread.sleep(2000);
    webDriver.findElement(By.name("commit")).click();
    logger.info("the Ownership questions are answered sucessfully");
    // Management questions starts
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_oper3_q1']/fieldset/h4")).getText();
    Expected_Text =
        "Are the management and daily operations of the business controlled by the qualifying individual(s)?";
    assertEquals(Actual_Text, Expected_Text);
    // 1st question meaning.
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_207_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_247_value_yes")).click();
    }
    // 2nd question.
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_oper3_q2']/fieldset/h4")).getText();
    Expected_Text =
        "Does the qualifying individual(s) hold the highest officer position in the business and does she have the managerial experience needed to run the business?";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_208_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_248_value_yes")).click();
    }
    Thread.sleep(2000);
    file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.genericUploadDoc(webDriver, "Yes", file_path_abs);
    Thread.sleep(2000);
    // 3rd question meaning.
    Actual_Text =
        webDriver.findElement(By.xpath("//*[@id='answers_oper4_q1']/fieldset/h4")).getText();
    Expected_Text =
        "Does the qualifying individual(s) have ultimate managerial and supervisory control over those who possess the required licenses or technical expertise for the business? The qualifying individual(s) herself may have the technical expertise or possess the required license for the business.";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_209_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_249_value_yes")).click();
    }
    Thread.sleep(2000);
    // 4th Question
    Actual_Text =
        webDriver.findElement(By.cssSelector("#answers_oper4_q2 > fieldset > h4")).getText();
    Expected_Text =
        "Does the qualifying individual(s) who holds the highest officer position manage the business on a full-time basis and devote full-time attention to the business during the normal working hours of similar businesses?";
    assertEquals(Actual_Text, Expected_Text);
    Thread.sleep(2000);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_210_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_250_value_yes")).click();
    }
    Thread.sleep(2000);
    // 5th Question
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_oper5_q2']/fieldset/p[2]")).getText();
    Expected_Text =
        "Does the qualifying individual(s) fully control the business, that is, no one else has actual control or has the power to control the business?";
    assertEquals(Actual_Text, Expected_Text);
    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_211_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_251_value_yes")).click();
    }
    // 6th question.
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_oper6_q2']/fieldset/p[2]")).getText();
    Expected_Text =
        "Is the qualifying individual(s) in control of long-term decision making and day-to-day operations?";
    assertEquals(Actual_Text, Expected_Text);

    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_212_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_212_value_yes")).click();
    }
    //
    webDriver.findElement(By.name("commit")).click();
    Thread.sleep(2000);
    // SBA Exam
    Actual_Text =
        webDriver.findElement(By.xpath("//div[@id='answers_oper6_q1']/fieldset/p[2]")).getText();
    Expected_Text =
        "Is the following statement true? The qualifying individual(s) has not received a decision from the SBA – in connection to an examination or protest – finding that the business does not qualify as a WOSB or an EDWOSB.";
    assertEquals(Actual_Text, Expected_Text);

    try {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_213_value_yes")).click();
    } catch (Exception e1) {
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_253_value_yes")).click();
    }
    Thread.sleep(2000);
    webDriver.findElement(By.name("commit")).click();
    // check if the certificate is for Wosb - Then Review page should display else if it Edwosb the
    // finanical section should display
    String text = webDriver
        .findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(text(),'Review')]"))
        .getText();
    logger.info(text);
    if (text.toLowerCase() == "Review") {
      logger.info("Wosb Application LLC questionaire answered sucessfully");
      webDriver.findElement(By.xpath("//input[@type='submit']")).click();
      Thread.sleep(2000);
      try {
        logger.info("Check Alert");
        webDriver.switchTo().alert().accept();
      } catch (Exception excp) {
        logger.info("No Alert");
      }
    }}
  private void assertEquals(String actual_Text1, String expected_Text1) {
    // TODO Auto-generated method stub
  }
}

