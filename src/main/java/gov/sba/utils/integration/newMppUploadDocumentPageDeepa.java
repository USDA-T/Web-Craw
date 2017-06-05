// TS created by Deepa Patri
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static gov.sba.automation.CommonApplicationMethods.click_Element;

public class newMppUploadDocumentPageDeepa {
  private static final Logger logger =
      LogManager.getLogger(newMppUploadDocumentPageDeepa.class.getName());
  WebDriver webDriver;

  public newMppUploadDocumentPageDeepa(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void deepaUploadMppDocument(String upload_Path) throws Exception {
    logger.info("Uploading a new document");
    click_Element(webDriver, "Upload_Add_Req_Button");
    Thread.sleep(1500); // Need to
    click_Element(webDriver, "Upload_doc_Upload_Button");
    Thread.sleep(1500);
    click_Element(webDriver, "Upload_doc_Choose_File");
    Thread.sleep(1500);

    StringSelection ss = new StringSelection(upload_Path);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    Robot robot = new Robot();
    logger.info("Uploading a new document - Clicked on Upload");

    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(300);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_TAB);
    Thread.sleep(300);
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(300);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_TAB);
    Thread.sleep(300);
    logger.info("Uploading a new document - Alt tab done");

    robot.keyPress(KeyEvent.VK_F4);
    Thread.sleep(300);
    robot.keyPress(KeyEvent.VK_ESCAPE);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on F4");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Tab");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Tab2");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Tab3");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);

    logger.info("Uploading a new document - Clicked on Tab4");
    robot.keyPress(KeyEvent.VK_CONTROL);

    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Paste");


    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    logger.info("Uploading a new document - Clicked on All Enters");
    Thread.sleep(3500);
    click_Element(webDriver, "Upload_Attach_Button");
    Thread.sleep(1500);
    logger.info("Doc has been uploaded.");

  }

  public void deepaUploadScndAttach(String upload_Path) throws Exception {

    logger.info("Uploading a new document");
    for (int i = 0; i < 21; i++) {

      try {
        webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
        i = 100;
        Thread.sleep(300);
      } catch (Exception e) {
        logger.info("trying to click again - add");
      }
    }

    for (int i = 0; i < 21; i++) {
      try {
        webDriver.findElement(By.id("doc-upload-button")).click();
        i = 100;
        Thread.sleep(300);
      } catch (Exception e) {
        logger.info("trying to click again - upld");
      }
    }

    for (int i = 0; i < 21; i++) {
      try {
        webDriver.findElement(By.linkText("Choose a .pdf file")).click();
        i = 100;
        Thread.sleep(300);
      } catch (Exception e) {
        logger.info("trying to click again - choose");
      }
    }

    Thread.sleep(700);

    StringSelection ss = new StringSelection(upload_Path);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    Robot robot = new Robot();
    logger.info("Uploading a new document - Clicked on Upload");

    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(300);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_TAB);
    Thread.sleep(300);
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyPress(KeyEvent.VK_TAB);
    Thread.sleep(300);
    robot.keyRelease(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_TAB);
    Thread.sleep(300);
    logger.info("Uploading a new document - Alt tab done");

    robot.keyPress(KeyEvent.VK_F4);
    Thread.sleep(300);
    robot.keyPress(KeyEvent.VK_ESCAPE);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on F4");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Tab");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Tab2");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Tab3");
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_TAB);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    Thread.sleep(300);

    logger.info("Uploading a new document - Clicked on Tab4");
    robot.keyPress(KeyEvent.VK_CONTROL);

    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    Thread.sleep(300);
    logger.info("Uploading a new document - Clicked on Paste");
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    logger.info("Uploading a new document - Clicked on All Enters");

    for (int i = 0; i < 11; i++) {
      Thread.sleep(400);
      try {
        webDriver.findElement(By.id("attach")).click();
        i = 100;
      } catch (Exception e) {
        logger.info("trying to click again - attach");
      }

    }

  }

}
