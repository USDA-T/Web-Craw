package gov.sba.utils.integration;

import static org.junit.Assert.assertEquals;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class NewScorpQuestionPage {
  private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
  WebDriver webDriver;

  public NewScorpQuestionPage(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void  NewScorpQuestion() throws Exception {
    String actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    String expected_Text = "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
    assertEquals(actual_Text, expected_Text);
    // Verify the More detail meaning for the 8(A) question.
    actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[2]")).getText();
    expected_Text = "If the qualifying individual is not currently an 8(a) BD Program Participant, please select “No”. If the qualifying individual was already approved by the 8(a) BD Program and at least 51% of the business is held by women, you are eligible for the WOSB Program as an EDWOSB and you will skip forward to the “Review” section of this application. Please upload your original 8(a) Acceptance Letter and your most recent Annual Review Letter.";
    assertEquals(actual_Text, expected_Text);
    actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[3]")).getText();
    expected_Text = "If the qualifying individual is both 8(a) and Third-Party Certified, upload the documentation for both certifications.";
    assertEquals(actual_Text, expected_Text);
    webDriver.findElement(By.id("answers_257_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    logger.info("  8(a) question has been answered");
    // Locate the Third Party Certification, question1 and select No and
    // continue.
    actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    expected_Text = "Is the qualifying individual(s) certified as a WOSB or EDWOSB by an SBA-approved Third-Party Certifier?";
    assertEquals(actual_Text, expected_Text);
    // Verify the detail meaning for the third party question.
    actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_tpc1_q1']/fieldset/p[2]")).getText();
    expected_Text = "You may self-certify for the WOSB Program through this website or you may elect to use the services of a Third-Party Certifier to demonstrate eligibility. There is no requirement to use a Third-Party Certifier. However, if you have worked with an SBA-approved Third-Party Certifier to review your business information, please upload the current Third-Party Certifier Certificate.";
    assertEquals(actual_Text, expected_Text);
    webDriver.findElement(By.id("answers_258_value_yes")).click();
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    webDriver.findElement(By.name("commit")).click();
    //Locate the Changes in Eligiblity
     actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
     expected_Text = "Since receiving an SBA-approved Third-Party Certification, have there been any changes in circumstances affecting the qualifying individual(s) eligibility?";
     assertEquals(actual_Text, expected_Text);
    // Verify the more detail for the Non-qualification question
     webDriver.findElement(By.id("answers_259_value_yes")).click();
     webDriver.findElement(By.name("commit")).click();
    // Locate the Non-qualification question,Verify,select No and continue.
   // actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
   // expected_Text = "Has an SBA-approved Third-Party Certifier declined WOSB or EDWOSB certification for the qualifying individual(s)?";
   // assertEquals(actual_Text, expected_Text);
    // Verify the more detail for the Non-qualification question.
  //  webDriver.findElement(By.id("answers_250_value_no")).click();
  //  webDriver.findElement(By.name("commit")).click();
    logger.info("  Third Party questions have been answered");
    // Locate the Three Business Corporation and S-Corp(Stocks) question
    webDriver.findElement(By.id("answers_261_value_yes")).click();
    file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    //webDriver.findElement(By.id("answers_251_comment")).sendKeys(" QA Testing");
    webDriver.findElement(By.id("answers_262_value_no")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_263_value_no")).click();
    webDriver.findElement(By.id("answers_264_value_no")).click();

    webDriver.findElement(By.id("answers_265_value_no")).click();

    webDriver.findElement(By.id("answers_266_value_no")).click();
     webDriver.findElement(By.id("answers_267_value_no")).click();
    webDriver.findElement(By.id("answers_267_comment")).sendKeys(" QA Testing");
    webDriver.findElement(By.name("commit")).click();
    // Locate the Citizenship & Ownership question 1and2, Verify,select No
    // and continue.
    // Verify the more detail meaning for the Citizenship & Ownership

    webDriver.findElement(By.id("answers_272_value_no")).click();
    webDriver.findElement(By.name("commit")).click();

    webDriver.findElement(By.id("answers_273_value_no")).click();
    webDriver.findElement(By.id("answers_274_value_no")).click();
    webDriver.findElement(By.id("answers_275_value_no")).click();
    webDriver.findElement(By.id("answers_275_comment")).sendKeys(" QA Testing");
    webDriver.findElement(By.name("commit")).click();

    webDriver.findElement(By.id("answers_276_value_no")).click();

    webDriver.findElement(By.id("answers_277_value_no")).click();
    webDriver.findElement(By.id("answers_278_value_no")).click();
    webDriver.findElement(By.id("answers_279_value_no")).click();
    webDriver.findElement(By.id("answers_280_value_no")).click();
    webDriver.findElement(By.id("answers_281_value_no")).click();
    webDriver.findElement(By.id("answers_281_comment")).sendKeys("Qa Testing");
    webDriver.findElement(By.name("commit")).click();

    // Locate the SBA Exam & Daily Operations questions,Verify, select No
    // for both and continue.
    // 1st question.
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_282_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    webDriver.findElement(By.id("answers_283_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    webDriver.findElement(By.id("answers_284_value_no")).click();

    webDriver.findElement(By.id("answers_285_value_no")).click();
    webDriver.findElement(By.name("commit")).click();

    webDriver.findElement(By.id("answers_286_value_no")).click();
    webDriver.findElement(By.id("answers_287_value_no")).click();
    webDriver.findElement(By.id("answers_288_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_289_value_no")).click();

    webDriver.findElement(By.name("commit")).click();
    logger.info("EDWOSB application questions have been answered");
    // Validate that user successfully navigated to the Financial Data
    // section.
    String actual_Text49 = webDriver.findElement(By.cssSelector("h2")).getText();
    String expected_Text49 = "Financial Data";
    assertEquals(actual_Text49, expected_Text49);
    String actual_Text52 = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    String expected_Text52 = "This section must be completed by each individual claiming economic disadvantage in connection with the 8(a) Program and/or the Women-Owned Small Business Federal Contract Program. If married, the spouse must complete this section, except when the individual and the spouse are legally separated. If separated, provide copy of separation document.";
    assertEquals(actual_Text52, expected_Text52);
    //Validate the Personal Information.
    webDriver.findElement(By.xpath("//fieldset/div[2]/button")).click();
    Thread.sleep(2000);
    // Verify that the section to Create new record is been seen by user and
    // enter record2.
    String actual_Text511 = webDriver.findElement(By.className("DTE_Header_Content")).getText();
    String expected_Text511 = "Create new record";
    assertEquals(actual_Text511, expected_Text511);
    logger.info("the page to Create and Add new Record is Present, PASS");
    webDriver.findElement(By.id("owners__first_name")).sendKeys("Denzel");
    webDriver.findElement(By.id("owners__last_name")).sendKeys("Washington");
    webDriver.findElement(By.id("owners__title")).click();
    webDriver.findElement(By.xpath("//option[2]")).click();
    webDriver.findElement(By.id("owners__ssn")).sendKeys("187669987");
    webDriver.findElement(By.id("owners__email")).sendKeys("DWashington@mailinator.com");
    webDriver.findElement(By.id("owners__marital_status")).click();
    webDriver.findElement(By.xpath("//div[5]/span/select/option[3]")).click();
    webDriver.findElement(By.id("owners__address")).sendKeys("8765 Weems dr");
    webDriver.findElement(By.id("owners__city")).sendKeys("Manassas");
    webDriver.findElement(By.id("owners__state")).sendKeys("Virginia");
    webDriver.findElement(By.id("owners__postal_code")).sendKeys("28776");
    webDriver.findElement(By.id("owners__country")).sendKeys("United State");
    webDriver.findElement(By.id("owners__home_phone")).sendKeys("7024762987");
    webDriver.findElement(By.id("owners__business_phone")).sendKeys("7023764876");
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//button[2]")).click();
    Thread.sleep(3000);
    // Select No for question Is anyone listed above divorced? If yes,
    // please provide separation documents.
    //Actions act4 = new Actions(webDriver);
    //act4.doubleClick(webDriver.findElement(By.id("answers_262_value_no"))).build().perform();
    // Locate the Continue Button and click on it to continue.
    Point coordinates = webDriver.findElement(By.xpath("//h2[contains(text(),'Financial Data')]")).getLocation();;
    Robot robot = new Robot();;
    robot.keyPress(KeyEvent.VK_ESCAPE);
    robot.mouseMove(coordinates.getX(),coordinates.getY());
    webDriver.findElement(By.xpath("//h2[contains(text(),'Financial Data')]")).click();
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(1500);
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(1500);
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(1500);
    robot.keyPress(KeyEvent.VK_RIGHT);
//    robot.keyPress(KeyEvent.VK_DOWN);
//    robot.keyPress(KeyEvent.VK_DOWN);

    Thread.sleep(3000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
  }
}