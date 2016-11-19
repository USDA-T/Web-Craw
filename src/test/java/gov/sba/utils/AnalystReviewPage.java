package gov.sba.utils;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import junit.framework.TestCase;

public class AnalystReviewPage extends TestCase {
	WebDriver webDriver;
 	public void TestReviewDriver(WebDriver mydriver) {
		this.webDriver = mydriver;
	}
 	private static final Logger AnalystReviewPage = LogManager.getLogger(AnalystReviewPage.class.getName());
    public void testMainTest() throws Exception {
		try { 	
		   List < WebElement > current_Row = webDriver.findElements(
		           By.xpath("//div[@id='table-search']/table/tbody/tr[ td[8][contains(text(),'Submitted')]   ]"));
		
		   if (current_Row.size() > 1) {
			   AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
			   WebElement a1 = current_Row.get(1).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
			   AnalystReviewPage.info(a1.getText());
			   a1.click();
			   // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
			   WebElement current_Page_Title = webDriver.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/h1"));
			   AnalystReviewPage.info(current_Page_Title.getText());
			
			   String Expected_Text = "Case Overview";
			   assertEquals(Expected_Text, current_Page_Title.getText());
			   
			   WebElement current_Review_Text = webDriver.findElement(By.xpath("//h2[@class='usa-width-one-third']"));
			   assertEquals("Start a review", current_Review_Text.getText());
			
			   Select dropdown = new Select(webDriver.findElement(By.id("review_type")));
			  assertEquals(1, dropdown.getOptions().size());
			  assertEquals("Initial Review", dropdown.getFirstSelectedOption().getText());
			
			   Select dropdown1 = new Select(webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_reviewer_id']")));
			   dropdown1.selectByIndex(0);
			
			   Select dropdown2 = new Select(webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_owner_id']")));
			   dropdown2.selectByIndex(1);
			
			   Select dropdown3 = new Select(webDriver.findElement(By.xpath("//select[@id='review_current_assignment_attributes_supervisor_id']")));
			   dropdown3.selectByIndex(1);
			   webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
			   webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
			
			       webDriver.navigate().back();
			       webDriver.navigate().back();
			  }
			   current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ td[8][contains(text(),'Under Review')]  ]"));
			if (current_Row.size() > 0) {
			   AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
			   WebElement a1 = current_Row.get(1).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
			   AnalystReviewPage.info(a1.getText());
			   a1.click();
			   AnalystReviewPage.info("alkanaaaaaa");
			   webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
			   AnalystReviewPage.info("aaaalkj");
			                   
			   //Question Review Page                
			   webDriver.findElement(By.xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]")).click();
			   List<WebElement> dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
			   AnalystReviewPage.info( dropdown.get(0).getText());
			   assertEquals("Confirmed", dropdown.get(0).getText());
			   assertEquals("Not reviewed", dropdown.get(1).getText());
			   assertEquals("Information missing", dropdown.get(2).getText());
			   assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
			   assertEquals("Needs further review", dropdown.get(4).getText());
			   webDriver.findElement(By.id("note_link")).click();
			   webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding notes QA");
			   webDriver.findElement(By.id("save_notes")).click();
			   //Signature Review Page
               webDriver.findElement(By.xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Signature review')]")).click();
               dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']"))).getOptions();
               AnalystReviewPage.info( dropdown.get(0).getText());
               assertEquals("Confirmed", dropdown.get(0).getText());
               assertEquals("Not reviewed", dropdown.get(1).getText());
               assertEquals("Information missing", dropdown.get(2).getText());
               assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
               assertEquals("Needs further review", dropdown.get(4).getText());
               webDriver.findElement(By.id("note_link")).click();
               webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']")).sendKeys("Adding notes QA Signature Page");
               webDriver.findElement(By.xpath("//*[@id='new_assessment']/div/input[@value='Save and commit']")).click();          
               
			}
		}catch (Exception e) {
			AnalystReviewPage.info(e.toString());
	        }	
			   
   }
	
}    
			
