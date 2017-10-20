package gov.sba.utils.integration;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Upload3pdfOnSamePage {
	private static final Logger logger = LogManager.getLogger(Upload3pdfOnSamePage.class.getName());
	WebDriver webDriver;

	public Upload3pdfOnSamePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void Upload3pdfOnSame(String upload_Path) throws Exception {
		logger.debug("Uploading a new document");
		WebDriverWait wait = new WebDriverWait(webDriver, 40);
		if (webDriver.getCurrentUrl().contains("certify.qa")) {
			Thread.sleep(2000);
			WebElement rateElement2 = webDriver
					.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement2);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='doc-lib-button'])[3]")));
			WebElement rateElement1 = webDriver.findElement(By.xpath("(//button[@id='doc-lib-button'])[3]"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement1);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//td[@id='document_library_file_name']/ul/li/label)[3]")));
			WebElement rateElement11 = webDriver.findElement(By.xpath("(//td[@id='document_library_file_name']/ul/li/label)[3]"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement11);	
			WebElement rateElement111 = webDriver
					.findElement(By.xpath("(//div[@id='document_library']/fieldset/div/div/button)[3]"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement111);
			logger.info("document uploaded successully");
			Thread.sleep(1000);
		} else {
			if (webDriver.getCurrentUrl().contains("localhost")) {
				Thread.sleep(2000);
				WebElement rateElement2 = webDriver
						.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]"));
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement2);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='doc-lib-button'])[3]")));
				WebElement rateElement1 = webDriver.findElement(By.xpath("(//button[@id='doc-lib-button'])[3]"));
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement1);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//td[@id='document_library_file_name']/ul/li/label)[3]")));
				WebElement rateElement11 = webDriver.findElement(By.xpath("(//td[@id='document_library_file_name']/ul/li/label)[3]"));
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement11);	
				WebElement rateElement111 = webDriver
						.findElement(By.xpath("(//div[@id='document_library']/fieldset/div/div/button)[3]"));
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement111);
				logger.info("document uploaded successully");
				Thread.sleep(1000);
				;
			} else {
				if (webDriver.getCurrentUrl().contains("newqa")) {
					WebElement rateElement2 = webDriver
							.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]"));
					((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement2);
					WebElement rateElement1 = webDriver.findElement(By.xpath("(//button[@id='doc-lib-button'])[3]"));
					((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement1);
					WebElement rateElement11 = webDriver.findElement(By.xpath("(//input[@id='truth'])[3]"));
					((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement11);
					WebElement rateElement111 = webDriver
							.findElement(By.xpath("(//div[@id='document_library']/fieldset/div/div/button)[3]"));
					((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement111);
					logger.info("document uploaded successully");
					Thread.sleep(1000);
					;
				} else {
					Thread.sleep(2000);
					logger.debug("Uploading a new document");
					webDriver.findElement(By.xpath("(//a[contains(text(),'Add required documents')])[3]")).click();
					Thread.sleep(2000);
					webDriver.findElement(By.xpath("(//button[@id='doc-upload-button'])[3]")).click();
					Thread.sleep(2000);
					Actions act = new Actions(webDriver);
					act.doubleClick(webDriver.findElement(By.xpath("(//a[contains(text(),'Choose a .pdf file')])[3]")))
							.build().perform();
					StringSelection ss = new StringSelection(upload_Path);
					Thread.sleep(1000);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					Thread.sleep(2000);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='attach'])[3]")));
					Actions act1 = new Actions(webDriver);
					act1.doubleClick(webDriver.findElement(By.xpath("(//button[@id='attach'])[3]"))).build().perform();
					Thread.sleep(2000);
				}
			}
		}
	}
}
