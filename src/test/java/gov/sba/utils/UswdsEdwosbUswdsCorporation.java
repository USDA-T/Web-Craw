package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class UswdsEdwosbUswdsCorporation {
	private static final Logger logger = LogManager.getLogger(UswdsEdwosbUswdsCorporation.class.getName());
	private static WebDriver webDriver;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
	}

	@Test
	public void mainTest() throws Exception {
		logger.info("Start or add new cert page will be called");
		AddOrStartNewEdwosbCertPage addOrStartNewEdwosbCert = new AddOrStartNewEdwosbCertPage(webDriver);
		addOrStartNewEdwosbCert.AddOrStartNewEdwosbCert();
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
