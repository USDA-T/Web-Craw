// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.casesPageSearch;
import static gov.sba.automation.CommonApplicationMethods.clear_Env_Chrome;
import static gov.sba.automation.CommonApplicationMethods.focus_window;
import static gov.sba.automation.CommonApplicationMethods.get_Stop_Execution_Flag;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.utils.integration.fillApplCreatePages.finalSignatureSubmit;
import static gov.sba.utils.integration.fillApplCreatePages.page8aFillUp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })

public class TestAnalystEDWOSBReviewWorkflow extends TestCase {
    private static final Logger logger_TestEDWOSBWorkflow = LogManager
            .getLogger(TestAnalystEDWOSBReviewWorkflow.class.getName());
    WebDriver webDriver;
    String duns_Number, email, password, typ_App;

    @Before
    public void setUp() throws Exception {
        get_Stop_Execution_Flag();
        clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        focus_window();
        String[] details = DatabaseUtils.findUnusedDunsNumber();
        email = details[0];
        password = details[1];
        duns_Number = details[2];
    }

    @Test
    public void testMainTest() throws Exception {
        try {
            LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();
            programs_Page.join_New_Program_CheckBoxes(webDriver, "EDWOSB");
            page8aFillUp(webDriver, "Yes", FixtureUtils.fixturesDir() + "Upload.pdf");
            finalSignatureSubmit(webDriver);

            // Assert Application is Created
            Assert.assertTrue(CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active"));

            navigationMenuClick(webDriver, "Logout");
            new LoginPageWithReference(webDriver, 11).Login_With_Reference();
            navigationBarClick(webDriver, "Cases");
            casesPageSearch(webDriver, duns_Number);

        } catch (Exception e) {
            logger_TestEDWOSBWorkflow.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
                    new String[] { "TestAnalystEDWOSBReviewWorkflow", "Exception" });
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
