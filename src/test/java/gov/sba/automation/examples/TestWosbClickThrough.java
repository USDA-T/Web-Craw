package gov.sba.automation.examples;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.sba.automation.BrowserUtils;
import gov.sba.automation.CoreUtils;
import gov.sba.automation.CoreWait;

public class TestWosbClickThrough {

    private static final Logger logger = LoggerFactory.getLogger(TestWosbClickThrough.class);

    private static WebDriverWait wdriver;
    private static WebDriver driver;
    private String programName;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        BrowserUtils browserUtils = new BrowserUtils();
        browserUtils.initFirefox();

        wdriver = browserUtils.getWaitDriver();
        driver = browserUtils.getWebDriver();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.programName = "wosb";
    }

    @After
    public void tearDown() throws Exception {
    }

    public void login(String username, String password) {
        driver.findElement(By.cssSelector(".button-full")).click();
        wdriver.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#user_email")));

        driver.findElement(By.cssSelector("#user_email")).sendKeys(username);
        driver.findElement(By.cssSelector("#user_password")).sendKeys(password);
        driver.findElement(By.cssSelector("#business_signin")).click();
    }

    @Test
    public void testClickThrough() throws Exception {
        logger.info("FYI: click through for program:" + programName);

        driver.get("http://localhost:3000");
        CoreWait.waitForPresenceOfElementLocated(wdriver, By.cssSelector(".button-full"));

        login("john@mailinator.com", "password");

        By locator = By.partialLinkText("Programs");
        CoreWait.waitForElementToBeClickable(driver, wdriver, locator);

        String actual = driver.findElement(locator).getText();
        CoreUtils.assertContentEquals("Programs", actual);

        // Now let's click the program link
        CoreUtils.locateAndClick(driver, locator);
        
        boolean deleteDraft = true;
        
        if (deleteDraft) {
            logger.debug("FYI: about to call deleteDrafProgram()");
            // TODO: delete the 'Draft' application if any?
            CoreUtils.deleteDraftProgram(driver, programName);
            Thread.sleep(5000);
        }
        
        // Get the program name
        String programDesc = CoreUtils.lookupProgram(programName);
        logger.debug("FYI: using : " + programDesc);

        // Need to click "#certificate_type_" + programName here
        locator = By.cssSelector("#certificate_type_" + programName);
        CoreUtils.locateAndClick(driver, locator);

        locator = By.cssSelector("#add_certification");
        CoreUtils.locateAndClick(driver, locator);

        // Then we click Continue button
        CoreUtils.clickContinue(driver);

        clickThroughWosb(driver);
    }
    
    public int offSet(int inputId) {
        int delta = -10;
        return inputId + delta;
    }
    
    public void clickThroughWosb(WebDriver driver) {
        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(198) });
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(199) });
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(201) });
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(211) });
        CoreUtils.comment(driver, offSet(211));

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(212) });
        CoreUtils.comment(driver, offSet(212));
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(213) });
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(214), offSet(215), offSet(216) });
        CoreUtils.comment(driver, offSet(216));
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(217), offSet(218), offSet(219), offSet(220), offSet(221), offSet(222) });
        CoreUtils.comment(driver, offSet(222));
        CoreUtils.clickContinue(driver);

        CoreUtils.yesOrNo(driver, "no", new int[] { offSet(223) });
        CoreUtils.clickContinue(driver);

        CoreWait.waitForUrlContains(wdriver, "/wosb/review_sections/review/edit");
        CoreUtils.clickContinue(driver);

        CoreUtils.accepTermsAndConditions(driver, new int[] { 0, 1, 2, 3, 4, 5 });
        // Note: but don't click continue for now to simplify the test
    }
}
