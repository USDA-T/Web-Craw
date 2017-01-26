package gov.sba.utils.part_01_Deepa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;
import junit.runner.Version;

public class testJunitVersion extends TestCase {
    // Set The variabl.es/Define
    private static WebDriver webDriver;
    private static final Logger testJunitVersionLogger = LogManager
            .getLogger(testJunitVersion.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        System.out.println("JUnit version is: " + Version.id());

    }

    @Test
    public void testMainTest() throws Exception {
        // Login to dashboard.

    }
}