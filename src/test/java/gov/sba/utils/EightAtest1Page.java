package gov.sba.utils;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EightAtest1Page {
    private static final Logger logger = LogManager.getLogger(EightAtest1Page.class.getName());
    WebDriver webDriver;

    public EightAtest1Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void EightAtest1() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        // Locate the accept button at the bottom of the 8(a) agreement and
        // click on it to continue.
        webDriver.findElement(By.name("commit")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "8(a) Document Upload";
        assertEquals(Actual_Text, Expected_Text);
        // Locate the 8(a) question and upload a document and continue.
        String actual_Text1 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text1 = "Please upload the completed, signed forms you downloaded from BDMIS as well as the supporting documents as described in the application checklist.";
        assertEquals(actual_Text1, expected_Text1);
        // Detail Section.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_eight_a_documents']/fieldset/p[2]")).getText();
        Expected_Text = "It may take a few moments for your document to appear, the system performs virus scans on all documents. If you accidentally uploaded an incorrect document, select the Remove action link in the Edit column.";
        assertEquals(Actual_Text, Expected_Text);
        // Upload a document.
        MontanaUploadDocumentPage montanaUploadDocument0 = new MontanaUploadDocumentPage(webDriver);
        montanaUploadDocument0.MontanaUploadDocument();
        Thread.sleep(4000);
        webDriver.findElement(By.name("commit")).click();
        logger.info("  8(a) question has been answered");
        // Review page.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Review";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
        Expected_Text = "Please review below answers and Submit.";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Please upload the completed, signed forms you downloaded from BDMIS as well as the supporting documents as described in the application checklist.";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='eight_a_documents']/div/div/div/table/tbody/tr/td"))
                .getText();
        Expected_Text = "MainTestUploadDoc.pdf";
        assertEquals(Actual_Text, Expected_Text);
        WebElement EightADoc = webDriver
                .findElement(By.xpath("//div[@id='eight_a_documents']/div/div/div/table/tbody/tr/td"));
        HighLight.highLightElement(webDriver, EightADoc);
        WebElement EightADocType = webDriver
                .findElement(By.xpath("//div[@id='eight_a_documents']/div/div/div/table/tbody/tr/td[2]"));
        HighLight.highLightElement(webDriver, EightADocType);
        WebElement EightADocComment = webDriver
                .findElement(By.xpath("//div[@id='eight_a_documents']/div/div/div/table/tbody/tr/td[3]"));
        HighLight.highLightElement(webDriver, EightADocComment);
        Thread.sleep(4000);
        webDriver.findElement(By.name("commit")).click();
        logger.info(webDriver.switchTo().alert().getText());
        webDriver.switchTo().alert().accept();
        // Step - Verify the Signature page for MPP
        logger.info("Step  - Verify the Signature page for MPP");
        // Verify you are on the Signature page
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Signature";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("label")).getText();
        Expected_Text = "Under 18 U.S.C. § 1001 and 15 U.S.C. § 645, any person who makes any false statement in order to influence the certification or continuing eligibility process in any way or to obtain a contract awarded under the preference programs established pursuant to section 8(a), 8(d), 9, or 15 of the Small Business Act, or any other provision of Federal Law that references section 8(a) or 8(d) for a definition of program eligibility, shall be: (1) subject to fines and imprisonment of up to 5 years, or both, as stated in 18 U.S.C. § 1001; and subject to fines of up to $500,000 and imprisonment of up to 10 years, or both, as stated in 15 U.S.C. § 645; (2) subject to suspension or termination as a participant in the 8(a) Program; (3) subject to civil and administrative remedies, including suspension and debarment; and (4) ineligible for participation in programs conducted under the authority of the Small Business Act. I hereby certify that any information provided via my Username/Password pair has been reviewed by me personally, and is true and accurate.";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("i")).getText();
        Expected_Text = "PLEASE NOTE: According to the Paperwork Reduction Act, you are not required to respond to this information collection unless it displays a valid OMB approval number. The estimated burden for completing this form, including reading the instructions and compiling the information, is 2 hours. If you have questions or comments concerning this estimate or other aspects of this information collection, please contact the US Small Business Administration, Chief, Administrative Information Branch, Washington, D.C. 20416 and/or SBA Desk Officer, Office of Management and Budget, New Executive Office Building, Room 10202, Washington, D.C. 20503.";
        assertEquals(Actual_Text, Expected_Text);
        logger.info("Step 11 - Click to accept the statements");
        webDriver.findElement(By.id("legal_0")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("accept-button")).click();
        // Verify that submission is successful.
        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
        Expected_Text = "Your application has been submitted";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
        Expected_Text = "Pending";
        assertEquals(Actual_Text, Expected_Text);
        WebElement EightAPending = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
        HighLight.highLightElement(webDriver, EightAPending);

    }

}
