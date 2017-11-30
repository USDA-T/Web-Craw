package gov.sba.utils.integration;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gov.sba.automation.ConfigUtils;
import gov.sba.automation.Constants;

public class LoginHelpers {
  private static final Logger logger = LogManager.getLogger(LoginHelpers.class.getName());

  public static LoginData getLoginData() throws Exception {
    List<LoginData> loginFixtures = LoginHelpers.loadFixtures();
    return loginFixtures.get(3);
  }

  public static LoginData getLoginDataWithIndex(int passed_Index_Number) throws Exception {
    List<LoginData> loginFixtures = LoginHelpers.loadFixtures();
    return loginFixtures.get(passed_Index_Number);
  }

  public static List<LoginData> loadFixtures() throws Exception {
    // Make sure that we have the TEST_ENV set
    ConfigUtils.loadDefaultProperties();
    String fixturesFile = "fixtures_" + System.getProperty(Constants.TEST_ENV) + ".csv";

    // TODO: may be load this through the getResourceAsStream() e.g.
    // classPath instead?
    Reader in = new FileReader("src/main/resources/" + fixturesFile);

    Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

    List<LoginData> logins = new ArrayList<LoginData>();

    for (CSVRecord record : records) {

      String email = record.get(0);
      String password = record.get(1);
      String dunsNumber = record.get(2);
      String taxIdentifier = record.get(3);
      String miscInfo = record.get(4);
      String businessType = record.get(5);

      LoginData current = new LoginData();

      logger.debug("email          :" + email);
      logger.debug("password       :" + password);
      logger.debug("duns_number    :" + dunsNumber);
      logger.debug("tax_identifier :" + taxIdentifier);
      logger.debug("misc_info      :" + miscInfo);
      logger.debug("business_type   :" + businessType);

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

}
