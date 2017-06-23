// TS created by Deepa Patri
package gov.sba.utils.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import junit.framework.TestCase;

public class SuperVisorReviewPage extends TestCase {
    WebDriver webDriver;
    String duns_Number;

    public void TestReviewDriver(WebDriver mydriver, String duns_Numb) {
        this.webDriver = mydriver;
        this.duns_Number = duns_Numb;
    }

    private static final Logger SuperVisorReviewPage = LogManager.getLogger(SuperVisorReviewPage.class.getName());

    public void testMainTest() throws Exception {
        try {
            List<WebElement> current_Row = webDriver.findElements(
                    By.xpath("//div[@id='table-search']/table/tbody/tr[ " + "td[position()=2 and contains(text(),'"
                            + duns_Number + "')]	and " + "td[position()=3 and contains(text(),'EDWOSB')]	" + "]"));

            SuperVisorReviewPage.info(current_Row.size() + ": Is the total Submitted Elements");
            SuperVisorReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
            WebElement a1 = current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
            SuperVisorReviewPage.info(a1.getText() + "__1");
            a1.click();
            // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
            WebElement current_Page_Title = webDriver
                    .findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/h1"));
            SuperVisorReviewPage.info(current_Page_Title.getText());

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
            webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
            // Question Review Page
            webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_outer')]/div[contains(@class,'review_nav')]/div/aside/ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Question review')]"))
                    .click();
            List<WebElement> dropdown11 = new Select(
                    webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
            SuperVisorReviewPage.info(dropdown11.get(0).getText());
            assertEquals("Confirmed", dropdown11.get(0).getText());
            assertEquals("Not reviewed", dropdown11.get(1).getText());
            assertEquals("Information missing", dropdown11.get(2).getText());
            assertEquals("Makes vendor ineligible", dropdown11.get(3).getText());
            assertEquals("Needs further review", dropdown11.get(4).getText());
            webDriver.findElement(By.id("note_link")).click();
            webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding notes QA");
            webDriver.findElement(By.id("save_notes")).click();

            // Signature Review Page
            webDriver
                    .findElement(By
                            .xpath("//div[@id='question-review']/div/div/aside/ul[@class='usa-sidenav-list']/li/a[contains(text(),'Signature review')]"))
                    .click();
            dropdown11 = new Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']"))).getOptions();
            SuperVisorReviewPage.info(dropdown11.get(0).getText());
            assertEquals("Confirmed", dropdown11.get(0).getText());
            assertEquals("Not reviewed", dropdown11.get(1).getText());
            assertEquals("Information missing", dropdown11.get(2).getText());
            assertEquals("Makes vendor ineligible", dropdown11.get(3).getText());
            assertEquals("Needs further review", dropdown11.get(4).getText());
            webDriver.findElement(By.id("note_link")).click();
            webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']"))
                    .sendKeys("Adding notes QA Signature Page");

            webDriver.findElement(By.xpath("//*[@id='new_assessment']/div/input[contains(@value,'Save and commit')]"))
                    .click();
            // *[@id="main-content"]/div[2]/div[1]/div/aside/ul/li[5]/a

            webDriver
                    .findElement(By
                            .xpath("//ul[contains(@class,'usa-sidenav-list')]/li/a[contains(text(),'Determination')]"))
                    .click();

            String text_CheckBox_Labels = webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Review Started')]"))
                    .getText();
            Assert.assertEquals(text_CheckBox_Labels, "Review Started");

            text_CheckBox_Labels = webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Return for Modification')]"))
                    .getText();
            Assert.assertEquals(text_CheckBox_Labels, "Return for Modification");

            text_CheckBox_Labels = webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Recommend Eligible')]"))
                    .getText();
            Assert.assertEquals(text_CheckBox_Labels, "Recommend Eligible");

            text_CheckBox_Labels = webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Recommend Ineligible')]"))
                    .getText();
            Assert.assertEquals(text_CheckBox_Labels, "Recommend Ineligible");

            text_CheckBox_Labels = webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Determination Made')]"))
                    .getText();
            Assert.assertEquals(text_CheckBox_Labels, "Determination Made");

            text_CheckBox_Labels = webDriver
                    .findElement(By
                            .xpath("//div[contains(@class,'review_main')]/form[@id='new_determination']/fieldset/ul/li[input[contains(@name,'review[workflow_state]')]]/label[contains(text(),'Decision')]"))
                    .getText();
            Assert.assertEquals(text_CheckBox_Labels, "Decision");

            Select dropdwn = new Select(webDriver.findElement(By.xpath("//select[@id='determination_decision']")));
            // assertEquals("Decline Ineligible", dropdwn.get(0).getText());
            // assertEquals("Sba Approved", dropdwn.get(1).getText());
            dropdwn.selectByIndex(1);

            webDriver.findElement(By.id("assessment_note_body")).sendKeys("QA Test");

            // webDriver.findElement(By.xpath("//input[@type='submit']")).click();

        } catch (Exception e) {
            SuperVisorReviewPage.info(e.toString());
        }

    }
}
