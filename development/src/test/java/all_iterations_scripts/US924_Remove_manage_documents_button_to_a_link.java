package all_iterations_scripts;

import static org.junit.Assert.assertEquals;

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
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US924_Remove_manage_documents_button_to_a_link {

	private WebDriver mydriver;
	String BaseUrl;

	@Before
	public void US924_Remove_manage_documents_button_to_a_link_setup() throws Exception {

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
	public void US924_Remove_manage_documents_button_to_a_link_mymaintest() throws Exception {
		// Locate the test date from the PC and verify that all data are valid.
		Thread.sleep(4000);
		mydriver.navigate().to(BaseUrl);
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

		WebElement alllinks = mydriver.findElement(By.linkText("Manage documents"));
		System.out.println("The Manage Documents button was successfully change to link: " + alllinks);

		System.out.println(alllinks.getText());

		Thread.sleep(3000);
		// Verify that the new link "Manage documents" works.
		mydriver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/a")).click();
		Thread.sleep(4000);
		String actual_text1 = mydriver.findElement(By.xpath(".//*[@id='typefaces']")).getText();
		String expected_text1 = "My Documents";
		assertEquals(actual_text1, expected_text1);

	}

	@After
	public void US924_Remove_manage_documents_button_to_a_link_teardown() throws Exception {
		mydriver.quit();
	}
}
