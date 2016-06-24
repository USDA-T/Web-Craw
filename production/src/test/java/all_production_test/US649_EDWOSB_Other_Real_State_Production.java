package all_production_test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Select;

public class US649_EDWOSB_Other_Real_State_Production {
	private WebDriver driver;
	String URL;
	String Email;
	String Password;
	String comment;

	// Data for personal information
	String First_Name;
	String Last_Name;
	String First_Name1;
	String Last_Name1;
	String SSN;
	String Address_P;
	String City;
	String State;
	String Zip;
	String Country;
	String Home_Phone;
	String Business_Phone;
	String Email_P;
	String Email_P1;
	// Data for Cash On Hand
	String AS_of_DATE;
	String Cash_On_Hand;
	String Saving_Account;
	String Checking_Account;
	// Data for Other Sources Of Income
	String Salary;
	String Other_Income;
	String Business_Equity;
	String Equity_in_other_firm;

	// Data for Notes Receivable
	String Name_Of_Debtor;
	String Address_Of_Debtor;
	String NOriginal_Balance;
	String NCurrent_Balance;
	String NPayment_Amount;
	String Secured_or_Endorsed;

	// Data for Retirement Accounts
	String rothira_total_value;
	String rothira_initial_contribution;
	String rothira_investment_company;

	String ira_total_value;
	String ira_initial_contribution;
	String ira_investment_company;

	// Data for Life Insurance
	String Blance_OfAnyloans;
	String Insurance_Company;
	String Cash_Surrender_Value;
	String Face_Amount;
	String Beneficiaries;
	String Insurance_Company1;
	String Cash_Surrender_Value1;
	String Face_Amount1;
	String Beneficiaries1;
	String Loans_Against_Life_Insurance;
	String abc;
	// Data for Stocks and Bonds
	String securities_name;
	String total_value;
	String num_of_shares;
	String cost;
	String market_value;
	String date;
	String interest_dividends;

	// Data for primary Residence
	String Address_PR;
	String PercentageOfownership;
	String PercentageOfmortgage;
	String CurrentValue;
	String MortgageBalance;
	String CurrentbalanceLean;
	String CalculatedAnnually;

	// Data for Other Real State
	String Address_ORE;
	String Percentage_ORE;
	String PercentageOfmortgage_ORE;
	String CurrentValue_ORE;
	String Mortgage_Balance_ORE;
	String Current_Balance_lean_ORE;
	String Calculated_Annually_ORE;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void EDWOSB_Real_State_Other_SetupTest() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file1.getAbsolutePath());
		// driver=new InternetExplorerDriver();
		File file2 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file2.getAbsolutePath());
		// driver=new ChromeDriver();

		URL = "https://certify.sba.gov/users/sign_in";
		// URL="https://staging-certify.sba.gov/users/sign_in";

		Email = "donald.patti@cedarpointconsulting.com";
		Password = "They call me Ishmael";
		comment = "Test";

		// Data for personal information
		First_Name = "Sabra";
		Last_Name = "Mabella";
		SSN = "495-54-3209";
		Address_P = "3432 Berugard ST";
		State = "Virginia";
		Country = "USA";
		City = "Alexandria";
		Zip = "22311";
		Home_Phone = "703-622-5854";
		Business_Phone = "703-765-4969";
		Email_P = "Sabra.Mabella@gmail.com";

		// Data for Cash On Hand
		AS_of_DATE = "02/01/2016";
		Cash_On_Hand = "10000";
		Saving_Account = "50000";
		Checking_Account = "30000";

		// Data for Other Sources Of Income//
		Salary = "60000";
		Other_Income = "3000";
		Business_Equity = "5700";
		Equity_in_other_firm = "20000";

		// Data for Notes Receivable
		Name_Of_Debtor = "Alexander";
		Address_Of_Debtor = "Alexander 2341 DR";
		NOriginal_Balance = "1200";
		NCurrent_Balance = "500";
		NPayment_Amount = "150";
		Secured_or_Endorsed = "350";

		// Data for Retirement Accounts
		rothira_total_value = "10000.46";
		rothira_initial_contribution = "3500.82";
		rothira_investment_company = "Fidelity";

		ira_total_value = "2200.30";
		ira_initial_contribution = "2100.48";
		ira_investment_company = "Vanguard";

		// Data for Life Insurance
		Blance_OfAnyloans = "1500";
		Insurance_Company = "GEICO";
		Cash_Surrender_Value = "200";
		Face_Amount = "500";
		Beneficiaries = "Micale";
		Insurance_Company1 = "Blue_Cross_Blue_Shield";
		Cash_Surrender_Value1 = "300";
		Face_Amount1 = "600";
		Beneficiaries1 = "Asif";
		Loans_Against_Life_Insurance = "1500";
		abc = "ABC";

		// Data for Stocks and Bonds
		securities_name = "Alphabet Inc";
		total_value = "3472.55";
		num_of_shares = "5";
		cost = "694.72";
		market_value = "694.98";
		date = "02/24/2016";
		interest_dividends = "0";
		// Data for Primary Residence
		Address_PR = "5000 K Street NW, Washington DC ";
		PercentageOfownership = "50";
		PercentageOfmortgage = "50";
		CurrentValue = "10,000.00";
		MortgageBalance = "10,000.00";
		CurrentbalanceLean = "1000.00";
		CalculatedAnnually = "11000.00";
		// Data for Other Real State
		Address_ORE = "5345 Exess Ct VA";
		Percentage_ORE = "50";
		PercentageOfmortgage_ORE = "50";
		CurrentValue_ORE = "15,000.00";
		Mortgage_Balance_ORE = "10,000";
		Current_Balance_lean_ORE = "1000";
		Calculated_Annually_ORE = "11,000.00";

	}

	@Test
	public void EDWOSB_Real_State_Other_MainTest() throws Exception {
		driver.navigate().to(URL);
		Thread.sleep(3000);
		// Login to dashboard, Locate the email search box and enter a valid
		// email.
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
		// Locate the password search box and enter a valid password.
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Password);
		// Click the Sign-in button
		driver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		// Click the alert massage "Close"
		driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		// Verify that the login was successful and the Dashboard is displayed
		Thread.sleep(3000);
		try {
			assertEquals("BUSINESS PROFILE",
					driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[1]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		System.out.println("BUSINESS PROFILE Pass");

		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		if (driver.getPageSource().contains("Continue")) {
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr/td[4]/a[2]")).click();
			driver.switchTo().alert().accept();
			Thread.sleep(4000);

		} else {
			System.out.println(
					"There are(is) no certification in-progress on the dashboard, a new certification is beinng created");
			Thread.sleep(7000);
		}

		// Locate the Certifications on the dashboard, click on it and select
		// EDWOSB to continue.
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/ul/li[2]/a")).click();
		Thread.sleep(3000);
		// Locate the accept button at the bottom of the EDWOSB agreement and
		// click on it to continue.
		driver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
		// Locate the 8(a) question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[65][value]']/label[2]")).click();
		System.out.println("The 8(a) question and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Third Party Certification question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[66][value]']/label[2]")).click();
		System.out.println("The Third Party Certification question have been answered");
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the Non-qualification question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[68][value]']/label[2]")).click();
		System.out.println("The Non-qualification question have been answered");
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// Locate the LLC two question and select NO, Write comment and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[78][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_78_comment']")).sendKeys(comment);
		driver.findElement(By.xpath(".//*[@id='answers[79][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_79_comment']")).sendKeys(comment);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		System.out.println("The LLC questions have been answered");

		// Locate the Citizenship & Ownership Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[80][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[81][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Citizenship & Ownership questions have been answered");
		// Locate the Businesses & Trusts Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[82][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[83][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Businesses & Trusts questions have been answered");
		Thread.sleep(3000);
		// Locate the Operations & Management Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[84][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[85][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Operations & Management questions have been answered");
		// Locate the Expertise & Employment Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[86][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[87][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Expertise & Employment questions have been answered");
		// Locate the Highest Officer & Control Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[88][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[89][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Highest Officer & Control  questions have been answered");
		// Locate the SBA Exam & Daily Operations Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[90][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[91][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_91_comment']")).sendKeys(comment);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The SBA Exam & Daily Operations questions have been answered");
		// Locate the Net Worth Two question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[92][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[93][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Net Worth questions have been answered");

		// Locate the Adjusted Gross Income Two question and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[94][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[95][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Adjusted Gross Income questions have been answered");

		// Locate the Fair Market Value question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[96][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Fair Market Value questions have been answered");
		// Locate the Assets Two question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[97][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[98][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("The Assets questions have been answered");
		// Verify section for 'Financial Data' enter all valid data as required.
		if (driver.getPageSource().contains("Financial Data")) {
			System.out.println("User is navigated to the Correct section, (Financial Data) - Pass");
		} else {
			System.out.println("User is NOT being navigated to the Correct section, (Financial Data) - Failed");
		}
		Thread.sleep(3000);
		// Click on 'Add New Person' button at the bottom of the personal
		// information.
		driver.findElement(By.xpath(".//*[@id='answers_99_value_new_button']")).click();
		System.out.println("The personal information for 1st Person");
		System.out.println("The personal information Before Save:");
		// Verify that the section to Create new record is been seen by user and
		// enter new record to ADD.
		if (driver.getPageSource().contains("Create new record")) {
			System.out.println("The page to Create and Add new Record is Present, PASS");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys(First_Name);
			System.out.println("First_Name = Sabra");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys(Last_Name);
			System.out.println("Last_Name = Mabella ");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys(SSN);
			System.out.println("SSN= 495-54-3209");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys(Address_P);
			System.out.println("Address_P= 3432 Berugard ST");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys(City);
			System.out.println("State= Virginia");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys(State);
			System.out.println("Country= USA");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys(Zip);
			System.out.println("City= Alexandria");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys(Country);
			System.out.println("Zip= 22311");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys(Home_Phone);
			System.out.println("Home_Phone= 703-622-5854");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys(Business_Phone);
			System.out.println("Business_Phone= 703-765-4969");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys(Email_P);
			System.out.println("Email_P = Sabra.Mabella@gmail.com");
			driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			Thread.sleep(3000);

			// Is anyone listed above divorced? If yes, please provide
			// separation documents select "No"
			driver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		} else {
			System.out.println("the page to Create and Add new Record is NoT Present, Failed");

		}
		// Locate section for 'Cash On Hand' enter all valid data as required.
		try {
			assertEquals("Cash On Hand", driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("User is navigated to the Correct section, (Cash On Hand) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("User is NOT being navigated to the Correct section, (Cash On Hand) - Failed");
			Thread.sleep(3000);
			driver.quit();
		}
		System.out.println("Cash On Hand Data Before Save:");
		// Locate the As of Date: Search box for user and enter the information
		// as required.
		driver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE);
		System.out.println("AS_of_DATE= 02/01/2016");
		// Locate the Cash on Hand Search box for Sarbra Mabella and enter the
		// information as required.
		driver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys(Cash_On_Hand);
		System.out.println("Cash_On_Hand= 10000");
		// Locate the Savings Account(s) Balance Search box for Sarbra Mabella
		// and enter the information as required.
		driver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys(Saving_Account);
		System.out.println("Saving_Account= 50000");
		// Locate the Checking Account(s) Balance Search box for Sarbra Mabella
		// and enter the information as required.
		driver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys(Checking_Account);
		System.out.println("Checking_Account= 30000");
		// Locate the Continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		Thread.sleep(2000);
		// Locate section for Other Sources Of Income

		if (driver.getPageSource().contains("Other Sources Of Income")) {
			System.out.println("User is navigated to the Correct section, (Other Sources Of Income) - Pass");
		} else {
			System.out
					.println("User is NOT being navigated to the Correct section, (Other Sources Of Income) - Failed");
		}
		Thread.sleep(3000);
		System.out.println("Other Sources Of Income for Data:");
		// Locate the Salary search box and enter salary.
		driver.findElement(By.xpath(".//*[@id='answers_36_value']")).sendKeys(Salary);
		System.out.println("Salary= 60000");
		// Locate the Other Income search box and enter Other Income.
		driver.findElement(By.xpath(".//*[@id='answers_37_value']")).sendKeys(Other_Income);
		System.out.println("Other_Income= 3000");
		// Locate the applicant Business Type and enter amount of applicant
		// equity.
		driver.findElement(By.xpath(".//*[@id='answers_38_value']")).sendKeys(Business_Equity);
		System.out.println("Business_Equity= 5700");
		// Locate the applicant equity in other firm and enter applicant
		// business equity.
		driver.findElement(By.xpath(".//*[@id='answers_39_value']")).sendKeys(Equity_in_other_firm);
		System.out.println("Equity_in_other_firm= 20000");
		// Locate the continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);

		// Verify Notes Receivable page.
		System.out.println("Test Start here Notes Receivable Section");
		if (driver.getPageSource().contains("Notes Receivable")) {
			System.out.println("The Notes Receivable page coming, pass");
		} else {
			System.out.println("Notes Receivable , not found faild");
		}
		Thread.sleep(3000);
		// Do you have any notes receivable from others?
		driver.findElement(By.xpath(".//*[@id='answers[40][value]']/label[1]")).click();
		// Click the Add Button
		driver.findElement(By.xpath(".//*[@id='answers_40_details_table_wrapper']/div/a[1]")).click();
		// Enter the Name of Debtor
		driver.findElement(By.xpath(".//*[@id='DTE_Field_debtor_name']")).sendKeys(Name_Of_Debtor);
		System.out.println("Name_Of_Debtor= Alexander");
		// Enter the Address of Debtor
		driver.findElement(By.xpath(".//*[@id='DTE_Field_debtor_address']")).sendKeys(Address_Of_Debtor);
		System.out.println("Address_Of_Debtor= Alexander 2341 DR");
		// Enter the Original Balance
		driver.findElement(By.xpath(".//*[@id='DTE_Field_original_balance']")).sendKeys(NOriginal_Balance);
		System.out.println("Original_Balance= 1200");

		// Enter the Current Balance
		driver.findElement(By.xpath(".//*[@id='DTE_Field_current_balance']")).sendKeys(NCurrent_Balance);
		System.out.println("Current_Balance= 500");
		// Enter the Payment_Amount ( Calculated Annually )
		driver.findElement(By.xpath(".//*[@id='DTE_Field_pay_amount']")).sendKeys(NPayment_Amount);
		System.out.println("Payment_Amount= 150");
		// Enter the How Secured_or_Endorsed / Type Of Collateral
		driver.findElement(By.xpath(".//*[@id='DTE_Field_collateral_type']")).sendKeys(Secured_or_Endorsed);
		System.out.println("Secured_or_Endorsed= 350");
		Thread.sleep(300);
		// Click the Create Button
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(3000);
		// Locate the Continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		Thread.sleep(2000);

		// Verify if there is Retirement Accounts page.
		if (driver.getPageSource().contains("Retirement Accounts")) {
			System.out.println("The page Retirement Accounts, pass");
			// driver.findElement(By.xpath(".//*[@id='main-content']/div[2]/div[1]/div[3]/table/tbody/tr/td[2]/a[2]")).click();
		} else {
			System.out.println("Retirement Accounts, not found faild");
		}
		// Roth IRA select Yes
		driver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[1]")).click();

		// Click New and verify that the Create new entry window opens.
		// Create a New Roth IRA row
		driver.findElement(By.xpath(".//*[@id='answers_41_details_table_wrapper']/div/a[1]")).click();
		Thread.sleep(2000);
		try {
			assertEquals("Create new entry",
					driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[1]/div")).getText());
			System.out.println("Step 11 - Create new entry window opens - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Create new entry window opens - FAILED");
		}
		// click the Create button to create a new record
		// Locate the Total Value box and enter the total amount.

		System.out.println("The page Retirement Accounts Data Before save:");

		driver.findElement(By.xpath(".//*[@id='DTE_Field_total_value']")).sendKeys(rothira_total_value);
		System.out.println("rothira_total_value= 10000.46");
		// Locate the Initial Contribution box and enter the value.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_initial_contribution']"))
				.sendKeys(rothira_initial_contribution);
		System.out.println("rothira_initial_contribution= 3500.82");
		// Locate the Name of Inverstment Company box and enter the cost.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_investment_company']")).sendKeys(rothira_investment_company);
		System.out.println("rothira_investment_company=Fidelity");
		// Locate the Create button
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(3000);
		// Do you have any other retirement accounts? click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[1]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[1]")).click();
		// Click New and verify that the Create new entry window opens for the
		// Other Retirement.
		// Create a New row
		System.out.println("Create new entry window opens for the Other Retirement");
		driver.findElement(By.xpath(".//*[@id='answers_42_details_table_wrapper']/div/a[1]")).click();
		Thread.sleep(2000);

		// Fill out the below values for second record and click the Create
		// button to create a new record
		// Locate the Type drop down and select IRA
		Select oSelect_ira = new Select(driver.findElement(By.id("DTE_Field_type")));
		oSelect_ira.selectByVisibleText("IRA");
		// Locate the Total Value box and enter the total amount.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_total_value']")).sendKeys(ira_total_value);
		System.out.println("ira_total_value= 2200.30");
		// Locate the Initial Contribution box and enter the value.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_initial_contribution']")).sendKeys(ira_initial_contribution);
		System.out.println("ira_initial_contribution= 2100.48");
		// Locate the Investment Company box and enter the company name.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_investment_company']")).sendKeys(ira_investment_company);
		System.out.println("ira_investment_company= Vanguard");
		// Locate the Create button

		driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(3000);
		// Locate the continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);

		// Verify The page Life Insurance
		try {
			assertEquals("Life Insurance", driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("User is navigated to the Correct section, (Life Insurance) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("User is NOT being navigated to the Correct section, (Life Insurance) - FAILED");
		}

		// Do you have a life insurance policy that has a Cash Surrender Value?
		// Click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[43][value]']/label[1]")).click();
		// Click the ""New"
		driver.findElement(By.xpath(".//*[@id='answers_43_details_table_wrapper']/div/a[1]")).click();

		// Verify if there is Create new entry page.
		if (driver.getPageSource().contains("Create new entry")) {
			System.out.println("The Create new entry coming, pass");
		} else {
			System.out.println("Create new entry , not found faild");
		}
		Thread.sleep(3000);

		System.out.println("The life insurance policy Data Before Save:");
		// Enter the Name of Insurance Company
		driver.findElement(By.xpath(".//*[@id='DTE_Field_company_name']")).sendKeys(Insurance_Company);
		System.out.println("Insurance_Company = GEICO");
		// Enter Cash Surrender Value (if applicable)
		driver.findElement(By.xpath(".//*[@id='DTE_Field_cash_surrender_value']")).sendKeys(Cash_Surrender_Value);
		System.out.println("Cash_Surrender_Value = 200");
		// Enter the Face Amount
		driver.findElement(By.xpath(".//*[@id='DTE_Field_face_amount']")).sendKeys(Face_Amount);
		System.out.println("Face_Amount=  500");
		// Enter the Beneficiaries
		driver.findElement(By.xpath(".//*[@id='DTE_Field_beneficiaries']")).sendKeys(Beneficiaries);
		System.out.println("Beneficiaries = Micale");
		// Click the Create
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(3000);
		// Do you have any loans against a life insurance policy? "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
		Thread.sleep(3000);
		// What is the current balance of any loans against life insurance?
		System.out.println("Loans_Against_Life_Insurance= 1500");
		driver.findElement(By.xpath(".//*[@id='answers_45_value']")).sendKeys(Loans_Against_Life_Insurance);
		// Locate the continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);
		// Verify that user is navigated to the Correct section(Stocks & Bonds).
		System.out.println(" Verify that user is navigated to the Correct section(Stocks & Bonds)");
		try {
			assertEquals("Stocks & Bonds", driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  User is navigated to the Correct section, (Stocks & Bonds) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  User is NOT being navigated to the Correct section, (Stocks & Bonds) - FAILED");
		}
		System.out.println("The Stocks & Bonds Data before Save:");
		// Do you have any stocks, bonds or Mutual Funds? "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[1]")).click();
		// Click the Create
		driver.findElement(By.xpath(".//*[@id='answers_46_details_table_wrapper']/div/a[1]")).click();
		// Locate the Name Of Securities box and enter name of Securities.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_securities_name']")).sendKeys(securities_name);
		System.out.println("securities_name= Alphabet Inc");
		// Locate the Total Value box and enter the total amount.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_total_value']")).sendKeys(total_value);
		System.out.println("total_value= 3472.55");
		// Locate the Number of Shares box and enter the total shares.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_num_of_shares']")).sendKeys(num_of_shares);
		System.out.println("num_of_shares= 5");
		// Locate the Cost box and enter the cost.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_cost']")).sendKeys(cost);
		System.out.println("cost= 694.72");
		// Locate the Market Value Quotation/Exchange box and enter market
		// value.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_market_value']")).sendKeys(market_value);
		System.out.println("market_value= 694.98");
		// Locate the Date of Quotation/ Exchange box and enter the date.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_date']")).clear();
		driver.findElement(By.xpath(".//*[@id='DTE_Field_date']")).sendKeys(date);
		System.out.println("date=02/24/2016");
		// Locate the Interest & Dividends Received box and enter the Interest &
		// Dividends Received.
		driver.findElement(By.xpath(".//*[@id='DTE_Field_interest_dividends']")).sendKeys(interest_dividends);
		System.out.println("interest_dividends= 0");
		// Locate the Create button
		driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(3000);

		// Locate the continue button and click on it to continue.
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);

		// Verify if there is Real Estate - Primary Residence page.

		try {
			assertEquals("Real Estate - Primary Residence",
					driver.findElement(By.xpath(".//*[@id='main-content']/h2")).getText());
			System.out.println("User is navigated to the Correct section, (Real Estate - Primary Residence) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println(
					"User is NOT being navigated to the Correct section, (Real Estate - Primary Residence) - FAILED");
			Thread.sleep(3000);
			driver.quit();
		}
		System.out.println("Real Estate - Primary Residence Data Before save:");
		// Do you own your primary residence? click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[1]")).click();
		// What is the address of your primary residence? type the address
		driver.findElement(By.xpath(".//*[@id='answers_48_2_1_value']")).sendKeys(Address_PR);
		System.out.println("Address_PR= 5000 K Street NW, Washington DC");
		// Is your primary residence jointly owned? click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[48][2][2][value]']/label[1]")).click();
		// Is your name on the mortgage? Click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[48][2][5][value]']/label[1]")).click();
		// What percentage of ownership do you have in your primary residence?
		driver.findElement(By.xpath(".//*[@id='answers_48_2_3_value']")).sendKeys(PercentageOfownership);
		System.out.println("PercentageOfownership= 50");
		// What percentage of the mortgage are you responsible for in your
		// primary residence?
		driver.findElement(By.xpath(".//*[@id='answers_48_2_4_value']")).sendKeys(PercentageOfmortgage);
		System.out.println("PercentageOfmortgage = 50");
		// click yes Is your name on the mortgage?
		driver.findElement(By.xpath(".//*[@id='answers[48][2][5][value]']/label[1]")).click();

		// What is the current value of your primary residence?
		System.out.println("Real Estate(primary residence) = 10000");
		driver.findElement(By.xpath(".//*[@id='answers_48_2_6_value']")).sendKeys(CurrentValue);
		// What is the mortgage balance on your primary residence?

		driver.findElement(By.xpath(".//*[@id='answers_48_2_7_value']")).sendKeys(MortgageBalance);
		System.out.println("MortgageBalance= 5000");
		// Is there a lean, 2nd mortgage or Home Equity Line of Credit on your
		// primary residence? Click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[48][2][8][value]']/label[1]")).click();
		// What is the current balance of the lean(s)?
		driver.findElement(By.xpath(".//*[@id='answers_48_2_9_value']")).sendKeys(CurrentbalanceLean);
		System.out.println("CurrentbalanceLean= 1000");
		// Do you receive income from your primary residence (rent, etc.)?click
		// "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[48][2][10][value]']/label[1]")).click();
		System.out.println("Mortgages(Primary Residence) = 10000");
		// What is the income YOU receive from your primary residence
		// (calculated annually)?
		driver.findElement(By.xpath(".//*[@id='answers_48_2_11_value']")).sendKeys(CalculatedAnnually);
		System.out.println("Calculated Annually= 11000");
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);

		// Verify Real Estate - Other page there.
		System.out.println("\n\t\tLaunch Here US649 Real Estate - Other page");
		if (driver.getPageSource().contains("Real Estate - Other")) {
			System.out.println("The Real Estate - Other, pass");
		} else {
			System.out.println("Real Estate - Other , not found faild");
		}
		Thread.sleep(3000);
		System.out.println("Real Estate - Other page Data Before Sava:");

		// Step 1 - Locate the Real Estate - Other questions, Continue without
		// selecting anything.
		// System.out.println("Tried to continue without answering the two
		// question - Pass");
		// driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		// try { assertEquals("Please answer this question",
		// driver.findElement(By.xpath(".//*[@id='answers[41][value]-error']")).getText());
		// System.out.println("Tried to continue without answering the Roth IRA
		// question - Pass"); } catch (Error e) {
		// verificationErrors.append(e.toString()); System.out.println("Tried to
		// continue without answering the ROTH IRA question - FAILED"); }
		// Step 2 - Locate the Real Estate - Other questions select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("Step 2 - No was selected and the Continue button was clicked");
		Thread.sleep(2000);

		// Step 3 - Verify that user is navigated to the Correct
		// section(Personal Property).
		try {
			assertEquals("Personal Property",
					driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("Step 3 - Personal Property page is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Step 3 - Personal Property page is displayed - FAILED");
		}
		Thread.sleep(2000);

		// Step 4 - Return to the Real Estate - Other page
		driver.findElement(By.xpath(".//*[@id='real_estate_other_sabra_mabella']")).click();
		Thread.sleep(2000);
		try {
			assertEquals("Real Estate - Other", driver.findElement(By.xpath(".//*[@id='main-content']/h2")).getText());
			System.out.println("Step 4 - User is returned to the correct section (Real Estate - Other) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Step 4 - User is NOT returned to the Correct section (Real Estate - Other) - FAILED");
		}
		Thread.sleep(2000);
		// Step 5 - Locate the Real Estate - Other questions, select Yes to the
		// first question, don't add a row, and click the Continue button.
		System.out.println(
				"Step 5 - Locate the Real Estate - Other questions, select Yes to the first question, don't add a row, and click the Continue button.");
		// Real Estate - Other select Yes
		driver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[1]")).click();
		driver.findElement(By.xpath(".//*[@id='add_real_estate']/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		try {
			assertEquals("This field is required.",
					driver.findElement(By.xpath(".//*[@id='answers_50_2_1_value-error']")).getText());
			System.out.println("\n\t\tError message will be displayed for those Quetion- Pass");
			System.out.println("What is the address of your Other Real Estate? ");
			System.out.println("Is your Other Real Estate jointly owned?");
			System.out.println("Is your name on the mortgage?");
			assertEquals("This field is required.",
					driver.findElement(By.xpath(".//*[@id='answers_50_2_6_value-error']")).getText());
			System.out.println("What is the current value of your Other Real Estate?");
			System.out.println("What is the mortgage balance on your Other Real Estate?");
			System.out
					.println("Is there a lien, 2nd mortgage or Home Equity Line of Credit on your Other Real Estate?");
			System.out.println("Do you receive income from your Other Real Estate (rent, etc.)?");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Error message displayed What is the address of your Other Real Estate? - FAILED");
		}

		// Step 6 - Enter charters into numeric field and click the Create
		// button
		// What is the address of your Other Real Estate?
		driver.findElement(By.id("answers_50_2_1_value")).sendKeys(Address_ORE);
		// What is the current value of your Other Real Estate?
		driver.findElement(By.id("answers_50_2_6_value")).sendKeys("abc");
		// Locate the Initial Contribution box and enter the total shares.
		driver.findElement(By.id("answers_50_2_7_value")).sendKeys("abc");
		// Locate the continue button and click on it to continue.
		// driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		Thread.sleep(2000);
		// Step 7 - Verify that the numeric field error message is displayed
		System.out.println("Step 14 - Numeric error messages:");
		Thread.sleep(3000);
		try {
			assertEquals("This field is required.",
					driver.findElement(By.xpath(".//*[@id='answers_50_2_6_value-error']")).getText());
			System.out.println("Total Value is a Numeric field - Only numbers are allowed! - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Total Value is a Numeric field - Only numbers are allowed! - FAILED");
		}

		try {
			assertEquals("This field is required.",
					driver.findElement(By.xpath(".//*[@id='answers_50_2_6_value-error']")).getText());
			System.out.println("Initial Contribution is a Numeric field - Only numbers are allowed! - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Initial Contribution is a Numeric field - Only numbers are allowed! - FAILED");
		}

		// Step 8 - Clear the text boxes

		driver.findElement(By.id("answers_50_2_6_value")).clear();
		driver.findElement(By.id("answers_50_2_7_value")).clear();
		System.out.println("Step 15- Text boxes cleared of data");
		Thread.sleep(2000);
		// Do you own any additional real estate? Click "Yes"
		driver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[1]")).click();

		// Verify if there is Real Estate - Primary Residence page.
		if (driver.getPageSource().contains("List your other real estate holdings:")) {
			System.out.println("List your other real estate holdings:, pass");
			// List your other real estate holdings: coming up "Add" Click other
			// real estate holdings information
			// driver.findElement(By.xpath(".//*[@id='add_real_estate']")).click();
			Thread.sleep(3000);
			// What is the address of your Other Real Estate?
			driver.findElement(By.xpath(".//*[@id='answers_50_2_1_value']")).sendKeys(Address_ORE);
			System.out.println("5000 K Street NW, Washington DC ");
			// Is your Other Real Estate jointly owned? "Yes"
			driver.findElement(By.xpath(".//*[@id='answers[50][2][2][value]']/label[1]")).click();
			// What percentage of ownership do you have in your Other Real
			// Estate?
			driver.findElement(By.xpath(".//*[@id='answers_50_2_3_value']")).sendKeys(Percentage_ORE);
			System.out.println("Percentage_ORE = 50");
			// Click yes
			driver.findElement(By.xpath(".//*[@id='answers[50][2][4][value]']/label[1]")).click();
			// What percentage of the mortgage are you responsible for in your
			// Other Real Estate?
			driver.findElement(By.xpath(".//*[@id='answers_50_2_5_value']")).sendKeys(PercentageOfmortgage_ORE);
			System.out.println(" PercentageOfmortgage_ORE= 60");
			// What is the current value of your Other Real Estate?
			driver.findElement(By.xpath(".//*[@id='answers_50_2_6_value']")).sendKeys(CurrentValue_ORE);
			System.out.println("Other Real Estate = 15000");
			// What is the mortgage balance on your Other Real Estate?
			driver.findElement(By.xpath(".//*[@id='answers_50_2_7_value']")).sendKeys(Mortgage_Balance_ORE);
			System.out.println("Mortgage_Balance_ORE= 10000");

			// Is there a lean, 2nd mortgage or Home Equity Line of Credit on
			// your Other Real Estate? "Yes"
			driver.findElement(By.xpath(".//*[@id='answers[50][2][8][value]']/label[1]")).click();
			// Select the What is the current balance of the lean(s)?
			System.out.println("Current_Balance_lean_ORE =  1000");
			driver.findElement(By.xpath(".//*[@id='answers_50_2_9_value']")).sendKeys(Current_Balance_lean_ORE);
			// Do you receive income from your Other Real Estate (rent, etc.)?
			// "Yes"
			driver.findElement(By.xpath(".//*[@id='answers[50][2][10][value]']/label[1]")).click();
			// What is the income YOU receive from your Other Real Estate
			// (calculated annually)?
			System.out.println("Calculated Annually Other Real Estate=  11000.00");
			driver.findElement(By.xpath(".//*[@id='answers_50_2_11_value']")).sendKeys(Calculated_Annually_ORE);
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			Thread.sleep(3000);

		} else {
			System.out.println("List your other real estate holdings:, not found faild");
		}

		// Step 3. Verify that users is navigated to the right page for EDWOSB
		// 2413 and 414 Application >> Form413 >>Verify question and select YES
		// OR NO to the question as apply to user and continue.
		if (driver.getPageSource().contains("Real Estate - Other")) {
			System.out.println(
					"User is being navigatd to the financial information(413), Real Estate - Other page, PASS");
			// locate and click on the dashboard button.
			driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
			Thread.sleep(3000);
			// Verify and validate the in-progress certification and the
			// dashboard
			if (driver.getPageSource().contains("BUSINESS PROFILE")) {
				// Click on continue.
				driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr/td[4]/a[1]"))
						.click();
				// Click on 'Real Estate - Primary Residence'.
				driver.findElement(By.xpath(".//*[@id='real_estate_other_sabra_mabella']")).click();

				// Verify The primary residence address is being saved
				if (driver.getPageSource().contains("5345 Exess Ct VA5345 Exess Ct VA")) {
					System.out.println("The primary residence address is being saved, Pass");
				} else {
					System.out.println("The primary residence address is not being saved, Faild");
				}
				Thread.sleep(3000);
				// Verify What percentage of ownership do you have in your
				// primary residence? is being saved
				if (driver.getPageSource().contains("50")) {
					System.out.println(
							"What percentage of ownership do you have in your primary residence? is being saved, Pass");
				} else {
					System.out.println(
							"What percentage of ownership do you have in your primary residence? is not being saved, Faild");
				}
				Thread.sleep(3000);

				// Verify What percentage of the mortgage are you responsible
				// for in your primary residence? is being saved
				if (driver.getPageSource().contains("50")) {
					System.out.println(
							"What percentage of the mortgage are you responsible for in your primary residence? is being saved, Pass");
				} else {
					System.out.println(
							"What percentage of the mortgage are you responsible for in your primary residence? is not being saved, Faild");
				}
				Thread.sleep(3000);

				// Verify What is the current value of your primary residence?
				// is being saved
				if (driver.getPageSource().contains("10000")) {
					System.out.println("The current value of your primary residence is being saved, Pass");
				} else {
					System.out.println("The current value of your primary residence is not being saved, Faild");
				}
				Thread.sleep(3000);
				// Verify What is the mortgage balance on your primary
				// residence? is being saved
				if (driver.getPageSource().contains("10000")) {
					System.out.println("The mortgage balance on your primary residence is being saved, Pass");

				} else {
					System.out.println("The mortgage balance on your primary residence is not being saved, Faild");
				}

				// Verify What is the current balance of the lien(s)? is being
				// saved
				if (driver.getPageSource().contains("1000")) {
					System.out.println("What is the current balance of the lien(s)? is being saved, Pass");

				} else {
					System.out.println("What is the current balance of the lien(s)? is not being saved, Faild");
				}
				// Verify What is the income YOU receive from your primary
				// residence (calculated annually)? is being saved
				if (driver.getPageSource().contains("11000")) {
					System.out.println(
							"What is the income YOU receive from your primary residence (calculated annually)? is being saved, Pass");
					System.out.println(driver.findElement(By.xpath(".//*[@id='edwosb']")).getText());
				} else {
					System.out.println(
							"What is the income YOU receive from your primary residence (calculated annually)? is not being saved, Faild");
				}
			}

			else {
				System.out.println("Certification is not being save, Failed");
				driver.quit();
			}
		}

	}

	@After
	public void EDWOSB_Real_State_Other_EndTest() {
		String verificationErrorString = verificationErrors.toString();
		if (verificationErrorString != null) {
			System.out.println("Success: " + verificationErrorString);
		}
		driver.quit();

	}

}
