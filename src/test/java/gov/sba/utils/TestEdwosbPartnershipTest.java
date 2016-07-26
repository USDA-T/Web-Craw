package gov.sba.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;
public class TestEdwosbPartnershipTest extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestEdwosbPartnershipTest.class.getName());
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
	public void testMainLogic() throws Exception {
		// Login to dashboard.
		LoginPage login = new LoginPage(webDriver);
		login.Login();
		Thread.sleep(3000);
		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		try{
		assertTrue(webDriver.getPageSource().contains("Draft"));
			webDriver.findElement(By.linkText("Delete")).click();
			webDriver.switchTo().alert().accept();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			webDriver.navigate().refresh();
		} catch (Error e){
			logger.info(
					"There are(is) no certification in-progress on the dashboard, a new certification is beinng created");
		}
		Thread.sleep(7000);
		// Locate the Certifications on the dashboard, click on it and select
		// EDWOSB to continue.
		webDriver.findElement(By.linkText("Certifications")).click();
		webDriver.findElement(By.linkText("EDWOSB")).click();
		Thread.sleep(2000);
		// partnership test for 1st person.
		PartnershipQuestionsPage partnershipquestions = new PartnershipQuestionsPage(webDriver);
		partnershipquestions.Partnershipquestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();
	}
}