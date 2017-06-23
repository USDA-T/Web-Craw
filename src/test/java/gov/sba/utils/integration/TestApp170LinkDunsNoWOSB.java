// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.casesPageSearch;
import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.navigationBarClick;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
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
public class TestApp170LinkDunsNoWOSB extends TestCase {
    private static final Logger TestApp170LinkDunsNo = LogManager.getLogger(TestApp170LinkDunsNoWOSB.class.getName());
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
        CommonApplicationMethods.focus_window();
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

            programs_Page.join_New_Program_CheckBoxes(webDriver, "WOSB");
            fillApplCreatePages.page8aFillUp(webDriver, "Yes", FixtureUtils.fixturesDir() + "Upload.pdf");
            fillApplCreatePages.finalSignatureSubmit(webDriver);
            navigationMenuClick(webDriver, "Logout");

            new LoginPageWithReference(webDriver, 11).Login_With_Reference();

            // Click on Case Link on main navigator-- SBA Analyst
            navigationBarClick(webDriver, "Cases");

            casesPageSearch(webDriver, duns_Number);
            CommonApplicationMethods.click_Element(webDriver, "Xpath",
                    "//div[@id='table-search']/table/tbody//a[contains(text(),'" + duns_Number + "')]");
            find_Element(webDriver, "xpath",
                    "//p[ b[contains(text(),'DUNS:')] and span[contains(text(),'" + duns_Number + "')] ]");
            navigationBarClick(webDriver, "Logout");

        } catch (Exception e) {
            TestApp170LinkDunsNo.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
                    new String[] { "TestApp170LinkDunsNoWOSB", "Exception" });
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
