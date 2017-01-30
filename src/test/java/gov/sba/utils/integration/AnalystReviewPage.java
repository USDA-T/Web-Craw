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

public class AnalystReviewPage extends TestCase {
    private static final Logger AnalystReviewPage = LogManager.getLogger(AnalystReviewPage.class.getName());
    WebDriver webDriver;
    String duns_Number;
    String typ_App_Passed;
    String rev_Type;
    String curr_Review;
    String owner;
    String supervisor;

    public void TestReviewDriver(WebDriver mydriver, String duns_Numb, String typ_App, String rev_Type_p,
            String curr_Review_p, String owner_p, String supervisor_p) {
        this.webDriver = mydriver;
        this.duns_Number = duns_Numb;
        this.typ_App_Passed = typ_App;
        this.rev_Type = rev_Type_p;
        this.curr_Review = curr_Review_p;
        this.owner = owner_p;
        this.supervisor = supervisor_p;
    }

    public void testSubmitted() throws Exception {
        try {
            AnalystReviewPage.info("Currently No applications in Submitted state");
            // assertEquals("Currently No applications =" ," in Submitted
            // state");
            List<WebElement> current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                    + "td[position()=8 and contains(text(),'Under Review')]   and "
                    + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                    + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]"));
            AnalystReviewPage.info(current_Row.size() + ": Is the total Under Review Elements");
            if (current_Row.size() > 0) {
                testUnderReview();
            }
            // else{
            current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                    + "td[position()=8 and contains(text(),'Submitted')]   and "
                    + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                    + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]"));

            AnalystReviewPage.info(current_Row.size() + ": Is the total Submitted Elements");
            if (current_Row.size() <= 0) {
                // assertEquals("Currently No applications =" ," Could be
                // submitted");
                CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 10);
                login_Data.Login_With_Reference();

                CommonApplicationMethods.deleteApplication(webDriver, "WOSB", "Draft");

                if (!CommonApplicationMethods.checkApplicationExists(webDriver, "WOSB", "Active")) {
                    CommonApplicationMethods.createApplication(webDriver, "WOSB");

                    webDriver.findElement(By.id("answers_5_value_yes")).click();

                    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

                    AnalystReviewPage.info(file_path_abs);
                    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
                    fillApplCreatePages.finalSignatureSubmit(webDriver);
                    AnalystReviewPage.info("Doc has been uploaded.");

                }

                CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                login_Data = new LoginPageWithReference(webDriver, 11);
                login_Data.Login_With_Reference();

            }

            // Start Common Function
            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                    + "td[position()=8 and contains(text(),'Submitted')]   and "
                    + "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
                    + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]"));
            if (current_Row.size() > 0) {
                AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
                WebElement a1 = current_Row.get(0)
                        .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                AnalystReviewPage.info(a1.getText() + "__1");
                a1.click();
                // webDriver.findElement(By.xpath("//div[@id='table-search']/table[contains(@class,'usa-table')]/tbody/tr/td[text()='WOSB']"));
                WebElement current_Page_Title = webDriver
                        .findElement(By.xpath("//article[@id='main-content']/div/div[2]/h1"));
                AnalystReviewPage.info(current_Page_Title.getText());

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
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                // End Common Function

                // Verify Cancel Review link -APP 77 Acceptance Criteria
                webDriver.findElement(By.xpath("//a[contains(text(),'Cancel review')]")).click();
                Thread.sleep(1000);
                webDriver.switchTo().alert().accept();
                // To call DB-- pass Sql query, no of rows,no of cols to db
                // function
                String sql_query = " select D.workflow_state as review_status,D.sba_application_id as app_id , D.updated_at as updated_at"
                        + "   from sbaone.organizations A, sbaone.certificates B, sbaone.sba_applications C, sbaone.reviews D "
                        + "    where A.id = B.organization_id  " + "    and B.sba_application_id = C.id "
                        + "    and c.id = D.sba_application_id " + "    and b.id = D.certificate_id     "
                        + "    and  A.duns_number='159165917' " + "    order by D.updated_at desc ";

                DatabaseQuery dbcall = new DatabaseQuery();
                String[][] returned_workflow_review_status = dbcall.getDBData(sql_query, 2, 3);
                AnalystReviewPage.info("aaaaa+" + returned_workflow_review_status[1][0].toString()
                        + returned_workflow_review_status[1][1].toString()
                        + returned_workflow_review_status[1][2].toString());
                Thread.sleep(1000);
                assertEquals(returned_workflow_review_status[1][0].toString().toLowerCase(), "cancelled");

                // verify the review status as cancelled- start again review
                // assignment
                // webDriver.navigate().back();
                // webDriver.navigate().back();
                CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");

                current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                        + "td[position()=8 and contains(text(),'Submitted')]   and "
                        + "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
                        + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]"));

                Assert.assertTrue(current_Row.size() > 0);
                AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
                current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]")).click();
                fillApplCreatePages.pageCaseOverviewFillup(webDriver, rev_Type, curr_Review, owner, supervisor);
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
            } else {
                testUnderReview();
            }

        } catch (Exception e) {
            AnalystReviewPage.info(e.toString());
            throw e;
        }

    }

    public void testUnderReview() throws Exception {
        try {
            List<WebElement> current_Row = webDriver.findElements(By.xpath("//div[@id='table-search']/table/tbody/tr[ "
                    // + "td[position()=8 and contains(text(),'Under Review')]
                    // and "
                    + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
                    + "td[position()=3 and contains(text(),'" + typ_App_Passed + "')]	" + "]"));

            AnalystReviewPage.info(current_Row.size() + ": Is the total Under Review Elements");

            if (current_Row.size() > 0) {

                AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
                WebElement a1 = current_Row.get(0)
                        .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
                AnalystReviewPage.info(a1.getText());
                a1.click();
                AnalystReviewPage.info("alkanaaaaaa");
                webDriver.findElement(By.xpath("//input[@id='submit_button']")).click();
                AnalystReviewPage.info("aaaalkj");

                // Question Review Page
                webDriver.findElement(By.xpath("//ul[ contains(@class,'usa-sidenav-list') ]"
                        + "/li/a[ contains(text(),'uestion')  and contains(text(),'review')  ]")).click();

                List<WebElement> dropdown = new Select(
                        webDriver.findElement(By.xpath("//select[@id='assessments__status']"))).getOptions();
                AnalystReviewPage.info(dropdown.get(0).getText());
                assertEquals("Confirmed", dropdown.get(0).getText());
                assertEquals("Not reviewed", dropdown.get(1).getText());
                assertEquals("Information missing", dropdown.get(2).getText());
                assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
                assertEquals("Needs further review", dropdown.get(4).getText());
                webDriver.findElement(By.id("note_link")).click();
                webDriver.findElement(By.xpath("//textarea[@id='assessments__note_body']")).sendKeys("Adding notes QA");
                webDriver.findElement(By.id("save_notes")).click();
                Thread.sleep(2000);
                // Signature Review Page
                webDriver
                        .findElement(
                                By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'signature_review')]"))
                        .click();
                dropdown = new Select(webDriver.findElement(By.xpath("//select[@id='assessment_status']")))
                        .getOptions();
                AnalystReviewPage.info(dropdown.get(0).getText());
                assertEquals("Confirmed", dropdown.get(0).getText());
                assertEquals("Not reviewed", dropdown.get(1).getText());
                assertEquals("Information missing", dropdown.get(2).getText());
                assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
                assertEquals("Needs further review", dropdown.get(4).getText());
                webDriver.findElement(By.id("note_link")).click();
                webDriver.findElement(By.xpath("//textarea[@id='assessment_note_body']"))
                        .sendKeys("Adding notes QA Signature Page");

                webDriver
                        .findElement(By
                                .xpath("//input[contains(@value,'Save') and contains(@value,'and') and contains(@value,'commit')]"))
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
                webDriver.findElement(By.id("assessment_note_body")).sendKeys("QA Test");
                webDriver.findElement(By.xpath("//input[@type='submit']")).click();

                webDriver
                        .findElement(By.xpath("//ul[@class='usa-sidenav-list']/li/a[contains(@href,'determinations')]"))
                        .click();
                webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
                webDriver.findElement(By.xpath("//input[contains(@value,'Save and commit')]")).click();

                webDriver.navigate().back();
                webDriver.navigate().back();
                webDriver.navigate().back();

                CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
                LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 11);
                login_Data.Login_With_Reference();
                Thread.sleep(2000);

            }
        } catch (Exception e) {
            AnalystReviewPage.info(e.toString());
            throw e;

        }

    }

}