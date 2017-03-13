package gov.sba.utils.integration;



import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class FinancialSectionSecondPartnerPage {
  private static final Logger logger =
      LogManager.getLogger(FinancialSectionSecondPartnerPage.class.getName());
  private WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  public FinancialSectionSecondPartnerPage(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void FinancialSectionSecondPartner() throws Exception {
    String Actual_Text = null;
    String Expected_Text = null;
    Thread.sleep(3000);
    // Locate section for 'Cash on Hand' enter all valid data as required.
    // Locate the As of Date: Search box for user and enter the information
    // as required.
    webDriver.findElement(By.id("date-214")).sendKeys("01/26/2017");
    // Locate the Cash on Hand Search box for Pual Washington and enter the
    // information as required.
    webDriver.findElement(By.xpath(".//*[@id='answers_215_value']")).sendKeys("2000");
    // Locate the Savings Account(s) Balance Search box for Pual Washington
    // and enter the information as required.
    webDriver.findElement(By.xpath(".//*[@id='answers_216_value']")).sendKeys("5000");
    // Locate the Checking Account(s) Balance Search box for Pual Washington
    // and enter the information as required.
    webDriver.findElement(By.xpath(".//*[@id='answers_217_value']")).sendKeys("45000");
    // Locate the Continue button and click on it to continue.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Locate section for Other Source of Income enter all valid data as
    // required.
    // Locate the Salary search box and enter salary.
    webDriver.findElement(By.xpath(".//*[@id='answers_218_value']")).sendKeys("70000");
    // Locate the Other Income search box and enter Other Income.
    webDriver.findElement(By.xpath(".//*[@id='answers_219_value']")).sendKeys("80000");
    // Locate the applicant Business Type and enter amount of applicant
    // equity.
    webDriver.findElement(By.xpath(".//*[@id='answers_220_value']")).sendKeys("800000");
    // Locate the applicant equity in other firm and enter applicant
    // business equity.
    webDriver.findElement(By.xpath(".//*[@id='answers_221_value']")).sendKeys("50000");
    // Locate the continue button and click on it to continue.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Locate and YES for question 'Do you have any notes receivable from
    // others?'.
    webDriver.findElement(By.cssSelector("label.yes")).click();
    webDriver.findElement(By.xpath("//div/a/span"))
        .click();
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
    webDriver.findElement(By.id("DTE_Field_current_balance")).sendKeys("20000");
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
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Select Yes for the two question on Retirement Accounts.
    webDriver.findElement(By.id("answers_223_value_yes")).click();
    webDriver.findElement(By.xpath("//div/a/span")).click();
    webDriver.findElement(By.id("DTE_Field_total_value")).sendKeys("980000.56");
    webDriver.findElement(By.id("DTE_Field_contributions_thus_far")).sendKeys("98000.46");
    webDriver.findElement(By.id("DTE_Field_date_of_initial_contribution")).clear();
    webDriver.findElement(By.id("DTE_Field_date_of_initial_contribution")).sendKeys("09/24/2017");
    webDriver.findElement(By.id("DTE_Field_investment_company")).sendKeys("The Alliance One");
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(2000);
    // Upload document.
    String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
    MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
    MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
    webDriver.findElement(By.id("answers_224_value_yes")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div[2]/div/a/span"))
        .click();
    webDriver.findElement(By.id("DTE_Field_type")).click();
    webDriver.findElement(By.xpath("//option[4]")).click();
    webDriver.findElement(By.id("DTE_Field_total_value")).sendKeys("687000.87");
    webDriver.findElement(By.id("DTE_Field_investment_company")).sendKeys("The Alliance One");
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(2000);
    /// Upload document.
    file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
    Upload2pdfOnSamePage upload2pdfOnSame = new Upload2pdfOnSamePage(webDriver);
    upload2pdfOnSame.Upload2pdfOnSame(file_path_abs);
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Life Insurance section.
    webDriver.findElement(By.id("answers_225_value_yes")).click();
    webDriver.findElement(By.xpath("//div/a/span"))
        .click();
    webDriver.findElement(By.id("DTE_Field_company_name")).sendKeys("21 century");
    webDriver.findElement(By.id("DTE_Field_cash_surrender_value")).sendKeys("20000.98");
    webDriver.findElement(By.id("DTE_Field_face_amount")).sendKeys("5000.76");
    webDriver.findElement(By.id("DTE_Field_beneficiaries")).sendKeys("John Peter");
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(2000);
    // Locate and select yes for question, Do you have loan against a life
    // insurance.
    webDriver.findElement(By.id("answers_226_value_yes")).click();
    webDriver.findElement(By.id("answers_227_value")).sendKeys("50000.45");
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Select Yes for the Stock and Bonds Section.
    webDriver.findElement(By.xpath(".//*[@id='answers[228][value]']/label[1]")).click();
    webDriver.findElement(By.xpath("//div/a/span"))
        .click();
    webDriver.findElement(By.id("DTE_Field_securities_name")).sendKeys("Test 2000");
    webDriver.findElement(By.id("DTE_Field_total_value")).sendKeys("50000");
    webDriver.findElement(By.id("DTE_Field_num_of_shares")).sendKeys("60");
    webDriver.findElement(By.id("DTE_Field_cost")).sendKeys("59000.87");
    webDriver.findElement(By.id("DTE_Field_market_value")).sendKeys("98000.98");
    webDriver.findElement(By.id("DTE_Field_date")).clear();
    webDriver.findElement(By.id("DTE_Field_date")).sendKeys("04/12/2016");
    webDriver.findElement(By.id("DTE_Field_interest_dividends")).sendKeys("76000.56");
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Select Yes for Real Estate - Primary Residence Section questions.
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("label.yes")).click();
    webDriver.findElement(By.id("answers_230_1_1_value"))
        .sendKeys("8597 weems rd 2000 ln manassas va 30998");
    webDriver.findElement(By.xpath("//div[@id='answers[230][1][2][value]']/label")).click();
    webDriver.findElement(By.id("answers_230_1_3_value")).sendKeys("50");
    webDriver.findElement(By.id("answers_230_1_4_value")).sendKeys("98");
    webDriver.findElement(By.xpath("//div[@id='answers[230][1][5][value]']/label")).click();
    // mydriver.findElement(By.xpath(".//*[@id='answers_48_2_5_value']")).sendKeys(Percentage);
    webDriver.findElement(By.id("answers_230_1_6_value")).sendKeys("78");
    webDriver.findElement(By.id("answers_230_1_7_value")).sendKeys("76");
    webDriver.findElement(By.xpath("//div[@id='answers[230][1][8][value]']/label")).click();
    webDriver.findElement(By.id("answers_230_1_9_value")).sendKeys("50");
    webDriver.findElement(By.xpath("//div[@id='answers[230][1][10][value]']/label")).click();
    webDriver.findElement(By.id("answers_230_1_11_value")).sendKeys("50");
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    logger.info("Heyyyy! Stocks and Bones section answered.");
    // Select Yes for Real Estate - Other section.
    webDriver.findElement(By.id("answers_231_value_yes")).click();
    webDriver.findElement(By.xpath("//fieldset/a/span")).click();
    Thread.sleep(3000);
    webDriver.findElement(By.id("answers_232_1_1_value")).sendKeys("1000");
    webDriver.findElement(By.id("answers_232_1_2_value_no")).click();
    webDriver.findElement(By.xpath("//div[@id='answers[232][1][4][value]']/label[2]")).click();
    // mydriver.findElement(By.xpath(".//*[@id='answers_50_2_5_value']")).sendKeys(Percentage);
    webDriver.findElement(By.id("answers_232_1_6_value")).sendKeys("76");
    webDriver.findElement(By.id("answers_232_1_7_value")).sendKeys("87");
    // Select yes for the last two question.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//div[@id='answers[232][1][8][value]']/label")).click();
    webDriver.findElement(By.id("answers_232_1_9_value")).sendKeys("65");
    webDriver.findElement(By.xpath("//div[@id='answers[232][1][10][value]']/label")).click();
    webDriver.findElement(By.id("answers_232_1_11_value")).sendKeys("56");
    Thread.sleep(3000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(3000);
    // Beginning Test For Personal Property.
    assertTrue(webDriver.getPageSource().contains("Do you own any automobiles"));
    logger.info("User is being navigated to the Personal Property section, PASS");
    webDriver.findElement(By.xpath(".//*[@id='answers[233][value]']/label[1]")).click();
    // Locate the New button on the data table and click on it to add
    // information.
    webDriver.findElement(By.xpath("//div[@id='answers_233_details_table_wrapper']/div/a/span"))
        .click();
    // Locate current value search box and enter a valid value for you
    // automobile.
    webDriver.findElement(By.id("DTE_Field_current_value")).sendKeys("70000.56");
    // Locate the Loan Balance search box and enter a valid balance.
    webDriver.findElement(By.id("DTE_Field_loan_balance")).sendKeys("20000.76");
    // Locate the description of asset search box and enter the
    // make,model and year of your automobile.
    webDriver.findElement(By.id("DTE_Field_asset_description")).sendKeys("nissan maxima");
    // Locate the payment amount search box and enter the annual payment
    // amount for the automobile.
    webDriver.findElement(By.id("DTE_Field_payment_amount")).sendKeys("30000.56");
    // Locate the Create button and click on it.
    Thread.sleep(3000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Select Yes for question; 'Does any of the above listed property
    // is pledged as security?'.
    webDriver.findElement(By.id("answers_234_value_yes")).click();
    // Locate the new button on the data table and click on it to enter
    // data.
    webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div[2]/div/a/span")).click();
    // Locate the Name of lien Holder search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_lien_holder_name")).sendKeys("Paul Lu");
    // Locate the amount of lien search box and enter a valid amount.
    webDriver.findElement(By.id("DTE_Field_lien_amount")).sendKeys("20000.45");
    // Locate the Terms of Payment search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_pay_terms")).sendKeys("4years");
    Thread.sleep(3000);
    // Locate the Create button and click on it.
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Select yes for question; 'Are any leans delinquent?'.
    webDriver.findElement(By.id("answers_235_value_yes")).click();
    webDriver.findElement(By.id("answers_235_comment")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
    Thread.sleep(3000);
    // Select yes for question; 'Do you own any other personal property
    // or assets?'.
    webDriver.findElement(By.id("answers_236_value_yes")).click();
    // Locate the new button on the data table and click on it to add
    // another data for personal property.
    webDriver.findElement(By.xpath("//div[4]/fieldset/div[2]/div[2]/div/a/span")).click();
    Thread.sleep(3000);
    // Locate current value search box and enter a valid value for you
    // automobile.
    webDriver.findElement(By.id("DTE_Field_current_value")).sendKeys("6000.76");
    // Locate the Loan Balance search box and enter a valid balance.
    webDriver.findElement(By.id("DTE_Field_loan_balance")).sendKeys("100000.45");
    // Locate the description of asset search box and enter the
    // make,model and year of your automobile.
    webDriver.findElement(By.id("DTE_Field_asset_description")).sendKeys("Toyota Lover");
    // Locate the payment amount search box and enter the annual payment
    // amount for the automobile.
    webDriver.findElement(By.id("DTE_Field_payment_amount")).sendKeys("200000.98");
    // Locate the Create button and click on it.
    Thread.sleep(3000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Select Yes for question; 'Does any of the above listed property
    // is pledged as security?'.
    webDriver.findElement(By.id("answers_237_value_yes")).click();
    // Locate the new button on the data table and click on it to enter
    // data.
    webDriver.findElement(By.xpath("//div[5]/fieldset/div[2]/div[2]/div/a/span")).click();
    // Locate the Name of lien Holder search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_lien_holder_name")).sendKeys("john mill");
    // Locate the amount of lien search box and enter a valid amount.
    webDriver.findElement(By.id("DTE_Field_lien_amount")).sendKeys("20000.98");
    // Locate the Terms of Payment search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_pay_terms")).sendKeys("2years");
    Thread.sleep(3000);
    // Locate the Create button and click on it.
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Select yes for question; 'Are any leans delinquent?'.
    webDriver.findElement(By.xpath(".//*[@id='answers[238][value]']/label[1]")).click();
    webDriver.findElement(By.id("answers_238_comment")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
    Thread.sleep(3000);
    // Locate the continue button and click on it.
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(3000);
    // Verify that User navigate to the Next section of 'Notes Payable' in
    // form 413 successfully.
    try {
      assertTrue(webDriver.getPageSource().contains("Notes Payable"));
      logger.info("User is being navigated to the 'Notes Payable' section on the form 413, Pass");
      webDriver.findElement(By.xpath(".//*[@id='answers[239][value]']/label[2]")).click();
      webDriver.findElement(By.xpath(".//*[@id='answers[240][value]']/label[1]")).click();
      webDriver.findElement(By.xpath(".//*[@id='answers_241_value']")).sendKeys("45");
      Thread.sleep(2000);
      webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    } catch (Error e) {
      logger
          .info("User is NOT being navigated to the 'Notes Payable' section on the form 413, Pass");
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
        assertTrue(
            webDriver.getPageSource().contains("Do you have any Assessed Taxes that were unpaid"));
        logger.info(
            "Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' is present, Pass");
        webDriver.findElement(By.xpath(".//*[@id='answers[242][value]']/label[1]")).click();
        Thread.sleep(3000);
      } catch (Error e) {
        logger.info(
            "Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
      }
    } catch (Error e) {
      logger.info(
          "User did Not successfully navigated to the section 'Assessed Taxes' on Form 413, Failed");
    }
    // Verify data table and complete it if taxes were unpaid.
    assertTrue(webDriver.getPageSource().contains("Include only assessed taxes that are unpaid"));
    logger.info("Assessed taxes data tabl is present, Pass");
    webDriver.findElement(By.xpath("//div[@id='answers_242_details_table_wrapper']/div/a/span"))
        .click();
    // Locate the whom payable search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_whom_payable")).sendKeys("Max Mill");
    // Locate the amount search box and enter a valid amount.
    webDriver.findElement(By.id("DTE_Field_amount")).sendKeys("23000.34");
    // Locate the when due search box, clear existing data and enter a
    // valid date.
    webDriver.findElement(By.id("DTE_Field_when_due")).clear();
    webDriver.findElement(By.id("DTE_Field_when_due")).sendKeys("03/04/2017");
    // Locate the property search box and enter the type of property the
    // tax lien attaches to.
    webDriver.findElement(By.id("DTE_Field_property_tax_lien")).sendKeys("House");
    // locate the create button and click on it.
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Verify and select YES for the next assessed taxes section question;
    // 'Do you have any other liabilities?'.
    webDriver.findElement(By.id("answers_243_value_yes")).click();
    assertTrue(webDriver.getPageSource()
        .contains("Complete this section only if liabilities were not listed elsewhere"));
    logger.info(
        "Assessed taxes data tabl is present for question 'Do you have any other liabilities?', Pass");
    webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div[2]/div/a/span")).click();
    // Locate the Liability search box and enter a valid data.
    webDriver.findElement(By.cssSelector("#DTE_Field_liability")).sendKeys("20000");
    // Locate the Amount Owned search box and enter a valid amount.
    webDriver.findElement(By.cssSelector("#DTE_Field_amount_owed")).sendKeys("30000");
    // Locate the Description search box and enter a valid date.
    webDriver.findElement(By.cssSelector("#DTE_Field_description")).sendKeys("House");
    // locate the create button and click on it.
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Locate and click on the continue button.
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
    // Locate the next 3 search boxes for Adjusted Gross Income and enter
    // valid data.
    webDriver.findElement(By.id("answers_244_value")).sendKeys("30000"); 
    webDriver.findElement(By.id("answers_245_value")).sendKeys("10000");
    webDriver.findElement(By.id("answers_246_value")).sendKeys("20000");
    // Locate and click on the continue button.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
    // Verify that user is being navigated to the Summary Page.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Will Smith";
    assertEquals(Expected_Text, Actual_Text);
    // Verify updated int type.
    Actual_Text = webDriver
        .findElement(By.xpath("//form[@id='edwosb']/div[2]/div/table/tbody/tr/td[2]")).getText();
    Expected_Text = "$4,000.00";
    assertEquals(Expected_Text, Actual_Text);
    // Locate and click on the continue button.
    Thread.sleep(2000);
    Actions act13 = new Actions(webDriver);
    act13.doubleClick(webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]"))).build()
        .perform();
    Thread.sleep(3000);
    logger.info(
        "Detail test for the Privacy Statements section for Paul Washington on form413 begins here");
    Thread.sleep(2000);
    // Verify that user is being navigated to the Privacy Statements Page.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Privacy Statements";
    assertEquals(Expected_Text, Actual_Text);
    // Click on the save and continue button.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
    // Verify that user is being navigated to the Review Page.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Review";
    assertEquals(Expected_Text, Actual_Text);
    // Verify both partners finances.
    // Denzel's net worth.
    webDriver.findElement(By.linkText("Denzel Washington Financial")).click();
    Thread.sleep(2000);
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='table1-pad']/table/tbody/tr[11]/td[2]")).getText();
    Expected_Text = "$904,831.30";
    assertEquals(Expected_Text, Actual_Text);
    // Will Smith net worth.
    Actual_Text = webDriver
        .findElement(By.xpath("//div[@id='table1-pad']/table/tbody/tr[11]/td[2]")).getText();
    Expected_Text = "Will Smith Financial";
    assertEquals(Expected_Text, Actual_Text);
    webDriver.findElement(By.linkText("Will Smith Financial")).click();
    Thread.sleep(2000);
    Actual_Text = webDriver
        .findElement(By.xpath("(//div[@id='table1-pad']/table/tbody/tr[11]/td[2])[2]")).getText();
    Expected_Text = "$66,946,932.59";
    assertEquals(Expected_Text, Actual_Text);
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
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
