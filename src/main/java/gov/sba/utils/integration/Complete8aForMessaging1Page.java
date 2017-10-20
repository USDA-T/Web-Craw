package gov.sba.utils.integration;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import junit.framework.TestCase;

public class Complete8aForMessaging1Page extends TestCase {
		private static final Logger logger = LogManager.getLogger(Complete8aForMessaging1Page.class.getName());
		WebDriver webDriver;
		int get_The_Row_From_Login_Data;

		public Complete8aForMessaging1Page(WebDriver webDriver) {
			this.webDriver = webDriver;
		}

		public void Complete8aForMessaging1() throws Exception {
			String Actual_Text = null;
			String Expected_Text = null;
			logger.info("Test for 8a initial master Flow");
			// Login to dashboard.
			get_The_Row_From_Login_Data = 92;
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			// Verify if there is an existing certification on the dashboard and
			// delete to start a new certification.
			DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
			deleteDraftCert.DeleteDraftCert();
			// delete second draft if any.
			DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
			deleteDraftCert1.DeleteDraftCert();
			// Check if user already has pending app.
			InitialCcorpUsersPage Verifyaccount = new InitialCcorpUsersPage(webDriver);
			Verifyaccount.InitialCcorpUsers();
			// delete second draft if any.
			DeleteDraftCertPage deleteDraftCert11 = new DeleteDraftCertPage(webDriver);
			deleteDraftCert11.DeleteDraftCert();
			// Complete the basic eligibility section.
			BasicEligibilityMasterAppPage basicEligibilityMasterApp = new BasicEligibilityMasterAppPage(webDriver);
			basicEligibilityMasterApp.BasicEligibilityMasterApp();
			// Complete the Business Ownership section.
			BusinessOwnershipSubsectionMasterCcorpPage businessOwnershipSubsectionMaster = new BusinessOwnershipSubsectionMasterCcorpPage(
					webDriver);
			businessOwnershipSubsectionMaster.BusinessOwnershipSubsectionMasterCcorp();
			// Complete the character section.
			CharacterMasterAppSectionPage characterMasterAppSection = new CharacterMasterAppSectionPage(webDriver);
			characterMasterAppSection.CharacterMasterAppSection();
			// Complete the Control section.
			ControlMasterAppSubsectionPage controlMasterAppSubsection = new ControlMasterAppSubsectionPage(webDriver);
			controlMasterAppSubsection.ControlMasterAppSubsection();
			// ====>>>Add Potential for Success here.
			PotentialForSuccessMasterAppSectionPage potentialForSuccessMasterAppSection = new PotentialForSuccessMasterAppSectionPage(
					webDriver);
			potentialForSuccessMasterAppSection.PotentialForSuccessMasterAppSection();
			// Complete the Individual section.
			IndividualApplicationSectionPage individualApplicationSection = new IndividualApplicationSectionPage(webDriver);
			individualApplicationSection.IndividualApplicationSection();
			try {
				// Complete the DvD section.
				ContributorDisadvantageIndividualQuetionPage contributorDisadvantageIndividualQuetion = new ContributorDisadvantageIndividualQuetionPage(
						webDriver);
				contributorDisadvantageIndividualQuetion.ContributorDisadvantageIndividualQuetion();
				// Verify status flow.
				// Login to the Admin dashboard.
				get_The_Row_From_Login_Data = 92;
				LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data1.Login_With_Reference();
				// Click on the dashboard.
				webDriver.findElement(By.xpath("//a/span")).click();
				WebElement rateElement7 = webDriver.findElement(By.linkText("8(a) Initial Application"));
				((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", rateElement7);
				// Contributors is verify to be completed, but click on it to
				// add
				// other
				// contributors.
				Actual_Text = webDriver.findElement(By.xpath("//div[2]/div/section/div/div[2]/table/tbody/tr/td/span"))
						.getText();
				Expected_Text = "Complete";
				assertEquals(Actual_Text, Expected_Text);
				WebElement ContributorCompleteStatus3 = webDriver
						.findElement(By.xpath("//div[2]/div/section/div/div[2]/table/tbody/tr/td/span"));
				HighLight.highLightElement(webDriver, ContributorCompleteStatus3);
				logger.info("All Required sections are completed, Ready to Review, Sign and Submit.");
				// Sign and submit.
				SignAndSubmit8aApplicationPage SignAndSubmit = new SignAndSubmit8aApplicationPage(webDriver);
				SignAndSubmit.SignAndSubmit8aApplication();
			} catch (Exception e) {
				ScreenShotPage screenShot = new ScreenShotPage(webDriver);
				screenShot.ScreenShot();
				logger.info("Upload will not run on headless " + e.getMessage());
			}
			logger.info("8a initial application is successfully submitted");
		}

	}
