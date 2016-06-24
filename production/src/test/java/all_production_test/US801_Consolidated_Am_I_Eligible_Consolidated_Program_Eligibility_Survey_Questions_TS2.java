package all_production_test;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS2<actual_error1> {
	public WebDriver mydriver;
	String MyUrl;
	String NAICS;

	@Before
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS2_Setup()
			throws Exception {
		mydriver = new FirefoxDriver();
		// MyUrl="https://certify.qa.sba-one.net";
		NAICS = "335932";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new ChromeDriver();

	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS2_MainTest()
			throws Exception {
		// Open Firefox,Chrome or IE and navigate to the certify.sba.gov landing
		// page.

		ProdbasePage prodbase = new ProdbasePage(mydriver);
		prodbase.Prodbase();

		// mydriver.navigate().to(MyUrl);
		// mydriver.manage().window().maximize();
		System.out.println(
				"User is NOT eligible(due to NO for Qs1) for Any of the programs 8(a), WOSB, EDWOSB & Hob-zone");
		// Locate the Am I Eligible or the Find Out button on the
		// Certify.SBA.Gov landing page and click on it.
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();

		// Verify that user navigates to the am i eligible page.
		String actual_Text = mydriver.findElement(By.cssSelector("h1.usa-heading")).getText();
		String expected_Text = "Is there an SBA Small Business Program for me?";
		assertEquals(actual_Text, expected_Text);
		// Locate the first question and select NO and verify the More Detail
		// meaning of the question.
		String actual_Text2 = mydriver.findElement(By.cssSelector("div.usa-width-one-half > p.lead-para")).getText();
		String expected_Text2 = "Are the qualifying individual(s) of the firm who are applying for SBA small business programs U.S. citizens?";
		assertEquals(actual_Text2, expected_Text2);
		// Validate the meaning for question one.
		String actual_Text3 = mydriver.findElement(By.cssSelector("div.details.usa-width-one-half > p")).getText();
		String expected_Text3 = "A Citizen means a person born or naturalized in the United States. Resident ailens and holders of permanent visas are not considered to be citizens. 13 C.F.R. 127.102";
		assertEquals(actual_Text3, expected_Text3);
		// verify and click on the No button.
		mydriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[1]/div/button[2]")).click();

		// Verify searched results.
		String actual_Text1 = mydriver.findElement(By.cssSelector("span.message")).getText();
		String expected_Text1 = "In order to participate in SBA small business programs, the qualifying individual(s) of the firm must be U.S. citizens.";
		assertEquals(actual_Text1, expected_Text1);
		mydriver.findElement(By.linkText("Exit")).click();

	}

	@After
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS2_End_Test()
			throws Exception {
		mydriver.quit();
	}

}
