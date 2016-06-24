package gov.sba.utils;

import java.io.IOException;
import java.util.Properties;

public class TestHelpers {

  public static void initSystemConfig() {
    Properties props = new Properties();

    try {
      props.load(TestHelpers.class.getResourceAsStream("/default.properties"));
    } catch (IOException e) {
      throw new RuntimeException("Error loading the resource file." + e.getMessage());
    }

    // Setup the configuration based on the browser we are using
    String browser = props.getProperty("browser");
    System.out.println("I am using: " + browser);
    String[] configKeys;

    // Set this so that we can use the right browser later one
    System.setProperty("browser", browser);

    switch (browser) {
      case "Chrome":
        configKeys = new String[] { "webdriver.chrome.driver" };
        break;
      case "Firefox":
        configKeys = new String[] { "webdriver.firefox.bin", "webdriver.firefox.port" };
        break;
      case "IE":
        configKeys = new String[] {
          //TODO: add IE specific settings
        };
        throw new RuntimeException("IE is currently not supported, will be added later!");
      default:
        throw new RuntimeException("Unknown browser: " + browser);
    };

    for (String confKey : configKeys) {
      System.out.println("FYI: update system property: " + confKey + "->" + props.getProperty(confKey));
      System.setProperty(confKey, props.getProperty(confKey));
    }
  }
}
