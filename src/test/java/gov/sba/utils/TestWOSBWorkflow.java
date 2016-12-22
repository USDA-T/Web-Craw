package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class TestWOSBWorkflow {

    WebDriver webDriver;
    private static final Logger TestWOSBWorkflow = LogManager.getLogger(TestUS1280_OppSuppStaff.class.getName());
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {

        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 26;
    }

    @Test
    public void testMainTest() throws Exception {
        try {
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            Thread.sleep(2000);

            AddOrStartNewWosbCertPage addOrStartNewWosbCert = new AddOrStartNewWosbCertPage(webDriver);
            addOrStartNewWosbCert.AddOrStartNewWosbbCert();

        }

        catch (Exception e) {
            TestWOSBWorkflow.info(e.toString());
        }
    }
}