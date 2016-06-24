package all_production_test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US923_View_more_under_Business_Profile_should_be_a_link {

	public WebDriver mydriver;
	String myURL;
	String Email;
	String PassW;

	int myRows, myCols;

	/**
	 * @param args
	 */
	// Beginning part

	@Before
	public void US923_View_more_under_Business_Profile_should_be_a_link_413_setup() throws Exception {

		// Email="https://certify.qa.sba-one.net/users/sign_in";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();
	}

	@Test
	public void US923_View_more_under_Business_Profile_should_be_a_link_mymaintest() throws Exception {
		// Locate the test date from the PC and verify that all data are valid.
		Thread.sleep(4000);

		String SheetPath = "C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\Notes_Receivable_Test_Data..xls";
		String[][] myXLSheet = readXLSheet(SheetPath, "Test_Data");

		for (int k = 1; k < myRows; k++) {

			myURL = myXLSheet[k][25];
			Email = myXLSheet[k][21];
			PassW = myXLSheet[k][22];

			// Step 00. Open Firefox Browser and navigate to valid url.
			mydriver.navigate().to(myURL);
			Thread.sleep(3000);
			mydriver.manage().window().maximize();

			// Step 01. Login to dashboard, Locate the email search box and
			// enter a valid email.
			mydriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
			// Step 2. Locate the password search box and enter a valid
			// password.
			mydriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(PassW);
			// Step 3. Locate the Sign-in button and click on it to login.
			mydriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Signed in successfully")) {
				mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
			} else {
				System.out.println("Successful sign in alert message not present");
			}
			Thread.sleep(3000);

			// Verify that the Manage documents button is changed to a link and
			// bellow the Document Library table.
			mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a")).click();
			Thread.sleep(4000);
			if (mydriver.getPageSource().contains("Alt Gov Business POC First Name")) {
				System.out.println("Text on link 'View More' is validated, Pass");
				mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a")).click();

				WebElement alllinks = mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a"));
				System.out.println("The View More button was successfully change to link: " + alllinks);

				System.out.println(alllinks.getText());
				// Verify that the new link "Manage documents" works.
				// Verify the view more view less.
				assertElementPresent("view more+",
						mydriver.findElement(By.cssSelector("a.expander-view-more-trigger.expander-view-more-hidden")));
				mydriver.findElement(By.cssSelector("a.expander-view-more-trigger.expander-view-more-hidden")).click();
				assertElementPresent("view Less-",
						mydriver.findElement(By.cssSelector("a.expander-view-more-trigger")));

				String actual_Text4 = mydriver
						.findElement(By.xpath("//article[@id='dashboard-index']/div[3]/div/div/div[2]/p/b")).getText();
				String expected_Text4 = "Alt Gov Business POC First Name:";
				assertEquals(actual_Text4, expected_Text4);

				// Verify that the new link "Manage documents" works.
				mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a")).click();

				Thread.sleep(3000);
				if (mydriver.getPageSource().contains("Alt Gov Business POC First Name")) {
					System.out.println("The button was successfully change to a link and the link is active, Pass");
					mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
				} else {
					System.out.println("The 'View More' link is not active, Failed");
					mydriver.quit();
				}

			} else {
				System.out.println("Text on link 'View More' is not validated, failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

		}
	}

	private void assertElementPresent(String string, WebElement findElement) {
		// TODO Auto-generated method stub

	}

	// Method/Function for reading data from Excel Sheet
	public String[][] readXLSheet(String SheetPath, String SheetName) throws Exception {
		String[][] xData;
		File myXLSheet = new File(SheetPath);
		FileInputStream myStream = new FileInputStream(myXLSheet);
		@SuppressWarnings("resource")
		HSSFWorkbook myWB = new HSSFWorkbook(myStream);
		HSSFSheet mySheet = myWB.getSheet(SheetName);
		myRows = mySheet.getLastRowNum() + 1;
		myCols = mySheet.getRow(0).getLastCellNum();
		xData = new String[myRows][myCols];
		for (int i = 0; i < myRows; i++) {
			HSSFRow row = mySheet.getRow(i);
			for (int j = 0; j < myCols; j++) {
				HSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				xData[i][j] = value;
			}
		}
		return xData;
	}

	// Method/Function to Change cell type
	public static String cellToString(HSSFCell cell) {
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 0
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case HSSFCell.CELL_TYPE_BLANK: // 3
			result = "-";
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // 4
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: " + type);
		}
		return result.toString();
	}

	@After
	public void US923_View_more_under_Business_Profile_should_be_a_link_teardown() throws Exception {
		mydriver.quit();
	}
}
