//TS Created BY Deepa Patri
//TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import gov.sba.automation.AssertionUtils;
import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static gov.sba.automation.AssertionUtils.delete_All_Application_Draft;
import static gov.sba.automation.AssertionUtils.return_All_Applications;
import static gov.sba.automation.CommonApplicationMethods.*;
import static gov.sba.pageObjetcs.programs_Page.join_New_Program_CheckBoxes;
@Category({gov.sba.utils.integration.StableTests.class})
public class TestWorkflowWOSBVendorSelecting8aNo extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestWorkflowWOSBVendorSelecting8aNo.class.getName());
    private static WebDriver webDriver;
    String duns_Number, email, password;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        get_Stop_Execution_Flag();
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        get_The_Row_From_Login_Data = 41;
    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.
        try {
            //Sample Delete and Return
            return_All_Applications(webDriver, 11, "159165917");
            delete_All_Application_Draft(webDriver, get_The_Row_From_Login_Data, duns_Number);
            new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data).Login_With_Reference();

            // start New WOSB Applicatiom
            join_New_Program_CheckBoxes(webDriver, "WOSB");
            new NewLLCQuestionanireDeepa().NewLLCQuestionanireDeepa(webDriver);
            FillApplCreatePages.finalSignatureSubmit(webDriver);
            //Return the Application to Vendor
            navigationMenuClick(webDriver, "LOGOUT");
            return_All_Applications(webDriver, 11, "159165917");
            new LoginPageWithReference(webDriver, 41).Login_With_Reference();
            //Verify application's returns to vendor after application is returned by Analyst
            String xpath_Element = "//*[@id='certifications']/tbody/tr[ (td[position()=5 and contains(text(),'Draft')]) and (td[position()" +
                    "  =1]/a[contains(text(),'WOSB')]) ]";
            List<WebElement> listOfDraftWOSB = find_Elements(webDriver, "xpath", xpath_Element);
            Assert.assertTrue(listOfDraftWOSB.size() > 0);
            AssertionUtils.delete_all_Drafts(webDriver);

        } catch (Exception e) {
            logger.info(e.toString());
            take_ScreenShot_TestCaseName(webDriver, new String[]{TestWorkflowCreateWosbLLc.class.getName(), "Exception"});
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {

        webDriver.quit();
    }
}
