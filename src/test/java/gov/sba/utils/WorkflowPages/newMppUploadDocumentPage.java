package gov.sba.utils.WorkflowPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.utils.TestSearchPage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class newMppUploadDocumentPage {
    private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());
    WebDriver webDriver;

    public newMppUploadDocumentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void deepaUploadMppDocument(String upload_Path) throws Exception {
        logger.info("Uploading a new document");
        webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
        Thread.sleep(2000);

        webDriver.findElement(By.id("doc-upload-button")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Choose a .pdf file")).click();
        StringSelection ss = new StringSelection(upload_Path);
        Thread.sleep(3000);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        logger.info("Uploading a new document - Clicked on Upload");
        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        Thread.sleep(1000);
        logger.info("Uploading a new document - Clicked on F4");

      robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
        logger.info("Uploading a new document - Clicked on Tab");

        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);

        logger.info("Uploading a new document - Clicked on Tab2");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
 
        logger.info("Uploading a new document - Clicked on Tab3");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(1000);
 
       logger.info("Uploading a new document - Clicked on Tab4");
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
 
       logger.info("Uploading a new document - Clicked on Paste");
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        logger.info("Uploading a new document - Clicked on All Enters");
        Thread.sleep(5000);

        webDriver.findElement(By.id("attach")).click();
    
    }

}
