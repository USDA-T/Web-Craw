package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
// @Category(StableTests.class)
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
        // Get the login based on the environment under test (e.g.
        // 'development', 'qa', 'staging')
        logger.info("FYI: your environment under test:" + System.getProperty(Constants.TEST_ENV));
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }

    @Test()
    // @Category(StableTests.class)
    public void testMainLogic() throws Exception {
        logger.debug("Using test login   : " + LoginHelpers.getLoginData().getEmail());
        logger.debug("Using test password: " + LoginHelpers.getLoginData().getPassword());
        webDriver.findElement(By.cssSelector("button.button-full")).click();
        webDriver.findElement(By.name("user[email]")).sendKeys(LoginHelpers.getLoginData().getEmail());
        webDriver.findElement(By.name("user[password]")).sendKeys(LoginHelpers.getLoginData().getPassword());
        webDriver.findElement(By.id("business_signin")).click();
        String url = webDriver.getCurrentUrl();
        org.junit.Assert.assertTrue(url.contains("dashboard"));
    }

}
