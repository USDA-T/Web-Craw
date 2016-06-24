package all_iterations_scripts;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US923_View_more_under_Business_Profile_should_be_a_link {

	public WebDriver mydriver;
	String BaseUrl;

	@Before
	public void US923_View_more_under_Business_Profile_should_be_a_link_413_setup() throws Exception {
		BaseUrl = "https://certify.qa.sba-one.net/users/sign_in";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();
	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void US923_View_more_under_Business_Profile_should_be_a_link_mymaintest() throws Exception {
		mydriver.navigate().to(BaseUrl);
		// Step 00. Open Firefox Browser and navigate to valid url.

		mydriver.manage().window().maximize();

		// Step 01. Login to dashboard, Locate the email search box and enter a
		// valid email.
		LogincorpPage login = new LogincorpPage(mydriver);
		login.Logincorp();

		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(3000);

		// Verify that the Manage documents button is changed to a link and
		// bellow the Document Library table.
		String actual_text = mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/a")).getText();
		String expected_text = "Manage documents";
		assertEquals(actual_text, expected_text);
		mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a")).click();
		Thread.sleep(4000);
		if (mydriver.getPageSource().contains("Alt Gov Business POC First Name")) {
			System.out.println("Text on link 'View More' is validated, Pass");
			mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a")).click();

			WebElement alllinks = mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[3]/div/a"));
			System.out.println("The View More button was successfully change to link: " + alllinks);

			System.out.println(alllinks.getText());
			// Verify that the new link "Manage documents" works.

			// Verify that the new link "Manage documents" works.
			mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/a")).click();
			Thread.sleep(4000);
			String actual_text1 = mydriver.findElement(By.xpath(".//*[@id='typefaces']")).getText();
			String expected_text1 = "My Documents";
			assertEquals(actual_text1, expected_text1);

		} else {
			System.out.println("Text on link 'View More' is not validated, failed");
			mydriver.quit();
		}
		Thread.sleep(3000);

	}

	@After
	public void US923_View_more_under_Business_Profile_should_be_a_link_teardown() throws Exception {
		mydriver.quit();
	}
}