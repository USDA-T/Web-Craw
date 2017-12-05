package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.TestCase;

public class CodsSupervisorAssignCaseToCodsAnalystPage extends TestCase {
	private static final Logger logger = LogManager
			.getLogger(CodsSupervisorAssignCaseToCodsAnalystPage.class.getName());
	WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	public CodsSupervisorAssignCaseToCodsAnalystPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void CodsSupervisorAssignCaseToCodsAnalyst() throws Exception {
		String Actual_Text = null;
		String Expected_Text = null;
		logger.info("Test for 8a initial master Flow");
		// Login to dashboard.
		get_The_Row_From_Login_Data = 70;
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		// Verify application firm.

		// verify and click on assign link for the application you submitted.
		webDriver.findElement(By.linkText("Assign")).click();

		Actual_Text = webDriver.findElement(By.xpath("//article/p")).getText();
		Expected_Text = "Select a CODS Analyst to review this case. After the case is assigned, the review status will go screening in progress.";
		assertEquals(Actual_Text, Expected_Text);
		
		
		
		
		
		
		
		try {

		} catch (Exception e) {
			ScreenShotPage screenShot = new ScreenShotPage(webDriver);
			screenShot.ScreenShot();
			logger.info("Upload will not run on headless " + e.getMessage());
		}
		logger.info("8a initial application is successfully submitted");
	}

}
