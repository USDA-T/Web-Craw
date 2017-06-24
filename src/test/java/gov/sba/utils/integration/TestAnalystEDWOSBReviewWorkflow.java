//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
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

import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.utils.integration.FillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.FillApplCreatePages.page8aFillUp;


@Category({gov.sba.utils.integration.StableTests.class})
public class TestAnalystEDWOSBReviewWorkflow extends TestCase {
  private static final Logger logger_TestEDWOSBWorkflow =
          LogManager.getLogger(TestAnalystEDWOSBReviewWorkflow.class.getName());
  WebDriver webDriver;
  String    duns_Number, email, password, typ_App;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    //focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0]; password = details[1]; duns_Number = details[2];
  }

  @Test
  //Test US1647- EDWosb financial section link disbaled for application not having finacial parterns information
  public void testMainTest() throws Exception {
    try {
      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();
      programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
      page8aFillUp(webDriver, "Yes");
      finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "LOGOUT");
      navigationBarClick(webDriver, "Cases");
      //casesPageSearch(webDriver, "146323332");
      List<WebElement> current_Row_EDWOSB = find_Elements(webDriver, "xpath", "//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'" + duns_Number + "')]  ]/td[1]/a", true);
     // List<WebElement> current_Row_EDWOSB = find_Elements(webDriver, "xpath", "//div[@id='table-search']/table/tbody/tr[  td[position()=2]/a[contains(text(),'146323332')]  ]/td[1]/a");


      current_Row_EDWOSB.get(0).click();
      assertEquals("Case Overview", find_Element(webDriver,"Case_CaseOverview_title").getText());
      assertEquals("Open application summary", find_Element(webDriver,"SBA_Case_Overview_Open_Application_Summary").getText());
      assertEquals("Return to Vendor", find_Element(webDriver,"SBA_Case_Overview_Return_to_vendor").getText());
      click_Element(webDriver,"Case_Submit_Button");
      //Verify the Question review page
      assertNotNull(find_Element(webDriver, "SBA_Question_Review_Fill_Up_SideNav", true));
      List<WebElement> dropdown = new Select(find_Element(webDriver,"SBA_Assesment_Status")).getOptions();
      logger_TestEDWOSBWorkflow.info(dropdown.get(0).getText());
      assertEquals("Confirmed", dropdown.get(0).getText());
      assertEquals("Not reviewed", dropdown.get(1).getText());
      assertEquals("Information missing", dropdown.get(2).getText());

      assertEquals("Makes vendor ineligible", dropdown.get(3).getText());
      assertEquals("Needs further review", dropdown.get(4).getText());
      click_Element(webDriver,"SBA_Note_Link");
      setText_Element(webDriver, "SBA_Assesments_Note_Body", "Adding notes QA");
      click_Element(webDriver,"Application_Common_Save_Notes");

      //For Wosb Financial review link not exist
      assertNull(find_Element(webDriver, "SBA_Question_Financial_Review_SideNav", true));
      // CommonApplicationMethods.click_Element(webDriver, "WOSB_Self_Certification_Link");
      //Signature page
      assertNotNull(find_Element(webDriver, "SBA_Question_Signature_Review_SideNav", true));
      dropdown = new Select(find_Element(webDriver,"SBA_Question_Assesment_Status_Options")).getOptions();
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


    }
    catch (Exception e) {
      logger_TestEDWOSBWorkflow.info("Search TextBox is on Main Navigator is not present" + e.toString());
      take_ScreenShot_TestCaseName(webDriver, new String[] {"TestAnalystEDWOSBReviewWorkflow", "Exception"});
      throw e;

    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
