//TS Created By _deepa patri
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestApp188SessionExpire extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp188SessionExpire = LogManager.getLogger(TestApp188SessionExpire.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        get_The_Row_From_Login_Data = 27;

    }

    @Test
    public void testMainTest() throws Exception {
        try {
            // For all below test cases we are just deleting cookies. Further
            // updates can be done to Expire them

            // Login to dashboard. Ops Support Staff
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();

            // Asserting Selectable App 187 AC
            assertEquals(CommonApplicationMethods.find_Element(webDriver, "OppSup_Dashboard_Govt_User_Radio" ).getText(),
                    "Government User");
            assertEquals(CommonApplicationMethods.find_Element(webDriver, "OppSup_Dashboard_Vend_User_Radio" ).getText(),
                    "Vendor User");
            CommonApplicationMethods.searchDuns_Number(webDriver, "111");

            TestApp188SessionExpire.info(webDriver.manage().getCookies());
            for (Cookie ck : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck.getName());
                TestApp188SessionExpire.info(ck.getValue());
                webDriver.manage().deleteCookie(ck);
            }

            CommonApplicationMethods.navigationMenuClick(webDriver, "DASHBOARD");

            assertEquals(webDriver.findElement(By.id("business_signin")).getAttribute("value"), "Sign-in");

            CommonApplicationMethods.navigationMenuClick(webDriver, "Home");

            // Login to dashboard. Analyst
            login_Data = new LoginPageWithReference(webDriver, 11);
            login_Data.Login_With_Reference();


            CommonApplicationMethods.searchDuns_Number(webDriver, "111");

            TestApp188SessionExpire.info(webDriver.manage().getCookies());
            for (Cookie ck : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck.getName());
                TestApp188SessionExpire.info(ck.getValue());
                webDriver.manage().deleteCookie(ck);
            }

            CommonApplicationMethods.navigationMenuClick(webDriver, "DASHBOARD");
            assertEquals(CommonApplicationMethods.find_Element(webDriver, "OppSup_Dashboard_Business_Signin").getAttribute("value"), "Sign-in");
            CommonApplicationMethods.navigationMenuClick(webDriver, "Home");

            // Login to dashboard. Vendor
            login_Data = new LoginPageWithReference(webDriver, 9);
            login_Data.Login_With_Reference();

            CommonApplicationMethods.find_Element(webDriver, "Navigation_Dashboard");
            TestApp188SessionExpire.info(webDriver.manage().getCookies());
            for (Cookie ck : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck.getName());
                TestApp188SessionExpire.info(ck.getValue());
                webDriver.manage().deleteCookie(ck);
            }

            CommonApplicationMethods.navigationMenuClick(webDriver, "Dashboard");
            assertEquals(webDriver.findElement(By.id("business_signin")).getAttribute("value"), "Sign-in");

        } catch (Exception e) {
            TestApp188SessionExpire.info(e.toString());
            CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver, new String[]{"TestApp188SessionExpire", "Exception"});
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}