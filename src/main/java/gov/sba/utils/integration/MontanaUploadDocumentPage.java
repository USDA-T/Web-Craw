package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MontanaUploadDocumentPage {
  private static final Logger logger = LogManager.getLogger(DeleteDraftCertPage.class.getName());
  WebDriver webDriver;

  public MontanaUploadDocumentPage(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void MontanaUploadDocument(String upload_Path) throws Exception {
    try{
    Thread.sleep(2000);
    logger.info("Uploading a new document");
    WebDriverWait wait = new WebDriverWait(webDriver, 40);
    webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/button")));
    webDriver.findElement(By.xpath("//div[2]/div/button")).click();
    //wait.until(ExpectedConditions.elementToBeClickable(By.name("selector")));
    webDriver.findElement(By.name("selector")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='document_library_associate']")));
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//button[@id='document_library_associate']")).click();
    logger.info("document uploaded successully");
    Thread.sleep(2000);
    } catch (Exception e) {
    ScreenShotPage screenShot = new ScreenShotPage(webDriver);
    screenShot.ScreenShot();
    logger.info(e.getMessage());
    logger.info("document uploaded failed");
    Assert.fail();
    }

   }}
