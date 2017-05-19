// TS Created By _deepa patri
package gov.sba.utils.integration;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import gov.sba.pageObjetcs.programs_Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class delete {
    // Set The variabl.es/Define
    private static final Logger TestAnalystReview =
            LogManager.getLogger(delete.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 58;
    }

    @Test
    public void testMainTest() throws Exception {
        try{
            TestAnalystReview.info("Test EDWOSB Sole-Proprietorship two partners on form413 with review");
            // Login to Dashboard.
            LoginPageWithReference login_Data =
                    new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();

            new programs_Page().select_MyCertifications(webDriver, "Delete_8a_Initial_Draft");

        } catch (Exception e) {
            TestAnalystReview.info(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}


