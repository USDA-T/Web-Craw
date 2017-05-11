//TS created by Deepa Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;

public class fillApplCreatePages {
    private static final Logger logger = LogManager.getLogger(fillApplCreatePages.class.getName());

    public static void pageCaseOverviewFillup(
        WebDriver webDriver,
        String review_Type,
        String curr_Reviewer,
        String owner,
        String supervisor) throws Exception {

        try {
            webDriver.findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(text(),'ase overview')]"))
                    .click();

            if (review_Type.length() > 0) {
                Select dropdown1 = new Select(webDriver.findElement(By.xpath("//select[@id='review_type']")));
                dropdown1.selectByVisibleText(review_Type);
            }

            if (curr_Reviewer.length() > 0) {
                Select dropdown1 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_reviewer_id']")));

                dropdown1.selectByVisibleText(curr_Reviewer);
            }

            if (owner.length() > 0) {
                Select dropdown1 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
                dropdown1.selectByVisibleText(owner);
            }

            if (supervisor.length() > 0) {
                Select dropdown1 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
                dropdown1.selectByVisibleText(supervisor);
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }

    }

    public static void answer_Finance_Questions(
        WebDriver webDriver,
        String answer_01_Some_Description,String answer_02_Some_Description, String answer_03_Some_Description,
        String answer_04_Some_Description, String answer_05_Some_Description) throws Exception {
        try {
            if (answer_01_Some_Description.length() > 0) {
                if (answer_01_Some_Description == "Yes") {
                    click_Element(webDriver, "Answer_Element_Yes");
                }
                else {
                    click_Element(webDriver, "Answer_Element_No");
                }
            }

            if (answer_02_Some_Description.length() > 0) {
                if (answer_02_Some_Description == "Yes") {
                    click_Element(webDriver, "Answer_Element_02_Yes");
                }
                else {
                    click_Element(webDriver, "Answer_Element_02_No");
                }
            }

            if (answer_03_Some_Description.length() > 0) {
                if (answer_03_Some_Description == "Yes") {
                    click_Element(webDriver, "Answer_Element_03_Yes");
                }
                else {
                    click_Element(webDriver, "Answer_Element_03_No");
                }
            }

        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }

    }

    public static void pageQuestionReviewFillup(WebDriver webDriver) throws Exception {

        try {
            webDriver
                    .findElement(By.xpath("//div[contains(@class,'review_outer')]"
                            + "/div[contains(@class,'review_nav')]" + "/div/aside"
                            + "/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]"))
                    .click();

            webDriver.findElement(By.xpath("//input[@value='Save and continue']")).click();
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void pageSignatureReviewFillup(WebDriver webDriver) throws Exception {

        try {
            webDriver
                    .findElement(By
                            .xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Signature review')]"))
                    .click();
            webDriver.findElement(By.xpath("//input[@value='Save and commit']")).click();
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void page8aFillUp(WebDriver webDriver, String answer01, String path) throws Exception {
        try {
            if (answer01.toUpperCase().contains("YES")) {
                List<WebElement> current_Row = webDriver.findElements(By
                        .xpath("//input[contains(@id,'answers_') and contains(@id,'value') and contains(@id,'yes') ]"));
                Iterator<WebElement> all_Rows = current_Row.iterator();
                while (all_Rows.hasNext()) {
                    all_Rows.next().click();
                }

                newMppUploadDocumentPageDeepa deepaUploadMppDocument1 = new newMppUploadDocumentPageDeepa(webDriver);
                deepaUploadMppDocument1.deepaUploadMppDocument(path);
                logger.info("Doc has been uploaded.");
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                Thread.sleep(2000);
                try {
                    logger.info("Check Alert");
                    webDriver.switchTo().alert().accept();
                } catch (Exception excp) {
                    logger.info("No Alert");
                }
                logger.info("Doc has been uploaded and accepted");
            } else {
                try {
                    webDriver.findElement(By.id("answers_188_value_yes")).click();
                } catch (Exception e1) {
                    webDriver.findElement(By.id("answers_247_value_yes")).click();
                }
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            }
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void genericUploadDoc(WebDriver webDriver, String answer01, String path) throws Exception {
        try {
            if (answer01.toUpperCase().contains("YES")) {
                List<WebElement> current_Row = webDriver.findElements(By
                    .xpath("//input[contains(@id,'answers_') and contains(@id,'value') and contains(@id,'yes') ]"));
                Iterator<WebElement> all_Rows = current_Row.iterator();
                while (all_Rows.hasNext()) {
                    all_Rows.next().click();
                }

                newMppUploadDocumentPageDeepa deepaUploadMppDocument1 = new newMppUploadDocumentPageDeepa(webDriver);
                deepaUploadMppDocument1.deepaUploadMppDocument(path);
                logger.info("Doc has been uploaded.");
                Thread.sleep(2000);
                try {
                    logger.info("Check Alert");
                    webDriver.switchTo().alert().accept();
                } catch (Exception excp) {
                    logger.info("No Alert");
                }
                logger.info("Doc has been uploaded and accepted");

            }
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void page8aFillUpDunsNo(WebDriver webDriver, String answer01, String path, String duns_No_Given)
            throws Exception {
        try {
            if (answer01.toUpperCase().contains("YES")) {
                List<WebElement> current_Row = webDriver.findElements(By
                        .xpath("//input[contains(@id,'answers_') and contains(@id,'value') and contains(@id,'yes') ]"));
                Iterator<WebElement> all_Rows = current_Row.iterator();
                while (all_Rows.hasNext()) {
                    all_Rows.next().click();
                }

                newMppUploadDocumentPageDeepa deepaUploadMppDocument1 = new newMppUploadDocumentPageDeepa(webDriver);
                deepaUploadMppDocument1.deepaUploadMppDocument(path);
                logger.info("Doc has been uploaded.");
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                List<WebElement> duns_No = webDriver
                        .findElements(By.xpath("//input[@type='number' and contains(@id,'duns-value')]"));
                if (duns_No.size() > 0) {
                    duns_No.get(0).sendKeys(duns_No_Given);
                    webDriver.findElement(By.xpath("//a[contains(@id,'search-duns')]")).click();
                    duns_No = webDriver.findElements(By.xpath("//a[contains(@id,'search-duns')]"));
                    if (duns_No.size() > 0) {
                        logger.info("Clicked now.");
                        duns_No.get(0).click();
                    }
                    Thread.sleep(2000);
                    try {
                        webDriver.switchTo().alert().accept();
                    } catch (Exception ex) {
                        logger.info("No alerts available");
                    }
        }

                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                Thread.sleep(2000);
                webDriver.findElement(By.className("review")).click();
                logger.info("Doc has been uploaded and accepted");

                try {
                    webDriver.switchTo().alert().accept();
                } catch (Exception ex) {
                    logger.info("No alerts available");
                }

            } else {
                try {
                    webDriver.findElement(By.id("answers_117_value_no")).click();
                } catch (Exception e1) {
                    webDriver.findElement(By.id("answers_228_value_yes")).click();
                }
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            }
        } catch (Exception e) {
            logger.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"page8aFillUpDunsNo"});
            throw e;
        }
    }

    public static void finalSignatureSubmit(WebDriver webDriver) throws Exception {
        try {
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//*[@id='legal_0']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//*[@id='legal_1']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//*[@id='legal_2']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//*[@id='legal_3']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//*[@id='legal_4']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//*[@id='legal_5']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//input[@type='submit']");
        } catch (Exception e) {
            logger.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"finalSignatureSubmit"});
            throw e;
        }
    }

    public static void finalSignatureSubmit8A(WebDriver webDriver) throws Exception {
        try {
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//input[@id='legal_0']");
            CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//input[@type='submit']");
        } catch (Exception e) {
            logger.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"finalSignatureSubmit8A"});
            throw e;
        }
    }

}