package all_iterations_scripts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US801NOQ6 {
	private WebDriver driver;
	String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://certify.qa.sba-one.net/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void test_US801NOQ6() throws Exception {
		driver.get("https://certify.qa.sba-one.net/");

		try {
			assertTrue(isElementPresent(By.linkText("Am I Eligible?")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Am I Eligible?")).click();
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.usa-heading")).getText()
					.matches("^exact:Is there an SBA Small Business Program for me[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("SBA-ONE Certification", driver.getTitle());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector("button.yes_button")).click();
		driver.findElement(By.cssSelector(
				"#unconditional_direct_51_percent > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#for_profit > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches("^exact:Is the firm organized for profit[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#for_profit > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(isElementPresent(By
					.cssSelector("#non_suspended > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#non_suspended > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#us_business > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Does the firm have a place of business in the U\\.S\\. and operate primarily within the United States, or makes a significant contribution to the U\\.S\\. economy through payment of taxes or use of American products, materials or labors[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#us_business > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#small_naics > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Is the firm considered small in accordance with its primary North American Industry Classification System \\(NAICS\\) code[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#small_naics > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.no_button"))
				.click();
		try {
			assertTrue(isElementPresent(By.cssSelector("span.message")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertTrue(isElementPresent(By.linkText("Exit")));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.linkText("Exit")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
