package all_production_test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US934_Save_and_continue_EDWOSB {
	private WebDriver driver;
	String AS_of_DATE;
	String Cash_On_Hand;
	String Saving_Account;
	String Checking_Account;
	String Salary;
	String Other_Income;
	String Business_Equity;
	String Equity_in_other_firm;
	String Loans_against_Life_Insurance;
	String agi;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		AS_of_DATE = "01/12/2016";
		Cash_On_Hand = "80000";
		Saving_Account = "100000";
		Checking_Account = "80000";
		Salary = "100000";
		Other_Income = "70000";
		Business_Equity = "57000";
		Equity_in_other_firm = "20000";
		agi = "1000";

		// setting up IE, Chrome, and FireFox Web Drivers
		File file = new File(Constants.IE);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File(Constants.Chrome);
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// driver=new InternetExplorerDriver();
		// driver=new ChromeDriver();
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void testSmokeLogin() throws Exception {
		// Step 1 - Open QA site
		// driver.get(baseUrl);
		driver.get(Constants.baseUrl);
		System.out.println("Step 1 - URL: " + Constants.baseUrl);

		// Click on the Login link and log in
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();

		// Enter a valid Login ID
		driver.findElement(By.xpath(".//*[@id='user_email']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Constants.LLC_Login);
		System.out.println("  Login ID: " + Constants.LLC_Login);

		// Enter a valid Password
		driver.findElement(By.xpath(".//*[@id='user_password']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Constants.Password);
		System.out.println("  Password: " + Constants.Password);

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
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr/td[4]/a[2]")).click();
			System.out.println("Certification in-progress on the dashboard was deleted");
			driver.switchTo().alert().accept();
		} else {
			System.out.println(
					"There are(is) no certification in-progress on the dashboard, a new certification is being created");
		}
		Thread.sleep(4000);

		// Step 2 - Locate the Certifications on the dashboard, click on it and
		// select EDWOSB to continue.
		System.out.println(
				"Step 2 - Locate the Certifications on the dashboard, click on it and select EDWOSB to continue.");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();

		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).getText();

		// Step 3 - Locate the accept button at the bottom of the EDWOSB
		// agreement and click on it to continue.
		System.out.println(
				"Step 3 - Locate the accept button at the bottom of the EDWOSB agreement and click on it to continue.");
		if (driver.getPageSource().contains("Accept")) {
			System.out.println(
					"  The continue button on the Review was not change or updated to Save and continue - Pass");
		} else {
			System.out.println(
					"  The continue button on the Review was change or updated to Save and continue - <<Failed>>");
			driver.quit();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
		Thread.sleep(2000);

		// Step 4 - Locate the 8(a) question, select No, valid Save and continue
		// button, and continue.
		System.out
				.println("Step 4 - Locate the 8(a) question, select No, valid Save and continue button, and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[65][value]']/label[2]")).click();

		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  8(a) question have been answered");

		// Step 5 - Locate the Third Party Certification question1, valid Save
		// and continue button, select Yes, attach a document, and continue.
		System.out.println(
				"Step 5 - Locate the Third Party Certification question1, valid Save and continue button and select Yes, attach a document, and continue..");
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='answers[66][value]']/label[1]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[6][attachment]']/div/label/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("selector")).click();
		driver.findElement(By.id("document_library_attach")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 6 - Locate the Third Party Certification question2 and valid
		// Save and continue button
		System.out
				.println("Step 6 - Locate the Third Party Certification question2 and valid Save and continue button");
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 7 - Return to the Third Party Certification question1, select
		// No, and continue
		System.out.println("Step 7 - Return to the Third Party Certification question1, select No, and continue");
		driver.findElement(By.xpath(".//*[@id='third_party_cert_part_1']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='answers[66][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 8 - Locate the Third Party Certification question3 and valid
		// Save and continue button
		System.out
				.println("Step 8 - Locate the Third Party Certification question2 and valid Save and continue button");
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='answers[68][value]']/label[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 9 - Locate the Business Corporation and LLC questions 1 - 2,
		// valid Save and continue button, and continue.
		System.out.println(
				"Step 9 - Locate the Business Corporation and LLC questions 1 and 2, valid Save and continue button, and continue");
		driver.findElement(By.xpath(".//*[@id='answers[78][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_78_comment']")).sendKeys(Constants.comment);
		driver.findElement(By.xpath(".//*[@id='answers[79][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_79_comment']")).sendKeys(Constants.comment);
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 10 - Locate the Citizenship & Ownership question 1and2, and
		// select No and continue.
		System.out
				.println("Step 10 - Locate the Citizenship & Ownership question 1 and 2, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[80][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[81][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 11 - Locate the Businesses & Trusts question 3and4, and select
		// No and continue.
		System.out.println("Step 11 - Locate the Businesses & Trusts question 3 and 4, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[82][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[83][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 12 - Locate the Operations & Management question 5and6, and
		// select No and continue.
		System.out
				.println("Step 12 - Locate the Operations & Management question 5 and 6, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[84][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[85][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 13 - Locate the Expertise & Employment, question 7and8, and
		// select No and continue.
		System.out
				.println("Step 13 - Locate the Expertise & Employment, question 7 and 8, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[86][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[87][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 14 - Locate the Highest Officer & Control question 9and10, and
		// select No and continue.
		System.out.println(
				"Step 14 - Locate the Highest Officer & Control question 9 and 10, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[88][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[89][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 15 - Locate the SBA Exam & Daily Operations question 11and12,
		// and select No and continue.
		System.out.println(
				"Step 15 - Locate the SBA Exam & Daily Operations question 11 and 12, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[90][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[91][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_91_comment']")).sendKeys(Constants.comment);
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  Operations questions have been answered");

		// Step 16 - Locate the EDWOSB Net Worth questions, and select No and
		// continue.
		System.out.println("Step 16 - Locate the EDWOSB Net Worth questions, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[92][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[93][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 17 - Locate the EDWOSB Adjusted Gross Income questions, and
		// select No and continue.
		System.out.println("Step 17 - Locate the EDWOSB Adjusted Gross Income questions, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[94][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[95][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 18 - Locate the EDWOSB Fair Market Value question, and select No
		// and continue.
		System.out.println("Step 18 - Locate the EDWOSB Fair Market Value question, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[96][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Step 19 - Locate the EDWOSB Section4 questions, and select No and
		// continue.
		System.out.println("Step 19 - Locate the EDWOSB Section4 questions, and select No and continue.");
		driver.findElement(By.xpath(".//*[@id='answers[97][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[98][value]']/label[2]")).click();
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Save and continue")) {
			System.out.println("  Save and continue button is displayed - Pass");
		} else {
			System.out.println("  Save and continue button is displayed - <<FAILED>>");
			driver.quit();
		}
		driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
		System.out.println("  EDWOSB application questions have been answered");

		// Step 20 - Create an Owner and verify the Save and continue button.
		System.out.println("Step 20 - Create an Owner and verify the Save and continue button.");
		// Locate the EDWOSB 2413 and 414 Application, Form413 and click on 'Add
		// New Person' button at the bottom of the personal information.
		driver.findElement(By.xpath(".//*[@id='answers_99_value_new_button']")).click();
		Thread.sleep(2000);

		// Verify that the section to Create new record is been seen by user and
		// enter new record to ADD.
		if (driver.getPageSource().contains("Create new record")) {
			System.out.println("  The page to Create and Add new Record is Present, PASS");
			driver.findElement(By.xpath(".//*[@id='DTE_Field_first_name']")).sendKeys(Constants.FN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_last_name']")).sendKeys(Constants.LN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_ssn']")).sendKeys(Constants.SSN);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_address']")).sendKeys(Constants.Address);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_city']")).sendKeys(Constants.City);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_state']")).sendKeys(Constants.State);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_postal_code']")).sendKeys(Constants.Zip);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_country']")).sendKeys(Constants.Country);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_home_phone']")).sendKeys(Constants.Home_Phone);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_business_phone']")).sendKeys(Constants.Business_Phone);
			driver.findElement(By.xpath(".//*[@id='DTE_Field_email']")).sendKeys(Constants.Email);
			driver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			Thread.sleep(3000);
			// Select No for question Is anyone listed above divorced? If yes,
			// please provide separation documents.
			driver.findElement(By.xpath(".//*[@id='answers[100][value]']/label[2]")).click();
			// Locate and verify the Save and continue button
			Thread.sleep(2000);
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			// click on the Save and continue button to continue.
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 21 - Locate section for Cash on Hand enter all valid data as
			// required.
			System.out.println("Step 21 - Locate section for Cash on Hand enter all valid data as required.");
			// Locate the As of Date: Search box for user and enter the
			// information as required.
			driver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys(AS_of_DATE);
			// Locate the Cash on Hand Search box for WWW eee user and enter the
			// information as required.
			driver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys(Cash_On_Hand);
			// Locate the Savings Account(s) Balance Search box for WWW eee user
			// and enter the information as required.
			driver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys(Saving_Account);
			// Locate the Checking Account(s) Balance Search box for WWW eee
			// user and enter the information as required.
			driver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys(Checking_Account);
			// Locate the Continue button and click on it to continue.
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			Thread.sleep(2000);

			// Step 22 - Locate section for Other Source of Income enter all
			// valid data as required.
			System.out.println("Step 22 - Locate section for Other Source of Income enter all valid data as required.");
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
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 23 - Select No for Notes Receivable Question and verify the
			// Save and continue button.
			System.out.println(
					"Step 23 - Select No for Notes Receivable Question and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[40][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 24 - Locate the Retirement Accounts questions 1 and 2,
			// select No, and verify the Save and continue button.
			System.out.println(
					"Step 24 - Locate the Retirement Accounts questions 1 and 2, select No, and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 25 - Locate the Life Insurance questions 1, 2, 3, select No,
			// and verify the Save and continue button.
			System.out.println(
					"Step 25 - Locate the Life Insurance questions 1, 2, 3, select No, and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[43][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 26 - Locate the Stocks & Bonds questions select No and
			// verify the Save and continue button.
			System.out.println(
					"Step 26 - Locate the Stocks & Bonds questions select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 27 - Locate the Real Estate - Primary Residence question
			// select No and verify the Save and continue button.
			System.out.println(
					"Step 27 - Locate the Real Estate - Primary Residence question select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 28 - Locate the Real Estate - Other question select No and
			// verify the Save and continue button.
			System.out.println(
					"Step 28 - Locate the Real Estate - Other question select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 29 - Locate the Personal Property questions select No and
			// verify the Save and continue button.
			System.out.println(
					"Step 29 - Locate the Personal Property questions select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[54][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[55][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 30 - Locate the Notes Payable questions select No and verify
			// the Save and continue button.
			System.out.println(
					"Step 30	 - Locate the Notes Payable questions select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[57][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[58][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 31 - Locate the Assessed Taxes questions select No and
			// verify the Save and continue button.
			System.out.println(
					"Step 31 - Locate the Assessed Taxes questions select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers[60][value]']/label[2]")).click();
			driver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[2]")).click();
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 32 - Locate the Adjusted Gross Income questions select No
			// and verify the Save and continue button.
			System.out.println(
					"Step 32 - Locate the Adjusted Gross Income questions select No and verify the Save and continue button.");
			driver.findElement(By.xpath(".//*[@id='answers_62_value']")).sendKeys(agi);
			driver.findElement(By.xpath(".//*[@id='answers_63_value']")).sendKeys(agi);
			driver.findElement(By.xpath(".//*[@id='answers_64_value']")).sendKeys(agi);
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 33 - Locate the Financial Summary questions select No and
			// verify the Save and continue button.
			System.out.println(
					"Step 33 - Locate the Financial Summary questions select No and verify the Save and continue button.");
			if (driver.getPageSource().contains("Save and continue")) {
				System.out.println("  Save and continue button is displayed - Pass");
			} else {
				System.out.println("  Save and continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 34 - Locate the Privacy Statements page and verify the Save
			// and continue button.
			System.out.println(
					"Step 34 - Locate the Privacy Statements questions select No and verify the Save and continue button.");
			if (driver.getPageSource().contains("Continue")) {
				System.out.println("  Continue button is displayed - Pass");
			} else {
				System.out.println("  Continue button is displayed - <<FAILED>>");
				driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

			// Step 35 - Locate the Review page and verify the Save and continue
			// button.
			System.out.println("Step 35 - Locate the Review page and verify the Save and continue button.");
			if (driver.getPageSource().contains("Submit")) {
				System.out.println("  Submit button is displayed - Pass");
			} else {
				System.out.println("  Submit button is displayed - <<FAILED>>");
				// driver.quit();
			}
			driver.findElement(By.xpath(".//*[@id='wosb']/div/input")).click();

			// Step 36 - Locate the Signature page and verify the Save and
			// continue button.
			System.out.println("Step 36 - Locate the Signature page and verify the Save and continue button.");
			if (driver.getPageSource().contains("Accept")) {
				System.out.println("  Accept button is displayed - Pass");
			} else {
				System.out.println("  Accept button is displayed - <<FAILED>>");
				driver.quit();
			}

			// Step 37 - Click the Dashboard link
			System.out.println("Step 37 - Returned to the Dashboard");
			driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
			Thread.sleep(2000);

			// Step 38 - Delete application
			System.out.println("Step 38 - Delete application");
			if (driver.getPageSource().contains("Draft")) {
				driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr/td[4]/a[2]"))
						.click();
				System.out.println("  Certification in-progress on the dashboard was deleted");
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
			}

			// Step 39 - Click the Logout link
			System.out.println("Step 39 - Logout link clicked");
			driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[6]/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='labelid']")).click();
			Thread.sleep(2000);
			System.out.println("END OF TEST");
		}
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
