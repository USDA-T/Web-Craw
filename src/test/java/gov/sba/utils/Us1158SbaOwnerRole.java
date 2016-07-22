package gov.sba.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Us1158SbaOwnerRole {
	private static final Logger logger = LogManager.getLogger(Us1158SbaOwnerRole.class.getName());
	private static WebDriver webDriver;
	String Duns;
	String B_N;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		Duns = "275276652";
		B_N = "Entity 37 Legal";
	}

	@Test
	public void mainTest() throws Exception {
		// Notes; Role Most be assigned at the database and validated at the
		// front end.
		// Open Firefox,Chrome or IE and navigate the certify.SBA.gov login
		// page.
		Thread.sleep(4000);
		// Locate the email search box and enter a valid email for the User who
		// have been assign the analyst role.
		AnalystloginPage analystlogin = new AnalystloginPage(webDriver);
		analystlogin.Analystlogin();
		Thread.sleep(3000);
		assertFalse(webDriver.getPageSource().contains("Signed in successfully"));
		Thread.sleep(3000);
		// Locate the search for vendor search box and enter a valid Duns
		// number.
		webDriver.findElement(By.cssSelector("div.desk-div-text > #vsearch > #query")).sendKeys(Duns);
		// Locate the search button and click it.
		webDriver.findElement(By.cssSelector("button.search-styli-desktop")).click();
		// Clear the search duns number in the search box and very that that the
		// duns number is thesame on the search result.
		webDriver.findElement(By.cssSelector("div.desk-div-text > #vsearch > #query")).clear();
		Thread.sleep(4000);
		assertTrue(webDriver.getPageSource().contains("275276652"));
		logger.info("The saerch results maches the business for the searched Duns number, Pass.");
		Thread.sleep(4000);
		// Locate the search for vendor search box and enter a valid business
		// name.
		webDriver.findElement(By.cssSelector("div.desk-div-text > #vsearch > #query")).sendKeys(B_N);
		// Click on the search button.
		webDriver.findElement(By.cssSelector("button.search-styli-desktop")).click();
		// Clear the search duns number in the search box and very that that the
		// duns number is thesame on the search result.
		webDriver.findElement(By.cssSelector("div.desk-div-text > #vsearch > #query")).clear();
		Thread.sleep(4000);
		String actual_Text = webDriver.findElement(By.id("view_business_profile")).getText();
		String expected_Text = "Entity 37 Legal Business Name";
		assertValueEquals(actual_Text, expected_Text);
		// business type.
		String actual_Text1 = webDriver.findElement(By.xpath("//form[@id='associate_business']/div[3]/div[3]/span[2]"))
				.getText();
		String expected_Text1 = "s-corp";
		assertEquals(actual_Text1, expected_Text1);
		// Duns.
		String actual_Text2 = webDriver.findElement(By.xpath("//form[@id='associate_business']/div[2]/div[3]/span[2]"))
				.getText();
		String expected_Text2 = "275276652";
		assertEquals(actual_Text2, expected_Text2);
		// address
		String actual_Text3 = webDriver.findElement(By.xpath("//form[@id='associate_business']/div[4]/div/span[2]"))
				.getText();
		String expected_Text3 = "Entity 37 Address Line 1\nWASHINGTON, DC 20004";
		assertEquals(actual_Text3, expected_Text3);
		// Loacte the view profile button and click on it.
		webDriver.findElement(By.xpath(".//*[@id='view_business_profile']")).click();
		Thread.sleep(3000);
		// Locate and verify the ASSOCIATED VENDOR ADMINISTRATOR profile link
		// and click on it.
		String actual_Text7 = webDriver.findElement(By.id("ava-pop")).getText();
		String expected_Text7 = "QA User";
		assertEquals(actual_Text7, expected_Text7);
		webDriver.findElement(By.id("ava-pop")).click();
		Thread.sleep(4000);
		String actual_Text8 = webDriver.findElement(By.id("msg_container")).getText();
		String expected_Text8 = "Last Name: User\nFirst Name: QA\nEmail: akanamontana@gmail.com\nRole: Vendor Administrator\nPhone No:";
		assertEquals(actual_Text8, expected_Text8);
		webDriver.findElement(By.id("labelid")).click();
		// Verify that user has access to the the vendors Documents library.
		assertElementpresent("Document Library",
				webDriver.findElement(By.xpath("//div[@id='business_search']/div[5]/div")));
		String actual_Text9 = webDriver.findElement(By.xpath("//div[@id='business_search']/div[5]/div")).getText();
		String expected_Text9 = "Document Library";
		assertEquals(actual_Text9, expected_Text9);
		// Locate Entity 37 Legal Business Name and click on it to view all the
		// company information.
		webDriver.findElement(By.xpath(".//*[@id='business_search']/div[3]/div/a")).click();
		// Verify clone, return to vendor.
		String actual_Text4 = webDriver.findElement(By.linkText("Return to Vendor")).getText();
		String expected_Text4 = "Return to Vendor";
		assertEquals(actual_Text4, expected_Text4);
		// case review.
		String actual_Text5 = webDriver.findElement(By.linkText("Case review")).getText();
		String expected_Text5 = "Case review";
		assertEquals(actual_Text5, expected_Text5);
		webDriver.findElement(By.linkText("Case review")).click();
		Thread.sleep(4000);
		// Verify that the case review link is active.
		String actual_Text6 = webDriver.findElement(By.xpath("//article[@id='main-content']/div/div[2]/h1")).getText();
		String expected_Text6 = "Case Overview";
		assertEquals(actual_Text6, expected_Text6);
		// logout.
		webDriver.findElement(By.linkText("Logout")).click();
	}

	private void assertElementpresent(String string, WebElement findElement) {
		// TODO Auto-generated method stub
	}

	private void assertValueEquals(String actual_Text, String expected_Text) {
		// TODO Auto-generated method stub
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}

}