//TS Created By _deepa patri
package gov.sba.utils.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestUs1674EDWOSBAnalystReview extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1674_EDWOSB = LogManager
            .getLogger(TestUs1674EDWOSBAnalystReview.class.getName());
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        CommonApplicationMethods.focus_window();
        String[] details = DatabaseUtils.findUnusedDunsNumber();
        email = details[0];
        password = details[1];
        duns_Number = details[2];

    }

    @Test
    public void testMainTest() throws Exception {

        LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
        login_Data.Login_With_Details();
        String app_Type_Passed = "EDWOSB";
        // For WOSB and EDWOSB Active status - Create new app if not existing
        CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
        CommonApplicationMethods.createApplication(webDriver, app_Type_Passed);

        String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

        logger_US1674_EDWOSB.info(file_path_abs);
        fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
        fillApplCreatePages.finalSignatureSubmit(webDriver);

        CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

        LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
        login_Data_01.Login_With_Reference();
        // Thread.sleep(3000);
        try {

            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            CommonApplicationMethods.casesPageSearch(webDriver, duns_Number);
            logger_US1674_EDWOSB.info("Cases link is on Main Navigator is Clicked");

            List<WebElement> current_Row_EDWOSB = webDriver.findElements(
                    By.xpath("//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'"
                            + duns_Number + "')]  ]/td[1]/a"));

            if (current_Row_EDWOSB.size() >= 1) {
                current_Row_EDWOSB.get(0).click();
                WebElement current_Page_Title = webDriver
                        .findElement(By.xpath("//article[@id='main-content']/div/div[2]/h1"));
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
                webDriver.findElement(By.xpath("//input[@id='save_notes']")).click();

                webDriver.navigate().back();
                webDriver.navigate().back();
                CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

            }

            // Come back Later
            //
            //
            webDriver.findElement(
                    By.xpath("//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains( text(), '"
                            + duns_Number + "' )]  ]/td[1]/a"))
                    .click();

            webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();

            // Question Review Page
            webDriver
                    .findElement(By
                            .xpath("//ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]"))
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
                            .xpath("//ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Financial review')]"))
                    .click();

            // Come back Later add a new test case for Questionaire flow and
            // check Enabled //Use below later

            // List<WebElement> table_Left_Rows =
            // webDriver.findElements(By.xpath(
            // "//article[@id='main-content']/form/div[contains(@class,'review_outer')]/div[contains(@class,'review_main')]/div/div[contains(@class,'tab-content')]/div/div[not(contains(@id,'table1-pad'))]/table/tbody/tr"));
            // List<WebElement> table_Right_Rows =
            // webDriver.findElements(By.xpath(
            // "//article[@id='main-content']/form/div[contains(@class,'review_outer')]/div[contains(@class,'review_main')]/div/div[contains(@class,'tab-content')]/div/div[contains(@id,'table1-pad')]/table/tbody/tr"));
            //
            // String[] table1_Data = { "Cash on Hand $100.00", "Savings
            // Account(s) Balances $100.00",
            // "Checking Account(s) Balances $100.00", "Accounts & Notes
            // Receivable $100.00",
            // "IRA, 401K or Other Retirement Account $100.00", "Roth IRA
            // $100.00",
            // "Cash Surrender Value of Whole Life Insurance $100.00",
            // "Stocks and Bonds or Mutual Funds? $100.00", "Real Estate
            // (Primary Residence) $100.00",
            // "Other Real Estate $0.00", "Automobiles $100.00", "Other Personal
            // Property/Assets $100.00",
            // "Applicant's Business Equity $100.00", "Applicant's Equity in
            // Other Firms $100.00",
            // "Total Assets $1,300.00", "Salary $100.00", "Investment Income
            // $100.00",
            // "Real Estate Income $100.00", "Other Income $100.00", "Most
            // Recent Tax Year $100.00",
            // "Year 2 $100.00", "Year 3 $100.00", "Total (Avg) $100.00" };
            // String[] table2_Data = { "Accounts Payable $100.00", "Notes
            // Payable to Banks & Others $100.00",
            // "Installment Account (Auto) $100.00", "Installment Account
            // (Other) $100.00",
            // "Loan(s) Against Life Insurance $100.00", "Mortgage (Primary
            // Residence)* $200.00",
            // "Mortgages on other Real Estate $0.00", "Unpaid Taxes $100.00",
            // "Other Liabilities $100.00",
            // "Total Liabilities $900.00", "Net Worth\nTotal Assets - Total
            // Liabilities $400.00" };
            // for (int i = 0; i < table_Left_Rows.size(); i++) {
            // logger_US1674_EDWOSB.info(table_Left_Rows.get(i).getText());
            // assertEquals(table_Left_Rows.get(i).getText(), table1_Data[i]);
            // }
            //
            // for (int j = 0; j < table_Right_Rows.size(); j++) {
            // logger_US1674_EDWOSB.info(table_Right_Rows.get(j).getText());
            // assertEquals(table_Right_Rows.get(j).getText(), table2_Data[j]);
            // }
            //
            // logger_US1674_EDWOSB.info(table_Left_Rows.size());
            // logger_US1674_EDWOSB.info(table_Left_Rows.get(0).getText());
            // logger_US1674_EDWOSB.info(table_Right_Rows.size());
            // logger_US1674_EDWOSB.info(table_Right_Rows.get(0).getText());
            //
            // WebElement ret_Accounts = webDriver.findElement(By.xpath(
            // "//article[@id='main-content']/form/div[contains(@class,'review_outer')]/div[contains(@class,'review_main')]/div/div[contains(@class,'tab-content')]/form/div/div/div/h3[contains(text(),'Retirement
            // Accounts')]"));
            // logger_US1674_EDWOSB.info(ret_Accounts.getText());
            // WebElement ret_Account_Title =
            // ret_Accounts.findElement(By.xpath("..")).findElement(
            // By.xpath("div/div[contains(@class,'table-top')]/strong[text()='Vendor
            // attachments:']"));
            // logger_US1674_EDWOSB.info(ret_Account_Title.getText());
            // WebElement ret_Account_Attach_Link =
            // ret_Account_Title.findElement(By.xpath(".."))
            // .findElement(By.xpath("table/tbody/tr/td/a[contains(text(),'.pdf')]"));
            // logger_US1674_EDWOSB.info(ret_Account_Attach_Link.getText());
            // @SuppressWarnings("unused")
            // WebElement ret_Account_Attach_Text =
            // ret_Account_Title.findElement(By.xpath("..")).findElement(
            // By.xpath("table/tbody/tr/td[contains(text(),'Retirement Account
            // Terms and Conditions')]"));
            // logger_US1674_EDWOSB.info(ret_Account_Attach_Link.getText());
            //
            // Thread.sleep(20000);
            // //
            // webDriver.findElement(By.xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/p/a[contains(text(),'Vendor
            // // Overview')]")).click();
            // webDriver.navigate().back();

            // Signature Review Page
            webDriver
                    .findElement(By
                            .xpath("//ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Signature review')]"))
                    .click();
            dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']"))).getOptions();
            logger_US1674_EDWOSB.info(dropdown.get(0).getText());
            assertEquals("Confirmed", dropdown.get(0).getText());
            assertEquals("Not reviewed", dropdown.get(1).getText());
            assertEquals("Information missing", dropdown.get(2).getText());
            assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
            assertEquals("Needs further review", dropdown.get(4).getText());
            webDriver.findElement(By.id("note_link")).click();
            webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']"))
                    .sendKeys("Adding notes QA Signature Page");

            webDriver.findElement(By.xpath("//input[@name='commit']")).click();

        } catch (Exception e) {
            logger_US1674_EDWOSB.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"TestUs1674EDWOSBAnalystReview", "Exception"});
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
         webDriver.quit();
    }
}