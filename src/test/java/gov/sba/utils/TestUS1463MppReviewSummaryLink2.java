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
//	import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.TestCase;

//_ Project Helpers
public class TestUS1463MppReviewSummaryLink2 extends TestCase {
	// Set The variabl.es/Define
	private static WebDriver webDriver;
	private static final Logger logger_US1463 = LogManager.getLogger(TestUS1463MppReviewSummaryLink2.class.getName());
	int get_The_Row_From_Login_Data;

	@Before
	public void setUp() throws Exception {
		webDriver = TestHelpers.getDefaultWebDriver();
		webDriver.get(TestHelpers.getBaseUrl());
		webDriver.manage().window().maximize();
		get_The_Row_From_Login_Data = 9;
	}

	@Test
	public void TestMainTest() throws Exception {
		// Login to dashboard.
		LoginPageWithReference login_Data = new LoginPageWithReference(webDriver, get_The_Row_From_Login_Data);
		login_Data.Login_With_Reference();
		Thread.sleep(3000);
		try {
			webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();

			WebElement current_Row_Draft1 = webDriver.findElement(
					By.xpath("//article[@id='main-content']//table/tbody/tr/td/a[contains(text(),'MPP Application')]"));
			WebElement current_Row1 = current_Row_Draft1.findElement(By.xpath("..")).findElement(By.xpath(".."));
			logger_US1463.info(current_Row1.getText());
			List<WebElement> all_Cells1 = current_Row1.findElements(By.xpath("td"));
			logger_US1463.info(all_Cells1.size());
			logger_US1463.info('|' + all_Cells1.get(0).getText() + '|');
			logger_US1463.info('|' + all_Cells1.get(2).getText() + '|');
			// If Pending - Click and verify the summary page
			if (all_Cells1.get(2).getText().equals("Pending")
					&& all_Cells1.get(0).getText().equals("MPP Application")) {
				all_Cells1.get(0).findElement(By.xpath("a")).click();
				WebElement current_Title = webDriver.findElement(By.xpath(
						"//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']//div[contains(@class,'wosb_detail_title')]/h1[text()='All Small Mentor Protégé Program Application Summary']"));
				logger_US1463.info(current_Title.getText());
				WebElement current_Title_Business = webDriver.findElement(By.xpath(
						"//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h3[contains(text(),'Entity ') and contains(text(),' Legal Business Name')]"));
				logger_US1463.info(current_Title_Business.getText());

				String url = "jdbc:postgresql://sbaonedev.cypwvkg7qp3n.us-east-1.rds.amazonaws.com:5432/sbaone_qa";
				Properties props = new Properties();
				props.setProperty("user", "app_etl");
				props.setProperty("password", "etlpassworddev");
				Connection connection_SBA_One_Qa = DriverManager.getConnection(url, props);
				logger_US1463.info(connection_SBA_One_Qa);

				Statement statement_SQL = connection_SBA_One_Qa.createStatement();
				ResultSet result_Set = statement_SQL
						.executeQuery("select C.duns_number as new_Duns, D.workflow_state as work_state"
								+ "		from 	sbaone.sba_applications A,	sbaone.certificates B, "
								+ "				sbaone.organizations C,		sbaone.certificates D "
								+ "		where 	A.organization_id = B.organization_id "
								+ "		and   	B.organization_id = D.organization_id "
								+ "		and   	C.id = B.organization_id "
								+ "		and   	A.workflow_state = 'submitted';");

				// Code for US 1457 And US 1491
				result_Set.next();
				String new_Duns = result_Set.getString("new_Duns");
				Assert.assertEquals(result_Set.getString("new_Duns"), all_Cells1.get(2).getText());
				logger_US1463.info(new_Duns); // Thread.sleep(50000);

				result_Set.close();

				WebElement current_Duns = webDriver.findElement(By.xpath(
						"//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/b[contains(text(),'DUNS')]"));
				logger_US1463.info(current_Duns.getText());
				WebElement current_Duns_Value = webDriver.findElement(By
						.xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/p/span[contains(text(),'"
								+ new_Duns + "')]"));
				logger_US1463.info(current_Duns_Value.getText());
				String text_To_Find = "Thank you for submitting your application to participate in SBA’s All Small Mentor-Protégé Program. Once your application is processed and evaluated, a member of the All Small Mentor-Protégé Program Office will contact you to verify your application status.";
				WebElement current_Title_Txt = webDriver.findElement(By
						.xpath("//article[@id='main-content']/div[@class='print-summary']/div[@class='wosb-detail-page']/div/div/h4[contains(text(),'"
								+ text_To_Find + "')]"));
				logger_US1463.info(current_Title_Txt.getText());
			} else {
				// else Delete it if in Draft all of the Draft applications
				Boolean isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
				logger_US1463.info(isPresent);
				while (isPresent) {
					webDriver.findElement(By.xpath("//a[@class='delete-cert']")).click();
					webDriver.findElement(By.xpath("//a[@href='/vendor_admin/my_certifications']")).click();
					isPresent = (webDriver.findElements(By.xpath("//a[@class='delete-cert']")).size() > 0);
					logger_US1463.info(isPresent);
				}
			}

		} catch (Exception e) {
			logger_US1463.info(e.toString());
			throw new Exception("Error: ", e);
		}
	}

	@After
	public void tearDown() throws Exception {
		webDriver.quit();
	}

}
