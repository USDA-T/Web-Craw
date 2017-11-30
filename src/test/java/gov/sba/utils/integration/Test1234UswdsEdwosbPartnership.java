// @Montana
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class Test1234UswdsEdwosbPartnership extends TestCase {

	private static final Logger logger = LogManager.getLogger(Test1234UswdsEdwosbPartnership.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;
	String DunsNumber;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 3;

	}

	@Test
	public void test1234UswdsEdwosbPartnership() throws Exception {
		WebDriverWait wait = new WebDriverWait(webDriver, 40);
		logger.info("Test EDWOSB Partnership Flow");
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		// Get the vendor Duns Number.
		webDriver.findElement(By.xpath("//li[4]/a/span")).click();
		wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//p[2]/span"), false));
		DunsNumber = webDriver.findElement(By.xpath("//p[2]/span")).getText();
		webDriver.findElement(By.xpath("//a/span")).click();
		logger.info("The Duns number for this business is " + DunsNumber);
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
		// Start the application.
		AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
		addOrStartCertification.AddOrStartCertification();
		// Delete second draft if any.
		DeleteDraftCertPage deleteDraftCert1111 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert1111.DeleteDraftCert();
		// Delete second draft if any.
		DeleteDraftCertPage deleteDraftCert11111 = new DeleteDraftCertPage(webDriver);
		deleteDraftCert11111.DeleteDraftCert();
		// partnership test for 1st person.
		NewPartnershipQuestionsPage newPartnershipQuestions = new NewPartnershipQuestionsPage(webDriver);
		newPartnershipQuestions.NewPartnershipQuestions();
		// Financial section.
		FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
		financialsection.Financialsection();
		// Submit and Return the submitted certification back to vendor.
		PartnershipReturnCertPage partnershipReturnCert = new PartnershipReturnCertPage(webDriver);
		partnershipReturnCert.PartnershipReturnCert();
		webDriver.findElement(By.id("query")).sendKeys("DunsNumber");
		jse.executeScript("arguments[0].scrollIntoView()", webDriver.findElement(By.xpath("//form/div/button")));
		webDriver.findElement(By.xpath("//form/div/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4/a")));
		webDriver.findElement(By.xpath("//h4/a")).click();
		if (webDriver.getPageSource().contains("Return to Vendor")) {
			webDriver.findElement(By.linkText("Return to Vendor")).click();
			// webDriver.switchTo().alert().accept();
			webDriver.findElement(By.id("profileid")).click();
			webDriver.findElement(By.linkText("Logout")).click();
		} else {
			logger.info("Return to Vendor Link is missing please verify why.");
			webDriver.findElement(By.linkText("EDWOSB Self-Certification")).click();
			webDriver.findElement(By.id("submit_button")).click();
			webDriver.findElement(By.linkText("Determination")).click();
			webDriver.findElement(By.id("review_workflow_state_returned_for_modification")).click();
			webDriver.findElement(By.xpath("//form[@id='new_determination']/input[5]")).click();
			webDriver.findElement(By.linkText("Vendor Overview")).click();
			webDriver.findElement(By.id("profileid")).click();
			webDriver.findElement(By.linkText("Logout")).click();
		}
		// Login with the vendor and verify the return draft.
		get_The_Row_From_Login_Data = 3;
		LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data1.Login_With_Reference();
		WebElement ReturnDraft = webDriver.findElement(By.xpath("//td[3]"));
		HighLight.highLightElement(webDriver, ReturnDraft);
		webDriver.findElement(By.id("profileid")).click();
		webDriver.findElement(By.linkText("Logout")).click();
		logger.info("Success");
	}

	@After
	public void tearDown() throws Exception {
		webDriver.close();
	}
}
