// @Montana
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

public class Test1234EdwosbAddSecondPartnerAfterReview extends TestCase {
    private static final Logger logger = LogManager
            .getLogger(Test1234EdwosbAddSecondPartnerAfterReview.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;
    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 8;
    }

    @Test
    public void testMainTest() throws Exception {
        try {
            logger.info("Test EDWOSB Sole-Proprietorship two partners on form413 with review");
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            // Verify if there is an existing certification on the dashboard and
            // delete to start a new certification.
            DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
            deleteDraftCert.DeleteDraftCert();
            // Verify for active and Draft certification on the dashboard, if
            // draft
            // delete and start a new one.
            AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
            addOrStartCertification.AddOrStartCertification();
            // partnership test for 1st person.
            NewSoleProprietorQuestionsPage newSoleProprietorQuestions = new NewSoleProprietorQuestionsPage(webDriver);
            newSoleProprietorQuestions.NewSoleProprietorQuestions();
            // Financial section for first partner.
            FinancialSectionPage financialsection = new FinancialSectionPage(webDriver);
            financialsection.Financialsection();
            // Submit and review first partner information.SubmitAndReviewPage
            SubmitAndReviewPage submitAndReview = new SubmitAndReviewPage(webDriver);
            submitAndReview.SubmitAndReview();
            // Financial section for Second partner after analyst review.
            FinancialSectionSecondPartnerPage financialSectionSecondPartner = new FinancialSectionSecondPartnerPage(
                    webDriver);
            financialSectionSecondPartner.FinancialSectionSecondPartner();
            // Submit and Return the submitted certification back to vendor.
            SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1Page soleProprietorReturnCertFirstAndSecondPartnerAfterReview = new SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1Page(
                    webDriver);
            soleProprietorReturnCertFirstAndSecondPartnerAfterReview
                    .SoleProprietorReturnCertFirstAndSecondPartnerAfterReview1();
            // Login with the vendor and verify the return draft.
            get_The_Row_From_Login_Data = 8;
            LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data1.Login_With_Reference();
            WebElement ReturnDraft = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[5]"));
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
