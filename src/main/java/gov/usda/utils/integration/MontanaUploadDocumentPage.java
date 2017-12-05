package gov.usda.utils.integration;

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

public class MontanaUploadDocumentPage {
	private static final Logger logger = LogManager.getLogger(DeleteDraftCertPage.class.getName());
	WebDriver webDriver;

	public MontanaUploadDocumentPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void MontanaUploadDocument(String upload_Path) throws Exception {

		Thread.sleep(2000);
		logger.info("Uploading a new document");
		WebDriverWait wait = new WebDriverWait(webDriver, 40);
		logger.info(webDriver.getCurrentUrl());
		if (webDriver.getCurrentUrl().contains("certify.qa")) {
			JavascriptExecutor jse = (JavascriptExecutor) webDriver;
			jse.executeScript("arguments[0].scrollIntoView()",
					webDriver.findElement(By.cssSelector("#add-req-doc-button > a")));
			webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("doc-lib-button")));
			webDriver.findElement(By.id("doc-lib-button")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='document_library_file_name']/ul/li/label")));
			WebElement rateElement11 = webDriver.findElement(By.xpath("//td[@id='document_library_file_name']/ul/li/label"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement11);			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='document_library_associate']")));
			jse.executeScript("arguments[0].scrollIntoView()",
					webDriver.findElement(By.xpath("//button[@id='document_library_associate']")));
			webDriver.findElement(By.xpath("//button[@id='document_library_associate']")).click();
			logger.info("document uploaded successully");
			Thread.sleep(1000);
		} else {
			if (webDriver.getCurrentUrl().contains("localhost")) {
				JavascriptExecutor jse = (JavascriptExecutor) webDriver;
				jse.executeScript("arguments[0].scrollIntoView()",
						webDriver.findElement(By.cssSelector("#add-req-doc-button > a")));
				webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("doc-lib-button")));
				webDriver.findElement(By.id("doc-lib-button")).click();
				// wait.until(ExpectedConditions.elementToBeClickable(By.name("selector")));
				WebElement rateElement11 = webDriver.findElement(By.cssSelector("li > label"));
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement11);
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//button[@id='document_library_associate']")));
				webDriver.findElement(By.xpath("//button[@id='document_library_associate']")).click();
				logger.info("document uploaded successully");
				Thread.sleep(1000);
			} else {
				if (webDriver.getCurrentUrl().contains("newqa")) {
					JavascriptExecutor jse = (JavascriptExecutor) webDriver;
					jse.executeScript("arguments[0].scrollIntoView()",
							webDriver.findElement(By.cssSelector("#add-req-doc-button > a")));
					webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div/button")));
					webDriver.findElement(By.xpath("//div[2]/div/button")).click();
					// wait.until(ExpectedConditions.elementToBeClickable(By.name("selector")));
					webDriver.findElement(By.name("selector")).click();
					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("//button[@id='document_library_associate']")));
					webDriver.findElement(By.xpath("//button[@id='document_library_associate']")).click();
					logger.info("document uploaded successully");
					Thread.sleep(1000);
				} else {
					Thread.sleep(2000);
					logger.debug("Uploading a new document");
					webDriver.findElement(By.cssSelector("#add-req-doc-button > a")).click();
					Thread.sleep(2000);
					webDriver.findElement(By.id("doc-upload-button")).click();
					Thread.sleep(2000);
					Actions act = new Actions(webDriver);
					act.doubleClick(webDriver.findElement(By.linkText("Choose a .pdf file"))).build().perform();
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
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='attach']")));
					Actions act1 = new Actions(webDriver);
					act1.doubleClick(webDriver.findElement(By.xpath("//button[@id='attach']"))).build().perform();
					Thread.sleep(2000);
				}
			}
		}
	}
}