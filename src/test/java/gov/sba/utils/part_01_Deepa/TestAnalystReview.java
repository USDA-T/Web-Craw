package gov.sba.utils.part_01_Deepa;

import gov.sba.utils.CommonFunctions.LoginPageWithDetails;
import gov.sba.utils.CommonFunctions.LoginPageWithReference;
import gov.sba.utils.CommonFunctions.TestHelpers;
import gov.sba.utils.WorkflowPages.commonApplicationMethods;
import gov.sba.utils.WorkflowPages.fillApplCreatePages;
import gov.sba.utils.helpers.FixtureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestAnalystReview {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestAnalystReview = LogManager.getLogger(TestAnalystReview.class.getName());
    int get_The_Row_From_Login_Data;
    String duns_Number, email, password;

    @Before
    public void setUp() throws Exception {
        commonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        commonApplicationMethods.focus_window();
        String[] details = commonApplicationMethods.return_Good_Duns_no();
        email = details[0];
        password = details[1];
        duns_Number = details[2];
        get_The_Row_From_Login_Data = 11;
    }

    @Test
    public void testMainTest() throws Exception {
        try{
            LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
            login_Data.Login_With_Details();

            commonApplicationMethods.navigationMenuClick(webDriver, "Programs");
            commonApplicationMethods.createApplication(webDriver, "EDWOSB");

            String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";

            TestAnalystReview.info(file_path_abs);
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", file_path_abs);
            fillApplCreatePages.finalSignatureSubmit(webDriver);

            commonApplicationMethods.navigationMenuClick(webDriver, "Logout");

            LoginPageWithReference login_Data_01 = new LoginPageWithReference(webDriver, 11);
            login_Data_01.Login_With_Reference();
            Thread.sleep(1000);
            // Click on Case Link on main navigator
            // //Come Back Later
//            WebElement Cases_Link = webDriver.findElement(By.cssSelector("a[href*='/sba_analyst/cases']"));
//            Cases_Link.click();
//            AnalystReviewPage TestReviewProcess = new AnalystReviewPage();
//
//            TestReviewProcess.TestReviewDriver(webDriver, duns_Number, "EDWOSB", "Initial Review", "Analyst2 X",
//                    "Analyst3 X", "Analyst4 X");
//            TestReviewProcess.testUnderReview();
//             webDriver.navigate().back();

        } catch (Exception e) {
            TestAnalystReview.info(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
