package gov.sba.utils;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import junit.framework.TestCase;

public class TestUs1674EDWOSBAnalystReview extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1674_EDWOSB = LogManager
            .getLogger(TestUs1674EDWOSBAnalystReview.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 21;
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        // Thread.sleep(3000);
        try {

            WebElement Cases_Link = webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
            Cases_Link.click();
            logger_US1674_EDWOSB.info("Cases link is on Main Navigator is Clicked");
            // List<WebElement> current_Row_WOSB =
            // webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[td[1][contains(text(),'Legal
            // Business Name')] and td[3][contains(text(),'WOSB')] and
            // td[8][contains(text(),'Submitted')]]"));
            List<WebElement> current_Row_EDWOSB = webDriver.findElements(By.xpath(
                    "//div[@id='table-search']/table/tbody/tr[  td[3][contains(text(),'EDWOSB')]  and td[8][contains(text(),'Submitted')]   ]"));

            if (current_Row_EDWOSB.size() > 1) {
                logger_US1674_EDWOSB.info(current_Row_EDWOSB.get(0).getAttribute("innerHTML"));
                WebElement a1 = current_Row_EDWOSB.get(1)
                        .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                logger_US1674_EDWOSB.info(a1.getText());
                a1.click();

                // WebElement current_Row_WOSB =
                // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
                WebElement current_Page_Title = webDriver
                        .findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/h1"));
                logger_US1674_EDWOSB.info(current_Page_Title.getText());

                String Expected_Text = "Case Overview";
                assertEquals(Expected_Text, current_Page_Title.getText());

                WebElement current_Review_Text = webDriver.findElement(By.xpath("//h2[@class='usa-width-one-third']"));
                assertEquals("Start a review", current_Review_Text.getText());

                Select dropdown = new Select(webDriver.findElement(By.id("review_type")));
                assertEquals(1, dropdown.getOptions().size());
                assertEquals("Initial Review", dropdown.getFirstSelectedOption().getText());

                Select dropdown1 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_reviewer_id']")));
                dropdown1.selectByIndex(0);

                @SuppressWarnings("unused")
                Select dropdown2 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
                dropdown1.selectByVisibleText("Analyst3 X");

                @SuppressWarnings("unused")
                Select dropdown3 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
                dropdown1.selectByVisibleText("Analyst4 X");
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();

                webDriver.navigate().back();
                webDriver.navigate().back();

            }

            current_Row_EDWOSB = webDriver.findElements(By.xpath(
                    "//div[@id='table-search']/table/tbody/tr[  td[3][contains(text(),'EDWOSB')]  and td[8][contains(text(),'Under Review')]  ]"));

            if (current_Row_EDWOSB.size() > 0) {
                logger_US1674_EDWOSB.info(current_Row_EDWOSB.get(0).getAttribute("innerHTML"));

                /*
                 * assertTrue(current_Row_EDWOSB.get(0).findElement(By.xpath(
                 * "td[6]")).getText().length() > 0);
                 * assertTrue(current_Row_EDWOSB.get(0).findElement(By.xpath(
                 * "td[7]")).getText().length() > 0);
                 */
                WebElement a1 = current_Row_EDWOSB.get(1)
                        .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                logger_US1674_EDWOSB.info(a1.getText());
                a1.click();
                logger_US1674_EDWOSB.info("alkanaaaaaa");

                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                logger_US1674_EDWOSB.info("aaaalkj");

                // Question Review Page
                webDriver
                        .findElement(By
                                .xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]"))
                        .click();
                List<WebElement> dropdown = new Select(
                        webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
                logger_US1674_EDWOSB.info(dropdown.get(0).getText());
                assertEquals("Confirmed", dropdown.get(0).getText());
                assertEquals("Not reviewed", dropdown.get(1).getText());
                assertEquals("Information missing", dropdown.get(2).getText());
                assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
                assertEquals("Needs further review", dropdown.get(4).getText());
                webDriver.findElement(By.id("note_link")).click();
                webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding notes QA");
                webDriver.findElement(By.id("save_notes")).click();

                // webDriver.findElement(By.xpath("//a[@class='expand_notes']")).click();
                // Financia Data Review Page
                webDriver
                        .findElement(By
                                .xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Financial review')]"))
                        .click();

                List<WebElement> table_Left_Rows = webDriver.findElements(By.xpath(
                        "//article[@id='main-content']/form/div[contains(@class,'review_outer')]/div[contains(@class,'review_main')]/div/div[contains(@class,'tab-content')]/div/div[not(contains(@id,'table1-pad'))]/table/tbody/tr"));
                List<WebElement> table_Right_Rows = webDriver.findElements(By.xpath(
                        "//article[@id='main-content']/form/div[contains(@class,'review_outer')]/div[contains(@class,'review_main')]/div/div[contains(@class,'tab-content')]/div/div[contains(@id,'table1-pad')]/table/tbody/tr"));

                String[] table1_Data = { "Cash on Hand $100.00", "Savings Account(s) Balances $100.00",
                        "Checking Account(s) Balances $100.00", "Accounts & Notes Receivable $100.00",
                        "IRA, 401K or Other Retirement Account $100.00", "Roth IRA $100.00",
                        "Cash Surrender Value of Whole Life Insurance $100.00",
                        "Stocks and Bonds or Mutual Funds? $100.00", "Real Estate (Primary Residence) $100.00",
                        "Other Real Estate $0.00", "Automobiles $100.00", "Other Personal Property/Assets $100.00",
                        "Applicant's Business Equity $100.00", "Applicant's Equity in Other Firms $100.00",
                        "Total Assets $1,300.00", "Salary $100.00", "Investment Income $100.00",
                        "Real Estate Income $100.00", "Other Income $100.00", "Most Recent Tax Year $100.00",
                        "Year 2 $100.00", "Year 3 $100.00", "Total (Avg) $100.00" };
                String[] table2_Data = { "Accounts Payable $100.00", "Notes Payable to Banks & Others $100.00",
                        "Installment Account (Auto) $100.00", "Installment Account (Other) $100.00",
                        "Loan(s) Against Life Insurance $100.00", "Mortgage (Primary Residence)* $200.00",
                        "Mortgages on other Real Estate $0.00", "Unpaid Taxes $100.00", "Other Liabilities $100.00",
                        "Total Liabilities $900.00", "Net Worth\nTotal Assets - Total Liabilities $400.00" };
                for (int i = 0; i < table_Left_Rows.size(); i++) {
                    logger_US1674_EDWOSB.info(table_Left_Rows.get(i).getText());
                    assertEquals(table_Left_Rows.get(i).getText(), table1_Data[i]);
                }

                for (int j = 0; j < table_Right_Rows.size(); j++) {
                    logger_US1674_EDWOSB.info(table_Right_Rows.get(j).getText());
                    assertEquals(table_Right_Rows.get(j).getText(), table2_Data[j]);
                }

                logger_US1674_EDWOSB.info(table_Left_Rows.size());
                logger_US1674_EDWOSB.info(table_Left_Rows.get(0).getText());
                logger_US1674_EDWOSB.info(table_Right_Rows.size());
                logger_US1674_EDWOSB.info(table_Right_Rows.get(0).getText());

                WebElement ret_Accounts = webDriver.findElement(By.xpath(
                        "//article[@id='main-content']/form/div[contains(@class,'review_outer')]/div[contains(@class,'review_main')]/div/div[contains(@class,'tab-content')]/form/div/div/div/h3[contains(text(),'Retirement Accounts')]"));
                logger_US1674_EDWOSB.info(ret_Accounts.getText());
                WebElement ret_Account_Title = ret_Accounts.findElement(By.xpath("..")).findElement(
                        By.xpath("div/div[contains(@class,'table-top')]/strong[text()='Vendor attachments:']"));
                logger_US1674_EDWOSB.info(ret_Account_Title.getText());
                WebElement ret_Account_Attach_Link = ret_Account_Title.findElement(By.xpath(".."))
                        .findElement(By.xpath("table/tbody/tr/td/a[contains(text(),'.pdf')]"));
                logger_US1674_EDWOSB.info(ret_Account_Attach_Link.getText());
                @SuppressWarnings("unused")
                WebElement ret_Account_Attach_Text = ret_Account_Title.findElement(By.xpath("..")).findElement(
                        By.xpath("table/tbody/tr/td[contains(text(),'Retirement Account Terms and Conditions')]"));
                logger_US1674_EDWOSB.info(ret_Account_Attach_Link.getText());

                Thread.sleep(20000);
                // webDriver.findElement(By.xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/p/a[contains(text(),'Vendor
                // Overview')]")).click();
                webDriver.navigate().back();

                // Signature Review Page
                webDriver
                        .findElement(By
                                .xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Signature review')]"))
                        .click();
                dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']")))
                        .getOptions();
                logger_US1674_EDWOSB.info(dropdown.get(0).getText());
                assertEquals("Confirmed", dropdown.get(0).getText());
                assertEquals("Not reviewed", dropdown.get(1).getText());
                assertEquals("Information missing", dropdown.get(2).getText());
                assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
                assertEquals("Needs further review", dropdown.get(4).getText());
                webDriver.findElement(By.id("note_link")).click();
                webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']"))
                        .sendKeys("Adding notes QA Signature Page");

                webDriver.findElement(By.xpath("//*[@id='new_assessment']/div/input[@value='Save and commit']"))
                        .click();

            }
            webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Case overview')]"))
                    .click();

        } catch (Exception e) {
            logger_US1674_EDWOSB.info(e.toString());
        }
    }
}