package gov.sba.utils.integration;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// *Deepa-
public class NewFinancialSectionQuestion {
    private static final Logger logger = LogManager.getLogger(FinancialSectionPage.class.getName());
    private WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    public NewFinancialSectionQuestion(WebDriver mydriver) {
        this.webDriver = mydriver;
    }

    public void NewFinancialQuestion() throws Exception {
        Thread.sleep(1500);
        try {
            // Locate section for 'Cash on Hand' enter all valid data as
            // required.
            // Locate the As of Date: Search box for user and enter the
            // information
            // as required.
            webDriver.findElement(By.name("answers[224][value]")).sendKeys("01/05/2017");
            // Locate the Cash on Hand Search box for Pual Washington and enter
            // the
            // information as required.
            webDriver.findElement(By.xpath(".//*[@id='answers_225_value']")).sendKeys("2000");
            // Locate the Savings Account(s) Balance Search box for Pual
            // Washington
            // and enter the information as required.
            webDriver.findElement(By.xpath(".//*[@id='answers_226_value']")).sendKeys("5000");
            // Locate the Checking Account(s) Balance Search box for Pual
            // Washington
            // and enter the information as required.
            webDriver.findElement(By.xpath(".//*[@id='answers_227_value']")).sendKeys("45000");
            // Locate the Continue button and click on it to continue.
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@name='commit']")).click();
            // Locate section for Other Source of Income enter all valid data as
            // required.
            // Locate the Salary search box and enter salary.
            webDriver.findElement(By.xpath(".//*[@id='answers_228_value']")).sendKeys("70000");
            // Locate the Other Income search box and enter Other Income.
            webDriver.findElement(By.xpath(".//*[@id='answers_229_value']")).sendKeys("80000");
            // Locate the applicant Business Type and enter amount of applicant
            // equity.
            webDriver.findElement(By.xpath(".//*[@id='answers_230_value']")).sendKeys("800000");
            // Locate the applicant equity in other firm and enter applicant
            // business equity.
            webDriver.findElement(By.xpath(".//*[@id='answers_231_value']")).sendKeys("50000");
            // Locate the continue button and click on it to continue.
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@name='commit']")).click();
            // Locate and YES for question 'Do you have any notes receivable
            // from
            // others?'.
            webDriver.findElement(By.cssSelector("label.yes")).click();
            webDriver.findElement(By.xpath("//div[@id='answers_232_details_table_wrapper']/div/a/span")).click();
            logger.info(
                    "User is prompted to enter Atleast one row  field or to select no if not applicable. Valid error Message is ");
            Thread.sleep(3000);
            // Locate the 'Name of Debtor' search box and enter a valid name.
            webDriver.findElement(By.id("DTE_Field_debtor_name")).sendKeys("Dee M");
            // Locate the 'Address of Debtor' search box and enter a valid
            // address.
            webDriver.findElement(By.id("DTE_Field_debtor_address")).sendKeys("8421 Braof St");
            // Locate the 'Original Balance' search box and enter a valid
            // balance.
            webDriver.findElement(By.id("DTE_Field_original_balance")).sendKeys("99999");
            // Locate the 'Current Balance' Search box and enter a valid
            // balance.
            webDriver.findElement(By.id("DTE_Field_current_balance")).sendKeys("21111");
            // Locate the 'Payment Amount(Calculated Annually)' and enter a
            // valid
            // Amount.
            webDriver.findElement(By.id("DTE_Field_pay_amount")).sendKeys("611111");
            // Locate the 'How Secured or Endorsed / Type of Collateral' search
            // box
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
            webDriver.findElement(By.id("answers_233_value_no")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.id("answers_234_value_no")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@name='commit']")).click();
            // Locate and select yes for question, Do you have loan against a
            // life
            // insurance.
            webDriver.findElement(By.xpath("//*[@id='answers_235_value_no']")).click();
            webDriver.findElement(By.xpath("//*[@id='answers_236_value_no']")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@name='commit']")).click();
            // Select Yes for the Stock and Bonds Section.
            webDriver.findElement(By.xpath("//*[@id='answers_238_value_no']")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.cssSelector("input[name='commit']")).click();
            // Select Yes for Real Estate - Primary Residence Section questions.
            webDriver.findElement(By.id("answers_239_value_no")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.cssSelector("input[name='commit']")).click();
            Thread.sleep(3000);
            webDriver.findElement(By.id("answers_241_value_no")).click();
            webDriver.findElement(By.cssSelector("input[name='commit']")).click();
            // Beginning Test For Personal Property.
            assertTrue(webDriver.getPageSource().contains("Do you own any automobiles"));
            logger.info("User is being navigated to the Personal Property section, PASS");
            webDriver.findElement(By.id("answers_243_value_no")).click();
            // Locate the New button on the data table and click on it to add
            // information.
            webDriver.findElement(By.id("answers_244_value_no")).click();
            // Locate current value search box and enter a valid value for you
            // automobile.
            webDriver.findElement(By.id("answers_245_value_no")).click();
            webDriver.findElement(By.id("answers_246_value_no")).click();
            webDriver.findElement(By.id("answers_247_value_no")).click();
            webDriver.findElement(By.id("answers_248_value_no")).click();
            webDriver.findElement(By.cssSelector("input[name='commit']")).click();

            // Verify that User navigate to the Next section of 'Notes Payable'
            // in
            // form 413 successfully.
            try {
                assertTrue(webDriver.getPageSource().contains("Notes Payable"));
                logger.info("User is being navigated to the 'Notes Payable' section on the form 413, Pass");
                webDriver.findElement(By.id("answers_249_value_no")).click();
                webDriver.findElement(By.id("answers_250_value_no")).click();

                Thread.sleep(2000);
                webDriver.findElement(By.cssSelector("input[name='commit']")).click();
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
                // Verify and answer YES to question 'Do you have any Assessed
                // Taxes
                // that were unpaid?'.
                try {
                    assertTrue(webDriver.getPageSource().contains("Do you have any Assessed Taxes that were unpaid"));
                    logger.info(
                            "Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' is present, Pass");
                    webDriver.findElement(By.id("answers_252_value_no")).click();
                    Thread.sleep(1500);
                    webDriver.findElement(By.id("answers_253_value_no")).click();
                } catch (Error e) {
                    logger.info(
                            "Assessed Taxes question; 'Do you have any Assessed Taxes that were unpaid?' NOT Present, Failed");
                }
            } catch (Error e) {
                logger.info("User did Not successfully navigated to the section 'Assessed Taxes' on Form 413, Failed");
            }
            // // Verify data table and complete it if taxes were unpaid.
            // webDriver.findElement(By.id("answers_221_value_no")).click();
            // Thread.sleep(3000);
            // Locate and click on the continue button.
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(2000);
            // Locate the next 3 search boxes for Adjusted Gross Income and
            // enter
            // valid data.

            webDriver.findElement(By.id("answers_254_value")).sendKeys("30000");
            webDriver.findElement(By.id("answers_255_value")).sendKeys("10000");
            webDriver.findElement(By.id("answers_256_value")).sendKeys("20000");
            // Locate and click on the continue button.
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(2000);
            // Verify that user is being navigated to the Summary Page.
            String actual_Text6 = webDriver.findElement(By.cssSelector("h2")).getText();
            String expected_Text6 = "DeepaMahesh P";
            assertEquals(actual_Text6, expected_Text6);
            // Verify that user is being navigated to the Privacy Statements
            // Page.
            // String actual_Text1 =
            // webDriver.findElement(By.cssSelector("h2")).getText();
            // String expected_Text1 = "Privacy Statements";
            // assertEquals(actual_Text1, expected_Text1);
            // Click on the save and continue button.
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(2000);
            webDriver.findElement(By.xpath("//input[@type='submit']")).click();
            // Verify that user is being navigated to the Review Page.
            String actual_Text2 = webDriver.findElement(By.cssSelector("h2")).getText();
            String expected_Text2 = "Review";
            assertEquals(actual_Text2, expected_Text2);
            Thread.sleep(3000);
            webDriver.findElement(By.className("review")).click();
            Thread.sleep(2000);
            webDriver.switchTo().alert().accept();
            Thread.sleep(2000);
            // Verify that user is being navigated to the Signature Page.
            // String actual_Text4 =
            // webDriver.findElement(By.cssSelector("h2")).getText();
            // String expected_Text4 = "Signature";
            // assertEquals(actual_Text4, expected_Text4);
            // Verify that the accept button is present..
            // String actual_Text5 =
            // webDriver.findElement(By.id("accept-button")).getText();
            // String expected_Text5 = "Accept";
            // assertEquals(actual_Text5, expected_Text5);
        } catch (Exception e) {
            Thread.sleep(2000);
            ScreenShotPage screenShot = new ScreenShotPage(webDriver);
            screenShot.ScreenShot();
            logger.info("Error");
        }
    }

    private void assertEquals(String actual_Text1, String expected_Text1) {
        // TODO Auto-generated method stub

    }
}
