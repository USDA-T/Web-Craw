package gov.sba.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotPage3 {
	private static final Logger logger = LogManager.getLogger(ScreenShotPage3.class.getName());
	WebDriver webDriver;

	public ScreenShotPage3(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void ScreenShot3() throws Exception {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to the screenshot folder.
			FileUtils.copyFile(src, new File("./Screenshots/Versions.png"));
		} catch (IOException e) {
			logger.info(e.getMessage());

		}
	}
}

