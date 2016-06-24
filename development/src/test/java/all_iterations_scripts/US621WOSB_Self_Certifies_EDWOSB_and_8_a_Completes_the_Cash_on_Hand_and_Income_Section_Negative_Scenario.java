package all_iterations_scripts;

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
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.ie.InternetExplorerDriver;

public class US621WOSB_Self_Certifies_EDWOSB_and_8_a_Completes_the_Cash_on_Hand_and_Income_Section_Negative_Scenario {
	private WebDriver mydriver;
	String myURL;
	String Email;
	String PassW;
	String comment;
	String FN;
	String LN;
	String SSN;
	String Address;
	String City;
	String State;
	String Zip;
	String Country;
	String Home_Phone;
	String Business_Phone;
	String Email2;
	String AS_Of_DATE;
	String Cash_On_Hand;
	String Saving_Account;
	String Checking_Account;
	String Salary;
	String Other_Income;
	String Business_Equity;
	String Equity_in_other_firm;
	String AS_of_DATE2;
	String Invalid_Data;

	int myRows, myCols;

	/**
	 * @param args
	 */
	// Beginning part

	@Before
	public void US621WOSB_Self_Certifies_EDWOSB_and_8_a_Completes_the_Cash_on_Hand_and_Income_Section_Negative_Scenario_setup()
			throws Exception {
		// Email="akanamontana@gmail.com";
		// PassW="Map Effect Applied Furniture 3365";
		comment = "Test";
		FN = "Pual";
		LN = "Washington";
		SSN = "209-88-3376";
		Address = "8675 Dons ST";
		City = "Manassas";
		Zip = "36510";
		Home_Phone = "703-765-8982";
		Business_Phone = "704-765-9872";
		Email2 = "applicant2@email.com";
		Salary = "100000";
		Other_Income = "70000";
		Business_Equity = "57000";
		Equity_in_other_firm = "20000";
		State = "Virginia";
		Country = "USA";
		AS_Of_DATE = "1/12/2016";
		AS_of_DATE2 = "1-12-2016";
		Invalid_Data = "Smiling";

		// myURL="http://certify.qa.sba-one.net/users/sign_in";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();

	}

	@Test
	public void US621WOSB_Self_Certifies_EDWOSB_and_8_a_Completes_the_Cash_on_Hand_and_Income_Section_Negative_Scenario_mymaintest()
			throws Exception {
		// Locate the test date from the PC and verify that all data are valid.
		Thread.sleep(4000);
		String SheetPath = "C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\Notes_Receivable_Test_Data..xls";
		String[][] myXLSheet = readXLSheet(SheetPath, "Test_Data");

		for (int k = 1; k < myRows; k++) {

			Cash_On_Hand = myXLSheet[k][6];
			Saving_Account = myXLSheet[k][7];
			Checking_Account = myXLSheet[k][8];
			myURL = myXLSheet[k][18];
			Email = myXLSheet[k][19];
			PassW = myXLSheet[k][20];

			// Step 00. Open Firefox Browser and navigate to valid url.
			mydriver.navigate().to(myURL);
			Thread.sleep(3000);
			mydriver.manage().window().maximize();
			Thread.sleep(6000);
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

			// Verify if there is an existing certification on the dashboard and
			// delete to start a new certification.
			if (mydriver.getPageSource().contains("Continue")) {
				mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr[1]/td[4]/a[2]"))
						.click();
				mydriver.switchTo().alert().accept();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mydriver.navigate().refresh();
			} else {
				System.out.println(
						"There are(is) no certification in-progress on the dashboard, a new certification is beinng created");

			}
			Thread.sleep(7000);
			// Locate the Certifications on the dashboard, click on it and
			// select EDWOSB to continue.
			mydriver.findElement(By.linkText("Certifications")).click();
			mydriver.findElement(By.linkText("EDWOSB")).click();
			Thread.sleep(2000);

			// MainTest CASH ON HAND.
			Thread.sleep(2000);
			// Locate and click on the continue button without entering any
			// data.
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			// Verify that user is not being navigated to the next and section
			// and user sees alert message 'Please answer this question' for all
			// required field
			if (mydriver.getPageSource().contains("Please answer this question")) {
				System.out.println("User sees alert message; 'Please answer this question', pass");
				// Locate the As of Date search box and enter a wrong date
				// format type and verify that system rejects data type.
				mydriver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE2);
				Thread.sleep(2000);
				// Verify that user sees an error message; 'Please enter a valid
				// date'.
				if (mydriver.getPageSource().contains("Please enter a valid date.")) {
					System.out.println("User is promted to enter a valid date format type, Pass");
					mydriver.findElement(By.xpath(".//*[@id='answers_32_value']")).clear();
				} else {
					System.out.println("User is Not promted to enter a valid date format type, Failed");
				}
			} else {
				System.out.println("User did not see alert message; 'Please answer this question', pass");

			}
			Thread.sleep(3000);

			// Locate the cash on hand, saving and checking account search box
			// and enter an invalid data type and verify that data type is being
			// rejected.
			mydriver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys(Invalid_Data);
			mydriver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys(Invalid_Data);
			mydriver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys(Invalid_Data);
			// Locate the Continue button and click on it to continue.
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			Thread.sleep(3000);

			if (mydriver.getPageSource().contains("Please answer this question")) {
				System.out
						.println("invalid data type is being rejected, user sees alert; 'Please answer this question'");
			} else {
				System.out.println(
						"invalid data type is Not being rejected, user Did Not see alert; 'Please answer this question'");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate and click on Cash on Hand.
			mydriver.findElement(By.xpath(".//*[@id='cash_on_hand_pual_washington']")).click();
			Thread.sleep(3000);
			// Locate section for 'Cash on Hand' enter all valid data as
			// required.
			// Locate the As of Date: Search box for user and enter the
			// information as required.
			mydriver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_Of_DATE);
			// Locate the Cash on Hand Search box for Pual Washington and enter
			// the information as required.
			mydriver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys(Cash_On_Hand);
			// Locate the Savings Account(s) Balance Search box for Pual
			// Washington and enter the information as required.
			mydriver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys(Saving_Account);
			// Locate the Checking Account(s) Balance Search box for Pual
			// Washington and enter the information as required.
			mydriver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys(Checking_Account);
			// Locate the Continue button and click on it to continue.
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Verify that user is navigated to the Other Sources of income
			// section.
			if (mydriver.getPageSource().contains("Other Sources Of Income")) {
				mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
				Thread.sleep(2000);
				if (mydriver.getPageSource().contains("Please answer this question")) {
					System.out.println("Ommited question alert message 'Please answer this question' is present, pass");
				} else {
					System.out.println(
							"Ommited question alert message 'Please answer this question' is Not present, Failed");

				}
				Thread.sleep(2000);

			} else {
				System.out.println("User is not being navigated to the Other Sources Of Income Section, Failed.");
				mydriver.quit();
			}
			// Locate section for Other Source of Income enter all valid data as
			// required.
			// Locate the Salary search box and enter salary.

			mydriver.findElement(By.xpath(".//*[@id='answers_36_value']")).sendKeys(Salary);
			// Locate the Other Income search box and enter Other Income.
			mydriver.findElement(By.xpath(".//*[@id='answers_37_value']")).sendKeys(Other_Income);
			// Locate the applicant Business Type and enter amount of applicant
			// equity.
			mydriver.findElement(By.xpath(".//*[@id='answers_38_value']")).sendKeys(Business_Equity);
			// Locate the applicant equity in other firm and enter applicant
			// business equity.
			mydriver.findElement(By.xpath(".//*[@id='answers_39_value']")).sendKeys(Equity_in_other_firm);
			// Locate the continue button and click on it to continue.
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			Thread.sleep(2000);
			// Verify that user is navigated to the Section Notes Receivable.
			if (mydriver.getPageSource().contains("Notes Receivable")) {
				System.out.println("User is being navigated to the next valid section 'Notes Receivable', pass");
			} else {
				System.out.println("User is not being navigated to the next valid section 'Notes Receivable', Failed");

			}

			Thread.sleep(3000);
			System.out.println(mydriver.findElement(By.xpath(".//*[@id='edwosb']")).getText());
		}
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
	public void US621WOSB_Self_Certifies_EDWOSB_and_8_a_Completes_the_Cash_on_Hand_and_Income_Section_Negative_Scenario_teardown()
			throws Exception {
		mydriver.quit();
	}

}
