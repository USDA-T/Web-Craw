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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContributorUploadPage3 {
  private static final Logger logger = LogManager.getLogger(ContributorUploadPage3.class.getName());
  WebDriver webDriver;

  public ContributorUploadPage3(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  WebDriverWait wait = new WebDriverWait(webDriver, 40);

  public void ContributorUpload(String upload_Path) throws Exception {
    Thread.sleep(2000);
    logger.debug("Uploading a new document");
    webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("(//button[@id='doc-upload-button'])[3]")).click();
    Thread.sleep(2000);
    Actions act = new Actions(webDriver);
    act.doubleClick(
        webDriver.findElement(By.xpath("(//a[contains(text(),'Choose a .pdf file')])[3]"))).build()
        .perform();
    StringSelection ss = new StringSelection(upload_Path);
    Thread.sleep(1000);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(2000);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    Thread.sleep(2000);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(2000);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(2000);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='attach'])[3]")));
    Actions act1 = new Actions(webDriver);
    act1.doubleClick(webDriver.findElement(By.xpath("(//button[@id='attach'])[3]"))).build()
        .perform();
    Thread.sleep(2000);
  }
}