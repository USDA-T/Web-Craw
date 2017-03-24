package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import junit.framework.TestCase;

public class TestApp375Applicant8aCharacterSectionYes extends TestCase {

    private static final Logger logger = LogManager.getLogger(TestApp375Applicant8aCharacterSectionYes.class.getName());
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
        logger.info("As an 8(a) applicant, I want to enter my business's Character information on an 8(a) application");
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
        deleteDraftCert.DeleteDraftCert();
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert1 = new DeleteDraftCertPage(webDriver);
        deleteDraftCert1.DeleteDraftCert();
        Thread.sleep(2000);
        webDriver.navigate().to("https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initia");
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        //Verify the Basic Eligibility link.
        Actual_Text = webDriver.findElement(By.linkText("Character")).getText();
        Expected_Text = "Character";
        assertEquals(Actual_Text, Expected_Text);
        //Verify Status.
        Actual_Text = webDriver.findElement(By.cssSelector("td.not_started")).getText();
        Expected_Text = "NOT STARTED";
        assertEquals(Actual_Text, Expected_Text);
        //Click on the link to start eligibility check.
        webDriver.findElement(By.linkText("Character")).click();
        //Click on the accept button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);  
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Character";
        assertEquals(Actual_Text, Expected_Text);
        // Click on the on the Save and continue button and verify that user is
        // prompted to answer the question.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.id("answers[169][value]-error")).getText();
        Expected_Text = "Please answer this question";
        assertEquals(Actual_Text, Expected_Text);
        // Verify question and detail section and Select Yes and upload a
        // document.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Has the applicant firm (under any name) ever been debarred or suspended by any Federal entity?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail section.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16a']/fieldset/p[2]")).getText();
        Expected_Text = "If yes, provide the details regarding the debarment or suspension. Debarred or suspended firms or firms owned by debarred or suspended persons are ineligible for admission to the 8(a) Business Development program. If you have documents showing the debarment has been lifted please upload.";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("answers_169_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document.
        String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
        Thread.sleep(2000);
        // Click on the on the Save and continue button and verify that user is
        // prompted to answer the question.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.id("answers[170][value]-error")).getText();
        Expected_Text = "Please answer this question";
        assertEquals(Actual_Text, Expected_Text);
        // Verify question and detail section and Select Yes and upload a
        // document.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_character_16b > fieldset > h4")).getText();
        Expected_Text = "Does the applicant firm have any outstanding delinquent Federal, state or local financial obligations or liens filed against it?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail section.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16b']/fieldset/p[2]")).getText();
        Expected_Text = "Report any obligations including delinquent tax returns and delinquent SBA loans. If yes, provide any of the following that may apply:";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("answers_170_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        Upload2pdfOnSamePage upload2pdfOnSame = new Upload2pdfOnSamePage(webDriver);
        upload2pdfOnSame.Upload2pdfOnSame(file_path_abs);
        Thread.sleep(2000);
        // Pending Lawsuit section.
        // Click on the on the Save and continue button and verify that user is
        // prompted to answer the question.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.id("answers[171][value]-error")).getText();
        Expected_Text = "Please answer this question";
        assertEquals(Actual_Text, Expected_Text);
        // Verify question and detail section and Select Yes and upload a
        // document.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_character_16c > fieldset > h4")).getText();
        Expected_Text = "Is the applicant firm a defendant in any pending lawsuit?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail section.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16c']/fieldset/p[2]")).getText();
        Expected_Text = "If yes, summarize its interest in the suit, the claims, the current status, and provide a copy of the complaint, answer, and/or counterclaim filed in the suit.";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("answers_171_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        Upload3pdfOnSamePage upload3pdfOnSame1 = new Upload3pdfOnSamePage(webDriver);
        upload3pdfOnSame1.Upload3pdfOnSame(file_path_abs);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        // Bankruptcy Section.
        Actual_Text = webDriver.findElement(By.id("answers[172][value]-error")).getText();
        Expected_Text = "Please answer this question";
        assertEquals(Actual_Text, Expected_Text);
        // Verify question and detail section and Select Yes and upload a
        // document.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_character_16d > fieldset > h4")).getText();
        Expected_Text = "Has the applicant firm filed for bankruptcy or insolvency within the past 7 years?";
        assertEquals(Actual_Text, Expected_Text);
        // Detail section.
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16d']/fieldset/p[2]")).getText();
        Expected_Text = "If yes, provide details and a copy of the bankruptcy court’s final order or discharge.";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("answers_172_value_yes")).click();
        Thread.sleep(2000);
        // Upload a document.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        Upload4pdfOnSamePage upload4pdfOnSame2 = new Upload4pdfOnSamePage(webDriver);
        upload4pdfOnSame2.Upload4pdfOnSame(file_path_abs);
        Thread.sleep(2000);
        Actions act = new Actions(webDriver);
        act.doubleClick(webDriver.findElement(By.xpath("//input[@name='commit']"))).build().perform();
        Thread.sleep(6000);
        // Review Section.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Review";
        assertEquals(Actual_Text, Expected_Text);
        Thread.sleep(2000);
        Actual_Text = webDriver.findElement(By.cssSelector("div.review_questions.question-separator > h3")).getText();
        Expected_Text = "Character";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("div.usa-width-three-fourths > p")).getText();
        Expected_Text = "Has the applicant firm (under any name) ever been debarred or suspended by any Federal entity?";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("#character_16b > div.usa-grid-full > div.usa-width-three-fourths > p")).getText();
        Expected_Text = "Does the applicant firm have any outstanding delinquent Federal, state or local financial obligations or liens filed against it?";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[@id='character_16c']/div/div/p")).getText();
        Expected_Text = "Is the applicant firm a defendant in any pending lawsuit?";
        assertEquals(Actual_Text, Expected_Text);
        //Navigate back and verify in-progress status for the draft.
        webDriver.findElement(By.xpath("//a/span")).click();
        //click on the draft 8(a) Initial Program.
        webDriver.findElement(By.linkText("8(a) Initial Program")).click();
        Actual_Text = webDriver.findElement(By.xpath("//tr[2]/td[3]")).getText();
        Expected_Text = "IN PROGRESS";
        assertEquals(Actual_Text, Expected_Text);
        WebElement CharacterInProgressStatus =webDriver.findElement(By.xpath("//tr[2]/td[3]"));
        HighLight.highLightElement(webDriver, CharacterInProgressStatus);
        webDriver.findElement(By.linkText("Character")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("character")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();         
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Review";
        assertEquals(Actual_Text, Expected_Text); 
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        webDriver.switchTo().alert().accept();
        //Click on the Save and Continue button.
        //Verify status.
        Thread.sleep(2000);
        Actual_Text = webDriver.findElement(By.xpath("//tr[2]/td[3]")).getText();
        Expected_Text = "COMPLETE";
        assertEquals(Actual_Text, Expected_Text);
        WebElement CharacterComplteStatus =webDriver.findElement(By.xpath("//tr[2]/td[3]"));
        HighLight.highLightElement(webDriver, CharacterComplteStatus);
        webDriver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
