package gov.sba.utils;
import static org.junit.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class Us1125Us1127Us1176Co {
	private static final Logger logger = LogManager.getLogger(Us1125Us1127Us1176Co.class.getName());
	public WebDriver webDriver;
	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
	}
	@Test
	public void mainTest() throws Exception {
		String actual_Text = null;
		String expected_Text = null;
		logger.info("CO request access and view vendor's record");
		// Login to Vendor CO Dashboard.
		Thread.sleep(4000);
		CologinPage cologin = new CologinPage(webDriver);
		cologin.Cologin();
		Thread.sleep(4000);
		// Verify and click on the Request access link.
		actual_Text = webDriver.findElement(By.linkText("Request access")).getText();
		expected_Text = "Request access";
		assertEquals(actual_Text, expected_Text);
		logger.info("Request access link present");
		webDriver.findElement(By.linkText("Request access")).click();
		// Verify the request access to a vendor screen page.
		actual_Text = webDriver.findElement(By.cssSelector("h1.usa-heading")).getText();
		expected_Text = "Request access to view records";
		assertEquals(actual_Text, expected_Text);
		//Verify text
		actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
		expected_Text = "Use this form to request access to a small business's WOSB or EDWOSB eligibility documentation. Once complete, the form will send an email to the small business that asks them to log in to certify.sba.gov and approve or reject your request for access. You must provide the following information to request access:";
		assertEquals(actual_Text, expected_Text);
		actual_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/section/article/p[2]")).getText();
		expected_Text = "Start by searching for a small business by DUNS number to verify eligibility within SBA's certify.sba.gov system.";
		assertEquals(actual_Text, expected_Text);
		// Verify that a search box and search button to search for vendor
		// business is present..
		actual_Text = webDriver.findElement(By.cssSelector("label")).getText();
		expected_Text = "DUNS number";
		assertEquals(actual_Text, expected_Text);
		// Locate the DUNs search box and enter a valid DUNs number and click
		// the search button.
		webDriver.findElement(By.id("duns_number")).sendKeys("137151292");
		webDriver.findElement(By.id("find_business")).click();
		// Verify the search results.
		Thread.sleep(4000);
		actual_Text = webDriver.findElement(By.cssSelector("#vendor_duns_number")).getText();
		expected_Text = "137151292";
		assertEquals(actual_Text, expected_Text);
		Thread.sleep(3000);
		actual_Text = webDriver.findElement(By.cssSelector("div.contracting_officer_complete_request > p")).getText();
		expected_Text = "Please enter the Solicitation number, Solicitation NAICS, and procurement type below.";
		assertEquals(actual_Text, expected_Text);
		// Enter a solicitation #.
		webDriver.findElement(By.id("access_request_solicitation_number")).sendKeys("343265");
		// Enter a solicitation NAICS.
		webDriver.findElement(By.id("access_request_solicitation_naics")).sendKeys("343265");
		// Locate the type of procurement and select as apply to the NAICS.
		webDriver.findElement(By.xpath("//form[@id='request_access']/div[2]/fieldset/ul/li[2]/label")).click();
		// Verify the terms of request.
		actual_Text = webDriver.findElement(By.xpath("//form[@id='request_access']/div[2]/p[3]")).getText();
		expected_Text = "I CO3 MAX_LAST_NAME certify that I am a Federal Contracting Officer or Specialist and am authorized by NSA to validate Women-Owned Small Business (WOSB) eligibility in order to award contracts to women-owned small businesses. I understand that the data that I am requesting contains Personal Identifiable Information (PII) and must be protected in accordance withOMB Memorandum M-07-16 Safeguarding Against and Responding to the Breach of Personally Identifiable Information.";
		assertEquals(actual_Text, expected_Text);
		// Verify that the mMemorandun document link is present and navigates to
		// the document.
		webDriver.get("https://certify.qa.sba-one.net/contracting_officer/access_requests/new");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String main_window = webDriver.getWindowHandle();
		logger.info("Before switching, title is =" + webDriver.getTitle());
		webDriver.findElement(By.id("duns_number")).sendKeys("137151292");
		webDriver.findElement(By.id("find_business")).click();
		webDriver
				.findElement(By.linkText(
						"OMB Memorandum M-07-16 Safeguarding Against and Responding to the Breach of Personally Identifiable Information"))
				.click();
		java.util.Set<String> S1 = webDriver.getWindowHandles();
		java.util.Iterator<String> i1 = S1.iterator();
		while (i1.hasNext()) {
			String Second_window = i1.next();
			if(!main_window.equalsIgnoreCase(Second_window)) {
				webDriver.switchTo().window(Second_window);
				Thread.sleep(1000);
				logger.info("After switching title is =" + webDriver.getTitle());
				webDriver.close();
			}
		}
		webDriver.switchTo().window(main_window);
		logger.info("Back to manin_window " + webDriver.getTitle());
		// Click on the i accept box.
		webDriver.findElement(By.cssSelector("div.usa-width-one-half > label")).click();
		// Verify and Click on the My Request link.
		actual_Text = webDriver.findElement(By.linkText("My requests")).getText();
		expected_Text = "My requests";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("My requests")).click();
		// Verify the my request page.
		actual_Text = webDriver.findElement(By.cssSelector("h2.usa-heading")).getText();
		expected_Text = "Review vendor certifications";
		assertEquals(actual_Text, expected_Text);
	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
