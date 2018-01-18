package gov.usda.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAnetPageWithReference1 {
	private static final Logger logger = LogManager.getLogger(LoginAnetPageWithReference1.class.getName());
	WebDriver webDriver;
	int get_Row_From_credentials_Recvd;

	public LoginAnetPageWithReference1(WebDriver webDriver_Passed_From_CallingFn, int get_Row_From_credentials_Passed) {
		this.webDriver = webDriver_Passed_From_CallingFn;
		this.get_Row_From_credentials_Recvd = get_Row_From_credentials_Passed;
	}

	public void Login_With_Reference() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		logger.debug("Using test login   : "
				+ LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
		logger.debug("Using test password: "
				+ LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getPassword());
		// Click the login link.
		click_Element(webDriver, "Click_HomePage_Login_Link");
		// Enter the user name.
		setText_Element(webDriver, "FullName_TextBox",
				LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
		click_Element(webDriver, "Click_Display_Name");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[7]/td")));
		// Click on the Submit Button.
		click_Element(webDriver, "Click_Submit_Button");
		String url = webDriver.getCurrentUrl();
		org.junit.Assert.assertTrue(url.contains("usda"));
		Thread.sleep(9000);
	}
}
