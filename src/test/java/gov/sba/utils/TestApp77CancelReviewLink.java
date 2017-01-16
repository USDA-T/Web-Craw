package gov.sba.utils;

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
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.DatabaseQuery;
import gov.sba.utils.helpers.FixtureUtils;
import junit.framework.TestCase;

public class TestApp77CancelReviewLink extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp77CancelReviewLink = LogManager
            .getLogger(TestApp77CancelReviewLink.class.getName());
    int get_The_Row_From_Login_Data;
    String duns_Number = "196374813";
    
    @Before
    public void setUp() throws Exception {
    	commonApplicationMethods.deleteAllApplicationTypes(webDriver, duns_Number);
    	webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 41;
    }

    @Test
    public void testMainTest() throws Exception {
        try {
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);

            commonApplicationMethods.createApplication(webDriver, "WOSB");
            webDriver.findElement(By.id("answers_5_value_yes")).click();
            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

            TestApp77CancelReviewLink.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            fillApplCreatePages.finalSignatureSubmit(webDriver);
            TestApp77CancelReviewLink.info("Doc has been uploaded.");

            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            login_Data = new LoginPageWithReference(webDriver, 11);
            login_Data.Login_With_Reference();

            // Start Common Function
            
            // Click on Case Link on main navigator
            commonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            
            List<WebElement> current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr"
                    + "[ "
                    + "td[position()=8 and contains(text(),'Submitted')]   and "
                    + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                    + "td[position()=3 and contains(text(),'WOSB')]	"
                    + "]"));

            if (current_Row.size() > 0) {
                TestApp77CancelReviewLink.info(current_Row.get(0).getAttribute("innerHTML"));
                WebElement a1 = current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
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

                Select dropdown2 = new Select(
                        webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
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
                String organization_Id = commonApplicationMethods.returnOrganization_Id(duns_Number);
                String count_Case = DatabaseQuery.getDBData(
                        	"select count(case_number) from sbaone.reviews "
                        + 	"		where workflow_state = 'cancelled' "
                        + 	"	and sba_application_id 	 = "
                        + 	"								(select max(id) from sbaone.sba_applications where organization_id = " + organization_Id.toString() + " and deleted_at is null )",
                        1, 1 // Row 1 Column 1 from query
                        )[0][0];
                assertEquals(count_Case, "1");

                commonApplicationMethods.navigationMenuClick(webDriver, "Cases");

                webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                        + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]"
                        + "]")).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]")).click();

                fillApplCreatePages.pageCaseOverviewFillup(webDriver, "Initial Review", "Analyst1 X", "Analyst1 X", "Analyst1 X");
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