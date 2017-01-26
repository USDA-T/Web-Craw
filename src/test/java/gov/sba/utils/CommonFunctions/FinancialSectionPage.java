package gov.sba.utils.CommonFunctions;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FinancialSectionPage {
    private static final Logger logger = LogManager.getLogger(FinancialSectionPage.class.getName());
    private WebDriver webDriver;

    public FinancialSectionPage(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void Financialsection() throws Exception {
        Thread.sleep(3000);
        // Locate section for 'Cash on Hand' enter all valid data as required.
        // Locate the As of Date: Search box for user and enter the information
        // as required.
        webDriver.findElement(By.id("date-32")).sendKeys("04/23/2016");
        // Locate the Cash on Hand Search box for Pual Washington and enter the
        // information as required.
        webDriver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys("4000");
        // Locate the Savings Account(s) Balance Search box for Pual Washington
        // and enter the information as required.
        webDriver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys("5000");
        // Locate the Checking Account(s) Balance Search box for Pual Washington
        // and enter the information as required.
        webDriver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys("45000");
        // Locate the Continue button and click on it to continue.
        webDriver.findElement(By.name("commit")).click();
        // Locate section for Other Source of Income enter all valid data as
        // required.
        // Locate the Salary search box and enter salary.
        webDriver.findElement(By.xpath(".//*[@id='answers_36_value']")).sendKeys("70000");
        // Locate the Other Income search box and enter Other Income.
        webDriver.findElement(By.xpath(".//*[@id='answers_37_value']")).sendKeys("80000");
        // Locate the applicant Business Type and enter amount of applicant
        // equity.
        webDriver.findElement(By.xpath(".//*[@id='answers_38_value']")).sendKeys("800000");
        // Locate the applicant equity in other firm and enter applicant
        // business equity.
        webDriver.findElement(By.xpath(".//*[@id='answers_39_value']")).sendKeys("50000");
        // Locate the continue button and click on it to continue.
        webDriver.findElement(By.name("commit")).click();
        // Locate and YES for question 'Do you have any notes receivable from
        // others?'.
        webDriver.findElement(By.cssSelector("label.yes")).click();
        webDriver.findElement(By.xpath("//div[@id='answers_40_details_table_wrapper']/div/a/span")).click();
        logger.info(
                "User is prompted to enter Atleast one row  field or to select no if not applicable. Valid error Message is ");
        Thread.sleep(3000);
        // Locate the 'Name of Debtor' search box and enter a valid name.
        webDriver.findElement(By.id("DTE_Field_debtor_name")).sendKeys("John Mall");
        // Locate the 'Address of Debtor' search box and enter a valid address.
        webDriver.findElement(By.id("DTE_Field_debtor_address")).sendKeys("8765 Kings st MD 23445");
        // Locate the 'Original Balance' search box and enter a valid balance.
        webDriver.findElement(By.id("DTE_Field_original_balance")).sendKeys("80000");
        // Locate the 'Current Balance' Search box and enter a valid balance.
        webDriver.findElement(By.id("DTE_Field_current_balance")).sendKeys("40000");
        // Locate the 'Payment Amount(Calculated Annually)' and enter a valid
        // Amount.
        webDriver.findElement(By.id("DTE_Field_pay_amount")).sendKeys("60000");
        // Locate the 'How Secured or Endorsed / Type of Collateral' search box
        // and enter a valid Collateral type(s).
        webDriver.findElement(By.id("DTE_Field_collateral_type")).sendKeys("secured");
        // Locate the 'Create' button at the Right bottom of the Create new
        // entry page and click on it .
        webDriver.findElement(By.cssSelector("button.btn")).click();
        // locate the 'CONTINUE' button at the right bottom of the page and
        // click on it to save
        Thread.sleep(3000);
        webDriver.findElement(By.name("commit")).click();
        // Select NO for the two question on Retirement Accounts.
        webDriver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();

        Thread.sleep(4000);

        webDriver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();

        Thread.sleep(4000);

        webDriver.findElement(By.name("commit")).click();

        webDriver.findElement(By.id("answers_43_value_yes")).click();
        webDriver.findElement(By.xpath("//div[@id='answers_43_details_table_wrapper']/div/a/span")).click();
        webDriver.findElement(By.id("DTE_Field_company_name")).sendKeys("21 century");
        webDriver.findElement(By.id("DTE_Field_cash_surrender_value")).sendKeys("40000");
        webDriver.findElement(By.id("DTE_Field_face_amount")).sendKeys("5000");
        webDriver.findElement(By.id("DTE_Field_beneficiaries")).sendKeys("John Peter");
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(5000);
        // Locate and select yes for question, Do you have loan against a life
        // insurance.
        webDriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
        webDriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
        webDriver.findElement(By.xpath(".//*[@id='answers_45_value']")).sendKeys("50000");
        webDriver.findElement(By.name("commit")).click();
        // Select Yes for the Stock and Bonds Section.
        webDriver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[1]")).click();
        webDriver.findElement(By.xpath("//div[@id='answers_46_details_table_wrapper']/div/a/span")).click();
        webDriver.findElement(By.id("DTE_Field_securities_name")).sendKeys("Test Testing");
        webDriver.findElement(By.id("DTE_Field_total_value")).sendKeys("50000");
        webDriver.findElement(By.id("DTE_Field_num_of_shares")).sendKeys("60");
        webDriver.findElement(By.id("DTE_Field_cost")).sendKeys("59000");
        webDriver.findElement(By.id("DTE_Field_market_value")).sendKeys("98000");
        webDriver.findElement(By.id("DTE_Field_interest_dividends")).sendKeys("76000");
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.name("commit")).click();
        // Select Yes for Real Estate - Primary Residence Section questions.
        webDriver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[1]")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.id("answers_48_1_1_value")).sendKeys("8597 weems rd testing ln manassas va 30998");
        webDriver.findElement(By.xpath("//div[@id='answers[48][1][2][value]']/label")).click();
        webDriver.findElement(By.id("answers_48_1_3_value")).sendKeys("50");
        webDriver.findElement(By.id("answers_48_1_4_value")).sendKeys("98");
        webDriver.findElement(By.xpath("//div[@id='answers[48][1][5][value]']/label")).click();
        // mydriver.findElement(By.xpath(".//*[@id='answers_48_2_5_value']")).sendKeys(Percentage);
        webDriver.findElement(By.id("answers_48_1_6_value")).sendKeys("78");
        webDriver.findElement(By.id("answers_48_1_7_value")).sendKeys("76");
        webDriver.findElement(By.xpath("//div[@id='answers[48][1][8][value]']/label")).click();
        webDriver.findElement(By.id("answers_48_1_9_value")).sendKeys("50");
        webDriver.findElement(By.xpath("//div[@id='answers[48][1][10][value]']/label")).click();
        webDriver.findElement(By.id("answers_48_1_11_value")).sendKeys("50");
        webDriver.findElement(By.name("commit")).click();
        // Select Yes for Real Estate - Other section.
        webDriver.findElement(By.xpath(".//*[@id='answers[49][value]']/label[1]")).click();
        webDriver.findElement(By.id("add_real_estate")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.id("answers_50_1_1_value")).sendKeys("1000");
        webDriver.findElement(By.id("answers_50_1_2_value_no")).click();
        webDriver.findElement(By.xpath("//div[@id='answers[50][1][4][value]']/label[2]")).click();
        // mydriver.findElement(By.xpath(".//*[@id='answers_50_2_5_value']")).sendKeys(Percentage);
        webDriver.findElement(By.id("answers_50_1_6_value")).sendKeys("76");
        webDriver.findElement(By.id("answers_50_1_7_value")).sendKeys("87");
        // Select yes for the last two question.
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//div[@id='answers[50][1][8][value]']/label")).click();
        webDriver.findElement(By.id("answers_50_1_9_value")).sendKeys("65");
        webDriver.findElement(By.xpath("//div[@id='answers[50][1][10][value]']/label")).click();
        webDriver.findElement(By.id("answers_50_1_11_value")).sendKeys("56");
        Thread.sleep(3000);
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(3000);
        // Beginning Test For Personal Property.
        assertTrue(webDriver.getPageSource().contains("Do you own any automobiles"));
        logger.info("User is being navigated to the Personal Property section, PASS");
        webDriver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[1]")).click();
        // Locate the New button on the data table and click on it to add
        // information.
        webDriver.findElement(By.xpath("//div[@id='answers_51_details_table_wrapper']/div/a/span")).click();
        // Locate current value search box and enter a valid value for you
        // automobile.
        webDriver.findElement(By.id("DTE_Field_current_value")).sendKeys("70000");
        // Locate the Loan Balance search box and enter a valid balance.
        webDriver.findElement(By.id("DTE_Field_loan_balance")).sendKeys("40000");
        // Locate the description of asset search box and enter the
        // make,model and year of your automobile.
        webDriver.findElement(By.id("DTE_Field_asset_description")).sendKeys("nissan maxima");
        // Locate the payment amount search box and enter the annual payment
        // amount for the automobile.
        webDriver.findElement(By.id("DTE_Field_payment_amount")).sendKeys("30000");
        // Locate the Create button and click on it.
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        // Select Yes for question; 'Does any of the above listed property
        // is pledged as security?'.
        webDriver.findElement(By.xpath("//div[2]/fieldset/div/label")).click();
        // Locate the new button on the data table and click on it to enter
        // data.
        webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div[2]/div/a/span")).click();
        // Locate the Name of lien Holder search box and enter a valid data.
        webDriver.findElement(By.id("DTE_Field_lien_holder_name")).sendKeys("Paul Lu");
        // Locate the amount of lien search box and enter a valid amount.
        webDriver.findElement(By.id("DTE_Field_lien_amount")).sendKeys("40000");
        // Locate the Terms of Payment search box and enter a valid data.
        webDriver.findElement(By.id("DTE_Field_pay_terms")).sendKeys("4years");
        Thread.sleep(3000);
        // Locate the Create button and click on it.
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        // Select yes for question; 'Are any leans delinquent?'.
        webDriver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[1]")).click();
        webDriver.findElement(By.id("answers_53_comment")).sendKeys("Testing");
        Thread.sleep(3000);
        // Select yes for question; 'Do you own any other personal property
        // or assets?'.
        webDriver.findElement(By.xpath(".//*[@id='answers[54][value]']/label[1]")).click();
        // Locate the new button on the data table and click on it to add
        // another data for personal property.
        webDriver.findElement(By.xpath("//div[3]/div/a/span")).click();
        Thread.sleep(3000);
        // Locate current value search box and enter a valid value for you
        // automobile.
        webDriver.findElement(By.id("DTE_Field_current_value")).sendKeys("6000");
        // Locate the Loan Balance search box and enter a valid balance.
        webDriver.findElement(By.id("DTE_Field_loan_balance")).sendKeys("100000");
        // Locate the description of asset search box and enter the
        // make,model and year of your automobile.
        webDriver.findElement(By.id("DTE_Field_asset_description")).sendKeys("Toyota Lover");
        // Locate the payment amount search box and enter the annual payment
        // amount for the automobile.
        webDriver.findElement(By.id("DTE_Field_payment_amount")).sendKeys("400000");
        // Locate the Create button and click on it.
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        // Select Yes for question; 'Does any of the above listed property
        // is pledged as security?'.
        webDriver.findElement(By.xpath(".//*[@id='answers[55][value]']/label[1]")).click();
        // Locate the new button on the data table and click on it to enter
        // data.
        webDriver.findElement(By.xpath("//div[5]/fieldset/div[2]/div[2]/div/a/span")).click();
        // Locate the Name of lien Holder search box and enter a valid data.
        webDriver.findElement(By.id("DTE_Field_lien_holder_name")).sendKeys("john mill");
        // Locate the amount of lien search box and enter a valid amount.
        webDriver.findElement(By.id("DTE_Field_lien_amount")).sendKeys("40000");
        // Locate the Terms of Payment search box and enter a valid data.
        webDriver.findElement(By.id("DTE_Field_pay_terms")).sendKeys("2years");
        Thread.sleep(3000);
        // Locate the Create button and click on it.
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        // Select yes for question; 'Are any leans delinquent?'.
        webDriver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[1]")).click();
        webDriver.findElement(By.id("answers_56_comment")).sendKeys("testing");
        Thread.sleep(3000);
        // Locate the continue button and click on it.
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(3000);
        // Locate the continue button and click on it.
        webDriver.findElement(By.xpath(".//*[@id='edwosb']/input[4]")).click();
        Thread.sleep(3000);
        // Verify that User navigate to the Next section of 'Notes Payable' in
        // form 413 successfully.
        try {
            assertTrue(webDriver.getPageSource().contains("Notes Payable"));
            logger.info("User is being navigated to the 'Notes Payable' section on the form 413, Pass");
            webDriver.findElement(By.xpath(".//*[@id='answers[57][value]']/label[2]")).click();
            webDriver.findElement(By.xpath(".//*[@id='answers[58][value]']/label[1]")).click();
            webDriver.findElement(By.xpath(".//*[@id='answers_59_value']")).sendKeys("45");
            webDriver.findElement(By.name("commit")).click();
        } catch (Error e) {
            logger.info("User is NOT being navigated to the 'Notes Payable' section on the form 413, Pass");
        }
        Thread.sleep(3000);
        // US660 'Assessed Taxes' test begin here.
        logger.info("US660 'Assessed Taxes' test begin here");
        // Verify that user successfully navigated to the section 'Assessed
        // Taxes' on form 413.
        try {
            assertTrue(webDriver.getPageSource().contains("Assessed Taxes"));
            logger.info("User successfully navigated to the section 'Assessed Taxes' on Form 413, Pass");
            // Verify and answer YES to question 'Do you have any Assessed Taxes
            // that were unpaid?'.
            try {
                assertTrue(webDriver.getPageSource().contains("Do you have any Assessed Taxes that were unpaid"));
                logger.info(
                        "Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' is present, Pass");
                webDriver.findElement(By.xpath(".//*[@id='answers[60][value]']/label[1]")).click();
                Thread.sleep(3000);
            } catch (Error e) {
                logger.info(
                        "Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
            }
        } catch (Error e) {
            logger.info("User did Not successfully navigated to the section 'Assessed Taxes' on Form 413, Failed");
        }
        // Verify data table and complete it if taxes were unpaid.

        assertTrue(webDriver.getPageSource().contains("Include only assessed taxes that are unpaid"));
        logger.info("Assessed taxes data tabl is present, Pass");
        webDriver.findElement(By.xpath("//div[@id='answers_60_details_table_wrapper']/div/a/span")).click();
        // Locate the whom payable search box and enter a valid data.
        webDriver.findElement(By.id("DTE_Field_whom_payable")).sendKeys("Max Mill");
        // Locate the amount search box and enter a valid amount.
        webDriver.findElement(By.id("DTE_Field_amount")).sendKeys("23000");
        // Locate the when due search box, clear existing data and enter a
        // valid date.
        webDriver.findElement(By.id("DTE_Field_when_due")).clear();
        webDriver.findElement(By.id("DTE_Field_when_due")).sendKeys("03/04/2017");
        // Locate the property search box and enter the type of property the
        // tax lien attaches to.
        webDriver.findElement(By.id("DTE_Field_property_tax_lien")).sendKeys("House");
        // locate the create button and click on it.
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        // Verify and select YES for the next assessed taxes section question;
        // 'Do you have any other liabilities?'.
        webDriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
        webDriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
        assertTrue(webDriver.getPageSource()
                .contains("Complete this section only if liabilities were not listed elsewhere"));
        logger.info("Assessed taxes data tabl is present for question 'Do you have any other liabilities?', Pass");
        webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div[3]/div/a/span")).click();
        // Locate the Liability search box and enter a valid data.
        webDriver.findElement(By.cssSelector("#DTE_Field_liability")).sendKeys("40000");
        // Locate the Amount Owned search box and enter a valid amount.
        webDriver.findElement(By.cssSelector("#DTE_Field_amount_owed")).sendKeys("30000");
        // Locate the Description search box and enter a valid date.
        webDriver.findElement(By.cssSelector("#DTE_Field_description")).sendKeys("House");
        // locate the create button and click on it.
        webDriver.findElement(By.cssSelector("button.btn")).click();
        Thread.sleep(3000);
        // Locate and click on the continue button.
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(4000);
        // Locate the next 3 search boxes for Adjusted Gross Income and enter
        // valid data.
        webDriver.findElement(By.id("answers_62_value")).sendKeys("30000");
        webDriver.findElement(By.id("answers_63_value")).sendKeys("10000");
        webDriver.findElement(By.id("answers_64_value")).sendKeys("20000");
        // Locate and click on the continue button.
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(4000);
        // Verify that user is being navigated to the Summary Page.
        String actual_Text6 = webDriver.findElement(By.cssSelector("h2")).getText();
        String expected_Text6 = "Denzel Washington";
        assertEquals(actual_Text6, expected_Text6);
        // Verify updated int type.
        String actual_Text11 = webDriver.findElement(By.xpath("//form[@id='edwosb']/div[2]/div/table/tbody/tr/td[2]"))
                .getText();
        String expected_Text11 = "$4,000.00";
        assertEquals(actual_Text11, expected_Text11);
        // Locate and click on the continue button.
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(5000);
        logger.info("Detail test for the Privacy Statements section for Paul Washington on form413 begins here");
        Thread.sleep(4000);
        // Verify that user is being navigated to the Privacy Statements Page.
        String actual_Text1 = webDriver.findElement(By.cssSelector("h2")).getText();
        String expected_Text1 = "Privacy Statements";
        assertEquals(actual_Text1, expected_Text1);
        // Click on the save and continue button.
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(4000);
        // Verify that user is being navigated to the Review Page.
        String actual_Text2 = webDriver.findElement(By.cssSelector("h2")).getText();
        String expected_Text2 = "Review";
        assertEquals(actual_Text2, expected_Text2);
        webDriver.findElement(By.name("commit")).click();
        Thread.sleep(4000);
        logger.info(webDriver.switchTo().alert().getText());
        webDriver.switchTo().alert().accept();
        // Verify that user is being navigated to the Signature Page.
        String actual_Text4 = webDriver.findElement(By.cssSelector("h2")).getText();
        String expected_Text4 = "Signature";
        assertEquals(actual_Text4, expected_Text4);
        // Verify that the accept button is present..
        String actual_Text5 = webDriver.findElement(By.id("accept-button")).getText();
        String expected_Text5 = "Accept";
        assertEquals(actual_Text5, expected_Text5);
    }

    private void assertEquals(String actual_Text1, String expected_Text1) {
        // TODO Auto-generated method stub
    }
}
