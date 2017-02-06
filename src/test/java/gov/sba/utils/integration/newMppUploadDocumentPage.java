package gov.sba.utils.integration;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class newMppUploadDocumentPage {
    private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());
    WebDriver webDriver;

    public newMppUploadDocumentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void deepaUploadMppDocument(String upload_Path) throws Exception {
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
        Thread.sleep(700);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(700);

        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(700);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(700);
        logger.info("Uploading a new document - Alt tab done");

        robot.keyPress(KeyEvent.VK_F4);
        Thread.sleep(700);
        robot.keyPress(KeyEvent.VK_ESCAPE);
        Thread.sleep(700);
        logger.info("Uploading a new document - Clicked on F4");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(700);
        logger.info("Uploading a new document - Clicked on Tab");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(700);
        logger.info("Uploading a new document - Clicked on Tab2");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(700);
        logger.info("Uploading a new document - Clicked on Tab3");
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        Thread.sleep(700);

        logger.info("Uploading a new document - Clicked on Tab4");
        robot.keyPress(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(700);
        logger.info("Uploading a new document - Clicked on Paste");
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        logger.info("Uploading a new document - Clicked on All Enters");

        for (int i = 0; i < 11; i++) {
            Thread.sleep(700);
            try {
                webDriver.findElement(By.id("attach")).click();
                i = 100;
            } catch (Exception e) {
                logger.info("trying to click again - attach");
            }

        }

    }

}
