package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

public class TestUs438EightARegression1 extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestUs438EightARegression1.class.getName());
	public WebDriver webDriver;
	int get_The_Row_From_Login_Data;


	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 1;

	}

	@Test
	// US
	public void testMainTest() throws Exception {
		// Step 1. Open Firefox browser and navigate to url.
		Thread.sleep(2000);
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		assertFalse(webDriver.getPageSource().contains("Signed in successfully"));
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
					"There are(is) no Program in-progress on the dashboard, a new program is beinng created");
		}
		webDriver.navigate().refresh();
		Thread.sleep(7000);
		// Step 5. Locate and click the Certifications button, from the
		// drop-down, select EDWOSB.
		webDriver.findElement(By.linkText("Programs")).click();
		webDriver.findElement(By.id("certificate_type_wosb")).click();
		webDriver.findElement(By.id("add_certification")).click();
		Thread.sleep(3000);
		// Step 6. Locate the accept button for the terms of certification and
		// click on it.
		webDriver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
		Thread.sleep(3000);
		// Step 7. Select YES for the 8(a) Question and attached document if
		// applicable .
		webDriver.findElement(By.xpath(".//*[@id='answers[65][value]']/label[1]")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.xpath(".//*[@id='answers[5][attachment]']/div/label/div")).click();
		Thread.sleep(2000);
		// mydriver.findElement(By.xpath(".//*[@id='answers[5][attachment]']/div/div[1]/div/ul/li[2]/a")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.name("selector")).click();
		Thread.sleep(5000);
		webDriver.findElement(By.id("document_library_attach")).click();
		// Step 10. Verify that document is attached.
		System.out.println(webDriver.findElement(By.xpath(".//*[@id='tentatively_attached']/tbody/tr/td")).getText());
		Thread.sleep(5000);
		// Step 11. Locate the save and continue button and click on it.
		webDriver.findElement(By.name("commit")).click();
		Thread.sleep(2000);
		// Verify that user navigated to the review page upon successful
		// document upload.
		assertTrue(webDriver.getPageSource().contains("Review"));
		logger.info("Document successfully uploaded, Pass");
		// Step 16. Locate the sign-out button and click on it.
		webDriver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
	}

	@After
	public void tearDown() throws Exception {
		webDriver.close();
	}
}
