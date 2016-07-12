package gov.sba.utils.helpers;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.sba.utils.Constants;
import gov.sba.utils.TestHelpers;

public class LoginHelpers {
	private static final Logger logger = LogManager.getLogger(LoginHelpers.class.getName());

	public static LoginInfo getLoginCredentials(String environment) throws Exception {
		LoginInfo loginInfo = new LoginInfo();

		logger.info("Your environment: " + environment);

		switch (environment) {
		case Constants.ENV_DEVELOPMENT :
			// TODO: read from property file so that no re-compile needed
			loginInfo.setLoginName("analyst1@mailinator.com");
			loginInfo.setPassword("password");
			break;
		case Constants.ENV_QA:
			// TODO: read from property file so that no re-compile needed
			loginInfo.setLoginName("deric");
			loginInfo.setPassword("password-for-qa");
			break;
		case Constants.ENV_STAGING:
			// TODO: read from property file so that no re-compile needed
			loginInfo.setLoginName("deric");
			loginInfo.setPassword("password-for-staging");
			break;
		default:
			throw new Exception("Invalid environment, must be one of 'development', 'qa', or 'staging'");
		}

		return loginInfo;
	}
	
	public static void readCsv() throws Exception {
		// Make sure that we have the TEST_ENV set
		TestHelpers.loadDefaultProperties();
		
		String fixturesFile = "/fixtures_" + System.getProperty(Constants.TEST_ENV) + ".csv";
		System.out.println("Your fixture path: " + fixturesFile);
		Scanner scanner = new Scanner(LoginHelpers.class.getResourceAsStream(fixturesFile));
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			System.out.print(scanner.next() + "|");
		}
		scanner.close();
	}
	
	// TODO: for testing only!
	public static void main(String[] args) throws Exception {
		LoginHelpers.readCsv();
	}
}