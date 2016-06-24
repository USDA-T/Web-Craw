package all_iterations_scripts;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US934_change_Continue_buttons_to_Save_and_continue_within_questionnaire {

	private WebDriver mydriver;
	String myURL;
	String NOD;
	String AOD;
	String OB;
	String CB;
	String PAc_a;
	String Col_Type;
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
	String AS_of_DATE;
	String Cash_On_Hand;
	String Saving_Account;
	String Checking_Account;
	String Salary;
	String Other_Income;
	String Business_Equity;
	String Equity_in_other_firm;
	String Loop;
	String Balance;
	String P_Resident;
	String Percentage;
	String Current_Value;
	String Loan_Balance;
	String Make_Model_Year;
	String Payment_Amoun;
	String Name_of_Lien_Holder;
	String Amount_of_Lien;
	String Terms_of_Payment;
	String Whom_Payable;
	String When_Due;
	String FN2;

	int myRows, myCols;

	/**
	 * @param args
	 */
	// Beginning part

	@Before
	public void US709_WOSB_Self_Certifies_EDWOSB_and_8_a_Users_reviews_Privacy_Statement_413_setup() throws Exception {
		comment = "Test";
		FN = "Pual";
		FN2 = "Danzel";
		LN = "Washington";
		SSN = "209-88-3376";
		Address = "8675 Dons ST";
		City = "Manassas";
		Zip = "36510";
		Home_Phone = "703-765-8982";
		Business_Phone = "704-765-9872";
		Email2 = "applicant2@email.com";
		AS_of_DATE = "01/12/2016";
		Cash_On_Hand = "80000.00";
		Saving_Account = "100000.00";
		Checking_Account = "80000.00";
		Salary = "100000";
		Other_Income = "70000";
		Business_Equity = "57000";
		Equity_in_other_firm = "20000";
		State = "Virginia";
		Country = "USA";
		Balance = "4000";
		P_Resident = "7645 Weems rd";
		Percentage = "70";

		// Email="https://certify.qa.sba-one.net/users/sign_in";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();
	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void US709_WOSB_Self_Certifies_EDWOSB_and_8_a_Users_reviews_Privacy_Statement_413_mymaintest()
			throws Exception {
		// Locate the test date from the PC and verify that all data are valid.
		Thread.sleep(4000);

		String SheetPath = "C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\Notes_Receivable_Test_Data..xls";
		String[][] myXLSheet = readXLSheet(SheetPath, "Test_Data");

		for (int k = 1; k < myRows; k++) {

			NOD = myXLSheet[k][0];
			AOD = myXLSheet[k][1];
			OB = myXLSheet[k][2];
			CB = myXLSheet[k][3];
			PAc_a = myXLSheet[k][4];
			Col_Type = myXLSheet[k][5];
			Cash_On_Hand = myXLSheet[k][6];
			Saving_Account = myXLSheet[k][7];
			Checking_Account = myXLSheet[k][8];
			Current_Value = myXLSheet[k][9];
			Loan_Balance = myXLSheet[k][10];
			Make_Model_Year = myXLSheet[k][11];
			Payment_Amoun = myXLSheet[k][12];
			Name_of_Lien_Holder = myXLSheet[k][13];
			Amount_of_Lien = myXLSheet[k][14];
			Terms_of_Payment = myXLSheet[k][15];
			Whom_Payable = myXLSheet[k][16];
			When_Due = myXLSheet[k][17];
			myURL = myXLSheet[k][18];

			// Step 00. Open Firefox Browser and navigate to valid url.
			mydriver.navigate().to(myURL);
			Thread.sleep(3000);
			mydriver.manage().window().maximize();

			// Step 01. Login to dashboard, Locate the email search box and
			// enter a valid email.
			LogincorpPage login = new LogincorpPage(mydriver);
			login.Logincorp();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Signed in successfully")) {
				mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
			} else {
				System.out.println("Successful sign in alert message not present");
			}
			Thread.sleep(3000);

			// Verify if there is an existing certification on the dashboard and
			// delete to start a new certification.
			if (mydriver.getPageSource().contains("Draft")) {
				mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr[1]/td[4]/a[2]"))
						.click();
				mydriver.switchTo().alert().accept();
				Thread.sleep(4000);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mydriver.navigate().refresh();

			} else {
				System.out.println(
						"There are(is) no certification in-progress on the dashboard, a new certification is beinng created");
				Thread.sleep(7000);
			}
			// Locate the Certifications on the dashboard, click on it and
			// select EDWOSB to continue.
			mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();
			mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/ul/li[2]/a")).click();
			Thread.sleep(2000);
			// Locate the accept button at the bottom of the EDWOSB agreement
			// and click on it to continue.

			mydriver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();

			// Locate the 8(a) question and select No and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[65][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			System.out.println("  8(a) question has been answered");
			// Locate the Third Party Certification, question1 and select No and
			// continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[66][value]']/label[2]")).click();

			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {

				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Locate the Non-qualification , question and select No and
			// continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[68][value]']/label[2]")).click();
			Thread.sleep(5000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			System.out.println("  Third Party questions have been answered");
			// Locate the Business Corporation and S-Corp(Stocks) question
			// 1,2and2, and select N/A and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[69][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_69_comment']")).sendKeys(comment);
			mydriver.findElement(By.xpath(".//*[@id='answers[70][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[71][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {

				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			System.out.println("  Business questions have been answered");
			// Locate the Corporation Ownership, question 1, and select No and
			// continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[73][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Ownership & Control, question and select No and
			// continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[75][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_75_comment']")).sendKeys(comment);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Citizenship & Ownership, question 1and2, and select No
			// and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[80][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[81][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Businesses & Trusts, questions and select No for both
			// and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[82][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[83][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Operations & Management, questions, and select No for
			// both and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[84][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[85][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Expertise & Employment, questions and select No for
			// both and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[86][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[87][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			System.out.println("  Operations questions have been answered");
			// Locate the Highest Officer & Control questions, and select No for
			// both and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[88][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[89][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the SBA Exam & Daily Operations questions, and select No
			// for both and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[90][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[91][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_91_comment']")).sendKeys(comment);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Net Worth questions, and select No for both and
			// continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[92][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[93][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Adjusted Gross Income questions, and select No for
			// both and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[94][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[95][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Fair Market Value questions, and select No and
			// continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[96][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			// Locate the Assets questions, and select No for both and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[97][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[98][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			System.out.println("Step 6 - EDWOSB application questions have been answered");
			// Locate the EDWOSB 2413 and 414 Application, Form413 and click on
			// 'Add New Person' button at the bottom of the personal
			// information.
			mydriver.findElement(By.xpath(".//*[@id='answers_99_value_new_button']")).click();
			Thread.sleep(2000);
			// Verify that the section to Create new record is been seen by user
			// and enter new record to ADD.
			if (mydriver.getPageSource().contains("Create new record")) {
				System.out.println("the page to Create and Add new Record is Present, PASS");
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys(FN);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys(LN);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys(SSN);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys(Address);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys(City);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys(State);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys(Zip);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys(Country);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys(Home_Phone);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys(Business_Phone);
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys(Email);
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				// Select No for question Is anyone listed above divorced? If
				// yes, please provide separation documents.
				mydriver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();

				// Locate the Continue Button and click on it to continue.
				Thread.sleep(3000);

				Thread.sleep(3000);
				if (mydriver.getPageSource().contains("Save and continue")) {
					mydriver.findElement(By.name("commit")).click();
					System.out.println("The continue button was successfully updated to Save and continue Pass");

				} else {
					System.out.println("The continue button was Not successfully updated to Save and continue Failed");
					mydriver.quit();
				}
				Thread.sleep(3000);
			} else {
				System.out.println("the page to Create and Add new Record is NoT Present, Failed");
			}

			/////////
			Thread.sleep(3000);
			// Locate section for 'Cash on Hand' enter all valid data as
			// required.
			// Locate the As of Date: Search box for user and enter the
			// information as required.
			mydriver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE);
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

			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

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
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Locate and NO for question 'Do you have any notes receivable from
			// others? and continue'.
			mydriver.findElement(By.xpath(".//*[@id='answers[40][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000); // Select NO the Retirement Account question one
								// and two and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Select NO for the two question on life insurance.
			mydriver.findElement(By.xpath(".//*[@id='answers[43][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_45_value']")).sendKeys(Balance);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000); // Select No for the Stock and Bonds Section.
			mydriver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Select Yes for Real Estate - Primary Residence Section questions.
			mydriver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[1]")).click();
			Thread.sleep(4000);
			mydriver.findElement(By.id("answers_48_2_1_value")).sendKeys(P_Resident);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][2][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_3_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_4_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][5][value]']/label[1]")).click();

			// mydriver.findElement(By.xpath(".//*[@id='answers_48_2_5_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_6_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_7_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][8][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_9_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][10][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_11_value']")).sendKeys(Percentage);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Select Yes for Real Estate - Other section.
			mydriver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[1]")).click();

			mydriver.findElement(By.xpath(".//*[@id='add_real_estate']")).click();

			Thread.sleep(3000);
			mydriver.findElement(By.xpath(".//*[@id='answers_50_2_1_value']")).sendKeys(P_Resident);
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][2][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][4][value]']/label[2]")).click();

			// mydriver.findElement(By.xpath(".//*[@id='answers_50_2_5_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_50_2_6_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_50_2_7_value']")).sendKeys(Percentage);
			// Select yes for the last two question.
			Thread.sleep(2000);
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][8][value]']/label[1]")).click();
			mydriver.findElement(By.id("answers_50_2_9_value")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][10][value]']/label[1]")).click();
			mydriver.findElement(By.id("answers_50_2_11_value")).sendKeys(Percentage);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			Thread.sleep(3000);
			// Beginning Test For Personal Property.

			if (mydriver.getPageSource().contains("Do you own any automobiles")) {
				System.out.println("User is being navigated to the Personal Property section, PASS");
				mydriver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[1]")).click();
				// Locate the New button on the data table and click on it to
				// add information.
				mydriver.findElement(By.xpath(".//*[@id='answers_51_details_table_wrapper']/div/a[1]")).click();
				// Locate current value search box and enter a valid value for
				// you automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_value']")).sendKeys(Current_Value);
				// Locate the Loan Balance search box and enter a valid balance.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_loan_balance']")).sendKeys(Loan_Balance);
				// Locate the description of asset search box and enter the
				// make,model and year of your automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_asset_description']")).sendKeys(Make_Model_Year);
				// Locate the payment amount search box and enter the annual
				// payment amount for the automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_payment_amount']")).sendKeys(Payment_Amoun);
				// Locate the Create button and click on it.
				Thread.sleep(3000);
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select Yes for question; 'Does any of the above listed
				// property is pledged as security?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[1]")).click();

				// Locate the new button on the data table and click on it to
				// enter data.
				mydriver.findElement(By.xpath(".//*[@id='answers_52_details_table_wrapper']/div/a[1]/span")).click();
				// Locate the Name of lien Holder search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_holder_name']")).sendKeys(Name_of_Lien_Holder);
				// Locate the amount of lien search box and enter a valid
				// amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_amount']")).sendKeys(Amount_of_Lien);
				// Locate the Terms of Payment search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_terms']")).sendKeys(Terms_of_Payment);
				Thread.sleep(3000);
				// Locate the Create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select yes for question; 'Are any leans delinquent?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers_53_comment']")).sendKeys(comment);
				Thread.sleep(3000);
				// Select yes for question; 'Do you own any other personal
				// property or assets?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[54][value]']/label[1]")).click();
				// Locate the new button on the data table and click on it to
				// add another data for personal property.
				mydriver.findElement(By.xpath(".//*[@id='answers_54_details_table_wrapper']/div/a[1]")).click();
				Thread.sleep(3000);
				// Locate current value search box and enter a valid value for
				// you automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_value']")).sendKeys(Current_Value);
				// Locate the Loan Balance search box and enter a valid balance.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_loan_balance']")).sendKeys(Loan_Balance);
				// Locate the description of asset search box and enter the
				// make,model and year of your automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_asset_description']")).sendKeys(Make_Model_Year);
				// Locate the payment amount search box and enter the annual
				// payment amount for the automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_payment_amount']")).sendKeys(Payment_Amoun);
				// Locate the Create button and click on it.
				Thread.sleep(3000);
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select Yes for question; 'Does any of the above listed
				// property is pledged as security?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[55][value]']/label[1]")).click();
				// Locate the new button on the data table and click on it to
				// enter data.
				mydriver.findElement(By.xpath(".//*[@id='answers_55_details_table_wrapper']/div/a[1]")).click();
				// Locate the Name of lien Holder search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_holder_name']")).sendKeys(Name_of_Lien_Holder);
				// Locate the amount of lien search box and enter a valid
				// amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_amount']")).sendKeys(Amount_of_Lien);
				// Locate the Terms of Payment search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_terms']")).sendKeys(Terms_of_Payment);
				Thread.sleep(3000);
				// Locate the Create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select yes for question; 'Are any leans delinquent?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers_56_comment']")).sendKeys(comment);
				Thread.sleep(3000);
				// Locate the continue button and click on it.
				Thread.sleep(3000);
				if (mydriver.getPageSource().contains("Save and continue")) {
					mydriver.findElement(By.name("commit")).click();
					System.out.println("The continue button was successfully updated to Save and continue Pass");

				} else {
					System.out.println("The continue button was Not successfully updated to Save and continue Failed");
					mydriver.quit();
				}
				Thread.sleep(3000);

			} else {
				System.out.println("The personal Property Section is not Present, Failed");
			}
			// Verify that User navigate to the Next section of 'Notes Payable'
			// in form 413 successfully.
			if (mydriver.getPageSource().contains("Notes Payable")) {
				System.out.println("User is being navigated to the 'Notes Payable' section on the form 413, Pass");
				mydriver.findElement(By.xpath(".//*[@id='answers[57][value]']/label[2]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers[58][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers_59_value']")).sendKeys(Percentage);
				Thread.sleep(3000);
				if (mydriver.getPageSource().contains("Save and continue")) {
					mydriver.findElement(By.name("commit")).click();
					System.out.println("The continue button was successfully updated to Save and continue Pass");

				} else {
					System.out.println("The continue button was Not successfully updated to Save and continue Failed");
					mydriver.quit();
				}
				Thread.sleep(3000);
			} else {
				System.out.println("User is NOT being navigated to the 'Notes Payable' section on the form 413, Pass");
			}
			Thread.sleep(3000);

			// US660 'Assessed Taxes' test begin here.
			System.out.println("US660 'Assessed Taxes' test begin here");

			// Verify that user successfully navigated to the section 'Assessed
			// Taxes' on form 413.
			if (mydriver.getPageSource().contains("Assessed Taxes")) {
				System.out.println("User successfully navigated to the section 'Assessed Taxes' on Form 413, Pass");
				// Verify and answer YES to question 'Do you have any Assessed
				// Taxes that were unpaid?'.
				if (mydriver.getPageSource().contains("Do you have any Assessed Taxes that were unpaid")) {
					System.out.println(
							"Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' is present, Pass");
					mydriver.findElement(By.xpath(".//*[@id='answers[60][value]']/label[1]")).click();
					Thread.sleep(3000);
				} else {
					System.out.println(
							"Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
				}
			} else {
				System.out.println(
						"User did Not successfully navigated to the section 'Assessed Taxes' on Form 413, Failed");
			}
			// Verify data table and complete it if taxes were unpaid.
			if (mydriver.getPageSource().contains("Include only assessed taxes that are unpaid")) {
				System.out.println("Assessed taxes data tabl is present, Pass");
				mydriver.findElement(By.xpath(".//*[@id='answers_60_details_table_wrapper']/div/a[1]/span")).click();
				// Locate the whom payable search box and enter a valid data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_whom_payable']")).sendKeys(Whom_Payable);
				// Locate the amount search box and enter a valid amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_amount']")).sendKeys(Payment_Amoun);
				// Locate the when due search box, clear existing data and enter
				// a valid date.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_when_due']")).clear();
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_when_due']")).sendKeys(When_Due);
				// Locate the property search box and enter the type of property
				// the tax lien attaches to.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_property_tax_lien']")).sendKeys(Col_Type);
				// locate the create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

			} else {
				System.out.println(
						"Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
			}
			Thread.sleep(3000);
			// Verify and select YES for the next assessed taxes section
			// question; 'Do you have any other liabilities?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();

			if (mydriver.getPageSource()
					.contains("Complete this section only if liabilities were not listed elsewhere")) {
				System.out.println(
						"Assessed taxes data tabl is present for question 'Do you have any other liabilities?', Pass");
				mydriver.findElement(By.xpath(".//*[@id='answers_61_details_table_wrapper']/div/a[1]/span")).click();
				// Locate the Liability search box and enter a valid data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_liability']")).sendKeys(Payment_Amoun);
				// Locate the Amount Owned search box and enter a valid amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_amount_owed']")).sendKeys(Payment_Amoun);
				// Locate the Description search box and enter a valid date.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_description']")).sendKeys(Col_Type);
				// locate the create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

			} else {
				System.out.println("Assessed Taxes question; 'Do you have any other liabilities?' NOT Present, Failed");
			}
			Thread.sleep(3000);

			// Locate and click on the continue button.
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

		}

		Thread.sleep(4000);
		// Locate the next 3 search boxes for Adjusted Gross Income and enter
		// valid data.
		mydriver.findElement(By.xpath(".//*[@id='answers_62_value']")).sendKeys(OB);
		mydriver.findElement(By.xpath(".//*[@id='answers_63_value']")).sendKeys(CB);
		mydriver.findElement(By.xpath(".//*[@id='answers_64_value']")).sendKeys(PAc_a);
		// Locate and click on the continue button.
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Save and continue")) {
			mydriver.findElement(By.name("commit")).click();
			System.out.println("The continue button was successfully updated to Save and continue Pass");

		} else {
			System.out.println("The continue button was Not successfully updated to Save and continue Failed");
			mydriver.quit();
		}
		Thread.sleep(3000);
		Thread.sleep(4000);
		// Verify that user is being navigated to the Summary Page.
		if (mydriver.getPageSource().contains("Financial Summary")) {
			System.out.println("User successfully navigated to the Summary Page on form413, Pass");
		} else {
			System.out.println("User Did Not successfully navigated to the Summary Page on form413, Failed");
			mydriver.quit();
		}

		// Locate and click on the continue button.
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Save and continue")) {
			mydriver.findElement(By.name("commit")).click();
			System.out.println("The continue button was successfully updated to Save and continue Pass");

		} else {
			System.out.println("The continue button was Not successfully updated to Save and continue Failed");
			mydriver.quit();
		}
		Thread.sleep(3000);
		Thread.sleep(5000);
		System.out.println("Detail test for the Privacy Statements section for Paul Washington on form413 begins here");

		// Verify that user is being navigated to the Privacy Statements Page.
		if (mydriver.getPageSource().contains("Privacy Statements")) {
			System.out
					.println("Pual Washington successfully navigated to the Privacy Statements Page on form413, Pass");

		} else {
			System.out.println(
					"Pual Washington Did Not successfully navigated to the Privacy Statements Page on form413, Failed");
			mydriver.quit();
		}
		//////////

		// Verify that the Privacy Statements is present.
		WebElement Element1 = (mydriver.findElement(By.xpath(".//*[@id='edwosb']/ul/div[2]/div")));
		System.out.println(Element1.getText());
		Thread.sleep(4000);
		// Go back to form413 and add another person and verify if user1 is
		// prompted back to complete the financial statements for the second
		// person upon arriving on the privacy statement section.
		mydriver.findElement(By.xpath(".//*[@id='form413']")).click();
		// Locate the add new person button and click on it.
		mydriver.findElement(By.id("answers_99_value_new_button")).click();

		Thread.sleep(2000);
		// Verify that the section to Create new record is been seen by user and
		// enter new record to ADD.
		if (mydriver.getPageSource().contains("Create new record")) {
			System.out.println("-the page to Create and Add new Record is Present, PASS");
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys(FN2);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys(LN);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys(SSN);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys(Address);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys(City);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys(State);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys(Zip);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys(Country);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys(Home_Phone);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys(Business_Phone);
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys(Email);
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

			// Locate the Continue Button and click on it to continue.
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

		} else {
			System.out.println("-the page to Create and Add new Record is Not Present, Failed");

		}
		// Verify that user1 is being navigated to the section; 'Cash on Hand'
		// on Form413.
		if (mydriver.getPageSource().contains("Cash On Hand")) {
			System.out.println("-User2 successfully navigated to the cash on hand section on form413, Pass");
		} else {
			System.out.println("-User2 Did Not successfully navigated to the cash on hand section on form413, Failed");
		}
		// Go back to the form413 and verify that data entered by user2 are
		// being saved and click on the continue button.
		mydriver.findElement(By.xpath(".//*[@id='form413']")).click();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Danzel")) {
			System.out.println("-User2 Personal Information entered on form413 are being successfully saved, Pass");
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		} else {
			System.out
					.println("-User2 Personal Information entered on form413 are Not being successfully saved, Failed");
		}
		// Click on the personal summary section for Pual Washington.
		mydriver.findElement(By.xpath(".//*[@id='personal_summary_pual_washington']")).click();
		// Click on the continue.
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Verify that user is being navigated to the Privacy Statements Page.
		if (mydriver.getPageSource().contains("Privacy Statements")) {
			System.out
					.println("-Pual Washington successfully navigated to the Privacy Statements Page on form413, Pass");

		} else {
			System.out.println(
					"-Pual Washington Did Not successfully navigated to the Privacy Statements Page on form413, Failed");
			mydriver.quit();
		}
		// Click on the continue button and verify if Pual Washington is being
		// navigated back for Danzel Washington to complete his financial
		// information.
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Verify that Danzel Washington is being prompted to enter His
		// Financial information.
		if (mydriver.getPageSource().contains("Danzel Washington")) {
			System.out.println("-Danzel Washington is being prompted to enter his financial information, Pass");
		} else {
			System.out.println("-Danzel Washington is Not being prompted to enter his financial information, Failed");
			mydriver.quit();
		}
		Thread.sleep(3000);
		// Verify that Danzel navigates to the Cash on hand section.
		if (mydriver.getPageSource().contains("Cash On Hand")) {
			System.out
					.println("-Danzel Washington Successfully navigated to the Cash On Hand Section on Form413, Pass");

			Thread.sleep(3000);
			// Locate section for 'Cash on Hand' enter all valid data as
			// required.
			// Locate the As of Date: Search box for user and enter the
			// information as required.
			mydriver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE);
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
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

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
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Locate and NO for question 'Do you have any notes receivable from
			// others? and continue'.
			mydriver.findElement(By.xpath(".//*[@id='answers[40][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000); // Select NO the Retirement Account question one
								// and two and continue.
			mydriver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Select NO for the two question on life insurance.
			mydriver.findElement(By.xpath(".//*[@id='answers[43][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_45_value']")).sendKeys(Balance);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000); // Select No for the Stock and Bonds Section.
			mydriver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[2]")).click();
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000); // Select Yes for Real Estate - Primary
								// Residence Section questions.
			mydriver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[1]")).click();
			Thread.sleep(4000);
			mydriver.findElement(By.id("answers_48_2_1_value")).sendKeys(P_Resident);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][2][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_3_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_4_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][5][value]']/label[1]")).click();

			// mydriver.findElement(By.xpath(".//*[@id='answers_48_2_5_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_6_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_7_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][8][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_9_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[48][2][10][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_48_2_11_value']")).sendKeys(Percentage);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000); // Select Yes for Real Estate - Other section.
			mydriver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[1]")).click();

			mydriver.findElement(By.xpath(".//*[@id='add_real_estate']")).click();

			Thread.sleep(3000);
			mydriver.findElement(By.xpath(".//*[@id='answers_50_2_1_value']")).sendKeys(P_Resident);
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][2][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][4][value]']/label[2]")).click();

			// mydriver.findElement(By.xpath(".//*[@id='answers_50_2_5_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_50_2_6_value']")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers_50_2_7_value']")).sendKeys(Percentage);
			// Select yes for the last two question.
			Thread.sleep(2000);
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][8][value]']/label[1]")).click();
			mydriver.findElement(By.id("answers_50_2_9_value")).sendKeys(Percentage);
			mydriver.findElement(By.xpath(".//*[@id='answers[50][2][10][value]']/label[1]")).click();
			mydriver.findElement(By.id("answers_50_2_11_value")).sendKeys(Percentage);

			Thread.sleep(3000);
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			Thread.sleep(3000);
			// Beginning Test For Personal Property.
			// Verify and Select Yes for the quetion; 'Do you own any
			// automobiles?'.
			if (mydriver.getPageSource().contains("Do you own any automobiles")) {
				System.out.println("-User is being navigated to the Personal Property section, PASS");
				mydriver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[1]")).click();
				// Locate the New button on the data table and click on it to
				// add information.
				mydriver.findElement(By.xpath(".//*[@id='answers_51_details_table_wrapper']/div/a[1]")).click();
				// Locate current value search box and enter a valid value for
				// you automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_value']")).sendKeys(Current_Value);
				// Locate the Loan Balance search box and enter a valid balance.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_loan_balance']")).sendKeys(Loan_Balance);
				// Locate the description of asset search box and enter the
				// make,model and year of your automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_asset_description']")).sendKeys(Make_Model_Year);
				// Locate the payment amount search box and enter the annual
				// payment amount for the automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_payment_amount']")).sendKeys(Payment_Amoun);
				// Locate the Create button and click on it.
				Thread.sleep(3000);
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select Yes for question; 'Does any of the above listed
				// property is pledged as security?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[1]")).click();

				// Locate the new button on the data table and click on it to
				// enter data.
				mydriver.findElement(By.xpath(".//*[@id='answers_52_details_table_wrapper']/div/a[1]/span")).click();
				// Locate the Name of lien Holder search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_holder_name']")).sendKeys(Name_of_Lien_Holder);
				// Locate the amount of lien search box and enter a valid
				// amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_amount']")).sendKeys(Amount_of_Lien);
				// Locate the Terms of Payment search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_terms']")).sendKeys(Terms_of_Payment);
				Thread.sleep(3000);
				// Locate the Create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select yes for question; 'Are any leans delinquent?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers_53_comment']")).sendKeys(comment);
				Thread.sleep(3000);
				// Select yes for question; 'Do you own any other personal
				// property or assets?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[54][value]']/label[1]")).click();
				// Locate the new button on the data table and click on it to
				// add another data for personal property.
				mydriver.findElement(By.xpath(".//*[@id='answers_54_details_table_wrapper']/div/a[1]")).click();
				Thread.sleep(3000);
				// Locate current value search box and enter a valid value for
				// you automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_value']")).sendKeys(Current_Value);
				// Locate the Loan Balance search box and enter a valid balance.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_loan_balance']")).sendKeys(Loan_Balance);
				// Locate the description of asset search box and enter the
				// make,model and year of your automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_asset_description']")).sendKeys(Make_Model_Year);
				// Locate the payment amount search box and enter the annual
				// payment amount for the automobile.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_payment_amount']")).sendKeys(Payment_Amoun);
				// Locate the Create button and click on it.
				Thread.sleep(3000);
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select Yes for question; 'Does any of the above listed
				// property is pledged as security?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[55][value]']/label[1]")).click();
				// Locate the new button on the data table and click on it to
				// enter data.
				mydriver.findElement(By.xpath(".//*[@id='answers_55_details_table_wrapper']/div/a[1]")).click();
				// Locate the Name of lien Holder search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_holder_name']")).sendKeys(Name_of_Lien_Holder);
				// Locate the amount of lien search box and enter a valid
				// amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_amount']")).sendKeys(Amount_of_Lien);
				// Locate the Terms of Payment search box and enter a valid
				// data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_terms']")).sendKeys(Terms_of_Payment);
				Thread.sleep(3000);
				// Locate the Create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
				Thread.sleep(3000);
				// Select yes for question; 'Are any leans delinquent?'.
				mydriver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers_56_comment']")).sendKeys(comment);
				Thread.sleep(3000);
				// Locate the continue button and click on it.
				Thread.sleep(3000);
				if (mydriver.getPageSource().contains("Save and continue")) {
					mydriver.findElement(By.name("commit")).click();
					System.out.println("The continue button was successfully updated to Save and continue Pass");

				} else {
					System.out.println("The continue button was Not successfully updated to Save and continue Failed");
					mydriver.quit();
				}
				Thread.sleep(3000);
				Thread.sleep(3000);
			} else {
				System.out.println("-The personal Property Section is not Present, Failed");
			}
			// Verify that User navigate to the Next section of 'Notes Payable'
			// in form 413 successfully.
			if (mydriver.getPageSource().contains("Notes Payable")) {
				System.out.println("-User is being navigated to the 'Notes Payable' section on the form 413, Pass");
				mydriver.findElement(By.xpath(".//*[@id='answers[57][value]']/label[2]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers[58][value]']/label[1]")).click();
				mydriver.findElement(By.xpath(".//*[@id='answers_59_value']")).sendKeys(Percentage);
				mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			} else {
				System.out.println("-User is NOT being navigated to the 'Notes Payable' section on the form 413, Pass");
			}
			Thread.sleep(3000);

			// US660 'Assessed Taxes' test begin here.
			System.out.println("US660 'Assessed Taxes' test begin here");

			// Verify that user successfully navigated to the section 'Assessed
			// Taxes' on form 413.
			if (mydriver.getPageSource().contains("Assessed Taxes")) {
				System.out.println("-User successfully navigated to the section 'Assessed Taxes' on Form 413, Pass");
				// Verify and answer YES to question 'Do you have any Assessed
				// Taxes that were unpaid?'.
				if (mydriver.getPageSource().contains("Do you have any Assessed Taxes that were unpaid")) {
					System.out.println(
							"Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' is present, Pass");
					mydriver.findElement(By.xpath(".//*[@id='answers[60][value]']/label[1]")).click();
					Thread.sleep(3000);
				} else {
					System.out.println(
							"-Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
				}
			} else {
				System.out.println(
						"User did Not successfully navigated to the section 'Assessed Taxes' on Form 413, Failed");
			}
			// Verify data table and complete it if taxes were unpaid.
			if (mydriver.getPageSource().contains("Include only assessed taxes that are unpaid")) {
				System.out.println("Assessed taxes data tabl is present, Pass");
				mydriver.findElement(By.xpath(".//*[@id='answers_60_details_table_wrapper']/div/a[1]/span")).click();
				// Locate the whom payable search box and enter a valid data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_whom_payable']")).sendKeys(Whom_Payable);
				// Locate the amount search box and enter a valid amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_amount']")).sendKeys(Payment_Amoun);
				// Locate the when due search box, clear existing data and enter
				// a valid date.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_when_due']")).clear();
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_when_due']")).sendKeys(When_Due);
				// Locate the property search box and enter the type of property
				// the tax lien attaches to.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_property_tax_lien']")).sendKeys(Col_Type);
				// locate the create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

			} else {
				System.out.println(
						"-Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
			}
			Thread.sleep(3000);
			// Verify and select YES for the next assessed taxes section
			// question; 'Do you have any other liabilities?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();

			if (mydriver.getPageSource()
					.contains("Complete this section only if liabilities were not listed elsewhere")) {
				System.out.println(
						"-Assessed taxes data tabl is present for question 'Do you have any other liabilities?', Pass");
				mydriver.findElement(By.xpath(".//*[@id='answers_61_details_table_wrapper']/div/a[1]/span")).click();
				// Locate the Liability search box and enter a valid data.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_liability']")).sendKeys(Payment_Amoun);
				// Locate the Amount Owned search box and enter a valid amount.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_amount_owed']")).sendKeys(Payment_Amoun);
				// Locate the Description search box and enter a valid date.
				mydriver.findElement(By.xpath(".//*[@id='DTE_Field_description']")).sendKeys(Col_Type);
				// locate the create button and click on it.
				mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

			} else {
				System.out
						.println("-Assessed Taxes question; 'Do you have any other liabilities?' NOT Present, Failed");
			}
			Thread.sleep(3000);

			// Locate and click on the continue button.
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);

			Thread.sleep(3000);
			// Verify that User is being navigated to the 'Adjusted Gross
			// Income' section of form 413.
			if (mydriver.getPageSource().contains("As shown on tax returns for Most Recent tax year")) {
				System.out
						.println("-User navigated to the Adjusted Gross Income section of form 413 seccessfully, Pass");

				Thread.sleep(4000);
				// Locate the next 3 search boxes for Adjusted Gross Income and
				// enter valid data.
				mydriver.findElement(By.xpath(".//*[@id='answers_62_value']")).sendKeys(OB);
				mydriver.findElement(By.xpath(".//*[@id='answers_63_value']")).sendKeys(CB);
				mydriver.findElement(By.xpath(".//*[@id='answers_64_value']")).sendKeys(PAc_a);
				// Locate and click on the continue button.
				Thread.sleep(3000);
				if (mydriver.getPageSource().contains("Save and continue")) {
					mydriver.findElement(By.name("commit")).click();
					System.out.println("The continue button was successfully updated to Save and continue Pass");

				} else {
					System.out.println("The continue button was Not successfully updated to Save and continue Failed");
					mydriver.quit();
				}
				Thread.sleep(3000);
			} else {
				System.out.println(
						"-User did not navigate to the Adjusted Gross Income section of form 413 seccessfully, Failed");
			}

			Thread.sleep(4000);
			// Verify that user is being navigated to the Summary Page.
			if (mydriver.getPageSource().contains("Financial Summary")) {
				System.out.println("-Danzel Washington successfully navigated to the Summary Page on form413, Pass");
			} else {
				System.out.println(
						"-Danzel Washington Did Not successfully navigated to the Summary Page on form413, Failed");
				mydriver.quit();
			}

			// Locate and click on the continue button.
			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("Save and continue")) {
				mydriver.findElement(By.name("commit")).click();
				System.out.println("The continue button was successfully updated to Save and continue Pass");

			} else {
				System.out.println("The continue button was Not successfully updated to Save and continue Failed");
				mydriver.quit();
			}
			Thread.sleep(3000);
			// Verify that user is being navigated to the Privacy Statements
			// Page.
			if (mydriver.getPageSource().contains("Privacy Statements")) {
				System.out.println(
						"-Danzel Washington successfully navigated to the Privacy Statements Page on form413, Pass");
			} else {
				System.out.println(
						"-Danzel Washington Did Not successfully navigated to the Privacy Statements Page on form413, Failed");
				mydriver.quit();
			}
			if (mydriver.getPageSource().contains("Danzel Washington")) {
				System.out.println(
						"-Danzel Washington successfully navigated to the Privacy Statements Page on form413, Pass");
				System.out.println(mydriver.findElement(By.xpath(".//*[@id='edwosb']/ul/div[2]/div")).getText());
			} else {
				System.out.println(
						"-Danzel Washington Did Not successfully navigated to the Privacy Statements Page on form413, Failed");
				mydriver.quit();
			}

		}

		else {
			System.out.println("-Danzel Washington is Not being prompted to enter his financial information, Failed");
			mydriver.quit();
		}
		// Locate the continue button and click on it, verify that user
		// navigates to the Review section on form413.
		Thread.sleep(3000);
		mydriver.findElement(By.name("commit")).click();
		Thread.sleep(2000);
		if (mydriver.getPageSource().contains("Review")) {
			System.out.println("User successfully navigated to the Review Page on form413, Pass");
		} else {
			System.out.println("-User Did Not successfully navigated to the Review Page on form413, Failed");
			mydriver.quit();
		}
		Thread.sleep(3000);
		// Locate the Submit button and click on it.
		mydriver.findElement(By.id("accept-button")).click();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signature")) {
			System.out.println("User successfully navigated to the Signature Pass");

		} else {
			System.out.println("User did not successfully navigated to the Signature Failed");
			mydriver.quit();
		}

		// Verify that the Accept button is present
		if (mydriver.getPageSource().contains("Accept")) {
			System.out.println("The Accept button is present and active on the signature page, Pass");
		} else {
			System.out.println("The Accept button is Not present and active on the signature page, Failed");
			mydriver.quit();
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
	public void US709_WOSB_Self_Certifies_EDWOSB_and_8_a_Users_reviews_Privacy_Statement_413_teardown()
			throws Exception {
		mydriver.quit();
	}

}
