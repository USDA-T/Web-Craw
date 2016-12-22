package gov.sba.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

public class TestUs1168IamConfigureSbaOpsSupportRole extends TestCase {
    private static WebDriver webDriver;
    String Email;
    String Passphrase;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        Email = "opssupport1@mailinator.com";
        Passphrase = "password";
    }

    @Test
    public void testMainTest() throws Exception {
        // open firefox, chrome or IE and navigate to certify.sba login page.
        // Login to SBA Ops support dashboard.
        Thread.sleep(5000);
        webDriver.findElement(By.cssSelector("button.usa-button.hp-login-btn")).click();
        Thread.sleep(4000);
        String actual_Text4 = webDriver.findElement(By.cssSelector("span.blue-bar-text")).getText();
        String expected_Text4 = "Sign in to certify.SBA.gov";
        assertEquals(actual_Text4, expected_Text4);
        webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("opssupport1@mailinator.com");
        // Step 2. Locate the password search box and enter a valid password.
        webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("password");
        // Step 3. Locate the Sign-in button and click on it to login.
        webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
        Thread.sleep(5000);
        verifyTrue("Save my work", webDriver.findElement(By.name("commit")));
        // Logout.
        webDriver.findElement(By.linkText("Logout")).click();
    }

    private void verifyTrue(String string, WebElement findElement) {
        // TODO Auto-generated method stub
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}