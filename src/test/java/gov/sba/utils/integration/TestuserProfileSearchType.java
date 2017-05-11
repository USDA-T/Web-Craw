//TS Created By _deepa patri
package gov.sba.utils.integration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;

public class TestuserProfileSearchType {

	public static void TestuserProfileSearch(WebDriver webDriver, String last_name, String xpathSearch,
			String Expected_Result) throws Exception {
		// Select Government User Radio button
		CommonApplicationMethods.click_Element(webDriver, xpathSearch);
		CommonApplicationMethods.setText_Element(webDriver, "OppSup_Dashboard_User_Search_Text", last_name);
		CommonApplicationMethods.click_Element(webDriver, "EDWOSB_Questionnaire_Page_Commit");
		// User Search results
		@SuppressWarnings("unused")
		WebElement table = webDriver.findElement(By.xpath("//table[@class='searchable']"));
		webDriver.findElement(By.xpath("//table[contains(@class,'searchable')]/tbody/tr/td[1]/a")).click();
		String Actual_result = webDriver
				.findElement(By.xpath("//article[@id='main-content']/div/div/div/h1[contains(text(),'user profile')]"))
				.getText();
		Assert.assertEquals(Actual_result, Expected_Result);
	}
}