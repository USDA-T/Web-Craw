package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

public class TestApp188SessionExpire extends TestCase {
    // Set The variabl.es/Define
    WebDriver webDriver;
    private static final Logger TestApp188SessionExpire = LogManager.getLogger(TestApp188SessionExpire.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
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
            Thread.sleep(2000);

            // Asserting Selectable App 187 AC
            assertEquals(webDriver.findElement(By.xpath("//li[input[@id='user_type_gov_user']]/label")).getText(),
                    "Government User");
            assertEquals(webDriver.findElement(By.xpath("//li[input[@id='user_type_vendor_user']]/label")).getText(),
                    "Vendor User");
            webDriver.findElement(By.xpath("//div[@role='search']/input[@id='query']")).sendKeys("111");
            webDriver
                    .findElement(By
                            .xpath("//*[@id='analyst-search']//button[@type='submit']/span[contains(text(),'Search')]"))
                    .click();

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
            Thread.sleep(2000);

            webDriver.findElement(By.xpath("//div[@role='search']/input[@id='query']")).sendKeys("111");
            webDriver
                    .findElement(By
                            .xpath("//*[@id='analyst-search']//button[@type='submit']/span[contains(text(),'Search')]"))
                    .click();

            TestApp188SessionExpire.info(webDriver.manage().getCookies());
            for (Cookie ck : webDriver.manage().getCookies()) {
                TestApp188SessionExpire.info(ck.getName());
                TestApp188SessionExpire.info(ck.getValue());
                webDriver.manage().deleteCookie(ck);
            }

            CommonApplicationMethods.navigationMenuClick(webDriver, "DASHBOARD");
            assertEquals(webDriver.findElement(By.id("business_signin")).getAttribute("value"), "Sign-in");
            CommonApplicationMethods.navigationMenuClick(webDriver, "Home");

            // Login to dashboard. Vendor
            login_Data = new LoginPageWithReference(webDriver, 9);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);

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
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}