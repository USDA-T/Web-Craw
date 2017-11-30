package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import gov.sba.automation.CoreUtils;
import junit.framework.TestCase;

public class SignAndSubmitWOSBApplicationPage extends TestCase {
	WebDriver webDriver;
	private static final Logger logger = LogManager.getLogger(SignAndSubmitWOSBApplicationPage.class.getName());
	int get_The_Row_From_Login_Data;

	public SignAndSubmitWOSBApplicationPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void SignAndSubmitWOSBApplication() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		String Actual_Text = null;
		String Expected_Text = null;

		// Sign and submit.
		try {
			WebElement rateElement = webDriver.findElement(By.xpath("//label"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement);
			jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[2]")));
			webDriver.findElement(By.xpath("//label[2]")).click();
			jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[3]")));
			webDriver.findElement(By.xpath("//label[3]")).click();
			jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[4]")));
			webDriver.findElement(By.xpath("//label[4]")).click();
			jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[5]")));
			webDriver.findElement(By.xpath("//label[5]")).click();
			jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//label[6]")));
			webDriver.findElement(By.xpath("//label[6]")).click();
			// Click on the save and continue button.
			CoreUtils.clickContinue(webDriver);
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "Your application has been submitted";
			assertEquals(Actual_Text, Expected_Text);
			WebElement rateElement2 = webDriver.findElement(By.id("profileid"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement2);
			WebElement rateElement11 = webDriver.findElement(By.linkText("Logout"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement11);
		} catch (Error e) {
			logger.info(e);
		}

	}
}
