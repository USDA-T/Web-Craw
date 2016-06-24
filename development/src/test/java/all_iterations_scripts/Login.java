package all_iterations_scripts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login {
	public WebDriver driver;
	String BaseURL;

	@Before
	public void login_setup() throws Exception {
		driver = new FirefoxDriver();
		BaseURL = "https://certify.qa.sba-one.net/users/sign_in";
	}

	@Test
	public void login_MainTest() throws Exception {
		driver.navigate().to(BaseURL);
		LogincorpPage login = new LogincorpPage(driver);
		login.Logincorp();
	}

	@After
	public void Teardown() throws Exception {
	}
}
