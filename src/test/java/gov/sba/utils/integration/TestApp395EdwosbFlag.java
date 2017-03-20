package gov.sba.utils.integration;

import static org.junit.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import gov.sba.automation.utils.CommonApplicationMethods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class TestApp395EdwosbFlag extends TestCase {

  //Get the questions names for which Prepopulate flag set to true
//Start create New Wosb/Edwosb application
//Check the Answers are prepopulating with previous answers.
  private static WebDriver webDriver;
  private static final Logger logger_TestApp395Edwosb = LogManager.getLogger(TestApp395EdwosbFlag.class.getName());
  String duns_Number, email, password;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 9;

  }
  @Test

  public void testMainTest() throws Exception {
    // Login to dashboard.
    try {
      // Check Dashboard Pending status
      LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data.Login_With_Reference();
      Thread.sleep(2000);
      //Return the Applicatiom;
      if (CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active"))
      { CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
        CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
        login_Data = new LoginPageWithReference(webDriver, 9);
        login_Data.Login_With_Reference();
      }
      CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      CommonApplicationMethods.deleteApplication( webDriver, "Wosb", "Draft");

      //start New Applicatiom
      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      webDriver.findElement(By.id("certificate_type_edwosb")).click();
      webDriver.findElement(By.id("add_certification")).click();
      webDriver.findElement(By.className("accept_button")).click();
      NewScorpQuestionPage scorpQuestionsPage = new NewScorpQuestionPage(webDriver);
      scorpQuestionsPage.NewScorpQuestion();
      NewFinancialSectionQuestion financialsection = new NewFinancialSectionQuestion(webDriver);
      financialsection.NewFinancialQuestion();
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      //Return the Applicatiom;
       CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
       CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
      //Delete All the Draft Applications
       CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
       login_Data = new LoginPageWithReference(webDriver, 9);
       login_Data.Login_With_Reference();
       CommonApplicationMethods.deleteApplication(webDriver, "Edwosb", "Draft");
      //Check the Pre-Answered with prior Answer.
      CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
      webDriver.findElement(By.id("certificate_type_edwosb")).click();
      webDriver.findElement(By.id("add_certification")).click();
      webDriver.findElement(By.className("accept_button")).click();

      // Verify the answers are prepopulated with prior answeres for certain questions and answers are not prepopulated for certian questions
      String checkBoxElement = webDriver.findElement(By.id("answers_247_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_247_value_no")).click();
      webDriver.findElement(By.name("commit")).click();
      logger_TestApp395Edwosb.info(" 8(a) question assert not being prepopulated");
      // Locate the Third Party Certification, question1 and select No and
      // continue.
      checkBoxElement = webDriver.findElement(By.id("answers_248_value_yes")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_248_value_yes")).click();
      String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
      fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
      webDriver.findElement(By.name("commit")).click();
      logger_TestApp395Edwosb.info("  Third party question assert not being prepopulated");
      //Locate Changes in Eligiblity
      checkBoxElement = webDriver.findElement(By.id("answers_249_value_yes")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_249_value_yes")).click();
      webDriver.findElement(By.name("commit")).click();
      logger_TestApp395Edwosb.info("  Chnage in Eligiblity assert not being prepopulated");
      // Locate the Three Business Corporation and S-Corp(Stocks) question
      checkBoxElement = webDriver.findElement(By.id("answers_251_value_yes")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_252_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_253_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_254_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_255_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_256_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_257_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);
      webDriver.findElement(By.name("commit")).click();
      // Locate the Citizenship & Ownership question 1and2, Verify,select No

      checkBoxElement = webDriver.findElement(By.id("answers_262_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);
      webDriver.findElement(By.name("commit")).click();

      checkBoxElement = webDriver.findElement(By.id("answers_263_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_264_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_265_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      webDriver.findElement(By.name("commit")).click();

      checkBoxElement = webDriver.findElement(By.id("answers_265_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_267_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_268_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_269_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_270_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_271_value_no")).getAttribute("outerHTML");
      assertTrue(checkBoxElement.toLowerCase().contains("checked"));
      Thread.sleep(200);

      webDriver.findElement(By.name("commit")).click();

      // Locate the SBA Exam & Daily Operations questions,Verify, select No
      // for both and continue.
      // 1st question.
      Thread.sleep(2000);
      checkBoxElement = webDriver.findElement(By.id("answers_272_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_272_value_no")).click();
      Thread.sleep(200);
      webDriver.findElement(By.name("commit")).click();
      checkBoxElement = webDriver.findElement(By.id("answers_273_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_273_value_no")).click();
      Thread.sleep(200);
      webDriver.findElement(By.name("commit")).click();

      checkBoxElement = webDriver.findElement(By.id("answers_274_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_274_value_no")).click();
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_275_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_275_value_no")).click();
      Thread.sleep(200);
      webDriver.findElement(By.name("commit")).click();

      checkBoxElement = webDriver.findElement(By.id("answers_276_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_276_value_no")).click();
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_277_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_277_value_no")).click();
      Thread.sleep(200);

      checkBoxElement = webDriver.findElement(By.id("answers_278_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_278_value_no")).click();
      Thread.sleep(200);

      webDriver.findElement(By.name("commit")).click();
      Thread.sleep(2000);

      checkBoxElement = webDriver.findElement(By.id("answers_279_value_no")).getAttribute("outerHTML");
      assertFalse(checkBoxElement.toLowerCase().contains("checked"));
      webDriver.findElement(By.id("answers_278_value_no")).click();
      Thread.sleep(200);
      webDriver.findElement(By.name("commit")).click();
      logger_TestApp395Edwosb.info("EDWOSB application questions have been answered");
      // Validate that user successfully navigated to the Financial Data
      // section.
      String actual_Text49 = webDriver.findElement(By.cssSelector("h2")).getText();
      String expected_Text49 = "Financial Data";
      assertEquals(actual_Text49, expected_Text49);
      String actual_Text52 = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
      String expected_Text52 = "This section must be completed by each individual claiming economic disadvantage in connection with the 8(a) Program and/or the Women-Owned Small Business Federal Contract Program. If married, the spouse must complete this section, except when the individual and the spouse are legally separated. If separated, provide copy of separation document.";
      assertEquals(actual_Text52, expected_Text52);
      // Validate the Personal Information.
      webDriver.findElement(By.id("answers_280_value_new_button")).click();
      Thread.sleep(1000);
      webDriver.findElement(By.cssSelector("div.DTED_Lightbox_Close")).click();
      Thread.sleep(2000);
      webDriver.findElement(By.id("answers_280_value_new_button")).click();
      Thread.sleep(2000);
      // Verify that the section to Create new record is been seen by user and
      // enter record2.
      String actual_Text511 = webDriver.findElement(By.className("DTE_Header_Content")).getText();
      String expected_Text511 = "Create new record";
      assertEquals(actual_Text511, expected_Text511);
      logger_TestApp395Edwosb.info("the page to Create and Add new Record");
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



      //Check if the Active certificate is exist-Then Return by analyst
      CommonApplicationMethods.checkApplicationExists(webDriver,"Edwosb","Active");
      CommonApplicationMethods.navigationMenuClick(webDriver,"LOGOUT");
      CommonApplicationMethods.return_all_Applications(webDriver, 11, "148832876");
    } catch (Exception e) {
      logger_TestApp395Edwosb.info(e.toString());
      throw new Exception("Error: ", e);
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}

