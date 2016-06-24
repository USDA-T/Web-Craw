package gov.sba.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.TestCase;

public class ChromeDriverTest extends TestCase {
	public static final Logger logger = LogManager.getLogger(ChromeDriverTest.class);
	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Properties props = new Properties();
		try {
			props.load(ChromeDriverTest.class.getResourceAsStream("/default.properties"));
			System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));
			driver = new ChromeDriver();
		} catch (IOException e) {
			logger.fatal("Couldn't load in default properties");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@After
	public void tearDown() throws Exception {
		logger.info("tearDown()");
		driver.quit();
	}

	@Test
	public void testGoogleSearch() throws Exception {
		System.out.println("testGoogleSearch()");
		driver.get("https://www.google.com/webhp?gws_rd=ssl");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		Assert.assertEquals("Google", driver.getTitle());
	}
}
