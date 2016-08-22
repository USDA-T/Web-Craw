package gov.sba.utils;

import static org.junit.Assert.assertEquals;import static org.junit.Assert.assertTrue;
//__Assertions
import java.lang.reflect.Field;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;
//__ Java Utils
import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
//__ Logger
import org.junit.After; import org.junit.Before; import org.junit.Test;
//__ JUnit
import org.openqa.selenium.By; import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebElement;import org.openqa.selenium.remote.server.handler.FindElement;
//__ Selenium
import gov.sba.utils.helpers.Load_Yaml; import gov.sba.utils.helpers.*;import gov.sba.utils.helpers.SBAGlobal;import gov.sba.utils.VerifyEDWOSBFlow;
//_ Project Helpers

public class US942_AddWSOBS {
	//Set The variables/Define
	private static final Logger logger = LogManager.getLogger(UswdsEdwosbScorpTest.class.getName());
	private static WebDriver webDriver;
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		SBAGlobal.set_Driver(webDriver);
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 7;
	}
	
	
	
	
	@Test
	public void mainTest() throws Exception {
		String Actual_Text = null;
		String Expected_Text = null;		
		// Login to dashboard.
		
		   LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		   login_Data.Login_With_Reference();
		   Thread.sleep(3000);
		

		  
		   VerifyEDWOSBFlow VerifyEDWOSBFlow = new VerifyEDWOSBFlow();
		   VerifyEDWOSBFlow.VerifyEDWOSBFlowSetDriver(webDriver);
		   VerifyEDWOSBFlow.VerifyEDWOSBFlowLogic();
	       
		   
		   
          
	 }

	
	@After
	public void tearDown() throws Exception {
		//webDriver.quit();
	}

}
