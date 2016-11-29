package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.TestCase;

public class TestApp99App90App100 extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestApp99App90App100.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 4;

	}

	@Test
	public void testMainTest() throws Exception {
		String Actual_Text=null;
		String Expected_Text=null;
		logger.info("Implement 8a page permissions - Copy MPP analyst permissions");
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		// Verify if there is an existing Draft certification on the dashboard and delete to start a new certification.
		DeleteDraftCertPage deleteDraftCert = new DeleteDraftCertPage(webDriver);
		deleteDraftCert.DeleteDraftCert();
		//Start an 8(a) Program.
		AddOrStartEightACertificationPage addOrStartEightACertification = new AddOrStartEightACertificationPage(webDriver);
		addOrStartEightACertification.AddOrStartEightACertification();
		//8(a) Interim questionnaire.
		EightAtest1Page eightAtest1 = new EightAtest1Page(webDriver);
		eightAtest1.EightAtest1();
		//Login as an 8(a) analyst and verify responsibilities.
		get_The_Row_From_Login_Data = 34;
		LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data1.Login_With_Reference();
		webDriver.findElement(By.id("query")).sendKeys("153566257");
		webDriver.findElement(By.xpath("//button")).click();
		Actual_Text = webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).getText();
		Expected_Text = "Entity 211 Legal Business Name";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).click();
		//Verify that analyst is navigated to the vendor overview page.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div[2]/div/p[4]/span")).getText();
		Expected_Text = "05RSL";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("td")).getText();
		Expected_Text = "8(a) Document Upload";
		assertEquals(Actual_Text, Expected_Text);
		//verify that 8(a) analyst sees only 8(a) application on the all case page or can not access WOSB and MPP certs.
		webDriver.findElement(By.linkText("Cases")).click();
		//WOSB
		if (webDriver.getPageSource().contains("WOSB")) {
			logger.info("8(a) analyst could see or access application from WOSB vendor applicants, Responsibility Failed");
		    webDriver.quit();
		} else {
			logger.info("8(a) analyst could not see or access application from WOSB vendor applicants, Responsibility Passed");	
		}
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
		Expected_Text = "WOSB";
		assertNotSame(Actual_Text, Expected_Text);
		//EDWOSB.
		if (webDriver.getPageSource().contains("EDWOSB")) {
			logger.info("8(a) analyst could see or access application from EDWOSB vendor applicants, Responsibility Failed");
		    webDriver.quit();
		} else {
			logger.info("8(a) analyst could not see or access application from EDWOSB vendor applicants, Responsibility Passed");	
		}
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
		Expected_Text = "EDWOSB";
		assertNotSame(Actual_Text, Expected_Text);
        //MPP
		if (webDriver.getPageSource().contains("MPP")) {
			logger.info("8(a) analyst could see or access application from MPP vendor applicants, Responsibility Failed");
		    webDriver.quit();
		} else {
			logger.info("8(a) analyst could not see or access application from MPP vendor applicants, Responsibility Passed");	
		}
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
		Expected_Text = "MPP";
		assertNotSame(Actual_Text, Expected_Text);
		//8(a) Document Upload accessible. 
		if (webDriver.getPageSource().contains("8(a) Document Upload")) {
			logger.info("8(a) analyst could see or access application from 8(a) Document Upload vendor applicants, Responsibility Passed");
			Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
			Expected_Text = "8(a) Document Upload";
			assertEquals(Actual_Text, Expected_Text);
			WebElement EightACert = webDriver.findElement(By.xpath(".//*[@id='table-search']/table/tbody/tr/td[3]"));
			HighLight.highLightElement(webDriver, EightACert);
		} else {
			logger.info("8(a) analyst could not see or access application from 8(a) Document Upload vendor applicants, Responsibility Failed");	
		}
		//Login as an 8(a) supervisor and verify responsibilities.
		get_The_Row_From_Login_Data = 35;
		LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data2.Login_With_Reference();
		//Verify the Management link for 8(a) supervisor dashboard. 
		Actual_Text = webDriver.findElement(By.linkText("Management")).getText();
		Expected_Text = "Management";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Management")).click();
		Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
		Expected_Text = "Manage analyst access";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Dashboard")).click();
		webDriver.findElement(By.id("query")).sendKeys("153566257");
		webDriver.findElement(By.xpath("//button")).click();
		Actual_Text = webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).getText();
		Expected_Text = "Entity 211 Legal Business Name";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).click();
		//Verify that analyst is navigated to the vendor overview page.
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='business_search']/div[2]/div/p[4]/span")).getText();
		Expected_Text = "05RSL";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.cssSelector("td")).getText();
		Expected_Text = "8(a) Document Upload";
		assertEquals(Actual_Text, Expected_Text);
		//verify that 8(a) analyst sees only 8(a) application on the all case page or can not access WOSB and MPP certs.
		webDriver.findElement(By.linkText("Cases")).click();
		//WOSB
		if (webDriver.getPageSource().contains("WOSB")) {
			logger.info("8(a) analyst could see or access application from WOSB vendor applicants, Responsibility Failed");
		    webDriver.quit();
		} else {
			logger.info("8(a) analyst could not see or access application from WOSB vendor applicants, Responsibility Passed");	
		}
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
		Expected_Text = "WOSB";
		assertNotSame(Actual_Text, Expected_Text);
		//EDWOSB.
		if (webDriver.getPageSource().contains("EDWOSB")) {
			logger.info("8(a) analyst could see or access application from EDWOSB vendor applicants, Responsibility Failed");
		    webDriver.quit();
		} else {
			logger.info("8(a) analyst could not see or access application from EDWOSB vendor applicants, Responsibility Passed");	
		}
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
		Expected_Text = "EDWOSB";
		assertNotSame(Actual_Text, Expected_Text);
        //MPP
		if (webDriver.getPageSource().contains("MPP")) {
			logger.info("8(a) analyst could see or access application from MPP vendor applicants, Responsibility Failed");
		    webDriver.quit();
		} else {
			logger.info("8(a) analyst could not see or access application from MPP vendor applicants, Responsibility Passed");	
		}
		Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
		Expected_Text = "MPP";
		assertNotSame(Actual_Text, Expected_Text);
		//8(a) Document Upload accessible. 
		if (webDriver.getPageSource().contains("8(a) Document Upload")) {
			logger.info("8(a) analyst could see or access application from 8(a) Document Upload vendor applicants, Responsibility Passed");
			Actual_Text = webDriver.findElement(By.xpath("//div[@id='table-search']/table/tbody/tr/td[3]")).getText();
			Expected_Text = "8(a) Document Upload";
			assertEquals(Actual_Text, Expected_Text);
			WebElement EightACert = webDriver.findElement(By.xpath(".//*[@id='table-search']/table/tbody/tr/td[3]"));
			HighLight.highLightElement(webDriver, EightACert);
		} else {
			logger.info("8(a) analyst could not see or access application from 8(a) Document Upload vendor applicants, Responsibility Failed");	
		}
		//Return the 8(a) submitted Application.
		webDriver.findElement(By.id("query")).sendKeys("153566257");
		webDriver.findElement(By.xpath("//button")).click();
		Actual_Text = webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).getText();
		Expected_Text = "Entity 211 Legal Business Name";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).click();
		webDriver.findElement(By.linkText("Return to Vendor")).click();
		webDriver.switchTo().alert().accept();
		//Verify that application was return.
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "A new application has been reopenned for the vendor";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Logout")).click();
		// Login to dashboard and verify the draft return 8(a) application..
		get_The_Row_From_Login_Data = 4;
		LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data3.Login_With_Reference();
		Thread.sleep(3000);
		Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
		Expected_Text = "Draft";
		assertEquals(Actual_Text, Expected_Text);
		WebElement EightADraft = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
		HighLight.highLightElement(webDriver, EightADraft);
		//Make changes and Resubmit
		webDriver.findElement(By.linkText("8(a) Document Upload")).click();
		// Upload a document.
		MontanaUploadDocumentPage montanaUploadDocument0 = new MontanaUploadDocumentPage(webDriver);
		montanaUploadDocument0.MontanaUploadDocument();
		webDriver.findElement(By.name("commit")).click();
		//Verify the second attachment.
		Actual_Text = webDriver.findElement(By.xpath("//tr[2]/td")).getText();
		Expected_Text = "MainTestUploadDoc.pdf";
		assertEquals(Actual_Text, Expected_Text);
		WebElement EightADoc2 = webDriver.findElement(By.xpath("//tr[2]/td"));
		HighLight.highLightElement(webDriver, EightADoc2);
		webDriver.findElement(By.name("commit")).click();
		webDriver.switchTo().alert().accept();
		Thread.sleep(3000);
		webDriver.findElement(By.id("legal_0")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.id("accept-button")).click();
		//Verify that submission is successful.
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "Your application has been submitted";
		assertEquals(Actual_Text, Expected_Text);
		Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
		Expected_Text = "Pending";
		assertEquals(Actual_Text, Expected_Text);
		WebElement EightAPending = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
		HighLight.highLightElement(webDriver, EightAPending);
		webDriver.findElement(By.linkText("Logout")).click();
		//Login as an 8(a) analyst or supervisor and verify the submitted app2
		get_The_Row_From_Login_Data = 35;
		LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data4.Login_With_Reference();
		webDriver.findElement(By.id("query")).sendKeys("153566257");
		webDriver.findElement(By.xpath("//button")).click();
		Actual_Text = webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).getText();
		Expected_Text = "Entity 211 Legal Business Name";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Entity 211 Legal Business Name")).click();
		Actual_Text = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]")).getText();
		Expected_Text = "Pending";
		assertEquals(Actual_Text, Expected_Text);
		WebElement EightAPending2 = webDriver.findElement(By.xpath("//table[@id='certifications']/tbody/tr/td[4]"));
		HighLight.highLightElement(webDriver, EightAPending2);
		Thread.sleep(3000);
		webDriver.findElement(By.linkText("Return to Vendor")).click();
		//Verify that application was return.
		Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		Expected_Text = "A new application has been reopenned for the vendor";
		assertEquals(Actual_Text, Expected_Text);
		webDriver.findElement(By.linkText("Logout")).click();
       
		return;
}

@After
public void tearDown() throws Exception {
	webDriver.quit();
}
}	
	


