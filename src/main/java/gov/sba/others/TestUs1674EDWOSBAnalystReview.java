// TS_Created_By_Deepa_Patri
package gov.sba.others;

import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;
import static gov.sba.pageObjetcs.analyst_Cases_Page.search_Duns_And_Verify;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import gov.sba.utils.integration.FillApplCreatePages;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.LoginPageWithReference;
import junit.framework.TestCase;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestUs1674EDWOSBAnalystReview extends TestCase {
  private static final Logger logger_US1674_EDWOSB =
      LogManager.getLogger(TestUs1674EDWOSBAnalystReview.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
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
    programs_Page.join_New_Program_CheckBoxes(webDriver, app_Type_Passed);

    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

    logger_US1674_EDWOSB.info(file_path_abs);
    FillApplCreatePages.page8aFillUp(webDriver, "Yes");
    FillApplCreatePages.finalSignatureSubmit(webDriver);

    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

    LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
    login_Data_01.Login_With_Reference();
    // Thread.sleep(3000);
    try {

      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      CommonApplicationMethods.casesPageSearch(webDriver, duns_Number);
      logger_US1674_EDWOSB.info("Cases link is on Main Navigator is Clicked");

      List<WebElement> current_Row_EDWOSB = CommonApplicationMethods.find_Elements(webDriver,
          "xpath", "//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'"
              + duns_Number + "')]  ]/td[1]/a");

      if (current_Row_EDWOSB.size() >= 1) {
        current_Row_EDWOSB.get(0).click();

        assertEquals("Case Overview", find_Element(webDriver, "Case_CaseOverview_title").getText());
        assertEquals("Start a review",
            find_Element(webDriver, "Case_CaseOverview_startReview").getText());

        assertEquals(1,
            new Select(find_Element(webDriver, "Case_Current_ReviewType")).getOptions().size());
        assertEquals("Initial Review",
            new Select(find_Element(webDriver, "Case_Current_ReviewType")).getFirstSelectedOption()
                .getText());
        new Select(find_Element(webDriver, "Case_Current_Reviewer")).selectByIndex(0);
        new Select(find_Element(webDriver, "Case_Current_Owner")).selectByVisibleText("Analyst3 X");

        new Select(find_Element(webDriver, "Case_Current_Supervisor"))
            .selectByVisibleText("Analyst4 X");
        click_Element(webDriver, "Case_Submit_Button");
        click_Element(webDriver, "Case_SaveNotes_Button");
        webDriver.navigate().back();
        webDriver.navigate().back();
        CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

      }

      search_Duns_And_Verify(webDriver, duns_Number, "Yes", "", "");

      click_Element(webDriver, "Case_Submit_Button");
      click_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav");

      List<WebElement> dropdown =
          new Select(find_Element(webDriver, "SBA_Assesment_Status")).getOptions();
      logger_US1674_EDWOSB.info(dropdown.get(0).getText());
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());

      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver, "SBA_Note_Link");

      setText_Element(webDriver, "SBA_Assesments_Note_Body", "Adding notes QA");

      click_Element(webDriver, "Application_Common_Save_Notes_Id");
      click_Element(webDriver, "SBA_Question_Financial_Review_SideNav");

      // webDriver.findElement(By.xpath("//a[@class='expand_notes']")).click();
      // Financia Data Review Page
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
      click_Element(webDriver, "SBA_Question_Signature_Review_SideNav");

      dropdown = new Select(find_Element(webDriver, "SBA_Assesment_Status")).getOptions();
      logger_US1674_EDWOSB.info(dropdown.get(0).getText());
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());
      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver, "SBA_Note_Link");
      setText_Element(webDriver, "SBA_Assesment_Note_Body", "Adding notes QA Signature Page");

      click_Element(webDriver, "SBA_Common_Page_Commit");

    } catch (Exception e) {
      logger_US1674_EDWOSB.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
          new String[] {"TestUs1674EDWOSBAnalystReview", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
