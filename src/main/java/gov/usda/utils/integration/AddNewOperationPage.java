package gov.usda.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.TestCase;

public class AddNewOperationPage extends TestCase {
	private static final Logger logger = LogManager.getLogger(AddNewOperationPage.class.getName());
	WebDriver webDriver;
	String Operation_Name;
	String Address;
	String City;
	String Zip;
	String Effective_date;

	public AddNewOperationPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		Operation_Name = "Test Data1";
		Address = "12344 Test data";
		City = "Washington";
		Zip = "20998";
		Effective_date = "12/23/2017";
	}

	public void AddNewOperation() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		logger.info("Completing test to add an Operation");
		// From the Operation menu, select "Select a certifier".
		Actions action = new Actions(webDriver);
		WebElement WE = find_Element(webDriver, "Operations_Tab_Locator");
		action.moveToElement(WE).build().perform();
		// Select "Select a certifier" from the dropdown.
		click_Element(webDriver, "Select_A_Certifier");
		// select a Cerifier.
		try {
			click_Element(webDriver, "Chose_one_Certifier");
			// Click on the add an operation link from the left nav.
			click_Element(webDriver, "Click_Add_an_Operation");
			// Verify the paage to add new operation.
			assertEquals("Staging Area - Add a New Operation", find_Element(webDriver, "Add_Operation_page").getText());
			// Click on the Create button without entering any data to validate
			// required field.
			click_Element(webDriver, "Create_Button");
			// Verify the all the required field message.
			assertEquals(
					"Operation Name is required.\nOperation Status is required.\nOperation Status Effective Date is required.\n[Physical Address] Address Line 1 is required.\n[Physical Address] City is required.\n[Physical Address] State is required.\n[Physical Address] Zip Code Zip Code is required.\n[Mailing Address] Address Line 1 is required.\n[Mailing Address] City is required.\n[Mailing Address] State is required.\n[Mailing Address] Zip Code Zip Code is required.",
					find_Element(webDriver, "Reqired_feild_error_message").getText());
			// Enter Operation name.
			setText_Element(webDriver, "Operaion_Name", Operation_Name);
			// Select operation status and enter effective date.
			WebElement mySelectElement3 = find_Element(webDriver, "Operaion_status");
			Select dropdown3 = new Select(mySelectElement3);
			dropdown3.selectByVisibleText("Surrendered");
			click_Element(webDriver, "Effective_date");
			click_Element(webDriver, "Select_day");
			// check address type.
			click_Element(webDriver, "Address_Type");
			// Enter Address line 1.
			setText_Element(webDriver, "Address_line1", Address);
			// Enter Address line 2.
			setText_Element(webDriver, "Address_line2", Address);
			// Enter City1.
			setText_Element(webDriver, "City_line1", City);
			// Enter City2.
			setText_Element(webDriver, "City_line2", City);
			// verify all the states
			assertEquals(
					"     -- Select --\n     Alabama\n     Alaska\n     American Samoa\n     Arizona\n     Arkansas\n     California\n     Colorado\n     Connecticut\n     Delaware\n     District of Columbia\n     Federated States of Micronesia\n     Florida\n     Georgia\n     Guam\n     Hawaii\n     Idaho\n     Illinois\n     Indiana\n     Iowa\n     Kansas\n     Kentucky\n     Louisiana\n     Maine\n     Maryland\n     Massachusetts\n     Michigan\n     Midway Islands\n     Minnesota\n     Mississippi\n     Missouri\n     Montana\n     Nebraska\n     Nevada\n     New Hampshire\n     New Jersey\n     New Mexico\n     New York\n     North Carolina\n     North Dakota\n     Northern Mariana Islands\n     Ohio\n     Oklahoma\n     Oregon\n     Pennsylvania\n     Puerto Rico\n     Rhode Island\n     South Carolina\n     South Dakota\n     Tennessee\n     Texas\n     Utah\n     Vermont\n     Virgin Islands\n     Virginia\n     Washington\n     West Virginia\n     Wisconsin\n     Wyoming\n    ",
					find_Element(webDriver, "State_For_line1").getText());
			// Select State on address line 1.
			WebElement mySelectElement = find_Element(webDriver, "State_For_line1");
			Select dropdown = new Select(mySelectElement);
			dropdown.selectByVisibleText("Alabama");
			// Select State on address line 2.
			WebElement mySelectElement1 = find_Element(webDriver, "State_For_line2");
			Select dropdown1 = new Select(mySelectElement1);
			dropdown1.selectByVisibleText("Alabama");
			// Enter zip code for address line 1 and 2.
			setText_Element(webDriver, "Zip_code1", Zip);
			setText_Element(webDriver, "Zip_code2", Zip);
			// Select Country for line 1 and 2.
			WebElement mySelectElement2 = find_Element(webDriver, "Country1");
			Select dropdown2 = new Select(mySelectElement2);
			dropdown2.selectByVisibleText("United States of America");
			WebElement mySelectElement21 = find_Element(webDriver, "Country1");
			Select dropdown21 = new Select(mySelectElement21);
			dropdown21.selectByVisibleText("United States of America");
			// Click on the Create button and verify that operation was created.
			click_Element(webDriver, "Create_button");
			assertEquals("The Operation was Created.", find_Element(webDriver, "Operation_success_message").getText());

		} catch (Exception ex) {
			// Take screenshot
			ScreenShotPage1 ScreenShot = new ScreenShotPage1(webDriver);
			ScreenShot.ScreenShot();
			logger.info(ex);
			throw new RuntimeException(ex);
		}
	}
}