package gov.sba.utils.integration;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadDocumentPage {
    private static final Logger logger = LogManager.getLogger(UploadDocumentPage.class.getName());
    WebDriver webDriver;

    public UploadDocumentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void MontanaUploadDocument() throws Exception {
        logger.debug("Uploading a new document");
        webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
        webDriver.findElement(By.id("doc-upload-button")).click();
        webDriver.findElement(By.linkText("Choose a .pdf file")).click();

        // Note: might need to be adjust to MainTestUploadDoc.pdf?
        String pdfFixture = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";

        // StringSelection ss = new StringSelection("C:\\Users\\Derec
        // Nguni\\Documents\\MainTestUploadDoc.pdf");
        StringSelection ss = new StringSelection(pdfFixture);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(6000);
        webDriver.findElement(By.id("comment")).sendKeys("Testing Documents");
        webDriver.findElement(By.cssSelector("#attach")).click();
    }

}
