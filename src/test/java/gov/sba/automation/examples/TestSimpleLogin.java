package gov.sba.automation.examples;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.CoreUtils;
import gov.sba.automation.CoreWait;
import gov.sba.automation.FormHelpers;
import gov.sba.automation.TestHelpers;
import gov.sba.automation.WaitUtils;

public class TestSimpleLogin {

  private static final Logger logger = LogManager.getLogger(TestSimpleLogin.class.getName());

  private static WebDriver webDriver;
  // wait for at most 10 seconds before timeout
  private static WebDriverWait webDriverWait;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriverWait = new WebDriverWait(webDriver, 10);
  }

  @After
  public void tearDown() throws Exception {
    // webDriver.quit();
  }

  @Test
  public void testSimpleLogin() throws Exception {

    String baseUrl = TestHelpers.getBaseUrl();

    logger.debug("FYI: your test baseUrl: " + baseUrl);

    webDriver.get(baseUrl);

    WebElement element;

    // Better - we can pick the next element we like to use after the page
    // is loaded!
    element = WaitUtils.waitForElement(webDriver, By.cssSelector("button.button-full"));

    String expectedText = element.getText();
    assertTrue(expectedText.contains("Login"));

    // Now we ready to proceed the login button
    element.click();

    // Now we should be able to see the login page e.g. "/user/sign_in" in
    // the url
    // at this point we have two items that we can use e.g. login/password
    // or the sign-in button

    // Q: What happen if we have the wrong id or the code is updated by the
    // Rails's team?
    // A: It should not wait longer than 10 seconds and should report error
    // in the test!
    WebElement signinButton = WaitUtils.waitForElement(webDriver, By.id("business_signin"));

    assert (signinButton.getAttribute("id").equalsIgnoreCase("business_signin"));

    // Now pick the element we need to interact with e.g. in this page we
    // need to fill in "Email" and "Passphase"
    FormHelpers.fillElement(webDriver, By.name("user[email]"), "john@mailinator.com");
    FormHelpers.fillElement(webDriver, By.name("user[password]"), "password");

    // Now we are ready to click the submit button
    FormHelpers.submitForm(signinButton);
    String url = webDriver.getCurrentUrl();

    org.junit.Assert.assertTrue(url.contains("dashboard"));

    deleteDraftApplicationIfAny(webDriver, webDriverWait, "wosb");
  }

  public void deleteDraftApplicationIfAny(WebDriver driver, WebDriverWait wdriver,
      String programName) throws Exception {
    // xpath: "//*[@id=\"header_nav\"]/header/nav/div/ul/li[2]/a"
    // css: "#header_nav > header > nav > div > ul > li:nth-child(2) > a > span"
    By locator = By.partialLinkText("Programs");

    CoreWait.waitForElementToBeClickable(driver, wdriver, locator);

    String actual = driver.findElement(locator).getText();

    CoreUtils.assertContentEquals("Programs", actual);

    // Now let's click the program link
    CoreUtils.locateAndClick(driver, locator);

    boolean deleteDraft = true;

    if (deleteDraft) {
      // CoreUtils.deleteDraftProgram(driver, "wosb");
      TestSimpleLogin.deleteDraftProgram(driver, programName);
      Thread.sleep(5000);
    } else {
      logger.info("FYI: skip a delete draft ..");
    }
  }

  public static void deleteDraftProgram(WebDriver driver, String programName) {
    logger.info("FYI: looking up using programName : " + programName);

    String programDesc = CoreUtils.lookupProgram(programName);
    logger.info("FYI: looking up using programDesc : " + programDesc);

    try {
      // xpath expression for 'Draft expression.."
      String xpathExpr = String.format(
          "//*[@id='certifications']/tbody/tr[(td[position()=1]/a[contains(text(),'%s')]) and (td[position()=5 and contains(text(),'Draft')])]/td[position()=7]/a[ contains(text(),'Delete')]",
          programDesc);

      By locator = By.xpath(xpathExpr);

      WebElement element = driver.findElement(locator);

      if (element != null) {
        logger.info("FYI: element found, and will be click: " + element);
        // TODO: just don't click this for now!
        element.click();

        // Note: this will click but we don't want to accept the alert just yet!
        new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
      } else {
        logger.info(String.format(
            "FYI: no draft application for '%s' found, no action will be taken.", programName));
      }
    } catch (Exception e) {
      logger.debug("FYI: exceptions .." + e.getMessage());
      // NOTE: this is to be expected if we don't have any 'Draft'
      // certification on the first run
    }
  }
}
