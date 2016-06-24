package all_iterations_scripts;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@SuppressWarnings("unused")
public class US801SmokeTS1 {
	private WebDriver driver;
	private String baseUrl;
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
	public void testUS801SmokeTS1() throws Exception {
		driver.get("https://certify.qa.sba-one.net/");
		driver.findElement(By.linkText("Am I Eligible?")).click();
		try {
			assertTrue(driver.findElement(By.cssSelector("h1.usa-heading")).getText()
					.matches("^exact:Is there an SBA Small Business Program for me[\\s\\S]$"));
		} catch (Throwable e) {
			System.out.println("h1.usa-heading");
			verificationErrors.append(e.toString());
			erroeCollector.addError(e);
		}
		try {
			assertTrue(driver.findElement(By.cssSelector("h4.copy")).getText().matches(
					"^exact:Are the qualifying individual\\(s\\) of the firm who are applying for SBA small business programs U\\.S\\. citizens[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector("button.yes_button")).click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#unconditional_direct_51_percent > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches("^exact:Is the 51% ownership of the firm unconditional and direct[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
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
			assertTrue(driver
					.findElement(By.cssSelector(
							"#non_suspended > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Do you affirm that neither this firm, nor any of its owners, have ever been debarred or suspended by any federal entity[\\s\\S]$"));
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
				"#small_naics > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#women_owning_business > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Are the qualifying individual\\(s\\) of the firm women who own at least 51% of the firm[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#women_owning_business > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#naics_fed_set_asides > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Are WOSB Federal Contract Program set-asides available in your primary NAICS code[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.id("naics_code")).clear();
		driver.findElement(By.id("naics_code")).sendKeys("541410");
		assertTrue(isElementPresent(By.id("naics_button")));
		driver.findElement(By.id("naics_button")).click();
		driver.findElement(By.cssSelector(
				"#economically_disadvantaged_wosb > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#economically_disadvantaged_8a > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Are the individual\\(s\\) interested in participating in SBA small business programs economically disadvantaged under 8\\(a\\) BD Program guidelines[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#economically_disadvantaged_8a > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertEquals("Black American", driver.findElement(By.cssSelector("h4.copy > ul > li")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Asian Pacific American",
					driver.findElement(By.xpath("//div[@id='socially_disadvantaged']/div/div/h4/ul/li[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Hispanic American",
					driver.findElement(By.xpath("//div[@id='socially_disadvantaged']/div/div/h4/ul/li[3]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Native American",
					driver.findElement(By.xpath("//div[@id='socially_disadvantaged']/div/div/h4/ul/li[4]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Subcontinent Asian American",
					driver.findElement(By.xpath("//div[@id='socially_disadvantaged']/div/div/h4/ul/li[5]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#socially_disadvantaged > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#socially_disadvantaged_chronic > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Do you consider yourself socially disadvantaged because of you experienced bias of a chronic and substantial nature[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#socially_disadvantaged_chronic > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#eighta_certified > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText()
					.matches("^exact:Has the firm previously been certified as an 8\\(a\\) participant[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#eighta_certified > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.no_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#eighta_one_time_used > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches(
							"^exact:Have any individual\\(s\\) claiming social and economic disadvantage, previously used their one time 8\\(a\\) eligibility to qualify a business for the 8\\(a\\) BD Program[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#eighta_one_time_used > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		driver.findElement(By.cssSelector(
				"#eighta_one_time_used > div.a > div.usa-width-one-whole.bottom-border > div.usa-width-one-third > a.change.change-ans"))
				.click();
		driver.findElement(By.cssSelector(
				"#eighta_one_time_used > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.no_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#address_in_hubzone > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches("^exact:Is the firm�s business address located in a HUBZone[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#address_in_hubzone > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		try {
			assertTrue(driver
					.findElement(By.cssSelector(
							"#employees_in_hubzone > div.q.makeitonehundredpercent > div.usa-width-one-half > h4.copy"))
					.getText().matches("^exact:Do 35% or more of the firm�s employees reside in a HUBZone[\\s\\S]$"));
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		driver.findElement(By.cssSelector(
				"#employees_in_hubzone > div.q.makeitonehundredpercent > div.usa-width-one-half > div.answer > button.yes_button"))
				.click();
		assertEquals("Based on the information you provided, you may be eligible for the WOSB Program.",
				driver.findElement(By.cssSelector("div.eligible > p > b")).getText());
		assertEquals("Based on the information you provided, you may be eligible for the EDWOSB program.",
				driver.findElement(By.cssSelector("#edwosb > div.eligible > p > b")).getText());
		assertEquals("Based on the information you provided, you may be eligible for the 8(a) BD Program.",
				driver.findElement(By.cssSelector("#eighta > div.eligible > p > b")).getText());
		assertEquals("Based on the information you provided, you may be eligible for the HUBZone Program.",
				driver.findElement(By.cssSelector("#hubzone > div.eligible > p > b")).getText());
		driver.findElement(By.linkText("Home")).click();

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

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

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