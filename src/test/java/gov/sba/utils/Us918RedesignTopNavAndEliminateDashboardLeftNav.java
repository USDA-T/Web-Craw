package gov.sba.utils;

import static org.junit.Assert.assertEquals;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Us918RedesignTopNavAndEliminateDashboardLeftNav {
	private static final Logger logger = LogManager
			.getLogger(Us918RedesignTopNavAndEliminateDashboardLeftNav.class.getName());
	public WebDriver webDriver;

	@Before
	public void setup() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
	}
	
	@Test
	public void mainTest() throws Exception {
		String actual_Text = null;
		String expected_Text = null;
		logger.info("Eliminating left navigation on internal/private pages for vendor test");
		// Login to dashboard.
		LoginPage login = new LoginPage(webDriver);
		login.Login();
		Thread.sleep(4000);
		// Locate and verify the My Certification link.
		actual_Text = webDriver.findElement(By.cssSelector("li.nav-link.current-top-nav > a")).getText();
		expected_Text = "Certifications";
		assertEquals(expected_Text, expected_Text);
		webDriver.findElement(By.linkText("Certifications")).click();
		// Validate the navigated page.
		actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		expected_Text = "My certifications";
		assertEquals(actual_Text, expected_Text);
		// Verify the table.
		actual_Text = webDriver.findElement(By.cssSelector("th")).getText();
		expected_Text = "Certification";
		assertEquals(actual_Text, expected_Text);
		// Locate and verify the My Document link.
		actual_Text = webDriver.findElement(By.linkText("Documents")).getText();
		expected_Text = "Documents";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("Documents")).click();
		// Verify the navigated page.
		actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		expected_Text = "My document library";
		assertEquals(actual_Text, expected_Text);
		// Verify the document table.
		actual_Text = webDriver.findElement(By.cssSelector("th.sorting_asc")).getText();
		expected_Text = "Document Name";
		assertEquals(actual_Text, expected_Text);
		// Locate and verify the Business link.
		actual_Text = webDriver.findElement(By.linkText("Business")).getText();
		expected_Text = "Business";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("Business")).click();
		// Verify the navigated page.
		actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		expected_Text = "Business profile";
		assertEquals(actual_Text, expected_Text);
		// click and verify the Manage CO access link.
		actual_Text = webDriver.findElement(By.linkText("Manage CO access")).getText();
		expected_Text = "Manage CO access";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("Manage CO access")).click();
		// Verify the navigated page.
		actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		expected_Text = "Manage Contracting Officer access";
		assertEquals(actual_Text, expected_Text);
		// Locate and verify the Profile link.
		actual_Text = webDriver.findElement(By.linkText("Profile")).getText();
		expected_Text = "Profile";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("Profile")).click();
		// Verify the navigated page.
		actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		expected_Text = "Edit passphrase";
		assertEquals(actual_Text, expected_Text);
		// Locate and verify the Help link.
		actual_Text = webDriver.findElement(By.linkText("Help")).getText();
		expected_Text = "Help";
		assertEquals(actual_Text, expected_Text);
		webDriver.get("http://certify.qa.sba-one.net");
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String main_window = webDriver.getWindowHandle();
		logger.info("Before switching, title is =" + webDriver.getTitle());
		webDriver.findElement(By.linkText("Help")).click();
		Thread.sleep(3000);
		actual_Text = webDriver.findElement(By.linkText("Return to top")).getText();
		expected_Text = "Return to top";
		assertEquals(actual_Text, expected_Text);
		java.util.Set<String> S1 = webDriver.getWindowHandles();
		Iterator<String> i1 = S1.iterator();
		webDriver.findElement(By.linkText("Login")).click();
		actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		expected_Text = "You are already signed in.";
		assertEquals(actual_Text, expected_Text);
		while (i1.hasNext()) {
			String Second_window = i1.next();
			if (!main_window.equalsIgnoreCase(Second_window)) {
				webDriver.switchTo().window(Second_window);
				Thread.sleep(1000);
				logger.info("After switching title is =" + webDriver.getTitle());
				//webDriver.close();
			}
		}
		webDriver.switchTo().window(main_window);
		logger.info("Back to manin_window " + webDriver.getTitle());
		// Locate, click and verify the Dashboard link.
		actual_Text = webDriver.findElement(By.linkText("Dashboard")).getText();
		expected_Text = "Dashboard";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("Dashboard")).getText();
		// logout.
		webDriver.findElement(By.linkText("Logout")).click();
	}

	@After
	public void teardown() throws Exception {
		webDriver.quit();
	}
}

