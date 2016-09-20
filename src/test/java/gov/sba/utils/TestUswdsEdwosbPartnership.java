package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;
public class TestUswdsEdwosbPartnership extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestUswdsEdwosbPartnership.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;


	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 3;

	}

	@Test
	public void testMainTest() throws Exception {
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		try {
			assertTrue(webDriver.getPageSource().contains("Delete"));
			webDriver.findElement(By.linkText("Delete")).click();
			//webDriver.switchTo().alert().accept();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			webDriver.navigate().refresh();
		} catch (Error e) {
			logger.info(
					"There are(is) no certification in-progress on the dashboard, a new certification is beinng created");
		}
		// Verify for active and Draft certification on the dashboard, if draft
		// delete and start a new one.
		AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
		addOrStartCertification.AddOrStartCertification();
		Thread.sleep(2000);
		// partnership test for 1st person.
		PartnershipQuestionsPage partnershipquestions = new PartnershipQuestionsPage(webDriver);
		partnershipquestions.Partnershipquestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();
	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
