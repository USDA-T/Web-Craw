package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(Constants.class.getName());

	final public static String TEST_ENV = "TEST_ENV";
	final public static String BROWSER = "browser";
	final public static String BASE_URL = "base_url_";
	
	final public static String ENV_DEVELOPMENT = "development";
	final public static String ENV_QA = "qa";
	final public static String ENV_STAGING = "staging";
	
	final public static String BROWSER_CHROME = "chrome";
	final public static String BROWSER_FIREFOX = "firefox";
	final public static String BROWSER_SAFARI = "safari";
	final public static String BROWSER_IE = "ie";
	final public static String BROWSER_PHANTOMJS = "phantomjs";

}