package gov.sba.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import junit.framework.TestCase;

	public class TestApp39OpsSupportDisassociatesUserFromVendor extends TestCase {
	    private static final Logger logger = LogManager.getLogger(TestApp39OpsSupportDisassociatesUserFromVendor.class.getName());
	    private static WebDriver webDriver;
	    int get_The_Row_From_Login_Data;

	    @Before
	    public void setUp() throws Exception {
	        webDriver = TestHelpers.getDefaultWebDriver();
	        webDriver.get(TestHelpers.getBaseUrl());
	        webDriver.manage().window().maximize();
	        get_The_Row_From_Login_Data = 37;

	    }

	    @Test
	    public void testMainTest() throws Exception {
	        String Actual_Text = null;
	        String Expected_Text = null;
	        logger.info("Second Vendor (Requesting access to a business that has already been claim) gets disassociated from the business");
	        
	        // Login to dashboard.
	        LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
	        login_Data.Login_With_Reference();
	        Thread.sleep(3000);
	        if (webDriver.getPageSource().contains("Request access")) {
	            logger.info("No business has been claimed, a new business will be claimed now.");
	        // Select Vendor Admin for a business that is new to certify.SBA.gov.
	        webDriver.findElement(By.id("request_new_application")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        // Enter a valid DUN#, TIN# and MPIN# and connect to business.
	        webDriver.findElement(By.id("search_duns_number")).sendKeys("142762936");
	        webDriver.findElement(By.id("search_ssn_ein")).sendKeys("123456789");
	        webDriver.findElement(By.id("search_mpin")).sendKeys("A12345678");
	        // Click on the Find Business Button to validate error message.
	        webDriver.findElement(By.xpath("//button[@id='find_business']")).click();
	        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
	        Expected_Text = "Entity 214 Legal Business Name";
	        assertEquals(Actual_Text, Expected_Text);
	        Thread.sleep(2000);
	        webDriver.findElement(By.id("business_type")).click();
	        Thread.sleep(3000);
	        Actions act = new Actions(webDriver);
	        act.doubleClick(webDriver.findElement(By.xpath("//option[@value='s-corp']"))).build().perform();
	        Thread.sleep(3000);
	        webDriver.findElement(By.id("connect")).click();
	        webDriver.switchTo().alert().accept();
	        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
	        Expected_Text = "You have been successfully associated with the organization.";
	        assertEquals(Actual_Text, Expected_Text);
	        //Verify admin profile.
	        webDriver.findElement(By.linkText("Profile")).click();
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "blank3 Steve";
	        assertEquals(Actual_Text, Expected_Text);
	        //Logout.
	        webDriver.findElement(By.linkText("Logout")).click();
	        //Login to the already claim business and request a new role.
	        get_The_Row_From_Login_Data = 38;
	        LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
	        login_Data1.Login_With_Reference();
	        //Select Vendor Admin for a business that is new to certify.SBA.gov.
	        webDriver.findElement(By.id("request_request_vendor_admin")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
	        Expected_Text = "Request access to view records";
	        assertEquals(Actual_Text, Expected_Text);
	        // Enter a valid DUN#, TIN# and MPIN# and connect to business.
	        webDriver.findElement(By.id("duns_number")).sendKeys("142762936");
	        webDriver.findElement(By.id("find_business")).click();
	        webDriver.findElement(By.id("request")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
	        Expected_Text = "Access Request was successfully created.";
	        assertEquals(Actual_Text, Expected_Text);
	        //Login as the vendor admin and accept the request.
	        webDriver.findElement(By.linkText("Logout")).click();
	      //Login to the already claim business and request a new role.
	        get_The_Row_From_Login_Data = 37;
	        LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
	        login_Data2.Login_With_Reference();
	        webDriver.findElement(By.linkText("Business")).click();
	        webDriver.findElement(By.linkText("Manage employee access")).click();
	        webDriver.findElement(By.xpath("//input[@value='Accept']")).click();
	        webDriver.switchTo().alert().accept();
	        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
	        Expected_Text = "Access request has been accepted";
	        assertEquals(Actual_Text, Expected_Text);
	       //Logout and login with the requested account.
	        webDriver.findElement(By.linkText("Logout")).click();
		    get_The_Row_From_Login_Data = 38;
		    LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		    login_Data3.Login_With_Reference();
		    Actual_Text = webDriver.findElement(By.xpath("//a[contains(text(),'My business')]")).getText();
	        Expected_Text = "My business";
	        assertEquals(Actual_Text, Expected_Text);
	        //Login as an Ops support staff and disassociate the added user.
	        webDriver.findElement(By.linkText("Logout")).click();
		    get_The_Row_From_Login_Data = 39;
		    LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		    login_Data4.Login_With_Reference();
		    Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
	        Expected_Text = "User search";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.id("user_type_vendor_user")).click();
	        webDriver.findElement(By.id("ops_query")).sendKeys("norole3@mailinator.com");
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.linkText("blank3 Steve")).getText();
	        Expected_Text = "blank3 Steve";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.linkText("blank3 Steve")).click();
	        Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
	        Expected_Text = "Disassociate";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.linkText("Disassociate")).click();
	        webDriver.switchTo().alert().accept();
	        //Click on the dashboard and disassociate the other vendor admin.
	        webDriver.findElement(By.linkText("Dashboard")).click();
	        webDriver.findElement(By.id("user_type_vendor_user")).click();
	        webDriver.findElement(By.id("ops_query")).sendKeys("norole9@mailinator.com");
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
	        Actual_Text = webDriver.findElement(By.linkText("blank9 Steve")).getText();
	        Expected_Text = "blank9 Steve";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.linkText("blank9 Steve")).click();
	        Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
	        Expected_Text = "Disassociate";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.linkText("Disassociate")).click();
	        webDriver.switchTo().alert().accept();
	        //Logout and try logging with the disassociated user and verify if user was successfully removed from the business.
	        webDriver.findElement(By.linkText("Logout")).click();
		    get_The_Row_From_Login_Data = 38;
		    LoginPageWithReference login_Data5 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		    login_Data5.Login_With_Reference();
		    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Request access";
	        assertEquals(Actual_Text, Expected_Text);
	        //Verify that the other vendor admin is successfully disassociated from the business.
	        webDriver.findElement(By.linkText("Logout")).click();
	        get_The_Row_From_Login_Data = 37;
	        LoginPageWithReference login_Data6 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
	        login_Data6.Login_With_Reference();
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Request access";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.linkText("Logout")).click();
	        } else {
		        if (webDriver.getPageSource().contains("My business")) {
		        	//Login as an Ops support staff and disassociate the vendor admin.
			        webDriver.findElement(By.linkText("Logout")).click();
				    get_The_Row_From_Login_Data = 39;
				    LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				    login_Data4.Login_With_Reference();
				    Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
			        Expected_Text = "User search";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.id("user_type_vendor_user")).click();
			        webDriver.findElement(By.id("ops_query")).sendKeys("norole3@mailinator.com");
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        Actual_Text = webDriver.findElement(By.linkText("blank3 Steve")).getText();
			        Expected_Text = "blank3 Steve";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("blank3 Steve")).click();
			        Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
			        Expected_Text = "Disassociate";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("Disassociate")).click();
			        webDriver.switchTo().alert().accept();
			        //Click on the dashboard and disassociate the other vendor admin.
			        webDriver.findElement(By.linkText("Dashboard")).click();
			        webDriver.findElement(By.id("user_type_vendor_user")).click();
			        webDriver.findElement(By.id("ops_query")).sendKeys("norole9@mailinator.com");
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        Actual_Text = webDriver.findElement(By.linkText("blank9 Steve")).getText();
			        Expected_Text = "blank9 Steve";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("blank9 Steve")).click();
			        Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
			        Expected_Text = "Disassociate";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("Disassociate")).click();
			        webDriver.switchTo().alert().accept();
			        
			        
			        webDriver.findElement(By.linkText("Logout")).click();
			        // Login to dashboard.
			        LoginPageWithReference login_Data8 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			        login_Data8.Login_With_Reference();
			        Thread.sleep(3000);
			        logger.info("No business has been claimed, a new business will be claimed now.");
			        // Select Vendor Admin for a business that is new to certify.SBA.gov.
			        webDriver.findElement(By.id("request_new_application")).click();
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        // Enter a valid DUN#, TIN# and MPIN# and connect to business.
			        webDriver.findElement(By.id("search_duns_number")).sendKeys("142762936");
			        webDriver.findElement(By.id("search_ssn_ein")).sendKeys("123456789");
			        webDriver.findElement(By.id("search_mpin")).sendKeys("A12345678");
			        // Click on the Find Business Button to validate error message.
			        webDriver.findElement(By.xpath("//button[@id='find_business']")).click();
			        Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
			        Expected_Text = "Entity 214 Legal Business Name";
			        assertEquals(Actual_Text, Expected_Text);
			        Thread.sleep(2000);
			        webDriver.findElement(By.id("business_type")).click();
			        Thread.sleep(3000);
			        Actions act = new Actions(webDriver);
			        act.doubleClick(webDriver.findElement(By.xpath("//option[@value='s-corp']"))).build().perform();
			        Thread.sleep(3000);
			        webDriver.findElement(By.id("connect")).click();
			        webDriver.switchTo().alert().accept();
			        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			        Expected_Text = "You have been successfully associated with the organization.";
			        assertEquals(Actual_Text, Expected_Text);
			        //Verify admin profile.
			        webDriver.findElement(By.linkText("Profile")).click();
			        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			        Expected_Text = "blank3 Steve";
			        assertEquals(Actual_Text, Expected_Text);
			        //Logout.
			        webDriver.findElement(By.linkText("Logout")).click();
			        //Login to the already claim business and request a new role.
			        get_The_Row_From_Login_Data = 38;
			        LoginPageWithReference login_Data1 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			        login_Data1.Login_With_Reference();
			        //Select Vendor Admin for a business that is new to certify.SBA.gov.
			        webDriver.findElement(By.id("request_request_vendor_admin")).click();
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        Actual_Text = webDriver.findElement(By.cssSelector("h1")).getText();
			        Expected_Text = "Request access to view records";
			        assertEquals(Actual_Text, Expected_Text);
			        // Enter a valid DUN#, TIN# and MPIN# and connect to business.
			        webDriver.findElement(By.id("duns_number")).sendKeys("142762936");
			        webDriver.findElement(By.id("find_business")).click();
			        webDriver.findElement(By.id("request")).click();
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			        Expected_Text = "Access Request was successfully created.";
			        assertEquals(Actual_Text, Expected_Text);
			        //Login as the vendor admin and accept the request.
			        webDriver.findElement(By.linkText("Logout")).click();
			      //Login to the already claim business and request a new role.
			        get_The_Row_From_Login_Data = 37;
			        LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			        login_Data2.Login_With_Reference();
			        webDriver.findElement(By.linkText("Business")).click();
			        webDriver.findElement(By.linkText("Manage employee access")).click();
			        webDriver.findElement(By.xpath("//input[@value='Accept']")).click();
			        webDriver.switchTo().alert().accept();
			        Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
			        Expected_Text = "Access request has been accepted";
			        assertEquals(Actual_Text, Expected_Text);
			       //Logout and login with the requested account.
			        webDriver.findElement(By.linkText("Logout")).click();
				    get_The_Row_From_Login_Data = 38;
				    LoginPageWithReference login_Data3 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				    login_Data3.Login_With_Reference();
				    Actual_Text = webDriver.findElement(By.xpath("//a[contains(text(),'My business')]")).getText();
			        Expected_Text = "My business";
			        assertEquals(Actual_Text, Expected_Text);
			        //Login as an Ops support staff and disassociate the added user.
			        webDriver.findElement(By.linkText("Logout")).click();
				    get_The_Row_From_Login_Data = 39;
				    LoginPageWithReference login_Data7 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				    login_Data7.Login_With_Reference();
				    Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
			        Expected_Text = "User search";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.id("user_type_vendor_user")).click();
			        webDriver.findElement(By.id("ops_query")).sendKeys("norole3@mailinator.com");
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        Actual_Text = webDriver.findElement(By.linkText("blank3 Steve")).getText();
			        Expected_Text = "blank3 Steve";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("blank3 Steve")).click();
			        Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
			        Expected_Text = "Disassociate";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("Disassociate")).click();
			        webDriver.switchTo().alert().accept();
			        //Click on the dashboard and disassociate the other vendor admin.
			        webDriver.findElement(By.linkText("Dashboard")).click();
			        webDriver.findElement(By.id("user_type_vendor_user")).click();
			        webDriver.findElement(By.id("ops_query")).sendKeys("norole9@mailinator.com");
			        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
			        Actual_Text = webDriver.findElement(By.linkText("blank9 Steve")).getText();
			        Expected_Text = "blank9 Steve";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("blank9 Steve")).click();
			        Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
			        Expected_Text = "Disassociate";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("Disassociate")).click();
			        webDriver.switchTo().alert().accept();
			        //Logout and try logging with the disassociated user and verify if user was successfully removed from the business.
			        webDriver.findElement(By.linkText("Logout")).click();
				    get_The_Row_From_Login_Data = 38;
				    LoginPageWithReference login_Data5 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
				    login_Data5.Login_With_Reference();
				    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			        Expected_Text = "Request access";
			        assertEquals(Actual_Text, Expected_Text);
			        //Verify that the other vendor admin is successfully disassociated from the business.
			        webDriver.findElement(By.linkText("Logout")).click();
			        get_The_Row_From_Login_Data = 37;
			        LoginPageWithReference login_Data6 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			        login_Data6.Login_With_Reference();
			        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
			        Expected_Text = "Request access";
			        assertEquals(Actual_Text, Expected_Text);
			        webDriver.findElement(By.linkText("Logout")).click();
		        	
		        }
	        else {
	        
	        logger.info("The disassociated business is about to be reclaimed.");
	        //Verify admin profile.
	        Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
	        Expected_Text = "Request access";
	        assertEquals(Actual_Text, Expected_Text);
	        webDriver.findElement(By.id("request_new_application")).click();
	        webDriver.findElement(By.xpath("//input[@name='commit']")).click();
		    //Enter a valid DUN#, TIN# and MPIN# and connect to business.
		    webDriver.findElement(By.id("search_duns_number")).sendKeys("142762936");
		    webDriver.findElement(By.id("search_ssn_ein")).sendKeys("123456789");
		    webDriver.findElement(By.id("search_mpin")).sendKeys("A12345678");
		    //Click on the Find Business Button to validate error message.
		    webDriver.findElement(By.xpath("//button[@id='find_business']")).click();
		    Actual_Text = webDriver.findElement(By.cssSelector("h4")).getText();
		    Expected_Text = "Entity 214 Legal Business Name";
		    assertEquals(Actual_Text, Expected_Text);
		    Thread.sleep(2000);
		    webDriver.findElement(By.id("business_type")).click();
		    Thread.sleep(3000);
		    Actions act = new Actions(webDriver);
		    act.doubleClick(webDriver.findElement(By.xpath("//option[@value='s-corp']"))).build().perform();
		    Thread.sleep(3000);
		    webDriver.findElement(By.id("connect")).click();
		    webDriver.switchTo().alert().accept();
		    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		    Expected_Text = "You have been successfully associated with the organization.";
		    assertEquals(Actual_Text, Expected_Text);
		    //Verify admin profile.
		    webDriver.findElement(By.linkText("Profile")).click();
		    Actual_Text = webDriver.findElement(By.cssSelector("h2")).getText();
		    Expected_Text = "blank3 Steve";
		    assertEquals(Actual_Text, Expected_Text);
		    //Login as an Ops support staff and disassociate the Vendor who just claimed the business.
		    webDriver.findElement(By.linkText("Logout")).click();
			get_The_Row_From_Login_Data = 39;
			LoginPageWithReference login_Data4 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data4.Login_With_Reference();
			Actual_Text = webDriver.findElement(By.cssSelector("h3")).getText();
		    Expected_Text = "User search";
		    assertEquals(Actual_Text, Expected_Text);
		    webDriver.findElement(By.id("user_type_vendor_user")).click();
		    webDriver.findElement(By.id("ops_query")).sendKeys("norole3@mailinator.com");
		    webDriver.findElement(By.xpath("//input[@name='commit']")).click();
		    Actual_Text = webDriver.findElement(By.linkText("blank3 Steve")).getText();
		    Expected_Text = "blank3 Steve";
		    assertEquals(Actual_Text, Expected_Text);
		    webDriver.findElement(By.linkText("blank3 Steve")).click();
		    Actual_Text = webDriver.findElement(By.linkText("Disassociate")).getText();
		    Expected_Text = "Disassociate";
		    assertEquals(Actual_Text, Expected_Text);
		    webDriver.findElement(By.linkText("Disassociate")).click();
		    webDriver.switchTo().alert().accept();
		    Actual_Text = webDriver.findElement(By.cssSelector("td")).getText();
		    Expected_Text = "This user does not have any assignments";
		    assertEquals(Actual_Text, Expected_Text);
		    //Login with the disassociated vendor and verify if vendor was successfully disassociated.
		    webDriver.findElement(By.linkText("Logout")).click();	
		    get_The_Row_From_Login_Data = 37;
		    LoginPageWithReference login_Data2 = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		    login_Data2.Login_With_Reference();
		    Actual_Text = webDriver.findElement(By.cssSelector("p.usa-alert-text")).getText();
		    Expected_Text = "Please select at least one business to associate to your application.";
		    assertEquals(Actual_Text, Expected_Text);
		    webDriver.findElement(By.linkText("Logout")).click();	
		    logger.info("Success");
	        }
	    }
	    }
	    @After
	    public void tearDown() throws Exception {
	        webDriver.quit();
	    }
	}

