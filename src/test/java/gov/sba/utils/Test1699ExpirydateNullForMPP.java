package gov.sba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import junit.framework.TestCase;

public class Test1699ExpirydateNullForMPP extends TestCase {
	// Set The variabl.es/Define
	private static WebDriver webDriver;
	private static final Logger logger_US1699 = LogManager.getLogger(Test1699ExpirydateNullForMPP.class.getName());
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 10;
	}

	@Test
	public void testMainTest() throws Exception {
		// Login to dashboard.
		@SuppressWarnings("unused")
		Boolean pending_Application_Found = false;
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		try {
			// Check Dashboard Pending status
			String get_Current_Duns_No = webDriver
					.findElement(By
							.xpath("//article[@id='main-content']/section[@class='usa-width-one-whole']/article[@class='usa-width-three-fourths']/div[@class='usa-width-one-whole']/div/div/p/b[contains(text(),'DUNS:')]"))
					.findElement(By.xpath("..")).findElement(By.xpath("span")).getText();
			// logger_US1463.info(get_Current_Duns_No.findElement(By.xpath("..")).getAttribute("innerHTML"));
			logger_US1699.info(get_Current_Duns_No);
			WebElement current_Row_Draft1_A = webDriver.findElement(
					By.xpath("//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP Application')]"));
			WebElement current_Row1_A = current_Row_Draft1_A.findElement(By.xpath("..")).findElement(By.xpath(".."));
			logger_US1699.info(current_Row1_A.getText());
			List<WebElement> all_Cells1_A = current_Row1_A.findElements(By.xpath("td"));
			logger_US1699.info('|' + all_Cells1_A.get(0).getText() + '|');
			logger_US1699.info('|' + all_Cells1_A.get(2).getText() + '|');
			// If Pending - Click and verify the summary page
			// Thread.sleep(999000);
			if (all_Cells1_A.get(3).getText().equals("Pending")
					&& all_Cells1_A.get(0).getText().equals("MPP Application")) {
				// Check Programs Pending status
				webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
				WebElement current_Row_Draft1 = webDriver.findElement(By.xpath(
						"//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP Application')]"));
				WebElement current_Row1 = current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
				logger_US1699.info(current_Row1.getText());
				List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
				logger_US1699.info(all_Cells1.size());
				logger_US1699.info('|' + all_Cells1.get(0).getText() + '|');
				logger_US1699.info('|' + all_Cells1.get(2).getText() + '|');
				String duns_Number_status_To_Verify = all_Cells1.get(3).getText();
				// US1463-If Pending - Click and verify the summary page
				if (all_Cells1.get(3).getText().equals("Pending")
						&& all_Cells1.get(0).getText().equals("MPP Application")) {
					all_Cells1.get(0).findElement(By.xpath("a")).click();
					WebElement current_Title = webDriver.findElement(By.xpath(
							"//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']//div[contains(@class,'wosb_detail_title')]/h1[text()='All Small Mentor Protégé Program Application Summary']"));
					logger_US1699.info(current_Title.getText());
					WebElement current_Title_Business = webDriver.findElement(By.xpath(
							"//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h3[contains(text(),'Entity ') and contains(text(),' Legal Business Name')]"));
					logger_US1699.info(current_Title_Business.getText());
					// Connect SBAONE QA DB -to get data from DB
					String url = "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
					Properties props = new Properties();
					props.setProperty("user", "app_etl");
					props.setProperty("password", "etlpassworddev");
					Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
					logger_US1699.info(connection_SBA_One_Qa);
					Statement statement_SQL = connection_SBA_One_Qa.createStatement();
					ResultSet result_Set = statement_SQL
							.executeQuery("select  C.duns_number  as new_Duns, D.workflow_state  as work_state,"
									+ " B.expiry_date as issue_date,B.expiry_date as expiry_date "
									+ "		from 	sbaone.sba_applications A,	sbaone.certificates B, "
									+ "				sbaone.organizations C,		sbaone.certificates D "
									+ "		where 	A.organization_id = B.organization_id "
									+ "		and   	B.organization_id = D.organization_id "
									+ "		and   	C.id = B.organization_id " + "		and   	C.duns_number = '"
									+ get_Current_Duns_No + "'" + "		and   	D.workflow_state = 'pending';");
					// Code for US 1457 And US 1491
					result_Set.next();
					// -- Get Data from DB to test Pending status validation on
					// UI with DB
					String new_Duns = result_Set.getString("new_Duns");
					String issue_date = result_Set.getString("issue_date");
					String expiry_date = result_Set.getString("expiry_date");
					if (result_Set.wasNull()) {
						Assert.assertEquals("Test case Passed on Issue date For US1491",
								"Test case Passed on Issue date For US1491");
					} else {
						logger_US1699.info(issue_date);
						Assert.assertEquals("Test case Failed For issue date value:", issue_date);
					}
					// Expiry date Null for Mpp application
					if (result_Set.wasNull()) {
						Assert.assertEquals("Test case Passed on Expiry date For US1699",
								"Test case Passed on Expiry date For US1699");
					} else {
						logger_US1699.info(expiry_date);
						Assert.assertEquals("Test case Failed For issue date value:", expiry_date);
					}

					Assert.assertEquals(result_Set.getString("work_state").toLowerCase(),
							duns_Number_status_To_Verify.toLowerCase());
					logger_US1699.info(new_Duns); // Thread.sleep(50000);
					result_Set.close();
				}
			}
			// else Delete it if in Draft all of the Draft applications
			Boolean isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
			logger_US1699.info(isPresent);
			while (isPresent) {
				webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
				webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
				isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
				logger_US1699.info(isPresent);
			}
			// Assert.assertEquals(pending_Application_Found, true);
		} catch (Exception e) {
			logger_US1699.info(e.toString());
			throw new Exception("Error: ", e);
		}
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}
}