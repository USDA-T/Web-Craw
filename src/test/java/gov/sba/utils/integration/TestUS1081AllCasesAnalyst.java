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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Category({gov.sba.utils.integration.StableTests.class})

public class TestUS1081AllCasesAnalyst extends TestCase {
  private static final Logger logger_US1081 =
          LogManager.getLogger(TestUS1081AllCasesAnalyst.class.getName());
  // Set The variabl.es/Define
  private static WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {

    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
        CommonApplicationMethods.get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
    CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {

    // Create a application to check
    LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
    login_Data.Login_With_Details();

    CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
            new String[] {"TestUS1081AllCasesAnalyst", "Login_Screenshot"});


    programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
    String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
    logger_US1081.info(file_path_abs);
    fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
    fillApplCreatePages.finalSignatureSubmit(webDriver);
    CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");

    // Login to verify analyst Dashboard
    LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 21);
    login_Data_01.Login_With_Reference();

    try {

      CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
      CommonApplicationMethods.casesPageSearch(webDriver, "WOSB");
      logger_US1081.info("Cases link is on Main Navigator is Clicked");

      String Allcases_PageTitle = webDriver
              .findElement(By.xpath("//article[@id='main-content']//h1[contains(text(),'ases')]"))
              .getText();
      assertEquals(Allcases_PageTitle, "Cases");

      String[] header_Names_Array =
              new String[] {"Business name", "DUNS", "Program", "Review type", "Submitted", "Owner",
                      "Current reviewer", "Certificate Status", "Recommendation & Determination"};
      List<WebElement> rows_Header =
              webDriver.findElements(By.xpath("//div[@id='table-search']/table/thead/tr/th"));

      String[]                       header_Names_Array_Validate = new String[9];
      java.util.Iterator<WebElement> list_elements               = rows_Header.iterator();
      int                            i                           = 0;
      while (list_elements.hasNext()) {
        logger_US1081.info(i);
        header_Names_Array_Validate[i] = list_elements.next().getText();
        i = i + 1;
      }

      Assert.assertArrayEquals(header_Names_Array, header_Names_Array_Validate);

      Connection databaseConnection = DatabaseUtils.getDatabaseConnection();

      logger_US1081.info(databaseConnection);

      Statement statement_SQL = databaseConnection.createStatement();
      ResultSet result_Set =
              statement_SQL.executeQuery(" SELECT F.legal_business_name AS legal_Name, "
                      + "		 C.duns_number AS duns_No, " + "		 G.name AS cert_Name, "
                      + "		 to_char(A.application_submitted_at, 'mm/dd/yyyy') AS sub_Date , "
                      + "		 I.workflow_state AS sub_Status" + " 	FROM "
                      + "			sbaone.sba_applications A INNER JOIN sbaone.organizations C ON (A.organization_id = C.id), "
                      + "			sbaone.certificate_types 		G , "
                      + "			reference.mvw_sam_organizations 	F,"
                      + "			sbaone.certificates I"
                      + " 	where(  A.workflow_state 		= 'submitted'				"
                      + "       AND A.progress 				->>'current' = 'signature')	"
                      + "       AND C.duns_number 			= F.duns"
                      + "       AND C.duns_number 			= '" + duns_Number + "' "
                      + "       and G.name != 'mpp' " + "       and G.name != 'eight_a' "
                      + "       and G.name != 'edwosb' "
                      + "       and  I.organization_id =  A.organization_id "
                      + "		order by sub_Date Asc, cert_Name Desc ;	");

      List<ArrayList<String>> db_rows_array = new ArrayList<>();
      while (result_Set.next()) {
        ArrayList<String> db_rows_Cell = new ArrayList<>();

        db_rows_Cell.add(result_Set.getString("legal_Name").toUpperCase());
        db_rows_Cell.add(result_Set.getString("duns_No"));
        db_rows_Cell.add(result_Set.getString("cert_Name").toUpperCase());
        db_rows_Cell.add(result_Set.getString("sub_Date"));
        db_rows_Cell.add(result_Set.getString("sub_Status").toUpperCase());
        // Add a Row
        db_rows_array.add(db_rows_Cell);
      }
      logger_US1081.info("db data :" + db_rows_array.toString());

      CommonApplicationMethods.search_Cases_Duns_Number_Table(webDriver, duns_Number);
      result_Set.close();

      // Entire Table Verification for next Sprint
      List<ArrayList<String>> ui_rows_array = new ArrayList<>();
      List<WebElement> rows_Body = webDriver.findElements(By
              .xpath("//div[@id='table-search']/table/tbody/tr[ td[position()=2]/a[ contains( text(),'"
                      + duns_Number + "') ] ]")); // Get

      for (int j = 0; j < rows_Body.size(); j++) {
        ArrayList<String> ui_rows_Cell = new ArrayList<>();
        logger_US1081.info(rows_Body.get(j).getAttribute("innerHTML"));
        List<WebElement> rows_Body_Cells = rows_Body.get(j).findElements(By.xpath("td"));

        logger_US1081.info("+++++++" + rows_Body_Cells.size());
        ui_rows_Cell.add(rows_Body_Cells.get(0).getText().toUpperCase());
        ui_rows_Cell.add(rows_Body_Cells.get(1).getText());
        ui_rows_Cell.add(rows_Body_Cells.get(2).getText().toUpperCase());
        ui_rows_Cell.add(rows_Body_Cells.get(4).getText());
        ui_rows_Cell.add(rows_Body_Cells.get(7).getText().toUpperCase());
        ui_rows_array.add(ui_rows_Cell);
      }
      logger_US1081.info(ui_rows_array.toString());
      for (int j = 0; j < ui_rows_array.size(); j++) {
        for (int k = 0; k < ui_rows_array.get(j).size(); k++) {
          logger_US1081.info("Asserting" + ui_rows_array.get(j).get(k).toString());
          assertEquals(ui_rows_array.get(j).get(k), db_rows_array.get(j).get(k));
        }
      }

    } catch (Exception e) {
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
              new String[] {"TestUS1081AllCasesAnalyst", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
