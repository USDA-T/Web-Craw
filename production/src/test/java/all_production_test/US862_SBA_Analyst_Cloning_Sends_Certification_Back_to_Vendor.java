package all_production_test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US862_SBA_Analyst_Cloning_Sends_Certification_Back_to_Vendor {
	public WebDriver mydriver;
	String myurl;
	String AEmail;
	String APassW;
	String VEmail;
	String VPassW;
	String Duns;

	@Before
	public void US862_SBA_Analyst_Cloning_Sends_Certification_Back_to_Vendor_Setup() throws Exception {
		AEmail = "arun@enquizit.com";
		APassW = "Be at least 8 characters";
		VEmail = "donald.patti@cedarpointconsulting.com";
		VPassW = "They call me Ishmael";
		myurl = "https://certify.sba.gov/users/sign_in";
		Duns = "023027981";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();

	}

	@Test
	public void US862_SBA_Analyst_Cloning_Sends_Certification_Back_to_Vendor_Maintest() throws Exception {
		// open Firefox,Chrome or IE and Navigate to valid URL.
		mydriver.navigate().to(myurl);
		mydriver.manage().window().maximize();

		// Login to dashboard, Locate the email search box and enter a valid
		// email.
		mydriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(AEmail);
		// Locate the password search box and enter a valid password.
		mydriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(APassW);
		// Locate the Sign-in button and click on it to login.
		mydriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(3000);
		// Locate the Search for a vendor search box and enter a valid duns
		// number.
		mydriver.findElement(By.xpath(".//*[@id='query']")).sendKeys(Duns);
		// Locate the search button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='analyst-search']/div/div[2]/button")).click();
		// Locate the View Profile button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='view_business_profile']")).click();
		// Verify that the analyst is able to send an active certification back
		// to the vendor for review.
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Return to Vendor")) {
			System.out.println("Analyst is able to return certification back to the vendor, Pass");
		} else {
			System.out.println("Analyst is Unable to return certification back to the vendor, Failed");
			mydriver.quit();

		}
		Thread.sleep(3000);

	}

	@After
	public void US862_SBA_Analyst_Cloning_Sends_Certification_Back_to_Vendor_Endtest() throws Exception {
		mydriver.quit();
	}

}
