package gov.sba.utils;

	import static org.junit.Assert.assertEquals;
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class EdwobEightAMppTest1Page {
		private static final Logger logger = LogManager.getLogger(EdwobEightAMppTest1Page.class.getName());
		WebDriver webDriver;

		public EdwobEightAMppTest1Page(WebDriver webDriver) {
			this.webDriver = webDriver;
		}
		
		public void EdwobEightAMppTest1() throws Exception {
				String Actual_Text = null;
				String Expected_Text = null;
				// Locate the accept button at the bottom of the EDWOSB agreement and
				// click on it to continue.
				//webDriver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
				// Locate the 8(a) question and select No and continue.
				String actual_Text1 = webDriver.findElement(By.cssSelector("h4")).getText();
				String expected_Text1 = "Are you an existing 8(a) firm in your final 6 months of the program, wishing to transfer your Mentor-Protégé relationship to the All Small Mentor-Protégé Program?";
				assertEquals(actual_Text1, expected_Text1);
				// Verify the More detail meaning for the 8(A) question.
				String actual_Text2 = webDriver.findElement(By.xpath("//div[@id='answers_8a_certified']/fieldset/p[2]")).getText();
				String expected_Text2 = "If yes, please upload your dated 8(a) Mentor-Protégé Approval Letter and your current 8(a) Mentor-Protégé Agreement. You are eligible for the All Small Mentor-Protégé Program and you will skip forward to the “Review” section of this application.";
				assertEquals(actual_Text2, expected_Text2);
				webDriver.findElement(By.id("answers_117_value_yes")).click();
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


