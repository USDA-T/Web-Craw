//TS_Created_By_Deepa_Patri
//US1674 -Edwosb-financial section left nav enabled and add notes on each review page.Analyst will not have determination-decision option
//______________________________
package gov.sba.utils.integration;
import gov.sba.automation.*;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static gov.sba.automation.AssertionUtils.delete_All_Application_Draft;
import static gov.sba.automation.AssertionUtils.return_All_Applications;
import static gov.sba.automation.CommonApplicationMethods.*;


@Category({gov.sba.utils.integration.StableTests.class})

public class TestWorkflowEDWOSBAnalystReviewSelect8aNo extends TestCase {
  private static final Logger logger = LogManager.getLogger(TestWorkflowEDWOSBAnalystReviewSelect8aNo.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;
  String duns_Number, email, password;

//  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    //CommonApplicationMethods.focus_window();
    get_The_Row_From_Login_Data = 49;

  }

 @Test
  public void testMainTest() throws Exception {

   return_All_Applications(webDriver, 11, "246235962");
   delete_All_Application_Draft(webDriver, get_The_Row_From_Login_Data, duns_Number);
   new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data).Login_With_Reference();// start New Applicatiom
   programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB"); // Create New Edwosb Application with select No to all questions with one person's 413 form
   new NewScorpQuestionPageDeepa(webDriver).NewScorpQuestionPageDeepa();
   new NewFinancialSectionQuestionDeepa(webDriver).NewFinancialQuestion();
   FillApplCreatePages.finalSignatureSubmit(webDriver);
   navigationMenuClick(webDriver, "LOGOUT");
    try {
      new LoginPageWithReference(webDriver, 11).Login_With_Reference();
      navigationBarClick(webDriver, "Cases");
      logger.info("Cases link is on Main Navigator is Clicked");
      search_Cases_Duns_Number_Table(webDriver, "246235962");
      click_Element(webDriver, "SBA_Legal_Businesss_Name_Link");
      assertEquals("Case Overview", find_Element(webDriver,"Case_CaseOverview_title").getText());
      assertEquals("Open application summary", find_Element(webDriver,"SBA_Case_Overview_Open_Application_Summary").getText());
      assertEquals("Return to Vendor", find_Element(webDriver,"SBA_Case_Overview_Return_to_vendor").getText());

      click_Element(webDriver, "Application_Common_Submit_Button_Id");
      click_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav");

      List<WebElement> dropdown = new Select(find_Element(webDriver,"SBA_Assesment_Status")).getOptions();
      logger.info(dropdown.get(0).getText());
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());
      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver,"SBA_Note_Link");

      setText_Element(webDriver, "SBA_Assesments_Note_Body", "Adding notes QA");
      click_Element(webDriver,"Application_Common_Save_Notes");
      //For Edwosb Financial review link -- link is enabled for application having financial section.
      assertNotNull(find_Element(webDriver, "SBA_Question_Financial_Review_SideNav", true));
        click_Element(webDriver,"EDWOSB_Common_Page_Commit");
      //click_Element(webDriver,"SBA_Question_Financial_Review_SideNav");

      // Signature Review Page
      click_Element(webDriver,"SBA_Question_Signature_Review_SideNav");

      dropdown = new Select(find_Element(webDriver,"SBA_Question_Assesment_Status_Options")).getOptions();
      logger.info(dropdown.get(0).getText());
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());
      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver,"SBA_Note_Link");
      setText_Element(webDriver, "SBA_Assesment_Note_Body", "Adding notes QA Signature Page");
      click_Element(webDriver,"EDWOSB_Common_Page_Commit");
      //Determination page
      assertNotNull(find_Element(webDriver, "SBA_Question_Determinations_SideNav", true));
      Assert.assertEquals(find_Element(webDriver, "SBA_Question_New_Determination_Review_Started").getText(), "Review Started");
      Assert.assertEquals(find_Element(webDriver, "SBA_Question_New_Determination_Return_For_Mod").getText(), "Return for Modification");
      Assert.assertEquals(find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_Eligibile").getText(), "Recommend Eligible");
      Assert.assertEquals(find_Element(webDriver, "SBA_Question_New_Determination_Reccomend_For_InEligibile").getText(), "Recommend Ineligible");

      setText_Element(webDriver, "SBA_Assesment_Note_Body", "Qa Test");
      //Verify on Analyst  Detremination page -Determination Made, Decision not displayed
      assertNull(find_Element(webDriver, "SBA_Review_Determ_Made", true));
      assertNull(find_Element(webDriver, "SBA_Review_Determ_Decision", true));

      click_Element(webDriver, "Application_Common_Submit_Button");

      click_Element(webDriver, "SBA_Question_Determinations_SideNav");
      click_Element(webDriver, "Analyst_Review_Determ_Page_Return_Mod_Link");
      //click_Element(webDriver, "SBA_Signature_Review_Save_Continue");

    } catch (Exception e) {
      logger.info(e.toString());
      take_ScreenShot_TestCaseName(webDriver, new String[]{"TestWorkflowEDWOSBAnalystReviewSelect8aNo", "Exception"});
      throw e;
    }
  }

//  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
