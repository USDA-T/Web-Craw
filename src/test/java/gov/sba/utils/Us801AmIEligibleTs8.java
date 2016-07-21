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
public class Us801AmIEligibleTs8 {
	private static final Logger logger = LogManager.getLogger(Us801AmIEligibleTs8.class.getName());
	public WebDriver webDriver;
	String naics;
	@Before
	public void setUp()throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
     	naics = "335932";
	}
	@Test
	public void mainTest()throws Exception {
		// Open Firefox,Chrome or IE and navigate to the certify.sba.gov landing
		// page.
		logger.info("User is NOT eligible for Any of the programs (Due to NO for Qs6) 8(a), WOSB, EDWOSB & Hob-zone");
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
		// Locate the 4th question and select YES and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Do you affirm that neither this firm"));
		logger.info("4th question was validated");
		String More_Details_Qs4;
		More_Details_Qs4 = webDriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 4th is" + More_Details_Qs4);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[1]/div/button[1]")).click();
		Thread.sleep(4000);
		// Locate the 5th question and select YES and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Does the firm have a place of business in the U.S."));
		logger.info("5th question was validated");
		String More_Details_Qs5;
		More_Details_Qs5 = webDriver.findElement(By.xpath(".//*[@id='us_business']/div[1]/div[2]/p[1]")).getText();
		logger.info("The Detail meaning for question 5th is" + More_Details_Qs5);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='us_business']/div[1]/div[1]/div/button[1]")).click();
		Thread.sleep(4000);
		// Locate the 6th question and select Yes and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Is the firm considered small in accordance with its primary"));
		logger.info("6th question was validated");
		String More_Details_Qs6;
		More_Details_Qs6 = webDriver.findElement(By.xpath(".//*[@id='small_naics']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 6th is" + More_Details_Qs6);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='small_naics']/div[1]/div[1]/div/button[1]")).click();
		Thread.sleep(4000);
		// Locate the 7th question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Are the qualifying individual"));
		logger.info("7th question was validated");
		String More_Details_Qs7;
		More_Details_Qs7 = webDriver.findElement(By.xpath(".//*[@id='women_owning_business']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 7th is" + More_Details_Qs7);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='women_owning_business']/div[1]/div[1]/div/button[2]")).click();
		Thread.sleep(3000);
		// Verify that user is being navigated to the 10th question because
		// user select NO for the 7th question.
		assertTrue(webDriver.getPageSource().contains("Are WOSB Federal Contract Program set-asides available"));
		logger.info("===Question 8 was not skipped after answering NO for question 7");
		// Enter a valid NAICS Code.
		webDriver.findElement(By.xpath(".//*[@id='naics_code']")).sendKeys(naics);
		webDriver.findElement(By.xpath(".//*[@id='naics_button']")).click();
		Thread.sleep(4000);
		assertTrue(webDriver.getPageSource().contains("Are the qualifying individual"));
		logger.info("===Question 9 was not skipped after answering NO for question 7");
		// Select NO for question 9.
		webDriver.findElement(By.xpath(".//*[@id='economically_disadvantaged_wosb']/div[1]/div[1]/div/button[2]"))
				.click();
		Thread.sleep(4000);
		// Locate the 10th question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("interested in participating in SBA small"));
		logger.info("10th question was validated");
		String More_Details_Qs10;
		More_Details_Qs10 = webDriver.findElement(By.xpath(".//*[@id='economically_disadvantaged_8a']/div[1]/div[2]"))
				.getText();
		logger.info("The Detail meaning for question 10th is" + More_Details_Qs10);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='economically_disadvantaged_8a']/div[1]/div[1]/div/button[2]"))
				.click();
		Thread.sleep(3000);
		// Verify that user is being navigated to the 13th question because
		// user select NO for the 10th question.
		assertTrue(webDriver.getPageSource().contains("Do you identify as one of the following"));
		logger.info(
				"User Successfully navigated to the 13th question ':Do you identify as one of the following?'");
		Thread.sleep(4000);
		// Locate the 13th question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Do you identify as one of the following"));
		logger.info("13th question was validated");
		String More_Details_Qs13;
		More_Details_Qs13 = webDriver.findElement(By.xpath(".//*[@id='socially_disadvantaged']/div[1]/div[2]/p[1]"))
				.getText();
		logger.info("The Detail meaning for question 13th is" + More_Details_Qs13);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='socially_disadvantaged']/div[1]/div[1]/div/button[2]")).click();
		Thread.sleep(4000);
		// Locate the 14th question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Do you consider yourself socially disadvantaged because"));
		logger.info("14th question was validated");
		String More_Details_Qs14;
		More_Details_Qs14 = webDriver
				.findElement(By.xpath(".//*[@id='socially_disadvantaged_chronic']/div[1]/div[2]/p[1]")).getText();
		logger.info("The Detail meaning for question 14th is" + More_Details_Qs14);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='socially_disadvantaged_chronic']/div[1]/div[1]/div/button[2]"))
				.click();
		Thread.sleep(4000);
		// Locate the next question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Has the firm previously been certified as an"));
		logger.info("15th question was validated");
		String More_Details_Qs15;
		More_Details_Qs15 = webDriver.findElement(By.xpath(".//*[@id='eighta_certified']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 15th is" + More_Details_Qs15);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='eighta_certified']/div[1]/div[1]/div/button[2]")).click();
		Thread.sleep(4000);
		// Locate the next question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("claiming social and economic"));
		logger.info("Claiming EDWOSB question was validated");
		String More_Details;
		More_Details = webDriver.findElement(By.xpath(".//*[@id='eighta_one_time_used']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 15th is" + More_Details);
		Thread.sleep(4000);
		webDriver.findElement(By.xpath(".//*[@id='eighta_one_time_used']/div[1]/div[1]/div/button[2]")).click();
		Thread.sleep(4000);
		// Locate the next question and select NO and verify the More Detail
		// meaning of the question.
		assertTrue(webDriver.getPageSource().contains("Is the firms business address located in a HUBZone"));
		logger.info(" question was validated");
		String More_Details1;
		More_Details1 = webDriver.findElement(By.xpath(".//*[@id='address_in_hubzone']/div[1]/div[2]")).getText();
		logger.info("The Detail meaning for question 15th is" + More_Details1);
		webDriver.findElement(By.cssSelector("#employees_in_hubzone > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.no_button")).click();
		Thread.sleep(4000);
		// Verify searched results.
		Thread.sleep(4000);
		String actual_Text4 = webDriver.findElement(By.cssSelector("div.not_eligible")).getText();
		String expected_Text4 = "Based on the information you provided, you may not be eligible for the 8(a) BD Program:\nQualifying individuals must meet the economically disadvantaged financial criteria to participate in the program. However, many factors are taken into consideration during application review and it is possible that you may still qualify to participate. Contact your local SBA 8(a) Business Office for more information.\nQualifying individuals must meet socially disadvantaged criteria in order to participate in the program.";
		assertEquals(actual_Text4, expected_Text4);
		String actual_Text5 = webDriver.findElement(By.cssSelector("#wosb > div.not_eligible")).getText();
		String expected_Text5 = "Based on the information you provided, you may not be eligible for the WOSB Program:\nTo certify as a WOSB or EDWOSB the firm must be owned by women.\nThe NAICS code entered does not appear on the list of designated industries in which WOSB Program set-asides are authorized. As such, be advised that federal contracts currently cannot be set-aside under the WOSB Program in this industry. However, this does not preclude you from certifying as a WOSB or EDWOSB if you meet the requirements of the WOSB Program.";
		assertEquals(actual_Text5, expected_Text5);
		String actual_Text6 = webDriver.findElement(By.cssSelector("#edwosb > div.not_eligible")).getText();
		String expected_Text6 = "Based on the information you provided, you may not be eligible for the EDWOSB Program:\nTo certify as a WOSB or EDWOSB the firm must be owned by women.\nThe NAICS code entered does not appear on the list of designated industries in which WOSB Program set-asides are authorized. As such, be advised that federal contracts currently cannot be set-aside under the WOSB Program in this industry. However, this does not preclude you from certifying as a WOSB or EDWOSB if you meet the requirements of the WOSB Program.\nTo qualify for EDWOSB the firm must be economically disadvantaged.";
		assertEquals(actual_Text6, expected_Text6);
		String actual_Text7 = webDriver.findElement(By.cssSelector("#hubzone > div.not_eligible > b")).getText();
		String expected_Text7 = "Based on the information you provided, you may not be eligible for the HUBZone Program:";
		assertEquals(actual_Text7, expected_Text7);
	}
	@After
	public void tearDown()throws Exception {
		webDriver.quit();
	}
}

