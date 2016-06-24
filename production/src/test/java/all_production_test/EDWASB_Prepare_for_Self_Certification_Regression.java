package all_production_test;

import static org.junit.Assert.assertEquals;

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

public class EDWASB_Prepare_for_Self_Certification_Regression {
	public WebDriver mydriver;
	String myurl;

	@Before
	public void WASB_Prepare_for_Self_Certification_setup() throws Exception {
		myurl = "https://certify.sba.gov/";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();
	}

	@Rule
	public ErrorCollector erroeCollector = new ErrorCollector();

	@Test
	public void WOSB_Prepare_for_Self_Certification_Main_test() throws Exception {
		// Step 1. Open Firefox and enter the the valid Url.
		mydriver.navigate().to(myurl);
		mydriver.manage().window().maximize();
		// Verify that the Prepare Button is present and active on the landing
		// page.
		String actual_Text1 = mydriver.findElement(By.linkText("Prepare")).getText();
		String expected_Text1 = "Prepare";
		assertEquals(actual_Text1, expected_Text1);
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[2]/a")).click();
		// Verify that user navigates to the prepare for cert page.
		String actual_Text2 = mydriver.findElement(By.cssSelector("h1.usa-heading")).getText();
		String expected_Text2 = "Prepare for SBA small business programs:";
		assertEquals(actual_Text2, expected_Text2);
		Thread.sleep(2000);
		// Verify the introductive message.
		String actual_Text = mydriver.findElement(By.cssSelector("div.usa-content > p")).getText();
		String expected_Text = "These checklists provide help before you start the application or continuing eligibility process. You may have to take action and gather documents before starting. Each program has unique criteria and thresholds and may request additional supporting information. To make things easier, gather these documents before you start.";
		assertEquals(actual_Text, expected_Text);

		// Verify WOSB Documents needed.
		String actual_Text3 = mydriver.findElement(By.cssSelector("b")).getText();
		String expected_Text3 = "Women-Owned Small Business (WOSB) Preparation Checklist";
		assertEquals(actual_Text3, expected_Text3);
		mydriver.findElement(By.cssSelector("b")).click();
		// Verify documents needed to participate in the program.
		String actual_Text4 = mydriver
				.findElement(By.cssSelector("div.usa-width-five-sixths > div.usa-content > ul.ul-level-one > li"))
				.getText();
		String expected_Text4 = "Active registration in the System for Award Management for the firm, available at SAM.gov (Note: The firm�s DUNS number and EIN, and MPIN must exactly match SAM registration)";
		assertEquals(actual_Text4, expected_Text4);

		String actual_Text5 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul/li[2]"))
				.getText();
		String expected_Text5 = "Proof of U.S. citizenship (i.e., birth certificate, naturalization paper, or unexpired passport) for qualifying individual(s).";
		assertEquals(actual_Text5, expected_Text5);

		String actual_Text6 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul/li[3]"))
				.getText();
		String expected_Text6 = "Joint Venture agreements if applicable";
		assertEquals(actual_Text6, expected_Text6);
		// Verify WOSB business types and the documents needed for each to take
		// part in the program.
		// Corporation.
		String actual_Text7 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul/li[4]"))
				.getText();
		String expected_Text7 = "Corporation";
		assertEquals(actual_Text7, expected_Text7);

		String actual_Text8 = mydriver.findElement(By.cssSelector("ul.ul-level-two > li")).getText();
		String expected_Text8 = "Articles of Incorporation";
		assertEquals(actual_Text8, expected_Text8);

		String actual_Text9 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[2]/li[2]"))
				.getText();
		String expected_Text9 = "Copies of stock certificates (front and back)";
		assertEquals(actual_Text9, expected_Text9);

		String actual_Text10 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[2]/li[3]"))
				.getText();
		String expected_Text10 = "Stock Ledger";
		assertEquals(actual_Text10, expected_Text10);

		String actual_Text11 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[2]/li[4]"))
				.getText();
		String expected_Text11 = "Corporate Bylaws and any amendments";
		assertEquals(actual_Text11, expected_Text11);

		String actual_Text12 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[2]/li[5]"))
				.getText();
		String expected_Text12 = "Certificate of Good Standing";
		assertEquals(actual_Text12, expected_Text12);

		// Limited Liability Company (LLC).
		String actual_Text13 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[3]/li"))
				.getText();
		String expected_Text13 = "Limited Liability Company (LLC)";
		assertEquals(actual_Text13, expected_Text13);

		String actual_Text14 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[4]/li"))
				.getText();
		String expected_Text14 = "Operating Agreement and any amendments";
		assertEquals(actual_Text14, expected_Text14);

		String actual_Text15 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[4]/li[2]"))
				.getText();
		String expected_Text15 = "Articles of Organization and any amendments";
		assertEquals(actual_Text15, expected_Text15);

		String actual_Text16 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[4]/li[3]"))
				.getText();
		String expected_Text16 = "Certificate of Good Standing";
		assertEquals(actual_Text16, expected_Text16);
		// Partnership.
		String actual_Text17 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[5]/li"))
				.getText();
		String expected_Text17 = "Partnership";
		assertEquals(actual_Text17, expected_Text17);

		String actual_Text18 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[6]/li"))
				.getText();
		String expected_Text18 = "Partnership Agreement and any amendments";
		assertEquals(actual_Text18, expected_Text18);
		// Sole Proprietor.
		String actual_Text19 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[7]/li"))
				.getText();
		String expected_Text19 = "Sole Proprietor";
		assertEquals(actual_Text19, expected_Text19);

		String actual_Text20 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[2]/div/div/ul[8]/li"))
				.getText();
		String expected_Text20 = "DBA (Doing Business As) or Trade Name Certificate";
		assertEquals(actual_Text20, expected_Text20);
		// Verify EDWOSB business types and the documents needed for each to
		// take part in the program.
		String actual_Text0 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[3]/div/div/a/h3"))
				.getText();
		String expected_Text0 = "Economically Disadvantaged Women-Owned Small Business (EDWOSB) Preparation Checklist";
		assertEquals(actual_Text0, expected_Text0);

		String actual_Text21 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[3]/div/div/p")).getText();
		String expected_Text21 = "Qualifying individual(s) include all women claiming 51% ownership and economic disadvantage";
		assertEquals(actual_Text21, expected_Text21);
		// Verify 8(a) business types and the documents needed for each to take
		// part in the program.

		String actual_Text22 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[4]/div/div/a/h3"))
				.getText();
		String expected_Text22 = "8(a) Business Development (BD) Program Preparation Checklist";
		assertEquals(actual_Text22, expected_Text22);
		// Verify HOB-zone business types and the documents needed for each to
		// take part in the program.

		String actual_Text23 = mydriver.findElement(By.xpath("//div[@id='prep-for-cert']/div[5]/div/div/a/h3"))
				.getText();
		String expected_Text23 = "Historically Underutilized Business Zones (HUBZone) Program Preparation Checklist";
		assertEquals(actual_Text23, expected_Text23);

		mydriver.navigate().back();
		mydriver.navigate().back();

	}

	@After
	public void WASB_Prepare_for_Self_Certification_End_test() throws Exception {
		mydriver.quit();
	}
}
