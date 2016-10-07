package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

public class TestMppBuildQuestionnaireTs1 extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestMppBuildQuestionnaireTs1.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 25;
	}
	@Test
	public void testMainTest() throws Exception {
		String Actual_Text;
		String Expected_Text;
		// Login to dashboard.
		logger.info("Mpp question test Scenario 2 possitive");
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
		// Click on the continue button without answering the question and
		// verify error message.
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.id("answers[117][value]-error")).getText();
		Expected_Text = "Please answer this question";
		assertEquals(Actual_Text, Expected_Text);
		// Select No and commit.
		webDriver.findElement(By.id("answers_117_value_yes")).click();
		// Upload a document.
		MontanaUploadDocumentPage montanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
		montanaUploadDocument.MontanaUploadDocument();
		Thread.sleep(4000);
		webDriver.findElement(By.name("commit")).click();
		// Review page.
		Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		Expected_Text = "Review";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("p")).getText();
		Expected_Text = "Please review below answers and Submit.";
		assertEquals(Actual_Text, Expected_Text);
		// 8(a) question.
		Actual_Text = webDriver.findElement(By.xpath("//form[@id='mpp']/ul/li/div/div/div/div/h4")).getText();
		Expected_Text = "Are you an existing 8(a) firm in your final 6 months of the program, wishing to transfer your Mentor-Protégé relationship to the All Small Mentor-Protégé Program?";
		assertEquals(Actual_Text, Expected_Text);
		// Verify doc uploaded.
		Actual_Text = webDriver.findElement(By.cssSelector("td")).getText();
		Expected_Text = "MainTestUploadDoc.pdf";
		assertEquals(Actual_Text, Expected_Text);
		// Click on change your answer and verify.
		webDriver.findElement(By.linkText("change your answer")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("#currently_attached > h4")).getText();
		Expected_Text = "Documents previously added";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.name("commit")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		Expected_Text = "Review";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.name("commit")).click();
		logger.info(webDriver.switchTo().alert().getText());
		webDriver.switchTo().alert().accept();
		
		// Step - Verify the Signature page for MPP
		logger.info("Step  - Verify the Signature page for MPP");
		// Verify you are on the Signature page
		logger.info("  Verify you are on the Signature page");
		Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		Expected_Text = "Signature";
		assertEquals(Actual_Text, Expected_Text);
		// Verify title
		logger.info("  Verify title");
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "All Small Mentor Protégé Program Application Summary";
		assertEquals(Actual_Text, Expected_Text);
		// Verify Business name
		logger.info("  Verify Business name");
		Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
		Expected_Text = "Entity 412 Legal Business Name";
		assertEquals(Actual_Text, Expected_Text);
		// Verify DUNS label
		logger.info("  Verify DUNS label");
		Actual_Text = webDriver.findElement(By.xpath("//b")).getText();
		Expected_Text = "DUNS:";
		// assertEquals(actual_error9, expected_error9);
		// Verify DUNS number
		logger.info("  Verify DUNS number");
		Actual_Text = webDriver.findElement(By.xpath("//span")).getText();
		Expected_Text = "129913885";
		assertEquals(Actual_Text, Expected_Text);
		// Verify first paragraph
		logger.info("  Verify first paragraph");
		Actual_Text = webDriver.findElement(By.cssSelector("label")).getText();
		Expected_Text = "All required documents verifying eligibility for the All Small Mentor-Protégé Program (All Small MPP) have been uploaded to certify.SBA.gov. I understand if any changes are made after I submit this application, I must notify the All Small Mentor-Protégé Program Office and submit additional documentation if needed.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify Second paragraph
		logger.info("  Verify second paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[2]")).getText();
		Expected_Text = "All the statements and information provided in this form and any documents submitted are true, accurate and complete. If assistance was obtained in completing this form and the supporting documentation, I have personally reviewed the information and it is true and accurate. I understand that these statements are made for the purpose of determining eligibility for participation in the All Small MPP.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify third paragraph
		logger.info("  Verify third paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[3]")).getText();
		Expected_Text = "I understand that the information submitted may be given to Federal, State and local agencies for determining violations of law and other purposes.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify fourth paragraph
		logger.info("  Verify fourth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[4]")).getText();
		Expected_Text = "I understand that I may not misrepresent my status as a small business to: 1) obtain a contract under the Small Business Act; or 2) obtain any benefit under a provision of Federal law that references the All Small MPP for a definition of program eligibility.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify fifth paragraph
		logger.info("  Verify fifth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[5]")).getText();
		Expected_Text = "By submitting this certification I, QA User, am an officer or owner of Entity 412 Legal Business Name authorized to represent it and electronically sign this certification on its behalf.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify sixth paragraph
		logger.info("  Verify sixth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[6]")).getText();
		Expected_Text = "Warning: By clicking the Submit button, you are certifying that you are representing on your own behalf that the information provided in this application, and any document or supplemental information submitted, is true and correct as of the date set forth opposite your signature. Any intentional or negligent misrepresentation of the information contained in this certification may result in criminal, civil or administrative sanctions including, but not limited to: 1) fines of up to $500,000, and imprisonment of up to 10 years, or both, as set forth in 15 U.S.C. § 645 and 18 U.S.C. § 1001, as well as any other applicable criminal laws; 2) treble damages and civil penalties under the False Claims Act; 3) double damages and civil penalties under the Program Fraud Civil Remedies Act; 4) suspension and/or debarment from all Federal procurement and non-procurement transactions; and 5) program termination.";
		assertEquals(Actual_Text, Expected_Text);
		// Step 9 - Click the Continue button
		logger.info("Step 9 - Click the Continue button");
		webDriver.findElement(By.id("accept-button")).click();
		Thread.sleep(3000);
		// Step 10 - Accept the error message
		logger.info("Step 10 - Accept the error message");
		webDriver.switchTo().alert().accept();
		// Step 11 - Accept the statements and click Continue
		logger.info("Step 11 - Click to accept the statements");
		webDriver.findElement(By.id("legal_0")).click();
		webDriver.findElement(By.id("legal_1")).click();
		webDriver.findElement(By.id("legal_2")).click();
		webDriver.findElement(By.id("legal_3")).click();
		webDriver.findElement(By.id("legal_4")).click();
		webDriver.findElement(By.id("legal_5")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id("accept-button")).click();
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}
