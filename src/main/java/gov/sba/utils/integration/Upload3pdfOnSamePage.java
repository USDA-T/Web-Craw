package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Upload3pdfOnSamePage {
  private static final Logger logger = LogManager.getLogger(Upload3pdfOnSamePage.class.getName());
  WebDriver webDriver;

  public Upload3pdfOnSamePage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void Upload3pdfOnSame(String upload_Path) throws Exception {
    try{
      Thread.sleep(1000);
      logger.debug("Uploading a new document");
      WebDriverWait wait = new WebDriverWait(webDriver, 40);
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]")).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='doc-lib-button'])[3]")));
      webDriver.findElement(By.xpath("(//button[@id='doc-lib-button'])[3]")).click();
      webDriver.findElement(By.xpath("(//input[@id='truth'])[3]")).click();
      Thread.sleep(2000);;
      webDriver.findElement(By.xpath("(//button[@id='document_library_associate'])[3]")).click();
      Thread.sleep(2000);;
      } catch (Exception e) {
      ScreenShotPage screenShot = new ScreenShotPage(webDriver);
      screenShot.ScreenShot();
      logger.info(e.getMessage());
      logger.debug("document uploaded failed");
      Assert.fail();
      }

     }}