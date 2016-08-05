package gov.sba.utils;
import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class UswdsEdwosbScorpTest {
	private static final Logger logger = LogManager.getLogger(UswdsEdwosbPartnershipTest.class.getName());
	private static WebDriver webDriver;
	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
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
		Thread.sleep(7000);
		// Locate the Certifications on the dashboard, click on it and select
		// EDWOSB to continue.
		webDriver.findElement(By.xpath("//article[@id='main-content']/section/article/div[4]/h3/a[2]/b")).click();
		Thread.sleep(2000);
		// Corp test for 1st person.
		ScorpQuestionsPage scorpQuestions = new ScorpQuestionsPage(webDriver);
		scorpQuestions.ScorpQuestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();
	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
