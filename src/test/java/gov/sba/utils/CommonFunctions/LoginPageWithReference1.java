package gov.sba.utils.CommonFunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gov.sba.utils.helpers.LoginHelpers;

public class LoginPageWithReference1 {
    private static final Logger logger = LogManager.getLogger(LoginPageWithReference1.class.getName());
    WebDriver webDriver;
    int get_Row_From_credentials_Recvd;

    public LoginPageWithReference1(WebDriver webDriver_Passed_From_CallingFn, int get_Row_From_credentials_Passed) {
        this.webDriver = webDriver_Passed_From_CallingFn;
        this.get_Row_From_credentials_Recvd = get_Row_From_credentials_Passed;
    }

    public void Login_With_Reference() throws Exception {
        logger.debug("Using test login   : "
                + LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
        logger.debug("Using test password: "
                + LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getPassword());
        webDriver.findElement(By.cssSelector("button.button-full")).click();
        webDriver.findElement(By.name("user[email]"))
                .sendKeys(LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getEmail());
        webDriver.findElement(By.name("user[password]"))
                .sendKeys(LoginHelpers.getLoginDataWithIndex(get_Row_From_credentials_Recvd).getPassword());
        webDriver.findElement(By.id("business_signin")).click();
        String url = webDriver.getCurrentUrl();
        org.junit.Assert.assertTrue(url.contains("certify"));
    }
}