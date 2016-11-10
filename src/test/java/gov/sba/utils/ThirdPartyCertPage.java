package gov.sba.utils;


import static org.junit.Assert.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ThirdPartyCertPage {
	private static final Logger logger = LogManager.getLogger(ThirdPartyCertPage.class.getName());
	WebDriver webDriver;

	public ThirdPartyCertPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public void ThirdPartyCert() throws Exception {
			String Actual_Text = null;
			String Expected_Text = null;
			// Locate the accept button at the bottom of the EDWOSB agreement and
			// click on it to continue.
			webDriver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
			// Locate the 8(a) question and select No and continue.
			String actual_Text1 = webDriver.findElement(By.cssSelector("h4")).getText();
			String expected_Text1 = "Is the qualifying individual(s) currently certified by the U.S. Small Business Administration as an 8(a) Business Development (BD) Program Participant and does this woman own at least 51% of the business?";
			assertEquals(actual_Text1, expected_Text1);
			webDriver.findElement(By.id("answers_65_value_no")).click();
			webDriver.findElement(By.name("commit")).click();
			webDriver.findElement(By.id("answers_66_value_yes")).click();
			// Upload a document.
			MontanaUploadDocumentPage montanaUploadDocument0 = new MontanaUploadDocumentPage(webDriver);
			montanaUploadDocument0.MontanaUploadDocument();
			Thread.sleep(4000);
			webDriver.findElement(By.name("commit")).click();
			//Change Eligibility.
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Changes in Eligibility";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.id("answers_67_value_no")).click();
			webDriver.findElement(By.name("commit")).click();
			logger.info("  ThirdParty question has been answered");
			// Review page.
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Review";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
			Expected_Text = "Please review below answers and Submit.";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.name("commit")).click();
			logger.info(webDriver.switchTo().alert().getText());
			webDriver.switchTo().alert().accept();
			// Step - Verify the Signature page for MPP
			logger.info("Step  - Verify the Signature page for MPP");
			// Verify you are on the Signature page
			logger.info("  Verify you are on the Signature page");
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Signature";
			assertEquals(Actual_Text, Expected_Text);
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
