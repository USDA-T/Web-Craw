package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.TestCase;

public class SignAndSubmit8aApplicationPage extends TestCase {
	WebDriver webDriver;
	private static final Logger logger = LogManager.getLogger(SignAndSubmit8aApplicationPage.class.getName());
	int get_The_Row_From_Login_Data;

	public SignAndSubmit8aApplicationPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void SignAndSubmit8aApplication() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		String Actual_Text = null;
		String Expected_Text = null;

		// Sign and submit.
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Review and sign']")));
			WebElement rateElement = webDriver.findElement(By.xpath("//input[@value='Review and sign']"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement);
			jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.id("legal_0")));
			webDriver.findElement(By.id("legal_0")).click();
			WebElement rateElement1 = webDriver.findElement(By.id("accept-button"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement1);
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