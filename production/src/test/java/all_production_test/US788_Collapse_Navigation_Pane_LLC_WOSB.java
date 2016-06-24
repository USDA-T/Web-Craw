package all_production_test;

import java.io.File;
//import java.util.List;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.support.ui.Select;

public class US788_Collapse_Navigation_Pane_LLC_WOSB {
	private WebDriver driver;
	String baseUrl;

	String Login;
	String Password;
	String comment;

	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		Login = "donald.patti@cedarpointconsulting.com";
		Password = "They call me Ishmael";
		comment = "Test";

		// QA URL
		baseUrl = "https://certify.sba.gov/";
		// DEMO URL
		// baseUrl = "http://demo.qa.sba-one.net/";

		// setting up IE, Chrome, and FireFox Web Drivers
		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}

	@Test
	public void testSmokeLogin() throws Exception {
		// Step 1 - Open QA site
		driver.get(baseUrl);
		System.out.println("Step 1 - URL: " + baseUrl);

		// Click on the Login link and log in
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='gov_login_box']/form[1]/button")).click();

		// Enter a valid Login ID
		driver.findElement(By.xpath(".//*[@id='user_email']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Login);
		System.out.println("  Login ID: " + Login);

		// Enter a valid Password
		driver.findElement(By.xpath(".//*[@id='user_password']")).clear();
		driver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Password);
		System.out.println("  Password: " + Password);

		// Click the Sign-in button
		driver.findElement(By.xpath(".//*[@id='business_signin']")).click();
		System.out.println("  Click on Login Link and log in");

		// Close pop up window
		Thread.sleep(3000);
		if (driver.getPageSource().contains("Signed in successfully")) {
			driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("  Successful sign in alert message not present");
		}

		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		if (driver.getPageSource().contains("Draft")) {
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr[1]/td[4]/a[2]"))
					.click();
			System.out.println("Certification in-progress on the dashboard was deleted");
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.navigate().refresh();
		} else {
			System.out.println(
					"There are(is) no certification in-progress on the dashboard, a new certification is being created");
		}

		// Step 2 - Answer questions for WOSB application
		// Locate the Certifications on the dashboard, click on it and select
		// WOSB to continue.
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		// Locate the accept button at the bottom of the WOSB agreement and
		// click on it to continue.
		driver.findElement(By.xpath(".//*[@id='new_sba_application']/input[3]")).click();
		Thread.sleep(2000);
		// Locate the 8(a) question and select No and continue.
		driver.findElement(By.xpath(".//*[@id='answers[5][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  8(a) question have been answered");
		// Locate the Third Party Certification, question1 and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[6][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Third Party Certification, question3 and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[8][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  Third Party questions have been answered");
		// Locate the Business Corporation and LLC questions and continue.

		// answer No radio button for the 1st LLC question
		driver.findElement(By.xpath(".//*[@id='answers[18][value]']/label[2]")).click();
		// add comment
		driver.findElement(By.xpath(".//*[@id='answers_18_comment']")).sendKeys(comment);
		// click the No radio button for the 2nd LLC question
		driver.findElement(By.xpath(".//*[@id='answers[19][value]']/label[2]")).click();
		// add comment
		driver.findElement(By.xpath(".//*[@id='answers_19_comment']")).sendKeys(comment);
		// click the Continue button
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  Business questions have been answered");

		// Locate the Operations Part1, question 1and2, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[20][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[21][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Operations Part2, question 3and4, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[22][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[23][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Operations Part3, question 5and6, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[24][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[25][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Operations Part4, question 7and8, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[26][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[27][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Operations Part5, question 9and10, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[28][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[29][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		// Locate the Operations Part6, question 11and12, and select No and
		// continue.
		driver.findElement(By.xpath(".//*[@id='answers[30][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers[31][value]']/label[2]")).click();
		driver.findElement(By.xpath(".//*[@id='answers_31_comment']")).sendKeys(comment);
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();
		System.out.println("  Operations questions have been answered");

		// Step 3 - Click the Accept button on the Review page
		System.out.println("Step 3 - Click the Accept button on the Review page");
		driver.findElement(By.xpath(".//*[@id='wosb']/input[4]")).click();

		// Step 4 - Click the Dashboard link
		System.out.println("Step 4 - Returned to the Dashboard");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
		Thread.sleep(2000);

		// Step 5 - Return to the certification
		System.out.println("Step 5 - Return to the certification");
		if (driver.getPageSource().contains("Draft")) {
			driver.findElement(By.xpath(".//*[@id='dashboard-index']/div[4]/div[2]/table/tbody/tr[1]/td[4]/a[1]"))
					.click();
			System.out.println("Certification in-progress on the dashboard was clicked");
			Thread.sleep(4000);
		}

		// Step 6 - Verify that user is navigated to the Correct
		// section(Signature).
		System.out.println("Step 6 - Verify that user is navigated to the Correct section(Other Sources Of Income)");
		try {
			assertEquals("Signature", driver.findElement(By.xpath(".//*[@id='main-content']/h2")).getText());
			System.out.println("  User is navigated to the Correct section, (Signature) - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  User is NOT being navigated to the Correct section, (Signature) - <<FAILED>>");
			driver.quit();
		}

		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Start of US788

		System.out.println("");
		System.out.println("--Expand sections--");

		// Step 7 - Click to expand the Prior Certification section
		System.out.println("Step 7 - Click to expand the Prior Certification section");
		driver.findElement(By.xpath(".//*[@id='pc']")).click();

		// Step 8 - Verify that the Prior Certification section was expanded
		System.out.println("Step 8 - Verify that the Prior Certification section was expanded");

		try {
			assertEquals("8(a)", driver.findElement(By.xpath(".//*[@id='8a']")).getText());
			System.out.println("  Prior Certification section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Prior Certification section expanded - <<FAILED>>");
			driver.quit();
		}

		// Step 9 - Click to expand the Third Party Certification section
		System.out.println("Step 9 - Click to expand the Third Party Certification section");
		driver.findElement(By.xpath(".//*[@id='third_party_cert']")).click();

		// Step 10 - Verify that the Third Party Certification section was
		// expanded
		System.out.println("Step 10 - Verify that the Third Party Certification section was expanded");
		try {
			assertEquals("Third Party", driver.findElement(By.xpath(".//*[@id='third_party_cert_part_1']")).getText());
			System.out.println("  Third Party section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Third Party section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 11 - Click to expand the Business section
		System.out.println("Step 11 - Click to expand the Business section");
		driver.findElement(By.xpath(".//*[@id='business']")).click();

		// Step 12 - Verify that the Business section was expanded
		System.out.println("Step 12 - Verify that the Business section was expanded");
		try {
			assertEquals("LLC", driver.findElement(By.xpath(".//*[@id='llc']")).getText());
			System.out.println("  Business section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Business section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		// Step 13 - Click to expand the Operations section
		System.out.println("Step 13 - Click to expand the Operations section");
		driver.findElement(By.xpath(".//*[@id='operations']")).click();

		// Step 14 - Verify that the Operations section was expanded
		System.out.println("Step 14 - Verify that the Operations section was expanded");
		try {
			assertEquals("Citizenship & Ownership",
					driver.findElement(By.xpath(".//*[@id='operations_part_1']")).getText());
			System.out.println("  Operations section expanded - Pass");
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Operations section expanded - <<FAILED>>");
			driver.quit();
		}
		Thread.sleep(2000);

		System.out.println("");
		System.out.println("--Collapse sections--");

		// Step 15 - Click to collapse the Third Party Certification section
		System.out.println("Step 15 - Click to collapse the Third Party Certification section");
		driver.findElement(By.xpath(".//*[@id='third_party_cert']")).click();

		// Step 16 - Verify that the Third Party Certification section was
		// collapsed
		System.out.println("Step 16 - Verify that the Third Party Certification section was collapsed");
		try {
			assertEquals("Third Party", driver.findElement(By.xpath(".//*[@id='third_party_cert_part_1']")).getText());
			System.out.println("  Third Party section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Third Party section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 17 - Click to collapse the Prior Certification section
		System.out.println("Step 17 - Click to collapse the Prior Certification section");
		driver.findElement(By.xpath(".//*[@id='pc']")).click();

		// Step 18 - Verify that the Prior Certification section was collapsed
		System.out.println("Step 18 - Verify that the Prior Certification section was collapsed");

		try {
			assertEquals("8 (a)", driver.findElement(By.xpath(".//*[@id='8a']")).getText());
			System.out.println("  Prior Certification section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Prior Certification section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 19 - Click to collapse the Business section
		System.out.println("Step 19 - Click to collapse the Business section");
		driver.findElement(By.xpath(".//*[@id='business']")).click();

		// Step 20 - Verify that the Business section was collapsed
		System.out.println("Step 20 - Verify that the Business section was collapsed");
		try {
			assertEquals("LLC", driver.findElement(By.xpath(".//*[@id='llc']")).getText());
			System.out.println("  Business section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Business section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 21 - Click to collapse the Operations section
		System.out.println("Step 21 - Click to collapse the Operations section");
		driver.findElement(By.xpath(".//*[@id='operations']")).click();

		// Step 22 - Verify that the Operations section was collapsed
		System.out.println("Step 22 - Verify that the Operations section was collapsed");
		try {
			assertEquals("Citizenship & Ownership",
					driver.findElement(By.xpath(".//*[@id='operations_part_1']")).getText());
			System.out.println("  Operations section collapsed - <<FAILED>>");
			driver.quit();
		} catch (Error e) {
			verificationErrors.append(e.toString());
			System.out.println("  Operations section collapsed - Pass");
		}
		Thread.sleep(2000);

		// Step 23 - Click the Dashboard link
		System.out.println("Step 23 - Returned to the Dashboard");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[1]/a")).click();
		Thread.sleep(2000);

		// Step 24 - Click the Logout link
		System.out.println("Step 24 - Logout link clicked");
		driver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[6]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='labelid']")).click();
		Thread.sleep(3000);
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
