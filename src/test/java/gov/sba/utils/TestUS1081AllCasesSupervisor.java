package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.TestCase;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TestUS1081AllCasesSupervisor extends TestCase {
	// Set The variables/Define
	private static WebDriver webDriver;
	private static final Logger logger = LogManager.getLogger(TestUS1081AllCasesSupervisor.class.getName());
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 21;
	}

	@Test
	public void TestMainTest() throws Exception {
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);

		// Need to submit the application in EDWosb, Wosb
		// Log in As Super visor - validate as per the US1081 Acceptance
		// criteria on Supervisor All cases page
		// Log in As Analyst - validate as per the US1081 Acceptance criteria on
		// Supervisor All cases page

		try {
			WebElement Cases_Link = webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
			Cases_Link.click();
			Thread.sleep(3000);
			logger.info("Cases link is on Main Navigator is Clicked");

			String Allcases_PageTitle = webDriver
					.findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'All cases')]")).getText();
			assertEquals(Allcases_PageTitle, "All cases");

			String[] header_Names_Array = new String[] { "Business name", "DUNS", "Program", "Review type", "Submitted",
					"Owner", "Current reviewer", "Status" };

			// WebElement allcase_Table =
			// webDriver.findElement(By.xpath("//table[@class='searchable
			// usa-table-borderless']"));
			List<WebElement> rows_Header = webDriver
					.findElements(By.xpath("//div[@id='table-search']/table/thead/tr/th")); // Get
																							// the
																							// Table
																							// Header
																							// Cells
			String[] header_Names_Array_Validate = new String[8];
			java.util.Iterator<WebElement> list_elements = rows_Header.iterator();
			int i = 0;
			while (list_elements.hasNext()) {
				header_Names_Array_Validate[i] = list_elements.next().getText();
				i = i + 1;
			}

			logger.info(header_Names_Array_Validate[0]);

			Assert.assertArrayEquals(header_Names_Array, header_Names_Array_Validate);
			String duns_Number = "144754156";

			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // get
																		// current
																		// date
																		// time
																		// with
																		// Date()
			Date date = new Date();
			String todays_Date = dateFormat.format(date);
			logger.info(todays_Date);

			todays_Date = "09/25/2016"; // Comment this during actual Run as
										// this is for testing

			// Get the row for EDWOSB //String xpath_String_ForEdwosb =
			// "//div[@id='table-search']/table/tbody/tr[contains(text(),'" +
			// duns_Number + "') and contains(text(),'EDWOSB')]" ; // String
			// xpath_String_ForEdwosb =
			// "//div[@id='table-search']/table/tbody/tr/td[contains(text(),'" +
			// duns_Number + "')]" ;// String row_Edwosb_Duns =
			// webDriver.findElement(By.xpath(xpath_String_ForEdwosb)).findElement(By.xpath("./..")).getText();
			// // Get the Table rows
			String xpath_String_ForEdwosb = "//div[@id='table-search']/table/tbody/tr[td[contains(text(),'"
					+ duns_Number + "')] and td[text()='EDWOSB'] ]";
			String row_Edwosb_Duns = webDriver.findElement(By.xpath(xpath_String_ForEdwosb)).getText(); // Get
																										// the
																										// Table
																										// rows
			logger.info(row_Edwosb_Duns);
			Assert.assertEquals(row_Edwosb_Duns,
					"Entity 334 Legal Business Name " + duns_Number + " EDWOSB " + todays_Date + " submitted");
			logger.info("___________________");

			String xpath_String_ForWosb = "//div[@id='table-search']/table/tbody/tr[td[contains(text(),'" + duns_Number
					+ "')] and td[text()='WOSB'] ]";
			String row_Wosb_Duns = webDriver.findElement(By.xpath(xpath_String_ForWosb)).getText(); // Get
																									// the
																									// Table
																									// rows
			logger.info(row_Wosb_Duns);
			Assert.assertEquals(row_Wosb_Duns,
					"Entity 334 Legal Business Name " + duns_Number + " WOSB " + todays_Date + " submitted");

			// Entire Table VErification for next Sprint
			// List<WebElement> rows_Body =
			// webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr"));
			// // Get the Table rows
			// list_elements = rows_Body.iterator();
			// i = 0;
			// String[] row_Value_Array_Validate = new String[200];
			// while (list_elements.hasNext()) {
			// i = 0;
			// java.util.Iterator<WebElement> rows_Body_Cells =
			// list_elements.next().findElements(By.xpath("//th")).iterator();
			// // Get the Table Cells
			// while (rows_Body_Cells.hasNext()){
			// row_Value_Array_Validate[i] = rows_Body_Cells.next().getText();
			// i= i+1;
			// }
			// }

		} catch (Exception e) {
			logger.info("Cases link is on Main Navigator is not present" + e.toString());
		}

	}

	@After
	public void tearDown() throws Exception {
	webDriver.quit();
	}
}