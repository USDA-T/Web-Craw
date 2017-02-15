package gov.sba.utils.integration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import junit.framework.TestCase;

	public class TestApp375Applicant8aEnterSectionNo extends TestCase {

		private static final Logger logger = LogManager.getLogger(TestApp375Applicant8aEnterSectionNo.class.getName());
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
	        //Login to dashboard.
	        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
	        login_Data.Login_With_Reference();
	        Thread.sleep(3000);
	        //Start an application.
	        Actual_Text = webDriver.findElement(By.linkText("All Small Business Mentor-Protégé agreement")).getText();
	        Expected_Text = "All Small Business Mentor-Protégé agreement";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.linkText("All Small Business Mentor-Protégé agreement")).click();
	        //click on the accept button.
	        //Debarred Section.
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Debarred";
	        assertEquals(Actual_Text, Expected_Text);
	        //Click on the on the Save and continue button and verify that user is prompted to answer the question.
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.id("answers[169][value]-error")).getText();
	        Expected_Text = "Please answer this question";
	        assertEquals(Actual_Text, Expected_Text);
	        //Verify question and detail section and Select Yes and upload a document.
	        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
	        Expected_Text = "Has the applicant firm (under any name) ever been debarred or suspended by any Federal entity?";
	        assertEquals(Actual_Text, Expected_Text);
	        //Detail section.
	        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16a']/fieldset/p[2]")).getText();
	        Expected_Text = "If yes, provide the details regarding the debarment or suspension. Debarred or suspended firms or firms owned by debarred or suspended persons are ineligible for admission to the 8(a) Business Development program. If you have documents showing the debarment has been lifted please upload.";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.id("answers_169_value_no")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        //Delinquent Liens Section.
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Delinquent Liens";
	        assertEquals(Actual_Text, Expected_Text);
	        //Click on the on the Save and continue button and verify that user is prompted to answer the question.
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.id("answers[170][value]-error")).getText();
	        Expected_Text = "Please answer this question";
	        assertEquals(Actual_Text, Expected_Text);
	        //Verify question and detail section and Select Yes and upload a document.
	        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
	        Expected_Text = "Does the applicant firm have any outstanding delinquent Federal, state or local financial obligations or liens filed against it?";
	        assertEquals(Actual_Text, Expected_Text);
	        //Detail section.
	        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16b']/fieldset/p[2]")).getText();
	        Expected_Text = "Report any obligations including delinquent tax returns and delinquent SBA loans. If yes, provide any of the following that may apply:";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.id("answers_170_value_no")).click();
		    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        //Pending Lawsuit section.
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Pending Lawsuit";
	        assertEquals(Actual_Text, Expected_Text);
	        //Click on the on the Save and continue button and verify that user is prompted to answer the question.
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.id("answers[171][value]-error")).getText();
	        Expected_Text = "Please answer this question";
	        assertEquals(Actual_Text, Expected_Text);
	        //Verify question and detail section and Select Yes and upload a document.
	        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
	        Expected_Text = "Is the applicant firm a defendant in any pending lawsuit?";
	        assertEquals(Actual_Text, Expected_Text);
	        //Detail section.
	        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16c']/fieldset/p[2]")).getText();
	        Expected_Text = "If yes, summarize its interest in the suit, the claims, the current status, and provide a copy of the complaint, answer, and/or counterclaim filed in the suit.";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.id("answers_171_value_no")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        //Bankruptcy Section.
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Bankruptcy";
	        assertEquals(Actual_Text, Expected_Text);
	        //Click on the on the Save and continue button and verify that user is prompted to answer the question.
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.id("answers[171][value]-error")).getText();
	        Expected_Text = "Please answer this question";
	        assertEquals(Actual_Text, Expected_Text);
	        //Verify question and detail section and Select Yes and upload a document.
	        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
	        Expected_Text = "Has the applicant firm filed for bankruptcy or insolvency within the past 7 years?";
	        assertEquals(Actual_Text, Expected_Text);
	        //Detail section.
	        Actual_Text = webDriver.findElement(By.xpath("//div[@id='answers_character_16d']/fieldset/p[2]")).getText();
	        Expected_Text = "If yes, provide details and a copy of the bankruptcy court’s final order or discharge.";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.id("answers_171_value_no")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        //Review Section.
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Review";
	        assertEquals(Actual_Text, Expected_Text);
	        Actual_Text = webDriver.findElement(By.cssSelector("div.review_questions.question-separator > h3")).getText();
	        Expected_Text = "Debarred";
	        assertEquals(Actual_Text, Expected_Text);
	        Actual_Text = webDriver.findElement(By.xpath("//form[@id='character']/ul/li/div/div[2]/h3")).getText();
	        Expected_Text = "Delinquent Liens";
	        assertEquals(Actual_Text, Expected_Text);
	        Actual_Text = webDriver.findElement(By.xpath("//form[@id='character']/ul/li/div/div[3]/h3")).getText();
	        Expected_Text = "Pending Lawsuit";
	        assertEquals(Actual_Text, Expected_Text);
	        Actual_Text = webDriver.findElement(By.xpath("//form[@id='character']/ul/li/div/div[4]/h3")).getText();
	        Expected_Text = "Bankruptcy";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        //Signature Section.
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Signature";
	        assertEquals(Actual_Text, Expected_Text);
	        //Signature.
	        webDriver.findElement(By.id("accept-button")).click();    
	    }

	    @After
	    public void tearDown() throws Exception {
	        webDriver.close();
	    }
	}
