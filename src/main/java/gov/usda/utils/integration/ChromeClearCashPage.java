package gov.usda.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.TestCase;

public class ChromeClearCashPage extends TestCase {
	private static final Logger logger = LogManager.getLogger(ChromeClearCashPage.class.getName());
	WebDriver webDriver;

	public ChromeClearCashPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void ChromeClearCash() throws Exception {
		String Actual_Browser = null;
		String Expected_Browser = null;
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		if (webDriver.getPageSource().contains("Organic")) {
			Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();
			logger.info(browserName);
			String os = cap.getPlatform().toString();
			logger.info(os);
			String v = cap.getVersion().toString();
			logger.info(v);
			try {
			Actual_Browser = browserName;
			Expected_Browser = "chrome";
			assertEquals(Actual_Browser, Expected_Browser);
			} catch (Exception ex) {
				// Take screenshot
				ScreenShotPage1 ScreenShot = new ScreenShotPage1(webDriver);
				ScreenShot.ScreenShot();
				logger.info(ex);
				throw new RuntimeException(ex);
			}
			logger.info("Clearing chome cash");
			webDriver.get("chrome://settings/clearBrowserData");
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")));
			click_Element(webDriver, "Clear_Cash_button");
			webDriver.get("https://organic.ams.usda.gov/Integrity/");
			// webDriver.navigate().refresh();
		} else {
			if (webDriver.getPageSource().contains("AssuranceNet")) {
				Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
				String browserName = cap.getBrowserName().toLowerCase();
				logger.info(browserName);
				String os = cap.getPlatform().toString();
				logger.info(os);
				String v = cap.getVersion().toString();
				logger.info(v);
				try {
				Actual_Browser = browserName;
				Expected_Browser = "chrome";
				assertEquals(Actual_Browser, Expected_Browser);
				} catch (Exception ex) {
					// Take screenshot
					ScreenShotPage1 ScreenShot = new ScreenShotPage1(webDriver);
					ScreenShot.ScreenShot();
					logger.info(ex);
					throw new RuntimeException(ex);
				}
				logger.info("Clearing chome cash");
				webDriver.get("chrome://settings/clearBrowserData");
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")));
				click_Element(webDriver, "Clear_Cash_button");
				webDriver.get("https://dev.assurancenet.fsis.usda.gov/assurancenet/loginAction.do");
			} else {
				logger.info("The test browser is not Chrome");
			}
		}
	}
}
