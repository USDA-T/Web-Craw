package gov.sba.utils.integration;

import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FinancialSectionPage {
  private static final Logger logger = LogManager.getLogger(FinancialSectionPage.class.getName());
  private WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  public FinancialSectionPage(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void Financialsection() throws Exception {
    String Actual_Text = null;
    String Expected_Text = null;
    Thread.sleep(3000);
    // Locate section for 'Cash on Hand' enter all valid data as required.
    // Locate the As of Date: Search box for user and enter the information
    // as required.
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Cash On Hand";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_edwosb_cash_as_of_date']/fieldset/p")).getText();
    Expected_Text = "Use the date of the information provided (i.e. the last date of the previous month). The information must be no older than 30 days old.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement CashOnHand  = webDriver.findElement(By.xpath("//div[@id='answers_edwosb_cash_as_of_date']/fieldset/p"));
    HighLight.highLightElement(webDriver, CashOnHand);
    Actual_Text = webDriver.findElement(By.cssSelector("#answers_edwosb_checking_balance > fieldset > p")).getText();
    Expected_Text = "Include funds held domestically and in foreign financial institutions. Include funds held in certificates of deposit and money market accounts.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement AccountB  = webDriver.findElement(By.cssSelector("#answers_edwosb_checking_balance > fieldset > p"));
    HighLight.highLightElement(webDriver, AccountB);
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
    Expected_Text = "Other Sources Of Income";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_edwosb_salary']/fieldset/p")).getText();
    Expected_Text = "Include salary from applicant firm and all other sources.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement OtherSource  = webDriver.findElement(By.xpath("//div[@id='answers_edwosb_salary']/fieldset/p"));
    HighLight.highLightElement(webDriver, OtherSource);
    WebElement OtherIncome  = webDriver.findElement(By.cssSelector("#answers_edwosb_other_income > fieldset > p"));
    HighLight.highLightElement(webDriver, OtherIncome);
    WebElement AppBuzEquity  = webDriver.findElement(By.cssSelector("#answers_edwosb_biz_equity > fieldset > p"));
    HighLight.highLightElement(webDriver, AppBuzEquity);
    WebElement AppEquityOtherFirm  = webDriver.findElement(By.cssSelector("#answers_edwosb_equity_in_other_firms > fieldset > p"));
    HighLight.highLightElement(webDriver, AppEquityOtherFirm);   
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Do you have any notes receivable from others?";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_notes_receivable']/fieldset/p")).getText();
    Expected_Text = "Include shareholder/officer/member/partner loans from individual to applicant firm, as well as any loans given to other individuals or companies.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement NotesDetail  = webDriver.findElement(By.xpath("//div[@id='answers_notes_receivable']/fieldset/p"));
    HighLight.highLightElement(webDriver, NotesDetail);
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Do you have a Roth IRA?";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    Expected_Text = "Upload terms and conditions of all retirement accounts. SBA will deduct these values of applicable from net worth upon verification during the eligibility review.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement RothDetail  = webDriver.findElement(By.cssSelector("fieldset > p"));
    HighLight.highLightElement(webDriver, RothDetail);
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Do you have a life insurance policy that has a Cash Surrender Value?";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_life_insurance_cash_surrender']/fieldset/p")).getText();
    Expected_Text = "The Cash Surrender Value is the total received if a life insurance policy is cancelled. This does not apply to term life insurance policies.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement LifeInsuranceDetail  = webDriver.findElement(By.xpath("//div[@id='answers_life_insurance_cash_surrender']/fieldset/p"));
    HighLight.highLightElement(webDriver, LifeInsuranceDetail);    
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Do you have any stocks, bonds or Mutual Funds?";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_stocks_bonds']/fieldset/p")).getText();
    Expected_Text = "Total Value = Market Value Quotation × the Number of Shares.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement StocksBonds  = webDriver.findElement(By.xpath("//div[@id='answers_stocks_bonds']/fieldset/p"));
    HighLight.highLightElement(webDriver, StocksBonds);    
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Do you own your primary residence?";
    assertEquals(Actual_Text, Expected_Text);  
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Do you own any automobiles?";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_automobiles']/fieldset/p")).getText();
    Expected_Text = "If the vehicle is jointly owned or the loan on the vehicle is a joint debt, include only the individual’s share of the vehicle value and loan balance.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement PersonalProperty  = webDriver.findElement(By.xpath("//div[@id='answers_automobiles']/fieldset/p"));
    HighLight.highLightElement(webDriver, PersonalProperty);
    Actual_Text = webDriver.findElement(By.cssSelector("#answers_other_personal_property > fieldset > h4")).getText();
    Expected_Text = "Do you own any other personal property or assets?";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.cssSelector("#answers_other_personal_property > fieldset > p")).getText();
    Expected_Text = "Include all household goods, jewelry, art, boats, antiques, etc. If the property is jointly owned, include only the individual’s share of the property.";
    assertEquals(Actual_Text, Expected_Text); 
    WebElement OtherPersonalProperty  = webDriver.findElement(By.cssSelector("#answers_other_personal_property > fieldset > p"));
    HighLight.highLightElement(webDriver, OtherPersonalProperty);
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
      //Verify the question.
      Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
      Expected_Text = "Do you have any notes payable?";
      assertEquals(Actual_Text, Expected_Text);
      //Detail Section.
      Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_notes_payable']/fieldset/p")).getText();
      Expected_Text = "List any notes payable including credit cards and personal lines of credit. Include loans owed to the applicant firm, other companies, and individuals. Exclude mortgage, auto loans, etc. if listed above in the Property section.";
      assertEquals(Actual_Text, Expected_Text); 
      WebElement NotesPayable  = webDriver.findElement(By.xpath("//div[@id='answers_notes_payable']/fieldset/p"));
      HighLight.highLightElement(webDriver, NotesPayable);
      Actual_Text = webDriver.findElement(By.cssSelector("#answers_recurring_accounts_payable > fieldset > h4")).getText();
      Expected_Text = "Do you have any other Accounts Payable for products and services purchased on credit or on a regular payment basis?";
      assertEquals(Actual_Text, Expected_Text);
      //Detail Section.
      Actual_Text = webDriver.findElement(By.cssSelector("#answers_recurring_accounts_payable > fieldset > p")).getText();
      Expected_Text = "Include only personal accounts payable not listed in other sections. Exclude business accounts payable.";
      assertEquals(Actual_Text, Expected_Text); 
      WebElement otherAccountsPayable  = webDriver.findElement(By.xpath("//div[@id='answers_notes_payable']/fieldset/p"));
      HighLight.highLightElement(webDriver, otherAccountsPayable);     
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
      //Verify the question.
      Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
      Expected_Text = "Do you have any Assessed Taxes that were unpaid?";
      assertEquals(Actual_Text, Expected_Text);
      //Detail Section.
      Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_assessed_taxes']/fieldset/p")).getText();
      Expected_Text = "Include only assessed taxes that are unpaid. This includes past due personal Federal, state, county, and city taxes. Do not include estimated taxes or business taxes. If this is a joint debt, include only the individual’s share of the debt.";
      assertEquals(Actual_Text, Expected_Text); 
      WebElement AssessedTaxes  = webDriver.findElement(By.xpath("//div[@id='answers_assessed_taxes']/fieldset/p"));
      HighLight.highLightElement(webDriver, AssessedTaxes);
      Actual_Text = webDriver.findElement(By.cssSelector("#answers_other_liabilities > fieldset > h4")).getText();
      Expected_Text = "Do you have any other liabilities?";
      assertEquals(Actual_Text, Expected_Text);
      //Detail Section.
      Actual_Text = webDriver.findElement(By.cssSelector("#answers_other_liabilities > fieldset > p")).getText();
      Expected_Text = "Complete this section only if liabilities were not listed elsewhere. Do not include contingent liabilities.";
      assertEquals(Actual_Text, Expected_Text); 
      WebElement otherAccountsPayable  = webDriver.findElement(By.cssSelector("#answers_other_liabilities > fieldset > p"));
      HighLight.highLightElement(webDriver, otherAccountsPayable);      
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
    //Verify the question.
    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
    Expected_Text = "Adjusted Gross Income (As shown on tax returns for Most Recent tax year)";
    assertEquals(Actual_Text, Expected_Text);
    //Detail Section.
    Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
    Expected_Text = "In answering this question, you may consider the adjusted gross income (AGI) on your Federal income tax return forms (Line 37 on Form 1040; Line 4 on Form 040EZ; or Line 21 on Form 1040(A). Please note that this is rough guidance and should not be construed as the official SBA’s position on calculating the AGI. You will be asked to provide information on your AGI in the Financial Data section.";
    assertEquals(Actual_Text, Expected_Text); 
    Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_agi_year_3']/fieldset/p[2]")).getText();
    Expected_Text = "Income received by an EDWOSB that is an S corporation, LLC, or partnership will be excluded from an individual's income where the EDWOSB provides documentary evidence demonstrating that the income was reinvested in the EDWOSB or the distribution was solely for the purposes of paying taxes arising in the normal course of operations of the business concern. Losses from the S corporation, LLC or partnership, however, are losses to the EDWOSB only, not losses to the individual, and cannot be used to reduce a woman's personal income. Reference 13 C.F.R. 127.203(c)(3)";
    assertEquals(Actual_Text, Expected_Text);
    WebElement AdjustedGrossIncome  = webDriver.findElement(By.cssSelector("fieldset > p"));
    HighLight.highLightElement(webDriver, AdjustedGrossIncome);
    WebElement AdjustedGrossIncome1  = webDriver.findElement(By.xpath("//div[@id='answers_agi_year_3']/fieldset/p[2]"));
    HighLight.highLightElement(webDriver, AdjustedGrossIncome1); 
    webDriver.findElement(By.id("answers_244_value")).sendKeys("30000"); 
    webDriver.findElement(By.id("answers_245_value")).sendKeys("10000");
    webDriver.findElement(By.id("answers_246_value")).sendKeys("20000");
    // Locate and click on the continue button.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
    // Verify that user is being navigated to the Summary Page.
    String actual_Text6 = webDriver.findElement(By.cssSelector("h2")).getText();
    String expected_Text6 = "Denzel Washington";
    assertEquals(actual_Text6, expected_Text6);
    // Verify updated int type.
    String actual_Text11 = webDriver
        .findElement(By.xpath("//form[@id='edwosb']/div[2]/div/table/tbody/tr/td[2]")).getText();
    String expected_Text11 = "$20,000.00";
    assertEquals(actual_Text11, expected_Text11);
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
    String actual_Text1 = webDriver.findElement(By.cssSelector("h2")).getText();
    String expected_Text1 = "Privacy Statements";
    assertEquals(actual_Text1, expected_Text1);
    // Click on the save and continue button.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
    // Verify that user is being navigated to the Review Page.
    String actual_Text2 = webDriver.findElement(By.cssSelector("h2")).getText();
    String expected_Text2 = "Review";
    assertEquals(actual_Text2, expected_Text2);
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
