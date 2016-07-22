package gov.sba.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Us801AmIEligibleTs5 {
	private static final Logger logger = LogManager.getLogger(Us801AmIEligibleTs5.class.getName());
	public WebDriver webDriver;

	@Before
	public void setup() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
	}

	@Test
	public void maintest() throws Exception {
		// Open Firefox,Chrome or IE and navigate to the certify.sba.gov landing
		// page.
		logger.info("User is NOT eligible for Any of the programs (Due to NO for Qs4) 8(a), WOSB, EDWOSB & Hob-zone");
		// Locate the Am I Eligible or the Find Out button on the
		// Certify.SBA.Gov landing page and click on it.
		webDriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();
		Thread.sleep(4000);
		// Locate the first question and select Yes and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource()
				.contains("A Citizen means a person born or naturalized in the United States"));
		logger.info("First question was validated");
		String More_Details_Qs1;
		More_Details_Qs1 = webDriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 1 is" + More_Details_Qs1);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[1]/div/button[1]")).click();
		Thread.sleep(4000);
		// Locate the 2nd question and select YES and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Is the 51% ownership of the firm unconditional and direct"));
		logger.info("Second question was validated");
		String More_Details_Qs2;
		More_Details_Qs2 = webDriver.findElement(By.xpath(".//*[@id='unconditional_direct_51_percent']/div[1]/div[2]"))
				.getText();
		logger.info("The Detail meaning for question 2 is" + More_Details_Qs2);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='unconditional_direct_51_percent']/div[1]/div[1]/div/button[1]"))
				.click();
		Thread.sleep(4000);
		// Locate the Third question and select YES and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Is the firm organized for profit"));
		logger.info("Third question was validated");
		String More_Details_Qs3;
		More_Details_Qs3 = webDriver.findElement(By.xpath(".//*[@id='for_profit']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 3rd is" + More_Details_Qs3);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='for_profit']/div[1]/div[1]/div/button[1]")).click();
		Thread.sleep(4000);
		// Locate the 4th question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Do you affirm that neither this firm"));
		logger.info("4th question was validated");
		String More_Details_Qs4;
		More_Details_Qs4 = webDriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 4th is" + More_Details_Qs4);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[1]/div/button[2]")).click();
		// Verify searched results.
		String actual_Text1 = webDriver.findElement(By.cssSelector("span.message")).getText();
		String expected_Text1 = "In order to participate in SBA small business programs, the owner(s) of the firm must not have been debarred or suspended by a federal entity.";
		assertEquals(actual_Text1, expected_Text1);
		webDriver.findElement(By.linkText("Exit")).click();
	}

	@After
	public void teardown() throws Exception {
		webDriver.quit();
	}
}
