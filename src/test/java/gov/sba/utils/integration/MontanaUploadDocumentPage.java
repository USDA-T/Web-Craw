package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class MontanaUploadDocumentPage {
    private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());
    WebDriver webDriver;

    public MontanaUploadDocumentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void MontanaUploadDocument() throws Exception {
        logger.info("Uploading a new document");
        String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    }
}

