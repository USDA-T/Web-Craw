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

public class US888_Registration_Activate_Submit_Button_use_dialog_for_error_messages {

	public WebDriver mydrivers;
	String myurl;
	String FN;
	String LN;
	String PN;
	String Email;
	String PassW;
	String Re_PassW;
	String Weak_PassW;
	String Average_PassW;

	@Before
	public void IAM_Custom_Registration_Vendor_Starts_New_Registration_US652_Setup() throws Exception {
		myurl = "https://certify.sba.gov/";
		FN = "Danzel";
		LN = "Washington";
		PN = "708-765-9876";
		Email = "deric.nguni@gmail.com";
		PassW = "The Quick Brown Fox Jumbs Over The Lazy Dog?";
		Re_PassW = "The Quick Brown Fox Jumbs Over The Lazy Dog?";
		Weak_PassW = "123456";
		Average_PassW = "Hardworkpays$";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydrivers = new FirefoxDriver();

	}

	@Test
	public void IAM_Custom_Registration_Vendor_Starts_New_Registration_US652_Maintest() throws Exception {
		// Step 1. Open Firefox and navigate to url.
		mydrivers.navigate().to(myurl);
		mydrivers.manage().window().maximize();
		// Step 2. Verify if User see the registration link on the landing page.
		if (mydrivers.getPageSource().contains("CREATE ACCOUNT")) {
			System.out.println("User sees the link on the landing page for user to create an account, PASS");
			mydrivers.findElement(By.xpath(".//*[@id='gov_login_box']/form[2]/button")).click();

		} else {
			System.out.println("User is Not being navigated to the Registration Page, Failed");
			mydrivers.quit();
		}
		// Verify all required field search box and click on the submit button
		// to very that user can not submit without entering all required field.
		if (mydrivers.getPageSource().contains("New User Registration")) {
			mydrivers.findElement(By.xpath(".//*[@id='new_user']/div/div[1]/div/div[1]")).getText();
			Thread.sleep(3000);

		} else {
			System.out.println(
					"User is able to continue to the next page without filling all the required filed, Test Failed");
		}
		// Attempt clicking on the submit button and verify if all required
		// filed error out.
		mydrivers.findElement(By.xpath(".//*[@id='submit']")).click();
		Thread.sleep(4000);
		if (mydrivers.getPageSource().contains("Passphrase confirmation is required")) {
			System.out.println(
					"The submit button is made active to stimulate required filed when left emty by usser, PASS");

		} else {
			System.out.println(
					"The submit button is not active to stimulate required filed when left emty by usser, Failed");
			mydrivers.quit();
		}

		Thread.sleep(4000);

		mydrivers.findElement(By.xpath(".//*[@id='error-alert-container']/div")).click();

		Thread.sleep(4000);
		if (mydrivers.getPageSource().contains("I am not a robot' check box must be checked")) {
			System.out.println("user see error message saying '7 errors prohibited user to submit Registration'");
			Thread.sleep(4000);
			System.out.println(mydrivers.findElement(By.xpath(".//*[@id='error-alert-container']/div")).getText());

		} else {
			System.out
					.println("User did not see error message saying '7 errors prohibited user to submit Registration");
		}

		// Step 3. Verify that user is on the registration page
		if (mydrivers.getPageSource().contains("New User Registration")) {
			// Step 4. Locate the first name search box and enter a valid first
			// name.
			mydrivers.findElement(By.xpath(".//*[@id='user_first_name']")).sendKeys(FN);
			// Step 5. Locate the last name search box and enter a valid last
			// name.
			mydrivers.findElement(By.xpath(".//*[@id='user_last_name']")).sendKeys(LN);
			// Step 6. Locate the Phone Number search box and enter a valid
			// Phone Number.
			mydrivers.findElement(By.xpath(".//*[@id='user_phone_number']")).sendKeys(PN);
			// Step 7. Locate the Email search box and enter a valid Email.
			mydrivers.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
			// Step 8. Locate the Password search box and enter a valid password
			// as instructed and verify the Strength type.
			mydrivers.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(PassW);
			System.out.println(mydrivers.findElement(By.xpath(".//*[@id='text_strength']")).getText());
			mydrivers.findElement(By.xpath(".//*[@id='user_password']")).clear();
			mydrivers.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Weak_PassW);
			// Locate the Show my typing link and click on it.
			mydrivers.findElement(By.xpath(".//*[@id='new_user']/div/div[1]/div/div[1]/div[2]/div[2]/a")).click();
			Thread.sleep(3000);

			Thread.sleep(5000);
			if (mydrivers.getPageSource().contains("Weak")) {
				System.out.println("Password type is Weak, Weak password validated, Pass");
				mydrivers.findElement(By.xpath(".//*[@id='user_password']")).clear();
				Thread.sleep(2000);
				mydrivers.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Average_PassW);
				Thread.sleep(2000);
				System.out.println(mydrivers.findElement(By.xpath(".//*[@id='text_strength']")).getText());

			} else {
				System.out.println("Weak password type not validated, Failed");
				mydrivers.quit();
			}

			Thread.sleep(5000);

		} else {
			System.out.println("User is Not being navigated to the Registration Page, Failed");
			mydrivers.quit();
		}

		Thread.sleep(5000);
		// Verify stong password.
		mydrivers.findElement(By.xpath(".//*[@id='user_password']")).clear();
		Thread.sleep(2000);
		mydrivers.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(PassW);
		Thread.sleep(2000);
		if (mydrivers.getPageSource().contains("Strong")) {
			System.out.println("Password type is strong, Weak password validated, Pass");

			System.out.println(mydrivers.findElement(By.xpath(".//*[@id='text_strength']")).getText());

		} else {
			System.out.println("Weak password type not strong, Failed");
			mydrivers.quit();
		}

		Thread.sleep(4000);
		// Step 9. Verify that user sees the Terms and condition note .
		if (mydrivers.getPageSource()
				.contains("This is a Federal computer system and is the property of the United States Government")) {
			System.out.println("user sees the Privacy Act as stated bellow, Pass");
			System.out.println(mydrivers.findElement(By.xpath(".//*[@id='new_user']/div/div[2]/div[1]")).getText());
		} else {
			System.out.println("Privacy Act is not visible to user, Failed");
		}
		// Step 10. Accept the terms and condition by locating and checking the
		// box on the terms before the submission button.

		mydrivers.findElement(By.xpath(".//*[@id='new_user']/div/div[2]/div[2]/div/label")).click();
		Thread.sleep(3000);
		// Step 11. Locate the reCaptcha button and click on it to verify user.
		Thread.sleep(4000);
		// Step 12. Verify that the reCAPTCHA images are being display and
		// visible to user .
		if (mydrivers.getPageSource().contains("not a robot")) {
			System.out.println("Re-Captcha box Present, Pass.");
		} else {
			System.out.println("Re-Captcha Box Not Present, Failed");

		}
		// Step 13. Verify that that user Sees notification Saying 'an email
		// with verification link was send to users email'.

	}

	@After
	public void IAM_Custom_Registration_Vendor_Starts_New_Registration_US652_Endtest() throws Exception {
		mydrivers.quit();

	}

}
