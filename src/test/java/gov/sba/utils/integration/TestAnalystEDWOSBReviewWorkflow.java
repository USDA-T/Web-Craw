package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.UnstableTests.class })
public class TestAnalystEDWOSBReviewWorkflow extends TestCase {
    WebDriver webDriver;
    private static final Logger logger_TestEDWOSBWorkflow = LogManager
            .getLogger(TestAnalystEDWOSBReviewWorkflow.class.getName());
    String duns_Number, email, password, typ_App;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        CommonApplicationMethods.focus_window();
        String[] details = CommonApplicationMethods.findUnusedDunsNumber();
        email = details[0];
        password = details[1];
        duns_Number = details[2];

    }

    @Test
    public void testMainTest() throws Exception {
        try {
            LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();
            Thread.sleep(1500);
            typ_App = "EDWOSB";

            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

            CommonApplicationMethods.navigationMenuClick(webDriver, "Programs");
            CommonApplicationMethods.createApplication(webDriver, typ_App);
            logger_TestEDWOSBWorkflow.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            fillApplCreatePages.finalSignatureSubmit(webDriver);

            // Assert Application is Created
            org.junit.Assert.assertTrue(CommonApplicationMethods.checkApplicationExists(webDriver, "EDWOSB", "Active"));

            CommonApplicationMethods.navigationMenuClick(webDriver, "Logout");
            LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
            login_Data_01.Login_With_Reference();
            CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            AnalystReviewPage TestReviewProcess = new AnalystReviewPage();
            TestReviewProcess.TestReviewDriver(webDriver, duns_Number, typ_App, "Initial Review", "Analyst2 X",
                    "Analyst3 X", "Analyst4 X");
            TestReviewProcess.testUnderReview();

            // //Come Back Later
            // CommonApplicationMethods.navigationMenuClick(webDriver,
            // "Logout");
            // login_Data_01 = new LoginPageWithReference(webDriver, 31);
            // login_Data_01.Login_With_Reference();
            // CommonApplicationMethods.navigationMenuClick(webDriver, "Cases");
            // SuperVisorReviewPage TestReviewProcess1 = new
            // SuperVisorReviewPage();
            // TestReviewProcess1.TestReviewDriver(webDriver, duns_Number);
            // TestReviewProcess1.testMainTest();

        } catch (Exception e) {
            logger_TestEDWOSBWorkflow.info(e.toString());
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}