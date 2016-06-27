package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

public class TestSearchPage extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestSearchPage.class.getName());
    private static WebDriver webDriver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

    @Test
    public void testMainLogic() throws Exception {
        webDriver.findElement(By.xpath(".//*[@id=\"gov_login_box\"]/form[1]/button")).click();
        webDriver.findElement(By.name("user[email]")).sendKeys("analyst1@mailinator.com");
        webDriver.findElement(By.name("user[password]")).sendKeys("password");
        webDriver.findElement(By.id("business_signin")).click();
        String url = webDriver.getCurrentUrl();
        assertTrue(url.contains("dashboard"));
    }
}
