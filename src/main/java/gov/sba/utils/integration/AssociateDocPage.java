package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AssociateDocPage {
    private static final Logger logger = LogManager.getLogger(AssociateDocPage.class.getName());
    WebDriver webDriver;

    public AssociateDocPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void AssociateDoc() throws Exception {
        logger.debug("Associate doc from doc libriary");
        webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
        Thread.sleep(2000);
        Actions act = new Actions(webDriver);
        act.doubleClick(webDriver.findElement(By.cssSelector("#doc-lib-button"))).build().perform();
        Thread.sleep(2000);
        webDriver.findElement(By.id("truth")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("document_library_associate")).click();
    }

}
