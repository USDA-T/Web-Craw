package gov.sba.utils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
public class Us1225ImplementUSWDSForAnalystViews {
	private static final Logger logger = LogManager.getLogger(Us1225ImplementUSWDSForAnalystViews.class.getName());
	private static WebDriver webDriver;
	String DUNS;
	String Invalid_DUNS;
	int get_The_Row_From_Login_Data;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		DUNS = "275276652";
		Invalid_DUNS = "123456789";
		get_The_Row_From_Login_Data = 0;
	}
	@Test
	public void mainTest() throws Exception {
		// Step 1 - Open QA site
		Thread.sleep(3000);
		// Step 2 - Log in as an Analyst
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		// Step 3 - Verify that the certify.SBA.gov Logo is displayed
		// Check the title of the page
		logger.info("Page title is: " + webDriver.getTitle());
		logger.info("\nStep 3 - Verify that the certify.SBA.gov Logo is displayed");
		logger.info("certify.SBA.gov Logo is Present");
		// Step 4 - Tried Without DUNS number find the business click Search
		// button.
		logger.info("\nStep 4 - Tried Without  DUNS number find the business click Search button");
		webDriver.findElement(By.xpath("//button")).click();
		webDriver.switchTo().alert().accept();
		logger.info("Verify that the  Search Results 'No Match Found' ");
		assertEquals("No Match Found",
				webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
		logger.info("Message is displayed 'No Match Found' - Pass");
		Thread.sleep(3000);
		// Step 5 - Tried to find the businesses matched By Invalid DUNS number
		logger.info("\nStep 5 - Tried to find the businesses matched By Invalid DUNS number");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query"))
				.sendKeys(Invalid_DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		logger.info("Verify that the  Search Results 'No Match Found' ");
		assertEquals("No Match Found",
				webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
		logger.info("Message is displayed No Match Found - Pass");
		// Step 6 - Enter the Valid DUNS number to search on
		logger.info("\nStep 6 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		// Step 7 - Verify that the Search Results 'Entity 211 Legal Business
		// Name' is Display
		logger.info("\nStep 7 - Verify that the  Search Results 'Entity 211 Legal Business Name' is Display");
		assertTrue(webDriver.getPageSource().contains("Entity 37 Legal Business Name"));
		logger.info("Displayed for Entity 211 Legal Business Name is Display - Pass");
		// Step 8 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  8 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 9 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 9 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		// Step 10 - Click the EDWOSB Self-Certification section 'View' Link
		logger.info("\nStep 10 - Click the EDWOSB Self-Certification section View Link");
		webDriver.findElement(By.linkText("View summary")).click();
		// Step 11 - Verify that the Economically Disadvantaged Women-Owned
		// Small Business Program Certification is displayed
		logger.info("\nStep 11 - Verify that the Women-Owned Small Business Program Certification is displayed");
		Thread.sleep(4000);
		webDriver.getPageSource().contains("Women-Owned Small Business Program Certification");
		logger.info(webDriver.findElement(By.cssSelector("h3")).getText());
		// Step 12 - Enter the Valid DUNS number to search on
		logger.info("\nStep 12 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		// Step 13 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  13 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 14 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 14 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 15 - Click the EDWOSB Self-Certification section 'Case review'
		// Link
		logger.info("\nStep 15 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		// Step16 - Enter the Valid DUNS number to search on
		logger.info("\nStep 16 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		// Step 17 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  17 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 18 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 18 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 19 - Click the EDWOSB Self-Certification section 'Case review'
		// Link
		logger.info("\nStep 19 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Signature review")).click();
		Thread.sleep(3000);
		// Step 20 - Enter the Valid DUNS number to search on
		logger.info("\nStep 20 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		// Step 21 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  21 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 22 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 22 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 23 - Click the WOSB Self-Certification section 'View' Link
		logger.info("\nStep 23 - Click the WOSB Self-Certification section View Link");
		webDriver.findElement(By.linkText("View summary")).click();
		// Step 24 - Verify that the Women-Owned Small Business Program
		// Certification is displayed
		logger.info("\nStep 24 - Verify that the Women-Owned Small Business  Program Certification is displayed");
		assertTrue(webDriver.getPageSource().contains("Women-Owned Small Business Program"));
		logger.info(webDriver.findElement(By.cssSelector("h3")).getText());
		// Step 25 - Enter the Valid DUNS number to search on
		logger.info("\nStep 25 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		// Step 26 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  26 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 27 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 27 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 28 - Click the WOSB Self-Certification section 'Case review'
		// Link
		logger.info("\nStep 28 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		// Step 29 - Enter the Valid DUNS number to search on
		logger.info("\nStep 29 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		// Step 30 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  30 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 31 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 31 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 32 - Click the WOSB Self-Certification section 'Case review'
		// Link
		logger.info("\nStep 32 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Signature review")).click();
		Thread.sleep(3000);
		// Step 33 - Enter the Valid DUNS number to search on
		logger.info("\nStep 33 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button")).click();
		Thread.sleep(3000);
		Dimension d = new Dimension(800, 480);
		// Step 34 - Resize current window to the set dimension
		logger.info("\nStep 34 - Resize current window to the set dimension");
		webDriver.manage().window().setSize(d);
		// Click the Navigation menu button for width
		logger.info("Click the Navigation menu button for width ");
		webDriver.findElement(By.linkText("MENU")).click();
		Thread.sleep(4000);
		// Step 35 - Click the 'Dashboard' Navigation link
		logger.info("\nStep 35 - Click the 'Dashboard' Navigation link");
		webDriver.findElement(By.linkText("Dashboard")).click();
		logger.info("Verify that the  Search Results 'No Match Found' ");
		assertEquals("No Match Found",
				webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
		logger.info("Message is displayed 'No Match Found' - Pass");
		Thread.sleep(3000);
		// Step 36 - Click the Navigation menu button for close
		logger.info("Click the Navigation menu button for width ");
		webDriver.findElement(By.linkText("MENU")).click();
		Thread.sleep(3000);
		logger.info("Click the Navigation menu button for close");
		webDriver.findElement(By.linkText("MENU")).click();
		Thread.sleep(3000);
		// Step 37 - Click the fa-search Icon
		logger.info("\nStep 37 - Click the fa-search Icon");
		webDriver.navigate().refresh();
		Thread.sleep(3000);
		// Step 38 - Enter the Valid DUNS number to search on
		logger.info("\nStep 38 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Step 39 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  39 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 40 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 40 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 41 - Click the EDWOSB Self-Certification section 'View' Link
		logger.info("\nStep 41 - Click the EDWOSB Self-Certification section View Link");
		webDriver.findElement(By.linkText("View summary")).click();
		// Step 42 - Verify that the Economically Disadvantaged Women-Owned
		// Small Business Program Certification is displayed
		logger.info("\nStep 42 - Verify that the Women-Owned Small Business Program Certification is displayed");
		webDriver.getPageSource().contains("Women-Owned Small Business Program Certification");
		logger.info(webDriver.findElement(By.cssSelector("h3")).getText());
		// Click the fa-search Icon
		logger.info("Click the fa-search Icon");
		webDriver.navigate().refresh();
		Thread.sleep(3000);
		// Step 43 - Enter the Valid DUNS number to search on
		logger.info("\nStep 43 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Step 44 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  44 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 45 - Verify that the Entity 37 Legal Business Name page is
		// Display
		logger.info("\nStep 45 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 46 - Click the EDWOSB Self-Certification section 'Case review'
		// Link
		logger.info("\nStep 46 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		// Click the fa-search Icon
		logger.info("Click the fa-search Icon");
		webDriver.navigate().refresh();
		Thread.sleep(3000);
		// Step 47 - Enter the Valid DUNS number to search on
		logger.info("\nStep 47 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Step 48 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  48 - Click Entity 37 Legal Business Name Link");
		webDriver.findElement(By.linkText("Entity 37 Legal Business Name")).click();
		logger.info("Analyst can move to the next section Entity 37 Legal Business Name page");
		// Step 49 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 49 - Verify that the Entity 37 Legal Business Name page is Display");
		assertEquals("Entity 37 Legal Business Name", webDriver.findElement(By.cssSelector("h4")).getText());
		logger.info("Entity 37 Legal Business Name is displayed - Pass");
		// Step 50 - Click the EDWOSB Self-Certification section 'Signature
		// review' Link
		logger.info("\nStep 50 - Click the EDWOSB Self-Certification section 'Signature review' Link");
		webDriver.findElement(By.linkText("WOSB Self-Certification")).click();
		Thread.sleep(3000);
		// Click the Signature review Link
		logger.info("Click the Signature review Link");
		webDriver.findElement(By.linkText("Signature review")).click();
		Thread.sleep(3000);
		// Click the fa-search Icon
		logger.info("Click the fa-search Icon");
		webDriver.navigate().refresh();
		Thread.sleep(3000);
		// Step 51 - Enter the Valid DUNS number to search on
		logger.info("\nStep 51 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Maximize current window size
		logger.info("Maximize current window size");
		webDriver.manage().window().maximize();
		// Step 52 - Click the Dashboard link
		logger.info("\nStep 52 - Returned to the Dashboard");
		webDriver.findElement(By.linkText("Dashboard")).click();
		Thread.sleep(2000);
		// Step 53 - Click the Logout link
		logger.info("\nStep 53 - Logout link clicked");
		webDriver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		logger.info("END OF TEST");
	}
	@After
	public void tearDown() {
		String verificationErrorString = verificationErrors.toString();
		assertTrue(verificationErrorString != null);
		{
			logger.info("Success: " + verificationErrorString);
		}
		webDriver.quit();
	}
}
