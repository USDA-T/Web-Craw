package gov.sba.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;
//TODO: this name is too long, not follow Java naming convention, consider change this please!
public class TestUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS2
		extends TestCase {
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
	public void testUS801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS2_MainTest()
			throws Exception {
		System.out.println(
				"User is NOT eligible(due to NO for Qs1) for Any of the programs 8(a), WOSB, EDWOSB & Hob-zone");

		// Locate the Am I Eligible or the Find Out button on the
		// Certify.SBA.Gov landing page and click on it.
		webDriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();

		// Verify that user navigates to the am i eligible page.
		String actual_Text = webDriver.findElement(By.xpath(".//*[@id='am-i']/h1")).getText();
		String expected_Text = "Is there an SBA Small Business Program for me?";
		assertEquals(actual_Text, expected_Text);

		// Locate the first question and select NO and verify the More Detail
		// meaning of the question.
		String actual_Text2 = webDriver.findElement(By.cssSelector("div.usa-width-one-half > p.lead-para")).getText();
		String expected_Text2 = "Are the qualifying individual(s) of the firm who are applying for SBA small business programs U.S. citizens?";
		assertEquals(actual_Text2, expected_Text2);

		// Validate the meaning for question one.
		String actual_Text3 = webDriver.findElement(By.cssSelector("div.details.usa-width-one-half > p")).getText();
		String expected_Text3 = "A Citizen means a person born or naturalized in the United States. Resident ailens and holders of permanent visas are not considered to be citizens. 13 C.F.R. 127.102";
		assertEquals(actual_Text3, expected_Text3);

		// Verify and click on the No button.
		webDriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[1]/div/button[2]")).click();
		// Verify searched results.
		String actual_Text1 = webDriver.findElement(By.cssSelector("span.message")).getText();
		String expected_Text1 = "In order to participate in SBA small business programs, the qualifying individual(s) of the firm must be U.S. citizens.";
		assertEquals(actual_Text1, expected_Text1);

		webDriver.findElement(By.linkText("Exit")).click();
	}
}
