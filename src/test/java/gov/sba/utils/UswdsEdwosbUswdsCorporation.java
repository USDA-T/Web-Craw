package gov.sba.utils;

import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UswdsEdwosbUswdsCorporation {
	private static final Logger logger = LogManager.getLogger(UswdsEdwosbUswdsCorporation.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;


	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 20;

	}

	@Test
	public void mainTest() throws Exception {
		// Login to dashboard.
		LoginPage login = new LoginPage(webDriver);
		login.Login();
		Thread.sleep(3000);
		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		try {
			assertTrue(webDriver.getPageSource().contains("Draft"));
			webDriver.findElement(By.linkText("Delete")).click();
			webDriver.switchTo().alert().accept();
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
		Thread.sleep(4000);
		AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
		addOrStartCertification.AddOrStartCertification();
		Thread.sleep(2000);
		// partnership test for 1st person.
		CorporationQuestionsPage corporationQuestions = new CorporationQuestionsPage(webDriver);
		corporationQuestions.CorporationQuestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
