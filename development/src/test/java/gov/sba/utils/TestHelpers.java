package gov.sba.utils;

import java.io.IOException;
import java.util.Properties;
public class TestHelpers {

	public static void initSystemConfig() {
		Properties props = new Properties();

		try {
			props.load(TestHelpers.class.getResourceAsStream("/default.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//TODO: pass this as parameters
		String[] configKeys = { "webdriver.firefox.bin", "webdriver.firefox.port" };

		for (String confKey : configKeys) {
			System.setProperty(confKey, props.getProperty(confKey));
		}
	}
}
