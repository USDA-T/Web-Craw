package all_production_test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US855_Improve_Text_Work_In_Progress_From_All_pages {
	public WebDriver mydriver;
	String myurl;
	String Email;
	String PassW;
	String Aemail;
	String ApassW;

	@Before
	public void US855_Improve_Text_Work_In_Progress_From_All_pages_Setup() throws Exception {
		myurl = "https://staging-certify.sba.gov/";
		Email = "akanamontana@gmail.com";
		PassW = "password";
		Aemail = "analyst1@mailinator.com";
		ApassW = "password";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();
	}

	@Test
	public void US855_Improve_Text_Work_In_Progress_From_All_pages_MainTest() throws Exception {
		// Open Firefox,IE or Chrome and navigate to the Certify.SBA.gov landing
		// page.
		mydriver.navigate().to(myurl);
		mydriver.manage().window().maximize();
		// Verify if the text 'If you don�t find what you need, visit SBA.gov'
		// is present on the landing page.
		String actual_Text = mydriver.findElement(By.cssSelector("div.usa-grid > div > span")).getText();
		String expected_Text = "Your experience is important to us. Please email us at  ";
		assertEquals(actual_Text, expected_Text);
		// Locate the Find Out button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='hp']/div[4]/div/div[2]/div/form/input")).click();
		Thread.sleep(3000);
		// Verify if the text 'If you don�t find what you need, visit SBA.gov'
		// is present on the Am I Eligible page.
		String actual_Text1 = mydriver.findElement(By.cssSelector("div.usa-grid > div > span")).getText();
		String expected_Text1 = "Your experience is important to us. Please email us at  ";
		assertEquals(actual_Text1, expected_Text1);
		mydriver.navigate().back();
		Thread.sleep(3000);

		// Locate the Create account button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[2]/button")).click();
		Thread.sleep(3000);

		// Verify if the text 'If you don�t find what you need, visit SBA.gov'
		// is present on the Create account page.
		String actual_Text2 = mydriver.findElement(By.cssSelector("span.footer-feedback-text")).getText();
		String expected_Text2 = "Your experience is important to us. Please email us at  ";
		assertEquals(actual_Text2, expected_Text2);
		mydriver.navigate().back();
		Thread.sleep(3000);

		// Locate the Log-in button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();
		Thread.sleep(3000);

		// Verify if the text 'If you don�t find what you need, visit SBA.gov'
		// is present on the login page.
		String actual_Text3 = mydriver.findElement(By.cssSelector("span.footer-feedback-text")).getText();
		String expected_Text3 = "Your experience is important to us. Please email us at  ";
		assertEquals(actual_Text3, expected_Text3);
		mydriver.navigate().back();
		Thread.sleep(3000);

		// Locate the Log-in button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();
		// Login to dashboard.
		ProdloginPage prodlogin = new ProdloginPage(mydriver);
		prodlogin.Prodlogin();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(4000);
		// Verify if the text 'If you don�t find what you need, visit SBA.gov'
		// is present on the Vendor dashboard.
		String actual_Text5 = mydriver.findElement(By.cssSelector("span.footer-feedback-text")).getText();
		String expected_Text5 = "Your experience is important to us. Please email us at  ";
		assertEquals(actual_Text5, expected_Text5);
		// locate the sign out button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[6]/a")).click();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed out successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		// Locate the Log-in button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();
		// Locate the email search box and enter a valid analyst email.
		mydriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Aemail);
		// Locate password search box and enter a valid analyst password.
		mydriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(ApassW);
		// Click on the sign-in button.
		mydriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(4000);
		// Verify if the text 'If you don�t find what you need, visit SBA.gov'
		// is present on the Vendor dashboard.
		String actual_Text6 = mydriver.findElement(By.cssSelector("span.footer-feedback-text")).getText();
		String expected_Text6 = "Your experience is important to us. Please email us at  ";
		assertEquals(actual_Text6, expected_Text6);

		// locate the sign out button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed out successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}

	}

	@After
	public void US855_Improve_Text_Work_In_Progress_From_All_pages_EndTest() throws Exception {
		mydriver.quit();

	}

}
