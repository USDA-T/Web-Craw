package gov.sba.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinancialsectionPage {

	WebDriver mydriver;

	public FinancialsectionPage(WebDriver mydriver) {
		this.mydriver = mydriver;

	}

	public void Financialsection() throws Exception {

		Thread.sleep(3000);
		// Locate section for 'Cash on Hand' enter all valid data as required.
		// Locate the As of Date: Search box for user and enter the information
		// as required.
		mydriver.findElement(By.xpath(".//*[@id='answers_32_value']")).sendKeys("04/23/2016");
		// Locate the Cash on Hand Search box for Pual Washington and enter the
		// information as required.
		mydriver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys("4000");

		// Locate the Savings Account(s) Balance Search box for Pual Washington
		// and enter the information as required.
		mydriver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys("5000");
		// Locate the Checking Account(s) Balance Search box for Pual Washington
		// and enter the information as required.
		mydriver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys("45000");
		// Locate the Continue button and click on it to continue.
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Locate section for Other Source of Income enter all valid data as
		// required.
		// Locate the Salary search box and enter salary.

		mydriver.findElement(By.xpath(".//*[@id='answers_36_value']")).sendKeys("70000");
		// Locate the Other Income search box and enter Other Income.
		mydriver.findElement(By.xpath(".//*[@id='answers_37_value']")).sendKeys("80000");
		// Locate the applicant Business Type and enter amount of applicant
		// equity.
		mydriver.findElement(By.xpath(".//*[@id='answers_38_value']")).sendKeys("800000");
		// Locate the applicant equity in other firm and enter applicant
		// business equity.
		mydriver.findElement(By.xpath(".//*[@id='answers_39_value']")).sendKeys("50000");
		// Locate the continue button and click on it to continue.
		mydriver.findElement(By.name("commit")).click();

		// Locate and YES for question 'Do you have any notes receivable from
		// others?'.
		mydriver.findElement(By.xpath(".//*[@id='answers[40][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_40_details_table_wrapper']/div/a[1]/span")).click();
		System.out.println(
				"User is prompted to enter Atleast one row  field or to select no if not applicable. Valid error Message is ");
		Thread.sleep(3000);
		// Locate the 'Name of Debtor' search box and enter a valid name.
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_debtor_name']")).sendKeys("John Mall");
		// Locate the 'Address of Debtor' search box and enter a valid address.
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_debtor_address']")).sendKeys("8765 Kings st MD 23445");
		// Locate the 'Original Balance' search box and enter a valid balance.
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_original_balance']")).sendKeys("80000");
		// Locate the 'Current Balance' Search box and enter a valid balance.
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_balance']")).sendKeys("40000");
		// Locate the 'Payment Amount(Calculated Annually)' and enter a valid
		// Amount.
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_amount']")).sendKeys("60000");
		// Locate the 'How Secured or Endorsed / Type of Collateral' search box
		// and enter a valid Collateral type(s).
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_collateral_type']")).sendKeys("secured");
		// Locate the 'Create' button at the Right bottom of the Create new
		// entry page and click on it .
		mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		// locate the 'CONTINUE' button at the right bottom of the page and
		// click on it to save
		mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();

		// Select NO for the two question on Retirement Accounts.
		mydriver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();
		// mydriver.findElement(By.xpath(".//*[@id='answers_41_details_table_wrapper']/div/a[1]/span")).click();
		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_total_value']")).sendKeys("80000");
		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_contributions_thus_far']")).sendKeys("80000");
		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_date_of_initial_contribution']")).clear();

		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_date_of_initial_contribution']")).sendKeys("06/09/2016");

		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_investment_company']")).sendKeys("21
		// cenyury");
		// mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(4000);
		mydriver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();
		// .findElement(By.xpath(".//*[@id='answers[42][value]']/label[1]")).click();

		// mydriver.findElement(By.xpath(".//*[@id='answers_42_details_table_wrapper']/div/a[1]/span")).click();
		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_total_value']")).sendKeys("50000");
		//// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_initial_contribution']")).sendKeys("40000");
		// mydriver.findElement(By.xpath(".//*[@id='DTE_Field_investment_company']")).sendKeys("21
		// century");
		// mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(4000);
		mydriver.findElement(By.name("commit")).click();

		// Select Yes for the two question on Life Insurance.
		// Locate and select yes for question Do you have life insurance policy
		// that has a cash surrender value.
		mydriver.findElement(By.xpath(".//*[@id='answers[43][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_43_details_table_wrapper']/div/a[1]/span")).click();
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_company_name']")).sendKeys("21 century");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_cash_surrender_value']")).sendKeys("40000");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_face_amount']")).sendKeys("5000");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_beneficiaries']")).sendKeys("John Peter");
		mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		Thread.sleep(5000);
		// Locate and select yes for question, Do you have loan against a life
		// insurance.
		mydriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();

		mydriver.findElement(By.xpath(".//*[@id='answers_45_value']")).sendKeys("50000");
		mydriver.findElement(By.name("commit")).click();

		// Select Yes for the Stock and Bonds Section.
		mydriver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_46_details_table_wrapper']/div/a[1]/span")).click();
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_securities_name']")).sendKeys("Test Testing");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_total_value']")).sendKeys("50000");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_num_of_shares']")).sendKeys("60");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_cost']")).sendKeys("59000");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_market_value']")).sendKeys("98000");
		mydriver.findElement(By.xpath(".//*[@id='DTE_Field_interest_dividends']")).sendKeys("76000");
		mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
		mydriver.findElement(By.name("commit")).click();

		// Select Yes for Real Estate - Primary Residence Section questions.
		mydriver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[1]")).click();
		Thread.sleep(4000);
		mydriver.findElement(By.id("answers_48_2_1_value")).sendKeys("testing Data");
		mydriver.findElement(By.xpath(".//*[@id='answers[48][2][2][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_48_2_3_value']")).sendKeys("50");
		mydriver.findElement(By.xpath(".//*[@id='answers_48_2_4_value']")).sendKeys("98");
		mydriver.findElement(By.xpath(".//*[@id='answers[48][2][5][value]']/label[1]")).click();

		// mydriver.findElement(By.xpath(".//*[@id='answers_48_2_5_value']")).sendKeys(Percentage);
		mydriver.findElement(By.xpath(".//*[@id='answers_48_2_6_value']")).sendKeys("78");
		mydriver.findElement(By.xpath(".//*[@id='answers_48_2_7_value']")).sendKeys("76");
		mydriver.findElement(By.xpath(".//*[@id='answers[48][2][8][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_48_2_9_value']")).sendKeys("50");
		mydriver.findElement(By.xpath(".//*[@id='answers[48][2][10][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers_48_2_11_value']")).sendKeys("50");
		mydriver.findElement(By.name("commit")).click();

		// Select Yes for Real Estate - Other section.
		mydriver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[1]")).click();

		mydriver.findElement(By.xpath(".//*[@id='add_real_estate']")).click();

		Thread.sleep(3000);
		mydriver.findElement(By.xpath(".//*[@id='answers_50_2_1_value']")).sendKeys("1000");
		mydriver.findElement(By.xpath(".//*[@id='answers[50][2][2][value]']/label[2]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers[50][2][4][value]']/label[2]")).click();

		// mydriver.findElement(By.xpath(".//*[@id='answers_50_2_5_value']")).sendKeys(Percentage);
		mydriver.findElement(By.xpath(".//*[@id='answers_50_2_6_value']")).sendKeys("76");
		mydriver.findElement(By.xpath(".//*[@id='answers_50_2_7_value']")).sendKeys("87");
		// Select yes for the last two question.
		Thread.sleep(2000);
		mydriver.findElement(By.xpath(".//*[@id='answers[50][2][8][value]']/label[1]")).click();
		mydriver.findElement(By.id("answers_50_2_9_value")).sendKeys("65");
		mydriver.findElement(By.xpath(".//*[@id='answers[50][2][10][value]']/label[1]")).click();
		mydriver.findElement(By.id("answers_50_2_11_value")).sendKeys("56");
		Thread.sleep(3000);
		mydriver.findElement(By.name("commit")).click();

		Thread.sleep(3000);
		// Beginning Test For Personal Property.

		if (mydriver.getPageSource().contains("Do you own any automobiles")) {
			System.out.println("User is being navigated to the Personal Property section, PASS");
			mydriver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[1]")).click();
			// Locate the New button on the data table and click on it to add
			// information.
			mydriver.findElement(By.xpath(".//*[@id='answers_51_details_table_wrapper']/div/a[1]")).click();
			// Locate current value search box and enter a valid value for you
			// automobile.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_value']")).sendKeys("70000");
			// Locate the Loan Balance search box and enter a valid balance.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_loan_balance']")).sendKeys("40000");
			// Locate the description of asset search box and enter the
			// make,model and year of your automobile.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_asset_description']")).sendKeys("nissan maxima");
			// Locate the payment amount search box and enter the annual payment
			// amount for the automobile.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_payment_amount']")).sendKeys("30000");
			// Locate the Create button and click on it.
			Thread.sleep(3000);
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			Thread.sleep(3000);
			// Select Yes for question; 'Does any of the above listed property
			// is pledged as security?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[52][value]']/label[1]")).click();

			// Locate the new button on the data table and click on it to enter
			// data.
			mydriver.findElement(By.xpath(".//*[@id='answers_52_details_table_wrapper']/div/a[1]/span")).click();
			// Locate the Name of lien Holder search box and enter a valid data.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_holder_name']")).sendKeys("Paul Lu");
			// Locate the amount of lien search box and enter a valid amount.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_amount']")).sendKeys("40000");
			// Locate the Terms of Payment search box and enter a valid data.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_terms']")).sendKeys("4years");
			Thread.sleep(3000);
			// Locate the Create button and click on it.
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			Thread.sleep(3000);
			// Select yes for question; 'Are any leans delinquent?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_53_comment']")).sendKeys("Testing");
			Thread.sleep(3000);
			// Select yes for question; 'Do you own any other personal property
			// or assets?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[54][value]']/label[1]")).click();
			// Locate the new button on the data table and click on it to add
			// another data for personal property.
			mydriver.findElement(By.xpath(".//*[@id='answers_54_details_table_wrapper']/div/a[1]")).click();
			Thread.sleep(3000);
			// Locate current value search box and enter a valid value for you
			// automobile.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_current_value']")).sendKeys("6000");
			// Locate the Loan Balance search box and enter a valid balance.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_loan_balance']")).sendKeys("100000");
			// Locate the description of asset search box and enter the
			// make,model and year of your automobile.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_asset_description']")).sendKeys("Toyota Lover");
			// Locate the payment amount search box and enter the annual payment
			// amount for the automobile.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_payment_amount']")).sendKeys("400000");
			// Locate the Create button and click on it.
			Thread.sleep(3000);
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			Thread.sleep(3000);
			// Select Yes for question; 'Does any of the above listed property
			// is pledged as security?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[55][value]']/label[1]")).click();
			// Locate the new button on the data table and click on it to enter
			// data.
			mydriver.findElement(By.xpath(".//*[@id='answers_55_details_table_wrapper']/div/a[1]")).click();
			// Locate the Name of lien Holder search box and enter a valid data.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_holder_name']")).sendKeys("john mill");
			// Locate the amount of lien search box and enter a valid amount.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_lien_amount']")).sendKeys("40000");
			// Locate the Terms of Payment search box and enter a valid data.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_pay_terms']")).sendKeys("2years");
			Thread.sleep(3000);
			// Locate the Create button and click on it.
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();
			Thread.sleep(3000);
			// Select yes for question; 'Are any leans delinquent?'.
			mydriver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_56_comment']")).sendKeys("testing");
			Thread.sleep(3000);
			// Locate the continue button and click on it.
			mydriver.findElement(By.name("commit")).click();

			Thread.sleep(3000);
			// Locate the continue button and click on it.
			mydriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
			Thread.sleep(3000);
		} else {
			System.out.println("The personal Property Section is not Present, Failed");
		}
		// Verify that User navigate to the Next section of 'Notes Payable' in
		// form 413 successfully.
		if (mydriver.getPageSource().contains("Notes Payable")) {
			System.out.println("User is being navigated to the 'Notes Payable' section on the form 413, Pass");
			mydriver.findElement(By.xpath(".//*[@id='answers[57][value]']/label[2]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers[58][value]']/label[1]")).click();
			mydriver.findElement(By.xpath(".//*[@id='answers_59_value']")).sendKeys("45");
			mydriver.findElement(By.name("commit")).click();
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
			// Verify and answer YES to question 'Do you have any Assessed Taxes
			// that were unpaid?'.
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
			System.out
					.println("User did Not successfully navigated to the section 'Assessed Taxes' on Form 413, Failed");
		}
		// Verify data table and complete it if taxes were unpaid.
		if (mydriver.getPageSource().contains("Include only assessed taxes that are unpaid")) {
			System.out.println("Assessed taxes data tabl is present, Pass");
			mydriver.findElement(By.xpath(".//*[@id='answers_60_details_table_wrapper']/div/a[1]/span")).click();
			// Locate the whom payable search box and enter a valid data.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_whom_payable']")).sendKeys("Max Mill");
			// Locate the amount search box and enter a valid amount.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_amount']")).sendKeys("23000");
			// Locate the when due search box, clear existing data and enter a
			// valid date.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_when_due']")).clear();
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_when_due']")).sendKeys("03/04/2017");
			// Locate the property search box and enter the type of property the
			// tax lien attaches to.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_property_tax_lien']")).sendKeys("House");
			// locate the create button and click on it.
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

		} else {
			System.out.println(
					"Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
		}
		Thread.sleep(3000);
		// Verify and select YES for the next assessed taxes section question;
		// 'Do you have any other liabilities?'.
		mydriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
		mydriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();

		if (mydriver.getPageSource().contains("Complete this section only if liabilities were not listed elsewhere")) {
			System.out.println(
					"Assessed taxes data tabl is present for question 'Do you have any other liabilities?', Pass");
			mydriver.findElement(By.xpath(".//*[@id='answers_61_details_table_wrapper']/div/a[1]/span")).click();
			// Locate the Liability search box and enter a valid data.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_liability']")).sendKeys("40000");
			// Locate the Amount Owned search box and enter a valid amount.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_amount_owed']")).sendKeys("30000");
			// Locate the Description search box and enter a valid date.
			mydriver.findElement(By.xpath(".//*[@id='DTE_Field_description']")).sendKeys("House");
			// locate the create button and click on it.
			mydriver.findElement(By.xpath("html/body/div[3]/div/div/div/div[1]/div[4]/div[3]/button")).click();

		} else {
			System.out.println("Assessed Taxes question; 'Do you have any other liabilities?' NOT Present, Failed");
		}
		Thread.sleep(3000);

		// Locate and click on the continue button.
		mydriver.findElement(By.name("commit")).click();

		Thread.sleep(4000);
		// Locate the next 3 search boxes for Adjusted Gross Income and enter
		// valid data.
		mydriver.findElement(By.xpath(".//*[@id='answers_62_value']")).sendKeys("30000");
		mydriver.findElement(By.xpath(".//*[@id='answers_63_value']")).sendKeys("10000");
		mydriver.findElement(By.xpath(".//*[@id='answers_64_value']")).sendKeys("20000");
		// Locate and click on the continue button.
		mydriver.findElement(By.name("commit")).click();

		Thread.sleep(4000);
		// Verify that user is being navigated to the Summary Page.
		if (mydriver.getPageSource().contains("Financial Summary")) {
			System.out.println("User successfully navigated to the Summary Page on form413, Pass");
		} else {
			System.out.println("User Did Not successfully navigated to the Summary Page on form413, Failed");
			mydriver.quit();
		}

		// Locate and click on the continue button.
		mydriver.findElement(By.name("commit")).click();
		Thread.sleep(5000);
		System.out.println("Detail test for the Privacy Statements section for Paul Washington on form413 begins here");
		Thread.sleep(4000);

		// Verify that user is being navigated to the Privacy Statements Page.
		String actual_Text1 = mydriver.findElement(By.cssSelector("h2.usa-heading")).getText();
		String expected_Text1 = "Privacy Statements";
		assertEquals(actual_Text1, expected_Text1);
		// Click on the save and continue button.
		mydriver.findElement(By.name("commit")).click();
		Thread.sleep(4000);
		// Verify that user is being navigated to the Review Page.
		String actual_Text2 = mydriver.findElement(By.cssSelector("h2.usa-heading.review")).getText();
		String expected_Text2 = "Review";
		assertEquals(actual_Text2, expected_Text2);
		mydriver.findElement(By.cssSelector("div.print-summary > #review")).click();

		Thread.sleep(4000);
		String actual_Text3 = mydriver.findElement(By.id("alert-cancel-button")).getText();
		String expected_Text3 = "Return to edit application";
		assertEquals(actual_Text3, expected_Text3);
		mydriver.findElement(By.id("alert-continue-button")).click();
		// Verify that user is being navigated to the Signature Page.
		String actual_Text4 = mydriver.findElement(By.cssSelector("h2.usa-heading")).getText();
		String expected_Text4 = "Signature";
		assertEquals(actual_Text4, expected_Text4);
		// Verify that the accept button is present..
		String actual_Text5 = mydriver.findElement(By.id("accept-button")).getText();
		String expected_Text5 = "Accept";
		assertEquals(actual_Text5, expected_Text5);

	}

	private void assertEquals(String actual_Text1, String expected_Text1) {
		// TODO Auto-generated method stub

	}
}