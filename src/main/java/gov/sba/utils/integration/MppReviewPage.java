package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import gov.sba.automation.CoreUtils;
import junit.framework.TestCase;

public class MppReviewPage extends TestCase {
  private static final Logger logger = LogManager.getLogger(MppReviewPage.class.getName());
  WebDriver webDriver;

  public MppReviewPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void MppReview() throws Exception {
      WebDriverWait wait = new WebDriverWait(webDriver, 30);
      logger.info("Review begins");
      String Actual_Text = null;
      String Expected_Text = null;
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
      Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
      Expected_Text = "Case Overview";
      assertEquals(Actual_Text, Expected_Text);
      // Click on the start button to start a review.
      webDriver.findElement(By.id("submit_button")).click();
      Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
      Expected_Text = "Question review";
      assertEquals(Actual_Text, Expected_Text);
      // Add a note.
      webDriver.findElement(By.xpath("//div[2]/div/div[2]/a")).click();
      webDriver.findElement(By.xpath("//textarea")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[2]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[2]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[3]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[4]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[4]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[5]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[5]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[6]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[6]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[7]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[7]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[8]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[8]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[9]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[9]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[10]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[10]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[11]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[11]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[12]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[12]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[13]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[14]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[14]")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[15]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[15]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[16]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[16]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[17]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[17]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[18]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[18]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[19]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[19]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");     
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[20]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[20]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[21]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[21]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[22]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[22]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[23]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[23]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");     
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[24]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[24]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[25]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[25]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[26]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[26]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[27]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[27]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");      
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[28]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[28]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[29]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[29]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[30]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[30]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[31]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[31]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");     
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[32]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[32]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[33]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[33]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");      
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[34]")).click();
      webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[34]")).sendKeys("so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");           
      // Click on the save and continue button.
      webDriver.findElement(By.id("save_notes")).click();
      assertEquals("", webDriver.getTitle());
      Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
      Expected_Text = "Signature review";
      assertEquals(Actual_Text, Expected_Text);
      // Add a note.
      webDriver.findElement(By.xpath("//select")).click();
      webDriver.findElement(By.xpath("//option[4]")).click();
      webDriver.findElement(By.xpath("//div/div[2]/div[2]/a")).click();
      webDriver.findElement(By.xpath("//textarea")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      // Click on the save and continue button.
      CoreUtils.clickContinue(webDriver);
      Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
      Expected_Text = "Determination";
      assertEquals(Actual_Text, Expected_Text);
      // Select Return for Modification
      webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
      webDriver.findElement(By.xpath("//textarea")).sendKeys(
          "so you can tell me there is a bird trapped in the terminal all the people ignoring it because they do not know what to do with it except to leave it alone until it scares itself to death it makes you terribly terribly sad You wish you could take the bird outside and set it free or(failing that) call a bird-understander to come help the bird All you can do is notice the bird and feel for the bird and write to tell me how language feels. test101");
      // Click on the save and continue button.
      CoreUtils.clickContinue(webDriver);
      Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
      Expected_Text = "You can view the vendor's record but can not make edits";
      assertEquals(Actual_Text, Expected_Text);
      // Click on Vendor Overview link.
      webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();
      webDriver.findElement(By.linkText("Logout")).click();            
  }}     
        
        