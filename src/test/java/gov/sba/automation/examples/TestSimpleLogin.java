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

import gov.sba.automation.FormHelpers;
import gov.sba.automation.TestHelpers;
import gov.sba.automation.WaitUtils;

public class TestSimpleLogin {

  private static final Logger logger = LogManager.getLogger(TestSimpleLogin.class.getName());

  private static WebDriver webDriver;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();

  }

  @After
  public void tearDown() throws Exception {
    webDriver.quit();
  }

  @Test
  public void testSimpleLogin() throws Exception {

    String baseUrl = TestHelpers.getBaseUrl();

    logger.debug("FYI: your test baseUrl: " + baseUrl);

    // CommonApplicationMethods.clear_Env_Chrome();
    webDriver.get(baseUrl);
    // CommonApplicationMethods.focus_window()

    // WaitUtils waitUtils = new WaitUtils();

    WebElement element;

    // Better - we can pick the next element we like to use after the page
    // is loaded!
    element = WaitUtils.waitForElement(webDriver, By.cssSelector("button.button-full"));

    String expectedText = element.getText();

    System.out.println("FYI: the element's text :" + expectedText);
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
    WebElement signinButton = WaitUtils.waitForElement(webDriver, By.id("business_signin")); // the
                                                                                             // "Sign-in"
                                                                                             // button
    // System.out.println("FYI: the text :" +
    // signinButton.getAttribute("id"));

    assert (signinButton.getAttribute("id").equalsIgnoreCase("business_signin"));

    // Now pick the element we need to interact with e.g. in this page we
    // need to fill in "Email" and "Passphase"
    FormHelpers.fillElement(webDriver, By.name("user[email]"), "john@mailinator.com");
    FormHelpers.fillElement(webDriver, By.name("user[password]"), "password");

    // Now we are ready to click the submit button
    FormHelpers.submitForm(signinButton);
    String url = webDriver.getCurrentUrl();

    org.junit.Assert.assertTrue(url.contains("dashboard"));
  }

}
