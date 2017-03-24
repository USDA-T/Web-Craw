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

public class TestApp3708aBasicEligibilityNoSkipPossitive extends TestCase {
    private static final Logger logger = LogManager.getLogger(TestApp3708aBasicEligibilityNoSkipPossitive.class.getName());
    private static WebDriver webDriver;
    int get_The_Row_From_Login_Data;

    @Before
    public void setUp() throws Exception {
        webDriver = TestHelpers.getDefaultWebDriver();
        webDriver.get(TestHelpers.getBaseUrl());
        webDriver.manage().window().maximize();
        get_The_Row_From_Login_Data = 25;

        logger.info("FYI: your environment under test:" + System.getProperty(Constants.TEST_ENV));
    }

    @Test
    public void testMainTest() throws Exception {
      String Actual_Text = null;
      String Expected_Text = null;
        logger.info("8(a) Applicant determines their Basic Eligibility (i.e., a section) for the program");
        //Locate the accept button and click on it.
        //Login to dashboard.
        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
        login_Data.Login_With_Reference();
        Thread.sleep(3000);
        // Verify if there is an existing certification on the dashboard and
        // delete to start a new certification.
        DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
        deleteDraftCert.DeleteDraftCert();
        Thread.sleep(2000);
        webDriver.navigate().to("https://certify.qa.sba-one.net/questionnaires/eight_a_initial/sba_applications/new?application_type_id=initia");
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        //Verify the Basic Eligibility link.
        Actual_Text = webDriver.findElement(By.linkText("Basic Eligibility")).getText();
        Expected_Text = "Basic Eligibility";
        assertEquals(Actual_Text, Expected_Text);
        //Verify Status.
        Actual_Text = webDriver.findElement(By.cssSelector("td.not_started")).getText();
        Expected_Text = "NOT STARTED";
        assertEquals(Actual_Text, Expected_Text);
        //Click on the link to start eligibility check.
        webDriver.findElement(By.linkText("Basic Eligibility")).click();
        //Click on the accept button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        //===>Section 1: Eligibility Screening, Subsection 1.1: Screen. 
        //Verify and validate this question
        //Q1.1.a.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Is the applicant firm a for-profit business?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
        Expected_Text = "The applicant firm must be a for-profit business in order to qualify for the 8(a) Business Development Program. If the applicant firm is not a for-profit business, it is not eligible for program participation.";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes.
        webDriver.findElement(By.id("answers_181_value_yes")).click();
        //Q1.1.b.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_is_broker > fieldset > h4")).getText();
        Expected_Text = "Is the applicant firm operating as a broker?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_is_broker > fieldset > p")).getText();
        Expected_Text = "A broker adds no material value to an item being supplied to a procuring activity. If the applicant firm does not take ownership or possession of, or handle the item being procured with its own equipment or facilities, the applicant firm may be operating as a broker. Please note that SBA’s definition of ‘broker’ does not include real estate or investment brokers.";
        assertEquals(Actual_Text, Expected_Text);
        /////Verify link. +++Open when link added.
        //webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //String main_window = webDriver.getWindowHandle();
        //logger.info("Before switching, title is = certify.sba.gov");
        //webDriver.findElement(By.linkText("13 CFR § 124.108(d).")).click();
        //assertEquals(Actual_Text, Expected_Text);
        //java.util.Set<String> S = webDriver.getWindowHandles();
        //Iterator<String> i = S.iterator();
        //while (i.hasNext()) {
        //String Second_window = i.next();
        //if (!main_window.equalsIgnoreCase(Second_window)) {
        //webDriver.switchTo().window(Second_window);
        //logger.info("After switching title is = certify.sba.gov");
        //webDriver.close();
        //webDriver.switchTo().window(main_window);
        //logger.info("Back to manin_window = certify.sba.gov");
        //} else {
        //logger.info("Second Window is not thesame as first window");
        //}
        //}
        //Select No.
        webDriver.findElement(By.id("answers_182_value_no")).click();
        //Q1.1.c.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_generate_revenue > fieldset > h4")).getText();
        Expected_Text = "Has the firm generated any revenue?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section Paragraph1.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_generate_revenue > fieldset > p")).getText();
        Expected_Text = "Your firm must demonstrate potential for success by showing that it has been in business in its primary industry for two years, or you will need to request a waiver of this requirement. If the business concern has not yet generated revenues, you will not be successful in obtaining a waiver. Reference: 13 CFR § 124.107.\n\nPlease note that firms owned by Alaska Native Corporations, Tribes, Native Hawaiian Organizations, and Community Development Corporations have special rules regarding potential for success. Therefore, if your firm is entity-owned, select “Not applicable”. Reference: 13 CFR §§ 124.109(c)(6), 124.110(g), and 124.111(f).";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes.
        webDriver.findElement(By.id("answers_183_value_yes")).click();
        //Q1.1.d.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_disadvantaged_citizens > fieldset > h4")).getText();
        Expected_Text = "Are all of the individual(s) claiming disadvantaged status in the applicant firm U.S. citizens?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section Paragraph1.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_disadvantaged_citizens > fieldset > p")).getText();
        Expected_Text = "A U.S. citizen means a person born or naturalized in the United States. Resident aliens and holders of permanent visas are not considered to be citizens for program purposes. If the applicant firm is entity-owned, select ‘Not applicable’.";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes.
        webDriver.findElement(By.id("answers_184_value_yes")).click();
        //Q1.1.e.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_have_dba > fieldset > h4")).getText();
        Expected_Text = "Does the applicant firm have a Doing Business As (DBA) Name?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section Paragraph1.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_have_dba > fieldset > p")).getText();
        Expected_Text = "If yes, please add names. Doing Business As Name may include an Assumed Name, Fictitious Name, or Trade Name.";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes and enter comment.
        webDriver.findElement(By.id("answers_185_value_yes")).click();
        webDriver.findElement(By.id("answers_185_comment")).sendKeys("Cats are among the most feared and revered creatures on the planet.  Their power, strength, and enigmatic nature have fascinated us for centuries.  They’ve dominated human culture since the dawn of civilization.  Go from the rainforests, to the savannah, to the mountain peaks all the way into the comfort of our homes.  Get an in-depth look at this unique species and the evolutionary tricks and adaptations that truly make a cat, a cat. Cats are loving animals as all creature of mother nature are.");
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        //===>Subsection 1.2: Prior 8(a) Involvement.
        //Q1.2.a.
        //Verify and Validate this Question.
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Prior 8(a) Involvement";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Was the applicant firm ever a certified 8(a) Business Development Program Participant?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
        Expected_Text = "The 8(a) Business Development Program has one-time eligibility. The applicant firm must not have participated in the 8(a) Business Development Program in the past (including participation under another name, voluntary withdrawal, termination, or graduation).";
        assertEquals(Actual_Text, Expected_Text);
        //Select No.
        webDriver.findElement(By.id("answers_186_value_no")).click();
        //Q1.2.b.
        //Verify and Validate this Question.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_submitted_app_to_8a > fieldset > h4")).getText();
        Expected_Text = "Has the applicant firm ever submitted an application to the 8(a) Business Development Program?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_submitted_app_to_8a > fieldset > p")).getText();
        Expected_Text = "If yes, describe what happened to the previous application to the 8(a) Business Development Program. Possibilities may include that the applicant firm may have applied but failed to respond to a screening letter, or the applicant firm may have withdrawn the application prior to a formal decision, or the applicant firm may have been denied program participation. If the applicant firm was previously denied, it must wait one year from the date of the last denial letter to apply. Please include the date of the last denial letter. Please respond ‘yes’ if the applicant firm has ever submitted an application under another name.";
        assertEquals(Actual_Text, Expected_Text);
        //Select No.
        webDriver.findElement(By.id("answers_187_value_no")).click();
        //1.2.c
        //Verify and Validate this Question.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_previous_participant_assets_over_50_percent > fieldset > h4")).getText();
        Expected_Text = "Do the assets of a previously certified 8(a) Business Development Program Participant constitute 50% or more of applicant firm’s assets?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_previous_participant_assets_over_50_percent > fieldset > p")).getText();
        Expected_Text = "If yes, provide the name of the 8(a) Program Participant and any relevant acquisition documents. Assets may include machinery, contracts, equipment, etc. At the time of application, if the assets of a former 8(a) Program Participant constitute 50% or more of applicant firm’s assets, then the applicant firm will not be eligible for participation in the program because of one-time eligibility.";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes and attached a doc.
        webDriver.findElement(By.id("answers_188_value_yes")).click();
        //Upload document.
        String file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument.MontanaUploadDocument(file_path_abs);
        Thread.sleep(3000);
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        //===>Subsection 1.3: Outside Assistance.
        //1.3.a
        //Verify and Validate this Question.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Did the applicant firm hire an outside consultant to assist with its 8(a) application?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
        Expected_Text = "If yes, upload a signed representative information form, proof of payment and the consulting agreement (if applicable). Outside consultants may include attorneys, accountants, appraisers, agents, or other representatives who assisted in the preparation of the application.";
        assertEquals(Actual_Text, Expected_Text);
        //verify link.
        /////Verify link. +++Open when link added.
        //webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //String main_window = webDriver.getWindowHandle();
        //logger.info("Before switching, title is = certify.sba.gov");
        //webDriver.findElement(By.linkText("representative information")).click();
        //assertEquals(Actual_Text, Expected_Text);
        //java.util.Set<String> S1 = webDriver.getWindowHandles();
        //Iterator<String> i1 = S1.iterator();
        //while (i1.hasNext()) {
        //String Second_window = i1.next();
        //if (!main_window.equalsIgnoreCase(Second_window)) {
        //webDriver.switchTo().window(Second_window);
        //logger.info("After switching title is = certify.sba.gov");
        //webDriver.close();
        //webDriver.switchTo().window(main_window);
        //logger.info("Back to manin_window = certify.sba.gov");
        //} else {
        //logger.info("Second Window is not thesame as first window");
        //}
        //}
        //Select Yes and attached a doc.
        webDriver.findElement(By.id("answers_189_value_yes")).click();
        //Upload Doc.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument1 = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument1.MontanaUploadDocument(file_path_abs);
        Thread.sleep(3000);
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        //===>Subsection 1.4: Business Size.
        //Q1.4.a.
        //Verify and Validate this Question.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Is the applicant firm considered small in accordance with its primary North American Industry Classification System (NAICS) code?";
        assertEquals(Actual_Text, Expected_Text);
        //Detail Section.
        Actual_Text = webDriver.findElement(By.cssSelector("fieldset > p")).getText();
        Expected_Text = "If no, the applicant firm may be denied participation in the 8(a) Business Development Program, or a formal size determination may be requested by SBA officials.\n\nThe applicant firm must be small according to SBA’s size standards to participate in the program. Size standards have been established for types of economic activity, or industry, under the North American Industry Classification System (NAICS). To determine the size standard associated with a particular NAICS code, refer to the table of size standards in the Small Business Size Regulations, 13 CFR § 121.201. The applicable size standard is the one corresponding to the applicant firm’s primary NAICS code.";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes.
        webDriver.findElement(By.id("answers_190_value_yes")).click();
        //Q1.4.b.
        //Verify and Validate this Question.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_formal_determination > fieldset > h4")).getText();
        Expected_Text = "Has the applicant firm or any of its affiliates received a formal SBA size determination?";
        assertEquals(Actual_Text, Expected_Text);
        //Select Yes.
        webDriver.findElement(By.id("answers_191_value_yes")).click();
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        //===>Sub-Subsection 1.4.1: Size Determination.
        //Q1.4.1.a
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Please upload the size determination or redetermination letter(s) issued by SBA.";
        assertEquals(Actual_Text, Expected_Text);
        //Upload Doc.
        file_path_abs = FixtureUtils.fixturesDir() + "MainTestUploadDoc.pdf";
        MontanaUploadDocumentPage MontanaUploadDocument2 = new MontanaUploadDocumentPage(webDriver);
        MontanaUploadDocument2.MontanaUploadDocument(file_path_abs);
        //Q1.4.1.b.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_sba_area_office > fieldset > h4")).getText();
        Expected_Text = "Which SBA area office sent the most recent letter?";
        assertEquals(Actual_Text, Expected_Text);
        //Verify and Select area office.
        Actual_Text = webDriver.findElement(By.id("answers_193_value")).getText();
        Expected_Text = "SBA Area Office 1\nSBA Area Office 2\nSBA Area Office 3\nSBA Area Office 4\nSBA Area Office 5\nSBA Area Office 6\nSBA Headquarters";
        assertEquals(Actual_Text, Expected_Text);
        webDriver.findElement(By.id("answers_193_value")).click();
        Actions act = new Actions(webDriver);
        act.doubleClick(webDriver.findElement(By.xpath("//option[3]"))).build().perform();
        Thread.sleep(2000);
        //Q1.4.1.c.
        Actual_Text = webDriver.findElement(By.cssSelector("#answers_redetermination_date > fieldset > h4")).getText();
        Expected_Text = "What is the determination date stated in the most recent letter?";
        assertEquals(Actual_Text, Expected_Text);
        //Enter Date.
        webDriver.findElement(By.id("date-194")).sendKeys("09/20/2017");
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(2000);
        //===>Review section.
        Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
        Expected_Text = "8(A) Eligibility Summary";
        assertEquals(Actual_Text, Expected_Text);
        //Verify the section.
        Actual_Text = webDriver.findElement(By.cssSelector("div.review_questions.question-separator > h3")).getText();
        Expected_Text = "Screen";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[2]/h3")).getText();
        Expected_Text = "Prior 8(a) Involvement";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[3]/h3")).getText();
        Expected_Text = "Outside Assistance";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[4]/h3")).getText();
        Expected_Text = "Business Size";
        assertEquals(Actual_Text, Expected_Text);
        Actual_Text = webDriver.findElement(By.xpath("//div[5]/h3")).getText();
        Expected_Text = "Size Determination";
        assertEquals(Actual_Text, Expected_Text);
        //Click on the change answer for Outside Assistance and change it to yes.
        webDriver.findElement(By.cssSelector("#outside_consultant > div.usa-grid-full > div.usa-width-one-fourth > p > a")).click();
        //Verify.
        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
        Expected_Text = "Did the applicant firm hire an outside consultant to assist with its 8(a) application?";
        assertEquals(Actual_Text, Expected_Text);
        //Select No and continue.
        webDriver.findElement(By.id("answers_189_value_yes")).click();
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Business Size";
        assertEquals(Actual_Text, Expected_Text);
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Size Determination";
        assertEquals(Actual_Text, Expected_Text);
        //Click on the Save and Continue button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
        Expected_Text = "8(A) Eligibility Summary";
        assertEquals(Actual_Text, Expected_Text);        
        //Navigate back and verify in-progress status for the draft.
        webDriver.findElement(By.xpath("//a/span")).click();
        //click on the draft 8(a) Initial Program.
        webDriver.findElement(By.linkText("8(a) Initial Program")).click();
        Actual_Text = webDriver.findElement(By.xpath("//td[3]")).getText();
        Expected_Text = "IN PROGRESS";
        assertEquals(Actual_Text, Expected_Text);
        WebElement EligibilityInProgressStatus =webDriver.findElement(By.xpath("//td[3]"));
        HighLight.highLightElement(webDriver, EligibilityInProgressStatus);
        webDriver.findElement(By.linkText("Basic Eligibility")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("eight_a_basic_eligibility_size_determination")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();       
        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
        Expected_Text = "Review";
        assertEquals(Actual_Text, Expected_Text);       
        //Click on the Submit button.
        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
        webDriver.switchTo().alert().accept();
        //Verify status.
        Actual_Text = webDriver.findElement(By.xpath("//td[3]")).getText();
        Expected_Text = "COMPLETE";
        assertEquals(Actual_Text, Expected_Text);
        WebElement EligibilityCompleteStatus =webDriver.findElement(By.xpath("//td[3]"));
        HighLight.highLightElement(webDriver, EligibilityCompleteStatus);
        webDriver.findElement(By.linkText("Logout")).click();
        logger.info("Success");
 
    }

    @After
    public void tearDown() throws Exception {
        webDriver.quit();
    }
}
