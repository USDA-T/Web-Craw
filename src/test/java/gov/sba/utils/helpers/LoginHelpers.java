package gov.sba.utils.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginHelpers {
	private static final Logger logger = LogManager.getLogger(LoginHelpers.class.getName());

	public static LoginInfo getLoginCredentials(String environment) throws Exception {
		LoginInfo loginInfo = new LoginInfo();		
		
		logger.info("Your environment: " + environment);
		
		switch (environment) {
		case "development":
			// TODO: read from property file so that no re-compile needed
			loginInfo.setLoginName("analyst1@mailinator.com");
			loginInfo.setPassword("password");
			break;
		case "qa":
			// TODO: read from property file so that no re-compile needed
			loginInfo.setLoginName("deric");
			loginInfo.setPassword("password-for-qa");			
			break;
		case "staging":
			// TODO: read from property file so that no re-compile needed
			loginInfo.setLoginName("deric");
			loginInfo.setPassword("password-for-staging");
			break;
		default:
			throw new Exception("Invalid environment, must be one of 'development', 'qa', or 'staging'");
		}
		
		return loginInfo;
	}
}