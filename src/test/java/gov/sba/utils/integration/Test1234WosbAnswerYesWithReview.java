package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class Test1234WosbAnswerYesWithReview extends TestCase {
	private static final Logger logger = LogManager.getLogger(Test1234WosbAnswerYesWithReview.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {

		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 3;
	}

	@Test
	public void test1234WosbAnswerYesWithReview() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		String Actual_Text;
		String Expected_Text;
		// try {
		logger.info("Test WOSB Partnership Flow (Select Yes with review");
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		// Verify if there is an existing certification on the dashboard and
		// TestWorkFlowxx8aInProgress to start a new certification.
		DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
		deleteDraftCert.DeleteDraftCert();
		// TestWorkFlowxx8aInProgress second draft if any.
		DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert1.DeleteDraftCert();
		// Verify for active and Draft certification on the dashboard, if draft
		// delete and start a new one, if active or pending, Return it.
		ReturnActiveCertPage returnActiveCert = new ReturnActiveCertPage(webDriver);
		returnActiveCert.ReturnActiveCert();
		ReturnActiveCertPage returnActiveCert1 = new ReturnActiveCertPage(webDriver);
		returnActiveCert1.ReturnActiveCert();
		ReturnPendingCertPage returnPendingCert = new ReturnPendingCertPage(webDriver);
		returnPendingCert.ReturnPendingCert();
		ReturnPendingCertPage returnPendingCert1 = new ReturnPendingCertPage(webDriver);
		returnPendingCert1.ReturnPendingCert();
		// Verify if there is an existing certification on the dashboard and
		// TestWorkFlowxx8aInProgress to start a new certification.
		DeleteDraftCertPage deleteDraftCert11 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert11.DeleteDraftCert();
		// Delete second draft if any.
		DeleteDraftCertPage deleteDraftCert111 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert111.DeleteDraftCert();
		// Delete second draft if any.
		DeleteDraftCertPage deleteDraftCert1111 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert1111.DeleteDraftCert();
		// Delete second draft if any.
		DeleteDraftCertPage deleteDraftCert11111 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert11111.DeleteDraftCert();
		webDriver.findElement(By.xpath("//li[4]/a/span")).click();
		String Duns;
		Duns = webDriver.findElement(By.xpath("//p[2]/span")).getText();
		webDriver.findElement(By.xpath("//a/span")).click();
		// Start the application.
		StartWosbProgramPage startWosbProgram = new StartWosbProgramPage(webDriver);
		startWosbProgram.StartWosbProgram();
		// Start a new WOSB Question.
		NewWosbQuestionAnswerYesPage newWosbQuestionAnswerYes = new NewWosbQuestionAnswerYesPage(webDriver);
		newWosbQuestionAnswerYes.NewWosbQuestionAnswerYes();
		// Verify first paragraph
		logger.info("  Verify first paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//div[2]/label")).getText();
		Expected_Text = "All required documents verifying eligibility for the Woman-Owned Small Business (WOSB) or Economically Disadvantaged Woman-Owned Small Business (EDWOSB) requirements have been submitted to the WOSB Program Repository, including any supplemental documents if there have been changes since the last representation.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify Second paragraph
		logger.info("  Verify second paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[2]")).getText();
		Expected_Text = "All the statements and information provided in this form and any documents submitted are true, accurate and complete. If assistance was obtained in completing this form and the supporting documentation, I have personally reviewed the information and it is true and accurate. I understand that these statements are made for the purpose of determining eligibility for a WOSB Program contract.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify third paragraph
		logger.info("  Verify third paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[3]")).getText();
		Expected_Text = "I understand that the information submitted may be given to Federal, State and local agencies for determining violations of law and other purposes. The certifications in this document are continuing in nature. Each WOSB or EDWOSB prime contract for which the WOSB or EDWOSB submits an offer/quote or receives an award constitutes a restatement and reaffirmation of these certifications.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify fourth paragraph
		logger.info("  Verify fourth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[4]")).getText();
		Expected_Text = "Warning: By clicking the Submit Button, you are certifying that you are representing on your own behalf, and on behalf of the woman-owned small business or economically disadvantaged woman-owned small business, that the information provided in this certification, and any document or supplemental information submitted, is true and correct as of the date set forth opposite your signature. Any intentional or negligent misrepresentation of the information contained in this certification may result in criminal, civil or administrative sanctions including, but not limited to: 1) fines of up to $500,000, and imprisonment of up to 10 years, or both, as set forth in 15 U.S.C. § 645 and 18 U.S.C. § 1001, as well as any other applicable criminal laws; 2) treble damages and civil penalties under the False Claims Act; 3) double damages and civil penalties under the Program Fraud Civil Remedies Act; 4) suspension and/or debarment from all Federal procurement and non-procurement transactions; and 5) program termination.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify fifth paragraph
		logger.info("  Verify fifth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[5]")).getText();
		Expected_Text = "I understand that the woman-owned small business or economically disadvantaged woman-owned small business may not misrepresent its status as a WOSB or EDWOSB to: 1) obtain a contract under the Small Business Act; or 2) obtain any benefit under a provision of Federal law that references the WOSB Program for a definition of program eligibility.";
		assertEquals(Actual_Text, Expected_Text);
		// Verify sixth paragraph
		logger.info("  Verify sixth paragraph");
		Actual_Text = webDriver.findElement(By.xpath("//label[6]")).getText();
		Expected_Text = "By submitting this certification I, QA User, am an officer or owner of Entity 81 Legal Business Name authorized to represent it and electronically sign this certification on its behalf.";
		assertEquals(Actual_Text, Expected_Text);
		// Step 9 - Click the Continue button
		logger.info("Clicking accept buuton to trigger pop up alert");
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.id("accept-button")));
		Actions act = new Actions(webDriver);
		act.doubleClick(webDriver.findElement(By.id("accept-button"))).build().perform();
		wait.until(ExpectedConditions.alertIsPresent());
		Actual_Text = webDriver.switchTo().alert().getText();
		Expected_Text = "In order to submit your application, you must accept all of the conditions of authorization.";
		assertEquals(Actual_Text, Expected_Text);
		// Step 10 - Accept the error message
		logger.info("Step 10 - Accept the error message");
		webDriver.switchTo().alert().accept();
		// Step 11 - Accept the statements and click Continue
		logger.info("Step 11 - Click to accept the statements");
		SignAndSubmitWOSBApplicationPage signAndSubmitWOSBApplication = new SignAndSubmitWOSBApplicationPage(webDriver);
		signAndSubmitWOSBApplication.SignAndSubmitWOSBApplication();
		get_The_Row_From_Login_Data = 0;
		LoginPageWithReference login_Data7 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data7.Login_With_Reference();
		webDriver.findElement(By.id("query")).sendKeys(Duns);
		webDriver.findElement(By.xpath("//form/div/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4/a")));
		webDriver.findElement(By.xpath("//h4/a")).click();
		// Submit and review the WOSB application.
		WosbReturnCertPage wosbReturnCert = new WosbReturnCertPage(webDriver);
		wosbReturnCert.WosbReturnCert();
		// Login with the vendor and verify the return draft.
		get_The_Row_From_Login_Data = 3;
		LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data1.Login_With_Reference();
		WebElement ReturnDraft = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]"));
		HighLight.highLightElement(webDriver, ReturnDraft);
		webDriver.findElement(By.id("profileid")).click();
		webDriver.findElement(By.linkText("Logout")).click();
		// } catch (Exception e) {
		// ScreenShotPage screenShot = new ScreenShotPage(webDriver);
		// screenShot.ScreenShot();
		// logger.info(e.getMessage());
		// Assert.fail();
		// }
		logger.info("Success");
	}

	@After
	public void tearDown() throws Exception {
		webDriver.close();
	}
}
