package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class Test1234WosbAnswerYesWithReview extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(Test1234WosbAnswerYesWithReview.class.getName());
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
  public void testMainTest() throws Exception {
    try {
      logger.info("Test WOSB Partnership Flow (Select Yes with review");
      // Login to dashboard.
      LoginPageWithReference login_Data =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data.Login_With_Reference();
      // Verify if there is an existing certification on the dashboard and
      // TestWorkFlowxx8aInProgress to start a new certification.
      DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
      deleteDraftCert.DeleteDraftCert();
      // TestWorkFlowxx8aInProgress second draft if any.
      DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
      deleteDraftCert1.DeleteDraftCert();
      // Start new WOSB program.
      StartWosbProgramPage startWosbProgram = new StartWosbProgramPage(webDriver);
      startWosbProgram.StartWosbProgram();
      // Start a new WOSB Question.
      NewWosbQuestionAnswerYesPage newWosbQuestionAnswerYes =
          new NewWosbQuestionAnswerYesPage(webDriver);
      newWosbQuestionAnswerYes.NewWosbQuestionAnswerYes();
      // Submit and review the WOSB application.
      WosbReturnCertPage wosbReturnCert = new WosbReturnCertPage(webDriver);
      wosbReturnCert.WosbReturnCert();
      // Login with the vendor and verify the return draft.
      get_The_Row_From_Login_Data = 3;
      LoginPageWithReference login_Data1 =
          new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
      login_Data1.Login_With_Reference();
      WebElement ReturnDraft =
          webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]"));
      HighLight.highLightElement(webDriver, ReturnDraft);
      webDriver.findElement(By.linkText("Logout")).click();
    } catch (Exception e) {
      ScreenShotPage screenShot = new ScreenShotPage(webDriver);
      screenShot.ScreenShot();
      logger.info(e.getMessage());
      Assert.fail();
    }
    logger.info("Success");
  }

  @After
  public void tearDown() throws Exception {
    webDriver.close();
  }
}
