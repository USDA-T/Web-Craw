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
        logger.debug("Uploading a new document");
        webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
        Thread.sleep(1000);

        webDriver.findElement(By.id("doc-upload-button")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Choose a .pdf file")).click();
        StringSelection ss = new StringSelection(upload_Path);
        Thread.sleep(3000);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_F4);
        robot.keyPress(KeyEvent.VK_ESCAPE);

        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);


        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);

        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);

        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        
        
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("#attach")).click();
    }

}