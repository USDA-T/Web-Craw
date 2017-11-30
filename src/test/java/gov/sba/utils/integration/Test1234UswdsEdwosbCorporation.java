// @Montana
package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class Test1234UswdsEdwosbCorporation extends TestCase {
  private static final Logger logger =
      LogManager.getLogger(Test1234UswdsEdwosbCorporation.class.getName());
  private static WebDriver webDriver;
  int get_The_Row_From_Login_Data;

  @Before
  public void setUp() throws Exception {
    webDriver = TestHelpers.getDefaultWebDriver();
    webDriver.get(TestHelpers.getBaseUrl());
    webDriver.manage().window().maximize();
    get_The_Row_From_Login_Data = 7;

  }

  @Test
  public void test1234UswdsEdwosbCorporation() throws Exception {
    // try{
    logger.info("Test EDWOSB Corporation Flow");
    // Login to dashboard.
    // try{
    LoginPageWithReference login_Data =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data.Login_With_Reference();
    // get the business DUNS #.
    String BusinessDuns;
    BusinessDuns = webDriver.findElement(By.xpath("//p[2]/span")).getText();
    logger.info("This Company DUNS number is " + BusinessDuns);
    // Verify if there is an existing certification on the dashboard and
    // delete to start a new certification.
    DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
    deleteDraftCert.DeleteDraftCert();
    // delete second draft if any.
    DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
    deleteDraftCert1.DeleteDraftCert();
    // Verify for active and Draft certification on the dashboard, if draft
    // delete and start a new one, if active or pending, Return it.
    ReturnActiveCert4Page returnActiveCert = new ReturnActiveCert4Page(webDriver);
    returnActiveCert.ReturnActiveCert4();
    ReturnActiveCert4Page returnActiveCert1 = new ReturnActiveCert4Page(webDriver);
    returnActiveCert1.ReturnActiveCert4();
    ReturnPendingCert4Page ReturnPendingCert4 = new ReturnPendingCert4Page(webDriver);
    ReturnPendingCert4.ReturnPendingCert4();
    ReturnPendingCert4Page ReturnPendingCert41 = new ReturnPendingCert4Page(webDriver);
    ReturnPendingCert41.ReturnPendingCert4();
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
    // Start the application.
    AddOrStartCertificationPage addOrStartCertification =
        new AddOrStartCertificationPage(webDriver);
    addOrStartCertification.AddOrStartCertification();
    // partnership test for 1st person.
    NewCorporationQuestionPage newCorporationQuestion = new NewCorporationQuestionPage(webDriver);
    newCorporationQuestion.NewCorporationQuestion();
    // Financial section.
    FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
    financialsection.Financialsection();
    // Submit and Return the submitted certification back to vendor.
    CorporationReturnCertPage corporationReturnCert = new CorporationReturnCertPage(webDriver);
		corporationReturnCert.CorporationReturnCert();
	    // Login with the vendor and verify the return draft.
    get_The_Row_From_Login_Data = 7;
    LoginPageWithReference login_Data1 =
        new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
    login_Data1.Login_With_Reference();
    WebElement ReturnDraft =
        webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]"));
    HighLight.highLightElement(webDriver, ReturnDraft);
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
