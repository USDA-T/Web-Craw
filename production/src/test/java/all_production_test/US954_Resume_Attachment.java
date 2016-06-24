package all_production_test;

import static org.junit.Assert.assertEquals;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class US954_Resume_Attachment {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	// *************************************
	// Document Type of Resume is a visual check
	// and not verified in this script
	// *************************************
	@Before
	public void setUp() throws Exception {

		// setting up IE, Chrome, and FireFox Web Drivers
		File file = new File(Constants.IE);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File(Constants.Chrome);
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		driver = new InternetExplorerDriver();
		// driver=new ChromeDriver();
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void testSmokeLogin_Maintest() throws Exception {
		// Step 1 - Open QA site
		driver.get(Constants.baseUrl);
		System.out.println("Step 1 - URL: " + Constants.baseUrl);

		// Click on the Login link and log in
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();

		// Enter a valid Login ID
		driver.findElement(By.xpath(".//*[@id='user_email']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Constants.LLC_Login);
		System.out.println("  Login ID: " + Constants.LLC_Login);

		// Enter a valid Password
		driver.findElement(By.xpath(".//*[@id='user_password']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Constants.Password);
		System.out.println("  Password: " + Constants.Password);

		// Click the Sign-in button
		driver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		System.out.println("  Click on Login Link and log in");

		// Close pop up window
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Signed in successfully")) {
			driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("  Successful sign in alert message not present");
		}

		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		if (driver.getPageSource().contains("Draft")) {
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr/td[4]/a[2]")).click();
			Thread.sleep(2000);
			System.out.println("WOSB Certification in-progress on the dashboard was deleted");
			driver.switchTo().alert().accept();
			Thread.sleep(7000);
		} else {
			System.out.println(
					"There was no WOSB certification in-progress on the dashboard, a new certification is being created");
		}

		// Step 2 - Answer questions for WOSB application
		// Locate the Certifications on the dashboard, click on it and select
		// WOSB to continue.
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		// Locate the accept button at the bottom of the WOSB agreement and
		// click on it to continue.
		driver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
		Thread.sleep(2000);
		// Locate the 8(a) question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[5][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  8(a) question has been answered");
		// Locate the Third Party Certification, question1 and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[6][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Non-qualification, question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[8][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  Third Party questions have been answered");
		// Locate the LLC questions, select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[18][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_18_comment']")).sendKeys(Constants.comment);
		driver.findElement(By.xpath(".//*[@id='answers[19][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_19_comment']")).sendKeys(Constants.comment);
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  LLC questions have been answered");
		// Locate the Citizenship & Ownership questions, select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[20][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[21][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  Citizenship & Ownership questions have been answered");
		// Locate the Business & Trusts questions, select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[22][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[23][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  Business & Trusts questions have been answered");
		// Locate the first Operations & Management question and select No.
		driver.findElement(By.xpath(".//*[@id='answers[24][value]']/label[2]")).click();
		System.out.println("  The first Operations & Management question have been answered");

		// ********************************************
		// Start of US954
		// Verify that Non Corp applicant's don't see Corp questions

		// Step 3 - Verify that the second question needs to be answered before
		// moving on.
		System.out.println("Step 3 - Verify that the second question needs to be answered before moving on");
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		try {
			assertEquals("Please answer this question",
					driver.findElement(By.xpath(".//*[@id='answers[25][value]-error']")).getText());
			System.out.println("  Question has to be answered - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Question has to be answered - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 4 - Verify that the second question's Details for Operations &
		// Management has been updated.
		System.out.println(
				"Step 4 - Verify that the second question's Details for Operations & Management has been updated");
		try {
			assertEquals(
					"(Resume Optional) The woman must have managerial experience of the extent and complexity needed to run the concern. The woman manager need not have the technical expertise or possess the required license to be found to control the concern if she can demonstrate that she has ultimate managerial and supervisory control over those who possess the required licenses or technical expertise. However, if a man possesses the required license and has an equity interest in the concern, he may be found to control the concern.",
					driver.findElement(By.xpath(".//*[@id='collapsible-25']/div/p")).getText());
			System.out.println("  Question has been updated - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Question has been updated - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 5 - Select No and move to the Expertise & Employment screen
		System.out.println("Step 5 - Select No and move to the Expertise & Employment screen");
		driver.findElement(By.xpath(".//*[@id='answers[25][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		Thread.sleep(2000);

		// Step 6 - Verify that the Expertise & Employment screen is displayed
		System.out.println("Step 6 - Verify that the Expertise & Employment screen is displyed");
		try {
			assertEquals("Expertise & Employment",
					driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  Expertise & Employment screen is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Expertise & Employment screen is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 7 - Return to the Operations & Management screen
		System.out.println("Step 7 - Return to the Operations & Management screen");
		driver.findElement(By.xpath(".//*[@id='operations_part_3']")).click();
		try {
			assertEquals("Operations & Management",
					driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  Operation & Management screen is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Operation & Management screen is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 8 - Select Yes to the second Operation & Management question
		System.out.println("Step 8 - Select Yes to the second Operation & Management question");
		driver.findElement(By.xpath(".//*[@id='answers[25][value]']/label[1]")).click();

		// Step 9 - Verify that the Upload button is displayed
		System.out.println("Step 9 - Verify that the Upload button is displayed");
		try {
			assertEquals("Upload",
					driver.findElement(By.xpath(".//*[@id='answers[25][attachment]']/div/label")).getText());
			System.out.println("  Upload button is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Upload button is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 10 - Verify that a upload is optional by clicking the Save and
		// continue button
		System.out.println("Step 10 - Verify that a upload is optional by clicking the Save and continue button");
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		Thread.sleep(2000);
		try {
			assertEquals("Expertise & Employment",
					driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  Upload is optional, the Expertise & Employment screen is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Upload is optional, the Expertise & Employment screen is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 11 - Return to the Operations & Management screen
		System.out.println("Step 11 - Return to the Operations & Management screen");
		driver.findElement(By.xpath(".//*[@id='operations_part_3']")).click();
		try {
			assertEquals("Operations & Management",
					driver.findElement(By.xpath(".//*[@id='main-content']/div/h2")).getText());
			System.out.println("  Operation & Management screen is displayed - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Operation & Management screen is displayed - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 12 - Click the Dashboard link
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
		Thread.sleep(2000);
		System.out.println("Step 12 - Returned to the Dashboard");

		// Step 13 - Click the Logout link
		System.out.println("Step 13 - Logout link clicked");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[6]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		Thread.sleep(2000);
		System.out.println("END OF TEST");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (verificationErrorString != null) {
			System.out.println("Error: " + verificationErrorString);
		}

	}
}
