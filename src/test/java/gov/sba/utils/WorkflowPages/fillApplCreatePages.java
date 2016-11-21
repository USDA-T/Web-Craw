package gov.sba.utils.WorkflowPages;

import gov.sba.utils.DeepaMppUploadDocumentPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class fillApplCreatePages {
	private static final Logger logger = LogManager.getLogger(fillApplCreatePages.class.getName());
	
	public static void page8aFillUp(WebDriver webDriver, String answer01, String path) throws Exception {

		if (answer01.toUpperCase().contains("YES")){
			// Use webDriver.findElement(By.id("answers_65_value_no")).click(); for no
			webDriver.findElement(By.id("answers_65_value_yes")).click();
			DeepaMppUploadDocumentPage deepaUploadMppDocument = new DeepaMppUploadDocumentPage(webDriver);
			deepaUploadMppDocument.deepaUploadMppDocument(path);
			logger.info("Doc has been uploaded.");
			Thread.sleep(2000);
			webDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);
			webDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(1000);
			webDriver.switchTo().alert().accept();
			logger.info("Doc has been uploaded and accepted");
		}
		else{
			webDriver.findElement(By.id("answers_65_value_no")).click();
			webDriver.findElement(By.xpath("//input[@type='submit']")).click();
		}
	}

	public static void finalSignatureSubmit(WebDriver webDriver) throws Exception {
		webDriver.findElement(By.xpath("//*[@id='legal_0']")).click();
		webDriver.findElement(By.xpath("//*[@id='legal_1']")).click();
		webDriver.findElement(By.xpath("//*[@id='legal_2']")).click();
		webDriver.findElement(By.xpath("//*[@id='legal_3']")).click();
		webDriver.findElement(By.xpath("//*[@id='legal_4']")).click();
		webDriver.findElement(By.xpath("//*[@id='legal_5']")).click();
		webDriver.findElement(By.xpath("//input[@type='submit']")).click();
	}
}
