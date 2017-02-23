package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

public class DeleteDraftCertPage extends TestCase {

    private static final Logger logger = LogManager.getLogger(gov.sba.utils.integration.DeleteDraftCertPage.class.getName());
    WebDriver webDriver;

    public DeleteDraftCertPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void DeleteDraftCert() throws Exception {
        logger.debug("Draft cert will be deleted");
        // Verify if there is an existing certification on the dashboard and
        // delete to start a new certification.
        try {
            assertTrue(webDriver.getPageSource().contains("Draft"));
            webDriver.findElement(By.linkText("Delete")).click();
            webDriver.switchTo().alert().accept();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webDriver.navigate().refresh();
        } catch (Error e) {
        }
    }

}