// TS_Created_By_Deepa_Patri
package gov.sba.pageObjetcs;

import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.click_Element;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.take_ScreenShot_TestCaseName;
import static gov.sba.pageObjetcs.ProgramsPage.join_New_Program_CheckBoxes;

import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import gov.sba.automation.ConfigUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.utils.integration.LoginPageWithDetails;
import gov.sba.utils.integration.newMppUploadDocumentPageDeepa;
import junit.framework.TestCase;


/*
 * Documentation for Workflow WorkFlows for EDWOSB - Accommodating best minimal Workflow Tests
 * TestWorkflowEDWOSB + 01. Vendor Create - 8a Yes , logout. Update draft submit , Analyst Review,
 * Supervisor Approve 02. Vendor Create - 8a No , Submit, Analyst Review, Supervisor Approve 03.
 * Vendor Create - 8a Yes, Submit, Analyst Review, Supervisor reject 04. Vendor Create - 8a Yes,
 * Submit, Analyst return, Vendor Change Draft , Resubmit, Analyst Review, Supervisor Approve 05.
 * Vendor Create - 8a Yes, Submit, Analyst return, Vendor Change Draft , Resubmit, AAnalyst Review,
 * Supervisor reject 06. Vendor Create - 8a Yes, Submit, Annual Review, ReSubmit, Supervisor Review,
 * Supervisor Approve 07. Vendor Create - 8a No , Submit, Annual Review, ReSubmit, Supervisor
 * Review, Supervisor Reject
 */


@Category({gov.sba.utils.integration.StableTests.class})
public class TestUploadOneFileAllLogins extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(TestUploadOneFileAllLogins.class.getName());
  WebDriver webDriver;
  String duns_Number, email, password, typ_App;

  @Before
  public void setUp() throws Exception {
    get_Stop_Execution_Flag();
    clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
  }

  @Test
  public void testMainTest() throws Exception {
    try {

      String csvFile = FixtureUtils.resourcesDir()
          + ConfigUtils.loadDefaultProperties().getProperty("fixture_file");

      CSVReader reader = new CSVReader(new FileReader(csvFile), CSVParser.DEFAULT_SEPARATOR,
          CSVParser.DEFAULT_QUOTE_CHARACTER, 1);

      String[] detailFields;

      while ((detailFields = reader.readNext()) != null) {

        String email = detailFields[0];
        String password = detailFields[1];
        logger.info(email + "-----" + password);
        new LoginPageWithDetails(webDriver, email, password).Login_With_Details();
        join_New_Program_CheckBoxes(webDriver, "MPP");
        click_Element(webDriver, "All_Answers_Yes");
        new newMppUploadDocumentPageDeepa(webDriver)
            .deepaUploadMppDocument(FixtureUtils.fixturesDir() + "Upload.pdf");

        navigationMenuClick(webDriver, "Logout");
        webDriver.get(TestHelpers.getBaseUrl());
      }

    } catch (Exception e) {
      logger.info("Search TextBox is on Main Navigator is not present" + e.toString());
      take_ScreenShot_TestCaseName(webDriver, new String[] {"TestWorkflowEDWOSB01", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
