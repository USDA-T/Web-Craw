// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import static gov.sba.automation.CommonApplicationMethods.*;

@Ignore
@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowVendorOverviewPage extends TestCase {
  private static final Logger Logger_VendorOverviewPage =
          LogManager.getLogger(TestWorkflowVendorOverviewPage.class.getName());
  // Set The variabl.es/Define
  WebDriver webDriver;
  String duns_Number, email, password;

  @Before
  public void setUp() throws Exception {
    CommonApplicationMethods.get_Stop_Execution_Flag();
    CommonApplicationMethods.clear_Env_Chrome();
    webDriver = TestHelpers.getDefaultWebDriver();
    CommonApplicationMethods.get_Stop_Execution_Flag();
    webDriver.get(TestHelpers.getBaseUrl());
    //CommonApplicationMethods.focus_window();
    String[] details = DatabaseUtils.findUnusedDunsNumber();
    email = details[0];
    password = details[1];
    duns_Number = details[2];
  }

  @Test
  public void testMainTest() throws Exception {
    try {
    //Test APP170, Test APP473- download file zip
      LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();

      programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
      fillApplCreatePages.page8aFillUp(webDriver, "Yes");
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "Logout");

      new LoginPageWithReference(webDriver, 11).Login_With_Reference();

      // Click on Case Link on main navigator-- SBA Analyst
      navigationBarClick(webDriver, "Cases");

      //casesPageSearch(webDriver, duns_Number);
      casesPageSearch(webDriver, "158899368");
     // CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//div[@id='table-search']/table/tbody//a[contains(text(),'" + duns_Number + "')]");
      click_Element(webDriver, "Xpath", "//div[@id='table-search']/table/tbody//a[contains(text(),'158899368')]");
      //find_Element(webDriver, "xpath", "//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]");
      find_Element(webDriver, "xpath", "//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'158899368')] ]");
      //App473- file Zip present
      Assert.assertEquals("Return to Vendor",find_Element(webDriver,"Vendor_Overview_Page_Rt_Vend_All").getText());
      Assert.assertEquals("Download Zip",find_Element(webDriver,"Vendor_Overview_Page_Download_Zip").getText());
      //Return the Application to vendor
      click_Element(webDriver,"Vendor_Overview_Page_Rt_Vend_All");
      navigationBarClick(webDriver, "Logout");
      //Submit the application again
      new LoginPageWithDetails(webDriver, email, password);
      login_Data.Login_With_Details();
      programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
      fillApplCreatePages.page8aFillUp(webDriver, "Yes");
      fillApplCreatePages.finalSignatureSubmit(webDriver);
      navigationMenuClick(webDriver, "Logout");

      new LoginPageWithReference(webDriver, 11).Login_With_Reference();
      // Click on Case Link on main navigator-- SBA Analyst
      navigationBarClick(webDriver, "Cases");
      //casesPageSearch(webDriver, duns_Number);
      casesPageSearch(webDriver, "158899368");
      // CommonApplicationMethods.click_Element_Loc(webDriver, "Xpath", "//div[@id='table-search']/table/tbody//a[contains(text(),'" + duns_Number + "')]");
      click_Element(webDriver, "Xpath", "//div[@id='table-search']/table/tbody//a[contains(text(),'158899368')]");
      //find_Element(webDriver, "xpath", "//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]");
      find_Element(webDriver, "xpath", "//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'158899368')] ]");
      //App473- Verify file Zip present on resumitted Returned applications
      Assert.assertEquals("Return to Vendor",find_Element(webDriver,"Vendor_Overview_Page_Rt_Vend_All").getText());
      Assert.assertEquals("Download Zip",find_Element(webDriver,"Vendor_Overview_Page_Download_Zip").getText());
      //Return the Application to vendor
      click_Element(webDriver,"Vendor_Overview_Page_Rt_Vend_All");
      navigationBarClick(webDriver, "Logout");

    } catch (Exception e) {
      Logger_VendorOverviewPage.info(e.toString());
      CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
              new String[] {"TestWorkflowVendorOverviewPage", "Exception"});
      throw e;
    }
  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}
