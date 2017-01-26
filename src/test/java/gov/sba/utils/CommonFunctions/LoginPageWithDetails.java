package gov.sba.utils.CommonFunctions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageWithDetails {
    private static final Logger logger = LogManager.getLogger(LoginPageWithDetails.class.getName());
    WebDriver webDriver;
    String email , password;
    public LoginPageWithDetails(WebDriver webDriver_Passed_From_CallingFn, String email_Passed, String pwd_Passed) {
        this.webDriver = webDriver_Passed_From_CallingFn;
        this.email = email_Passed;
        this.password = pwd_Passed;
    }
    public void Login_With_Details() throws Exception {
        logger.debug("Using test login   : " + email);
        logger.debug("Using test password: " + password);
        webDriver.findElement(By.cssSelector("button.button-full")).click();
        webDriver.findElement(By.name("user[email]")).sendKeys(email);
        webDriver.findElement(By.name("user[password]")).sendKeys(password);
        webDriver.findElement(By.id("business_signin")).click();
    }
}