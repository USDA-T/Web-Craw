package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

public class TestMppNonApplicationVendorUserExperience extends TestCase {

	private static final Logger logger = LogManager.getLogger(TestMppNonApplicationVendorUserExperience.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 1;

	}

	@Test
	public void testMainTest() throws Exception {
		// Login to dashboard.
		String Actual_Text = null;
		String Expected_Text = null;
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		try {
			assertTrue(webDriver.getPageSource().contains("Delete"));
			webDriver.findElement(By.linkText("Delete")).click();
			//webDriver.switchTo().alert().accept();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			webDriver.navigate().refresh();
		} catch (Error e) {
			logger.info("There are(is) no programs in-progress on the dashboard, a new program is beinng created");
		}
		// Verify that if there is No active programs, users sees message ot
		// text (There are currently no programs).
		if (webDriver.getPageSource().contains("Active")) {
			logger.info("There is (are) an active Program on the dashboard");
			// Verify test for Current Certification to Current Programs:.
			Actual_Text = webDriver.findElement(By.xpath("//div[3]/p[2]/b")).getText();
			Expected_Text = "Current Programs:";
			assertEquals(Actual_Text, Expected_Text);
			String Programs;
			Programs = webDriver.findElement(By.xpath("//div[3]/p[2]")).getText();
			logger.info("Displied " + Programs);
			Actual_Text = webDriver.findElement(By.linkText("My SBA Contracting Programs")).getText();
			Expected_Text = "My SBA Contracting Programs";
			assertEquals(Actual_Text, Expected_Text);
			//Verify text on table change from Certifications to programs.
			Actual_Text = webDriver.findElement(By.cssSelector("th")).getText();
			Expected_Text = "Programs";
			assertEquals(Actual_Text, Expected_Text);
			
		} else {
			// Verify the dashboard text.
			Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
			Expected_Text = "Welcome to your certify.SBA.gov dashboard. You have successfully connected your business from SAM.gov.";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.cssSelector("div.usa-font-lead")).getText();
			Expected_Text = "To get started on an application, select which program you'd like to join below. You may only upload documents to certify.SBA.gov during the application process. While completing the online questionnaire, you will be instructed to upload documents to support your answers. These online applications replace any forms released by SBA for these contracting programs in the past.";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.xpath(".//*[@id='main-content']/section/article[1]/div[1]/div[4]/div[3]/p[2]")).getText();
			Expected_Text = "Current Programs: \nThere are currently no programs";
			assertEquals(Actual_Text, Expected_Text);
			// Verify test for Current Certification to Current Programs:.
			Actual_Text = webDriver.findElement(By.xpath("//div[3]/p[2]/b")).getText();
			Expected_Text = "Current Programs:";
			assertEquals(Actual_Text, Expected_Text);
			String Programs;
			Programs = webDriver.findElement(By.xpath("//div[3]/p[2]")).getText();
			logger.info("The Active Program display is "+ Programs);
		}
		//Verify that the top navigation link Certifications is changed to Programs.
		Actual_Text = webDriver.findElement(By.xpath("//li[2]/a")).getText();
		Expected_Text = "Programs";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.xpath("//li[2]/a")).click();
		//Verify and validate all changes made to the programs navigated page.
		if (webDriver.getPageSource().contains("Active")) {
			logger.info("There is (are) an active Program on the dashboard");
			Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
			Expected_Text = "My SBA Contracting Programs";
			assertEquals(Actual_Text, Expected_Text);
			String Programs;
			Programs = webDriver.findElement(By.cssSelector("td > a")).getText();
			logger.info("Active Displied Program is " + Programs);
			Actual_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/div/section/article/h1[2]")).getText();
			Expected_Text = "Join a new program";
			assertEquals(Actual_Text, Expected_Text);
			//Verify text on table change from Certifications to programs.
			Actual_Text = webDriver.findElement(By.cssSelector("th")).getText();
			Expected_Text = "Programs";
			assertEquals(Actual_Text, Expected_Text);
			
		} else {
			// Verify the dashboard text.
			Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
			Expected_Text = "My SBA Contracting Programs";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.cssSelector("span")).getText();
			Expected_Text = "There are currently no programs";
			assertEquals(Actual_Text, Expected_Text);
			Actual_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/div/section/article/h1[2]")).getText();
			Expected_Text = "Join a new program";
			assertEquals(Actual_Text, Expected_Text);
		}
		//Click on the Business nav and select Manage employee access to verify test Certification changed to programs.
		webDriver.findElement(By.linkText("Business")).click();
		webDriver.findElement(By.linkText("Manage employee access")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
		Expected_Text = "You may accept, reject, or revoke employee access to your business's certifications in certify.SBA.gov here. Employees will have access to all documents and certifications associated with this DUNS number.";
		assertEquals(Actual_Text, Expected_Text);
		//Logout.
		webDriver.findElement(By.linkText("Logout")).click();
		Thread.sleep(3000);
		//Verify that all text, certification on Home page is being changed to Programs.
		Actual_Text = webDriver.findElement(By.cssSelector("div.usa-font-lead")).getText();
		Expected_Text = "The U.S. Small Business Administration (SBA) is working to modernize the application process for federal contracting programs. Manage your application and eligibility documentation for the WOSB and All Small Mentor-Protégé programs online from our easy-to-use dashboard. Get started today!";
		assertEquals(Actual_Text, Expected_Text);
		//New Features.
		Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-one-third > p")).getText();
		Expected_Text = "Experience a unified SBA contracting program certification process and complete SBA forms directly online. Upload and manage your documents across multiple programs and receive email notifications such as expiration and renewal notices.";
		assertEquals(Actual_Text, Expected_Text);
		//Am I Eligible.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='homepage']/div[3]/div[2]/p")).getText();
		Expected_Text = "Answer a few questions to discover if your company should pursue participation in SBA’s contracting programs. Are you eligible to join the Woman Owned Small Business (WOSB) Program, HUBZone Program, or 8(a) Business Development Program? Find out";
		assertEquals(Actual_Text, Expected_Text);
		//Upcoming Activities.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='homepage']/div[3]/div[3]/p")).getText();
		Expected_Text = "New programs will soon be incorporated onto certify.SBA.gov including the 8(a) Business Development Program (Winter 2017), HUBZone Program (Spring 2017) and Dynamic Small Business Search (DSBS) (Summer 2017).";
		assertEquals(Actual_Text, Expected_Text);
		//Footer text.
		Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-one-whole.currently-grey-box > p")).getText();
		Expected_Text = "Currently, this website is available for the Women-Owned Small Business (WOSB) and All Small Mentor Protégé programs only. For the 8(a) Business Development and HUBZone programs, please continue to use the SBA General Login System (GLS) to manage your certifications.";
		assertEquals(Actual_Text, Expected_Text);
		//Click on the Am I Eligible link.
		webDriver.findElement(By.linkText("Am I eligible?")).click();
		//Verify opening text: words Certification has been updated to programs.
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Is there an SBA Contracting Program for me?";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("p.lead-para")).getText();
		Expected_Text = "The Federal Government is the largest buyer of products and services in the US. The SBA has a variety of programs to help small businesses compete for these federal contracts. Is your business eligible for one of our contracting programs?";
		assertEquals(Actual_Text, Expected_Text);
		//8(a).
		Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-one-third.grey-box-text > p")).getText();
		Expected_Text = "The 8(a) Business Development (BD) Program offers a broad scope of assistance to firms that are owned and controlled at least 51% socially and economically disadvantaged individual(s).";
		assertEquals(Actual_Text, Expected_Text);
		//WOSB.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='am-i']/div[2]/div[2]/p")).getText();
		Expected_Text = "The Women-Owned Small Business (WOSB) Federal Contract Program allows set-asides for WOSBs in industries where firms are underrepresented. WOSBs must be at least 51% owned and controlled by women.";
		assertEquals(Actual_Text, Expected_Text);
		//HOBZone.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='am-i']/div[2]/div[3]/p")).getText();
		Expected_Text = "The Historically Underutilized Business Zone (HUBzone) Program allows federal contract set-asides for small businesses in economically depressed communities.";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
		Expected_Text = "Answer the questions below to find out if your firm may meet criteria to participate in one of these programs.";
		assertEquals(Actual_Text, Expected_Text);
		//Click on the Home button.
		webDriver.findElement(By.linkText("Home")).click();
		Thread.sleep(3000);
		//Click on the Prepare link.
		webDriver.findElement(By.linkText("Prepare")).click();
		//Verify the opening message, text Certification is being updated to Programs.
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Prepare for SBA small business programs:";
		assertEquals(Actual_Text, Expected_Text);
		//Link 3(8(a)),4(HOPZone),5(MPP).
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='prep-for-cert']/div/p[5]/a/b")).getText();
		Expected_Text = "8(a) Business Development (BD) Program Preparation Checklist";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='prep-for-cert']/div/p[6]/a/b")).getText();
		Expected_Text = "Historically Underutilized Business Zones (HUBZone) Program Preparation Checklist";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='prep-for-cert']/div/p[7]/a/b")).getText();
		Expected_Text = "All Small Mentor-Protégé Program Preparation Checklist";
		assertEquals(Actual_Text, Expected_Text);
		//8(a).
		Actual_Text = webDriver.findElement(By.id("eighta-anc")).getText();
		Expected_Text = "8(a) Business Development (BD) Program Preparation Checklist";
		assertEquals(Actual_Text, Expected_Text);
		//HOBZone.
		Actual_Text = webDriver.findElement(By.id("hubzone-anc")).getText();
		Expected_Text = "Historically Underutilized Business Zones (HUBZone) Program Preparation Checklist";
		assertEquals(Actual_Text, Expected_Text);
		//MPP.
		Actual_Text = webDriver.findElement(By.id("mpp-anc")).getText();
		Expected_Text = "All Small Mentor-Protégé Program Preparation Checklist";
		assertEquals(Actual_Text, Expected_Text);
		//Click on the home button.
		webDriver.findElement(By.linkText("Home")).click();
		//Login as a CO.
		get_The_Row_From_Login_Data = 6;
		LoginPageWithReference1 login_Data1 = new LoginPageWithReference1(webDriver, get_The_Row_From_Login_Data);
		login_Data1.Login_With_Reference();
		Thread.sleep(3000);
		//Verify if the CO account has a role, if not request a co role for the account.
		if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
			logger.info("The CO Account has no role, new CO role is requested.");
			Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
			Expected_Text = "If you are a federal contracting officer or contracting specialist please request access to the system by selecting the role below.";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.id("role_name_contracting_officer")).click();
			webDriver.findElement(By.name("commit")).click();
			//Click on the My Request Link.
			webDriver.findElement(By.linkText("My requests")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Review vendor certifications";
			assertEquals(Actual_Text, Expected_Text);
			//click on the logout button.
			webDriver.findElement(By.linkText("Logout")).click();
		} else {
			//Click on the My Request Link.
			webDriver.findElement(By.linkText("My requests")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Review vendor certifications";
			assertEquals(Actual_Text, Expected_Text);
			//click on the logout button.
			webDriver.findElement(By.linkText("Logout")).click();
		}
		logger.info("Success");
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
