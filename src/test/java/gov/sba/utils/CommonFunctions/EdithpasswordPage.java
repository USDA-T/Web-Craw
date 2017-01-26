package gov.sba.utils.CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EdithpasswordPage {
    WebDriver driver;

    public EdithpasswordPage(WebDriver driver) {
        this.driver = driver;

    }

    public void Edithpassword() throws Exception {

        driver.findElement(By.cssSelector("button.button-full")).click();
        Thread.sleep(4000);
        String actual_Text4 = driver.findElement(By.cssSelector("h2")).getText();
        String expected_Text4 = "Sign in to certify.SBA.gov";
        assertEquals(actual_Text4, expected_Text4);
        driver.findElement(By.id("user_email")).sendKeys("staging3@mailinator.com");
        // Step 2. Locate the password search box and enter a valid password.
        driver.findElement(By.id("user_password")).sendKeys("password");
        // Step 3. Locate the Sign-in button and click on it to login.
        driver.findElement(By.id("business_signin")).click();

    }

    private void assertEquals(String actual_Text4, String expected_Text4) {
        // TODO Auto-generated method stub

    }

}