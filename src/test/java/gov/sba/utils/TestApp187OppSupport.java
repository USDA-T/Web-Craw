package gov.sba.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gov.sba.utils.helpers.DatabaseQuery;
import junit.framework.TestCase;
import java.util.*;
public class TestApp187OppSupport extends TestCase {
	
	// Set The variabl.es/Define
		private static WebDriver webDriver;
		private static final Logger TestApp187OppSupport = LogManager.getLogger(TestApp187OppSupport.class.getName());
		int get_The_Row_From_Login_Data;

		@Before
		public void setUp() throws Exception {

			webDriver = TestHelpers.getDefaultWebDriver();
			webDriver.get(TestHelpers.getBaseUrl());
			webDriver.manage().window().maximize();
			get_The_Row_From_Login_Data = 27;
		}
		@Test
		public void testMainTest() throws Exception {
			// Login to dashboard.
			LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
			login_Data.Login_With_Reference();
			Thread.sleep(3000);
			try {
				 //US1280- Search Government ;
				//Get Email,First,Name,Last from the Db to use as serach term in UI
				// Connect SBAONE QA DB -to get data from DB
		        //To call DB-- pass Sql query, no of rows,no of cols to db function
				//String sql_query = "select email,max_first_name,max_last_name from sbaone.users where max_id is not null";
				//DatabaseQuery dbcall = new DatabaseQuery();
				//String[][] returned_GovProfile_Rows = dbcall.getDBData(sql_query, 2,3);
				//TestApp187OppSupport.info(returned_GovProfile_Rows[1][1]);
				//pass Government/vendor profile criteria
				String Gov_Radio_xpath = "//input[@id='user_type_gov_user']" ;
				String Expected_Result = "Government user profile";
				//TestuserProfileSearchType.TestuserProfileSearch(webDriver, returned_GovProfile_Rows[1][1],Gov_Radio_xpath,Expected_Result);
				TestuserProfileSearchType.TestuserProfileSearch(webDriver, "Analyst",Gov_Radio_xpath,Expected_Result);
				 webDriver.navigate().back();
			    //US1280- Search Vendor ;
				//Get Email,First,Name,Last from the Db to use as serach term in UI
				// Connect SBAONE QA DB -to get data from DB
		        //To call DB-- pass Sql query, no of rows,no of cols to db function
				//sql_query = "select email,first_name,last_name from sbaone.users where max_id is null and email like 'd%'";
				//String[][] areturned_Rows = dbcall.getDBData(sql_query, 2,3);
				//TestApp187OppSupport.info(areturned_Rows[1][1]);
		        //Pass the user search radio button,the serach term to the userprofile search function
				//pass Government/vendor profile criteria
					String Ven_Radio_xpath = "//input[@id='user_type_vendor_user']" ;
					String Expected_Result1 = "Vendor user profile";
					TestuserProfileSearchType.TestuserProfileSearch(webDriver, "Deepa",Ven_Radio_xpath,Expected_Result1);
			} catch (Exception e) {
				TestApp187OppSupport.info(e.toString());
				// TestApp187OppSupport.info("test failed ");
			}
		}

		@After
		public void tearDown() throws Exception {
			//webDriver.quit();
		}

}
