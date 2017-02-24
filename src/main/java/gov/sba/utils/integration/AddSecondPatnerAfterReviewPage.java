package gov.sba.utils.integration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import junit.framework.TestCase;

public class AddSecondPatnerAfterReviewPage extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(AddSecondPatnerAfterReviewPage.class.getName());
  private WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  public AddSecondPatnerAfterReviewPage(WebDriver mydriver) {
    this.webDriver = mydriver;
  }

  public void AddSecondPatnerAfterReview() throws Exception {
    Thread.sleep(3000);
    // Locate section for 'Cash on Hand' enter all valid data as required.
    // Locate the As of Date: Search box for user and enter the information
    // as required.
    webDriver.findElement(By.id("date-32")).sendKeys("01/26/2017");
    // Locate the Cash on Hand Search box for Pual Washington and enter the
    // information as required.
    webDriver.findElement(By.xpath(".//*[@id='answers_33_value']")).sendKeys("200000.98");
    // Locate the Savings Account(s) Balance Search box for Pual Washington
    // and enter the information as required.
    webDriver.findElement(By.xpath(".//*[@id='answers_34_value']")).sendKeys("500099");
    // Locate the Checking Account(s) Balance Search box for Pual Washington
    // and enter the information as required.
    webDriver.findElement(By.xpath(".//*[@id='answers_35_value']")).sendKeys("45000000");
    // Locate the Continue button and click on it to continue.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Locate section for Other Source of Income enter all valid data as
    // required.
    // Locate the Salary search box and enter salary.
    webDriver.findElement(By.xpath(".//*[@id='answers_36_value']")).sendKeys("700000");
    // Locate the Other Income search box and enter Other Income.
    webDriver.findElement(By.xpath(".//*[@id='answers_37_value']")).sendKeys("800000.98");
    // Locate the applicant Business Type and enter amount of applicant
    // equity.
    webDriver.findElement(By.xpath(".//*[@id='answers_38_value']")).sendKeys("800000.98");
    // Locate the applicant equity in other firm and enter applicant
    // business equity.
    webDriver.findElement(By.xpath(".//*[@id='answers_39_value']")).sendKeys("5000000.87");
    // Locate the continue button and click on it to continue.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Locate and YES for question 'Do you have any notes receivable from
    // others?'.
    webDriver.findElement(By.cssSelector("label.yes")).click();
    webDriver.findElement(By.xpath("//div[@id='answers_40_details_table_wrapper']/div/a/span"))
        .click();
    logger.info(
        "User is prompted to enter Atleast one row  field or to select no if not applicable. Valid error Message is ");
    Thread.sleep(3000);
    // Locate the 'Name of Debtor' search box and enter a valid name.
    webDriver.findElement(By.id("DTE_Field_debtor_name")).sendKeys("John Mall");
    // Locate the 'Address of Debtor' search box and enter a valid address.
    webDriver.findElement(By.id("DTE_Field_debtor_address")).sendKeys("8765 Kings st MD 23445");
    // Locate the 'Original Balance' search box and enter a valid balance.
    webDriver.findElement(By.id("DTE_Field_original_balance")).sendKeys("8000000");
    // Locate the 'Current Balance' Search box and enter a valid balance.
    webDriver.findElement(By.id("DTE_Field_current_balance")).sendKeys("2000000");
    // Locate the 'Payment Amount(Calculated Annually)' and enter a valid
    // Amount.
    webDriver.findElement(By.id("DTE_Field_pay_amount")).sendKeys("6000000");
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
    // Select NO for the two question on Retirement Accounts.
    webDriver.findElement(By.xpath(".//*[@id='answers[41][value]']/label[2]")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.xpath(".//*[@id='answers[42][value]']/label[2]")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    webDriver.findElement(By.id("answers_43_value_yes")).click();
    webDriver.findElement(By.xpath("//div[@id='answers_43_details_table_wrapper']/div/a/span"))
        .click();
    webDriver.findElement(By.id("DTE_Field_company_name")).sendKeys("21 century");
    webDriver.findElement(By.id("DTE_Field_cash_surrender_value")).sendKeys("20000.98");
    webDriver.findElement(By.id("DTE_Field_face_amount")).sendKeys("5000.76");
    webDriver.findElement(By.id("DTE_Field_beneficiaries")).sendKeys("John Peter");
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(5000);
    // Locate and select yes for question, Do you have loan against a life
    // insurance.
    webDriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
    webDriver.findElement(By.xpath(".//*[@id='answers[44][value]']/label[1]")).click();
    webDriver.findElement(By.xpath(".//*[@id='answers_45_value']")).sendKeys("50000.45");
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
    // Select Yes for the Stock and Bonds Section.
    webDriver.findElement(By.xpath(".//*[@id='answers[46][value]']/label[1]")).click();
    webDriver.findElement(By.xpath("//div[@id='answers_46_details_table_wrapper']/div/a/span"))
        .click();
    webDriver.findElement(By.id("DTE_Field_securities_name")).sendKeys("Test 2000");
    webDriver.findElement(By.id("DTE_Field_total_value")).sendKeys("5000000");
    webDriver.findElement(By.id("DTE_Field_num_of_shares")).sendKeys("60");
    webDriver.findElement(By.id("DTE_Field_cost")).sendKeys("590000.87");
    webDriver.findElement(By.id("DTE_Field_market_value")).sendKeys("980000.98");
    webDriver.findElement(By.id("DTE_Field_date")).clear();
    webDriver.findElement(By.id("DTE_Field_date")).sendKeys("04/12/2016");
    webDriver.findElement(By.id("DTE_Field_interest_dividends")).sendKeys("760000.56");
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.cssSelector("input[name='commit']")).click();
    // Select Yes for Real Estate - Primary Residence Section questions.
    webDriver.findElement(By.xpath(".//*[@id='answers[47][value]']/label[1]")).click();
    Thread.sleep(2000);
    webDriver.findElement(By.id("answers_48_1_1_value"))
        .sendKeys("8597 weems rd 2000 ln manassas va 30998");
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
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    logger.info("Heyyyy! Stocks and Bones section answered.");
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
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(3000);
    // Beginning Test For Personal Property.
    assertTrue(webDriver.getPageSource().contains("Do you own any automobiles"));
    logger.info("User is being navigated to the Personal Property section, PASS");
    webDriver.findElement(By.xpath(".//*[@id='answers[51][value]']/label[1]")).click();
    // Locate the New button on the data table and click on it to add
    // information.
    webDriver.findElement(By.xpath("//div[@id='answers_51_details_table_wrapper']/div/a/span"))
        .click();
    // Locate current value search box and enter a valid value for you
    // automobile.
    webDriver.findElement(By.id("DTE_Field_current_value")).sendKeys("7000000.56");
    // Locate the Loan Balance search box and enter a valid balance.
    webDriver.findElement(By.id("DTE_Field_loan_balance")).sendKeys("2000000.76");
    // Locate the description of asset search box and enter the
    // make,model and year of your automobile.
    webDriver.findElement(By.id("DTE_Field_asset_description")).sendKeys("Chevy Camaro");
    // Locate the payment amount search box and enter the annual payment
    // amount for the automobile.
    webDriver.findElement(By.id("DTE_Field_payment_amount")).sendKeys("3000000.56");
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
    webDriver.findElement(By.id("DTE_Field_lien_amount")).sendKeys("200000.45");
    // Locate the Terms of Payment search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_pay_terms")).sendKeys("4years");
    Thread.sleep(3000);
    // Locate the Create button and click on it.
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Select yes for question; 'Are any leans delinquent?'.
    webDriver.findElement(By.xpath(".//*[@id='answers[53][value]']/label[1]")).click();
    webDriver.findElement(By.id("answers_53_comment")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
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
    webDriver.findElement(By.id("DTE_Field_current_value")).sendKeys("600000.76");
    // Locate the Loan Balance search box and enter a valid balance.
    webDriver.findElement(By.id("DTE_Field_loan_balance")).sendKeys("100000.45");
    // Locate the description of asset search box and enter the
    // make,model and year of your automobile.
    webDriver.findElement(By.id("DTE_Field_asset_description")).sendKeys("Toyota Lover");
    // Locate the payment amount search box and enter the annual payment
    // amount for the automobile.
    webDriver.findElement(By.id("DTE_Field_payment_amount")).sendKeys("2000000.98");
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
    webDriver.findElement(By.id("DTE_Field_lien_amount")).sendKeys("2000000.98");
    // Locate the Terms of Payment search box and enter a valid data.
    webDriver.findElement(By.id("DTE_Field_pay_terms")).sendKeys("2years");
    Thread.sleep(3000);
    // Locate the Create button and click on it.
    webDriver.findElement(By.cssSelector("button.btn")).click();
    Thread.sleep(3000);
    // Select yes for question; 'Are any leans delinquent?'.
    webDriver.findElement(By.xpath(".//*[@id='answers[56][value]']/label[1]")).click();
    webDriver.findElement(By.id("answers_56_comment")).sendKeys(
        "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
    Thread.sleep(3000);
    // Locate the continue button and click on it.
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
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
        webDriver.findElement(By.xpath(".//*[@id='answers[60][value]']/label[1]")).click();
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
    webDriver.findElement(By.xpath("//div[@id='answers_60_details_table_wrapper']/div/a/span"))
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
    webDriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
    webDriver.findElement(By.xpath(".//*[@id='answers[61][value]']/label[1]")).click();
    assertTrue(webDriver.getPageSource()
        .contains("Complete this section only if liabilities were not listed elsewhere"));
    logger.info(
        "Assessed taxes data tabl is present for question 'Do you have any other liabilities?', Pass");
    webDriver.findElement(By.xpath("//div[2]/fieldset/div[2]/div[3]/div/a/span")).click();
    // Locate the Liability search box and enter a valid data.
    webDriver.findElement(By.cssSelector("#DTE_Field_liability")).sendKeys("2000000.97");
    // Locate the Amount Owned search box and enter a valid amount.
    webDriver.findElement(By.cssSelector("#DTE_Field_amount_owed")).sendKeys("3000000.54");
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
    webDriver.findElement(By.id("answers_62_value")).sendKeys("3000000");
    webDriver.findElement(By.id("answers_63_value")).sendKeys("10000900");
    webDriver.findElement(By.id("answers_64_value")).sendKeys("2000000");
    // Locate and click on the continue button.
    Thread.sleep(2000);
    webDriver.findElement(By.xpath("//form[@id='edwosb']/input[4]")).click();
    Thread.sleep(2000);
    // Verify that user is being navigated to the Summary Page.
    String actual_Text6 = webDriver.findElement(By.cssSelector("h2")).getText();
    String expected_Text6 = "Will Smith";
    assertEquals(actual_Text6, expected_Text6);
    // Verify updated int type.
    String actual_Text11 = webDriver
        .findElement(By.xpath("//form[@id='edwosb']/div[2]/div/table/tbody/tr/td[2]")).getText();
    String expected_Text11 = "$4,000.00";
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

}

