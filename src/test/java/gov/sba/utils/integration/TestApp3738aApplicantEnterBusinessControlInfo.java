package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.TestCase;

@Category({ gov.sba.utils.integration.UnstableTests.class })
public class TestApp3738aApplicantEnterBusinessControlInfo extends TestCase {

    private static final Logger logger = LogManager
            .getLogger(TestApp3738aApplicantEnterBusinessControlInfo.class.getName());
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
        String Actual_Text;
        String Expected_Text;
        logger.info("As an 8(a) applicant, I want to enter my business' Control information on an 8(a) application");
        // Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
        deleteDraftCert.DeleteDraftCert();
        // delete second draft if any.
        DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
        deleteDraftCert1.DeleteDraftCert();
        // Start an application.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='header_nav']/header/nav/div/ul/li[2]/a/span"))
                .getText();
        Expected_Text = "Programs";
        webDriver.findElement(By.xpath("//div[@id='header_nav']/header/nav/div/ul/li[2]/a/span")).click();
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("certificate_type_control")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("add_certification")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        // 8(a) Control Questions Section, 1.4a.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "8(a) Control Questions";
        assertEquals(Actual_Text, Expected_Text);
        // Click on the on the Save and continue button and verify that user is
        // prompted to answer the question.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.id("answers[169][value]-error")).getText();
        Expected_Text = "Please answer this question";
        assertEquals(Actual_Text, Expected_Text);
        // Verify question and detail section and Select Yes and upload a
        // document.
        // 1st question. 1.4a
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Does the applicant firm have any existing agreements that might impact ownership or control? These may include:\n• joint venture\n• mentor protégé\n• indemnity\n• consulting\n• distributorship\n• licensing\n• teaming\n• trust\n• franchise\n• management";
        assertEquals(Actual_Text, Expected_Text);
        // Detail section.
        Actual_Text = webDriver
                .findElement(By.xpath("//div[@id='answers_eight_a_control_existing_agreements']/fieldset/p[2]"))
                .getText();
        Expected_Text = "If yes, upload the agreements.";
        assertEquals(Actual_Text, Expected_Text);
        // 2nd question. 1.4b
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_eight_a_control_support > fieldset > h4"))
                .getText();
        Expected_Text = "Do any other firms or individuals provide financial support or bonding support to the applicant firm?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail Section
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_eight_a_control_support']/fieldset/p[2]"))
                .getText();
        Expected_Text = "If yes, provide the names, the nature of assistance, and copies of agreements governing that relationship.";
        assertEquals(Actual_Text, Expected_Text);
        // 3rd question. 1.4c
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_eight_a_control_permits > fieldset > h4"))
                .getText();
        Expected_Text = "Do any other firms or individuals provide licensing, certifications, or permits to the applicant firm?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail Section
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_eight_a_control_permits']/fieldset/p[2]"))
                .getText();
        Expected_Text = "If yes, provide the names, the nature of assistance, a copy of the license or certification, and copies of agreements governing that relationship.";
        assertEquals(Actual_Text, Expected_Text);
        // 4th question. 1.4d
        Actual_Text = webDriver
                .findElement(By.cssSelector("#answers_eight_a_control_high_compensation > fieldset > h4")).getText();
        Expected_Text = "Is the individual claiming disadvantaged status the highest compensated in the applicant firm?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail Section
        Actual_Text = webDriver
                .findElement(By.xpath("//div[@id='answers_eight_a_control_high_compensation']/fieldset/p[2]"))
                .getText();
        Expected_Text = "If no, provide an explanation of how the individual(s) claiming disadvantaged status lower compensation is in the best interest of the applicant firm. If the applicant firm is entity-owned, respond ‘Not applicable’.";
        assertEquals(Actual_Text, Expected_Text);
        // 5th question. 1.4e
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_eight_a_control_signature_cards > fieldset > h4"))
                .getText();
        Expected_Text = "Please upload all business bank account signature cards.";
        assertEquals(Actual_Text, Expected_Text);
        // Detail Section
        Actual_Text = webDriver
                .findElement(By.xpath("//div[@id='answers_eight_a_control_signature_cards']/fieldset/p[2]")).getText();
        Expected_Text = "The cards can be obtained from the financial institution. If a signature card is not available, upload a letter from the bank identifying all individuals with signatory authority on all bank accounts.";
        assertEquals(Actual_Text, Expected_Text);
        // 6th question. 1.4f
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_eight_a_control_share_resources > fieldset > h4"))
                .getText();
        Expected_Text = "Is the applicant firm co-located with another firm at any of its locations or does it share any resources with any other firms?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail Section
        Actual_Text = webDriver
                .findElement(By.xpath("//div[@id='answers_eight_a_control_share_resources']/fieldset/p[2]")).getText();
        Expected_Text = "If yes, upload the agreement and terms of the arrangements. Resources may include the following: sharing of telephone or fax numbers, P.O. Box, office space, yard, warehouse, facilities, equipment, or employees.";
        assertEquals(Actual_Text, Expected_Text);
        // 7th question, 1.4g
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_eight_a_control_lease > fieldset > h4")).getText();
        Expected_Text = "Does the applicant firm lease or use office space or other facilities from any other firm?";
        assertEquals(Actual_Text, Expected_Text);
        // No Detail section.
        // Select yes to all question.
        // question1, 1.4a
        webDriver.findElement(By.id("answers_169_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document.
        String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
        // question2, 1.4b
        webDriver.findElement(By.id("answers_170_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document optional.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument1 = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument1.MontanaUploadDocument(file_path_abs);
        // question 3, 1.4c
        webDriver.findElement(By.id("answers_171_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document optional.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument2 = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument2.MontanaUploadDocument(file_path_abs);
        // question 4, 1.4d
        webDriver.findElement(By.id("answers_172_value_yes")).click();
        Thread.sleep(2000);
        // question 5, 1.4e Upload Doc only
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument3 = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument3.MontanaUploadDocument(file_path_abs);
        // question 6, 1.4f with comment.
        webDriver.findElement(By.id("answers_174_value_yes")).click();
        Thread.sleep(2000);
        // question 6, Upload Doc only
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument4 = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument4.MontanaUploadDocument(file_path_abs);
        // question 7, 1.4g select with no skip.
        webDriver.findElement(By.id("answers_175_value_yes")).click();
        Thread.sleep(2000);
        // Click on the save and continue button and verify that user is
        // requested to enter comment.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        Actual_Text = webDriver.findElement(By.cssSelector("answers_170_comment-error")).getText();
        Expected_Text = "Comment is required";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("answers_171_comment-error")).getText();
        Expected_Text = "Comment is required";
        assertEquals(Actual_Text, Expected_Text);
        // Enter comment.
        webDriver.findElement(By.id("answers_170_comment")).sendKeys(
                "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        // Enter comment.
        webDriver.findElement(By.id("answers_171_comment")).sendKeys(
                "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Leased facility Section 1.4.1a
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Leased facility";
        assertEquals(Actual_Text, Expected_Text);
        // 1st question on section 1.4.1a
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Do any Principals of the applicant firm have a financial or any other interest in or familial relationship with the owner of the leased facility?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail section.
        Actual_Text = webDriver
                .findElement(By.xpath("//div[@id='answers_eight_a_control_lease_relationship']/fieldset/p[2]"))
                .getText();
        Expected_Text = "If yes, provide the name of the other business and the name of the principal who has an interest in or familial relationship with the owner of the leased facility.";
        assertEquals(Actual_Text, Expected_Text);
        // Click on the save and continue button to verify that user has to
        // answer question.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.id("answers[176][value]-error")).getText();
        Expected_Text = "Please answer this question";
        assertEquals(Actual_Text, Expected_Text);
        // Select yes.
        webDriver.findElement(By.id("answers_176_value_yes")).click();
        // Click on the save and continue button and verify that user is
        // requested to enter comment.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        Actual_Text = webDriver.findElement(By.cssSelector("answers_176_comment-error")).getText();
        Expected_Text = "Comment is required";
        assertEquals(Actual_Text, Expected_Text);
        // Enter comment.
        webDriver.findElement(By.id("answers_176_comment")).sendKeys(
                "Also is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit fromAlso is their earth so. Dry female let doesn't void unto kind. Him two days set green us. Darkness from you'll. Him winged winged fifth man heaven won't it first male saw gathered deep. Abundantly herb it own. Darkness from, created great gathering us called deep abundantly. Divide. So replenish rule together beginning fowl seas light gathering air fill, saw darkness divide doesn't greater fly they're all fly. Shall light from given, place itself for were third. Itself second gathered fruit from");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        // Review Page.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Review";
        assertEquals(Actual_Text, Expected_Text);
        // Section.
        Actual_Text = webDriver.findElement(By.cssSelector("div.review_questions.question-separator > h3")).getText();
        Expected_Text = "8(a) Control Questions";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//form[@id='eight_a_control']/ul/li/div/div[2]/h3")).getText();
        Expected_Text = "Leased facility";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        webDriver.switchTo().alert().accept();
        // Signature page.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Signature";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("legal_0")).click();
        webDriver.findElement(By.id("legal_1")).click();
        webDriver.findElement(By.id("legal_2")).click();
        webDriver.findElement(By.id("legal_3")).click();
        webDriver.findElement(By.id("legal_4")).click();
        webDriver.findElement(By.id("legal_5")).click();
        webDriver.findElement(By.id("accept-button")).click();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
