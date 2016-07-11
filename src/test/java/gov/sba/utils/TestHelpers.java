package gov.sba.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class TestHelpers {
	private static final Logger logger = LogManager.getLogger(TestHelpers.class.getName());

	// TODO: may be rename this to RAILS_ENV to make it consistent with Rails
	final public static String TEST_ENV = "TEST_ENV";
	final public static String BROWSER = "browser";
	final public static String BASE_URL = "base_url_";

	public static WebDriver getDefaultWebDriver() {
		Properties props = new Properties();
		WebDriver driver = null;
		try {
			props.load(TestHelpers.class.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			throw new RuntimeException("Error loading the resource file." + e.getMessage());
		}

		// Setup the configuration based on the browser we are using
		String browser = props.getProperty(BROWSER);
		System.setProperty(BROWSER, browser);
		String envUnderTest = System.getenv(TEST_ENV);

		// Default to 'development' if none is provided
		if (envUnderTest == null) {
			envUnderTest = "development";
		}

		logger.info("Your system under test :" + envUnderTest);
		System.setProperty(TEST_ENV, envUnderTest);
		logger.info("Setting environment:" + TEST_ENV + " to " + envUnderTest);

		String testUrl = props.getProperty(BASE_URL + envUnderTest);

		if (testUrl == null) {
			throw new RuntimeException(
					"You need to setup the '" + BASE_URL + envUnderTest + "' in your default.properties file");
		}

		logger.info("FYI: your test URL:" + testUrl);
		logger.info("FYI: you are using the browser: " + browser);

		// Set it so that we can use it later
		System.setProperty(BASE_URL + envUnderTest, testUrl);

		String[] configKeys;
		switch (browser) {
		case "chrome":
			configKeys = new String[] { "webdriver.chrome.driver" };
			setSystemProperties(configKeys, props);
			driver = new ChromeDriver();
			break;
		case "firefox":
			if (TestHelpers.isUnix(TestHelpers.systemType())) {
				// Need to provide specific type information for Linux
				configKeys = new String[] { "webdriver.firefox.bin", "webdriver.firefox.port" };
				setSystemProperties(configKeys, props);
			}

			// TODO: verify if we need to do the same for MacOs?
			driver = new FirefoxDriver();
			break;
		case "phantomjs":
			configKeys = new String[] {
					// Note: add PhantomJS specific setting if any
			};
			setSystemProperties(configKeys, props);
			driver = new PhantomJSDriver();
			break;
		case "ie":
			configKeys = new String[] {
					// Note: add IE specific setting if any
			};
			throw new RuntimeException("IE is currently not supported, will be added later!");
		default:
			throw new RuntimeException("Unknown browser: " + browser);
		}
		;

		// NOTE: additional settings to make the driver more robust
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return driver;
	}

	public static String getBaseUrl() {
		return System.getProperty(TestHelpers.BASE_URL + System.getProperty(TEST_ENV));
	}

	private static void setSystemProperties(String[] configKeys, Properties props) {
		for (String confKey : configKeys) {
			logger.info("FYI: Update system property :" + confKey + "=" + props.getProperty(confKey));
			System.setProperty(confKey, props.getProperty(confKey));
		}
	}

	// For OS detection
	private static String systemType() {
		return System.getProperty("os.name").toLowerCase();
	}

	@SuppressWarnings("unused")
	private static boolean isWindows(String osName) {
		return (osName.indexOf("win") >= 0);
	}

	@SuppressWarnings("unused")
	private static boolean isMac(String osName) {
		return (osName.indexOf("mac") >= 0);
	}

	private static boolean isUnix(String osName) {
		return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0);
	}
}
