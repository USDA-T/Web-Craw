package gov.sba.utils.WorkflowPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class fillApplCreatePages {
    private static final Logger logger = LogManager.getLogger(fillApplCreatePages.class.getName());

    public static void pageCaseOverviewFillup(WebDriver webDriver, String review_Type, String curr_Reviewer,
            String owner, String supervisor) throws Exception {

        try {
            // webDriver.findElement(By.xpath("//*[@id='question-review']//a[contains(text(),'Case
            // overview')]")).click();
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

    public static void pageQuestionReviewFillup(WebDriver webDriver) throws Exception {

        try {
            webDriver
            .findElement(By
                    .xpath("//div[contains(@class,'review_outer')]" +
                            "/div[contains(@class,'review_nav')]" +
                            "/div/aside" +
                            "/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]"))
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
                // Use
                // webDriver.findElement(By.id("answers_65_value_no")).click();
                // for no


            	List<WebElement> current_Row = webDriver.
                        findElements(By.xpath
                                ("//input[contains(@id,'answers_') and contains(@id,'value') and contains(@id,'yes') ]"));
                Iterator<WebElement> all_Rows = current_Row.iterator();
                while (all_Rows.hasNext()) {
                    all_Rows.next().click();
                }
                
                newMppUploadDocumentPage deepaUploadMppDocument1 = new newMppUploadDocumentPage(webDriver);
                deepaUploadMppDocument1.deepaUploadMppDocument(path);
                logger.info("Doc has been uploaded.");
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                Thread.sleep(2000);
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
                Thread.sleep(1000);
                webDriver.switchTo().alert().accept();
                logger.info("Doc has been uploaded and accepted");
            } else {
                try{
                    webDriver.findElement(By.id("answers_117_value_no")).click();
                }catch (Exception e1){
                    webDriver.findElement(By.id("answers_65_value_no")).click();
                }
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            }
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

    public static void finalSignatureSubmit(WebDriver webDriver) throws Exception {
        try {
            webDriver.findElement(By.xpath("//*[@id='legal_0']")).click();
            webDriver.findElement(By.xpath("//*[@id='legal_1']")).click();
            webDriver.findElement(By.xpath("//*[@id='legal_2']")).click();
            webDriver.findElement(By.xpath("//*[@id='legal_3']")).click();
            webDriver.findElement(By.xpath("//*[@id='legal_4']")).click();
            webDriver.findElement(By.xpath("//*[@id='legal_5']")).click();
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
        } catch (Exception e) {
            logger.info(e.toString());
            throw e;
        }
    }

}