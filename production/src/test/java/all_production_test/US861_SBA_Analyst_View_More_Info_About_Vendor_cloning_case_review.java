package all_production_test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US861_SBA_Analyst_View_More_Info_About_Vendor_cloning_case_review {

	public WebDriver mydriver;
	String myurl;
	String Duns;
	String B_N;

	@Before
	public void US861_SBA_Analyst_View_More_Info_About_Vendor_cloning_case_review_Setup() throws Exception {
		myurl = "https://certify.sba.gov/users/sign_in";
		Duns = "023027981";
		B_N = "104 RUSSELL AVE";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();

	}

	@Test
	public void US861_SBA_Analyst_View_More_Info_About_Vendor_cloning_case_review_MainTest() throws Exception {
		// Notes; Role Most be assigned at the database and validated at the
		// front end.

		// Open Firefox,Chrome or IE and navigate the certify.SBA.gov login
		// page.
		mydriver.navigate().to(myurl);
		mydriver.manage().window().maximize();
		// Locate the email search box and enter a valid email for the User who
		// have been assign the analyst role.
		AnalystloginPage analystlogin = new AnalystloginPage(mydriver);
		analystlogin.Analystlogin();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(3000);
		// Locate the search for vendor search box and enter a valid Duns
		// number.
		mydriver.findElement(By.xpath(".//*[@id='query']")).sendKeys(Duns);
		// Locate the search button and click it.
		mydriver.findElement(By.xpath(".//*[@id='analyst-search']/div/div[2]/button")).click();
		// Clear the search duns number in the search box and very that that the
		// duns number is thesame on the search result.
		mydriver.findElement(By.xpath(".//*[@id='query']")).clear();
		Thread.sleep(4000);
		if (mydriver.getPageSource().contains("275276652")) {
			System.out.println("The saerch results maches the business for the searched Duns number, Pass.");

		} else {
			System.out.println("The saerch results does not mache the business for the searched Duns number, Failed");
			mydriver.quit();
		}
		Thread.sleep(4000);
		// Locate the search for vendor search box and enter a valid business
		// name.
		mydriver.findElement(By.xpath(".//*[@id='query']")).sendKeys(B_N);
		// Click on the search button.
		mydriver.findElement(By.xpath(".//*[@id='analyst-search']/div/div[2]/button")).click();
		// Clear the search duns number in the search box and very that that the
		// duns number is thesame on the search result.
		mydriver.findElement(By.xpath(".//*[@id='query']")).clear();
		Thread.sleep(4000);
		String actual_Text = mydriver.findElement(By.id("view_business_profile")).getText();
		String expected_Text = "Entity 37 Legal Business Name";
		assertValueEquals(actual_Text, expected_Text);
		// business type.
		String actual_Text1 = mydriver.findElement(By.xpath("//form[@id='associate_business']/div[3]/div[3]/span[2]"))
				.getText();
		String expected_Text1 = "s-corp";
		assertEquals(actual_Text1, expected_Text1);
		// Duns.
		String actual_Text2 = mydriver.findElement(By.xpath("//form[@id='associate_business']/div[2]/div[3]/span[2]"))
				.getText();
		String expected_Text2 = "275276652";
		assertEquals(actual_Text2, expected_Text2);
		// address.
		String actual_Text3 = mydriver.findElement(By.xpath("//form[@id='associate_business']/div[4]/div/span[2]"))
				.getText();
		String expected_Text3 = "Entity 37 Address Line 1\nWASHINGTON, DC 20004";
		assertEquals(actual_Text3, expected_Text3);

		// Loacte the view profile button and click on it.
		mydriver.findElement(By.xpath(".//*[@id='view_business_profile']")).click();
		Thread.sleep(3000);
		// Locate Entity 37 Legal Business Name and click on it to view all the
		// company information.
		mydriver.findElement(By.xpath(".//*[@id='business_search']/div[3]/div/a")).click();
		// Verify clone, return to vendor.
		String actual_Text4 = mydriver.findElement(By.linkText("Return to Vendor")).getText();
		String expected_Text4 = "Return to Vendor";
		assertEquals(actual_Text4, expected_Text4);
		// case review.
		String actual_Text5 = mydriver.findElement(By.linkText("Case review")).getText();
		String expected_Text5 = "Case review";
		assertEquals(actual_Text5, expected_Text5);
		mydriver.findElement(By.linkText("Case review")).click();
		Thread.sleep(4000);
		// Verify that the case review link is active.
		String actual_Text6 = mydriver.findElement(By.xpath("//article[@id='main-content']/div/div[2]/h1")).getText();
		String expected_Text6 = "Case Overview";
		assertEquals(actual_Text6, expected_Text6);

		// logout.
		mydriver.findElement(By.xpath(".//*[@id='js-navigation-menu']/li[3]/a")).click();

	}

	private void assertValueEquals(String actual_Text, String expected_Text) {
		// TODO Auto-generated method stub

	}

	@After
	public void US861_SBA_Analyst_View_More_Info_About_Vendor_cloning_case_review_EndTest() throws Exception {
		// mydriver.quit();
	}

}
