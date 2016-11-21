package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import junit.framework.TestCase;

public class TestMppBuildQuestionariesScenariosS3 extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestMppBuildQuestionariesScenariosS3.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 27;
	}

	@Test
	public void testMainTest() throws Exception {
		String Actual_Text;
		String Expected_Text;
		// Login to dashboard.
		logger.info("Mpp question test Scenario 3 possitive + skip Logic");
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		// Verify if there is an existing program on the dashboard and
		// delete to start a new certification.
		DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
		deleteDraftCert.DeleteDraftCert();
		// Verify for active and Draft program on the dashboard, if draft
		// delete and start a new one.
		AddOrStartNewMppProgramPage addOrStartNewMppProgram = new AddOrStartNewMppProgramPage(webDriver);
		addOrStartNewMppProgram.AddOrStartNewMppProgram();
		// MPP Questions Section, 8(a) Participants. Answer=NO.
		// Verifying Question.
		Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
		Expected_Text = "Are you an existing 8(a) firm in your final 6 months of the program, wishing to transfer your Mentor-Protégé relationship to the All Small Mentor-Protégé Program?";
		assertEquals(Actual_Text, Expected_Text);
		// Verifying detail meaning for question.
		Actual_Text = webDriver.findElement(By.xpath("//p[2]")).getText();
		Expected_Text = "If yes, please upload your dated 8(a) Mentor-Protégé Approval Letter and your current 8(a) Mentor-Protégé Agreement. You are eligible for the All Small Mentor-Protégé Program and you will skip forward to the “Review” section of this application.";
		assertEquals(Actual_Text, Expected_Text);
		// Select No and commit.
		webDriver.findElement(By.id("answers_117_value_no")).click();
		webDriver.findElement(By.name("commit")).click();
		// Eligibility Section, Verifying Question.
		Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
		Expected_Text = "Are you either a for-profit business or an agricultural cooperative?";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("#answers_mentor_for_profit > fieldset > h4")).getText();
		Expected_Text = "Is your Mentor a for-profit business?";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("#answers_prior_sba_mpp_determination > fieldset > h4"))
				.getText();
		Expected_Text = "Has the SBA ever made a determination of affiliation between you and the Mentor?";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("#answers_mentor_over_40_percent_protege > fieldset > h4"))
				.getText();
		Expected_Text = "Does the Mentor own or plan to own more than 40 percent equity interest in your firm?";
		assertEquals(Actual_Text, Expected_Text);
		// Verify detail meaning for questions.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_for_profit_or_ag_coop']/fieldset/p[2]"))
				.getText();
		Expected_Text = "Except for small agricultural cooperatives, only business entities organized for-profit are eligible for small business assistance from the SBA.";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_mentor_for_profit']/fieldset/p[2]")).getText();
		Expected_Text = "A mentor is defined as “a for-profit business concern of any size.”";
		assertEquals(Actual_Text, Expected_Text);
		// Open this command when fix.
		// Actual_Text = webDriver.findElement(By.xpath("")).getText();
		// Expected_Text = "Affiliation exists when one business has the power
		// to control another business. This could occur through ownership,
		// management, or other relationships or interactions between two
		// parties. If the SBA has made a determination of affiliation between
		// you and your mentor, they would have counted your business’s
		// receipts, employees, or other measures of size.";
		// assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver
				.findElement(By.xpath("//div[@id='answers_mentor_over_40_percent_protege']/fieldset/p[2]")).getText();
		Expected_Text = "To raise capital for the Protégé firm the Mentor may generally own an equity interest of up to 40% in the Protégé firm. Reference: 13 CFR 124.520(d)(2)";
		assertEquals(Actual_Text, Expected_Text);
		// Click on the continue button without answering the question and
		// verify error message.
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.id("answers[118][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.id("answers[119][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.id("answers[120][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.id("answers[121][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		// Select yes for the first two questions.
		webDriver.findElement(By.id("answers_118_value_yes")).click();
		webDriver.findElement(By.id("answers_119_value_yes")).click();
		webDriver.findElement(By.id("answers_120_value_no")).click();
		webDriver.findElement(By.id("answers_121_value_no")).click();
		webDriver.findElement(By.name("commit")).click();
		// NAICS Code Section, Verifying Question.
		Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
		Expected_Text = "Please select the NAICS code for which you are creating a mentor-protégé relationship:";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("#answers_prior_naics_code_work > fieldset > h4")).getText();
		Expected_Text = "Have you performed work in the NAICS code in which you’re requesting business development assistance?";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("#answers_small_for_mpp_naics_code > fieldset > h4"))
				.getText();
		Expected_Text = "Are you considered small for the NAICS code in which you’re requesting business development assistance?";
		assertEquals(Actual_Text, Expected_Text);
		// Verifying detail meaning for questions.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_mpp_naics_code']/fieldset/p[2]")).getText();
		Expected_Text = "A Mentor-Protégé Agreement must be in a NAICS code that is identified in SAM.gov as a protégé’s primary or secondary code.";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_mpp_naics_code']/fieldset/p[3]")).getText();
		Expected_Text = "The NAICS codes listed above are the primary or secondary codes associated with your business in SAM.gov. You may update your SAM.gov profile to include an additional NAICS code if the correct NAICS is not listed. If you add a code to your SAM.gov profile, please wait 24 hours for us to receive the update and then return to continue your application.";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_prior_naics_code_work']/fieldset/p[2]"))
				.getText();
		Expected_Text = "You must have prior experience in the NAICS code in the NAICS code in which you’re seeking assistance.";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_small_for_mpp_naics_code']/fieldset/p[2]"))
				.getText();
		Expected_Text = "To qualify as a protégé firm, your business must qualify as small for the NAICS code for which it is seeking business development assistance. Size standards have been established for types of economic activity, or industry, under the North American Industry Classification System (NAICS). To determine the size standard associated with a particular NAICS code, refer to the table of size standards in the Small Business Size Regulations. Reference: 13 CFR 121.201";
		assertEquals(Actual_Text, Expected_Text);
		// Click on the continue button without answering the question and
		// verify error message.
		webDriver.findElement(By.name("commit")).click();
		// Select A NAICS code from t he drop down.
		WebElement mySelect = webDriver.findElement(By.xpath("//*[@id='answers_122_value']"));
		Select dropdown = new Select(mySelect);
		dropdown.selectByIndex(1);
		Actual_Text = webDriver.findElement(By.id("answers[123][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.id("answers[124][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		// Select yes for the first two questions.
		webDriver.findElement(By.id("answers_123_value_yes")).click();
		webDriver.findElement(By.id("answers_124_value_yes")).click();
		webDriver.findElement(By.name("commit")).click();
		// Size Determination Section(Sub-Subsection 1.1), Verifying Question.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_size_determination']/fieldset/h4")).getText();
		Expected_Text = "Have you ever received a size determination letter from the SBA that found you to be “other than small” in the NAICS code in which you’re requesting business development assistance?";
		assertEquals(Actual_Text, Expected_Text);
		// Click on the continue button without answering the question and
		// verify error message.
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.id("answers[125][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		// Select yes for the first two questions.
		webDriver.findElement(By.id("answers_125_value_no")).click();
		webDriver.findElement(By.name("commit")).click();
		// Verify the Size Redetermination Section(Sub-Subsection 1.2) is
		// disabled , Verifying Question.
		// Training Section(Subsection 2.1), Verifying Question.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_mpp_completion_cert']/fieldset/h4")).getText();
		Expected_Text = "Please view the Mentor-Protégé Program training module and upload the certificate of completion.";
		assertEquals(Actual_Text, Expected_Text);
		// Upload a document.
		DeepaMppUploadDocumentPage deepaUploadMppDocument = new DeepaMppUploadDocumentPage(webDriver);
		deepaUploadMppDocument.deepaUploadMppDocument("C:\\Users\\Deepa3\\Documents\\UI_For_MPP.pdf");
		webDriver.findElement(By.name("commit")).click();

	}
	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}