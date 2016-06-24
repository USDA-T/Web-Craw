package all_production_test;

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

public class US880_User_Support_Passphrase_visible_when_creating_account_or_resetting_my_passphrase {
	public WebDriver mydriver;
	String myurl;
	String Email;
	String PassW;
	String Passphrass;
	String FN;
	String LN;
	String PN;
	String Re_PassW;

	@Before
	public void US880_User_Support_Passphrase_visible_when_creating_account_or_resetting_my_passphrase_Setup()
			throws Exception {
		myurl = "https://certify.sba.gov/";
		Email = "donald.patti@cedarpointconsulting.com";
		PassW = "They call me Ishmael";
		Passphrass = "The Quick Brown Fox Jumps Over The Lazy Dog";

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
	public void US880_User_Support_Passphrase_visible_when_creating_account_or_resetting_my_passphrase_Maintest()
			throws Exception {
		// Open fireFox, Chrome or IE and navigate to to to the certify.sba.gov
		// landing page.
		mydriver.navigate().to(myurl);
		mydriver.manage().window().maximize();
		// locate the create account button and click on it.
		assertElementPresent("Get started",
				mydriver.findElement(By.xpath("//div[@id='gov_login_box']/form[2]/button")));
		mydriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[2]/button")).click();
		Thread.sleep(4000);
		// verify that the word Password on the password search box has been
		// changed to Passphrase.
		assertElementPresent("Passphrase", mydriver
				.findElement(By.cssSelector("div.usa-width-one-third.xregs-tooltip-pass > h4.usa-heading-alt")));
		// Verify Enter email and password has been changed to enter email and
		// passphrase.
		assertElementPresent("Enter Email and Passphrase", mydriver
				.findElement(By.cssSelector("div.usa-width-one-half.right_box_noborder_right > h4.usa-heading-alt")));
		// Verify password name text has been changed to passphrase.
		assertElementPresent("Passphrase*",
				mydriver.findElement(By.xpath("//form[@id='new_user']/div/div/div[2]/div/div[2]/div[2]/label")));
		// Verify Confirm password name text has been changed to confirm
		// passphrase.
		assertElementPresent("Confirm Passphrase*",
				mydriver.findElement(By.xpath("//form[@id='new_user']/div/div/div[2]/div/div[2]/div[4]/label")));

		// Verify that a link (Show my typing) is been created for users to view
		// their password when typing.
		assertElementPresent("Show my typing", mydriver.findElement(By.linkText("Show my typing")));

		// Locate the Passphrase search boxes and enter a passphrase.
		mydriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Passphrass);
		mydriver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).sendKeys(Passphrass);
		Thread.sleep(4000);
		// Locate the Show my typing link and click on it.
		mydriver.findElement(By.linkText("Show my typing")).click();
		Thread.sleep(3000);
		// Verify that the link 'Hide my typing' is activated and click on it to
		// hide passphrase.
		assertElementPresent("Hide my typing", mydriver.findElement(By.linkText("Hide my typing")));

		mydriver.findElement(By.linkText("Hide my typing")).click();

		System.out.println(mydriver.findElement(By.xpath(".//*[@id='user_password']")).getText());

		// Verify that the passphrase entered matches the display passphrase.
		if (mydriver.getPageSource().contains("The Quick Brown Fox Jumps")) {
			System.out.println(
					"The passphrass entered successfully matches the display passphrass, 'show my typing' link is active, Failed");

		} else {
			System.out.println(
					"The passphrass entered  successfully matches the display passphrass, 'show my typing' link is active, Pass");

		}

		// Locate the home button and click on it.
		mydriver.navigate().back();
		Thread.sleep(4000);
		// Locate the login button and click on it.
		mydriver.findElement(By.cssSelector("button.usa-button.hp-login-btn")).click();

		Thread.sleep(2000);
		String actual_Text4 = mydriver.findElement(By.xpath(".//*[@id='hp']/div[2]/div[1]/div/span")).getText();
		String expected_Text4 = "Sign in to certify.SBA.gov";
		assertEquals(actual_Text4, expected_Text4);
		// Login to dashboard.
		ProdloginPage prodlogin = new ProdloginPage(mydriver);
		prodlogin.Prodlogin();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(3000);
		// Locate the my profile button and click on it.
		mydriver.findElement(By.linkText("My Profile")).click();
		// Verify and click on the link Edit Password.
		String actual_Text = mydriver.findElement(By.linkText("Edit Passphrase")).getText();
		String expected_Text = "Edit Passphrase";
		assertEquals(actual_Text, expected_Text);
		mydriver.findElement(By.linkText("Edit Passphrase")).click();

		// Verify if the Edit password text on the header of the edit passphrase
		// page has been updated to Edit Passphrase.
		String actual_Text1 = mydriver.findElement(By.cssSelector("h2.usa-heading")).getText();
		String expected_Text1 = "Edit Passphrase";
		assertEquals(actual_Text1, expected_Text1);
		// Verify if the Existing password search boxes has been updated to
		// Existing passphrase..
		Thread.sleep(3000);
		String actual_Text2 = mydriver.findElement(By.cssSelector("div.field > label")).getText();
		String expected_Text2 = "Existing passphrase";
		assertEquals(actual_Text2, expected_Text2);
		// Verify if the New password confirmation search boxes has been updated
		// to Existing New passphrase confirmation.
		String actual_Text3 = mydriver.findElement(By.xpath("//form[@id='edit_user']/div[4]/label")).getText();
		String expected_Text3 = "New passphrase confirmation";
		assertEquals(actual_Text3, expected_Text3);

		// Locate the the new passphrase search boxes and enter a passphrase.
		mydriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Passphrass);
		mydriver.findElement(By.xpath(".//*[@id='user_password_confirmation']")).sendKeys(Passphrass);
		Thread.sleep(4000);
		// Verify that a link (Show my typing) is been created for users to view
		// their password when typing.
		assertElementPresent("Show my typing", mydriver.findElement(By.linkText("Show my typing")));
		// Locate the Show my typing link and click on it.
		mydriver.findElement(By.linkText("Show my typing")).click();
		Thread.sleep(3000);
		// Verify that the link 'Hide my typing' is activated and click on it to
		// hide passphrase.
		assertElementPresent("Hide my typing", mydriver.findElement(By.linkText("Hide my typing")));
		// click on it to hide passphrase.
		mydriver.findElement(By.linkText("Hide my typing")).click();

		// Locate the logout button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[6]/a")).click();
		Thread.sleep(5000);
		if (mydriver.getPageSource().contains("Signed out successfully")) {
			System.out.println("Signed out was successfull, Pass");
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();

		} else {
			System.out.println("Signed out successfull pop up alert not activated, Failed");

		}
		// Click on the login button.
		mydriver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();
		// Verify if the Forgot your password text on the sign-in page has been
		// updated to Forgot your passphrase.
		Thread.sleep(3000);
		String actual_Text5 = mydriver.findElement(By.linkText("Forgot your passphrase?")).getText();
		String expected_Text5 = "Forgot your passphrase?";
		assertEquals(actual_Text5, expected_Text5);
		// Click on the Forget password link.
		mydriver.findElement(By.linkText("Forgot your passphrase?")).click();
		// Verify that all the word password on the forget your passphrase page
		// are all updated to passphrase.
		Thread.sleep(3000);
		String actual_Text6 = mydriver.findElement(By.cssSelector("span.xusa-heading")).getText();
		String expected_Text6 = "Forgot Passphrase";
		assertEquals(actual_Text6, expected_Text6);
		assertValue("Send reset passphrase instructions", mydriver.findElement(By.name("commit")));

		// click on the home button
		mydriver.findElement(By.linkText("Home")).click();

	}

	private void assertValue(String string, WebElement findElement) {
		// TODO Auto-generated method stub

	}

	private void assertElementPresent(String string, WebElement findElement) {
		// TODO Auto-generated method stub

	}

	@After
	public void US880_User_Support_Passphrase_visible_when_creating_account_or_resetting_my_passphrase_Endtest()
			throws Exception {
		mydriver.quit();

	}

}
