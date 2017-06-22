
//// TS_Created_By_Deepa_Patri
// package gov.sba.utils.integration;
// import gov.sba.automation.CommonApplicationMethods;
// import gov.sba.automation.DatabaseUtils;
// import gov.sba.automation.FixtureUtils;
// import gov.sba.automation.TestHelpers;
// import gov.sba.pageObjetcs.programs_Page;
// import junit.framework.TestCase;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.experimental.categories.Category;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.support.ui.Select;
//
// import java.util.List;
//
// import static gov.sba.automation.CommonApplicationMethods.*;
//
// @Category({gov.sba.utils.integration.StableTests.class})
// public class TestUS1674AnalystReviewPage extends TestCase {
// private static final Logger logger_US1674 =
//// LogManager.getLogger(TestUS1674AnalystReviewPage.class.getName());
// // Set The variabl.es/Define
// private static WebDriver webDriver;
// String duns_Number, email, password;
//
// @Before
// public void setUp() throws Exception {
//
// CommonApplicationMethods.clear_Env_Chrome();
// webDriver = TestHelpers.getDefaultWebDriver();
//
// webDriver.get(TestHelpers.getBaseUrl());
// CommonApplicationMethods.focus_window();
// String[] details = DatabaseUtils.findUnusedDunsNumber();
// email = details[0];
// password = details[1];
// duns_Number = details[2];
// }
//
// @Test
// public void testMainTest() throws Exception {
//
// try {
// LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
// login_Data.Login_With_Details();
//
// navigationMenuClick(webDriver, "Programs");
// programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
// String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
// logger_US1674.info(file_path_abs);
// fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
// fillApplCreatePages.finalSignatureSubmit(webDriver);
// navigationMenuClick(webDriver, "Logout");
//
// LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, 11);
// login_Data1.Login_With_Reference();
//
// navigationMenuClick(webDriver, "Cases");
// casesPageSearch(webDriver, duns_Number);
// logger_US1674.info("Cases link is on Main Navigator is Clicked");
//
// // assertFalse(webDriver.getPageSource().contains("Under Review"));
//
// List<WebElement> current_Row_WOSB = find_Elements(webDriver, "xpath",
//// "//div[@id='table-search']/table/tbody/tr[ td[3][contains(text(),'WOSB')] and
//// td[8][contains(text(),'Active')] and td[4][not(contains(text(),'eview'))] ]");
//
// if (current_Row_WOSB.size() > 0) {
// for (int i = 0; i < current_Row_WOSB.size(); i++) {
// logger_US1674.info(current_Row_WOSB.get(i).getText());
// assertTrue(current_Row_WOSB.get(i).findElement(By.xpath("td[6]")).getText().length() <= 0);
// assertTrue(current_Row_WOSB.get(i).findElement(By.xpath("td[7]")).getText().length() <= 0);
// }
//
// searchDuns_Number(webDriver, duns_Number);
//
// click_Element(webDriver, "SBA_Legal_Businesss_Name_Link");
//
// click_Element(webDriver, "SBA_WOSB_Cert_Link");
// assertEquals("Case Overview", find_Element(webDriver, "Case_CaseOverview_title").getText());
// assertEquals("Start a review", find_Element(webDriver,
//// "Case_CaseOverview_startReview").getText());
//
// Select dropdown = new Select(find_Element(webDriver, "Case_Current_ReviewType"));
// assertEquals(1, dropdown.getOptions().size());
// assertEquals("Initial Review", dropdown.getFirstSelectedOption().getText());
//
// new Select(find_Element(webDriver, "Case_Current_Reviewer")).selectByVisibleText("Analyst2 X");
// new Select(find_Element(webDriver, "Case_Current_Owner")).selectByVisibleText("Analyst2 X");
// new Select(find_Element(webDriver, "Case_Current_Supervisor")).selectByVisibleText("Analyst2 X");
// click_Element(webDriver, "Application_Common_Submit_Button_Id");
// webDriver.navigate().back();
//
// }
//
// navigationMenuClick(webDriver, "Cases");
// casesPageSearch(webDriver, duns_Number);
// // assertFalse(webDriver.getPageSource().contains("Under Review"));
// current_Row_WOSB = CommonApplicationMethods.find_Element_Loc(webDriver, "xpath",
//// "//div[@id='table-search']/table/tbody/tr[ td[3][contains(text(),'WOSB')] and
//// td[8][contains(text(),'Active')] and td[4][(contains(text(),'eview'))] ]");
//
// if (current_Row_WOSB.size() > 0) {
// for (int i = 0; i < current_Row_WOSB.size(); i++) {
// assertTrue(current_Row_WOSB.get(i).findElement(By.xpath("td[6]")).getText().length() > 0);
// assertTrue(current_Row_WOSB.get(i).findElement(By.xpath("td[7]")).getText().length() > 0);
// }
// }
//
// // //Under Review Process Changed - to be updated
// //
// // current_Row_WOSB = webDriver.findElements(By.xpath(
// // "//div[@id='table-search']/table/tbody/tr[
// // td[3][contains(text(),'WOSB')] and
// // td[8][contains(text(),'ctive')] ]"));
// //
// // if (current_Row_WOSB.size() <= 0) {
// //
// // logger_US1674.info(current_Row_WOSB.get(0).getAttribute("innerHTML"));
// //
// // WebElement a1 = current_Row_WOSB.get(0)
// // .findElement(By.xpath("td/a[contains(text(),'Legal Business
// // Name')]"));
// // logger_US1674.info(a1.getText());
// // a1.click();
// // logger_US1674.info("alkanaaaaaa");
// //
// // webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
// // logger_US1674.info("aaaalkj");
// //
// // webDriver
// // .findElement(By
// //
//// .xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question
// // review')]"))
// // .click();
// // List<WebElement> dropdown = new Select(
// // webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
// // logger_US1674.info(dropdown.get(0).getText());
// // assertEquals("Confirmed", dropdown.get(0).getText());
// // assertEquals("Not reviewed", dropdown.get(1).getText());
// // assertEquals("Information missing", dropdown.get(2).getText());
// // assertEquals("Makes vendor ineligible",
// // dropdown.get(3).getText());
// // assertEquals("Needs further review", dropdown.get(4).getText());
// // webDriver.findElement(By.id("note_link")).click();
// // webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding
// // notes QA");
// // webDriver.findElement(By.id("save_notes")).click();
// //
// // webDriver.findElement(By.xpath("//a[@class='expand_notes']")).click();
// //
// // webDriver
// // .findElement(By
// //
//// .xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Signature
// // review')]"))
// // .click();
// //
// // dropdown = new
// // Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']")))
// // .getOptions();
// // logger_US1674.info(dropdown.get(0).getText());
// // assertEquals("Confirmed", dropdown.get(0).getText());
// // assertEquals("Not reviewed", dropdown.get(1).getText());
// // assertEquals("Information missing", dropdown.get(2).getText());
// // assertEquals("Makes vendor ineligible",
// // dropdown.get(3).getText());
// // assertEquals("Needs further review", dropdown.get(4).getText());
// // webDriver.findElement(By.id("note_link")).click();
// // webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']"))
// // .sendKeys("Adding notes QA Signature Page");
// //
// // webDriver.findElement(By.xpath("//*[@id='new_assessment']/div/input[@value='Save
// // and commit']"))
// // .click();
// //
// // }
// // webDriver
// // .findElement(By
// //
//// .xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Case
// // overview')]"))
// // .click();
//
// } catch (Exception e) {
// logger_US1674.info(e.toString());
// CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
// new String[] {"TestUS1674AnalystReviewPage", "Exception"});
// throw e;
// }
// }
//
// @After
// public void tearDown() throws Exception {
// webDriver.quit();
// }
// }
