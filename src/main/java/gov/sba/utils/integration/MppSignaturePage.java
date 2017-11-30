package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.TestCase;

public class MppSignaturePage extends TestCase {
	private static final Logger logger = LogManager.getLogger(MppSignaturePage.class.getName());
	WebDriver webDriver;

	public MppSignaturePage(WebDriver webDriver) {
		this.webDriver = webDriver;

	}

	public void MppSignature() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		logger.info("Character section question begins");
		String Actual_Text = null;
		String Expected_Text = null;
		//Verify first paragraph.
		Actual_Text = webDriver.findElement(By.xpath("//label")).getText();
		Expected_Text = "All required documents verifying eligibility for the All Small Mentor-Protégé Program (All Small MPP) have been uploaded to certify.SBA.gov. I understand if any changes are made after I submit this application, I must notify the All Small Mentor-Protégé Program Office and submit additional documentation if needed.";
		assertEquals(Actual_Text, Expected_Text);
		//Verify Second paragraph.
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
		// Verify sixth paragraph
		logger.info("  Verify sixth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[6]")).getText();
		Expected_Text = "Warning: By clicking the Submit button, you are certifying that you are representing on your own behalf that the information provided in this application, and any document or supplemental information submitted, is true and correct as of the date set forth opposite your signature. Any intentional or negligent misrepresentation of the information contained in this certification may result in criminal, civil or administrative sanctions including, but not limited to: 1) fines of up to $500,000, and imprisonment of up to 10 years, or both, as set forth in 15 U.S.C. § 645 and 18 U.S.C. § 1001, as well as any other applicable criminal laws; 2) treble damages and civil penalties under the False Claims Act; 3) double damages and civil penalties under the Program Fraud Civil Remedies Act; 4) suspension and/or debarment from all Federal procurement and non-procurement transactions; and 5) program termination.";
		assertEquals(Actual_Text, Expected_Text);
		// Step 9 - Click the Continue button
		logger.info("Step 9 - Click the Continue button");
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.id("accept-button")));
		webDriver.findElement(By.id("accept-button")).click();
		// Step 10 - Accept the error message
		logger.info(webDriver.switchTo().alert().getText());
		Actual_Text = webDriver.switchTo().alert().getText();
		Expected_Text = "In order to submit your application, you must accept all of the conditions of authorization.";
		assertEquals(Actual_Text, Expected_Text);
		wait.until(ExpectedConditions.alertIsPresent());
		webDriver.switchTo().alert().accept();
		// Step 11 - Accept the statements and click Continue
		logger.info("Step 11 - Click to accept the statements");
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label")));
		webDriver.findElement(By.xpath("//label")).click();
		webDriver.findElement(By.xpath("//label[2]")).click();
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[3]")));
		webDriver.findElement(By.xpath("//label[3]")).click();
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[4]")));
		webDriver.findElement(By.xpath("//label[4]")).click();
		webDriver.findElement(By.xpath("//label[5]")).click();
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[6]")));
		webDriver.findElement(By.xpath("//label[6]")).click();
		webDriver.findElement(By.id("accept-button")).click();
	}
}
