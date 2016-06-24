package all_production_test;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class US402_EDWOSB_Self_Certifies_Questionnaire_Test {
	public WebDriver mydriver;
	String myurl;
	String email;
	String PassW;
	String comment;

	@Before
	public void EDWOSB_Self_Certifies_Questionnaire_Setup() throws Exception {
		myurl = "https://certify.sba.gov/users/sign_in";
		email = "akanamontana@gmail.com";
		PassW = "password";
		comment = "Test";

		File file = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\IEDriverServer32b.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		File file1 = new File("C:\\SBA-AUTOMATION-TEST\\SBAONE-REGRESSION-TEST\\libs\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
		// mydriver=new InternetExplorerDriver();
		// mydriver=new ChromeDriver();
		mydriver = new FirefoxDriver();

	}

	@Test // issue with html code but not an error.
	public void EDWOSB_Self_Certifies_Questionnaire_Maintest() throws Exception {
		// Step 1. Open Firefox browser and navigate to url.
		mydriver.navigate().to(myurl);
		mydriver.manage().window().maximize();
		Thread.sleep(2000);
		// Login to dashboard.
		ProdloginPage prodlogin = new ProdloginPage(mydriver);
		prodlogin.Prodlogin();
		Thread.sleep(3000);
		if (mydriver.getPageSource().contains("Signed in successfully")) {
			mydriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		} else {
			System.out.println("Successful sign in alert message not present");
		}
		Thread.sleep(3000);

		// Verify if there is an existing certification on the dashboard and
		// delete to start a new certification.
		if (mydriver.getPageSource().contains("Draft")) {
			mydriver.findElement(By.linkText("Delete")).click();
			mydriver.switchTo().alert().accept();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mydriver.navigate().refresh();
		} else {
			System.out.println(
					"There are(is) no certification in-progress on the dashboard, a new certification is beinng created");
		}
		Thread.sleep(7000);

		// Locate the Certifications on the dashboard, click on it and select
		// EDWOSB to continue.
		mydriver.findElement(By.linkText("Certifications")).click();
		mydriver.findElement(By.linkText("EDWOSB")).click();
		Thread.sleep(2000);
		LlcquestionsPage llcquestions = new LlcquestionsPage(mydriver);
		llcquestions.Llcquestions();
	}

	@After
	public void EDWOSB_Self_Certifies_Questionnaire_EndTest() throws Exception {
		mydriver.quit();
	}
}
