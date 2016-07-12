package gov.sba.utils.helpers;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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
		case Constants.ENV_DEVELOPMENT:
			// TODO: read from property file so that no re-compile needed
			loginInfo.setEmail("analyst1@mailinator.com");
			loginInfo.setPassword("password");
			break;
		case Constants.ENV_QA:
			// TODO: read from property file so that no re-compile needed
			loginInfo.setEmail("deric");
			loginInfo.setPassword("password-for-qa");
			break;
		case Constants.ENV_STAGING:
			// TODO: read from property file so that no re-compile needed
			loginInfo.setEmail("deric");
			loginInfo.setPassword("password-for-staging");
			break;
		default:
			throw new Exception("Invalid environment, must be one of 'development', 'qa', or 'staging'");
		}

		return loginInfo;
	}

	public static List<LoginData> loadFixtures() throws Exception {
		// Make sure that we have the TEST_ENV set
		TestHelpers.loadDefaultProperties();
		String fixturesFile = "fixtures_" + System.getProperty(Constants.TEST_ENV) + ".csv";

		// TODO: may be load this through the getResourceAsStream() e.g.
		// classPath instead?
		Reader in = new FileReader("src/main/resources/" + fixturesFile);
		// email,password,duns_number,tax_identifier,col5,businessType

		Iterable<CSVRecord> records = CSVFormat.RFC4180
				.withHeader("email", "password", "duns_number", "tax_identifier", "misc_info", "business_type").parse(in);
		
		List <LoginData> logins = new ArrayList<LoginData>();
		
		for (CSVRecord record : records) {

			String email = record.get("email");
			String password = record.get("password");
			String dunsNumber = record.get("duns_number");
			String taxIdentifier = record.get("tax_identifier");
			String miscInfo = record.get("misc_info");
			String businessType = record.get("business_type");
			
			LoginData current = new LoginData();
			
			// TODO: use debug when done!
			logger.info("-----------------------------");
			logger.info("email          :" + email);
			logger.info("password       :" + password);
			logger.info("duns_number    :" + dunsNumber);
			logger.info("tax_identifier :" + taxIdentifier);
			logger.info("col5           :" + miscInfo);
			logger.info("business_type  :" + businessType);
			
			current.setEmail(email);
			current.setPassword(password);
			current.setDunsNumber(dunsNumber);
			current.setTaxIdentifier(taxIdentifier);
			current.setMiscInfo(miscInfo);
			current.setBusinessType(businessType);
			
			logins.add(current);
		}
		
		return logins;
		
	}

	// TODO: for testing only, remove when done!
	public static void main(String[] args) throws Exception {
		List<LoginData> result = LoginHelpers.loadFixtures();
		System.out.println(result.get(1));
	}
}