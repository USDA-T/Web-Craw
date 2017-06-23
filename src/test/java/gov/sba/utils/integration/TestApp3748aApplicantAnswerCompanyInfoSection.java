package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gov.sba.automation.CoreUtils;
import gov.sba.automation.TestHelpers;
import junit.framework.TestCase;

public class TestApp3748aApplicantAnswerCompanyInfoSection extends TestCase {

    private static final Logger logger = LogManager
            .getLogger(TestApp3748aApplicantAnswerCompanyInfoSection.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();

        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 12;
    }

    @Test
    public void testMainTest() throws Exception {
        try {
            String Actual_Text;
            String Expected_Text;
            WebDriverWait wait = new WebDriverWait(webDriver, 30);
            logger.info(
                    "As an 8(a) applicant, I want to enter my business's company info sectionon an 8(a) application");
            // Login to dashboard.
            LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
            login_Data.Login_With_Reference();
            // delete to start a new certification.
            DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
            deleteDraftCert.DeleteDraftCert();
            // delete to start a new certification.
            DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
            deleteDraftCert1.DeleteDraftCert();
            webDriver.navigate().to(
                    "https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a_initial");
            // webDriver.navigate().to("http://localhost/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a");
            // Verify new intro page.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
            Actual_Text = webDriver.findElement(By.xpath("//form/div/div/p")).getText();
            Expected_Text = "The Federal government relies on the information in the forms and any documents or supplemental information submitted to determine whether your business is eligible to participate in the 8(a) Business Development Program. The definition of important terms are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 CFR § 124.3), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person providing information may be prosecuted if they have provided false information. The Government may pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
            assertEquals(Actual_Text, Expected_Text);
            CoreUtils.clickContinue(webDriver);
            // Verify the Basic Eligibility link.
            Actual_Text = webDriver.findElement(By.linkText("Company Info")).getText();
            Expected_Text = "Company Info";
            assertEquals(Actual_Text, Expected_Text);
            // Verify Status.
            Actual_Text = webDriver.findElement(By.xpath("//tr[6]/td[3]")).getText();
            Expected_Text = "NOT STARTED";
            assertEquals(Actual_Text, Expected_Text);
            // Click on the link to start eligibility check.
            webDriver.findElement(By.linkText("Company Info")).click();
            // Verify intro page.
            // Actual_Text =
            // webDriver.findElement(By.xpath("//form/div/div/p")).getText();
            // Expected_Text =
            // "The Federal government relies on the information in the forms
            // and any documents or
            // supplemental information submitted to determine whether your
            // business is eligible to
            // participate in the 8(a) Business Development Program. The
            // definition of important terms are
            // set forth in the Small Business Act, U.S. Small Business
            // Administration (SBA) regulations
            // (13 CFR § 124.3), and also any statutory and regulatory provision
            // referenced in those
            // authorities. In addition, please note that the SBA may request
            // further clarification or
            // supporting documentation in order to assist in the verification
            // of any of the information
            // provided and that each person providing information may be
            // prosecuted if they have provided
            // false information. The Government may pursue criminal, civil or
            // administrative remedies for
            // incorrect or incomplete information given, even if correct
            // information has been included in
            // other materials submitted to SBA.";
            // assertEquals(Actual_Text, Expected_Text);
            // Click on the accept button.
            // CoreUtils.clickContinue(webDriver);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Company Stuff";
            assertEquals(Actual_Text, Expected_Text);
            // Verify that both questions are required.
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
            Expected_Text = "Please answer this question";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[2]/fieldset/div/span")).getText();
            Expected_Text = "Please answer this question";
            assertEquals(Actual_Text, Expected_Text);
            // Verify question and detail section and Select Yes for the 1st
            // question.
            Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
            Expected_Text = "Have you performed work in the NAICS code in which you’re requesting business development assistance?";
            assertEquals(Actual_Text, Expected_Text);
            // Detail section.
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_prior_naics_code_work']/fieldset/p[2]"))
                    .getText();
            Expected_Text = "You must have prior experience in the NAICS code in the NAICS code in which you’re seeking assistance.";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.xpath("//div/input")).click();
            // Verify question and detail section and Select Yes for the 2nd
            // question.
            Actual_Text = webDriver.findElement(By.cssSelector("#answers_small_for_mpp_naics_code > fieldset > h4"))
                    .getText();
            Expected_Text = "Are you considered small for the NAICS code in which you’re requesting business development assistance?";
            assertEquals(Actual_Text, Expected_Text);
            // Detail section.
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_small_for_mpp_naics_code']/fieldset/p[2]"))
                    .getText();
            Expected_Text = "To qualify as a protégé firm, your business must qualify as small for the NAICS code for which it is seeking business development assistance. Size standards have been established for types of economic activity, or industry, under the North American Industry Classification System (NAICS). To determine the size standard associated with a particular NAICS code, refer to the table of size standards in the Small Business Size Regulations. Reference: 13 CFR 121.201";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.xpath("//div[2]/fieldset/div/input")).click();
            // Click on the continue button.
            CoreUtils.clickContinue(webDriver);
            // Verify page.
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "More Co Stuff";
            assertEquals(Actual_Text, Expected_Text);
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
            Expected_Text = "Please answer this question";
            assertEquals(Actual_Text, Expected_Text);
            // Verify question and detail section and Select Yes.
            Actual_Text = webDriver
                    .findElement(By.xpath("//div[@id='answers_have_redetermination_letter']/fieldset/h4")).getText();
            Expected_Text = "Have you received a size redetermination letter from the SBA that subsequently found you to be small in that NAICS code?";
            assertEquals(Actual_Text, Expected_Text);
            // NO Detail section.
            webDriver.findElement(By.xpath("//div/input")).click();
            // Click on the on the Save and continue button and verify that user
            // is navigated to the
            // review
            // page.
            CoreUtils.clickContinue(webDriver);
            // Review Section.
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Review";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.cssSelector("div.review_questions.question-separator > h3"))
                    .getText();
            Expected_Text = "Company Stuff";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-three-fourths > p")).getText();
            Expected_Text = "Have you performed work in the NAICS code in which you’re requesting business development assistance?";
            assertEquals(Actual_Text, Expected_Text);
            // 2nd section.
            Actual_Text = webDriver.findElement(By.xpath("//div[2]/h3")).getText();
            Expected_Text = "More Co Stuff";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver
                    .findElement(By.cssSelector(
                            "#have_redetermination_letter > div.usa-grid-full > div.usa-width-three-fourths > p"))
                    .getText();
            Expected_Text = "Have you received a size redetermination letter from the SBA that subsequently found you to be small in that NAICS code?";
            assertEquals(Actual_Text, Expected_Text);
            // click on change answer and make changes and verify.
            webDriver.findElement(By.xpath("//p/a")).click();
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Company Stuff";
            assertEquals(Actual_Text, Expected_Text);
            // Change first question from yes to NO.
            webDriver.findElement(By.xpath("//label[2]")).click();
            // Click on the continue button.
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "More Co Stuff";
            assertEquals(Actual_Text, Expected_Text);
            // Click on the continue button.
            CoreUtils.clickContinue(webDriver);
            // Verify that question was updated to NO.
            Actual_Text = webDriver.findElement(By.xpath("//li/div/div/div/div[2]")).getText();
            Expected_Text = "Response: No";
            assertEquals(Actual_Text, Expected_Text);
            // Navigate back and verify in-progress status for the draft.
            webDriver.findElement(By.xpath("//a/span")).click();
            // click on the draft 8(a) Initial Program.
            webDriver.findElement(By.linkText("8(a) Initial Program")).click();
            Actual_Text = webDriver.findElement(By.xpath("//tr[6]/td[3]")).getText();
            Expected_Text = "IN PROGRESS";
            assertEquals(Actual_Text, Expected_Text);
            WebElement CompanyInfoInProgressStatus = webDriver.findElement(By.xpath("//tr[6]/td[3]"));
            HighLight.highLightElement(webDriver, CompanyInfoInProgressStatus);
            webDriver.findElement(By.linkText("Company Info")).click();
            // Thread.sleep(2000);
            webDriver.findElement(By.id("eight_a_company_stuff_too")).click();
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Review";
            assertEquals(Actual_Text, Expected_Text);
            CoreUtils.clickContinue(webDriver);
            // webDriver.switchTo().alert().accept();
            // Click on the Save and Continue button.
            // Verify status.
            Actual_Text = webDriver.findElement(By.xpath("//tr[6]/td[3]")).getText();
            Expected_Text = "COMPLETE";
            assertEquals(Actual_Text, Expected_Text);
            WebElement CompanyInfoInComplteStatus = webDriver.findElement(By.xpath("//tr[6]/td[3]"));
            HighLight.highLightElement(webDriver, CompanyInfoInComplteStatus);
            // Scenario two for user who select all no.
            logger.info("Starting new scenario for user who select NO.");
            webDriver.findElement(By.xpath("//a/span")).click();
            // delete to start a new certification.
            DeleteDraftCertPage deleteDraftCert2 = new DeleteDraftCertPage(webDriver);
            deleteDraftCert2.DeleteDraftCert();
            webDriver.navigate().to(
                    "https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a_initial");
            // webDriver.navigate().to("http://localhost/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initial&certificate_type_id=eight_a");
            // Verify new intro page.
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2")));
            Actual_Text = webDriver.findElement(By.xpath("//form/div/div/p")).getText();
            Expected_Text = "The Federal government relies on the information in the forms and any documents or supplemental information submitted to determine whether your business is eligible to participate in the 8(a) Business Development Program. The definition of important terms are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 CFR § 124.3), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person providing information may be prosecuted if they have provided false information. The Government may pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
            assertEquals(Actual_Text, Expected_Text);
            CoreUtils.clickContinue(webDriver);
            // Verify the Basic Eligibility link.
            Actual_Text = webDriver.findElement(By.linkText("Company Info")).getText();
            Expected_Text = "Company Info";
            assertEquals(Actual_Text, Expected_Text);
            // Verify Status.
            Actual_Text = webDriver.findElement(By.xpath("//tr[6]/td[3]")).getText();
            Expected_Text = "NOT STARTED";
            assertEquals(Actual_Text, Expected_Text);
            // Click on the link to start eligibility check.
            webDriver.findElement(By.linkText("Company Info")).click();
            // Verify intro page.
            Actual_Text = webDriver.findElement(By.xpath("//form/div/div/p")).getText();
            Expected_Text = "The Federal government relies on the information in the forms and any documents or supplemental information submitted to determine whether your business is eligible to participate in the 8(a) Business Development Program. The definition of important terms are set forth in the Small Business Act, U.S. Small Business Administration (SBA) regulations (13 CFR § 124.3), and also any statutory and regulatory provision referenced in those authorities. In addition, please note that the SBA may request further clarification or supporting documentation in order to assist in the verification of any of the information provided and that each person providing information may be prosecuted if they have provided false information. The Government may pursue criminal, civil or administrative remedies for incorrect or incomplete information given, even if correct information has been included in other materials submitted to SBA.";
            assertEquals(Actual_Text, Expected_Text);
            // Click on the accept button.
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Company Stuff";
            assertEquals(Actual_Text, Expected_Text);
            // Verify that both questions are required.
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
            Expected_Text = "Please answer this question";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.xpath("//div[2]/fieldset/div/span")).getText();
            Expected_Text = "Please answer this question";
            assertEquals(Actual_Text, Expected_Text);
            // Verify question and detail section and Select NO for the 1st
            // question.
            Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
            Expected_Text = "Have you performed work in the NAICS code in which you’re requesting business development assistance?";
            assertEquals(Actual_Text, Expected_Text);
            // Detail section.
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_prior_naics_code_work']/fieldset/p[2]"))
                    .getText();
            Expected_Text = "You must have prior experience in the NAICS code in the NAICS code in which you’re seeking assistance.";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.xpath("//label[2]")).click();
            // Verify question and detail section and Select NO for the 2nd
            // question.
            Actual_Text = webDriver.findElement(By.cssSelector("#answers_small_for_mpp_naics_code > fieldset > h4"))
                    .getText();
            Expected_Text = "Are you considered small for the NAICS code in which you’re requesting business development assistance?";
            assertEquals(Actual_Text, Expected_Text);
            // Detail section.
            Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_small_for_mpp_naics_code']/fieldset/p[2]"))
                    .getText();
            Expected_Text = "To qualify as a protégé firm, your business must qualify as small for the NAICS code for which it is seeking business development assistance. Size standards have been established for types of economic activity, or industry, under the North American Industry Classification System (NAICS). To determine the size standard associated with a particular NAICS code, refer to the table of size standards in the Small Business Size Regulations. Reference: 13 CFR 121.201";
            assertEquals(Actual_Text, Expected_Text);
            webDriver.findElement(By.xpath("//div[2]/fieldset/div/label[2]")).click();
            // Click on the continue button.
            CoreUtils.clickContinue(webDriver);
            // Verify page.
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "More Co Stuff";
            assertEquals(Actual_Text, Expected_Text);
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.xpath("//div/span")).getText();
            Expected_Text = "Please answer this question";
            assertEquals(Actual_Text, Expected_Text);
            // Verify question and detail section and Select Yes.
            Actual_Text = webDriver
                    .findElement(By.xpath("//div[@id='answers_have_redetermination_letter']/fieldset/h4")).getText();
            Expected_Text = "Have you received a size redetermination letter from the SBA that subsequently found you to be small in that NAICS code?";
            assertEquals(Actual_Text, Expected_Text);
            // NO Detail section.
            webDriver.findElement(By.xpath("//label[2]")).click();
            // Click on the on the Save and continue button and verify that user
            // is navigated to the
            // review
            // page.
            CoreUtils.clickContinue(webDriver);
            // Review Section.
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Review";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.cssSelector("div.review_questions.question-separator > h3"))
                    .getText();
            Expected_Text = "Company Stuff";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-three-fourths > p")).getText();
            Expected_Text = "Have you performed work in the NAICS code in which you’re requesting business development assistance?";
            assertEquals(Actual_Text, Expected_Text);
            // 2nd section.
            Actual_Text = webDriver.findElement(By.xpath("//div[2]/h3")).getText();
            Expected_Text = "More Co Stuff";
            assertEquals(Actual_Text, Expected_Text);
            Actual_Text = webDriver
                    .findElement(By.cssSelector(
                            "#have_redetermination_letter > div.usa-grid-full > div.usa-width-three-fourths > p"))
                    .getText();
            Expected_Text = "Have you received a size redetermination letter from the SBA that subsequently found you to be small in that NAICS code?";
            assertEquals(Actual_Text, Expected_Text);
            // click on change answer and make changes and verify.
            webDriver.findElement(By.xpath("//p/a")).click();
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Company Stuff";
            assertEquals(Actual_Text, Expected_Text);
            // Change first question from NO to Yes.
            webDriver.findElement(By.xpath("//div/input")).click();
            // Click on the continue button.
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "More Co Stuff";
            assertEquals(Actual_Text, Expected_Text);
            // Click on the continue button.
            CoreUtils.clickContinue(webDriver);
            // Verify that question was updated to NO.
            Actual_Text = webDriver.findElement(By.xpath("//li/div/div/div/div[2]")).getText();
            Expected_Text = "Response: Yes";
            assertEquals(Actual_Text, Expected_Text);
            // Navigate back and verify in-progress status for the draft.
            webDriver.findElement(By.xpath("//a/span")).click();
            // click on the draft 8(a) Initial Program.
            webDriver.findElement(By.linkText("8(a) Initial Program")).click();
            Actual_Text = webDriver.findElement(By.xpath("//tr[6]/td[3]")).getText();
            Expected_Text = "IN PROGRESS";
            assertEquals(Actual_Text, Expected_Text);
            WebElement CompanyInfoInProgressStatus1 = webDriver.findElement(By.xpath("//tr[6]/td[3]"));
            HighLight.highLightElement(webDriver, CompanyInfoInProgressStatus1);
            webDriver.findElement(By.linkText("Company Info")).click();
            // Thread.sleep(2000);
            webDriver.findElement(By.id("eight_a_company_stuff_too")).click();
            CoreUtils.clickContinue(webDriver);
            Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
            Expected_Text = "Review";
            assertEquals(Actual_Text, Expected_Text);
            CoreUtils.clickContinue(webDriver);
            // webDriver.switchTo().alert().accept();
            // Click on the Save and Continue button.
            // Verify status.
            Actual_Text = webDriver.findElement(By.xpath("//tr[6]/td[3]")).getText();
            Expected_Text = "COMPLETE";
            assertEquals(Actual_Text, Expected_Text);
            WebElement CompanyInfoInComplteStatus2 = webDriver.findElement(By.xpath("//tr[6]/td[3]"));
            HighLight.highLightElement(webDriver, CompanyInfoInComplteStatus2);
            webDriver.findElement(By.linkText("Logout")).click();
        } catch (Exception e) {
            ScreenShotPage screenShot = new ScreenShotPage(webDriver);
            screenShot.ScreenShot();
            logger.info(e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
