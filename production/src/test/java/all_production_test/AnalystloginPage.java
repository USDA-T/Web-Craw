package all_production_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnalystloginPage {
	WebDriver driver;

	public AnalystloginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void Analystlogin() throws Exception {
		String actual_Text4 = driver.findElement(By.xpath(".//*[@id='hp']/div[2]/div[1]/div/span")).getText();
		String expected_Text4 = "Sign in to certify.SBA.gov";
		assertEquals(actual_Text4, expected_Text4);

		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("arun@enquizit.com");
		// Step 2. Locate the password search box and enter a valid password.
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("Be at least 8 characters");
		// Step 3. Locate the Sign-in button and click on it to login.
		driver.findElement(By.xpath(".//*[@id='business_signin']")).click();
	}

	private void assertEquals(String actual_Text4, String expected_Text4) {
		// TODO Auto-generated method stub
	}
}
