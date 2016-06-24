package all_production_test;

import org.junit.*;

import static org.junit.Assert.*;

import java.io.File;

import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.support.ui.Select;

public class US788_Collapse_Navigation_Pane_Corp_EDWOSB {
	private WebDriver driver;
	String baseUrl;
	String NOD;
	String AOD;
	String OB;
	String CB;
	String PAc_a;
	String Col_Type;
	String Login;
	String Password;
	String comment;
	String FN;
	String FN_2;
	String LN;
	String LN_2;
	String SSN;
	String Address;
	String City;
	String State;
	String Zip;
	String Country;
	String Home_Phone;
	String Business_Phone;
	String Email;
	String Email_2;
	String agi_recent;
	String agi_previous;
	String agi_beforeprevious;
	String AS_of_DATE;
	String Cash_On_Hand;
	String Saving_Account;
	String Checking_Account;
	String AS_of_DATE_secondowner;
	String Cash_On_Hand_secondowner;
	String Saving_Account_secondowner;
	String Checking_Account_secondowner;
	String Salary;
	String Other_Income;
	String Business_Equity;
	String Equity_in_other_firm;
	String Loans_against_Life_Insurance;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		Login = "donald.patti@cedarpointconsulting.com";
		// Login="jonathan.whitley@telesishq.com";
		Password = "They call me Ishmael";
		comment = "Test";
		FN = "James";
		FN_2 = "Peter";
		LN = "Thompson";
		LN_2 = "Smith";
		SSN = "222-23-3333";
		Address = "123 Oak Street";
		City = "Alexandria";
		Zip = "22310";
		State = "VA";
		Country = "USA";
		Home_Phone = "703-555-1234";
		Business_Phone = "703-555-4321";
		Email = "test@email.com";
		Email_2 = "Peter.Smith@email.com";
		agi_recent = "100";
		agi_previous = "200";
		agi_beforeprevious = "300";
		AS_of_DATE = "01/12/2016";
		Cash_On_Hand = "80000";
		Saving_Account = "100000";
		Checking_Account = "80000";
		AS_of_DATE_secondowner = "01/12/2016";
		Cash_On_Hand_secondowner = "80000";
		Saving_Account_secondowner = "100000";
		Checking_Account_secondowner = "80000";
		Salary = "100000";
		Other_Income = "70000";
		Business_Equity = "57000";
		Equity_in_other_firm = "20000";

		// QA URL
		baseUrl = "https://certify.sba.gov/";
		// DEMO URL
		// baseUrl = "http://demo.qa.sba-one.net/";

		// setting up FireFox Driver
		// driver = new FirefoxDriver();

		// setting up Chrome Driver
		// String exePath = "C:\\Users\\Jonathan
		// Whitley\\Documents\\NEW-SBAONE-SCRIPTS\\SBAONE_AUTOMATION\\src\\library\\chromedriver.exe";
		// System.setProperty("webdriver.chrome.driver", exePath);
		// driver=new ChromeDriver();

		// setting up IE Driver

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void testSmokeLogin() throws Exception {
		// Step 1 - Open QA site
		driver.get(baseUrl);
		System.out.println("Step 1 - URL: " + baseUrl);

		// Click on the Login link and log in
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();

		// Enter a valid Login ID
		driver.findElement(By.xpath(".//*[@id='user_email']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Login);
		System.out.println("  Login ID: " + Login);

		// Enter a valid Password
		driver.findElement(By.xpath(".//*[@id='user_password']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Password);
		System.out.println("  Password: " + Password);

		// Click the Sign-in button
		driver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		System.out.println("  Click on Login Link and log in");

		// Close pop up window
		Thread.sleep(3000);
		if (driver.getPageSource().contains("Signed in successfully")) {
			driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("  Successful sign in alert message not present");
		}

		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		if (driver.getPageSource().contains("Draft")) {
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr[1]/td[4]/a[2]"))
					.click();
			System.out.println("Certification in-progress on the dashboard was deleted");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			Thread.sleep(4000);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.navigate().refresh();
		} else {
			System.out.println(
					"There are(is) no certification in-progress on the dashboard, a new certification is being created");
		}
		Thread.sleep(4000);

		// Step 2 - Answer questions for EDWOSB application
		// Locate the Certifications on the dashboard, click on it and select
		// EDWOSB to continue.
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		// Locate the accept button at the bottom of the EDWOSB agreement and
		// click on it to continue.
		driver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
		Thread.sleep(2000);
		// Locate the 8(a) question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[65][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  8(a) question have been answered");
		// Locate the Third Party Certification, question1 and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[66][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Third Party Certification, question3 and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[68][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Third Party questions have been answered");

		// Locate the Business type LLC question 1and2, and select Yes and
		// attached a document.
		driver.findElement(By.xpath(".//*[@id='answers[78][value]']/label[1]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[18][attachment]']/div/label/div")).click();
		driver.findElement(By.xpath(".//*[@id='70832']/td[1]/input")).click();
		driver.findElement(By.xpath(".//*[@id='document_library_attach']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='answers[79][value]']/label[1]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[19][attachment]']/div/label/div")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//input[@name='selector'])[9]")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[@id='document_library_attach'])[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("commit")).click();
		System.out.println("Business questions have been answered");
		System.out.println("  Business questions have been answered");

		// Locate the Operations Part1, question 1and2, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[80][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[81][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Operations Part2, question 3and4, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[82][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[83][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Operations Part3, question 5and6, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[84][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[85][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Operations Part4, question 7and8, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[86][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[87][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Operations Part5, question 9and10, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[88][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[89][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Operations Part6, question 11and12, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[90][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[91][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_91_comment']")).sendKeys(comment);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Operations questions have been answered");
		// Locate the EDWOSB Section1 questions, and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[92][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[93][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the EDWOSB Section2 questions, and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[94][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[95][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the EDWOSB Section3 questions, and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[96][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the EDWOSB Section4 questions, and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[97][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[98][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("Step 2 - EDWOSB application questions have been answered");

		// Step 3 - Create an Owner and fill out the Form 413 pages until the
		// Stocks & Bonds window
		// Locate the EDWOSB 2413 and 414 Application, Form413 and click on 'Add
		// New Person' button at the bottom of the personal information.
		driver.findElement(By.xpath(".//*[@id='answers_99_value_new_button']")).click();
		Thread.sleep(2000);

		// Verify that the section to Create new record is been seen by user and
		// enter new record to ADD.
		System.out.println("Step 3 - The page to Create and Add new Record");
		if (driver.getPageSource().contains("Create new record")) {
			driver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys(FN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys(LN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys(SSN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys(Address);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys(City);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys(State);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys(Zip);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys(Country);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys(Home_Phone);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys(Business_Phone);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys(Email);
			driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			System.out.println("  The page to Create and Add new Record is present - Pass");
		} else {
			System.out.println("  The page to Create and Add new Record is present - <<FAILED>>");
		}
		Thread.sleep(2000);

		// Step 4 - Create a second owner record
		// Locate the EDWOSB 2413 and 414 Application, Form413 and click on 'Add
		// New Person' button at the bottom of the personal information.
		System.out.println("Step 4 - Create a second owner record");
		driver.findElement(By.xpath(".//*[@id='answers_99_value_new_button']")).click();
		Thread.sleep(2000);

		// Verify that the section to Create new record is been seen by user and
		// enter new record to ADD.
		if (driver.getPageSource().contains("Create new record")) {
			driver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys(FN_2);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys(LN_2);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys(SSN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys(Address);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys(City);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys(State);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys(Zip);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys(Country);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys(Home_Phone);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys(Business_Phone);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys(Email_2);
			driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			System.out.println("  The page to Create and Add new Record is present - Pass");
			Thread.sleep(3000);
			// Select No for question Is anyone listed above divorced? If yes,
			// please provide separation documents.
			driver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();
		} else {
			System.out.println("  The page to Create and Add new Record is present - <<FAILED>>");
		}
		// Locate the Continue Button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);

		// Locate section for Cash on Hand enter all valid data as required.
		// Locate the As of Date: Search box for user and enter the information
		// as required.
		driver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE);
		// Locate the Cash on Hand Search box for WWW eee user and enter the
		// information as required.
		driver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys(Cash_On_Hand);
		// Locate the Savings Account(s) Balance Search box for WWW eee user and
		// enter the information as required.
		driver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys(Saving_Account);
		// Locate the Checking Account(s) Balance Search box for WWW eee user
		// and enter the information as required.
		driver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys(Checking_Account);
		// Locate the Continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Cash on Hand questions have been answered");
		Thread.sleep(3000);

		// Locate section for Other Source of Income enter all valid data as
		// required.
		// Locate the Salary search box and enter salary.
		driver.findElement(By.xpath(".//*[@id='answers_36_value']")).sendKeys(Salary);
		// Locate the Other Income search box and enter Other Income.
		driver.findElement(By.xpath(".//*[@id='answers_37_value']")).sendKeys(Other_Income);
		// Locate the applicant Business Type and enter amount of applicant
		// equity.
		driver.findElement(By.xpath(".//*[@id='answers_38_value']")).sendKeys(Business_Equity);
		// Locate the applicant equity in other firm and enter applicant
		// business equity.
		driver.findElement(By.xpath(".//*[@id='answers_39_value']")).sendKeys(Equity_in_other_firm);
		// Locate the continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Other Source of Income questions have been answered");

		// Verify that users is navigated to the right page for EDWOSB 2413 and
		// 414 Application >> Form413 >> James Thompson, Verify question and
		// select YES OR NO to the question as apply to user and continue.
		if (driver.getPageSource().contains("Notes Receivable")) {
			System.out
					.println("User is being navigated to the financial information(413), Notes Receivable page, PASS");

		} else {
			System.out.println(
					"User is not being navigated to the financial information(413), Notes Receivable page, <<FAILED>>");
			Thread.sleep(2000);
			driver.quit();

		}
		Thread.sleep(2000);

		// Select No for Notes Receivable Question.
		driver.findElement(By.xpath(".//*[@id='answers[40][value]']/label[2]")).click();
		// Locate the continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Notes Receivable question has been answered");

		// Verify that user is navigated to the Correct section(Retirement
		// Accounts).
		if (driver.getPageSource().contains("Retirement Accounts")) {
			System.out.println("User is navigated to the Correct section, (Retirement Accounts), PASS");
		} else {
			System.out.println("User is NOT being navigated to the Correct section, (Retirement Accounts), <<FAILED>>");
			driver.quit();
		}
		// Locate the Retirement Accounts questions 1 and 2, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Retirement Accounts application questions have been answered");

		// Verify that user is navigated to the Correct section(Life Insurance).
		if (driver.getPageSource().contains("Life Insurance")) {
			System.out.println("User is navigated to the Correct section, (Life Insurance), PASS");
		} else {
			System.out.println("User is NOT being navigated to the Correct section, (Life Insurance), <<FAILDED>>");
			driver.quit();
		}
		// Locate the Life Insurance questions 1, 2, 3, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[43][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Life Insurance application questions have been answered");

		// Step 5 - Verify that user is navigated to the Correct section(Stocks
		// & Bonds).
		System.out.println("Step 5 - Verify that user is navigated to the Correct section(Stocks & Bonds)");
		try {
			assertEquals("Stocks & Bonds", driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  User is navigated to the Correct section, (Stocks & Bonds) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  User is NOT being navigated to the Correct section, (Stocks & Bonds) - <<FAILED>>");
			driver.quit();
		}

		// Step 6 - Locate the Stocks & Bonds questions, select No
		System.out.println("Step 6 - Locate the Stocks & Bonds questions, select No");
		driver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 7 - Click No for the Real Estate Primary question
		System.out.println("Step 7 - Click No for the Real Estate Primary question	");
		driver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 8 - Click No for the Real Estate Other question
		System.out.println("Step 8 - Click No for the Real Estate Other question");
		driver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 9 - Click No for the Personal Property questions
		System.out.println("Step 9 - Click No for the Personal Property questions");
		driver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[54][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[55][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 10 - Click No for the Notes Payable questions
		System.out.println("Step 10 - Click No for the Notes Payable questions");
		driver.findElement(By.xpath(".//*[@id='answers[57][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[58][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 11 - Click No for the Assessed Taxes questions
		System.out.println("Step 11 - Click No for the Assessed Taxes questions");
		driver.findElement(By.xpath(".//*[@id='answers[60][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 12 - Answer questions for the Adjusted Gross Income
		System.out.println("Step 12 - Answer questions for the Adjusted Gross Income");
		driver.findElement(By.xpath(".//*[@id='answers_62_value']")).sendKeys(agi_recent);
		;
		driver.findElement(By.xpath(".//*[@id='answers_63_value']")).sendKeys(agi_previous);
		driver.findElement(By.xpath(".//*[@id='answers_64_value']")).sendKeys(agi_beforeprevious);
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 13 - Click Continue on the Summary page
		System.out.println("Step 13 - Click the Continue on the Personal Summary page");
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 14 - Click Continue on the Privacy Statement page
		System.out.println("Step 14 - Click the Continue on the Privacy Statement page");
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 15 - Enter data for second owners Cash on Hand page
		System.out.println("Step 15 - Enter data for second owners Cash on Hand page");
		// Locate section for Cash on Hand enter all valid data as required.
		// Locate the As of Date: Search box for user and enter the information
		// as required.
		driver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE_secondowner);
		// Locate the Cash on Hand Search box for WWW eee user and enter the
		// information as required.
		driver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys(Cash_On_Hand_secondowner);
		// Locate the Savings Account(s) Balance Search box for WWW eee user and
		// enter the information as required.
		driver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys(Saving_Account_secondowner);
		// Locate the Checking Account(s) Balance Search box for WWW eee user
		// and enter the information as required.
		driver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys(Checking_Account_secondowner);
		// Locate the Continue button and click on it to continue.
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 16 - Click the Dashboard link
		System.out.println("Step 16 - Returned to the Dashboard");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
		Thread.sleep(2000);

		// Return to the certification
		if (driver.getPageSource().contains("Draft")) {
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr[1]/td[4]/a[1]"))
					.click();
			System.out.println("Certification in-progress on the dashboard was clicked");
			Thread.sleep(4000);
		}

		// Step 17 - Verify that user is navigated to the Correct section(Cash
		// On Hand).
		System.out.println("Step 17 - Verify that user is navigated to the Correct section(Other Sources Of Income)");
		try {
			assertEquals("Other Sources Of Income",
					driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  User is navigated to the Correct section, (Other Sources Of Income) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println(
					"  User is NOT being navigated to the Correct section, (Other Sources Of Income) - <<FAILED>>");
			driver.quit();
		}

		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Start of US788

		System.out.println("");
		System.out.println("--Expand sections--");

		// Step 18 - Click to expand the Prior Certification section
		System.out.println("Step 18 - Click to expand the Prior Certification section");
		driver.findElement(By.xpath(".//*[@id='pc']")).click();

		// Step 19 - Verify that the Prior Certification section was expanded
		System.out.println("Step 19 - Verify that the Prior Certification section was expanded");

		try {
			assertEquals("8(a)", driver.findElement(By.xpath(".//*[@id='8a']")).getText());
			System.out.println("  Prior Certification section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Prior Certification section expanded - <<FAILED>>");
			driver.quit();
		}

		// Step 20 - Click to expand the Third Party Certification section
		System.out.println("Step 20 - Click to expand the Third Party Certification section");
		driver.findElement(By.xpath(".//*[@id='third_party_cert']")).click();

		// Step 21 - Verify that the Third Party Certification section was
		// expanded
		System.out.println("Step 21 - Verify that the Third Party Certification section was expanded");
		try {
			assertEquals("Third Party", driver.findElement(By.xpath(".//*[@id='third_party_cert_part_1']")).getText());
			System.out.println("  Third Party section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Third Party section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 22 - Click to expand the Business section
		System.out.println("Step 22 - Click to expand the Business section");
		driver.findElement(By.xpath(".//*[@id='business']")).click();

		// Step 23 - Verify that the Business section was expanded
		System.out.println("Step 23 - Verify that the Business section was expanded");
		if (driver.getPageSource().contains("LLC")) {
			System.out.println("  Business section is successfully expanded - Pass");
		} else {
			System.out.println("  Business section is Not successfully expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 24 - Click to expand the llc section
		System.out.println("Step 24 - Click to expand the Corporation section");
		driver.findElement(By.xpath(".//*[@id='corporation']")).click();

		Thread.sleep(2000);

		// Step 26 - Click to expand the Operations section
		System.out.println("Step 26 - Click to expand the Operations section");
		driver.findElement(By.xpath(".//*[@id='operations']")).click();

		// Step 27 - Verify that the Operations section was expanded
		System.out.println("Step 27 - Verify that the Operations section was expanded");
		try {
			assertEquals("Citizenship & Ownership",
					driver.findElement(By.xpath(".//*[@id='operations_part_1']")).getText());
			System.out.println("  Operations section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Operations section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 28 - Click to expand the EDWOSB Group section
		System.out.println("Step 28 - Click to expand the EDWOSB Group section");
		driver.findElement(By.xpath(".//*[@id='edwosb_section']")).click();

		// Step 29 - Verify that the EDWOSB Group section was expanded
		System.out.println("Step 29 - Verify that the EDWOSB Group section was expanded");
		try {
			assertEquals("Net Worth", driver.findElement(By.xpath(".//*[@id='edwosb_section_1']")).getText());
			System.out.println("  EDWOSB Group section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  EDWOSB Group section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		System.out.println("");
		System.out.println("--Collapse sections--");

		// Step 30 - Click to collapse the Third Party Certification section
		System.out.println("Step 30 - Click to collapse the Third Party Certification section");
		driver.findElement(By.xpath(".//*[@id='third_party_cert']")).click();

		// Step 31 - Verify that the Third Party Certification section was
		// collapsed
		System.out.println("Step 31 - Verify that the Third Party Certification section was collapsed");
		try {
			assertEquals("Third Party", driver.findElement(By.xpath(".//*[@id='third_party_cert_part_1']")).getText());
			System.out.println("  Third Party section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Third Party section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 32 - Click to collapse the Prior Certification section
		System.out.println("Step 32 - Click to collapse the Prior Certification section");
		driver.findElement(By.xpath(".//*[@id='pc']")).click();

		// Step 33 - Verify that the Prior Certification section was collapsed
		System.out.println("Step 33 - Verify that the Prior Certification section was collapsed");

		try {
			assertEquals("8(a)", driver.findElement(By.xpath(".//*[@id='8a']")).getText());
			System.out.println("  Prior Certification section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Prior Certification section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 34 - Click to collapse the Corporation section
		System.out.println("Step 34 - Click to collapse the Corporation section");
		driver.findElement(By.xpath(".//*[@id='corporation']")).click();

		// Step 35 - Verify that the llc section was collapsed
		System.out.println("Step 35 - Verify that the Corporation section was collapsed");
		try {
			assertEquals("Stocks", driver.findElement(By.xpath(".//*[@id='llc']")).getText());
			System.out.println("  llc section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  llc section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 36 - Click to collapse the Business section
		System.out.println("Step 36 - Click to collapse the Business section");
		driver.findElement(By.xpath(".//*[@id='business']")).click();

		// Step 37 - Verify that the Business section was collapsed
		System.out.println("Step 37 - Verify that the Business section was collapsed");
		try {
			assertEquals("Corporation & S-Corp",
					driver.findElement(By.xpath(".//*[@id='js-navigation-menu2']/li[1]/span")).getText());
			System.out.println("  Business section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Business section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 38 - Click to collapse the Operations section
		System.out.println("Step 38 - Click to collapse the Operations section");
		driver.findElement(By.xpath(".//*[@id='operations']")).click();

		// Step 39 - Verify that the Operations section was collapsed
		System.out.println("Step 39 - Verify that the Operations section was collapsed");
		try {
			assertEquals("Citizenship & Ownership",
					driver.findElement(By.xpath(".//*[@id='operations_part_1']")).getText());
			System.out.println("  Operations section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Operations section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 40 - Click to collapse the EDWOSB Group section
		System.out.println("Step 40 - Click to collapse the EDWOSB Group section");
		driver.findElement(By.xpath(".//*[@id='edwosb_section']")).click();

		// Step 41 - Verify that the EDWOSB Group section was collapsed
		System.out.println("Step 41 - Verify that the EDWOSB Group section was collapsed");
		try {
			assertEquals("Net Worth", driver.findElement(By.xpath(".//*[@id='edwosb_section_1']")).getText());
			System.out.println("  EDWOSB Group section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  EDWOSB Group section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 42 - Click to collapse the Financial section for James Thompson
		System.out.println("Step 42 - Click to collapse the Financial section for James Thompson");
		driver.findElement(By.xpath(".//*[@id='owners_james_thompson']")).click();

		// Step 43 - Verify that the Financial section for James Thompson was
		// collapsed
		System.out.println("Step 43 - Verify that the Financial section for James Thompson was collapsed");
		try {
			assertEquals("Cash On Hand",
					driver.findElement(By.xpath(".//*[@id='cash_on_hand_james_thompson']")).getText());
			System.out.println("  James Thompson Financial section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  James Thompson Financial section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 44 - Click to collapse the Financial section for Peter Smith
		System.out.println("Step 44 - Click to collapse the Financial section for Peter Smith");
		driver.findElement(By.xpath(".//*[@id='owners_peter_smith']")).click();

		// Step 45 - Verify that the Financial section for Peter Smith was
		// collapsed
		System.out.println("Step 45 - Verify that the Financial section for Peter Smith was collapsed");
		try {
			assertEquals("Cash On Hand",
					driver.findElement(By.xpath(".//*[@id='cash_on_hand_peter_smith']")).getText());
			System.out.println("  Peter Smith Financial section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Peter Smith Financial section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 46 - Click to expand the Financial section for James Thompson
		System.out.println("Step 46 - Click to expand the Financial section for James Thompson");
		driver.findElement(By.xpath(".//*[@id='owners_james_thompson']")).click();

		// Step 47 - Verify that the Financial section for James Thompson was
		// expanded
		System.out.println("Step 47 - Verify that the Financial section for James Thompson was expanded");
		try {
			assertEquals("Cash On Hand",
					driver.findElement(By.xpath(".//*[@id='cash_on_hand_james_thompson']")).getText());
			System.out.println("  James Thompson Financial section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  James Thompson Financial section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 48 - Click to expand the Financial section for Peter Smith
		System.out.println("Step 48 - Click to expand the Financial section for Peter Smith");
		driver.findElement(By.xpath(".//*[@id='owners_peter_smith']")).click();

		// Step 49 - Verify that the Financial section for Peter Smith was
		// expanded
		System.out.println("Step 49 - Verify that the Financial section for Peter Smith was expanded");
		try {
			assertEquals("Cash On Hand",
					driver.findElement(By.xpath(".//*[@id='cash_on_hand_peter_smith']")).getText());
			System.out.println("  Peter Smith Financial section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Peter Smith Financial section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 50 - Click the Dashboard link
		System.out.println("Step 50 - Returned to the Dashboard");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
		Thread.sleep(2000);

		// Step 51 - Click the Logout link
		System.out.println("Step 51 - Logout link clicked");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[6]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		Thread.sleep(3000);
		System.out.println("END OF TEST");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (verificationErrorString != null) {
			System.out.println("Error: " + verificationErrorString);
		}

	}

}
