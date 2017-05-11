//TS Created By _deepa patri
package gov.sba.utils.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.CommonApplicationMethods;
import gov.sba.automation.DatabaseUtils;
import gov.sba.automation.FixtureUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.StableTests.class })
public class TestUS1463MppReviewSummaryLink extends TestCase {
	// Set The variabl.es/Define
	private static WebDriver webDriver;
	private static final Logger logger_US1463 = LogManager.getLogger(TestUS1463MppReviewSummaryLink.class.getName());
	String duns_Number, email, password;

	@Before
	public void setUp() throws Exception {
		CommonApplicationMethods.clear_Env_Chrome();
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		CommonApplicationMethods.focus_window();
		String[] details = DatabaseUtils.findUnusedDunsNumber();
		email = details[0];
		password = details[1];
		duns_Number = details[2];
	}

	@Test
	public void testMainTest() throws Exception {

		// Login to dashboard.
		LoginPageWithDetails login_Data = new LoginPageWithDetails(webDriver, email, password);
		login_Data.Login_With_Details();

		try {

			CommonApplicationMethods.createApplication(webDriver, "MPP");
			webDriver.findElement(By.id("answers_117_value_yes")).click();
			String file_path_abs = FixtureUtils.fixturesDir() + "Upload.pdf";
			fillApplCreatePages.page8aFillUpDunsNo(webDriver, "Yes", file_path_abs, duns_Number);
			fillApplCreatePages.finalSignatureSubmit(webDriver);
			logger_US1463.info("Doc has been uploaded.");

			List<WebElement> count_Pending = webDriver.findElements(
					By.xpath("//*[@id='certifications']/tbody/tr" + "[" + "td[position()=1]/a[contains(text(),'MPP')]"
							+ " and " + "td[position()=5 and (contains(text(),'ending'))]" + "]"));

			assertEquals(count_Pending.size(), 1);

			count_Pending.get(0).findElement(By.xpath("td[position()=1]/a")).click();
			assertTrue(webDriver.getPageSource().toString().contains(duns_Number));
			// Come back later for CAGE Assertion with DB

			String stext = webDriver
					.findElement(By
							.xpath("//h4[contains(text(),'ation is processed and evaluated, a member of the All Smal')]"))
					.getText();
			logger_US1463.info(stext);
			assertEquals(stext,
					"Thank you for submitting your application to participate in SBA’s All Small Mentor-Protégé Program. Once your application is processed and evaluated, a member of the All Small Mentor-Protégé Program Office will contact you to verify your application status.");

			stext = webDriver.findElement(By.xpath("//h1[contains(text(),'Program Self-Certification Summary')]"))
					.getText();
			logger_US1463.info(stext);
			assertEquals(stext, "All Small Mentor Protégé Program Program Self-Certification Summary");

			Boolean found = false;
			try {
				webDriver.findElement(
						By.xpath("//a[ @class='tab-link' and contains ( text(), 'Certificate Letter' ) ]"));
				found = true;
			} catch (Exception e) {
				logger_US1463.info("Good , no Link");
			}
			assertFalse(found);

		} catch (Exception e) {
			logger_US1463.info(e.toString());
			CommonApplicationMethods.take_ScreenShot_TestCaseName(webDriver,
					new String[] { "TestUS1463MppReviewSummaryLink", "Exception" });
			throw new Exception("Error: ", e);
		}
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}

}