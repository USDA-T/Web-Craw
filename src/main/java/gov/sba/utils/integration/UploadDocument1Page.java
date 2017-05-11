package gov.sba.utils.integration;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import gov.sba.automation.FixtureUtils;

public class UploadDocument1Page {
  private static final Logger logger = LogManager.getLogger(UploadDocument1Page.class.getName());
  WebDriver webDriver;

  public UploadDocument1Page(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void MontanaUploadDocument1() throws Exception {
    logger.info("Uploading a new document");
    webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//button[@id='doc-upload-button']")).click();
    Thread.sleep(1000);
    webDriver.findElement(By.linkText("Choose a .pdf file")).click();
    // Note: might need to be adjust to MainTestUploadDoc.pdf?
    String pdfFixture = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
    // StringSelection ss = new StringSelection("C:\\Users\\Derec
    // Nguni\\Documents\\MainTestUploadDoc.pdf");
    StringSelection ss = new StringSelection(pdfFixture);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(1000);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    Thread.sleep(1000);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(2000);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(2000);
    // webDriver.findElement(By.id("comment")).sendKeys("2000 Documents");
    Actions act4 = new Actions(webDriver);
    act4.doubleClick(webDriver.findElement(By.xpath("//button[@id='attach']"))).build().perform();
  }

}
