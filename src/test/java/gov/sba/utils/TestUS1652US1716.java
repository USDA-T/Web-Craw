package gov.sba.utils;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.TestCase;

public class TestUS1652US1716 extends TestCase {
	private static WebDriver webDriver;
	private static final Logger logger = LogManager.getLogger(TestUS1652US1716.class.getName());
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {

		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 24;
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void testMainTest() throws Exception {
		String Actual_Text = null;
		String Expected_Text = null;
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		logger.info("Versioning: Analyst views a past 'read-only' revision of a review");
		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
		deleteDraftCert.DeleteDraftCert();
		// Verify for active and Draft certification on the dashboard, if draft
		// delete and start a new EDWOSB certification.
		Thread.sleep(4000);
		AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
		addOrStartCertification.AddOrStartCertification();
		// Start new third party application.
		ThirdPartyCertPage thirdPartyCert = new ThirdPartyCertPage(webDriver);
		thirdPartyCert.ThirdPartyCert();
		// Logout and login as an analyst.
		webDriver.findElement(By.linkText("Logout")).click();
		get_The_Row_From_Login_Data = 31;
		LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data1.Login_With_Reference();
		// Search for the business with submitted certification.
		webDriver.findElement(By.id("query")).sendKeys("129913885");
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		webDriver.findElement(By.linkText("Entity 412 Legal Business Name")).click();
		Thread.sleep(6000);
		// Verify the fist version alert.
		webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Revision history")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
		Expected_Text = webDriver
				.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/table/tbody/tr/td[4]")).getText();
		String[] Actual_Text0 = Actual_Text.split(" ");
		String part0 = Actual_Text0[4]; // Submitted Date
		assertEquals(part0, Expected_Text);
		logger.info(part0);
		webDriver.findElement(By.linkText("Case overview")).click();
		Thread.sleep(5000);
		WebElement AlertV1 = webDriver.findElement(By.cssSelector("p.usa-alert-text"));
		HighLight.highLightElement(webDriver, AlertV1);
		// Return certification to vendor for second scenario
		webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();
		// Locate and click on the return to vendor link.
		webDriver.findElement(By.linkText("Return to Vendor")).click();
		webDriver.switchTo().alert().accept();
		Thread.sleep(3000);
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "A new application has been reopenned for the vendor";
		assertEquals(Actual_Text, Expected_Text);
		// Logout and login as the vendor with the clone certification and
		// re-submit.
		webDriver.findElement(By.linkText("Logout")).click();
		// login as the vendor.
		get_The_Row_From_Login_Data = 24;
		LoginPageWithReference login_Data11 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data11.Login_With_Reference();
		Thread.sleep(3000);
		//Vendor dashboard.
		Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
		Expected_Text = "Draft";
		assertEquals(Actual_Text, Expected_Text);
		WebElement Draft1 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
		HighLight.highLightElement(webDriver, Draft1);
		webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		Expected_Text = "Third Party";
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		Expected_Text = "Changes in Eligibility";
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		Expected_Text = "Review";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.name("commit")).click();
		webDriver.switchTo().alert().accept();
		logger.info("Step 11 - Click to accept the statements");
		webDriver.findElement(By.id("legal_0")).click();
		webDriver.findElement(By.id("legal_1")).click();
		webDriver.findElement(By.id("legal_2")).click();
		webDriver.findElement(By.id("legal_3")).click();
		webDriver.findElement(By.id("legal_4")).click();
		webDriver.findElement(By.id("legal_5")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id("accept-button")).click();
		Thread.sleep(3000);
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "Your application has been submitted";
		assertEquals(Actual_Text, Expected_Text);
		WebElement RESUBMIT = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
		HighLight.highLightElement(webDriver, RESUBMIT);
		// Logout and login as an analyst.
		webDriver.findElement(By.linkText("Logout")).click();
		get_The_Row_From_Login_Data = 0;
		LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data2.Login_With_Reference();
		// Search for the business with submitted certification.
		webDriver.findElement(By.id("query")).sendKeys("129913885");
		webDriver.findElement(By.xpath("//button[@type='submit']")).click();
		webDriver.findElement(By.linkText("Entity 412 Legal Business Name")).click();
		Thread.sleep(6000);
		// Verify the fist version alert.
		webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Revision history")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
		Expected_Text = webDriver.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/table/tbody/tr/td[4]")).getText();
		String[] Actual_Text1 = Actual_Text.split(" ");
		String part1 = Actual_Text1[4]; // Submitted Date
		assertEquals(part1, Expected_Text);
		logger.info(part1);
		webDriver.findElement(By.linkText("Case overview")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "You are currently viewing the most recent version of this application";
		assertEquals(Actual_Text, Expected_Text);
		WebElement V2 = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
		HighLight.highLightElement(webDriver, V2);
		Thread.sleep(5000);
		WebElement AlertV2 = webDriver.findElement(By.cssSelector("p.usa-alert-text"));
		HighLight.highLightElement(webDriver, AlertV2);
		// Take screenshot and store as a file format
		ScreenShotPage1 screenShot = new ScreenShotPage1(webDriver);
		screenShot.ScreenShot();
		Thread.sleep(4000);
		// Verify the previous Version1 with no review before return and with no
		// left navigation.
		webDriver.findElement(By.linkText("Revision history")).click();
		Actual_Text = webDriver.findElement(By.linkText("Version 1")).getText();
		Expected_Text = "Version 1";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.linkText("View previous version")).getText();
		Expected_Text = "View previous version";
		assertEquals(Actual_Text, Expected_Text);
		WebElement Vs1 = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
		HighLight.highLightElement(webDriver, Vs1);
		Thread.sleep(5000);
		WebElement AlertVs1 = webDriver.findElement(By.cssSelector("p.usa-alert-text"));
		HighLight.highLightElement(webDriver, AlertVs1);
		Thread.sleep(2000);
		// Take screenshot and store as a file format
		ScreenShotPage screenShot1 = new ScreenShotPage(webDriver);
		screenShot1.ScreenShot();
		// Click to view previous version.
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String main_window = webDriver.getWindowHandle();
		logger.info("Before switching, title is = certify.sba.gov");
		webDriver.findElement(By.linkText("View previous version")).click();
		Thread.sleep(5000);
		assertEquals(Actual_Text, Expected_Text);
		java.util.Set<String> S1 = webDriver.getWindowHandles();
		Iterator<String> i1 = S1.iterator();
		while (i1.hasNext()) {
			String Second_window = i1.next();
			if (!main_window.equalsIgnoreCase(Second_window)) {
				webDriver.switchTo().window(main_window);
				webDriver.manage().window().maximize();
				Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
				Expected_Text = "You are in view-only mode (Version #1)";
				assertEquals(Actual_Text, Expected_Text);
				Thread.sleep(5000);
				WebElement ViewOnly = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
				HighLight.highLightElement(webDriver, ViewOnly);
				// Take screenshot and store as a file format
				ScreenShotPage screenShot3 = new ScreenShotPage(webDriver);
				screenShot3.ScreenShot();
				Thread.sleep(3000);
				webDriver.close();
				webDriver.switchTo().window(Second_window);
				logger.info("V1 in a view-only mode with no side nav");
				logger.info("Back to manin_window = certify.sba.gov");
			} else {
				logger.info("Second Window is thesame as first window");

			}
			// Start a review. Click on the start a review button to begin
			// second scenario with review.
			webDriver.findElement(By.linkText("Case overview")).click();
			webDriver.findElement(By.id("submit_button")).click();
			Thread.sleep(4000);
			// Verify and click on the question review to start.
			webDriver.findElement(By.linkText("Question review")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
			Expected_Text = "Question review";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.id("assessments__status")).click();
			webDriver.findElement(By.xpath("//option[@value='Confirmed']")).click();
			// Click on add a note.
			webDriver.findElement(By.xpath("//a[contains(text(),'Add a note')]")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.id("assessments__note_body")).sendKeys(
					"Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold");
			// Verify Third Party section.
			Actual_Text = webDriver.findElement(By.xpath("//div[@id='question-review']/div[2]/div[3]/h2")).getText();
			Expected_Text = "Third Party";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[2]")).click();
			webDriver.findElement(By.xpath("(//option[@value='Confirmed'])[2]")).click();
			Thread.sleep(4000);
			// Click on add a note.
			webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[2]")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[2]")).sendKeys(
					"Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold");
			// Verify Changes in Eligibility section.
			Actual_Text = webDriver.findElement(By.xpath("//div[@id='question-review']/div[2]/div[4]/h2")).getText();
			Expected_Text = "Changes in Eligibility";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.xpath("(//select[@id='assessments__status'])[3]")).click();
			webDriver.findElement(By.xpath("(//option[@value='Confirmed'])[3]")).click();
			Thread.sleep(4000);
			// Click on add a note.
			webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[3]")).click();
			Thread.sleep(2000);
			webDriver.findElement(By.xpath("(//a[contains(text(),'Add a note')])[3]")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.xpath("(//textarea[@id='assessments__note_body'])[3]")).sendKeys(
					"Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold");
			// click on the save and continue button.
			webDriver.findElement(By.id("save_notes")).click();
			// Verify that assessment has been saved.
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "Your assessments have been saved";
			assertEquals(Actual_Text, Expected_Text);
			// click on the Signature review to asses.
			webDriver.findElement(By.linkText("Signature review")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
			Expected_Text = "Signature review";
			assertEquals(Actual_Text, Expected_Text);
			// Click on add a note.
			webDriver.findElement(By.xpath("//a[contains(text(),'Add a note')]")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.name("assessment[note_body]")).sendKeys(
					"Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold");
			// click on the save and continue button.
			Thread.sleep(3000);
			webDriver.findElement(By.name("commit")).click();
			// Verify that assessment has been saved.
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "Your assessment has been saved";
			assertEquals(Actual_Text, Expected_Text);
			// Verify that Notes are being saved.
			webDriver.findElement(By.linkText("Question review")).click();
			webDriver.findElement(By.linkText("Show more")).click();
			Thread.sleep(3000);
			Actual_Text = webDriver.findElement(By.cssSelector("#assessments_long_65 > p")).getText();
			Expected_Text = "Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold";
			assertEquals(Actual_Text, Expected_Text);
			// On Determination, select Return for Modification.
			webDriver.findElement(By.linkText("Determination")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
			Expected_Text = "Determination";
			assertEquals(Actual_Text, Expected_Text);
			// on status select Return for Modification.
			webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
			// add a note.
			webDriver.findElement(By.name("assessment[note_body]")).sendKeys(
					"Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold");
			// click on the save and continue button.
			webDriver.findElement(By.name("commit")).click();
			Thread.sleep(4000);
			// Verify if the Analyst was able to do determination.
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "You can view the vendor's record but can not make edits";
			assertEquals(Actual_Text, Expected_Text);
			logger.info("Analyst successfully do a determination as Return for Mordification.");
			Thread.sleep(3000);
			webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();

			// Logout and login as the vendor with the clone certification and
			// re-submit.
			webDriver.findElement(By.linkText("Logout")).click();
			// login as the vendor.
			get_The_Row_From_Login_Data = 24;
			LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data4.Login_With_Reference();
			Thread.sleep(3000);
			//Vendor dashboard.
			Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
			Expected_Text = "Draft";
			assertEquals(Actual_Text, Expected_Text);
			WebElement Draft2 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
			HighLight.highLightElement(webDriver, Draft2);
			webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
			Thread.sleep(2000);
			webDriver.findElement(By.name("commit")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Third Party";
			webDriver.findElement(By.name("commit")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Changes in Eligibility";
			webDriver.findElement(By.name("commit")).click();
			Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			Expected_Text = "Review";
			assertEquals(Actual_Text, Expected_Text);
			webDriver.findElement(By.name("commit")).click();
			webDriver.switchTo().alert().accept();
			logger.info("Step 11 - Click to accept the statements");
			webDriver.findElement(By.id("legal_0")).click();
			webDriver.findElement(By.id("legal_1")).click();
			webDriver.findElement(By.id("legal_2")).click();
			webDriver.findElement(By.id("legal_3")).click();
			webDriver.findElement(By.id("legal_4")).click();
			webDriver.findElement(By.id("legal_5")).click();
			Thread.sleep(2000);
			webDriver.findElement(By.id("accept-button")).click();
			Thread.sleep(3000);
			Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			Expected_Text = "Your application has been submitted";
			assertEquals(Actual_Text, Expected_Text);
			WebElement RESUBMIT2 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
			HighLight.highLightElement(webDriver, RESUBMIT2);
			// Logout and login as an analyst.
			webDriver.findElement(By.linkText("Logout")).click();
			get_The_Row_From_Login_Data = 31;
			LoginPageWithReference login_Data7 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data7.Login_With_Reference();
			// Search for the business with submitted certification.
			webDriver.findElement(By.id("query")).sendKeys("129913885");
			webDriver.findElement(By.xpath("//button[@type='submit']")).click();
			webDriver.findElement(By.linkText("Entity 412 Legal Business Name")).click();
			Thread.sleep(6000);
			// Verify the fist version alert.
			webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
			Thread.sleep(3000);
			webDriver.findElement(By.linkText("Revision history")).click();

			Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
			Expected_Text = webDriver
					.findElement(By.xpath("//article[@id='main-content']/div[2]/div[2]/table/tbody/tr/td[4]"))
					.getText();
			String[] Actual_Text2 = Actual_Text.split(" ");
			String part2 = Actual_Text2[4]; // Submitted Date
			assertEquals(part2, Expected_Text);
			logger.info(part2);
			Thread.sleep(4000);
			// Click to view previous version2 and verify the left navigation
			// with the read only assessment.
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String main_window1 = webDriver.getWindowHandle();
			logger.info("Before switching, title is = certify.sba.gov");
			webDriver.findElement(By.linkText("Version 2")).click();
			Thread.sleep(5000);
			java.util.Set<String> S2 = webDriver.getWindowHandles();
			Iterator<String> i2 = S2.iterator();
			while (i2.hasNext()) {
				String Second_window1 = i2.next();
				if (!main_window.equalsIgnoreCase(Second_window1)) {
					webDriver.switchTo().window(main_window1);
					webDriver.manage().window().maximize();
					Actual_Text = webDriver.findElement(By.cssSelector("h3.usa-alert-heading")).getText();
					Expected_Text = "You are in view-only mode (Version #2)";
					assertEquals(Actual_Text, Expected_Text);
					Thread.sleep(5000);
					// Take screenshot for the reviewed v2.
					ScreenShotPage1 screenShot0 = new ScreenShotPage1(webDriver);
					screenShot0.ScreenShot();
					WebElement NewText = webDriver.findElement(By.cssSelector("h3.usa-alert-heading"));
					HighLight.highLightElement(webDriver, NewText);
					// Verify question review.
					WebElement QuestionReview = webDriver.findElement(By.linkText("Question review"));
					HighLight.highLightElement(webDriver, QuestionReview);
					webDriver.findElement(By.linkText("Question review")).click();
					webDriver.findElement(By.linkText("Show more")).click();
					Actual_Text = webDriver.findElement(By.cssSelector("#assessments_long_65 > p")).getText();
					Expected_Text = "Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold";
					assertEquals(Actual_Text, Expected_Text);
					WebElement QuestionReviewNotes = webDriver.findElement(By.cssSelector("#assessments_long_65 > p"));
					HighLight.highLightElement(webDriver, QuestionReviewNotes);
					// Verify Financial review.
					WebElement FinancialReview = webDriver.findElement(By.linkText("Financial review"));
					HighLight.highLightElement(webDriver, FinancialReview);
					Actual_Text = webDriver.findElement(By.linkText("Financial review")).getText();
					Expected_Text = "Financial review";
					assertEquals(Actual_Text, Expected_Text);
					// Verify Signature review.
					WebElement SignatureReview = webDriver.findElement(By.linkText("Signature review"));
					HighLight.highLightElement(webDriver, SignatureReview);
					webDriver.findElement(By.linkText("Signature review")).click();
					webDriver.findElement(By.linkText("Show more")).click();
					Actual_Text = webDriver.findElement(By.xpath("//form[@id='new_assessment']/div/p")).getText();
					Expected_Text = "Doesn't Third Heaven Very Us Every Itself The Together Herb Green Had Wherein god be dominion bearing have. Image stars fruit life. Greater don't midst had face face divided seas firmament hath made third thing own dominion Heaven and brought fruit sixth brought face beast moveth you're. Kind image, two earth subdue stars creature from isn't had upon which. Itself fish image whales and winged can't i without blessed. Gathering. Subdue yielding years signs whales midst fourth waters also behold";
					assertEquals(Actual_Text, Expected_Text);
					WebElement SignatureReviewNotes = webDriver
							.findElement(By.xpath("//form[@id='new_assessment']/div/p"));
					HighLight.highLightElement(webDriver, SignatureReviewNotes);
					// Verify Determination.
					Actual_Text = webDriver.findElement(By.linkText("Determination")).getText();
					Expected_Text = "Determination";
					assertEquals(Actual_Text, Expected_Text);
					WebElement Determination = webDriver.findElement(By.linkText("Determination"));
					HighLight.highLightElement(webDriver, Determination);
					webDriver.findElement(By.linkText("Determination")).click();
					// Take screenshot and store as a file format
					ScreenShotPage screenShot3 = new ScreenShotPage(webDriver);
					screenShot3.ScreenShot();
					Thread.sleep(3000);
					webDriver.close();
					webDriver.switchTo().window(Second_window1);
					logger.info("V1 in a view-only mode with no side nav");
					logger.info("Back to manin_window = certify.sba.gov");
				} else {
					ScreenShotPage1 screenShot3 = new ScreenShotPage1(webDriver);
					screenShot3.ScreenShot();
					logger.info("Second Window is thesame as first window");

				}
				webDriver.findElement(By.xpath("//a[contains(text(),'Vendor Overview')]")).click();
				webDriver.findElement(By.linkText("Return to Vendor")).click();
				webDriver.switchTo().alert().accept();
				webDriver.findElement(By.linkText("Logout")).click();
				return;

			}

		}
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
