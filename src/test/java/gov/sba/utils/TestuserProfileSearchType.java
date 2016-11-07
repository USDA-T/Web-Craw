package gov.sba.utils;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.TestCase;
import org.junit.Assert;

public class TestuserProfileSearchType extends TestCase {
	private static final Logger logger = LogManager.getLogger(TestuserProfileSearchType.class.getName());
	WebDriver webDriver;
	public void TestuserProfileSetDriver(WebDriver mydriver) {
		this.webDriver = mydriver;
	}
	public void TestuserProfileSearchType(String last_name,String Radio_xpath,String Expected_Result) throws Exception {
      
	//Select Government User Radio button
		webDriver.findElement(By.xpath(Radio_xpath)).click();
		webDriver.findElement(By.xpath("//input[@id='ops_query']")).sendKeys(last_name);		
		webDriver.findElement(By.xpath("//input[@name='commit']")).click();	
	//User Search results
		WebElement table = webDriver.findElement(By.xpath("//table[@class='searchable']"));
		webDriver.findElement(By.xpath("//table[contains(@class,'searchable')]/tbody/tr/td[1]/a")).click();
		String Actual_result = webDriver.findElement(By.xpath("//article[@id='main-content']/div/div/div/h1[contains(text(),'user profile')]")).getText();
		assertEquals(Actual_result, Expected_Result);
	}
}