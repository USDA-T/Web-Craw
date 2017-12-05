package gov.usda.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.TestCase;

public class AdvancedSearchValdationPage extends TestCase {
	private static final Logger logger = LogManager.getLogger(AdvancedSearchValdationPage.class.getName());
	WebDriver webDriver;

	public AdvancedSearchValdationPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void AdvancedSearchValdation() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		logger.info("Completing Advanced search");
		// Complete advanced search.
		try {
			Actions action = new Actions(webDriver);
			WebElement WE = find_Element(webDriver, "Search_Tab_Locator");
			action.moveToElement(WE).build().perform();
			// Select advanced from the dropdown.
			click_Element(webDriver, "Select_Advanced_From_Dropdown");
			// Wait for page to fully load.
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h2")));
			// Verify that user navigate to the correct page.
			assertEquals("Advanced Operation Search", find_Element(webDriver, "Advanced_Search_Page_Title").getText());
			// Sealect a role and search by the NOP id and verify data return.
			click_Element(webDriver, "Select_Operation");
			// Get the NOP ID and the Operation name.
			String Opeartion_Name;
			Opeartion_Name = find_Element(webDriver, "Get_Operation_Name").getText();
			String NOP_ID;
			NOP_ID = find_Element(webDriver, "Get_NOP_ID").getText();
			webDriver.navigate().back();
			// Enter the NOP ID and click on the search button.
			setText_Element(webDriver, "NOP_ID_Search_Box_Locator", NOP_ID);
			// Click on the search button.
			click_Element(webDriver, "Click_Top_Search_Button");
			// Verify Return Search data.
			click_Element(webDriver, "Return_search_data");
			String NOP_ID1;
			NOP_ID1 = find_Element(webDriver, "Get_NOP_ID").getText();
			assertEquals(NOP_ID1, find_Element(webDriver, "Get_NOP_ID").getText());
		} catch (Exception ex) {
			logger.info(ex);
			throw new RuntimeException(ex);
		}
	}
}