package gov.usda.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageWithReference {
	private static final Logger logger = LogManager.getLogger(LoginPageWithReference.class.getName());
	WebDriver webDriver;
	int get_Row_From_credentials_Recvd;

	public LoginPageWithReference(WebDriver webDriver_Passed_From_CallingFn, int get_Row_From_credentials_Passed) {
		this.webDriver = webDriver_Passed_From_CallingFn;
		this.get_Row_From_credentials_Recvd = get_Row_From_credentials_Passed;
	}

	public void Login_With_Reference() throws Exception {
		try {
			logger.debug("Using test login   : "
					+ LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
			logger.debug("Using test password: "
					+ LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getPassword());
			WebElement rateElement = webDriver.findElement(By.linkText("Log In"));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement);
			setText_Element(webDriver, "ID_Locator",
					LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
			setText_Element(webDriver, "USDA_Login_Pwd",
					LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getPassword());
			click_Element(webDriver, "Click_Login_Button");
		} catch (Exception e) {
			logger.info("Login error " + e.getMessage());
		}
	}
}
