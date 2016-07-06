package gov.sba.utils;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.After;
	import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Edith_Password_Regression_Test_1 {
	private static final Logger logger = LogManager.getLogger(Edith_Password_Regression_Test_1.class.getName());

	private static WebDriver webDriver;
		String myurl;
		String Email;
		String Current_PassW;
		String Current_PassW2;
		String Weak_PassW;
        String Better_PW;
		String New_PassW;
		String Confirm_New_PassW;
		
		
	@Before
	public void Edith_Password_Regression_Test_Setup()throws Exception{
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		Email="staging@mailinator.com";
		Current_PassW="password";
		Current_PassW2="Map Effect Applied Furniture 3365";
		Weak_PassW="1234";
		New_PassW="Map Effect Applied Furniture 9883";
		Confirm_New_PassW="Map Effect Applied Furniture 9883";
		Better_PW="Derico#3365";
		
	}
		@Rule
		public ErrorCollector erroeCollector=new ErrorCollector();	
			
		
	
	@Test
	public void Edith_Password_Regression_Test_Maintest()throws Exception{
	//Open Firefox,Chrome,and IE and navigate to the valid url.
		
		Thread.sleep(4000);
		EdithpasswordPage edithpassword=new EdithpasswordPage(webDriver);
		edithpassword.Edithpassword();
		
		Thread.sleep(4000);
		if(webDriver.getPageSource().contains("Invalid email or password")){

			webDriver.findElement(By.xpath(".//*[@id='alert-close-button']")).click();
			//Locate the email search box and enter a valid email.
			webDriver.findElement(By.xpath(".//*[@id='user_email']")).sendKeys(Email);
		//Locate the password search box and enter a valid password.
			webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Current_PassW2);
		//Locate the user Sign-in button and click on it.
			webDriver.findElement(By.xpath(".//*[@id='business_signin']")).click();
						
		}
		else{
			logger.info("First attempt to log-in with current password was successful, PASS");

		}
		Thread.sleep(4000);
		if(webDriver.getPageSource().contains("Signed in successfully")){
			webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();
		}
		else{
			logger.info("Successful sign in alert message not present");
		}
		Thread.sleep(3000);
	//Locate the My Profile button on the left navigation and click on it.
		String actual_Text=webDriver.findElement(By.linkText("My Profile")).getText();
		String expected_Text="My Profile";
		assertEquals(actual_Text, expected_Text);
		webDriver.findElement(By.linkText("My Profile")).click();
		Thread.sleep(5000);		
	//Verify and click on the link Edit Passphrase.
		assertElementpresent(webDriver.findElement(By.linkText("Edit Passphrase")));
		String actual_Text1=webDriver.findElement(By.linkText("Edit Passphrase")).getText();
		String expected_Text1="Edit Passphrase";
		assertEquals(actual_Text1, expected_Text1);
		webDriver.findElement(By.linkText("Edit Passphrase")).click();		
		Thread.sleep(5000);
	//Verify that user is navigated to the change password page.
		assertElementpresent(webDriver.findElement(By.cssSelector("h2.usa-heading")));
		String actual_Text2=webDriver.findElement(By.cssSelector("h2.usa-heading")).getText();
		String expected_Text2="Edit Passphrase";
		assertEquals(actual_Text2, expected_Text2);
	//Enter current and new password and Attempt to Update without entering the confirm new password data.
	    webDriver.findElement(By.xpath(".//*[@id='user_current_password']")).sendKeys(Current_PassW); 
	    Thread.sleep(3000);
	    webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(New_PassW);
	    Thread.sleep(4000);
	    System.out.println(webDriver.findElement(By.xpath(".//*[@id='text_strength']")).getText());
		webDriver.findElement(By.xpath(".//*[@id='submit']")).click();
		Thread.sleep(3000);

	//Verify that user sees alert message requesting user to enter all or confirm password.
		assertFalse(webDriver.getPageSource().contains("Password confirmation doesn't match Password"));
			logger.info("Usee sees alert messages requesting user to confirm password or password doesen't match, PASS");
		webDriver.findElement(By.xpath(".//*[@id='labelid']")).click();

		
		
   //Enter current and weak new password and Attempt to Update without entering the confirm new password data.
	    webDriver.findElement(By.xpath(".//*[@id='user_current_password']")).clear(); 
	    webDriver.findElement(By.xpath(".//*[@id='user_current_password']")).sendKeys(Current_PassW); 
	    Thread.sleep(3000);
	    webDriver.findElement(By.xpath(".//*[@id='user_password']")).clear();
	    webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Weak_PassW);
	    Thread.sleep(4000);
	    System.out.println(webDriver.findElement(By.xpath(".//*[@id='text_strength']")).getText());
		Thread.sleep(3000);
   //Verify that the weak password alert is activated when user enter a weak password.
		String actual_Text4=webDriver.findElement(By.id("password_strength")).getText();
		String expected_Text4="Weak";
		assertEquals(actual_Text4, expected_Text4);
		Thread.sleep(3000);
	    webDriver.findElement(By.xpath(".//*[@id='user_password']")).clear();
	    webDriver.findElement(By.xpath(".//*[@id='user_password']")).sendKeys(Better_PW);
	    Thread.sleep(4000);
	  //Verify that the Better password alert is activated when user enter a weak password.
	    String actual_Text3=webDriver.findElement(By.xpath("//span[@id='password_strength']")).getText();
		String expected_Text3="Better";
		assertEquals(actual_Text3, expected_Text3);
	
	}
	private void assertElementpresent(WebElement findElement) {
		// TODO Auto-generated method stub
		
	}
	@After
	public void Edith_Password_Regression_Test_Endtest()throws Exception{
		
		webDriver.quit();
				
	}


}
