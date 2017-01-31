package gov.sba.utils;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EdwobEightAMppTestPage {
    private static final Logger logger = LogManager.getLogger(EDWOSBEightATestPage.class.getName());
    WebDriver webDriver;

    public EdwobEightAMppTestPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void EdwobEightAMppTest() throws Exception {
        String Actual_Text = null;
        String Expected_Text = null;
        // Locate the accept button at the bottom of the EDWOSB agreement and
        // click on it to continue.
        // webDriver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
        // Locate the 8(a) question and select No and continue.
        String actual_Text1 = webDriver.findElement(By.cssSelector("h4")).getText();
        String expected_Text1 = "Are you an existing 8(a) firm in your final 6 months of the program, wishing to transfer your Mentor-Protégé relationship to the All Small Mentor-Protégé Program?";
        assertEquals(actual_Text1, expected_Text1);
        // Verify the More detail meaning for the 8(A) question.
        String actual_Text2 = webDriver.findElement(By.xpath("//div[@id='answers_8a_certified']/fieldset/p[2]"))
                .getText();
        String expected_Text2 = "If yes, please upload your dated 8(a) Mentor-Protégé Approval Letter and your current 8(a) Mentor-Protégé Agreement. You are eligible for the All Small Mentor-Protégé Program and you will skip forward to the “Review” section of this application.";
        assertEquals(actual_Text2, expected_Text2);
        webDriver.findElement(By.id("answers_117_value_yes")).click();
        // Upload a document.
        MontanaUploadDocumentPage montanaUploadDocument0 = new MontanaUploadDocumentPage(webDriver);
        montanaUploadDocument0.MontanaUploadDocument();
        Thread.sleep(2000);
        webDriver.findElement(By.className("usa-button")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("duns-value-167")).sendKeys("172115728");
        webDriver.findElement(By.linkText("Confirm DUNS")).click();
        webDriver.findElement(By.linkText("Confirm DUNS")).click();
        logger.info(webDriver.switchTo().alert().getText());
        Actual_Text = webDriver.switchTo().alert().getText();
        Expected_Text = "Please confirm that this is the correct business:\nEntity 81 Legal Business Name";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.switchTo().alert().accept();
        Thread.sleep(3000);
        webDriver.findElement(By.className("usa-button")).click();
        logger.info("  8(a) question has been answered");
        // Review page.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Review";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
        Expected_Text = "Please review below answers and Submit.";
        assertEquals(Actual_Text, Expected_Text);
        Thread.sleep(3000);
        webDriver.findElement(By.className("usa-button")).click();
        logger.info(webDriver.switchTo().alert().getText());
        webDriver.switchTo().alert().accept();
        // Step - Verify the Signature page for MPP
        logger.info("Step  - Verify the Signature page for MPP");
        // Verify you are on the Signature page
        logger.info("  Verify you are on the Signature page");
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Signature";
        assertEquals(Actual_Text, Expected_Text);
        // Verify title
        logger.info("  Verify title");
        Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
        Expected_Text = "All Small Mentor Protégé Program Application Summary";
        assertEquals(Actual_Text, Expected_Text);
        // Verify Business name
        logger.info("  Verify Business name");
        Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
        Expected_Text = "Entity 81 Legal Business Name";
        assertEquals(Actual_Text, Expected_Text);
        // Verify DUNS label
        logger.info("  Verify DUNS label");
        Actual_Text = webDriver.findElement(By.xpath("//b")).getText();
        Expected_Text = "DUNS:";
        // assertEquals(actual_error9, expected_error9);
        // Verify DUNS number
        logger.info("  Verify DUNS number");
        Actual_Text = webDriver.findElement(By.cssSelector("span")).getText();
        Expected_Text = "172115728";
        assertEquals(Actual_Text, Expected_Text);
        // Verify first paragraph
        logger.info("  Verify first paragraph");
        Actual_Text = webDriver.findElement(By.cssSelector("label")).getText();
        Expected_Text = "All required documents verifying eligibility for the All Small Mentor-Protégé Program (All Small MPP) have been uploaded to certify.SBA.gov. I understand if any changes are made after I submit this application, I must notify the All Small Mentor-Protégé Program Office and submit additional documentation if needed.";
        assertEquals(Actual_Text, Expected_Text);
        // Verify Second paragraph
        logger.info("  Verify second paragraph");
        Actual_Text = webDriver.findElement(By.xpath("//label[2]")).getText();
        Expected_Text = "All the statements and information provided in this form and any documents submitted are true, accurate and complete. If assistance was obtained in completing this form and the supporting documentation, I have personally reviewed the information and it is true and accurate. I understand that these statements are made for the purpose of determining eligibility for participation in the All Small MPP.";
        assertEquals(Actual_Text, Expected_Text);
        // Verify third paragraph
        logger.info("  Verify third paragraph");
        Actual_Text = webDriver.findElement(By.xpath("//label[3]")).getText();
        Expected_Text = "I understand that the information submitted may be given to Federal, State and local agencies for determining violations of law and other purposes.";
        assertEquals(Actual_Text, Expected_Text);
        // Verify fourth paragraph
        logger.info("  Verify fourth paragraph");
        Actual_Text = webDriver.findElement(By.xpath("//label[4]")).getText();
        Expected_Text = "I understand that I may not misrepresent my status as a small business to: 1) obtain a contract under the Small Business Act; or 2) obtain any benefit under a provision of Federal law that references the All Small MPP for a definition of program eligibility.";
        assertEquals(Actual_Text, Expected_Text);
        // Verify fifth paragraph
        logger.info("  Verify fifth paragraph");
        Actual_Text = webDriver.findElement(By.xpath("//label[5]")).getText();
        Expected_Text = "By submitting this certification I, QA User, am an officer or owner of Entity 81 Legal Business Name authorized to represent it and electronically sign this certification on its behalf.";
        assertEquals(Actual_Text, Expected_Text);
        // Verify sixth paragraph
        logger.info("  Verify sixth paragraph");
        Actual_Text = webDriver.findElement(By.xpath("//label[6]")).getText();
        Expected_Text = "Warning: By clicking the Submit button, you are certifying that you are representing on your own behalf that the information provided in this application, and any document or supplemental information submitted, is true and correct as of the date set forth opposite your signature. Any intentional or negligent misrepresentation of the information contained in this certification may result in criminal, civil or administrative sanctions including, but not limited to: 1) fines of up to $500,000, and imprisonment of up to 10 years, or both, as set forth in 15 U.S.C. § 645 and 18 U.S.C. § 1001, as well as any other applicable criminal laws; 2) treble damages and civil penalties under the False Claims Act; 3) double damages and civil penalties under the Program Fraud Civil Remedies Act; 4) suspension and/or debarment from all Federal procurement and non-procurement transactions; and 5) program termination.";
        assertEquals(Actual_Text, Expected_Text);
        // Step 9 - Click the Continue button
        logger.info("Step 9 - Click the Continue button");
        webDriver.findElement(By.id("accept-button")).click();
        Thread.sleep(3000);
        // Step 10 - Accept the error message
        logger.info("Step 10 - Accept the error message");
        webDriver.switchTo().alert().accept();
        // Step 11 - Accept the statements and click Continue
        logger.info("Step 11 - Click to accept the statements");
        webDriver.findElement(By.id("legal_0")).click();
        webDriver.findElement(By.id("legal_1")).click();
        webDriver.findElement(By.id("legal_2")).click();
        webDriver.findElement(By.id("legal_3")).click();
        webDriver.findElement(By.id("legal_4")).click();
        webDriver.findElement(By.id("legal_5")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("accept-button")).click();

    }

}
