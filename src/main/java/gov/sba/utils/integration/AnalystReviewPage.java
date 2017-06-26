// Created BY Deepa Patri

package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.accept_Alert;
import static gov.sba.automation.CommonApplicationMethods.checkApplicationExists;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.deleteApplication;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.find_Elements;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.setText_Element;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;

public class AnalystReviewPage extends TestCase {
  private static final Logger AnalystReviewPage =
      LogManager.getLogger(AnalystReviewPage.class.getName());
  WebDriver webDriver;
  String duns_Number;
  String typ_App_Passed;
  String rev_Type;
  String curr_Review;
  String owner;
  String supervisor;

  public void TestReviewDriver(WebDriver mydriver, String duns_Numb, String typ_App,
      String rev_Type_p, String curr_Review_p, String owner_p, String supervisor_p) {
    AnalystReviewPage.info("Came In to Set Values");
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
      String zpa = "//div[@id='table-search']/table/tbody/tr[ "
          + "td[position()=8 and contains(text(),'Under Review')]   and "
          + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
          + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]";
      List<WebElement> current_Row = find_Elements(webDriver, "xpath", zpa);
      AnalystReviewPage.info(current_Row.size() + ": Is the total Under Review Elements");
      if (current_Row.size() > 0) {
        testUnderReview();
      }
      zpa = "//div[@id='table-search']/table/tbody/tr[ "
          + "td[position()=8 and contains(text(),'Submitted')]   and "
          + "td[position()=2]/a[contains(text(),'" + duns_Number + "')]	and "
          + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]";
      // else{
      current_Row = find_Elements(webDriver, "xpath", zpa);

      AnalystReviewPage.info(current_Row.size() + ": Is the total Submitted Elements");
      if (current_Row.size() <= 0) {
        // assertEquals("Currently No applications =" ," Could be
        // submitted");
        navigationMenuClick(webDriver, "Logout");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, 10);
        login_Data.Login_With_Reference();

        deleteApplication(webDriver, "WOSB", "Draft");

        if (!checkApplicationExists(webDriver, "WOSB", "Active")) {
          programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");

          click_Element(webDriver, "General_5a_Id_Answer");

          String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

          AnalystReviewPage.info(file_path_abs);
          FillApplCreatePages.page8aFillUp(webDriver, "Yes");
          FillApplCreatePages.finalSignatureSubmit(webDriver);
          AnalystReviewPage.info("Doc has been uploaded.");

        }

        navigationMenuClick(webDriver, "Logout");
        login_Data = new LoginPageWithReference(webDriver, 11);
        login_Data.Login_With_Reference();

      }

      // Start Common Function
      navigationMenuClick(webDriver, "Cases");
      String xpa = "//div[@id='table-search']/table/tbody/tr[ "
          + "td[position()=8 and contains(text(),'Submitted')]   and "
          + "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
          + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]";
      current_Row = find_Elements(webDriver, "xpath", xpa);
      if (current_Row.size() > 0) {
        AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
        WebElement a1 = current_Row.get(0)
            .findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"));
        AnalystReviewPage.info(a1.getText() + "__1");
        a1.click();

        assertEquals("Case Overview", find_Element(webDriver, "Case_CaseOverview_title").getText());

        assertEquals("Start a review",
            find_Element(webDriver, "Case_CaseOverview_startReview").getText());

        Select dropdown = new Select(find_Element(webDriver, "Case_Current_ReviewType"));
        assertEquals(1, dropdown.getOptions().size());
        assertEquals("Initial Review", dropdown.getFirstSelectedOption().getText());

        new Select(find_Element(webDriver, "Case_Current_Reviewer")).selectByIndex(0);
        new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Reviewer_Id"))
            .selectByIndex(0);
        new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Owner_Id")).selectByIndex(1);
        new Select(find_Element(webDriver, "SBA_Case_Overview_Select_Supervisor_Id"))
            .selectByIndex(1);

        click_Element(webDriver, "Case_Submit_Button");
        click_Element(webDriver, "Case_Submit_Button");
        // End Common Function

        // Verify Cancel Review link -APP 77 Acceptance Criteria
        click_Element(webDriver, "Case_Cancel_Review");
        Thread.sleep(1000);
        accept_Alert(webDriver, 22);
        // To call DB-- pass Sql query, no of rows,no of cols to db
        // function
        String sql_query =
            " select D.workflow_state as review_status,D.sba_application_id as app_id , D.updated_at as updated_at"
                + "   from sbaone.organizations A, sbaone.certificates B, sbaone.sba_applications C, sbaone.reviews D "
                + "    where A.id = B.organization_id  " + "    and B.sba_application_id = C.id "
                + "    and c.id = D.sba_application_id " + "    and b.id = D.certificate_id     "
                + "    and  A.duns_number='159165917' " + "    order by D.updated_at desc ";

        DatabaseUtils dbcall = new DatabaseUtils();
        String[][] returned_workflow_review_status = DatabaseUtils.queryForData(sql_query, 2, 3);
        AnalystReviewPage.info(returned_workflow_review_status[1][0].toString()
            + returned_workflow_review_status[1][1].toString()
            + returned_workflow_review_status[1][2].toString());
        Thread.sleep(1000); // Deepa Sleep needed
        assertEquals(returned_workflow_review_status[1][0].toString().toLowerCase(), "cancelled");

        // verify the review status as cancelled- start again review
        // assignment
        // webDriver.navigate().back();
        // webDriver.navigate().back();

        navigationMenuClick(webDriver, "Cases");
        xpa = "//div[@id='table-search']/table/tbody/tr[ "
            + "td[position()=8 and contains(text(),'Submitted')]   and "
            + "td[position()=2 and contains(text(),'" + duns_Number + "')]	and "
            + "td[position()=3 and contains(text()," + typ_App_Passed + ")]	" + "]";
        current_Row = find_Elements(webDriver, "xpath", xpa);

        Assert.assertTrue(current_Row.size() > 0);
        AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
        current_Row.get(0).findElement(By.xpath("td/a[contains(text(),'Legal Business Name')]"))
            .click();
        FillApplCreatePages.pageCaseOverviewFillup(webDriver, rev_Type, curr_Review, owner,
            supervisor);
        click_Element(webDriver, "Case_Submit_Button");
        click_Element(webDriver, "Case_Submit_Button");

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
      String xpath =
          "//div[@id='table-search']/table/tbody/tr[ td[position()=2]/a[contains(text(),'"
              + duns_Number + "')]	and td[position()=3 and contains(text(),'" + typ_App_Passed
              + "')]	" + "]";
      List<WebElement> current_Row = find_Elements(webDriver, "xpath", xpath);

      AnalystReviewPage.info(current_Row.size() + ": Is the total Under Review Elements");

      Assert.assertTrue(current_Row.size() > 0);

      if (current_Row.size() > 0) {

        AnalystReviewPage.info(current_Row.get(0).getAttribute("innerHTML"));
        WebElement a1 = current_Row.get(0).findElement(By.xpath(""));
        AnalystReviewPage.info(a1.getText());
        a1.click();
        click_Element(webDriver, "Application_Common_Submit_Button_Id");

        // Question Review Page
        click_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav");

        List<WebElement> dropdown =
            new Select(find_Element(webDriver, "SBA_Assesment_Status")).getOptions();
        AnalystReviewPage.info(dropdown.get(0).getText());
        assertEquals("Confirmed", dropdown.get(0).getText());
        assertEquals("Not reviewed", dropdown.get(1).getText());
        assertEquals("Information missing", dropdown.get(2).getText());
        assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
        assertEquals("Needs further review", dropdown.get(4).getText());
        click_Element(webDriver, "SBA_Note_Link");

        setText_Element(webDriver, "SBA_Assesments_Note_Body", "Adding notes QA");
        click_Element(webDriver, "Case_SaveNotes_Button ");
        Thread.sleep(800); // Sleep needed here Deepa
        // Signature Review Page
        click_Element(webDriver, "SBA_Question_Signature_Review_SideNav");
        dropdown = new Select(find_Element(webDriver, "SBA_Question_Assesment_Status_Options"))
            .getOptions();
        AnalystReviewPage.info(dropdown.get(0).getText());
        assertEquals("Confirmed", dropdown.get(0).getText());
        assertEquals("Not reviewed", dropdown.get(1).getText());
        assertEquals("Information missing", dropdown.get(2).getText());
        assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
        assertEquals("Needs further review", dropdown.get(4).getText());
        click_Element(webDriver, "SBA_Note_Link");
        setText_Element(webDriver, "SBA_Assesment_Note_Body", "Adding notes QA Signature Page");

        click_Element(webDriver, "SBA_Signature_Review_Save_Continue");
        // *[@id="main-content"]/div[2]/div[1]/div/aside/ul/li[5]/a

        click_Element(webDriver, "SBA_Question_Determinations_SideNav");

        Assert.assertEquals(
            find_Element(webDriver, "SBA_Question_New_Determination_Review_Started").getText(),
            "Review Started");
        Assert.assertEquals(
            find_Element(webDriver, "SBA_Question_New_Determination_Return_For_Mod").getText(),
            "Return for Modification");
        Assert.assertEquals(
            find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_Eligibile")
                .getText(),
            "Recommend Eligible");
        Assert.assertEquals(
            find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_InEligibile")
                .getText(),
            "Recommend Ineligible");
        setText_Element(webDriver, "SBA_Assesment_Note_Body", "Qa Test");
        click_Element(webDriver, "Application_Common_Submit_Button");

        click_Element(webDriver, "SBA_Question_Determinations_SideNav");
        click_Element(webDriver, "SBA_Review_Return_For_Mod");
        click_Element(webDriver, "SBA_Signature_Review_Save_Continue");

        webDriver.navigate().back();
        Thread.sleep(1000);
        webDriver.navigate().back();
        Thread.sleep(1000);
        webDriver.navigate().back();
        Thread.sleep(2000);

        int cntr;
        for (cntr = 0; cntr < 3; cntr++) {
          try {
            navigationMenuClick(webDriver, "Logout");
          } catch (Exception e) { // Go back and try to logout so
                                  // logout button will be active
            webDriver.navigate().back();
            Thread.sleep(500);
          }
        }

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
