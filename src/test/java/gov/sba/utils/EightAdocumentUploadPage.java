package gov.sba.utils;

	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class EightAdocumentUploadPage {
		private static final Logger logger = LogManager.getLogger(EightAdocumentUploadPage.class.getName());
		WebDriver webDriver;
		public EightAdocumentUploadPage(WebDriver webDriver) {
			this.webDriver = webDriver;
		}

		public void EightAdocumentUpload() throws Exception {
			logger.debug("Uploading a new document");
			webDriver.findElement(By.linkText("Choose a .pdf file")).click();
			StringSelection ss = new StringSelection("C:\\Users\\Derec Nguni\\Documents\\MainTestUploadDoc.pdf");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(6000);
			webDriver.findElement(By.id("comment")).sendKeys("Testing Documents");
			webDriver.findElement(By.cssSelector("#attach")).click();
		}

	}
