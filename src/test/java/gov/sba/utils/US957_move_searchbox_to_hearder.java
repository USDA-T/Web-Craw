package gov.sba.utils;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class US957_move_searchbox_to_hearder {
	private static final Logger logger = LogManager.getLogger(US957_move_searchbox_to_hearder.class.getName());

	private static WebDriver webDriver;
	String URL;
	String Email;
	String Password;
	String DUNS;
	String Invalid_DUNS;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void SBA_Analyst_Workflow_Assign_Review_to_Analyst_Enhanced_Setup() {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();

		webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		DUNS = "275276652";
		Invalid_DUNS = "123456789";

	}

	@Test
	public void SBA_Analyst_Workflow_Assign_Review_to_Analyst_Enhanced_MainTest() throws Exception {
		// Step 1 - Open QA site

		Thread.sleep(3000);

		// Step 2 - Log in as an Analyst
		AnalystloginPage analystlogin = new AnalystloginPage(webDriver);
		analystlogin.Analystlogin();
		System.out.println("Click on Login Link and log in");
		// Step 3 - Verify that the certify.SBA.gov Logo is displayed
		// Check the title of the page
		System.out.println("Page title is: " + webDriver.getTitle());
		System.out.println("\nStep 3 - Verify that the certify.SBA.gov Logo is displayed");

		if (webDriver.findElements(By.xpath("html/body/header/div[1]/h2/img")).size() != 0) {
			logger.info("certify.SBA.gov Logo is Present");
		} else {
			logger.info("certify.SBA.gov Logo is Absent");
		}

		// Step 4 - Tried Without DUNS number find the business click Search
		// button.
		System.out.println("\nStep 4 - Tried Without  DUNS number find the business click Search button");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).click();
		logger.info("Verify that the  Search Results 'No Match Found' ");
		try {
			assertEquals("No Match Found",
					webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
			logger.info("Message is displayed 'No Match Found' - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			logger.info("Message is displayed 'No Match Found' - FAILED");
		}
		Thread.sleep(3000);

		// Step 5 - Tried to find the businesses matched By Invalid DUNS number
		System.out.println("\nStep 5 - Tried to find the businesses matched By Invalid DUNS number");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query"))
				.sendKeys(Invalid_DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);
		logger.info("Verify that the  Search Results 'No Match Found' ");
		try {
			assertEquals("No Match Found",
					webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
			System.out.println("Message is displayed No Match Found - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			logger.info("Message is displayed No Match Found - - FAILED");
		}

		// Step 6 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 6 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);

		// Step 7 - Verify that the Search Results 'Entity 211 Legal Business
		// Name' is Display
		logger.info("\nStep 7 - Verify that the  Search Results 'Entity 211 Legal Business Name' is Display");
		if (webDriver.getPageSource().contains("Entity 211 Legal Business Name")) {
			System.out.println("Displayed for Entity 211 Legal Business Name is Display - Pass");
		} else {
			logger.info("Displayed for Entity 211 Legal Business Name is Display - FAILED");
		}
		// Step 8 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  8 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");

		// Step 9 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 9 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			logger.info("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			logger.info("Entity 211 Legal Business Name is displayed - FAILED");
		}

		// Step 10 - Click the EDWOSB Self-Certification section 'View' Link
		System.out.println("\nStep 10 - Click the EDWOSB Self-Certification section View Link");
		webDriver.findElement(By.linkText("View")).click();
		// Step 11 - Verify that the Economically Disadvantaged Women-Owned
		// Small Business Program Certification is displayed
		System.out.println(
				"\nStep 11 - Verify that the Economically Disadvantaged Women-Owned Small Business  Program Certification is displayed");
		if (webDriver.getPageSource()
				.contains("Economically Disadvantaged Women-Owned Small Business  Program Certification")) {
			logger.info(webDriver.findElement(By.cssSelector(".wosb-detail-page>table>tbody>tr>td>h2")).getText());
		} else {
			logger.info(
					"Verify that the Economically Disadvantaged Women-Owned Small Business  Program Certification is displayed, Failed");

		}

		// Step 12 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 12 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);
		// Step 13 - Click on Entity 211 Legal Business Name link
		logger.info("\nStep  13 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 14 - Verify that the Entity 211 Legal Business Name page is
		// Display
		logger.info("\nStep 14 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			logger.info("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			logger.info("Entity 211 Legal Business Name is displayed - FAILED");
		}

		// Step 15 - Click the EDWOSB Self-Certification section 'Case review'
		// Link
		logger.info("\nStep 15 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("Case review")).click();
		// Step16 - Enter the Valid DUNS number to search on
		logger.info("\nStep 16 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);
		// Step 17 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  17 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 18 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 18 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 37 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 37 Legal Business Name is displayed - FAILED");
		}

		// Step 19 - Click the EDWOSB Self-Certification section 'Case review'
		// Link
		System.out.println("\nStep 19 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("Case review")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Signature review")).click();
		Thread.sleep(3000);

		// Step 20 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 20 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);

		// Step 21 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  21 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 22 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 22 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 211 Legal Business Name is displayed - FAILED");
		}
		// Step 23 - Click the WOSB Self-Certification section 'View' Link
		System.out.println("\nStep 23 - Click the WOSB Self-Certification section View Link");
		webDriver.findElement(By.xpath("(//a[contains(text(),'View')])[2]")).click();
		// Step 24 - Verify that the Women-Owned Small Business Program
		// Certification is displayed
		System.out
				.println("\nStep 24 - Verify that the Women-Owned Small Business  Program Certification is displayed");
		if (webDriver.getPageSource().contains("Women-Owned Small Business  Program Certification")) {
			System.out
					.println(webDriver.findElement(By.cssSelector(".wosb-detail-page>table>tbody>tr>td>h2")).getText());
		} else {
			System.out.println("Women-Owned Small Business  Program Certification, Failed");

		}

		// Step 25 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 25 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);
		// Step 26 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  26 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 27 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 27 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 211 Legal Business Name is displayed - FAILED");
		}

		// Step 28 - Click the WOSB Self-Certification section 'Case review'
		// Link
		System.out.println("\nStep 28 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.xpath("(//a[contains(text(),'Case review')])[2]")).click();
		// Step 29 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 29 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);
		// Step 30 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  30 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 31 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 31 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 211 Legal Business Name is displayed - FAILED");
		}

		// Step 32 - Click the WOSB Self-Certification section 'Case review'
		// Link
		System.out.println("\nStep 32 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("Case review")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Signature review")).click();
		Thread.sleep(3000);
		// Step 33 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 33 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).clear();
		webDriver.findElement(By.cssSelector("#searchid > #analyst-search > div > #vsearch > #query")).sendKeys(DUNS);
		webDriver.findElement(By.cssSelector(".search-styli-desktop")).click();
		Thread.sleep(3000);

		Dimension d = new Dimension(800, 480);
		// Step 34 - Resize current window to the set dimension
		System.out.println("\nStep 34 - Resize current window to the set dimension");
		webDriver.manage().window().setSize(d);
		// Click the Navigation menu button for width
		System.out.println("Click the Navigation menu button for width ");
		webDriver.findElement(By.xpath("//a[@id='js-mobile-menu']/i")).click();
		Thread.sleep(4000);
		// Step 35 - Click the 'Dashboard' Navigation link
		System.out.println("\nStep 35 - Click the 'Dashboard' Navigation link");
		webDriver.findElement(By.linkText("Dashboard")).click();
		System.out.println("Verify that the  Search Results 'No Match Found' ");
		try {
			assertEquals("No Match Found",
					webDriver.findElement(By.cssSelector(".usa-width-one-whole.results>h3")).getText());
			System.out.println("Message is displayed 'No Match Found' - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Message is displayed 'No Match Found' - FAILED");
		}
		Thread.sleep(3000);

		// Step 36 - Click the Navigation menu button for close
		System.out.println("Click the Navigation menu button for width ");
		webDriver.findElement(By.xpath("//a[@id='js-mobile-menu']/i")).click();
		Thread.sleep(3000);
		System.out.println("Click the Navigation menu button for close");
		webDriver.findElement(By.xpath("//a[@id='js-mobile-menu']/i")).click();
		Thread.sleep(3000);
		// Step 37 - Click the fa-search Icon
		System.out.println("\nStep 37 - Click the fa-search Icon");
		webDriver.findElement(By.id("fasearch")).click();
		Thread.sleep(3000);

		// Step 38 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 38 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Step 39 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  39 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 40 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 40 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 211 Legal Business Name is displayed - FAILED");
		}
		// Step 41 - Click the EDWOSB Self-Certification section 'View' Link
		System.out.println("\nStep 41 - Click the EDWOSB Self-Certification section View Link");
		webDriver.findElement(By.linkText("View")).click();
		// Step 42 - Verify that the Economically Disadvantaged Women-Owned
		// Small Business Program Certification is displayed
		System.out.println(
				"\nStep 42 - Verify that the Economically Disadvantaged Women-Owned Small Business  Program Certification is displayed");
		if (webDriver.getPageSource()
				.contains("Economically Disadvantaged Women-Owned Small Business  Program Certification")) {
			System.out
					.println(webDriver.findElement(By.cssSelector(".wosb-detail-page>table>tbody>tr>td>h2")).getText());
		} else {
			System.out.println(
					"Verify that the Economically Disadvantaged Women-Owned Small Business  Program Certification is displayed, Failed");

		}
		// Click the fa-search Icon
		System.out.println("Click the fa-search Icon");
		webDriver.findElement(By.id("fasearch")).click();
		Thread.sleep(3000);

		// Step 43 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 43 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Step 44 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  44 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 45 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 45 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 211 Legal Business Name is displayed - FAILED");
		}

		// Step 46 - Click the EDWOSB Self-Certification section 'Case review'
		// Link
		System.out.println("\nStep 46 - Click the EDWOSB Self-Certification section 'Case review' Link");
		webDriver.findElement(By.linkText("Case review")).click();
		// Click the fa-search Icon
		System.out.println("Click the fa-search Icon");
		webDriver.findElement(By.id("fasearch")).click();
		Thread.sleep(3000);
		// Step 47 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 47 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);

		// Step 48 - Click on Entity 211 Legal Business Name link
		System.out.println("\nStep  48 - Click Entity 211 Legal Business Name Link");
		webDriver.findElement(By.cssSelector("#view_business_profile")).click();
		System.out.println("Analyst can move to the next section Entity 211 Legal Business Name page");
		// Step 49 - Verify that the Entity 211 Legal Business Name page is
		// Display
		System.out.println("\nStep 49 - Verify that the Entity 211 Legal Business Name page is Display");
		try {
			assertEquals("Entity 211 Legal Business Name",
					webDriver.findElement(By.cssSelector(".usa-heading")).getText());
			System.out.println("Entity 211 Legal Business Name is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("Entity 211 Legal Business Name is displayed - FAILED");
		}

		// Step 50 - Click the EDWOSB Self-Certification section 'Signature
		// review' Link
		System.out.println("\nStep 50 - Click the EDWOSB Self-Certification section 'Signature review' Link");
		webDriver.findElement(By.linkText("Case review")).click();
		Thread.sleep(3000);
		// Click the Signature review Link
		System.out.println("Click the Signature review Link");
		webDriver.findElement(By.linkText("Signature review")).click();
		Thread.sleep(3000);
		// Click the fa-search Icon
		System.out.println("Click the fa-search Icon");
		webDriver.findElement(By.id("fasearch")).click();
		Thread.sleep(3000);
		// Step 51 - Enter the Valid DUNS number to search on
		System.out.println("\nStep 51 - Enter the Valid DUNS number to search on");
		webDriver.findElement(By.id("query")).clear();
		webDriver.findElement(By.id("query")).sendKeys(DUNS);
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		// Maximize current window size
		System.out.println("Maximize current window size");
		webDriver.manage().window().maximize();

		// Step 52 - Click the Dashboard link
		System.out.println("\nStep 52 - Returned to the Dashboard");
		webDriver.findElement(By.linkText("Dashboard")).click();
		Thread.sleep(2000);

		// Step 53 - Click the Logout link
		System.out.println("\nStep 53 - Logout link clicked");
		webDriver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		System.out.println("END OF TEST");

	}

	@After
	public void SBA_Analyst_Workflow_Assign_Review_to_Analyst_Enhanced_EndTest() {
		String verificationErrorString = verificationErrors.toString();
		if (verificationErrorString != null) {
			System.out.println("Success: " + verificationErrorString);
		}
		webDriver.quit();

	}

}
