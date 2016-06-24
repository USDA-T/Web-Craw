package all_iterations_scripts;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS5 {
	public WebDriver mydriver;
	String MyUrl;
	String NAICS;

	@Before
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS5_Setup()
			throws Exception {
		mydriver = new FirefoxDriver();
		MyUrl = "https://certify.qa.sba-one.net";
		NAICS = "335932";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-ACCEPTANCE-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new ChromeDriver();

	}

	@Test
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS5_MainTest()
			throws Exception {
		// Open Firefox,Chrome or IE and navigate to the certify.sba.gov landing
		// page.
		mydriver.navigate().to(MyUrl);
		mydriver.manage().window().maximize();
		System.out.println(
				"User is NOT eligible for Any of the programs (Due to NO for Qs4) 8(a), WOSB, EDWOSB & Hob-zone");
		// Locate the Am I Eligible or the Find Out button on the
		// Certify.SBA.Gov landing page and click on it.
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();
		Thread.sleep(4000);

		// Locate the first question and select Yes and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("A Citizen means a person born or naturalized in the United States")) {
			System.out.println("First question was validated");
			String More_Details_Qs1;
			More_Details_Qs1 = mydriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 1 is" + More_Details_Qs1);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='us_citizen']/div[1]/div[1]/div/button[1]")).click();

		} else {
			System.out.println("First question was Not validated Error");
		}
		Thread.sleep(4000);

		// Locate the 2nd question and select YES and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Is the 51% ownership of the firm unconditional and direct")) {
			System.out.println("Second question was validated");
			String More_Details_Qs2;
			More_Details_Qs2 = mydriver
					.findElement(By.xpath(".//*[@id='unconditional_direct_51_percent']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 2 is" + More_Details_Qs2);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='unconditional_direct_51_percent']/div[1]/div[1]/div/button[1]"))
					.click();

		} else {
			System.out.println("Second question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the Third question and select YES and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Is the firm organized for profit")) {
			System.out.println("Third question was validated");
			String More_Details_Qs3;
			More_Details_Qs3 = mydriver.findElement(By.xpath(".//*[@id='for_profit']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 3rd is" + More_Details_Qs3);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='for_profit']/div[1]/div[1]/div/button[1]")).click();

		} else {
			System.out.println("third question was Not validated Error");
		}

		Thread.sleep(4000);
		// Locate the 4th question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Do you affirm that neither this firm")) {
			System.out.println("4th question was validated");
			String More_Details_Qs4;
			More_Details_Qs4 = mydriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 4th is" + More_Details_Qs4);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[1]/div/button[2]")).click();

		} else {
			System.out.println("4th question was Not validated Error");
		}

		// Verify searched results.
		String actual_Text1 = mydriver.findElement(By.cssSelector("span.message")).getText();
		String expected_Text1 = "In order to participate in SBA small business programs, the owner(s) of the firm must not have been debarred or suspended by a federal entity.";
		assertEquals(actual_Text1, expected_Text1);
		mydriver.findElement(By.linkText("Exit")).click();

	}

	@After
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS5_End_Test()
			throws Exception {
		mydriver.quit();
	}

}