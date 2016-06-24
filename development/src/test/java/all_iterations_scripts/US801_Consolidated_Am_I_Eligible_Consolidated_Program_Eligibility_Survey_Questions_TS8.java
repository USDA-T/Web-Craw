package all_iterations_scripts;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS8 {
	public WebDriver mydriver;
	String MyUrl;
	String NAICS;

	@Before
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS8_Setup()
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
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS8_MainTest()
			throws Exception {
		// Open Firefox,Chrome or IE and navigate to the certify.sba.gov landing
		// page.
		mydriver.navigate().to(MyUrl);
		mydriver.manage().window().maximize();
		System.out.println(
				"User is NOT eligible for Any of the programs (Due to NO for Qs6) 8(a), WOSB, EDWOSB & Hob-zone");
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
		// Locate the 4th question and select YES and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Do you affirm that neither this firm")) {
			System.out.println("4th question was validated");
			String More_Details_Qs4;
			More_Details_Qs4 = mydriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 4th is" + More_Details_Qs4);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='non_suspended']/div[1]/div[1]/div/button[1]")).click();

		} else {
			System.out.println("4th question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the 5th question and select YES and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Does the firm have a place of business in the U.S.")) {
			System.out.println("5th question was validated");
			String More_Details_Qs5;
			More_Details_Qs5 = mydriver.findElement(By.xpath(".//*[@id='us_business']/div[1]/div[2]/p[1]")).getText();
			System.out.println("The Detail meaning for question 5th is" + More_Details_Qs5);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='us_business']/div[1]/div[1]/div/button[1]")).click();

		} else {
			System.out.println("5th question was Not validated Error");
		}

		Thread.sleep(4000);
		// Locate the 6th question and select Yes and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Is the firm considered small in accordance with its primary")) {
			System.out.println("6th question was validated");
			String More_Details_Qs6;
			More_Details_Qs6 = mydriver.findElement(By.xpath(".//*[@id='small_naics']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 6th is" + More_Details_Qs6);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='small_naics']/div[1]/div[1]/div/button[1]")).click();

		} else {
			System.out.println("6th question was Not validated Error");
		}

		Thread.sleep(4000);
		// Locate the 7th question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Are the qualifying individual")) {
			System.out.println("7th question was validated");
			String More_Details_Qs7;
			More_Details_Qs7 = mydriver.findElement(By.xpath(".//*[@id='women_owning_business']/div[1]/div[2]"))
					.getText();
			System.out.println("The Detail meaning for question 7th is" + More_Details_Qs7);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='women_owning_business']/div[1]/div[1]/div/button[2]")).click();
			Thread.sleep(3000);
			// Verify that user is being navigated to the 10th question because
			// user select NO for the 7th question.
			if (mydriver.getPageSource().contains("Are WOSB Federal Contract Program set-asides available")) {
				System.out.println("===Question 8 was not skipped after answering NO for question 7");
				// Enter a valid NAICS Code.
				mydriver.findElement(By.xpath(".//*[@id='naics_code']")).sendKeys(NAICS);
				mydriver.findElement(By.xpath(".//*[@id='naics_button']")).click();
			} else {
				System.out.println("===Question 8 was Successfully skipped after answering NO for question 7");

			}
			Thread.sleep(4000);
			if (mydriver.getPageSource().contains("Are the qualifying individual")) {
				System.out.println("===Question 9 was not skipped after answering NO for question 7");
				// Select NO for question 9.
				mydriver.findElement(
						By.xpath(".//*[@id='economically_disadvantaged_wosb']/div[1]/div[1]/div/button[2]")).click();
			} else {
				System.out.println("===Question 9 was Successfully skipped after answering NO for question 7");

			}
		} else {
			System.out.println("7th question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the 10th question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("interested in participating in SBA small")) {
			System.out.println("10th question was validated");
			String More_Details_Qs10;
			More_Details_Qs10 = mydriver
					.findElement(By.xpath(".//*[@id='economically_disadvantaged_8a']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 10th is" + More_Details_Qs10);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='economically_disadvantaged_8a']/div[1]/div[1]/div/button[2]"))
					.click();
			Thread.sleep(3000);
			// Verify that user is being navigated to the 13th question because
			// user select NO for the 10th question.
			if (mydriver.getPageSource().contains("Do you identify as one of the following")) {
				System.out.println(
						"User Successfully navigated to the 13th question ':Do you identify as one of the following?'");

			} else {
				System.out.println(
						"User is NOT Successfully navigated to the 13th question ':Do you identify as one of the following?' -FAILED");
				mydriver.quit();

			}
		} else {
			System.out.println("7th question was Not validated Error");
		}

		Thread.sleep(4000);
		// Locate the 13th question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Do you identify as one of the following")) {
			System.out.println("13th question was validated");
			String More_Details_Qs13;
			More_Details_Qs13 = mydriver.findElement(By.xpath(".//*[@id='socially_disadvantaged']/div[1]/div[2]/p[1]"))
					.getText();
			System.out.println("The Detail meaning for question 13th is" + More_Details_Qs13);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='socially_disadvantaged']/div[1]/div[1]/div/button[2]")).click();
		} else {
			System.out.println("13th question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the 14th question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Do you consider yourself socially disadvantaged because")) {
			System.out.println("14th question was validated");
			String More_Details_Qs14;
			More_Details_Qs14 = mydriver
					.findElement(By.xpath(".//*[@id='socially_disadvantaged_chronic']/div[1]/div[2]/p[1]")).getText();
			System.out.println("The Detail meaning for question 14th is" + More_Details_Qs14);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='socially_disadvantaged_chronic']/div[1]/div[1]/div/button[2]"))
					.click();
		} else {
			System.out.println("14th question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the next question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Has the firm previously been certified as an")) {
			System.out.println("15th question was validated");
			String More_Details_Qs15;
			More_Details_Qs15 = mydriver.findElement(By.xpath(".//*[@id='eighta_certified']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 15th is" + More_Details_Qs15);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='eighta_certified']/div[1]/div[1]/div/button[2]")).click();
		} else {
			System.out.println("15th question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the next question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("claiming social and economic")) {
			System.out.println("Claiming EDWOSB question was validated");
			String More_Details;
			More_Details = mydriver.findElement(By.xpath(".//*[@id='eighta_one_time_used']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 15th is" + More_Details);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='eighta_one_time_used']/div[1]/div[1]/div/button[2]")).click();
		} else {
			System.out.println("Claiming SED question was Not validated Error");
		}
		Thread.sleep(4000);
		// Locate the next question and select NO and verify the More Detail
		// meaning of the question.

		if (mydriver.getPageSource().contains("Is the firm�s business address located in a HUBZone")) {
			System.out.println(" question was validated");
			String More_Details;
			More_Details = mydriver.findElement(By.xpath(".//*[@id='address_in_hubzone']/div[1]/div[2]")).getText();
			System.out.println("The Detail meaning for question 15th is" + More_Details);
			Thread.sleep(4000);
			mydriver.findElement(By.xpath(".//*[@id='address_in_hubzone']/div[1]/div[1]/div/button[2]")).click();
		} else {
			System.out.println("question was Not validated Error");
		}

		// Verify searched results.
		Thread.sleep(4000);
		String actual_Text4 = mydriver.findElement(By.cssSelector("div.not_eligible")).getText();
		String expected_Text4 = "Based on the information you provided, you may not be eligible for the 8(a) BD Program:\nQualifying individuals must meet the economically disadvantaged financial criteria to participate in the program. However, many factors are taken into consideration during application review and it is possible that you may still qualify to participate. Contact your local SBA 8(a) Business Office for more information.\nQualifying individuals must meet socially disadvantaged criteria in order to participate in the program.";
		assertEquals(actual_Text4, expected_Text4);

		String actual_Text5 = mydriver.findElement(By.cssSelector("#wosb > div.not_eligible")).getText();
		String expected_Text5 = "Based on the information you provided, you may not be eligible for the WOSB Program:\nTo certify as a WOSB or EDWOSB the firm must be owned by women.\nThe NAICS code entered does not appear on the list of designated industries in which WOSB Program set-asides are authorized. As such, be advised that federal contracts currently cannot be set-aside under the WOSB Program in this industry. However, this does not preclude you from certifying as a WOSB or EDWOSB if you meet the requirements of the WOSB Program.";
		assertEquals(actual_Text5, expected_Text5);

		String actual_Text6 = mydriver.findElement(By.cssSelector("#edwosb > div.not_eligible")).getText();
		String expected_Text6 = "Based on the information you provided, you may not be eligible for the EDWOSB Program:\nTo certify as a WOSB or EDWOSB the firm must be owned by women.\nThe NAICS code entered does not appear on the list of designated industries in which WOSB Program set-asides are authorized. As such, be advised that federal contracts currently cannot be set-aside under the WOSB Program in this industry. However, this does not preclude you from certifying as a WOSB or EDWOSB if you meet the requirements of the WOSB Program.\nTo qualify for EDWOSB the firm must be economically disadvantaged.";
		assertEquals(actual_Text6, expected_Text6);

		String actual_Text7 = mydriver.findElement(By.cssSelector("#hubzone > div.not_eligible > b")).getText();
		String expected_Text7 = "Based on the information you provided, you may not be eligible for the HUBZone Program:";
		assertEquals(actual_Text7, expected_Text7);

	}

	@After
	public void US801_Consolidated_Am_I_Eligible_Consolidated_Program_Eligibility_Survey_Questions_TS8_End_Test()
			throws Exception {
		mydriver.quit();
	}

}
