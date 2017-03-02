package gov.sba.utils.integration;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D.Double;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;

public class NewScorpQuestionPage {
  private static final Logger logger = LogManager.getLogger(ScorpQuestionsPage.class.getName());
  WebDriver webDriver;

  public NewScorpQuestionPage(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void  NewScorpQuestion() throws Exception {
    String actual_Text1 = webDriver.findElement(By.cssSelector("h4")).getText();
    String expected_Text1 = "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
    assertEquals(actual_Text1, expected_Text1);
    // Verify the More detail meaning for the 8(A) question.
    String actual_Text2 = webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[2]")).getText();
    String expected_Text2 = "If the qualifying individual is not currently an 8(a) BD Program Participant, please select “No”. If the qualifying individual was already approved by the 8(a) BD Program and at least 51% of the business is held by women, you are eligible for the WOSB Program as an EDWOSB and you will skip forward to the “Review” section of this application. Please upload your original 8(a) Acceptance Letter and your most recent Annual Review Letter.";
    assertEquals(actual_Text2, expected_Text2);
    String actual_Text21 = webDriver.findElement(By.xpath("//div[@id='answers_8aq1']/fieldset/p[3]")).getText();
    String expected_Text21 = "If the qualifying individual is both 8(a) and Third-Party Certified, upload the documentation for both certifications.";
    assertEquals(actual_Text21, expected_Text21);
    webDriver.findElement(By.id("answers_228_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    logger.info("  8(a) question has been answered");
    // Locate the Third Party Certification, question1 and select No and
    // continue.
    String actual_Text3 = webDriver.findElement(By.cssSelector("h4")).getText();
    String expected_Text3 = "Is the qualifying individual(s) certified as a WOSB or EDWOSB by an SBA-approved Third-Party Certifier?";
    assertEquals(actual_Text3, expected_Text3);
    // Verify the detail meaning for the third party question.
    String actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_tpc1_q1']/fieldset/p[2]")).getText();
    String expected_Text = "You may self-certify for the WOSB Program through this website or you may elect to use the services of a Third-Party Certifier to demonstrate eligibility. There is no requirement to use a Third-Party Certifier. However, if you have worked with an SBA-approved Third-Party Certifier to review your business information, please upload the current Third-Party Certifier Certificate.";
    assertEquals(actual_Text, expected_Text);
    webDriver.findElement(By.id("answers_229_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    // Locate the Non-qualification question,Verify,select No and continue.
    String actual_Text4 = webDriver.findElement(By.cssSelector("h4")).getText();
    String expected_Text4 = "Has an SBA-approved Third-Party Certifier declined WOSB or EDWOSB certification for the qualifying individual(s)?";
    assertEquals(actual_Text4, expected_Text4);
    // Verify the more detail for the Non-qualification question.
    webDriver.findElement(By.id("answers_231_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    logger.info("  Third Party questions have been answered");
    // Locate the Three Business Corporation and S-Corp(Stocks) question
    webDriver.findElement(By.id("answers_232_value_yes")).click();
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    //webDriver.findElement(By.id("answers_232_comment")).sendKeys(" QA Testing");
    webDriver.findElement(By.id("answers_233_value_no")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_234_value_no")).click();
    webDriver.findElement(By.id("answers_235_value_no")).click();

    webDriver.findElement(By.id("answers_236_value_no")).click();

    webDriver.findElement(By.id("answers_237_value_no")).click();
     webDriver.findElement(By.id("answers_238_value_no")).click();
    webDriver.findElement(By.id("answers_238_comment")).sendKeys(" QA Testing");
    webDriver.findElement(By.name("commit")).click();
    // Locate the Citizenship & Ownership question 1and2, Verify,select No
    // and continue.
    // Verify the more detail meaning for the Citizenship & Ownership

    webDriver.findElement(By.id("answers_243_value_no")).click();
    webDriver.findElement(By.name("commit")).click();

    webDriver.findElement(By.id("answers_244_value_no")).click();

    webDriver.findElement(By.id("answers_245_value_no")).click();

    webDriver.findElement(By.id("answers_245_value_no")).click();

    webDriver.findElement(By.id("answers_246_value_no")).click();
    webDriver.findElement(By.id("answers_246_comment")).sendKeys(" QA Testing");
    webDriver.findElement(By.name("commit")).click();

    webDriver.findElement(By.id("answers_247_value_no")).click();

    webDriver.findElement(By.id("answers_248_value_no")).click();
    webDriver.findElement(By.id("answers_249_value_no")).click();
    webDriver.findElement(By.id("answers_250_value_no")).click();
    webDriver.findElement(By.id("answers_251_value_no")).click();
    webDriver.findElement(By.id("answers_252_value_no")).click();
    webDriver.findElement(By.id("answers_252_comment")).sendKeys("Qa Testing");
    webDriver.findElement(By.name("commit")).click();

    // Locate the SBA Exam & Daily Operations questions,Verify, select No
    // for both and continue.
    // 1st question.
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_253_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    webDriver.findElement(By.id("answers_254_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    webDriver.findElement(By.id("answers_255_value_no")).click();

    webDriver.findElement(By.id("answers_256_value_no")).click();
    webDriver.findElement(By.name("commit")).click();

    webDriver.findElement(By.id("answers_257_value_no")).click();
    webDriver.findElement(By.id("answers_258_value_no")).click();
    webDriver.findElement(By.id("answers_259_value_no")).click();
    webDriver.findElement(By.name("commit")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_260_value_no")).click();

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
    // Validate the Personal Information.
    webDriver.findElement(By.id("answers_261_value_new_button")).click();
    Thread.sleep(1000);
    webDriver.findElement(By.cssSelector("div.DTED_Lightbox_Close")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_261_value_new_button")).click();
    Thread.sleep(2000);
    // Verify that the section to Create new record is been seen by user and
    // enter record2.
    String actual_Text511 = webDriver.findElement(By.className("DTE_Header_Content")).getText();
    String expected_Text511 = "Create new record";
    assertEquals(actual_Text511, expected_Text511);
    logger.info("the page to Create and Add new Record");
    webDriver.findElement(By.id("DTE_Field_first_name")).sendKeys("DeepaMahesh");
    webDriver.findElement(By.id("DTE_Field_last_name")).sendKeys("P");
    webDriver.findElement(By.id("DTE_Field_title")).click();
    webDriver.findElement(By.xpath("//option[@value='Owner']")).click();
    webDriver.findElement(By.id("DTE_Field_ssn")).sendKeys("121-11-1211");
    webDriver.findElement(By.id("DTE_Field_marital_status")).click();
    webDriver.findElement(By.xpath("//option[@value='Married']")).click();
    webDriver.findElement(By.id("DTE_Field_address")).sendKeys("8421 Broad dr");
    webDriver.findElement(By.id("DTE_Field_city")).sendKeys("Mclean");
    webDriver.findElement(By.id("DTE_Field_state")).sendKeys("Virginia");
    webDriver.findElement(By.id("DTE_Field_postal_code")).sendKeys("22102");
    webDriver.findElement(By.id("DTE_Field_country")).sendKeys("United State");
    webDriver.findElement(By.id("DTE_Field_home_phone")).sendKeys("123-123-1234");
    webDriver.findElement(By.id("DTE_Field_business_phone")).sendKeys("123-123-1234");
    webDriver.findElement(By.id("DTE_Field_email")).sendKeys("Dee@gmail.com");
    Thread.sleep(1000);
    webDriver.findElement(By.xpath("//div[3]/button")).click();
    Thread.sleep(2000);
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