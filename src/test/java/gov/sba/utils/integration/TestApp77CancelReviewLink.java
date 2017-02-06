package gov.sba.utils.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import junit.framework.TestCase;

public class TestApp77CancelReviewLink extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp77CancelReviewLink = LogManager
            .getLogger(TestApp77CancelReviewLink.class.getName());
    int get_The_Row_From_Login_Data;
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        CommonApplicationMethods.focus_window();
        String[] details = CommonApplicationMethods.return_Good_Duns_no();
        email = details[0];
        password = details[1];
        duns_Number = details[2];
    }

    @Test
    public void testMainTest() throws Exception {
        try {

            LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();

            CommonApplicationMethods.createApplication(webDriver, "WOSB");
            webDriver.findElement(By.id("answers_5_value_yes")).click();
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

            TestApp77CancelReviewLink.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            fillApplCreatePages.finalSignatureSubmit(webDriver);
            TestApp77CancelReviewLink.info("Doc has been uploaded.");

            CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
            login_Data_01.Login_With_Reference();

            // Start Common Function

            // Click on Case Link on main navigator
            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

            List<WebElement> current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr"
                    + "[ " + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                    + "td[position()=3 and contains(text(),'WOSB') and not(contains(text(),'EDWOSB'))]	" + "]"));

            if (current_Row.size() > 0) {
                TestApp77CancelReviewLink.info(current_Row.get(0).getAttribute("innerHTML"));
                WebElement a1 = current_Row.get(0)
                        .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                TestApp77CancelReviewLink.info(a1.getText() + "__1");
                a1.click();
                // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
                WebElement current_Page_Title = webDriver
                        .findElement(By.xpath("//article[@id='main-content']/div/div[2]/h1"));
                TestApp77CancelReviewLink.info(current_Page_Title.getText());

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

                Select dropdown2 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
                dropdown2.selectByIndex(1);

                Select dropdown3 = new Select(webDriver
                        .findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
                dropdown3.selectByIndex(1);

                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                webDriver.findElement(By.xpath("//a[contains(text(),'Case overview')]")).click();

                // End Common Function

                // Verify Cancel Review link -APP 77 Acceptance Criteria
                webDriver.findElement(By.xpath("//a[contains(text(),'Cancel review')]")).click();
                Thread.sleep(1000);
                webDriver.switchTo().alert().accept();
                Thread.sleep(3000);
                String organization_Id = CommonApplicationMethods.returnOrganization_Id(duns_Number);
                String sql_Q = "select count(case_number) from sbaone.reviews "
                        + "		where workflow_state = 'cancelled' " + "	and sba_application_id 	 = "
                        + "								(select max(id) from sbaone.sba_applications where organization_id = "
                        + organization_Id.toString() + " and deleted_at is null )";
                TestApp77CancelReviewLink.info(sql_Q);
                String[][] count_Case = DatabaseQuery.getDBData(sql_Q, 1, 1);
                String count_Case_Count = count_Case[0][0];
                assertEquals(count_Case_Count, "1");

                CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

                webDriver
                        .findElement(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                                + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]" + "]"))
                        .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]")).click();

                fillApplCreatePages.pageCaseOverviewFillup(webDriver, "Initial Review", "Analyst1 X", "Analyst1 X",
                        "Analyst1 X");
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                webDriver.findElement(By.xpath("//input[@id='save_notes']")).click();

                webDriver.findElement(By.xpath("//a[contains(text(),'Case overview')]")).click();
                List<WebElement> cancelLInk = webDriver.findElements(By.xpath("//a[contains(text(),'Cancel review')]"));
                assertEquals(cancelLInk.size(), 0);
                //
            }

        } catch (Exception e) {
            TestApp77CancelReviewLink.info(e.toString());
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}