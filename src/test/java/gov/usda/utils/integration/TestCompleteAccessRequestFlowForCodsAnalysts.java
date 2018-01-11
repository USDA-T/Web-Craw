// Montana
package gov.usda.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import gov.sba.automation.TestHelpers;
import gov.usda.utils.integration.CodesSupervisorRevokeAccessPage;
import gov.usda.utils.integration.CodsAnalystSendsRequestAccessPage;
import gov.usda.utils.integration.CodsSupervisorApprovedRequestPage;
import gov.usda.utils.integration.CodsSupervisorRejectsRequestPage;
import gov.usda.utils.integration.LoginPageWithReference;
import gov.usda.utils.integration.ScreenShotPage;
import junit.framework.TestCase;

public class TestCompleteAccessRequestFlowForCodsAnalysts extends TestCase {

	private static final Logger logger = LogManager
			.getLogger(TestCompleteAccessRequestFlowForCodsAnalysts.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 66;

	}

	@Test
	public void testCompleteAccessRequestFlowForCodsAnalysts() throws Exception {
		// try {
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		String Actual_Text = null;
		String Expected_Text = null;
		logger.info("Completing Access Request flow for CODS Analysts.");
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		if (webDriver.getPageSource().contains("Dashboard")) {
			// Logout and revoke access.
			webDriver.findElement(By.id("profileid")).click();
			webDriver.findElement(By.linkText("Logout")).click();
			// Completing the Revoke Access process.
			CodesSupervisorRevokeAccessPage codesSupervisorRevokeAccess = new CodesSupervisorRevokeAccessPage(
					webDriver);
			codesSupervisorRevokeAccess.CodesSupervisorRevokeAccess();
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data1.Login_With_Reference();
		} else {
			logger.info("No pending request as of now, all good.");
		}
		if (webDriver.getPageSource().contains("Access Pending")) {
			// Logout and rejects request.
			webDriver.findElement(By.linkText("Logout")).click();
			// Complete process, CODS supervisor Rejects Request from other Cods
			// Supervisor.
			CodsSupervisorRejectsRequestPage codsSupervisorRejectsRequest = new CodsSupervisorRejectsRequestPage(
					webDriver);
			codsSupervisorRejectsRequest.CodsSupervisorRejectsRequest();
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data1.Login_With_Reference();
		} else {
			logger.info("No pending request as of now, all good.");
		}
		if (webDriver.getPageSource().contains("Welcome to certify.SBA.gov!")) {
			wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//article/div/p"), false));
			Actual_Text = webDriver.findElement(By.xpath("//article/div/p")).getText();
			Expected_Text = "Requesting Access";
			assertEquals(Actual_Text, Expected_Text);
			// Complete request access for CODS Analysts.
			CodsAnalystSendsRequestAccessPage codsAnalystSendsRequestAccess = new CodsAnalystSendsRequestAccessPage(
					webDriver);
			codsAnalystSendsRequestAccess.CodsAnalystSendsRequestAccess();
			// Try logging in with the user who just request access to verify
			// request is pending.
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data1.Login_With_Reference();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
				Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
				Expected_Text = "Access Pending";
				assertEquals(Actual_Text, Expected_Text);
				webDriver.findElement(By.linkText("Logout")).click();
			} catch (Exception e) {
				ScreenShotPage screenShot = new ScreenShotPage(webDriver);
				screenShot.ScreenShot();
				logger.info(e.getMessage());
			}
			// Complete process, CODS supervisor Approve Request from other Cods
			// Supervisor.
			CodsSupervisorApprovedRequestPage codsSupervisorApprovedRequest = new CodsSupervisorApprovedRequestPage(
					webDriver);
			codsSupervisorApprovedRequest.CodsSupervisorApprovedRequest();
			// Login with the Approved user.
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data2.Login_With_Reference();
			try {
				// Verify dashboard.
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
				Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
				Expected_Text = "Dashboard";
				assertEquals(Actual_Text, Expected_Text);
				// Logout.
				webDriver.findElement(By.id("profileid")).click();
				webDriver.findElement(By.linkText("Logout")).click();
			} catch (Exception ex) {
				logger.info(ex);
				throw new RuntimeException(ex);
			}
			// Completing the Revoke Access process.
			CodesSupervisorRevokeAccessPage codesSupervisorRevokeAccess = new CodesSupervisorRevokeAccessPage(
					webDriver);
			codesSupervisorRevokeAccess.CodesSupervisorRevokeAccess();
			// Login with the user's account who has been revoked and verify.
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data3.Login_With_Reference();
			try {
				// Verify dashboard
				if (webDriver.getPageSource().contains("What best describes you?")) {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
					Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
					Expected_Text = "Welcome to certify.SBA.gov!";
					assertEquals(Actual_Text, Expected_Text);
					webDriver.findElement(By.linkText("Logout")).click();
				} else {
					if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
						Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
						Expected_Text = "Your Request Has Been Rejected";
						assertEquals(Actual_Text, Expected_Text);
						webDriver.findElement(By.linkText("Logout")).click();
					} else {
						ScreenShotPage screenShot = new ScreenShotPage(webDriver);
						screenShot.ScreenShot();
						Assert.fail();
					}
				}
			} catch (Exception ex) {
				logger.info(ex);
				throw new RuntimeException(ex);
			}
			// Completing the Revoke Process.
			// Login to dashboard.
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data4.Login_With_Reference();
			if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
				webDriver.findElement(By.xpath("//form/button")).click();
			} else {
				logger.info(
						"Make a new request button not on this page because access was not rejected but was either revoked or never been submited");
			}
			// Complete request access for CODS Analysts.
			CodsAnalystSendsRequestAccessPage codsAnalystSendsRequestAccess1 = new CodsAnalystSendsRequestAccessPage(
					webDriver);
			codsAnalystSendsRequestAccess1.CodsAnalystSendsRequestAccess();
			// Try logging in with the user who just request access to verify
			// request is pending.
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data5 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data5.Login_With_Reference();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
				Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
				Expected_Text = "Access Pending";
				assertEquals(Actual_Text, Expected_Text);
				webDriver.findElement(By.linkText("Logout")).click();
			} catch (Exception ex) {
				logger.info(ex);
				throw new RuntimeException(ex);
			}
			// Complete process, CODS supervisor Rejects Request from other Cods
			// Supervisor.
			CodsSupervisorRejectsRequestPage codsSupervisorRejectsRequest = new CodsSupervisorRejectsRequestPage(
					webDriver);
			codsSupervisorRejectsRequest.CodsSupervisorRejectsRequest();
			// Login with the Reject Request user.
			get_The_Row_From_Login_Data = 66;
			LoginPageWithReference login_Data6 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data6.Login_With_Reference();
			try {
				// Verify dashboard.
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
				Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
				Expected_Text = "Your Request Has Been Rejected";
				assertEquals(Actual_Text, Expected_Text);
				// Verify role.
				Actual_Text = webDriver.findElement(By.xpath("//article/div/p")).getText();
				Expected_Text = "Your request to access certify.SBA.gov has been rejected. If you think this was done in error, you may submit another request for access.";
				assertEquals(Actual_Text, Expected_Text);
				// Logout.
				webDriver.findElement(By.linkText("Logout")).click();
			} catch (Exception ex) {
				logger.info(ex);
				throw new RuntimeException(ex);
			}
		}

		else {
			if (webDriver.getPageSource().contains("Dashboard")) {
				// Logout.
				webDriver.findElement(By.id("profileid")).click();
				webDriver.findElement(By.linkText("Logout")).click();

				// Login and the Revoke request.
				CodesSupervisorRevokeAccessPage codesSupervisorRevokeAccess = new CodesSupervisorRevokeAccessPage(
						webDriver);
				codesSupervisorRevokeAccess.CodesSupervisorRevokeAccess();
				// Login with the revoked user.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data1.Login_With_Reference();
				webDriver.findElement(By.xpath("//form/button")).click();
				wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//article/div/p"), false));
				Actual_Text = webDriver.findElement(By.xpath("//article/div/p")).getText();
				Expected_Text = "Requesting Access";
				assertEquals(Actual_Text, Expected_Text);
				// Complete request access for CODS Analysts.
				CodsAnalystSendsRequestAccessPage codsAnalystSendsRequestAccess = new CodsAnalystSendsRequestAccessPage(
						webDriver);
				codsAnalystSendsRequestAccess.CodsAnalystSendsRequestAccess();
				// Try logging in with the user who just request access to
				// verify request is pending.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data11 = new LoginPageWithReference(webDriver,
						get_The_Row_From_Login_Data);
				login_Data11.Login_With_Reference();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
					Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
					Expected_Text = "Access Pending";
					assertEquals(Actual_Text, Expected_Text);
					webDriver.findElement(By.linkText("Logout")).click();
				} catch (Exception e) {
					ScreenShotPage screenShot = new ScreenShotPage(webDriver);
					screenShot.ScreenShot();
					logger.info(e.getMessage());
				}
				// Complete process, CODS supervisor Approve Request from other
				// Cods Supervisor.
				CodsSupervisorApprovedRequestPage codsSupervisorApprovedRequest = new CodsSupervisorApprovedRequestPage(
						webDriver);
				codsSupervisorApprovedRequest.CodsSupervisorApprovedRequest();
				// Login with the Approved user.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data2.Login_With_Reference();
				try {
					// Verify dashboard.
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
					Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
					Expected_Text = "Dashboard";
					assertEquals(Actual_Text, Expected_Text);
					// Logout.
					webDriver.findElement(By.id("profileid")).click();
					webDriver.findElement(By.linkText("Logout")).click();
				} catch (Exception e) {
					ScreenShotPage screenShot = new ScreenShotPage(webDriver);
					screenShot.ScreenShot();
					logger.info(e.getMessage());
				}
				// Completing the Revoke Access process.
				CodesSupervisorRevokeAccessPage codesSupervisorRevokeAccess1 = new CodesSupervisorRevokeAccessPage(
						webDriver);
				codesSupervisorRevokeAccess1.CodesSupervisorRevokeAccess();
				// Login with the user's account who has been revoked and
				// verify.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data3.Login_With_Reference();
				try {
					// Verify dashboard
					if (webDriver.getPageSource().contains("What best describes you?")) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
						Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
						Expected_Text = "Welcome to certify.SBA.gov!";
						assertEquals(Actual_Text, Expected_Text);
						webDriver.findElement(By.linkText("Logout")).click();
					} else {
						if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
							Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
							Expected_Text = "Your Request Has Been Rejected";
							assertEquals(Actual_Text, Expected_Text);
							webDriver.findElement(By.linkText("Logout")).click();
						} else {
							ScreenShotPage screenShot = new ScreenShotPage(webDriver);
							screenShot.ScreenShot();
							Assert.fail();
						}
					}
				} catch (Exception ex) {
					logger.info(ex);
					throw new RuntimeException(ex);
				}
				// Completing the Revoke Process.
				// Login to dashboard.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data4.Login_With_Reference();
				if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
					webDriver.findElement(By.xpath("//form/button")).click();
				} else {
					logger.info(
							"Make a new request button not on this page because access was not rejected but was either revoked or never been submited");
				}
				// Complete request access for CODS Analysts.
				CodsAnalystSendsRequestAccessPage codsAnalystSendsRequestAccess1 = new CodsAnalystSendsRequestAccessPage(
						webDriver);
				codsAnalystSendsRequestAccess1.CodsAnalystSendsRequestAccess();
				// Try logging in with the user who just request access to
				// verify request is pending.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data5 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data5.Login_With_Reference();
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
					Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
					Expected_Text = "Access Pending";
					assertEquals(Actual_Text, Expected_Text);
					webDriver.findElement(By.linkText("Logout")).click();
				} catch (Exception ex) {
					logger.info(ex);
					throw new RuntimeException(ex);
				}
				// Complete process, CODS supervisor Rejects Request from other
				// Cods Supervisor.
				CodsSupervisorRejectsRequestPage codsSupervisorRejectsRequest = new CodsSupervisorRejectsRequestPage(
						webDriver);
				codsSupervisorRejectsRequest.CodsSupervisorRejectsRequest();
				// Login with the Reject Request user.
				get_The_Row_From_Login_Data = 66;
				LoginPageWithReference login_Data6 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				login_Data6.Login_With_Reference();
				try {
					// Verify dashboard.
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
					Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
					Expected_Text = "Your Request Has Been Rejected";
					assertEquals(Actual_Text, Expected_Text);
					// Verify role.
					Actual_Text = webDriver.findElement(By.xpath("//article/div/p")).getText();
					Expected_Text = "Your request to access certify.SBA.gov has been rejected. If you think this was done in error, you may submit another request for access.";
					assertEquals(Actual_Text, Expected_Text);
					// Logout.
					webDriver.findElement(By.linkText("Logout")).click();
				} catch (Exception ex) {
					logger.info(ex);
					throw new RuntimeException(ex);
				}
			} else {
				if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
					webDriver.findElement(By.xpath("//form/button")).click();
					wait.until(ExpectedConditions.elementSelectionStateToBe(By.xpath("//article/div/p"), false));
					Actual_Text = webDriver.findElement(By.xpath("//article/div/p")).getText();
					Expected_Text = "Requesting Access";
					assertEquals(Actual_Text, Expected_Text);
					// Complete request access for CODS Analysts.
					CodsAnalystSendsRequestAccessPage codsAnalystSendsRequestAccess = new CodsAnalystSendsRequestAccessPage(
							webDriver);
					codsAnalystSendsRequestAccess.CodsAnalystSendsRequestAccess();
					// Try logging in with the user who just request access to
					// verify request is pending.
					get_The_Row_From_Login_Data = 66;
					LoginPageWithReference login_Data11 = new LoginPageWithReference(webDriver,
							get_The_Row_From_Login_Data);
					login_Data11.Login_With_Reference();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
						Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
						Expected_Text = "Access Pending";
						assertEquals(Actual_Text, Expected_Text);
						webDriver.findElement(By.linkText("Logout")).click();
					} catch (Exception ex) {
						logger.info(ex);
						throw new RuntimeException(ex);
					}
					// Complete process, CODS supervisor Approve Request from
					// other Cods Supervisor.
					CodsSupervisorApprovedRequestPage codsSupervisorApprovedRequest = new CodsSupervisorApprovedRequestPage(
							webDriver);
					codsSupervisorApprovedRequest.CodsSupervisorApprovedRequest();
					// Login with the Approved user.
					get_The_Row_From_Login_Data = 66;
					LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver,
							get_The_Row_From_Login_Data);
					login_Data2.Login_With_Reference();
					try {
						// Verify dashboard.
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
						Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
						Expected_Text = "Dashboard";
						assertEquals(Actual_Text, Expected_Text);
						// Logout.
						webDriver.findElement(By.id("profileid")).click();
						webDriver.findElement(By.linkText("Logout")).click();
					} catch (Exception e) {
						ScreenShotPage screenShot = new ScreenShotPage(webDriver);
						screenShot.ScreenShot();
						logger.info(e.getMessage());
					}
					// Completing the Revoke Access process.
					CodesSupervisorRevokeAccessPage codesSupervisorRevokeAccess1 = new CodesSupervisorRevokeAccessPage(
							webDriver);
					codesSupervisorRevokeAccess1.CodesSupervisorRevokeAccess();
					// Login with the user's account who has been revoked and
					// verify.
					get_The_Row_From_Login_Data = 66;
					LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver,
							get_The_Row_From_Login_Data);
					login_Data3.Login_With_Reference();
					try {
						// Verify dashboard
						if (webDriver.getPageSource().contains("What best describes you?")) {
							wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
							Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
							Expected_Text = "Welcome to certify.SBA.gov!";
							assertEquals(Actual_Text, Expected_Text);
							webDriver.findElement(By.linkText("Logout")).click();
						} else {
							if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
								Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
								Expected_Text = "Your Request Has Been Rejected";
								assertEquals(Actual_Text, Expected_Text);
								webDriver.findElement(By.linkText("Logout")).click();
							} else {
								ScreenShotPage screenShot = new ScreenShotPage(webDriver);
								screenShot.ScreenShot();
								Assert.fail();
							}
						}
					} catch (Exception e) {
						ScreenShotPage screenShot = new ScreenShotPage(webDriver);
						screenShot.ScreenShot();
						logger.info(e.getMessage());
					}
					// Completing the Revoke Process.
					// Login to dashboard.
					get_The_Row_From_Login_Data = 66;
					LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver,
							get_The_Row_From_Login_Data);
					login_Data4.Login_With_Reference();
					if (webDriver.getPageSource().contains("Your Request Has Been Rejected")) {
						webDriver.findElement(By.xpath("//form/button")).click();
					} else {
						logger.info(
								"Make a new request button not on this page because access was not rejected but was either revoked or never been submited");
					}
					// Complete request access for CODS Analysts.
					CodsAnalystSendsRequestAccessPage codsAnalystSendsRequestAccess1 = new CodsAnalystSendsRequestAccessPage(
							webDriver);
					codsAnalystSendsRequestAccess1.CodsAnalystSendsRequestAccess();
					// Try logging in with the user who just request access to
					// verify request is pending.
					get_The_Row_From_Login_Data = 66;
					LoginPageWithReference login_Data5 = new LoginPageWithReference(webDriver,
							get_The_Row_From_Login_Data);
					login_Data5.Login_With_Reference();
					try {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
						Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
						Expected_Text = "Access Pending";
						assertEquals(Actual_Text, Expected_Text);
						webDriver.findElement(By.linkText("Logout")).click();
					} catch (Exception e) {
						ScreenShotPage screenShot = new ScreenShotPage(webDriver);
						screenShot.ScreenShot();
						logger.info(e.getMessage());
					}
					// Complete process, CODS supervisor Rejects Request from
					// other Cods Supervisor.
					CodsSupervisorRejectsRequestPage codsSupervisorRejectsRequest = new CodsSupervisorRejectsRequestPage(
							webDriver);
					codsSupervisorRejectsRequest.CodsSupervisorRejectsRequest();
					// Login with the Reject Request user.
					get_The_Row_From_Login_Data = 66;
					LoginPageWithReference login_Data6 = new LoginPageWithReference(webDriver,
							get_The_Row_From_Login_Data);
					login_Data6.Login_With_Reference();
					try {
						// Verify dashboard.
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
						Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
						Expected_Text = "Your Request Has Been Rejected";
						assertEquals(Actual_Text, Expected_Text);
						// Verify role.
						Actual_Text = webDriver.findElement(By.xpath("//article/div/p")).getText();
						Expected_Text = "Your request to access certify.SBA.gov has been rejected. If you think this was done in error, you may submit another request for access.";
						assertEquals(Actual_Text, Expected_Text);
						// Logout.
						webDriver.findElement(By.linkText("Logout")).click();
					} catch (Exception e) {
						ScreenShotPage screenShot = new ScreenShotPage(webDriver);
						screenShot.ScreenShot();
						logger.info(e.getMessage());
					}
				} else {

					// }

					logger.info(
							"Make a new request button not on this page because access was not rejected but was either revoked or never been submited");
				}
			}
		}
	}

	@After
	public void tearDown() throws Exception {
		try {
			webDriver.close();
		} catch (Exception ex) {
			logger.info(ex);
			throw new RuntimeException(ex);
		}
	}
}