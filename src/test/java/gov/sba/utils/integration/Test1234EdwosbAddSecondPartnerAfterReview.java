package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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
        logger.info("Test EDWOSB Sole-Proprietorship two partners on form413 with review");
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // Verify if there is an existing certification on the dashboard and
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
        deleteDraftCert.DeleteDraftCert();
        // Verify for active and Draft certification on the dashboard, if draft
        // delete and start a new one.
        Thread.sleep(2000);
        AddOrStartCertificationPage addOrStartCertification = new AddOrStartCertificationPage(webDriver);
        addOrStartCertification.AddOrStartCertification();
        Thread.sleep(2000);
        // partnership test for 1st person.
        SoleProprietorQuestionsPage soleProprietorQuestions = new SoleProprietorQuestionsPage(webDriver);
        soleProprietorQuestions.SoleProprietorQuestions();
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
        logger.info("Success");
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
