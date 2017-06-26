package gov.sba.utils.integration;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.verifier.exc.VerificationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class PotentialForSuccessMasterAppSectionPage extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(PotentialForSuccessMasterAppSectionPage.class.getName());
  WebDriver webDriver;

  public PotentialForSuccessMasterAppSectionPage(WebDriver webDriver) {
    this.webDriver = webDriver;

  }

  public void PotentialForSuccessMasterAppSection() throws Exception {
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    logger.info("Character section question begins");
    String Actual_Text = null;
    String Expected_Text = null;
    // Verify the Character Section link.
    Actual_Text = webDriver.findElement(By.linkText("Potential for Success")).getText();
    Expected_Text = "Potential for Success";
    assertEquals(Actual_Text, Expected_Text);
    // Verify Status.
    Actual_Text = webDriver.findElement(By.xpath("//tr[4]/td[3]")).getText();
    Expected_Text = "NOT STARTED";
    assertEquals(Actual_Text, Expected_Text);

    try {
      assertEquals("NOT STARTED", webDriver.findElement(By.xpath("//tr[5]/td[3]")).getText());
    } catch (Error e) {
      logger.info(e.getMessage());
    }
    // Click on the link to start Potential for Success.
    webDriver.findElement(By.linkText("Potential for Success")).click();
    assertEquals("", webDriver.getTitle());
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if ("Taxes".equals(webDriver.findElement(By.cssSelector("h2")).getText()))
          break;
      } catch (Exception e) {
      }
      Thread.sleep(1000);
    }

    webDriver.findElement(By.id("section_submit_button")).click();
    try {
      assertEquals("Attachment is required",
          webDriver.findElement(By.xpath("//div/span")).getText());
    } catch (Error e) {
      logger.info(e.getMessage());
    }
    webDriver.findElement(By.linkText("Add required documents")).click();
    webDriver.findElement(By.id("doc-lib-button")).click();
    webDriver.findElement(By.id("truth")).click();
    webDriver.findElement(By.id("document_library_associate")).click();
    webDriver.findElement(By.id("section_submit_button")).click();
    assertEquals("", webDriver.getTitle());
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if ("Revenue".equals(webDriver.findElement(By.cssSelector("h2")).getText()))
          break;
      } catch (Exception e) {
      }
      Thread.sleep(1000);
    }

    webDriver.findElement(By.id("section_submit_button")).click();
    try {
      assertEquals("Please answer this question",
          webDriver.findElement(By.xpath("//div/span")).getText());
    } catch (Error e) {
      logger.info(e.getMessage());
    }
    webDriver.findElement(By.xpath("//div/input")).click();
    webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[2]")).click();
    webDriver.findElement(By.name("634")).click();
    webDriver.findElement(By.xpath("(//input[@id='truth'])[2]")).click();
    webDriver.findElement(By.name("eight_a_pos_revenue_e")).click();
    webDriver.findElement(By.id("section_submit_button")).click();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).sendKeys("12333");
    webDriver.findElement(By.xpath("//td[2]/input")).clear();
    webDriver.findElement(By.xpath("//td[2]/input")).sendKeys("kbfkjdhfjkadkjf");
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).sendKeys("5678jjh");
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).sendKeys("thebdb 34");
    webDriver.findElement(By.xpath("//td[6]/button")).click();
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if ("The award date should be in MM/DD/YYYY format\nThe NAICS code should be a 5 or 6 digit number"
            .equals(webDriver.findElement(By.xpath("//div[2]/div/div/p")).getText()))
          break;
      } catch (Exception e) {
        logger.info(e.getMessage());
      }
      Thread.sleep(1000);
    }
    assertEquals(
        "The award date should be in MM/DD/YYYY format\nThe NAICS code should be a 5 or 6 digit number",
        webDriver.findElement(By.xpath("//div[2]/div/div/p")).getText());
    webDriver.findElement(By.xpath("//tr[3]/td/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).sendKeys("03/12/2016");
    webDriver.findElement(By.xpath("//td[2]/input")).clear();
    webDriver.findElement(By.xpath("//td[2]/input")).sendKeys("The Alliace");
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).sendKeys("123456");
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).sendKeys("Perform");
    webDriver.findElement(By.xpath("//tr[3]/td[5]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[5]/input")).sendKeys("67");
    webDriver.findElement(By.xpath("//td[6]/button")).click();
    webDriver.findElement(By.id("section_submit_button")).click();
    webDriver.findElement(By.id("answers_632_value")).clear();
    webDriver.findElement(By.id("answers_632_value")).sendKeys("56");
    webDriver.findElement(By.linkText("Add a row")).click();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).sendKeys("12/23/2009");
    webDriver.findElement(By.xpath("//td[2]/input")).clear();
    webDriver.findElement(By.xpath("//td[2]/input")).sendKeys("Google1");
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).sendKeys("12345");
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).sendKeys("Testing Time");
    webDriver.findElement(By.xpath("//tr[3]/td[5]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[5]/input")).sendKeys("123456");
    webDriver.findElement(By.xpath("//td[6]/button")).click();
    webDriver.findElement(By.linkText("Add a row")).click();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td/input")).sendKeys("10/23/1998");
    webDriver.findElement(By.xpath("//td[2]/input")).clear();
    webDriver.findElement(By.xpath("//td[2]/input")).sendKeys("Petco");
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[3]/input")).sendKeys("123456");
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[4]/input")).sendKeys("pets");
    webDriver.findElement(By.xpath("//tr[3]/td[5]/input")).clear();
    webDriver.findElement(By.xpath("//tr[3]/td[5]/input")).sendKeys("6000");
    webDriver.findElement(By.xpath("//td[6]/button")).click();
    webDriver.findElement(By.id("section_submit_button")).click();
    assertEquals("", webDriver.getTitle());
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if ("Potential for Success".equals(webDriver.findElement(By.cssSelector("h2")).getText()))
          break;
      } catch (Exception e) {
        logger.info(e.getMessage());
      }
    }

    webDriver.findElement(By.id("section_submit_button")).click();
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if ("Please answer this question"
            .equals(webDriver.findElement(By.xpath("//div/span")).getText()))
          break;
      } catch (Exception e) {
        logger.info(e.getMessage());
      }
    }

    assertEquals("Please answer this question",
        webDriver.findElement(By.xpath("//div/span")).getText());
    assertEquals("Please answer this question",
        webDriver.findElement(By.xpath("//div[2]/fieldset/div/span")).getText());
    assertEquals("Please answer this question",
        webDriver.findElement(By.xpath("//div[3]/fieldset/div/span")).getText());
    webDriver.findElement(By.xpath("//div/input")).click();
    webDriver.findElement(By.linkText("Add required documents")).click();
    webDriver.findElement(By.id("doc-lib-button")).click();
    webDriver.findElement(By.id("truth")).click();
    webDriver.findElement(By.id("document_library_associate")).click();
    webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[2]")).click();
    webDriver.findElement(By.name("636")).click();
    webDriver.findElement(By.xpath("(//input[@id='truth'])[2]")).click();
    webDriver.findElement(By.name("eight_a_pos_pos_a")).click();
    webDriver.findElement(By.xpath("//div[3]/fieldset/div/input")).click();
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]")).click();
    webDriver.findElement(By.name("637")).click();
    webDriver.findElement(By.xpath("(//input[@id='truth'])[3]")).click();
    webDriver.findElement(By.name("eight_a_pos_pos_b")).click();
    webDriver.findElement(By.id("section_submit_button")).click();
    assertEquals("", webDriver.getTitle());
    for (int second = 0;; second++) {
      if (second >= 60)
        fail("timeout");
      try {
        if ("Review".equals(webDriver.findElement(By.cssSelector("h2")).getText()))
          break;
      } catch (Exception e) {
      }
      Thread.sleep(1000);
    }

    webDriver.findElement(By.xpath("//div[4]/div/div[2]/p/a")).click();
    assertEquals("", webDriver.getTitle());
    webDriver.findElement(By.id("section_submit_button")).click();
    assertEquals("", webDriver.getTitle());
    webDriver.findElement(By.id("section_submit_button")).click();
    assertEquals("", webDriver.getTitle());
    try {
      assertEquals(
          "Please list current and past Federal and non-Federal awarded contracts within the last 12 months.",
          webDriver.findElement(By.xpath("//div[4]/fieldset/h4")).getText());
    } catch (Error e) {
      logger.info(e.getMessage());
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();

  }
}
