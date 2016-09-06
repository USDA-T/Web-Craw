package gov.sba.utils;

import static org.junit.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class US801AmIEligibleTs2 {
	private static final Logger logger = LogManager.getLogger(US801AmIEligibleTs2.class.getName());
	public WebDriver webDriver;

	@Before
	public void setup() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
	}

	@Test
	public void mainTest() throws Exception {
		logger.info("User is NOT eligible(due to NO for Qs1) for Any of the programs 8(a), WOSB, EDWOSB & Hob-zone");
		// Locate the Am I Eligible or the Find Out button on the
		// Certify.SBA.Gov landing page and click on it.
		webDriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();
		// Verify that user navigates to the am i eligible page.
		String actual_Text = webDriver.findElement(By.xpath(".//*[@id='am-i']/h1")).getText();
		String expected_Text = "Is there an SBA Contracting Program for me?";
		assertEquals(expected_Text, actual_Text);
		// Locate the first question and select NO and verify the More Detail
		// meaning of the question.
		String actual_Text2 = webDriver.findElement(By.cssSelector("div.usa-width-one-half > p.lead-para")).getText();
		String expected_Text2 = "Are the qualifying individual(s) of the firm who are applying for SBA small business programs U.S. citizens?";
		assertEquals(actual_Text2, expected_Text2);
		// Validate the meaning for question one.
		String actual_Text3 = webDriver.findElement(By.cssSelector("div.details.usa-width-one-half > p")).getText();
		String expected_Text3 = "A U.S.citizenmeans a person born or naturalized in the United States. Resident aliens and holders of permanent visas are not considered to be citizens for program purposes.";
		assertEquals(actual_Text3, expected_Text3);
		// verify and click on the No button.
		webDriver.findElement(By.id("no_button_us_citizen")).click();
		// Verify searched results.
		String actual_Text1 = webDriver.findElement(By.cssSelector("span.message")).getText();
		String expected_Text1 = "In order to participate in SBA small business programs, the qualifying individual(s) of the firm must be U.S. citizens.";
		assertEquals(actual_Text1, expected_Text1);
		webDriver.findElement(By.linkText("Exit")).click();
	}

	@After
	public void teardown() throws Exception {
		webDriver.quit();
	}
}
