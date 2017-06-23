// TS_Created_By_Deepa_Patri
package gov.sba.utils.integration;

import static gov.sba.automation.CommonApplicationMethods.find_Element;
import static gov.sba.automation.CommonApplicationMethods.navigationMenuClick;
import static gov.sba.automation.CommonApplicationMethods.non_Vendor_searchDuns_Number;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestApp188SessionExpire extends TestCase {
    private static final Logger TestApp188SessionExpire = LogManager.getLogger(TestApp188SessionExpire.class.getName());
    WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        CommonApplicationMethods.get_Stop_Execution_Flag();
        CommonApplicationMethods.clear_Env_Chrome();
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        get_The_Row_From_Login_Data = 27;

    }

    @Test
    public void testMainTest() throws Exception {
        try {
            // For all below test cases we are just deleting cookies. Further
            // updates can be done to
            // Expire them Login to dashboard. Ops Support Staff
            new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data).Login_With_Reference();
            // Asserting Selectable App 187 AC
            assertEquals(find_Element(webDriver, "OppSup_Dashboard_Govt_User_Radio").getText(), "Government User");
            assertEquals(find_Element(webDriver, "OppSup_Dashboard_Vend_User_Radio").getText(), "Vendor User");
            non_Vendor_searchDuns_Number(webDriver, "111");

            TestApp188SessionExpire.info(webDriver.manage().getCookies());
            for (Cookie ck : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck.getName());
                TestApp188SessionExpire.info(ck.getValue());
                webDriver.manage().deleteCookie(ck);
            }

            webDriver.navigate().refresh();

            try {
                navigationMenuClick(webDriver, "DASHBOARD");
            } catch (Exception e) {
                TestApp188SessionExpire.info("Its Good");
            }

            assertEquals(find_Element(webDriver, "OppSup_Dashboard_Business_Signin").getAttribute("value"), "Sign-in");

            navigationMenuClick(webDriver, "Home");

            // Login to dashboard. Analyst
            new LoginPageWithReference(webDriver, 11).Login_With_Reference();

            non_Vendor_searchDuns_Number(webDriver, "111");

            TestApp188SessionExpire.info(webDriver.manage().getCookies());

            for (Cookie ck : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck.getName());
                TestApp188SessionExpire.info(ck.getValue());
                webDriver.manage().deleteCookie(ck);
                webDriver.navigate().refresh();
            }

            try {
                navigationMenuClick(webDriver, "DASHBOARD");
            } catch (Exception e) {
                TestApp188SessionExpire.info("Its Good");
            }

            assertEquals(find_Element(webDriver, "OppSup_Dashboard_Business_Signin").getAttribute("value"), "Sign-in");
            navigationMenuClick(webDriver, "Home");

            // Login to dashboard. Vendor
            new LoginPageWithReference(webDriver, 9).Login_With_Reference();

            find_Element(webDriver, "Navigation_Dashboard");
            TestApp188SessionExpire.info(webDriver.manage().getCookies());
            for (Cookie ck1 : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck1.getName());
                TestApp188SessionExpire.info(ck1.getValue());
                webDriver.manage().deleteCookie(ck1);
            }
            webDriver.navigate().refresh();

            try {
                navigationMenuClick(webDriver, "DASHBOARD");
            } catch (Exception e) {
                TestApp188SessionExpire.info("Its Good");
            }
            assertEquals(find_Element(webDriver, "OppSup_Dashboard_Business_Signin").getAttribute("value"), "Sign-in");

        } catch (Exception e) {
            TestApp188SessionExpire.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
                    new String[] { "TestApp188SessionExpire", "Exception" });
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
