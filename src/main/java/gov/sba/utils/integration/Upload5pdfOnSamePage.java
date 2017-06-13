package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Upload5pdfOnSamePage {
  private static final Logger logger = LogManager.getLogger(Upload5pdfOnSamePage.class.getName());
  WebDriver webDriver;

  public Upload5pdfOnSamePage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void Upload5pdfOnSame(String upload_Path) throws Exception {
    try{
      Thread.sleep(1000);
      logger.debug("Uploading a new document");
      WebDriverWait wait = new WebDriverWait(webDriver, 40);
      webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[5]")).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='doc-lib-button'])[5]")));
      webDriver.findElement(By.xpath("(//button[@id='doc-lib-button'])[5]")).click();
      webDriver.findElement(By.xpath("(//input[@id='truth'])[5]")).click();
      Thread.sleep(2000);;
      webDriver.findElement(By.xpath("(//button[@id='document_library_associate'])[5]")).click();
      Thread.sleep(2000);;
      } catch (Exception e) {
      ScreenShotPage screenShot = new ScreenShotPage(webDriver);
      screenShot.ScreenShot();
      logger.info(e.getMessage());
      logger.debug("document uploaded failed");
      Assert.fail();
      }

     }}