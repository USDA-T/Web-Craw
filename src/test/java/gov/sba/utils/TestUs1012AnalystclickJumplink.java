package gov.sba.utils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.TestCase;

public class TestUs1012AnalystclickJumplink extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestUs1012AnalystclickJumplink.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;
    String dunsNumber;
    String invalidDUNS;
    String addNote;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 0;
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        dunsNumber = "135453634";
        invalidDUNS = "12344556";
        addNote = "public static final String max_comment The U.S. Small Business Administration (SBA) has modernized the Women-Owned Small Business (WOSB) Program certification process. Get started online today and manage your eligibility documentation or upload Third-Party Certifications directly from our easy to use dashboard. Get started today! The U.S. Small Business Administration (SBA) has modernized the Women-Owned Small Business (WOSB) Program certification process.  Get started online today and       5001";
    }

    @Test
    public void testMainTest() throws Exception {
        // Step 1 - Open QA site
        Thread.sleep(3000);
        // Step 2 - Log in as an Analyst
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Step 3 - Verify that the certify.SBA.gov Logo is displayed Check the
        // title of the page
        logger.info("Page title is: " + webDriver.getTitle());
        logger.info("Step 3 - Verify that the certify.SBA.gov Logo is displayed");
        assertTrue(webDriver.findElements(By.xpath("html/body/header/div[1]/h2/img")).size() != 0);
        logger.info("certify.SBA.gov Logo is Present");
        // Step 4 - Tried Without DUNS number find the business click Search
        // button.
        logger.info("Step 4 - Tried Without  DUNS number find the business click Search button");
        webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).click();
        logger.info("Verify that the  Search Results 'No Match Found' ");
        try {
            assertEquals("No Match Found",
                    webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
            logger.info("Message is displayed 'No Match Found' - Pass");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.info("Message is displayed 'No Match Found' - FAILED");
        }
        Thread.sleep(3000);
        // Step 5 - Tried to find the businesses matched By Invalid DUNS number
        logger.info("Step 5 - Tried to find the businesses matched By Invalid DUNS number");
        webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
        webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query"))
                .sendKeys(invalidDUNS);
        webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
        Thread.sleep(3000);
        logger.info("Verify that the  Search Results 'No Match Found' ");
        try {
            assertEquals("No Match Found",
                    webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
            logger.info("Message is displayed No Match Found - Pass");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.info("Message is displayed No Match Found - - FAILED");
        }
        // Step 6 - Enter the Valid DUNS number to search on
        logger.info("Step 6 - Enter the Valid DUNS number to search on");
        webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
        webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query"))
                .sendKeys(dunsNumber);
        webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
        Thread.sleep(3000);
        // Step 7 - Verify that the Search Results 'Entity 211 Legal Business
        // Name' is Display
        logger.info("Step 7 - Verify that the  Search Results 'Entity 336 Legal Business Name' is Display");
        assertTrue(webDriver.getPageSource().contains("Entity 172 Legal Business Name"));
        // Step 8 - Click on Entity 336 Legal Business Name link
        logger.info("Step  8 - Click Entity 172 Legal Business Name Link");
        webDriver.findElement(By.cssSelector("#view_business_profile")).click();
        logger.info("Analyst can move to the next section Entity 336 Legal Business Name page");
        // Step 9 - Verify that the Entity 336 Legal Business Name page is
        // Display
        logger.info("Step 9 - Verify that the Entity 336 Legal Business Name page is Display");
        try {
            assertEquals("Entity 172 Legal Business Name",
                    webDriver.findElement(By.cssSelector(".usa-heading")).getText());
            logger.info("Entity 336 Legal Business Name is displayed - Pass");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.info("Entity 336 Legal Business Name is displayed - FAILED");
        }
        // Step 10 - Click the EDWOSB Self-Certification Link
        logger.info("Step 10 - Click the EDWOSB Self-Certification  Link");
        webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
        // Step 11- Verify that the Case Overview page Display
        logger.info("Step 11 - Verify that the  Case Overview page Display ");
        try {
            assertEquals("Case Overview",
                    webDriver.findElement(By.xpath(".//*[@id='main-content']/div/div[2]/h1")).getText());
            logger.info("Case Overview page Display - Pass");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.info("Case Overview page Display - FAILED");
        }
        // Step 12 - SBA Analyst Assign 1st Reviewer to a member
        logger.info("Step 12 - SBA Analyst Assign 1st Reviewer   to a member");
        logger.info("Current reviewer Select the Analyst2 X");
        Select reviewer1Select = new Select(
                webDriver.findElement(By.id("review_current_assignment_attributes_reviewer_id")));
        reviewer1Select.selectByValue("7");
        logger.info("Owner Select the Analyst3 X");
        Select Ownerselect = new Select(webDriver.findElement(By.id("review_current_assignment_attributes_owner_id")));
        Ownerselect.selectByValue("8");
        Thread.sleep(3000);
        logger.info("Supervisor Select the Analyst3 X");
        Select Supervisor1select = new Select(
                webDriver.findElement(By.id("review_current_assignment_attributes_supervisor_id")));
        Supervisor1select.selectByValue("10");
        Thread.sleep(3000);
        // Click the Save and commit Button
        logger.info("Click the Save and commit Button");
        webDriver.findElement(By.id("submit_button")).click();
        logger.info("Returned to the Case Overviewge page");
        Thread.sleep(3000);
        // Step 13 - Click the Financial review Link
        logger.info("Step 13 - Click the Financial review Link");
        webDriver.findElement(By.linkText("Financial review")).click();
        Thread.sleep(3000);
        // Step 14 - Verify that the Financial review page Display
        logger.info("Step 14 - Verify that the Financial review page Display ");
        try {
            assertEquals("Financial review", webDriver
                    .findElement(By.xpath("//article[@id='main-content']/form/div/div[2]/div/div/h2[2]")).getText());
            logger.info("Financial review page Display - Pass");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            logger.info("Financial reviewy page Display - FAILED");
        }
        // Step 15 - verify the Left navigation populated with individuals names
        logger.info("Step 15 - verify the Left navigation populated with individuals names");
        assertTrue(webDriver.getPageSource().contains("James Thompson"));
        logger.info(webDriver.findElement(By.cssSelector(".selected.last>a")).getText());
        Thread.sleep(3000);
        // Step 16 - Verify the Financial Summary Notes text is present
        logger.info("Step 16 - Verify the Financial Summary Notes text is present");
        String actual_Text = webDriver
                .findElement(By.xpath("//article[@id='main-content']/form/div/div[2]/div/div/h3[2]")).getText();
        String expected_Text = "Financial Summary Notes";
        assertEquals(actual_Text, expected_Text);
        Select analystAssessment30 = new Select(webDriver.findElement(By.xpath(".//*[@id='assessments__status']")));
        analystAssessment30.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info(
                "Click Add a Note, viewMore,Less and Save and continue, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.id("note_link")).click();
        webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys(addNote);
        webDriver.findElement(By.id("note_link")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//article[@id='main-content']/form/div/div[2]/div/div/div[5]/div[4]/input"))
                .click();
        // Step 17 - Click the Cash On Hand Link
        logger.info("Step 17 - Click the Cash On Hand Link");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[1]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Cash On Hand");
        assertTrue(webDriver.getPageSource().contains("Cash On Hand"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[2]"))
                .getText());
        Thread.sleep(3000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[2]")));
        analystAssessment.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[2]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[2]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[2]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 18 - Click the Savings Account(s) Balances Link
        logger.info("Step 18 - Click the Savings Account(s) Balances Link");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[2]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to CSavings Account(s) Balances");
        assertTrue(webDriver.getPageSource().contains("Cash On Hand"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[2]"))
                .getText());
        Thread.sleep(3000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment1 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[2]")));
        analystAssessment1.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[2]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[2]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[2]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 19 - Click the Checking Account(s) Balances Link
        logger.info("Step 19 - Click the Checking Account(s) Balances Link");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[3]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Checking Account(s) Balances");
        assertTrue(webDriver.getPageSource().contains("Cash On Hand"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[2]"))
                .getText());
        Thread.sleep(3000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment2 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[2]")));
        analystAssessment2.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[2]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[2]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[2]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 20 - Click the Checking Account(s) Balances Link
        logger.info("Step 19 - Click the Accounts & Notes Receivable Link");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[4]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Accounts & Notes Receivable");
        assertTrue(webDriver.getPageSource().contains("Notes Receivable"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[4]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment3 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[2]")));
        analystAssessment3.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[4]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[4]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[4]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 21 - Click the IRA, 401K or Other Retirement Account Link Jump
        // Links from summary table to Retirement Accounts section
        logger.info(
                "Step 21 - Click the IRA, 401K or Other Retirement Account Link Jump Links from summary table to Retirement Accounts section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[5]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Retirement Accounts");
        assertTrue(webDriver.getPageSource().contains("Retirement Accounts"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[5]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment4 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[5]")));
        analystAssessment4.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[5]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[5]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[5]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 22- Click the Roth IRA Link Jump Links from summary table to
        // Retirement Accounts section
        logger.info("Step 22 - Click the Roth IRA Link Jump Links from summary table to Retirement Accounts section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[6]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Retirement Accounts");
        assertTrue(webDriver.getPageSource().contains("Retirement Accounts"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[5]"))
                .getText());
        Thread.sleep(3000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment5 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[5]")));
        analystAssessment5.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[5]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[5]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[5]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 23- Click the Cash Surrender Value of Whole Life Insurance Jump
        // Links from summary table after move to Life Insurance section
        logger.info(
                "Step 23 - Click the Cash Surrender Value of Whole Life Insurance Link Jump Links from summary table after move to Life Insurance section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[7]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Life Insurance");
        assertTrue(webDriver.getPageSource().contains("Life Insurance"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[6]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment6 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[6]")));
        analystAssessment6.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[6]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[6]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[6]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 24- Click the Stocks and Bonds or Mutual Funds? Jump Links from
        // summary table to Stocks & Bonds section
        logger.info(
                "Step 24 - Click the Stocks and Bonds or Mutual Funds? Link Jump Links from summary table to Stocks & Bonds section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[8]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Stocks & Bonds");
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[7]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment7 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[7]")));
        analystAssessment7.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[7]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[7]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[7]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();

        // Step 25- Click the Real Estate (Primary Residence) Link Jump Links
        // from summary table after move to Real Estate - Primary Residence
        // section
        logger.info(
                "Step 25 - Click the Real Estate (Primary Residence) Link Jump Links from summary table to Real Estate - Primary Residence section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[9]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Real Estate - Primary Residence");
        assertTrue(webDriver.getPageSource().contains("Real Estate - Primary Residence"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[8]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment8 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[8]")));
        analystAssessment8.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[8]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[8]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[8]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();

        // Step 26- Click the Other Real Estate link Jump Links from summary
        // table after move to Real Estate - Other section
        logger.info(
                "Step 26 - Click the Other Real Estate Link Jump Links from summary table after move to Real Estate - Other section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[10]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Real Estate - Other");
        assertTrue(webDriver.getPageSource().contains("Real Estate - Other"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[9]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment9 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[9]")));
        analystAssessment9.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[9]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[9]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[9]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 27- Click the Automobiles link Jump Links from summary table to
        // Personal Property section
        logger.info("Step 27 - Click the Automobiles Link Jump Links from summary table to Personal Property section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[11]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Personal Property");
        assertTrue(webDriver.getPageSource().contains("Personal Property"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[10]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment10 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[10]")));
        analystAssessment10.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[10]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 28- Click the Other Personal Property/Assets link Jump Links
        // from summary table to Personal Property section
        logger.info(
                "Step 28 - Click the Other Personal Property/Assets Link Jump Links from summary table to Personal Property section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[12]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Personal Property");
        assertTrue(webDriver.getPageSource().contains("Personal Property"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[10]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment11 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[10]")));
        analystAssessment11.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[10]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 28- Click the Applicant's Business Equity link Jump Links from
        // summary table to Other Sources Of Income section
        logger.info(
                "Step 28 - Click the Applicant's Business Equity Link Jump Links from summary table to Other Sources Of Income section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[13]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Other Sources Of Income");
        assertTrue(webDriver.getPageSource().contains("Other Sources Of Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[3]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment12 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[3]")));
        analystAssessment12.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 29- Click the Applicant's Business Equity link Jump Links from
        // summary table to Other Sources Of Income section
        logger.info(
                "Step 29 - Click the Applicant's Business Equity Link Jump Links from summary table to Other Sources Of Income section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[2]/div[1]/table/tbody/tr[14]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Other Sources Of Income");
        assertTrue(webDriver.getPageSource().contains("Other Sources Of Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[3]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment13 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[3]")));
        analystAssessment13.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 30 verify the result 'Total Assert'
        logger.info("Step 30 - verify the result 'Total Assert'");
        assertTrue(webDriver.getPageSource().contains("Total Assets"));
        logger.info(webDriver.findElement(
                By.xpath("//article[@id='main-content']/form/div/div[2]/div/div/div[2]/div/table/tbody/tr[15]/td[2]")));
        // Step 31 - Click the Accounts Payable link Jump Links from summary
        // table After move to 'Notes Payable' section
        logger.info(
                "Step 31 - Click the Accounts Payable link Jump Links from summary table After move to 'Notes Payable' section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[1]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Notes Payable");
        assertTrue(webDriver.getPageSource().contains("Notes Payable"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[11]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment14 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[11]")));
        analystAssessment14.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[11]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[11]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[11]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 32 - Click the Notes Payable to Banks & Others link Jump Links
        // from summary table After move to 'Notes Payable' section
        logger.info(
                "Step 32 - Click the Notes Payable to Banks & Others link Jump Links from summary table After move to 'Notes Payable' section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[2]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Notes Payable");
        assertTrue(webDriver.getPageSource().contains("Notes Payable"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[11]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment15 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[11]")));
        analystAssessment15.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[11]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[11]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[11]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 33- Click the Automobiles link Jump Links from summary table to
        // Personal Property section
        logger.info(
                "Step 33 - Click the Installment Account (Auto) Link Jump Links from summary table after move to Personal Property section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[3]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Personal Property");
        assertTrue(webDriver.getPageSource().contains("Personal Property"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[10]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment16 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[10]")));
        analystAssessment16.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[10]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 34 - Click the Automobiles link Jump Links from summary table to
        // Personal Property section
        logger.info(
                "Step 34 - Click the Installment Account (Auto) Link Jump Links from summary table after move to Personal Property section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[4]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Personal Property");
        assertTrue(webDriver.getPageSource().contains("Personal Property"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[10]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment17 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[10]")));
        analystAssessment17.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[10]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[10]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 35- Click the Cash Surrender Value of Whole Life Insurance Jump
        // Links from summary table after move to Life Insurance section
        logger.info(
                "Step 35 - Click the Cash Surrender Value of Whole Life Insurance Link Jump Links from summary table after move to Life Insurance section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[5]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Life Insurance");
        assertTrue(webDriver.getPageSource().contains("Life Insurance"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[6]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment18 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[6]")));
        analystAssessment18.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[6]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[6]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[6]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 36 - Click the Mortgage (Primary Residence)* Link Jump Links
        // from summary table after move to Real Estate - Primary Residence
        // section
        logger.info(
                "Step 36 - Click the Mortgage (Primary Residence)* Link Jump Links from summary table to Real Estate - Primary Residence section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[6]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Real Estate - Primary Residence");
        assertTrue(webDriver.getPageSource().contains("Real Estate - Primary Residence"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[8]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment19 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[8]")));
        analystAssessment19.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[8]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[8]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[8]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 37- Click the Other Real Estate link Jump Links from summary
        // table after move to Real Estate - Other section
        logger.info(
                "Step 37 - Click the Other Real Estate Link Jump Links from summary table after move to Real Estate - Other section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[7]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Real Estate - Other");
        assertTrue(webDriver.getPageSource().contains("Real Estate - Other"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[9]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment20 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[9]")));
        analystAssessment20.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[9]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[9]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[9]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();

        // Step 38- Click the Unpaid Taxes link Jump Links from summary table
        // after move to Adjusted Gross Income section
        logger.info(
                "Step 38 - Click the Unpaid Taxes Link Jump Links from summary table after move to Adjusted Gross Income section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[8]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Adjusted Gross Income");
        assertTrue(webDriver.getPageSource().contains("Adjusted Gross Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[13]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment21 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[13]")));
        analystAssessment21.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 39- Click the Other Liabilities link Jump Links from summary
        // table after move to Adjusted Gross Income section
        logger.info(
                "Step 39 - Click the Other Liabilities Link Jump Links from summary table after move to Adjusted Gross Income section");
        webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[9]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Adjusted Gross Income");
        assertTrue(webDriver.getPageSource().contains("Adjusted Gross Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[13]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment22 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[13]")));
        analystAssessment22.selectByVisibleText("Makes vendor ineligible");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 40 verify the result 'Total Liabilities'
        logger.info("Step 40 - verify the result 'Total Liabilities'");
        assertTrue(webDriver.getPageSource().contains("Total Liabilities"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[10]/td[2]")).getText());
        // Step 41 verify the result 'Total Assets - Total Liabilities'
        logger.info("Step 41 - verify the result 'Total Assets - Total Liabilities'");
        assertTrue(webDriver.getPageSource().contains("Total Assets - Total Liabilities"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='table1-pad']/table/tbody/tr[11]/td[2]")).getText());
        // Step 42 verify the result 'Sources of Income'
        logger.info("Step 42 - verify the result 'Sources of Income'");
        assertTrue(webDriver.getPageSource().contains("Sources of Income"));
        logger.info("The saerch results show Sources of Income, Pass.");
        // Step 43- Click the Salary link Jump Links from summary table to Other
        // Sources Of Income section
        logger.info("Step 43 - Click the Salary Link Jump Links from summary table to Other Sources Of Income section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[3]/div[1]/table/tbody/tr[1]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Other Sources Of Income");
        assertTrue(webDriver.getPageSource().contains("Other Sources Of Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[3]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment23 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[3]")));
        analystAssessment23.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 44- Click the Investment Income Jump Links from summary table to
        // Stocks & Bonds section
        logger.info(
                "Step 44 - Click the Investment Income Link Jump Links from summary table to Stocks & Bonds section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[3]/div[1]/table/tbody/tr[2]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Stocks & Bonds");
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[7]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment24 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[7]")));
        analystAssessment24.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[7]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[7]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[7]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 45- Click the Real Estate Income Link Jump Links from summary
        // table after move to Real Estate - Primary Residence section
        logger.info(
                "Step 45 - Click the Real Estate Income Link Jump Links from summary table to Real Estate - Primary Residence section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[3]/div[1]/table/tbody/tr[3]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Real Estate - Primary Residence");
        assertTrue(webDriver.getPageSource().contains("Real Estate - Primary Residence"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[8]"))
                .getText());

        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment25 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[8]")));
        analystAssessment25.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[8]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[8]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[8]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 46 - Click the Other Income link Jump Links from summary table
        // after move to Other Sources Of Income section
        logger.info(
                "Step 46 - Click the Other Income Link Jump Links from summary table after move to Other Sources Of Income section");
        webDriver
                .findElement(By
                        .xpath(".//*[@id='main-content']/form/div/div[2]/div/div/div[3]/div[1]/table/tbody/tr[4]/td[1]/a"))
                .click();
        logger.info("Verify that the data is saved to Other Sources Of Income");
        assertTrue(webDriver.getPageSource().contains("Other Sources Of Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[3]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment26 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[3]")));
        analystAssessment26.selectByVisibleText("Information missing");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[3]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 47 verify the result 'Adjusted Gross Income (AGI) '
        logger.info("Step 47 - verify the result 'Adjusted Gross Income (AGI) '");
        assertTrue(webDriver.getPageSource().contains("Adjusted Gross Income (AGI)"));
        logger.info("The saerch results Adjusted Gross Income (AGI), Pass.");
        // Step 48 - Click the Most Recent Tax Year link Jump Links from summary
        // table after move to Adjusted Gross Income section
        logger.info(
                "Step 48 - Click the Most Recent Tax Year Link Jump Links from summary table after move to Adjusted Gross Income section");
        webDriver.findElement(By.xpath(".//*[@id='table2-pad']/table/tbody/tr[1]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Adjusted Gross Income");
        assertTrue(webDriver.getPageSource().contains("Adjusted Gross Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[13]"))
                .getText());
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment27 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[13]")));
        analystAssessment27.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 49 - Click the Year 2 Tax year link Jump Links from summary
        // table after move to Adjusted Gross Income section
        logger.info(
                "Step 48 - Click the Year 2 Tax Year Link Jump Links from summary table after move to Adjusted Gross Income section");
        webDriver.findElement(By.xpath(".//*[@id='table2-pad']/table/tbody/tr[2]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Adjusted Gross Income");
        assertTrue(webDriver.getPageSource().contains("Adjusted Gross Income"));
        logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[13]"))
                .getText());

        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment28 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[13]")));
        analystAssessment28.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 50 - Click the Year 3 Tax year link Jump Links from summary
        // table after move to Adjusted Gross Income section
        logger.info(
                "Step 48 - Click the Year 3 Tax Year Link Jump Links from summary table after move to Adjusted Gross Income section");
        webDriver.findElement(By.xpath(".//*[@id='table2-pad']/table/tbody/tr[3]/td[1]/a")).click();
        logger.info("Verify that the data is saved to Adjusted Gross Income");
        if (webDriver.getPageSource().contains("Adjusted Gross Income")) {
            logger.info(webDriver.findElement(By.xpath(".//*[@id='main-content']/form/div/div[2]/div/div/form/div[13]"))
                    .getText());
        } else {
            logger.info("Data is saved to Adjusted Gross Income -  Failed");
        }
        Thread.sleep(5000);
        logger.info("Select the Analyest assessment");
        Select analystAssessment29 = new Select(
                webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[13]")));
        analystAssessment29.selectByVisibleText("Needs further review");
        Thread.sleep(3000);
        logger.info("Click Add a Note, viewMore,Less and Top, Check Not permited to enter more than 500 characters");
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[13]")).sendKeys(addNote);
        webDriver.findElement(By.xpath("(//a[@id='note_link'])[13]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.linkText("Top")).click();
        // Step 51 verify the result 'Total (Avg)'
        logger.info("Step 51 - verify the result 'Total (Avg)'");
        try {
            assertTrue(webDriver.getPageSource().contains("$200.00"));
            logger.info(webDriver.findElement(By.xpath(".//*[@id='table2-pad']/table/tbody/tr[4]/td[2]")).getText());
        } catch (Error e) {
            logger.info("Result not found - Faild");
        }
        webDriver.findElement(By.xpath(".//*[@id='save_notes']")).click();
        Thread.sleep(3000);
        // Step 52 - Click the Logout link
        logger.info("Step 52 - Logout link clicked");
        webDriver.findElement(By.id("logoutid")).click();
        Thread.sleep(3000);
        logger.info("END OF TEST");

    }

    @After
    public void tearDown() {
        String verificationErrorString = verificationErrors.toString();
        if (verificationErrorString != null) {
            logger.info("Success: " + verificationErrorString);
        }
        webDriver.quit();

    }
}
