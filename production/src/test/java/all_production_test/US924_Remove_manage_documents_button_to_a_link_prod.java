package all_production_test;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US924_Remove_manage_documents_button_to_a_link_prod {

	private WebDriver mydriver;
	String myURL;
	String Email;
	String PassW;

	@Before
	public void US924_Remove_manage_documents_button_to_a_link_setup() throws Exception {

		myURL = "https://certify.sba.gov/users/sign_in";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();
	}

	@Test
	public void US924_Remove_manage_documents_button_to_a_link_Maintest() throws Exception {
		// Locate the test date from the PC and verify that all data are valid.
		Thread.sleep(4000);
		// Step 00. Open Firefox Browser and navigate to valid url.
		mydriver.navigate().to(myURL);
		Thread.sleep(3000);
		mydriver.manage().window().maximize();

		ProdloginPage prodlogin = new ProdloginPage(mydriver);
		prodlogin.Prodlogin();
		Thread.sleep(3000);

		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(3000);

		// Verify that the Manage documents button is changed to a link and
		// bellow the Document Library table.
		if (mydriver.getPageSource().contains("Manage documents")) {
			System.out.println("Text on link 'Manage documents' is validated, Pass");
			WebElement alllinks = mydriver.findElement(By.linkText("Manage documents"));
			System.out.println("The Manage Documents button was successfully change to link: " + alllinks);

			System.out.println(alllinks.getText());

			// Verify that the new link "Manage documents" works.
			mydriver.findElement(By.linkText("Manage documents")).click();

			Thread.sleep(3000);
			if (mydriver.getPageSource().contains("My Documents")) {
				System.out.println("The button was successfully change to a link and the link is active, Pass");
				mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
			} else {
				System.out.println("The 'Manage documents' link is not active, Failed");
				mydriver.quit();
			}

		} else {
			System.out.println("Text on link 'Manage documents' is not validated, failed");
			mydriver.quit();
		}
		Thread.sleep(3000);

	}

	@After
	public void US924_Remove_manage_documents_button_to_a_link_teardown() throws Exception {
		mydriver.quit();
	}
}
