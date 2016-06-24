package all_iterations_scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogincorpPage {
	WebDriver driver;

	public LogincorpPage(WebDriver driver) {
		this.driver = driver;
	}

	public void Logincorp() throws Exception {
		String actual_Text4 = driver.findElement(By.xpath(".//*[@id='hp']/div[2]/div[1]/div/span")).getText();
		String expected_Text4 = "Sign in to certify.SBA.gov";
		assertEquals(actual_Text4, expected_Text4);

		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys("akanamontana@gmail.com");
		// Step 2. Locate the password search box and enter a valid password.
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys("password");
		// Step 3. Locate the Sign-in button and click on it to login.
		driver.findElement(By.xpath(".//*[@id='business_signin']")).click();
	}

	private void assertEquals(String actual_Text4, String expected_Text4) {
		// TODO Auto-generated method stub
	}
}
