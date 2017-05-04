package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.UnstableTests.class })
public class TestEdithPasswordRegressionTest1 extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestEdithPasswordRegressionTest1.class.getName());
    private static WebDriver webDriver;
    String myurl;
    String Email;
    String Current_PassW;
    String Current_PassW2;
    String Weak_PassW;
    String Better_PW;
    String New_PassW;
    String Confirm_New_PassW;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        Email = "staging2@mailinator.com";
        Current_PassW = "password";
        Current_PassW2 = "Map Effect Applied Furniture 3365";
        Weak_PassW = "1234";
        New_PassW = "Map Effect Applied Furniture 9883";
        Confirm_New_PassW = "Map Effect Applied Furniture 9883";
        Better_PW = "Derico#336587";
    }

    @Test
    public void testMainTest() throws Exception {
        // Open Firefox,Chrome,and IE and navigate to the valid url.
        EdithpasswordPage edithpassword = new EdithpasswordPage(webDriver);
        edithpassword.Edithpassword();
        if (webDriver.getPageSource().contains("Invalid email or password")) {
            // Locate the email search box and enter a valid email.
            webDriver.findElement(By.id("user_email")).sendKeys(Email);
            // Locate the password search box and enter a valid password.
            webDriver.findElement(By.id("user_password")).sendKeys(Current_PassW2);
            // Locate the user Sign-in button and click on it.
            webDriver.findElement(By.id("business_signin")).click();
        } else {
            logger.info("First attempt to log-in with current password was successful, PASS");
        }
        Thread.sleep(2000);
        try {
            assertTrue(webDriver.getPageSource().contains("Signed in successfully"));
            // webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
        } catch (Error e) {
            logger.info("Successful sign in alert message not present");
        }
        Thread.sleep(3000);
        // Locate the My Profile button on the left navigation and click on it.
        String actual_Text = webDriver.findElement(By.linkText("Profile")).getText();
        String expected_Text = "Profile";
        assertEquals(actual_Text, expected_Text);
        webDriver.findElement(By.linkText("Profile")).click();
        Thread.sleep(5000);
        // Verify and click on the link Edit Passphrase.
        assertElementpresent(webDriver.findElement(By.cssSelector("a.usa-button")));
        String actual_Text1 = webDriver.findElement(By.cssSelector("a.usa-button")).getText();
        String expected_Text1 = "Edit passphrase";
        assertEquals(actual_Text1, expected_Text1);
        webDriver.findElement(By.cssSelector("a.usa-button")).click();
        Thread.sleep(5000);
        // Verify that user is navigated to the change password page.
        assertElementpresent(webDriver.findElement(By.cssSelector("h1")));
        String actual_Text2 = webDriver.findElement(By.cssSelector("h1")).getText();
        String expected_Text2 = "Edit passphrase";
        assertEquals(actual_Text2, expected_Text2);
        // Enter current and new password and Attempt to Update without entering
        // the confirm new password data.
        webDriver.findElement(By.id("user_current_password")).sendKeys(Current_PassW);
        Thread.sleep(3000);
        webDriver.findElement(By.id("user_password")).sendKeys(New_PassW);
        Thread.sleep(2000);
        logger.info(webDriver.findElement(By.id("text_strength")).getText());
        webDriver.findElement(By.id("submit")).click();
        Thread.sleep(3000);
        // Verify that user sees alert message requesting user to enter all or
        // confirm password.
        String actual_Text5 = webDriver.findElement(By.cssSelector("div.usa-alert-body > ul > li")).getText();
        String expected_Text5 = "Password confirmation doesn't match Password";
        assertTrue(actual_Text5.equals(expected_Text5));
        assertTrue(webDriver.getPageSource().contains("Password confirmation doesn't match Password"));
        logger.info("Usee sees alert messages, Password confirmation doesn't match Password, PASS");
        // webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
        // Enter current and weak new password and Attempt to Update without
        // entering the confirm new password data.
        webDriver.findElement(By.id("user_current_password")).clear();
        webDriver.findElement(By.id("user_current_password")).sendKeys(Current_PassW);
        Thread.sleep(3000);
        webDriver.findElement(By.id("user_password")).clear();
        webDriver.findElement(By.id("user_password")).sendKeys(Weak_PassW);
        Thread.sleep(2000);
        logger.info(webDriver.findElement(By.id("text_strength")).getText());
        Thread.sleep(3000);
        // Verify that the weak password alert is activated when user enter a
        // weak password.
        String actual_Text4 = webDriver.findElement(By.id("password_strength")).getText();
        String expected_Text4 = "Weak";
        assertEquals(actual_Text4, expected_Text4);
        Thread.sleep(3000);
        webDriver.findElement(By.id("user_password")).clear();
        webDriver.findElement(By.id("user_password")).sendKeys(Better_PW);
        Thread.sleep(2000);
        // Verify that the Better password alert is activated when user enter a
        // weak password.
        String actual_Text3 = webDriver.findElement(By.xpath("//div[3]/span")).getText();
        String expected_Text3 = "Better";
        assertEquals(actual_Text3, expected_Text3);
        logger.info("Success");
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }

    private void assertElementpresent(WebElement findElement) {
        // TODO: need to remove this one!
    }
}
