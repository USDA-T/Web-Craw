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


public class TestUS1674_AnalystReviewPage extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger logger_US1674 = LogManager.getLogger(TestUS1674_AnalystReviewPage.class.getName());
    int get_The_Row_From_Login_Data;

    @
    Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 22;
    }@
    Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
//        Thread.sleep(3000);
        try {

            WebElement Cases_Link = webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
            Cases_Link.click();
            logger_US1674.info("Cases link is on Main Navigator is Clicked");
            //				List<WebElement> current_Row_WOSB = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[td[1][contains(text(),'Legal Business Name')] and td[3][contains(text(),'WOSB')] and td[8][contains(text(),'Submitted')]]"));
            List < WebElement > current_Row_WOSB = webDriver.findElements(
                By.xpath("//div[@id='table-search']/table/tbody/tr[  td[3][contains(text(),'WOSB')]  and td[8][contains(text(),'Submitted')]   ]"));
            //				logger_US1674.info(current_Row_WOSB.size());
            //				logger_US1674.info(current_Row_WOSB.get(0).getAttribute("innerHTML"));
            //
            //				logger_US1674.info(current_Row_WOSB.get(0).findElement(By.xpath("td[1]")).getText());
            //				logger_US1674.info(current_Row_WOSB.get(0).findElement(By.xpath("td[6]")).getText());
            //				logger_US1674.info(current_Row_WOSB.get(0).findElement(By.xpath("td[7]")).getText());


            if (current_Row_WOSB.size() > 8) {
                logger_US1674.info(current_Row_WOSB.get(0).getAttribute("innerHTML"));
                WebElement a1 = current_Row_WOSB.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                logger_US1674.info(a1.getText());
                a1.click();

                // WebElement current_Row_WOSB =
                // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
                WebElement current_Page_Title = webDriver.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/h1"));
                logger_US1674.info(current_Page_Title.getText());


                String Expected_Text = "Case Overview";
                assertEquals(Expected_Text, current_Page_Title.getText());
                WebElement current_Review_Text = webDriver.findElement(By.xpath("//h2[@class='usa-width-one-third']"));
                assertEquals("Start a review", current_Review_Text.getText());

                Select dropdown = new Select(webDriver.findElement(By.id("review_type")));
                assertEquals(1, dropdown.getOptions().size());
                assertEquals("Initial Review", dropdown.getFirstSelectedOption().getText());

                Select dropdown1 = new Select(webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_reviewer_id']")));
                dropdown1.selectByVisibleText("Analyst2 X");

                Select dropdown2 = new Select(webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
                dropdown1.selectByVisibleText("Analyst3 X");

                Select dropdown3 = new Select(webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
                dropdown1.selectByVisibleText("Analyst4 X");
                //webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();

                webDriver.navigate().back();

            }


            current_Row_WOSB = webDriver.findElements(
                By.xpath("//div[@id='table-search']/table/tbody/tr[  td[3][contains(text(),'WOSB')]  and td[8][contains(text(),'Under Review')]   ]"));

            if (current_Row_WOSB.size() > 0) {
            	logger_US1674.info(current_Row_WOSB.get(0).getAttribute("innerHTML"));

                assertTrue(current_Row_WOSB.get(0).findElement(By.xpath("td[6]")).getText().length() > 0);
                assertTrue(current_Row_WOSB.get(0).findElement(By.xpath("td[7]")).getText().length() > 0);

                WebElement a1 = current_Row_WOSB.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                logger_US1674.info(a1.getText());
                a1.click();
                logger_US1674.info("alkanaaaaaa");
                
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                logger_US1674.info("aaaalkj");
                
                
                webDriver.findElement(By.xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]")).click();
                List<WebElement> dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
                logger_US1674.info( dropdown.get(0).getText());
                assertEquals("Confirmed", dropdown.get(0).getText());
                assertEquals("Not reviewed", dropdown.get(1).getText());
                assertEquals("Information missing", dropdown.get(2).getText());
                assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
                assertEquals("Needs further review", dropdown.get(4).getText());
                webDriver.findElement(By.id("note_link")).click();
                webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding notes QA");
                webDriver.findElement(By.id("save_notes")).click();
                
                webDriver.findElement(By.xpath("//a[@class='expand_notes']")).click();

                webDriver.findElement(By.xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Signature review')]")).click();


                dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']"))).getOptions();
                logger_US1674.info( dropdown.get(0).getText());
                assertEquals("Confirmed", dropdown.get(0).getText());
                assertEquals("Not reviewed", dropdown.get(1).getText());
                assertEquals("Information missing", dropdown.get(2).getText());
                assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
                assertEquals("Needs further review", dropdown.get(4).getText());
                webDriver.findElement(By.id("note_link")).click();
                webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']")).sendKeys("Adding notes QA Signature Page");
                
                webDriver.findElement(By.xpath("//*[@id='new_assessment']/div/input[@value='Save and commit']")).click();          
                
            }
            webDriver.findElement(By.xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Case overview')]")).click();
            //String aa = webDriver.findElement(By.xpath("divdiv[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/br[containd(text(),'Owner:')]")).getText();
            webDriver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
			login_Data = new LoginPageWithReference(webDriver, 31);
			
			login_Data.Login_With_Reference();
			webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']")).click();
			current_Row_WOSB =webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[  td[3][contains(text(),'WOSB')]  and td[8][contains(text(),'Under Review')]   ]"));
			current_Row_WOSB.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]")).click();
            webDriver.findElement(By.xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]")).click();
            List<WebElement> dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
            logger_US1674.info( dropdown.get(0).getText());
            dropdown.get(1).click();
            
            String actual_text = webDriver.findElement(By.id("assessments_brief_5")).getText();
            logger_US1674.info(actual_text);

            webDriver.findElement(By.id("note_link")).click();
            webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding notes QA by another Analyst");
            webDriver.findElement(By.id("save_notes")).click();
            
            webDriver.findElement(By.xpath("//a[@class='expand_notes']")).click();		
		                
        } catch (Exception e) {
            logger_US1674.info(e.toString());
        }
    }
}